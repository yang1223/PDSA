import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


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

            ArrayList<Grid> gridList = new ArrayList<Grid>();
            for(String line:lines){
                String[] coordinate = line.split(",");
                Grid grid = new Grid(Integer.parseInt(coordinate[0]),Integer.parseInt(coordinate[1]));
                gridList.add(grid);
            }

            System.out.println(gridList.size());


        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    static class Grid {

        private int x;
        private int y;

        Grid(int x , int y){
            setX(x);
            setY(y);
        }

        public boolean isConnected(Grid p){
            if(x==p.getX()){
                if(y-p.getY()==1||y-p.getY()==-1){
                    return true;
                }
            } else if(y==p.getY()){
                if(x-p.getX()==1||x-p.getX()==-1){
                    return true;
                }
            }
            return false;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


}
