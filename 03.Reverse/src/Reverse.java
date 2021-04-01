import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Reverse {
    public static void main(String[] args) {

        Scanner a = new Scanner(System.in);

        List<ArrayList<Integer>> bigArray = new ArrayList<>();
        int i = 0;
        while (a.hasNextLine()) {
            Scanner b = new Scanner(a.nextLine());

            bigArray.add(new ArrayList<>());

            while (b.hasNext()) {
                bigArray.get(i).add(b.nextInt());
            }
            i++;
        }

        int n = bigArray.size();

        for (i = n - 1; i > -1; i--) {
            int m = bigArray.get(i).size();

            for (int j = m - 1; j > -1; --j) {
                System.out.print(bigArray.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
