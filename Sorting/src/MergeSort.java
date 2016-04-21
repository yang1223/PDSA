/**
 * Created by Yang Chi-Chang on 2016/4/22.
 */
public class MergeSort {


    boolean isPrint = false;

    public void setIsPrint (boolean isPrint){
        this.isPrint = isPrint;
    }

    private void merge(int[] array , int[] aux , int start , int mid ,  int end) {

        for (int k = start ; k <= end ; k++){
            aux[k] = array[k];
        }
        int i = start;
        int j = mid + 1;
        for (int k = start ; k <= end ; k++) {
            if ( i > mid) {
                array[k] = aux[j++];
            } else if ( j > end ) {
                array[k] = aux[i++];
            } else if ( lessThan(aux[j] , aux[i]) ) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }


    public void sort(int[] array){
        int N = array.length;
        int[] aux = new int[N];
        ArrayPrinter.print("Before sorted : " , array);
        sort(array , aux , 0 , N-1);
        ArrayPrinter.print("After sorted : " , array);
    }

    public void sort(int[] array , int[] aux , int start , int end) {

        if (start >= end) return;
        int mid = start + (end - start)/2;
        sort(array , aux , start , mid);
        sort(array , aux , mid+1 , end);
        merge(array , aux , start , mid , end);
        if (isPrint) {
            ArrayPrinter.print("merge("+start+","+end+"):" , array);
        }
    }


    private boolean lessThan(int a , int b){
        return a < b;
    }


    public static void main(String[] args) {

        int[] array = {7,2,6,8,1,5,9,4,3};

        MergeSort merge = new MergeSort();

        merge.setIsPrint(true);

        merge.sort(array);

    }

}
