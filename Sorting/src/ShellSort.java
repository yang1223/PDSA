/**
 * Created by Yang, Chi-Chang on 2016/4/21.
 */
public class ShellSort {


    boolean isPrint = false;

    public void setIsPrint (boolean isPrint){
        this.isPrint = isPrint;
    }

    public void sort(int[] array){
        ArrayPrinter.print("Before sorted : " , array);
        int N = array.length;

        int h = 1;
        while (3*h < N)
            h = 3 * h + 1; // find first h for 1, 4, 13, 40, 121, ...

        while (h >= 1) {

            for (int i = h ; i < N ; i++) {
                for (int j = i ; j >= h ; j -= h ) {
                    if ( lessThan(array[j] , array[j-h]) ){
                        exchange(array , j , j-h);
                    } else {
                        break;
                    }
                }
            }
            if(isPrint){
                ArrayPrinter.print("After h=" + h + " shell sort : " , array);
            }
            h = h/3;
        }

        ArrayPrinter.print("After sorted : " , array);
    }


    private boolean lessThan(int a , int b){
        return a < b;
    }

    private void exchange (int[] array , int i , int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {

        int[] array = {7,2,6,8,1,5,9,4,3};

        ShellSort shell = new ShellSort();
        shell.setIsPrint(true);
        shell.sort(array);

    }
}
