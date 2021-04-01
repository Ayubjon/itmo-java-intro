package game;

import java.util.Random;

public class TicTacToeTournament implements Tournament {
    private final int c;
    private final int number;
    private final Random random;
    private final int n;
    private final int m;
    private final int k;
    private final int t;
    private final boolean log;
    private final Table table;
    private final Player[] players;

    public TicTacToeTournament(boolean log, int number, int c, int n, int m, int k, int t, Player[] players) {
        this.number = number;
        this.c = c;
        this.random = new Random();
        this.n = n;
        this.m = m;
        this.k = k;
        this.log = log;
        this.t = t;
        table = new Table(number);
        this.players = players;
    }

    public void play_() {
        for (int i = 1; i <= number; ++i) {
            for (int j = i + 1; j <= number; ++j) {
                int r = random.nextInt(2);
                int first = (i * r + (1 - r) * j);
                int second = (j * r + (1 - r) * i);
                log("The gamer with number " + first + " plays with gamer with number " + second);
                TicTacToeMatch match = new TicTacToeMatch(n, m, k, t, players[first - 1], players[second - 1]);
                int result = match.play();
                if (result == 0) {
                    table.add(j, 1);
                    table.add(i, 1);
                } else {
                    result--;
                    table.add(first * (1 - result) + second * (result), 3);
                }
            }
        }
    }

    public void play() {
        for (int i = 0; i < c; ++i) {
            play_();
        }
        log(table.toString());
    }


    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
