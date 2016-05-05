import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HandPQ {

    int N;
    Hand[] pq;

    HandPQ (){
        N = 0;
        pq = new Hand[10];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Hand hand) {
        checkAndResize();
        pq[N++] = hand;
    }

    public Hand deleteMin() {
        int min = 0;
        for (int i = 0 ; i < N ; i++){
            if (pq[min].compareTo(pq[i]) == 1){
                min = i;
            }
        }
        Hand temp = pq[min];
        pq[min] = pq[N-1];
        pq[N-1] = temp;
        return pq[--N];
    }

    private void checkAndResize() {
        if (pq.length-N <= 1) {
            Hand[] copy = new Hand[2*pq.length];
            for (int i = 0 ; i < N ; i++){
                copy[i] = pq[i];
            }
            pq = copy;
        }
    }


    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String[] header = br.readLine().split(",");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            HandPQ pq = new HandPQ();
//            MinPQ<Hand> pq = new MinPQ<Hand>(); // use MinPQ in stdlib to get all points
            for (int line = 0 ; line < count ; line++ ){
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split(",");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split("_");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                pq.insert(hand);
                if (pq.size() > target){
                    pq.deleteMin();
//                    pq.delMin(); // use MinPQ in stdlib to get all points
                }

            }
            br.close();

            Card[] cards = pq.deleteMin().getCards();
//            Card[] cards = pq.delMin().getCards(); // use MinPQ in stdlib to get all points
            Arrays.sort(cards);
            System.out.println(toString(cards));

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String toString(Card[] cards){
        String temp = "";
        String sp = "";
        for (Card c:cards){
            temp += sp + c.getSuit() + "_" + c.getFace();
            sp = ",";
        }
        return temp;
    }

}
