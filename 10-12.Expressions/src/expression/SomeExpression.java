package expression;

public interface SomeExpression extends Expression, TripleExpression {
    @Override
    int evaluate(int x);

    @Override
    int evaluate(int x, int y, int z);
}
