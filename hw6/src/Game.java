import java.io.IOException;
import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.HashSet;

public class Game{
    
    // Judge System will Execute The Program Here
    public static void main(String[] args) throws Exception{


//        HashSet<String> set = new HashSet<>();
////        set.add("A");
////        set.add("K");
////        set.add("Q");
////        set.add("J");
////        set.add("10");
////        set.add("9");
////        set.add("8");
////        set.add("7");
////        set.add("6");
//        System.out.println(Collections.max(set , Player.cardLevel));


        try (BufferedReader br = new BufferedReader(new FileReader(args[0])))  {
//            BufferedReader br = new BufferedReader(new FileReader(args[0]));

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

            Arrays.sort(playerArray);
            System.out.println(playerArray[playerCount - 1].getName());

        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
