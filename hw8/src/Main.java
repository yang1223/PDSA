import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yang, Chi-Chang on 2016/5/5.
 */
public class Main {
    public static void main(String[] args) {

        boolean isDraw = false;
        Clustering.setDraw(isDraw);

        List<Clustering> clusterings = new LinkedList<Clustering>();

        clusterings.add( new Clustering( new Clustering.Point( 0.48333394289368536 , 0.005911560679846994)));
        clusterings.add( new Clustering( new Clustering.Point( 0.9426077818967152  , 0.32182077631674877 )));
        clusterings.add( new Clustering( new Clustering.Point( 0.17247234067835338 , 0.3164794008683418  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.5276097034046695  , 0.7964833541635068  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.9767268015598104  , 0.6050313667459967  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.3275329713070515  , 0.66183445023924    )));
        clusterings.add( new Clustering( new Clustering.Point( 0.41939697123242015 , 0.9626842308775182  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.6503412904484938  , 0.18090522436533318 )));
        clusterings.add( new Clustering( new Clustering.Point( 0.8693410639235616  , 0.9002806396543104  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.9884473707901976  , 0.7729294134211026  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.9342720395295824  , 0.16981731086644136 )));
        clusterings.add( new Clustering( new Clustering.Point( 0.31270910406823915 , 0.20051767486525673 )));
        clusterings.add( new Clustering( new Clustering.Point( 0.467900409978538   , 0.4875390545441505  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.30548287907410765 , 0.34467014393926365 )));
        clusterings.add( new Clustering( new Clustering.Point( 0.16580772939460164 , 0.22464710690266565 )));
        clusterings.add( new Clustering( new Clustering.Point( 0.2854506657767861  , 0.5559388309103223  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.6009118467985421  , 0.6091088164052958  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.2185378455860706  , 0.4382159663795573  )));
        clusterings.add( new Clustering( new Clustering.Point( 0.2066103278341087  , 0.49084609413621205 )));
        clusterings.add(new Clustering(new Clustering.Point(0.1320913570902731, 0.3210377206614142)));

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
