import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
//        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){


        // 先用指定路徑來讀檔案，上傳作業前要改回用args[0]來指定檔案名稱
        // 也就是以下兩行要刪掉，上面那一行要被註解掉的要加回來，最後面的catch要拿掉
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
            
            // read a line and split by ','
            String[] data = br.readLine().split(",");
            
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];

            // printf in Java (you should comment out or delete this in your final submission)
             System.out.printf("number of announced strings: %d\ndimension of matrix: %d x %d\n", stringCount, num, num);

            /*  now you can write your own solution to hw0
             *  you can follow the instruction described below:
             * 
             *  1. read the rest content of the file
             *  2. store the announce strings (2nd line of the file) in variable announce
             *  3. store the matrix (from the 3rd line to the end of the file) in variable matrix
             *  4. compare the matrix and announce strings (this is the tricky part)
             *  5. output how many 'straight line' are there in the matrix
             * 
             *  [note]
             *  you can use every data structure in standard Java packages (Java 8 supported)
             *  the packages in stdlib.jar and algs4.jar are also available for you to use
             *
             *  [hint]
             *  1. you should check whether Java pass the variable by references or by values.
             *  2. some data structure such as HashSet, HashMap, Arrays, ArrayList, Vector are very
             *     useful for solving problems. 
             */

            // store the announce strings (2nd line of the file) in variable announce
            announce = br.readLine().split(",");

            // store the matrix (from the 3rd line to the end of the file) in variable matrix
            for(int i = 0 ; i < num ; i++){
                matrix[i] = br.readLine().split(",");
            }

            // compare the matrix and announce strings (this is the tricky part)
            int straightLines = 0;
            // check for rows
            for(int i = 0 ; i < num ; i++){
                boolean bingo = true;
                for(int j = 0 ; j < num ; j++){
                    boolean equal = false;
                    for(String eachAnnounce:announce){
                        if(matrix[i][j].equals(eachAnnounce)){
                            equal = true;
                        }
                    }
                    if(!equal) bingo = false;
                }
                if(bingo) straightLines++;
            }

            // check for columns
            for(int j = 0 ; j < num ; j++){
                boolean bingo = true;
                for(int i = 0 ; i < num ; i++){
                    boolean equal = false;
                    for(String eachAnnounce:announce){
                        if(matrix[i][j].equals(eachAnnounce)){
                            equal = true;
                        }
                    }
                    if(!equal) bingo = false;
                }
                if(bingo) straightLines++;
            }

            // check for left-up to right-down diagonals
            boolean bingo1 = true;
            for(int i = 0 ; i < num ; i++){
                boolean equal = false;
                for(String eachAnnounce:announce){
                    if(matrix[i][i].equals(eachAnnounce)){
                        equal = true;
                    }
                }
                if(!equal) bingo1 = false;
            }
            if(bingo1) straightLines++;

            // check for left-up to right-down diagonals
            boolean bingo2 = true;
            for(int i = 0 ; i < num ; i++){
                boolean equal = false;
                for(String eachAnnounce:announce){
                    if(matrix[i][2-i].equals(eachAnnounce)){
                        equal = true;
                    }
                }
                if(!equal) bingo2 = false;
            }
            if(bingo2) straightLines++;

            // output how many 'straight line' are there in the matrix
            System.out.println(straightLines);

        }
        // "catch"這部分在上傳前要刪除
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
