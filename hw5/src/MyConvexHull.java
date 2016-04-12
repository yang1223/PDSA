import java.util.*;

/**
 * Created by Yang Chi-Chang on 2016/4/11.
 */
public class MyConvexHull {

    public static void main(String[] args) {

        double pointSize = 0.008;
        int num = 10;
        ArrayList<Point2D> points = new ArrayList<Point2D>();

        Stack<Point2D> convexhull = new Stack<Point2D>();

        for(int i = 0; i < num ; i++)
            points.add(new Point2D(StdRandom.uniform(),StdRandom.uniform()));

        int min = findMinY(points);

        convexhull.push(points.get(min));

        ArrayList<Double> angle = new ArrayList<Double>();

        for(int i = 0 ; i < points.size() ; i++){
            Point2D p = points.get(i);

        }


        Point2D p1 = new Point2D(0.5,0.1);
        Point2D p2 = new Point2D(0.8,0.4);
        Point2D p3 = new Point2D(0.6,0.7);
//        p1.drawTo(p2);
//        p2.drawTo(p3);
//        System.out.println(Point2D.ccw(p1, p2, p3));
//        System.out.println(Point2D.Y_ORDER.compare(p2,p1));




        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(-0.1, 1.1);
        StdDraw.setYscale(-0.1, 1.1);

        StdDraw.setPenColor(StdDraw.BOOK_BLUE);

        for(int i = 0 ; i < points.size() ; i++){
            Point2D p = points.get(i);

            StdDraw.setPenRadius(.025);
            p.draw();
            StdDraw.text(p.x() + 0.015 , p.y() + 0.015 , Integer.toString(i) );


        }

        StdDraw.setPenColor(StdDraw.MAGENTA);
        points.get(min).draw();

//        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
//        StdDraw.setPenRadius(.007);
//        StdDraw.line(0.76,0.52,0.21,0.6);

    }

    public static int findMinY (ArrayList<Point2D> points){
        int min = 0;
        for(int i = 0 ; i < points.size() ; i++){
            if(Point2D.Y_ORDER.compare(points.get(min),points.get(i))==1)
                min = i;
        }
        return min;
    }



}
