/**
 * Created by Yang, Chi-Chang on 2016/5/14.
 */
public class Main {
    public static void main(String[] args) {

//        String infix = "(4+(((4*2)/2)/3))";
//        String infix = "(21/2.1)";
        String infix = "";
        Expression expression = new Expression();
        expression.Infix2BT(infix);
        Node[] prefixNodes = expression.PrintPrefix();
        for(Node n:prefixNodes){
            System.out.print(n.getValue());
        }
        System.out.println("");

        Node[] postfixNodes = expression.PrintPostfix();
        for(Node n:postfixNodes){
            System.out.print(n.getValue());
        }
        System.out.println("");

        System.out.println(expression.Evaluation());

    }
}
