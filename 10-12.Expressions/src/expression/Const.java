package expression;

import java.util.Objects;

public class Const implements TripleExpression {
    private final int x;

    public Const(int x) {
        this.x = x;
    }

    public String toString() {
        return String.valueOf(x);
    }

    public int evaluate(int x) {
        return this.x;
    }

    public int evaluate(int x, int y, int z) {
        return this.x;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            Const that = (Const) obj;
            return this.x == that.x;
        }
        return false;
    }

    final public int hashCode() {
        return Objects.hash(x, getClass());
    }
}
