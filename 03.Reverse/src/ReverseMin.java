import java.util.Scanner;

public class ReverseMin {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int[] lengthOfLines = new int[1000010];
        int[] column = new int[1000010];
        int[] row = new int[1000010];
        int n = 0;
        for (int i = 0; i < 1000010; ++i) {
            column[i] = Integer.MAX_VALUE;
            row[i] = Integer.MAX_VALUE;
        }
        while (a.hasNextLine()) {
            Scanner b = new Scanner(a.nextLine());
            int j = 0;
            while (b.hasNext()) {
                int cur = b.nextInt();
                column[j] = Math.min(column[j], cur);
                row[n] = Math.min(row[n], cur);
                ++j;
            }
            lengthOfLines[n] = j;
            n++;
        }
        for (int i = 0; i < n; i++) {
            int m = lengthOfLines[i];
            for (int j = 0; j < m; j++) {
                System.out.print(Math.min(column[j], row[i]));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
