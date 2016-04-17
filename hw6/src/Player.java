import java.util.*;

public class Player implements Comparable<Player>{

    private Card[] cards = new Card[5];
    private String name;

    enum CardType {
        full_house , flush , straight , two_pair , one_pair , high_card
    }

    private CardType cardType;
    public CardQuery cardQuery = new CardQuery();
//    private static final CardLevel cardLevel = new CardLevel();
    public static final CardLevel cardLevel = new CardLevel();

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
            // check for straight
            String[] straights = {"A","K","Q","J","10","9","8","7","6","5","4","3","2","1"};
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

            // check for flush
            if (cardQuery.getSuitSet().size() == 1) {
                cardType = CardType.flush;
                return;
            }
            // high card otherwise
            cardType = CardType.high_card;
        }
    }

    // TODO 
    public int compareTo(Player that) {
        // complete this function so the Player can be sorted according to the cards he/she has.

        this.setCardType();
        that.setCardType();
        int type1 = cardLevel.byCardType(this.getCardType());
        int type2 = cardLevel.byCardType(that.getCardType());
        if (type1 < type2){
            return 1;
        } else if (type1 > type2) {
            return -1;
        } else {
            // broke ties
            switch (this.getCardType()) {
                case full_house:
                    int threes1 = cardLevel.byFace(Collections.max(this.cardQuery.getThrees() , cardLevel));
                    int threes2 = cardLevel.byFace(Collections.max(that.cardQuery.getThrees() , cardLevel));
                    if (threes1 < threes2) return 1;
                    else return -1;
                case two_pair:
                    String max1 = Collections.max(this.cardQuery.getPairs() , cardLevel);
                    String max2 = Collections.max(that.cardQuery.getPairs() , cardLevel);
                    int largePair1 = cardLevel.byFace(max1);
                    int largePair2 = cardLevel.byFace(max2);
                    if (largePair1 < largePair2) {
                        return 1;
                    } else if (largePair1 > largePair2) {
                        return -1;
                    } else {
                        Set<String> set1 = this.cardQuery.getPairs();
                        Set<String> set2 = that.cardQuery.getPairs();
                        set1.remove(max1);
                        set2.remove(max2);
                        int smallPair1 = cardLevel.byFace(Collections.max(set1 , cardLevel));
                        int smallPair2 = cardLevel.byFace(Collections.max(set2 , cardLevel));
                        if (smallPair1 < smallPair2) {
                            return 1;
                        } else if (smallPair1 > smallPair2) {
                            return -1;
                        }
                    }
                case one_pair:
                    int pair1 = cardLevel.byFace(Collections.max(this.cardQuery.getPairs() , cardLevel));
                    int pair2 = cardLevel.byFace(Collections.max(that.cardQuery.getPairs() , cardLevel));
                    if (pair1 < pair2) {
                        return 1;
                    } else if (pair1 > pair2) {
                        return -1;
                    }
                case high_card:
                    int one1 = cardLevel.byFace(Collections.max(this.cardQuery.getOnes() , cardLevel));
                    int one2 = cardLevel.byFace(Collections.max(that.cardQuery.getOnes() , cardLevel));
                    if (one1 < one2) {
                        return 1;
                    } else if (one1 > one2) {
                        return -1;
                    }
            }
        }
        return 0;
    }




    private static class CardLevel implements Comparator<String>{
        private static List<String> suitOrder;
        private static List<String> faceOrder;
        private static List<CardType> cartTypeOrder;

        CardLevel() {
            String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
            String[] faces = {"A","K","Q","J","10","9","8","7","6","5","4","3","2","1"};
            CardType[] cardTypes = {
                    CardType.full_house,
                    CardType.flush,
                    CardType.straight,
                    CardType.two_pair,
                    CardType.one_pair,
                    CardType.high_card
            };

            suitOrder = new ArrayList<String>();
            faceOrder = new ArrayList<String>();
            cartTypeOrder = new ArrayList<CardType>();

            // add greater item with greater index
            // eg. A = 13 , K = 12 , ...
            for (String suit : suits) suitOrder.add(suit);

            for (String face : faces) faceOrder.add(face);

            for (CardType cardType1 : cardTypes) cartTypeOrder.add(cardType1);
        }

        public int bySuit(String suit) {
            return suitOrder.indexOf(suit);
        }

        public int byFace(String face) {
            return faceOrder.indexOf(face);
        }

        public int byCardType(CardType cardType) {
            return cartTypeOrder.indexOf(cardType);
        }

        @Override
        public int compare(String face1, String face2) {
            // comparator of faces
            int faceLevel_1 = byFace(face1);
            int faceLevel_2 = byFace(face2);
            if (faceLevel_1 < faceLevel_2) return 1;
            else if (faceLevel_1 > faceLevel_2) return -1;
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

        public Set<String> getOnes(){
            Set<String> ones = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 1)
                    ones.add(entry.getKey());
            }
            return ones;
        }

        public Set<String> getPairs(){
            Set<String> pairs = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 2)
                    pairs.add(entry.getKey());
            }
            return pairs;
        }

        public Set<String> getThrees(){
            Set<String> threes = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 3)
                    threes.add(entry.getKey());
            }
            return threes;
        }

    }





}

