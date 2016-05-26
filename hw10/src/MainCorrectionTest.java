import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Yang, Chi-Chang on 2016/5/26.
 */
public class MainCorrectionTest {
    public static void main(String[] args) {

        int num = 1000;
        int k = 10;

        Point2D[] points = new Point2D[num];
        for (int i = 0; i < num; i++) {
            points[i]  = new Point2D(Math.random() , Math.random());
        }

        FindNeighbors findNeighbors = new FindNeighbors();
        findNeighbors.init(points);

        Point2D target = new Point2D(Math.random() , Math.random());
        Point2D[] result = findNeighbors.query(target , k);
        Point2D[] result2 = sort(points, target);

        boolean needToWrite = false;
        System.out.println("i\tyour\t\t\tcorrect\t\t\tthe same");
        for (int i = 0; i < result.length; i++) {
            String p1 = String.format("(%.3f,%.3f)",result[i].x(),result[i].y());
            String p2 = String.format("(%.3f,%.3f)",result2[i].x(),result2[i].y());
            System.out.println(i + "\t" + p1 + "\t" + p2 + "\t" + p1.equals(p2));
            if (!p1.equals(p2)) needToWrite = true;
        }

        if (needToWrite) {
            try {
                FileWriter fw = new FileWriter("temp.txt");
                fw.write(num + System.getProperty("line.separator"));
                fw.write("query point:" + System.getProperty("line.separator"));
                fw.write(target.x() + "," + target.y() + System.getProperty("line.separator"));
                fw.write("sample point:" + System.getProperty("line.separator"));
                for(Point2D p:points){
                    fw.write(p.x() + "," + p.y() + System.getProperty("line.separator"));
                }
                fw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

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
