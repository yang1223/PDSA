import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yang, Chi-Chang on 2016/5/5.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        int count = 2;
        boolean isDraw = false;
        Clustering.setDraw(isDraw);

        String tempTxt = "temp.txt";
        FileWriter fw = new FileWriter(tempTxt);
        fw.write(count + System.getProperty("line.separator"));
        for (int i = 0; i < count; i++) {
            fw.write(Math.random() + " " + Math.random() + System.getProperty("line.separator"));
        }

        fw.close();

        args = new String[1];
        args[0] = tempTxt;

        System.out.println("===============");
        System.out.println("Correct answer:");
        ClusteringY.main(args);
        System.out.println("===============");
        System.out.println("Your answer:");
        Clustering.main(args);
        System.out.println("===============");
        File file = new File(tempTxt);
        if(!file.delete()) System.out.println(tempTxt + " still exists!");


    }
}
