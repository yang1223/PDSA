import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Created by Yang Chi-Chang on 2016/5/25.
 */
public class MainTimeTest {
    public static void main(String[] args) {

        int num = 100000;
        int k = 10;

        Calendar c;
        long start;
        long end;
        Point2D[] points = new Point2D[num];
        for (int i = 0; i < num; i++) {
            points[i]  = new Point2D(Math.random() , Math.random());
        }
        Point2D target = new Point2D(Math.random() , Math.random());

        FindNeighbors findNeighbors = new FindNeighbors();

        c = Calendar.getInstance();
        start = c.getTimeInMillis();
        findNeighbors.init(points);
        findNeighbors.query(target, k);
        c = Calendar.getInstance();
        end = c.getTimeInMillis();
        System.out.println((end-start)/1000.0);

        c = Calendar.getInstance();
        start = c.getTimeInMillis();
        sort(points, target);
        c = Calendar.getInstance();
        end = c.getTimeInMillis();
        System.out.println((end-start)/1000.0);

    }

    public static Point2D[] sort(Point2D[] points , final Point2D target){
        Point2D[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy, new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                double d1 = o1.distanceTo(target);
                double d2 = o2.distanceTo(target);
                if (d1 > d2) return 1;
                else if (d1 < d2) return -1;
                return 0;
            }
        });
        return pointsCopy;
    }

}
