import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){


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
//             System.out.printf("number of announced strings: %d\ndimension of matrix: %d x %d\n", stringCount, num, num);

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
            String[] lines = br.readLine().split(",");
            for(int i = 0 ; i < stringCount ; i++){
                announce[i] = lines[i];
            }

            // store the matrix (from the 3rd line to the end of the file) in variable matrix
            for(int i = 0 ; i < num ; i++){
                String[] line = br.readLine().split(",");
                for(int j = 0 ; j < num ; j++){
                    matrix[i][j] = line[j];
                }
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

            // check for right-up to left-down diagonals
            boolean bingo2 = true;
            for(int i = 0 ; i < num ; i++){
                boolean equal = false;
                for(String eachAnnounce:announce){
                    if(matrix[i][num-1-i].equals(eachAnnounce)){
                        equal = true;
                    }
                }
                if(!equal) bingo2 = false;
            }
            if(bingo2) straightLines++;
            br.close();
            // output how many 'straight line' are there in the matrix
            System.out.println(straightLines);
            
        }
    }
}
