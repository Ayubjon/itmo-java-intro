package expression.exceptions;

public class UnknownFuncOrVarException extends ParsingException {
    public UnknownFuncOrVarException(String s) {
        super(s + " - wrong name of variable!");
    }
}
