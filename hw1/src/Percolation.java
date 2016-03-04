import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 其昌 on 2016/3/4.
 */
public class Percolation {
    public static void main(String[] args){
        try{
            String filename = args[0];
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int num = Integer.parseInt(br.readLine());

            ArrayList<String> lines = new ArrayList<String>();
            while(br.ready()){
                lines.add(br.readLine());
            }
            br.close();

            boolean[] isConnected = new boolean[lines.size()];
            Arrays.fill(isConnected , false);

            for(String line:lines){

            }

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

}
