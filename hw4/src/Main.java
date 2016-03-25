import java.util.Iterator;

/**
 * Created by Yang Chi-Chang on 2016/3/25.
 */
public class Main {
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        for(int i = 0 ; i < 10000 ; i++){
            deque.addFirst(i+"");
        }

        for(int i = 0 ; i < 9990 ; i++){
            deque.removeFirst();
        }


        System.out.println(deque.toString());

        System.out.println(deque.size());

        System.out.println("====");

        Iterator<String> iterator = deque.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());


    }
}
