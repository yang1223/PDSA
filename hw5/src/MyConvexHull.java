import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Yang Chi-Chang on 2016/4/11.
 */
public class MyConvexHull {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            double distance = Double.parseDouble(br.readLine());
            int num = Integer.parseInt(br.readLine());

            distance = 0.3;
            num = 12;
            Point2D[] points = new Point2D[num];

            for(int i = 0  ; i < num ; i++){
//                String[] coordinate = br.readLine().split("\\s");
//                double x = Double.parseDouble(coordinate[0]);
//                double y = Double.parseDouble(coordinate[1]);
//                points[i] = new Point2D(x,y);
                points[i] = new Point2D(Math.random(),Math.random());
                System.out.println(String.format("%d:(%.3f,%.3f)",i,points[i].x(),points[i].y()));
            }


            int[] parents = new int[num];
            for(int i = 0 ; i < parents.length ; i++)
                parents[i] = i;
//            printArray(parents);

            for(int i = 0 ; i < num ; i++){
                Point2D p1 = points[i];
                for(int j = 0 ; j < num ; j++){
                    Point2D p2 = points[j];
                    if(p1.distanceTo(p2) < distance){
                        int root1 = getRoot(parents,i);
                        int root2 = getRoot(parents,j);
                        if(root1<=root2)
                            parents[root2] = root1;
                        else
                            parents[root1] = root2;
                    }
                }
            }

            for(int i = 0 ; i < parents.length ; i++){
                parents[i] = getRoot(parents,i);
            }

            System.out.print("label:");
            printArray(parents);

            Map<Integer,Integer> map = new HashMap<Integer,Integer>();
            for(int i:parents){
                if (map.containsKey(i)) {
                    map.put(i , map.get(i)+1);
                } else {
                    map.put(i , 1);
                }
            }

            StdDraw.setCanvasSize(800,800);
            StdDraw.setXscale(-0.1,1.1);
            StdDraw.setYscale(-0.1,1.1);
            StdDraw.setPenRadius(0.02);
            for(int i = 0 ; i < num ; i++) {
                Point2D p1 = points[i];
                p1.draw();
                StdDraw.setPenRadius(0.002);
                StdDraw.circle(p1.x(), p1.y(), distance / 2.0);
                StdDraw.setPenRadius(0.02);
                StdDraw.text(p1.x() + 0.015 , p1.y() + 0.015 , ""+parents[i]+"");
            }
            StdDraw.setPenColor(StdDraw.MAGENTA);

            int totalConvexHull = 0;
            for(Map.Entry<Integer,Integer> entry:map.entrySet()){
                System.out.println("label="+entry.getKey() + " : " + entry.getValue());

                Point2D[] pointGroup = new Point2D[entry.getValue()];
                int count = 0;
                for(int i = 0 ; i < parents.length ; i++){
                    if(parents[i] == entry.getKey())
                        pointGroup[count++] = points[i];
                }
                if(pointGroup.length > 2){
                    Point2D[] copyArray = Arrays.copyOf(pointGroup,pointGroup.length);
                    int[] convex = ConvexHullVertex(pointGroup);
                    totalConvexHull += convex.length;
                    for(int i:convex){
                        copyArray[i].draw();
                    }
                }
            }

//            System.out.println(totalConvexHull);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRoot(int[] parents , int index){
        while(parents[index]!=index)
            index = parents[index];
        return index;
    }

    public static void printArray(int[] array){
        String sp = "";
        System.out.print("[");
        for(int i:array){
            System.out.print(sp + i);
            sp = ",";
        }
        System.out.println("]");
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
