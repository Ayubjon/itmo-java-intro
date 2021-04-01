package expression.exceptions;

import expression.parser.Token;

public class EndException extends ParsingException {
    public EndException(Token s) {
        super("Index: In the End. Forgot to write something.");
    }
}
