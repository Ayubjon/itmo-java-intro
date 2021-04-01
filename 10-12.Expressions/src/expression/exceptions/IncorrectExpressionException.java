package expression.exceptions;

public class IncorrectExpressionException extends ParsingException {
    public IncorrectExpressionException(char s, int n) {
        super("Index: " + n + ". Incorrect expression: \"" + s + "\"");
    }
}
