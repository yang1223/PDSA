import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yang Chi-Chang on 2016/6/7.
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int v = Integer.parseInt(br.readLine());
            Point[] points = new Point[v];
            for (int i = 0; i < v; i++) {
                String[] line = br.readLine().split("\\s");
                points[i] = new Point(Double.parseDouble(line[0]),Double.parseDouble(line[1]));
            }
            int source = 0;
            int target = 0;
            for (int i = 0; i < points.length; i++) {
                Point p = points[i];
                Point s = points[source];
                Point t = points[target];
                if (p.getX() + p.getY() < s.getX() + s.getY()) source = i;
                if (p.getX() + p.getY() > t.getX() + t.getY()) target = i;
            }

            Set<Double> distanceSet = new HashSet<>();
            for (int i = 0; i < points.length; i++) {
                Point p1 = points[i];
                for (int j = 0; j < points.length; j++) {
                    Point p2 = points[j];
                    distanceSet.add(p1.distanceTo(p2));
                }
            }
            Double[] distances = distanceSet.toArray(new Double[distanceSet.size()]);
            Arrays.sort(distances);

            for (double d : distances){
                DirectedGraph graph = new DirectedGraph(v);
                for (int i = 0; i < v; i++) {
                    Point p1 = points[i];
                    for (int j = 0; j < v; j++) {
                        Point p2 = points[j];
                        if (p1.getX() < p2.getX() && p1.getY() < p2.getY()) {
                            if (p1.distanceTo(p2) < d) {
                                graph.addEdge(i,j);
                            }
                        }
                    }
                }
                DFS dfs = new DFS(graph , source);
                if (dfs.isMarked(target)) {
                    System.out.println(String.format("%1.3f",d));
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Point {
        double x;
        double y;
        Point(double x , double y) {
            this.x = x;
            this.y = y;
        }

        public double distanceTo (Point that){
            return Math.sqrt((this.x-that.x)*(this.x-that.x)
                    +(this.y-that.y)*(this.y-that.y));
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

}
