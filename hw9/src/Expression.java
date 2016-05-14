import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression{
  
    private Node root;

    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){

        Stack<Node> ops = new Stack<Node>();
        Stack<Node> vals = new Stack<Node>();
        String num = "";
        for(int i = 0 ; i < infix.length() ; i++){
            char c = infix.charAt(i);
            switch (c){
                case '(':
                    num = "";
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    if(!num.equals(""))
                        vals.push(new Node(null , null , num));
                    num = "";
                    ops.push(new Node(null , null , c + ""));
                    break;
                case ')':
                    if(!num.equals(""))
                        vals.push(new Node(null , null , num));
                    num = "";
                    Node op = ops.pop();
                    Node val2 = vals.pop();
                    Node val1 = vals.pop();
                    op.setLeft(val1);
                    op.setRight(val2);
                    root = op;
                    vals.push(op);
                    break;
                default:
                    num += c;
            }
        }
        return root;
    }

    public Node[] PrintPrefix(){
        List<Node> list = new ArrayList<Node>();
        PrintPrefixHelper(list, root);
        return list.toArray(new Node[list.size()]);
    }

    private void PrintPrefixHelper(List<Node> list, Node root){
        if (root == null) return;
        list.add(root);
        PrintPrefixHelper(list, root.getLeft());
        PrintPrefixHelper(list , root.getRight());
    }

    public Node[] PrintPostfix(){
        List<Node> list = new ArrayList<Node>();
        PrintPostfixHelper(list, root);
        return list.toArray(new Node[list.size()]);
    }

    private void PrintPostfixHelper(List<Node> list, Node root){
        if (root == null) return;
        PrintPostfixHelper(list, root.getLeft());
        PrintPostfixHelper(list, root.getRight());
        list.add(root);
    }

    public double Evaluation(){
        Node[] nodes =  this.PrintPostfix();
        Stack<Double> values = new Stack<Double>();
        for (Node node:nodes) {
            String str = node.getValue();
            if (str.equals("+")){
                values.push( values.pop() + values.pop() );
            } else if (str.equals("-")){
                values.push( - values.pop() + values.pop() );
            } else if (str.equals("*")){
                values.push( values.pop() * values.pop() );
            } else if (str.equals("/")){
                values.push( 1/values.pop() * values.pop() );
            } else {
                values.push(Double.parseDouble(str));
            }
        }
        return values.pop();
    }
}