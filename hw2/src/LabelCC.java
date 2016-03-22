import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 其昌 on 2016/3/11.
 */
public class LabelCC {
    public static void main(String[] args){

        try {

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String[] firstLine = br.readLine().split(",");
            int num = Integer.parseInt(firstLine[0]);
            int targetX = Integer.parseInt(firstLine[1]) - 1; // index 從0開始所以讀出座標之後要-1
            int targetY = Integer.parseInt(firstLine[2]) - 1;

            int labelCount = 0;
            int[][] labelCC = new int[num][num];

            int[] parents = new int[num*num];
            for(int i = 0 ; i < parents.length ; i++){
                parents[i] = i;
            }

            for(int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    labelCC[i][j] = -1; // 先把labelCC的值都設成-1不然預設值都會是0
                }
            }

            while (br.ready()){
                String[] coordinate = br.readLine().split(",");
                int x = Integer.parseInt(coordinate[0]) - 1; // index 從0開始所以讀出座標之後要-1
                int y = Integer.parseInt(coordinate[1]) - 1;
                labelCC[x][y] = 0; // 把檔案讀到座標的label設成0
             }
            br.close();

            // 開始填label
            for(int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    if(labelCC[i][j] != 0){
                        // 如果這格的label不是0的話才開始做事情
                        if(i==0 && j==0){
                            // 如果這格是(1,1)的話，label=1
                            labelCC[i][j] = ++labelCount;
                        } else if(i == 0){
                            // 如果這格是(1,X)的話，只檢查左邊
                            int labelLeft = labelCC[i][j-1];
                            labelCC[i][j] = (labelLeft != 0)? labelLeft : ++labelCount;
                        } else if(j == 0){
                            // 如果這格是(X,1)的話，只檢查上面
                            int labelTop = labelCC[i-1][j];
                            labelCC[i][j] = (labelTop != 0)? labelTop : ++labelCount;
                        } else {
                            // 其他格上面和左邊都要檢查
                            int labelLeft = labelCC[i][j-1];
                            int labelTop = labelCC[i-1][j];
                            if(labelLeft == 0 && labelTop == 0){
                                // 上面和左邊均為0的話，label = 下一組group
                                labelCC[i][j] = ++labelCount;
                            } else if(labelLeft == 0) {
                                // 左邊為0的話，label = 上面的label
                                labelCC[i][j] = labelTop;
                            } else if(labelTop == 0) {
                                // 上面為0的話，label = 左邊的label
                                labelCC[i][j] = labelLeft;
                            } else {
                                // 均不為0的話，label = 左邊的label
                                labelCC[i][j] = labelLeft;
                                if(labelLeft != labelTop){
                                    // 左邊和上面label不相等的話，建立parent和child的關係
                                    int leftRoot = getRoot(parents , labelLeft);
                                    int topRoot = getRoot(parents , labelTop);
                                    int parentRoot = topRoot < leftRoot? topRoot : leftRoot;
                                    int childRoot = topRoot >= leftRoot? topRoot : leftRoot;
                                    parents[childRoot] = parentRoot;
                                }
                            }
                        }
                    }
                }
            }

//            // 把值改成parents裡面找到的root
//            for(int i = 0 ; i < num ; i++){
//                for(int j = 0 ; j < num ; j++){
//                    labelCC[i][j] = getRoot(parents , labelCC[i][j]);
//                }
//            }

            // 印出parents的對照表
            for(int i = 0 ; i < 10 ; i++){
                System.out.print(i+" ");
            }
            System.out.println("");
            for(int i = 0 ; i < 10 ; i++){
                System.out.print(parents[i]+" ");
            }
            System.out.println("");
            // 印出整個labelCC
            for(int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    System.out.print(labelCC[i][j]+"\t");
                }
                System.out.println("");
            }

            // 印出target的label
            System.out.println(labelCC[targetX][targetY]);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static int getRoot(int[] parents , int index){
        while(parents[index]!=index)
            index = parents[index];
        return index;
    }




}
