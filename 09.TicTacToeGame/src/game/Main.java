package game;

public class Main {
    static ReadMe readMe;

    public static void main(String[] args) {
        int n, m, k, c, number;
        readMe = new ReadMe(System.in, System.out);
        String enter = "";
        do {
            System.out.println(enter);
            n = readMe.ReadNextInt("n");
            m = readMe.ReadNextInt("m");
            k = readMe.ReadNextInt("k");
            enter = "Enter integers correctly\n";
        } while (n < 1 || m < 1 || k < 1 || Math.max(n, m) < k);
        int t;
        do {
            t = readMe.ReadNextInt("number of wins in Matches");
        } while (t < 1);
        int m_t;
        do {
            m_t = readMe.ReadNextInt("Match or Tournament?\n1 - Match\n2 - Tournament");
        } while (m_t != 2 && m_t != 1);
        if (m_t == 1) {
            Player player1 = getPlayersType(1);
            Player player2 = getPlayersType(2);
            TicTacToeMatch match = new TicTacToeMatch(n, m, k, t, player1, player2);
            match.play();
        } else {

            do {
                c = readMe.ReadNextInt("number of circles");
                number = readMe.ReadNextInt("number of players");
            } while (number < 2 || c < 1);

            Player[] players = new Player[number];
            for (int i = 0; i < number; ++i) {
                players[i] = getPlayersType(i + 1);
            }

            Tournament tournament = new TicTacToeTournament(true, number, c, n, m, k, t, players);
            tournament.play();
        }
    }

    public static Player getPlayersType(int number) {
        int type;
        do {
            type = readMe.ReadNextInt("type of Player " + number + " \n1 - HumanPlayer\n2 - RandomPlayer\n3 - SequentialPlayer");
        } while (type < 1 || type > 3);
        if (type == 1) {
            return new HumanPlayer();
        } else if (type == 2) {
            return new RandomPlayer();
        } else {
            return new SequentialPlayer();
        }
    }
}
