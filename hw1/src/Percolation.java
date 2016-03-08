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


//            Grid root = new Grid(0,0);
//            Grid grid1 = new Grid(1,1);
//            Grid grid2 = new Grid(2,2);
//            Grid grid3 = new Grid(3,3);
//            Grid grid4 = new Grid(4,4);
//            grid1.setParent(root);
//            grid2.union(grid1);
//            grid1.union(grid4);


            ArrayList<Grid> gridList = new ArrayList<Grid>();
            Grid root = new Grid(0,0);
            for(String line:lines){
                String[] coordinate = line.split(",");
                Grid grid = new Grid(Integer.parseInt(coordinate[0]),Integer.parseInt(coordinate[1]));
                if(grid.getX()==1){
                    grid.setParent(root);
                    gridList.add(grid);
                } else {
                    addGridToList(gridList , grid);
                }
            }

            for(Grid grid:gridList){
                System.out.println(grid.getRoot().getX() + " , " + grid.getRoot().getY());
            }



        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void addGridToList(ArrayList<Grid> gridList , Grid newGrid){
        for(Grid grid:gridList){
            if(newGrid.isConnected(grid)){
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
