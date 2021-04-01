package expression;

import java.util.Objects;

public class Variable implements TripleExpression {
    private final String s;

    public Variable(String s) {
        this.s = s;
    }

    public int evaluate(int x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        switch (s) {
            case "z":
                return z;
            case "y":
                return y;
            default:
                return x;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable that = (Variable) obj;
            return this.s.equals(that.s);
        }
        return false;
    }

    public String toString() {
        return s;
    }

    final public int hashCode() {
        return Objects.hash(s, getClass());
    }
}
