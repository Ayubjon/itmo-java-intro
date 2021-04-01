package game;

import java.io.InputStream;
import java.io.PrintStream;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final ReadMe readMe;

    public HumanPlayer(final PrintStream out, final InputStream in) {
        readMe = new ReadMe(in, out);
        this.out = out;
    }

    public HumanPlayer() {
        this(System.out, System.in);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position:");
            out.println(position);
            out.println(cell + "'s move");
            int row;
            int column;
            do {
                row = readMe.ReadNextInt("row") - 1;
                column = readMe.ReadNextInt("column") - 1;
            } while (!position.isValid(new Move(row, column, cell)));

            final Move move = new Move(row, column, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
