package expression.exceptions;

public class BracketException extends ParsingException {
    public BracketException(String s, int n) {
        super("Index: " + n + ". Single bracket: \"" + s + "\"");
    }
}
