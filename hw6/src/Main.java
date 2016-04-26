import java.util.ArrayList;

/**
 * Created by Yang Chi-Chang on 2016/4/26.
 */
public class Main {
    public static void main(String[] args) {
        Card[] cards1 = deal();
        Card[] cards2 = deal();
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setCards(cards1);
        player2.setCards(cards2);

        System.out.println("S = Spades ,  H = Hearts , D = Diamonds , C = Clubs");
        System.out.println(player1.getName() + " : " + toString(cards1));
        System.out.println(player2.getName() + " : " + toString(cards2));
        System.out.println(player1.getName() + ".compareTo("+player2.getName()+") = " + player1.compareTo(player2));

    }

    public static Card[] deal(){
        String[] faces = {"A","K","Q","J","10","9","8","7","6","5","4","3","2"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (list.size() < 10){
            int i = (int)(Math.random()*52);
            if(!list.contains(i)) list.add(i);
        }
        Card[] cards = new Card[5];
        int count = 0;
        for(int i = 0 ; i < 5 ; i ++){
            int n = list.get(i);
            int suit = n/13;
            int face = n - (n/13)*13;
            Card c = new Card(faces[face] , suits[suit]);
            cards[i] = c;
        }
        return cards;
    }

    public static String toString(Card[] cards){
        String temp = "";
        String sp = "";
        for (Card c:cards){
            temp += sp + c.getFace()+"("+c.getSuit().substring(0,1) +")";
            sp = ",";
        }
        return temp;
    }

}
