import java.util.*;

/**
 * Created by Yang Chi-Chang on 2016/4/11.
 */
public class MyConvexHull {

    public static void main(String[] args) {


    }


    public static int[] ConvexHullVertex(Point2D[] points) {


        ArrayList<Point2D> array = new ArrayList<Point2D>();
        for(Point2D p:points){
            array.add(p);
        }

        int min = findMinY(points);

        Point2D start = points[min];
//        Arrays.sort(points, start.ATAN2_ORDER);
        quickSort(points, start.ATAN2_ORDER);

        Stack<Point2D> convexhull = new Stack<Point2D>();
        convexhull.push(points[0]);
        convexhull.push(points[1]);
        for(int i = 2 ; i < points.length ; i++){
            Point2D b = convexhull.pop();
            Point2D a = convexhull.pop();
            Point2D c = points[i];
            while (Point2D.ccw(a,b,c)!=1) {
                b = a;
                a = convexhull.pop();
            }
            convexhull.push(a);
            convexhull.push(b);
            convexhull.push(c);
        }

        Stack<Point2D> reverse = new Stack<Point2D>();
        while(!convexhull.isEmpty())
            reverse.push(convexhull.pop());
        convexhull = reverse;

        Iterator<Point2D> iterator = convexhull.iterator();
        int count = 0;
        int[] index = new int[convexhull.size()];
        while (iterator.hasNext()) {
            Point2D p = iterator.next();
            index[count++] = array.indexOf(p);
        }
        return index;
    }


    public static int findMinY (Point2D[] points){
        int min = 0;
        for(int i = 0 ; i < points.length ; i++){
            if(Point2D.Y_ORDER.compare(points[min] , points[i])==1)
                min = i;
        }
        return min;
    }

    public static void quickSort(Point2D[] array , Comparator<Point2D> c) {
        quickSortKit(array , c , 0 , array.length-1);
    }

    public static void quickSortKit(Point2D[] array , Comparator<Point2D> c , int start , int end) {

        if(start >= end) return;
        Point2D pivot = array[start];
        int left = start;
        int right = end+1;

        while (true){
            while (c.compare(pivot , array[++left]) == 1)
                if (left == end) break;
            while (c.compare(pivot , array[--right]) == -1)
                if (right == start) break;

            if(left >= right) break;

            Point2D temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }
        array[start] = array[right];
        array[right] = pivot;

        quickSortKit(array , c , start , right-1);
        quickSortKit(array , c , right+1 , end);

    }

}
