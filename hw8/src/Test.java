import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yang Chi-Chang on 2016/5/6.
 */
public class Test {
    public static void main(String[] args) {
        try {
            int count = 100;
            String inTxt = "hw8.0001.r03525008.in.txt";
            String outTxt = "hw8.0001.r03525008.out.txt";

            FileWriter fw = new FileWriter(inTxt);
            fw.write(count+System.getProperty("line.separator"));
            for (int i = 0; i < count; i++) {
                fw.write(Math.random() + " " + Math.random() +System.getProperty("line.separator"));
            }
            fw.close();

            String outputContent = getOutputString(inTxt);
            fw = new FileWriter(outTxt);
            fw.write(outputContent);
            fw.close();

        } catch ( IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static String getOutputString(String filename) throws IOException {

            List<Clustering> clusterings = new ArrayList<Clustering>();

            BufferedReader br = new BufferedReader(new FileReader(filename));
            int count = Integer.parseInt(br.readLine());
            for (int i = 0; i < count; i++) {
                String[] line = br.readLine().split("\\s");
                Clustering.Point p = new Clustering.Point(Double.parseDouble(line[0]) , Double.parseDouble(line[1]));
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
                    for (Clustering.Point p1 : points1) {
                        for (Clustering.Point p2 : points2) {
                            if (p1.distanceTo(p2) < min) {
                                min = p1.distanceTo(p2);
                            }
                        }
                    }
                }
            }

            Clustering[] clusteringsArray = clusterings.toArray(new Clustering[clusterings.size()]);
            Arrays.sort(clusteringsArray);

            String output = "";
            for(Clustering c:clusteringsArray){
                output += String.format("%d %.2f %.2f",c.size() , c.getX() , c.getY() ) + System.getProperty("line.separator");
            }
            output += String.format("%.2f", min);

            return output;
    }

    public static class Clustering implements Comparable<Clustering> {

        private double x;
        private double y;
        private List<Point> points = new ArrayList<Point>();

        Clustering(){ }

        Clustering(Point p){
            points.add(p);
            x = p.getX();
            y = p.getY();
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

    }



}
