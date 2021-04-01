package game;

public class TicTacToeMatch implements Match {
    private int r1 = 0;
    private int r2 = 0;
    private final int t;
    private final int n;
    private final int m;
    private final int k;
    private final Game game;

    public TicTacToeMatch(int n, int m, int k, int t, Player player1, Player player2) {
        game = new Game(true, player1, player2);
        this.t = t;
        this.n = n;
        this.m = m;
        this.k = k;
    }

    public int play() {
        int turn = 1;
        while (r2 < t && r1 < t) {
            int result = game.play(new TicTacToeBoard(n, m, k));
            result--;
            if (turn == 1 && result != -1) {
                r2 += result;
                r1 += (1 - result);
            } else if (result != -1) {
                r2 += (1 - result);
                r1 += result;
            }
            turn++;
            turn %= 2;
        }
        if (r1 >= t) {
            System.out.println(1 + " Wins");
            return 1;
        } else {
            System.out.println(2 + " Wins");
            return 2;
        }
    }
}
