import java.util.ArrayList;
import java.util.Comparator;
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

    public int size(){ return points.size(); }
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
        public double getX(){return x;}
        public double getY(){return y;}
        public double distanceTo(Point that){
            return Math.sqrt( (this.x-that.x)*(this.x-that.x) + (this.y-that.y)*(this.y-that.y) );
        }
    }


}
