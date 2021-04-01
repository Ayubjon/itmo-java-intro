package expression;

import expression.exceptions.ExpressionParser;
import expression.exceptions.Parser;

public class Main {
    public static void main(String[] args) throws Exception {
        Parser a = new ExpressionParser();
        System.out.println(a.parse("-").evaluate(5, 6, 7));
    }
}
