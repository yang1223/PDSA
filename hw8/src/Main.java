import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yang, Chi-Chang on 2016/5/5.
 */
public class Main {
    public static void main(String[] args) {

        int count = 20;

        boolean isDraw = true;
        Clustering.setDraw(isDraw);

        List<Clustering> clusterings = new ArrayList<Clustering>();
        // Use more clusterings.get() than clusterings.remove()
        // Therefore, use ArrayList instead of LinkedList

        for (int i = 0; i < count; i++) {
            clusterings.add( new Clustering( new Clustering.Point( Math.random() , Math.random())));
        }


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
                for (int k = 0; k < points1.size(); k++) {
                    Clustering.Point p1 = points1.get(k);
                    for (int l = 0; l < points2.size(); l++) {
                        Clustering.Point p2 = points2.get(l);
                        if(p1.distanceTo(p2) < min) {
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

        Clustering[] clusteringsArray = clusterings.toArray(new Clustering[clusterings.size()]);
        Arrays.sort(clusteringsArray);

        for(Clustering c:clusteringsArray){
            System.out.println(String.format("%d %.2f %.2f",c.size() , c.getX() , c.getY() ));
        }
        System.out.println(String.format("%.2f",min));

    }
}
