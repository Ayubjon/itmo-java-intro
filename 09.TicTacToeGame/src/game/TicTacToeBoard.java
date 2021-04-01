package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private final int n;
    private final int m;
    private final int k;
    private int empty;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    public TicTacToeBoard(final int n, final int m, final int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.empty = n * m;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private int checkCells(int iMove, int jMove, Move move) {
        int cnt = 0;
        int i = move.getRow();
        int j = move.getColumn();

        while (i >= 0 && i < n && j >= 0 && j < m && cells[i][j] == move.getValue()) {
            i += iMove;
            j += jMove;
            cnt++;
        }
        return cnt;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        empty--;
        int row = move.getRow();
        int col = move.getColumn();
        cells[row][col] = move.getValue();
        for (int i = -1; i <= 0; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i < 0 || j < 0) {
                    if (checkCells(i, j, move) + checkCells(-i, -j, move) - 1 >= k) {
                        return Result.WIN;
                    }
                }
            }
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < getHeight()
                && 0 <= move.getColumn() && move.getColumn() < getWidth()
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && move.getValue() == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int r = 0; r <= n; r++) {
            sb.append(r).append("\t");
        }
        sb.append("\n");
        for (int r = 0; r < n; r++) {
            sb.append(r + 1).append("\t");
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int getHeight() {
        return n;
    }

    @Override
    public int getWidth() {
        return m;
    }
}
