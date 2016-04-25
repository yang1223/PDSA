import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split(",");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

        }
    }
}
