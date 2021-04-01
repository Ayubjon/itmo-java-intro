package game;

public interface Position {
    int getWidth();

    int getHeight();

    boolean isValid(Move move);

    Cell getCell(int r, int c);
}
