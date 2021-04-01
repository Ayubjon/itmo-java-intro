import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class ReverseMax {
    public static void main(String[] args) {

        Scanner a = new Scanner(System.in);

        List<ArrayList<Integer>> bigArray = new ArrayList<>();
        int R;
        int C = 0;
        int i = 0;
        while (a.hasNextLine()) {
            Scanner b = new Scanner(a.nextLine());

            bigArray.add(new ArrayList<>());
            int j = 0;
            while (b.hasNext()) {
                bigArray.get(i).add(b.nextInt());
                ++j;
            }
            i++;
            C = Math.max(C, j);
        }

        int n = bigArray.size();
        R = n;
        int[] Column = new int[C];
        int[] Row = new int[R];
        for (i = 0; i < C; ++i) {
            Column[i] = -2147483647;
        }
        for (i = 0; i < R; ++i) {
            Row[i] = -2147483647;
        }
        for (i = n - 1; i > -1; i--) {
            int m = bigArray.get(i).size();

            for (int j = m - 1; j > -1; j--) {
                Column[j] = Math.max(Column[j], bigArray.get(i).get(j));
                Row[i] = Math.max(Row[i], bigArray.get(i).get(j));
            }
        }
        for (i = 0; i < n; i++) {
            int m = bigArray.get(i).size();

            for (int j = 0; j < m; j++) {
                System.out.print(Math.max(Column[j], Row[i]));
                System.out.print(" ");

            }
            System.out.println();
        }
    }
}
