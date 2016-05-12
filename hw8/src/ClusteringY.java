import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yang, Chi-Chang on 2016/5/5.
 */
public class ClusteringY implements Comparable<ClusteringY> {

    private double x;
    private double y;
    private List<Point> points = new ArrayList<Point>();

    private static boolean draw;
    public static void setDraw(boolean draw) {
        ClusteringY.draw = draw;
        if (draw){
            StdDraw.setCanvasSize(800,800);
            StdDraw.setXscale(-0.1,1.1);
            StdDraw.setYscale(-0.1, 1.1);
            StdDraw.setPenRadius(0.02);
        }
    }

    ClusteringY(){ }

    ClusteringY(Point p){
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

    public static ClusteringY merge(ClusteringY c1 , ClusteringY c2){
        ClusteringY clustering = new ClusteringY();
        double sumX = 0.0;
        double sumY = 0.0;
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

    public double distanceTo(ClusteringY that){
        return Math.sqrt( (this.x-that.x)*(this.x-that.x) + (this.y-that.y)*(this.y-that.y) );
    }

    @Override
    public int compareTo(ClusteringY that) {
        if (this.size() < that.size()) return 1;
        else if (this.size() > that.size()) return -1;
        else if (this.getX() > that.getX()) return 1;
        else if (this.getX() < that.getX()) return -1;
        else if (this.getY() > that.getY()) return 1;
        else if (this.getY() < that.getY()) return -1;
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
            ClusteringY.setDraw(isDraw);
            List<ClusteringY> clusterings = new ArrayList<ClusteringY>();

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            int count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                String[] line = br.readLine().split("\\s");
                Point p = new Point(Double.parseDouble(line[0]) , Double.parseDouble(line[1]));
                clusterings.add( new ClusteringY(p) );
            }
            br.close();

            if (clusterings.size() == 0) {
                System.out.println("0.00");
            } else if (clusterings.size() == 1) {
                ClusteringY c = clusterings.get(0);
                System.out.println(String.format("%d %.2f %.2f", c.size(), c.getX(), c.getY()));
                System.out.println("0.00");
            } else if (clusterings.size() == 2) {
                ClusteringY.Point point1 = clusterings.get(0).getPoints().get(0);
                ClusteringY.Point point2 = clusterings.get(1).getPoints().get(0);
                double min = point1.distanceTo(point2);
                ClusteringY[] clusteringsArray = clusterings.toArray(new ClusteringY[2]);
                Arrays.sort(clusteringsArray);
                for(ClusteringY c:clusteringsArray){
                    System.out.println(String.format("%d %.2f %.2f" , c.size() , c.getX() , c.getY() ));
                }
                System.out.println(String.format("%.2f", min));

            } else {
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
                    clusterings.add(ClusteringY.merge(clusterings.remove(min2), clusterings.remove(min1)));
                }

                ClusteringY.Point point1 = clusterings.get(0).getPoints().get(0);
                ClusteringY.Point point2 = clusterings.get(1).getPoints().get(0);
                double min = point1.distanceTo(point2);
                for (int i = 0; i < clusterings.size(); i++) {
                    List<ClusteringY.Point> points1 = clusterings.get(i).getPoints();
                    for (int j = i+1 ; j < clusterings.size(); j++) {
                        List<ClusteringY.Point> points2 = clusterings.get(j).getPoints();
                        for (ClusteringY.Point p1 : points1) {
                            for (ClusteringY.Point p2 : points2) {
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

                ClusteringY[] clusteringsArray = clusterings.toArray(new ClusteringY[clusterings.size()]);
                Arrays.sort(clusteringsArray);

                for(ClusteringY c:clusteringsArray){
                    System.out.println(String.format("%d %.2f %.2f" , c.size() , c.getX() , c.getY() ));
                }
                System.out.println(String.format("%.2f", min));
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
