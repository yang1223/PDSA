import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)

        String face1 = this.getFace();
        String face2 = that.getFace();
        if (face1.equals(face2)) {
            return SUIT_ORDER.compare(this , that);
        } else if (face1.equals("A")) {
            return 1;
        } else if (face1.equals("K")) {
            if (face2.equals("A")) return -1;
            else return 1;
        } else if (face1.equals("Q")) {
            if (face2.equals("A")) return -1;
            else if (face2.equals("K")) return -1;
            else return 1;
        } else if (face1.equals("J")) {
            if (face2.equals("A")) return -1;
            else if (face2.equals("K")) return -1;
            else if (face2.equals("Q")) return -1;
            else return 1;
        } else {
            try {
                Integer.parseInt(face2);
            } catch (NumberFormatException e){
                return -1;
            }
            if (Integer.parseInt(face1) > Integer.parseInt(face2)) return 1;
            else return -1;
        }
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {

            // complete this function so the Card can be sorted according to the suit

            String suit1 = c1.getSuit();
            String suit2 = c2.getSuit();
            if (suit1.equals("Spades")) {
                if (suit2.equals("Spades")) return 0;
                else return 1;
            } else if (suit1.equals("Hearts")) {
                if (suit2.equals("Spades")) return -1;
                else if (suit2.equals("Hearts")) return 0;
                else return 1;
            } else if (suit1.equals("Diamonds")) {
                if (suit2.equals("Clubs")) return 1;
                else if (suit2.equals("Diamonds")) return 0;
                else return -1;
            } else if (suit1.equals("Clubs")) {
                if (suit2.equals("Clubs")) return 0;
                else return -1;
            }
            return 0;
        }
    }   
}

