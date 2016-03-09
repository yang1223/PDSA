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

            boolean stop = false;
            ArrayList<Grid> gridList = new ArrayList<Grid>();
            Grid root = new Grid(0,0);
            ArrayList<Grid> bottomGrid = new ArrayList<Grid>();
            for(String line:lines){
                String[] coordinate = line.split(",");
                Grid grid = new Grid(Integer.parseInt(coordinate[0]),Integer.parseInt(coordinate[1]));
                addGridToList(gridList , root , grid);
                if(grid.getX()==num){
                    bottomGrid.add(grid);
                }

                for(Grid bot:bottomGrid){
                    if(bot.getRoot()==root){
                        stop = true;
                        break;
                    }
                }

                if(stop){
                    System.out.println(line);
                    break;
                }
            }

            if(!stop){
                System.out.println(-1);
            }

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void addGridToList(ArrayList<Grid> gridList , Grid root , Grid newGrid){
        if(newGrid.getX()==1){
            newGrid.setParent(root);
        }
        for (Grid grid : gridList) {
            if (newGrid.isConnected(grid)) {
                grid.union(newGrid);
            }
        }
        gridList.add(newGrid);
    }

    static class Grid {

        private int x;
        private int y;
        private Grid parent;

        Grid(int x , int y){
            this.x = x;
            this.y = y;
            this.parent = this;
        }

        public boolean isRoot(){
            return this == parent;
        }

        public Grid getRoot(){
            Grid grid = this;
            while(!grid.isRoot()){
                grid = grid.getParent();
            }
            return grid;
        }

        public void union(Grid grid){
            Grid root1 = this.getRoot();
            Grid root2 = grid.getRoot();
            if(root1.isHigher(root2)){
                root2.setParent(root1);
            } else {
                root1.setParent(root2);
            }
        }

        public boolean isHigher(Grid grid){
            return this.getX() < grid.getX();
        }

        public boolean isConnected(Grid p){
            if(x == p.getX()){
                if(y-p.getY() == 1 || y-p.getY() == -1){
                    return true;
                }
            } else if(y==p.getY()){
                if(x-p.getX() == 1 || x-p.getX() == -1){
                    return true;
                }
            }
            return false;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Grid getParent() {
            return parent;
        }

        public void setParent(Grid parent) {
            this.parent = parent;
        }
    }


}
