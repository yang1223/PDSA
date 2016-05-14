/**
 * Created by Yang, Chi-Chang on 2016/5/14.
 */
public class Main {
    public static void main(String[] args) {

        String expression = "(4+(((4*2)/2)/3))";
        Expression e = new Expression();
        e.Infix2BT(expression);
        Node[] nodes = e.PrintPostfix();

        for(Node n:nodes){
            System.out.print(n.getValue());
        }
        System.out.println("");
        System.out.println(e.Evaluation());

    }
}
