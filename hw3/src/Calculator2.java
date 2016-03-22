import java.util.Stack;

/**
 * Created by 其昌 on 2016/3/22.
 */
public class Calculator2 {

    public Double ans(String e){
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        String[] input = e.split("\\s");
        for(String s:input){
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals(")"))
            {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                else if (op.equals("-")) vals.push(-vals.pop() + vals.pop());
                else if (op.equals("*")) vals.push(vals.pop() * vals.pop());
                else if (op.equals("/")) vals.push(1/vals.pop()*vals.pop());
            }
            else vals.push(Double.parseDouble(s));
        }
        return vals.pop();
    }
}
