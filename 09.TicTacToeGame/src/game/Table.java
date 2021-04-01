package game;

public class Table {
    private final int[] table;
    private final int n;

    public Table(int n) {
        this.n = n;
        table = new int[n + 5];
        for (int i = 1; i <= n; ++i) {
            table[i] = 0;
        }
    }

    public void add(int i, int add) {
        table[i] += add;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= n; ++i) {
            s.append("Player with number ").append(i).append(" has ").append(table[i]).append(" score \n");
        }
        return s.toString();
    }
}
