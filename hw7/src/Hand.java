import java.util.*;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards;

    private CardType cardType;
    public CardQuery cardQuery = new CardQuery();
    private static final CardTypeOrder CARD_TYPE_ORDER = new CardTypeOrder();

    enum CardType {
        full_house , flush , straight , two_pair , one_pair , high_card
    }


    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards = cards;
        this.setCardType();
    }

    public CardType getCardType(){
        return cardType;
    }

    private void setCardType(){
        Map<String,Integer> faces = cardQuery.getFaceCount();
        if (faces.size() == 2) {
            // (4,1) or (3,2)
            if (cardQuery.getThrees().size() != 0) {
                cardType = CardType.full_house;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 3){
            // (3,1,1) or (2,2,1)
            if (cardQuery.getPairs().size() != 0) {
                cardType = CardType.two_pair;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 4){
            // (2,1,1,1)
            cardType = CardType.one_pair;
        } else if (faces.size() == 5){
            // check for flush
            if (cardQuery.getSuitSet().size() == 1) {
                cardType = CardType.flush;
                return;
            }
            // check for straight
            String[] straights = {"A","K","Q","J","10","9","8","7","6","5","4","3","2","A"};
            Set<String> faceSet = faces.keySet();
            for(int i = 0; i < straights.length - 5 ; i++){
                Set<String> straightOne = new HashSet<String>();
                for(int j = i ; j < i + 5 ; j++){
                    straightOne.add(straights[j]);
                }
                if (faceSet.containsAll(straightOne)) {
                    cardType = CardType.straight;
                    return;
                }
            }
            // high card otherwise
            cardType = CardType.high_card;
        }
    }

    // TODO
    public int compareTo(Hand that) {
        int result = CARD_TYPE_ORDER.compare(this.getCardType(), that.getCardType());
        if (result != 0){
            return result;
        } else {
            // broke ties
            switch (this.getCardType()) {
                case full_house:
                    Card c1 = Collections.max(this.cardQuery.getThrees());
                    Card c2 = Collections.max(that.cardQuery.getThrees());
                    return c1.compareTo(c2);

                case two_pair:
                case one_pair:
                    c1 = Collections.max(this.cardQuery.getPairs());
                    c2 = Collections.max(that.cardQuery.getPairs());
                    return c1.compareTo(c2);

                case high_card:
                case straight:
                case flush:
                    c1 = Collections.max(this.cardQuery.getOnes());
                    c2 = Collections.max(that.cardQuery.getOnes());
                    return c1.compareTo(c2);
            }
        }
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }


    private static class CardTypeOrder implements Comparator<CardType> {
        private static List<CardType> order;
        CardTypeOrder() {
            CardType[] cardTypes = {
                    CardType.full_house,
                    CardType.flush,
                    CardType.straight,
                    CardType.two_pair,
                    CardType.one_pair,
                    CardType.high_card
            };
            order = new ArrayList<CardType>();
            for (CardType cardType : cardTypes) {
                order.add(cardType);
            }
        }

        @Override
        public int compare(CardType cardType1, CardType cardType2) {
            int cardType_1 = order.indexOf(cardType1);
            int cardType_2 = order.indexOf(cardType2);
            if (cardType_1 < cardType_2) return 1;
            else if (cardType_1 > cardType_2) return -1;
            else return 0;
        }
    }


    private class CardQuery {

        public Map<String , Integer> getFaceCount(){
            Map<String,Integer> faces = new HashMap<String,Integer>();
            for(Card c:cards){
                if (faces.containsKey(c.getFace()))
                    faces.put(c.getFace() , faces.get(c.getFace())+1);
                else
                    faces.put(c.getFace() , 1);
            }
            return faces;
        }

        public Set<String> getSuitSet(){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getSuitSet(String face){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                if (face.equals(c.getFace()))
                    suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<Card> getOnes(){
            Set<Card> ones = new HashSet<Card>();
            Map<String,Integer> faces = getFaceCount();
            for (Card c:cards){
                if (faces.get(c.getFace()) == 1)
                    ones.add(c);
            }
            return ones;
        }

        public Set<Card> getPairs(){
            Set<Card> pairs = new HashSet<Card>();
            Map<String,Integer> faces = getFaceCount();
            for (Card c:cards){
                if (faces.get(c.getFace()) == 2)
                    pairs.add(c);
            }
            return pairs;
        }

        public Set<Card> getThrees(){
            Set<Card> threes = new HashSet<Card>();
            Map<String,Integer> faces = getFaceCount();
            for (Card c:cards){
                if (faces.get(c.getFace()) == 3)
                    threes.add(c);
            }
            return threes;
        }
    }

}
