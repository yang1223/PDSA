import java.util.HashMap;
import java.util.Map;

public class Player implements Comparable<Player>{

    private Card[] cards = new Card[5];
    private String name;

    enum CardType {
        full_house, flush , straight , two_pair , one_pair , high_card
    }

    private CardType cardType;

    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public CardType getCardType(){ return this.cardType; }

    public void setCardType(){
        Map<String,Integer> faces = new HashMap<String,Integer>();
        for(Card c:cards){
            if (faces.containsKey(c.getFace()))
                faces.put(c.getFace() , faces.get(c.getFace())+1);
            else
                faces.put(c.getFace() , 1);
        }
        if (faces.size() == 2) {
            // (4,1) or (3,2)
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 3) {
                    cardType = CardType.full_house;
                }
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 3){
            // (3,1,1) or (2,2,1)
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 2) {
                    cardType = CardType.two_pair;
                }
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 4){
            // (2,1,1,1)
            cardType = CardType.one_pair;
        } else if (faces.size() == 5){
            // TODO : flush & straight
        }


    }

    // TODO 
    public int compareTo(Player that) {
        // complete this function so the Player can be sorted according to the cards he/she has.



        return 0;
    }
}

