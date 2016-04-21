/**
 * Created by Yang, Chi-Chang on 2016/4/21.
 */
public class ArrayPrinter {

    public static void print(int[] array){
        String sp = "";
        System.out.print("[");
        for(int i : array){
            System.out.print(sp + i);
            sp = ",";
        }
        System.out.print("]");
        System.out.println("");
    }

    public static void print(String pre, int[] array){
        String sp = "";
        System.out.print(pre + "[");
        for(int i : array){
            System.out.print(sp + i);
            sp = ",";
        }
        System.out.print("]");
        System.out.println("");
    }

    public static void print(int[] array , String post){
        String sp = "";
        System.out.print("[");
        for(int i : array){
            System.out.print(sp + i);
            sp = ",";
        }
        System.out.print("]" + post);
        System.out.println("");
    }

    public static void print(String pre , int[] array , String post){
        String sp = "";
        System.out.print(pre + "[");
        for(int i : array){
            System.out.print(sp + i);
            sp = ",";
        }
        System.out.print("]" + post);
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] a = {1,5,6,8,2,3,7};
        ArrayPrinter.print("this is : " , a);
    }

}
