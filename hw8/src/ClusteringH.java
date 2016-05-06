import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class ClusteringH implements Comparable<ClusteringH> {
    private double x;    // x coordinate
    private double y;    // y coordinate 
    private int n;
    List<Point2D> p = new ArrayList<Point2D>();

    public ClusteringH() {
    }

    public ClusteringH(double x, double y) {
        this.x = x;
        this.y = y;
        p.add(new Point2D(x, y));
        this.n = 1;
    }

    @Override
    public int compareTo(ClusteringH that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        if (this.n > that.n) return 1;
        else if (this.n < that.n) return -1;
        else if (this.x > that.x) return -1;
        else if (this.x < that.x) return 1;
        else if (this.y > that.y) return -1;
        else if (this.y < that.y) return 1;
        else return 0;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public int n() {
        return n;
    }

    static public ClusteringH merge(ClusteringH a, ClusteringH b) {
        ClusteringH m = new ClusteringH();
        m.n = a.n + b.n;

        double x_temp = 0.0;
        double y_temp = 0.0;

        for (int i = 0; i < a.n; i++) {
            m.p.add(a.p.get(i));
            x_temp += a.p.get(i).x();
            y_temp += a.p.get(i).y();
        }

        for (int i = 0; i < b.n; i++) {
            m.p.add(b.p.get(i));
            x_temp += b.p.get(i).x();
            y_temp += b.p.get(i).y();
        }

        m.x = x_temp / m.n;
        m.y = y_temp / m.n;
        return m;
    }

    public void print() {
        System.out.printf("( %.2f , %.2f )\n", x, y);
    }

    public double distanceTo(ClusteringH that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(ClusteringH that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            int draw = 0;

            String data = br.readLine();
            int n = Integer.parseInt(data);
            List<ClusteringH> c = new ArrayList<ClusteringH>();

            for (int i = 0; i < n; i++) {
                String cor[] = br.readLine().split(" ");
                c.add(new ClusteringH(Double.parseDouble(cor[0]), Double.parseDouble(cor[1])));
            }
            br.close();

            if (draw == 1) {
                StdDraw.setCanvasSize(800, 800);
                StdDraw.setXscale(0, 1);
                StdDraw.setYscale(0, 1);
                StdDraw.setPenRadius(.01);

                StdDraw.setPenColor(StdDraw.BLUE);
                for (int i = 0; i < c.size(); i++) {
                    c.get(i).draw();
                }

            }


            while (c.size() > 3) {
                int size = c.size();

                int i_temp = 0;
                int j_temp = 0;

                double temp = 100000;

                for (int i = 0; i < size; i++) {
                    for (int j = i; j < size; j++) {
                        if (i == j) ;
                        else if (temp > c.get(i).distanceTo(c.get(j))) {
                            temp = c.get(i).distanceTo(c.get(j));
                            i_temp = i;
                            j_temp = j;
                        }
                    }
                }

                if (draw == 1) {
                    StdDraw.setPenRadius(.01);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    merge(c.get(j_temp), c.get(i_temp)).draw();
                    StdDraw.setPenRadius(.003);
                    StdDraw.setPenColor(StdDraw.RED);
                    c.get(i_temp).drawTo(c.get(j_temp));
                }

                c.add(merge(c.remove(j_temp), c.remove(i_temp)));


            }


            if (n >= 3) {
                ClusteringH[] c_array = new ClusteringH[3];
                for (int i = 0; i < 3; i++)
                    c_array[i] = c.get(i);
                Arrays.sort(c_array);
                for (int i = 2; i >= 0; i--)
                    System.out.printf("%d %.2f %.2f\n", c_array[i].n(), c_array[i].x(), c_array[i].y());

                double temp = 10000;


                for (int i = 0; i < c_array[0].p.size(); i++) {
                    for (int j = 0; j < c_array[1].p.size(); j++) {

                        if (c_array[0].p.get(i).distanceTo(c_array[1].p.get(j)) < temp) {
                            temp = c_array[0].p.get(i).distanceTo(c_array[1].p.get(j));
                        }

                    }
                }

                for (int i = 0; i < c_array[0].p.size(); i++) {
                    for (int j = 0; j < c_array[2].p.size(); j++) {
                        if (c_array[0].p.get(i).distanceTo(c_array[2].p.get(j)) < temp) {
                            temp = c_array[0].p.get(i).distanceTo(c_array[2].p.get(j));
                        }
                    }
                }

                for (int i = 0; i < c_array[1].p.size(); i++) {
                    for (int j = 0; j < c_array[2].p.size(); j++) {
                        if (c_array[1].p.get(i).distanceTo(c_array[2].p.get(j)) < temp) {
                            temp = c_array[1].p.get(i).distanceTo(c_array[2].p.get(j));
                        }
                    }
                }

                System.out.printf("%.2f\n", temp);
            } else {
                ClusteringH[] c_array = new ClusteringH[n];
                for (int i = 0; i < n; i++)
                    c_array[i] = c.get(i);
                Arrays.sort(c_array);

                for (int i = n - 1; i >= 0; i--)
                    System.out.printf("%d %.2f %.2f\n", c_array[i].n(), c_array[i].x(), c_array[i].y());

                if (n == 2) {
                    System.out.printf("%.2f\n", c_array[0].distanceTo(c_array[1]));
                } else {
                    System.out.printf("0.00\n");
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printall(List<ClusteringH> q) {
        Iterator<ClusteringH> iterator = q.iterator();
        while (iterator.hasNext())
            iterator.next().print();
    }

    public static void print2D(List<Point2D> q) {
        Iterator<Point2D> iterator = q.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().toString());
    }

}
