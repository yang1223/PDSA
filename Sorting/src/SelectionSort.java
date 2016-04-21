/**
 * Created by Yang, Chi-Chang on 2016/4/21.
 */
public class SelectionSort {

    boolean isPrint = false;

    public void setIsPrint (boolean isPrint){
        this.isPrint = isPrint;
    }

    public void sort(int[] array){
        ArrayPrinter.print("Before sorted : " , array);
        int N = array.length;

        for (int i = 0 ; i < N ; i++) {
            int min = i;
            for (int j = i+1 ; j < N ; j++) {
                if ( lessThan(array[j] , array[min])) {
                    min = j;
                }
            }
            exchange(array, i, min);
            if (isPrint) {
                ArrayPrinter.print( "After "+(1+i)+" exchanges : " , array);
            }
        }
        // call lessThan() ~ N^2/2 times (compare)
        // call exchange() ~ N times (exchange)

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

        SelectionSort selection = new SelectionSort();
        selection.setIsPrint(true);
        selection.sort(array);
    }
}
