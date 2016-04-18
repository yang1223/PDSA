import java.io.IOException;
import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.HashSet;

public class Game{
    
    // Judge System will Execute The Program Here
    public static void main(String[] args) throws Exception{


//        HashSet<String> set = new HashSet<String>();
//        set.add("Spades");
//        set.add("Hearts");
//        set.add("Clubs");
//        set.add("Diamonds");
//
//        System.out.println(Collections.max(set , Player.suitOrder));

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split(",");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split("_");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
            }

            Player[] playerArray2 = new Player[2];
            playerArray2[0] = playerArray[4];
            playerArray2[1] = playerArray[5];

            Arrays.sort(playerArray);

            for (Player p:playerArray)
                System.out.println(p.getName());

//            Arrays.sort(playerArray2);
//            System.out.println(playerArray2[0].getName());
//            System.out.println(playerArray2[1].getName());

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
