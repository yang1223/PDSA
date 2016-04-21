/**
 * Created by Yang, Chi-Chang on 2016/4/21.
 */
public class InsertionSort {

    boolean isPrint = false;

    public void setIsPrint (boolean isPrint){
        this.isPrint = isPrint;
    }

    public void sort(int[] array){
        ArrayPrinter.print("Before sorted : " , array);
        int N = array.length;

        for (int i = 0 ; i < N ; i++) {
            for (int j = i ; j > 0 ; j--) {
                if ( lessThan(array[j] , array[j-1]) ){
                    exchange(array , j , j-1);
                } else {
                    break;
                }
            }
            if(isPrint){
                ArrayPrinter.print("After " + (i+1) + " insertion : " , array);
            }
        }
        // call lessThan() about ~ N^2/4 times
        // call exchange() about ~ N^2/4 times

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

        InsertionSort insertion = new InsertionSort();
        insertion.setIsPrint(true);
        insertion.sort(array);

    }

}
