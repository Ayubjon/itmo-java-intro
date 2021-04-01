package game;

public class SequentialPlayer implements Player {

    public SequentialPlayer() {
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        for (int r = 0; r < position.getHeight(); r++) {
            for (int c = 0; c < position.getWidth(); c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
