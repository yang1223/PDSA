/**
 * Created by Yang Chi-Chang on 2016/5/25.
 */
public class Main {
    public static void main(String[] args) {
        Point2D[] points = new Point2D[20];
        points[0]  = new Point2D(0.3833339428 , 0.1459115606);
        points[1]  = new Point2D(0.3426077152 , 0.7218207763);
        points[2]  = new Point2D(0.3406783533 , 0.3164794008);
        points[3]  = new Point2D(0.5034046695 , 0.7964833541);
        points[4]  = new Point2D(0.5969555271 , 0.1587087880);
        points[5]  = new Point2D(0.2126349801 , 0.4842532332);
        points[6]  = new Point2D(0.5299519862 , 0.4636946673);
        points[7]  = new Point2D(0.1171932327 , 0.6117403964);
        points[8]  = new Point2D(0.8730132530 , 0.5332436770);
        points[9]  = new Point2D(0.6247044587 , 0.0213209046);
        points[10] = new Point2D(0.9218660505 , 0.7907778275);
        points[11] = new Point2D(0.4253832308 , 0.3123947194);
        points[12] = new Point2D(0.4526798481 , 0.8498759517);
        points[13] = new Point2D(0.5245860322 , 0.0488684727);
        points[14] = new Point2D(0.3255085068 , 0.9310410020);
        points[15] = new Point2D(0.7281417757 , 0.7145077083);
        points[16] = new Point2D(0.5720377234 , 0.4108029499);
        points[17] = new Point2D(0.0447830281 , 0.2155560961);
        points[18] = new Point2D(0.8674125381 , 0.2314056188);
        points[19] = new Point2D(0.4914579564 , 0.8431045366);

        FindNeighbors findNeighbors = new FindNeighbors();
        findNeighbors.init(points);
        System.out.println(findNeighbors.root.getX());

    }

}
