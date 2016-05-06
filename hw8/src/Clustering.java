import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yang, Chi-Chang on 2016/5/5.
 */
public class Clustering implements Comparable<Clustering> {

    private double x;
    private double y;
    private List<Point> points = new ArrayList<Point>();

    private static boolean draw;
    public static void setDraw(boolean draw) {
        Clustering.draw = draw;
        if (draw){
            StdDraw.setCanvasSize(800,800);
            StdDraw.setXscale(-0.1,1.1);
            StdDraw.setYscale(-0.1, 1.1);
            StdDraw.setPenRadius(0.02);
        }
    }

    Clustering(){ }

    Clustering(Point p){
        points.add(p);
        x = p.getX();
        y = p.getY();
        if(draw){
            StdDraw.setPenRadius(0.002);
            StdDraw.filledCircle(x, y, 0.008);
        }
    }

    public int size() { return points.size(); }
    public double getX() { return this.x; }
    public double getY() { return this.y; }
    public List<Point> getPoints() { return points; }

    public static Clustering merge(Clustering c1 , Clustering c2){
        Clustering clustering = new Clustering();
        double sumX = 0;
        double sumY = 0;
        for(Point p:c1.points){
            clustering.points.add(p);
            sumX += p.getX();
            sumY += p.getY();
        }
        for(Point p:c2.points){
            clustering.points.add(p);
            sumX += p.getX();
            sumY += p.getY();
        }
        clustering.x = sumX/clustering.points.size();
        clustering.y = sumY/clustering.points.size();
        if(draw){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(clustering.x , clustering.y , 0.008+0.002*clustering.points.size());
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.line(c1.x, c1.y, c2.x, c2.y);
        }
        return clustering;
    }

    public double distanceTo(Clustering that){
        return Math.sqrt( (this.x-that.x)*(this.x-that.x) + (this.y-that.y)*(this.y-that.y) );
    }

    @Override
    public int compareTo(Clustering o) {
        if(this.size() < o.size()) return 1;
        else if(this.size() > o.size()) return -1;
        else return 0;
    }


    public static class Point {
        private double x;
        private double y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        public double getX(){ return x; }
        public double getY(){ return y; }
        public double distanceTo(Point that){
            return Math.sqrt( (this.x-that.x)*(this.x-that.x) + (this.y-that.y)*(this.y-that.y) );
        }
    }


    public static void main(String[] args) {
        try {

            boolean isDraw = false;
            Clustering.setDraw(isDraw);
            List<Clustering> clusterings = new ArrayList<Clustering>();

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            int count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                String[] line = br.readLine().split("\\s");
                Point p = new Point(Double.parseDouble(line[0]) , Double.parseDouble(line[1]));
                clusterings.add( new Clustering(p));
            }
            br.close();

            while(clusterings.size() > 3){
                double minDistance = clusterings.get(0).distanceTo(clusterings.get(1));
                int min1 = 0;
                int min2 = 1;
                for (int i = 0; i < clusterings.size(); i++) {
                    for (int j = i+1 ; j < clusterings.size(); j++) {
                        double newDistance = clusterings.get(i).distanceTo(clusterings.get(j));
                        if (minDistance > newDistance){
                            minDistance = newDistance;
                            min1 = i;
                            min2 = j;
                        }
                    }
                }
                clusterings.add(Clustering.merge(clusterings.remove(min2) , clusterings.remove(min1)));
            }


            Clustering.Point point1 = clusterings.get(0).getPoints().get(0);
            Clustering.Point point2 = clusterings.get(1).getPoints().get(0);
            double min = point1.distanceTo(point2);
            for (int i = 0; i < clusterings.size(); i++) {
                List<Clustering.Point> points1 = clusterings.get(i).getPoints();
                for (int j = i+1 ; j < clusterings.size(); j++) {
                    List<Clustering.Point> points2 = clusterings.get(j).getPoints();
                    for (Point p1 : points1) {
                        for (Point p2 : points2) {
                            if (p1.distanceTo(p2) < min) {
                                min = p1.distanceTo(p2);
                                point1 = p1;
                                point2 = p2;
                            }
                        }
                    }
                }
            }

            if(isDraw){
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(point1.getX() , point1.getY() , point2.getX() , point2.getY());
            }

            Clustering[] clusteringsArray = clusterings.toArray(new Clustering[clusterings.size()]);
            Arrays.sort(clusteringsArray);

            for(Clustering c:clusteringsArray){
                System.out.println(String.format("%d %.2f %.2f",c.size() , c.getX() , c.getY() ));
            }
            System.out.println(String.format("%.2f", min));


        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
