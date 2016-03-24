import java.util.Iterator;

/**
 * Created by Yang Chi-Chang on 2016/3/25.
 */
public class Main {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        System.out.println(deque.size());
        deque.addFirst(4);
        System.out.println(deque.toString());

        System.out.println("====");

        Iterator<Integer> iterator = deque.iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next());

    }
}
