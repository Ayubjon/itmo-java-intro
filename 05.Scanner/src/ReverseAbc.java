import java.util.*;
import java.io.*;

public class ReverseAbc {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);

        String[][] data = new String[1][];
        int position = 0;
        int positionLine = 0;
        String[] line = new String[1];
        String cur;
        int[] lineLength = new int[1];

        try {
            while (in.hasNext()) {
                if (in.isNewLine()) {
                    if (position == data.length) {
                        data = Arrays.copyOf(data, data.length * 2);
                        lineLength = Arrays.copyOf(lineLength, lineLength.length * 2);
                    }
                    data[position] = line;
                    lineLength[position++] = positionLine;
                    line = new String[1];
                    positionLine = 0;
                }
                if (in.hasNext()) {
                    cur = in.next();
                    if (positionLine == line.length) {
                        line = Arrays.copyOf(line, line.length * 2);
                    }
                    line[positionLine++] = cur;
                }
            }
        } catch (IOException e) {
            System.err.println("We are sorry, there is some problem with input/output");
        }
        try {
            if (in.isNewLine()) {
                positionLine = 0;
                if (position == data.length) {
                    data = Arrays.copyOf(data, data.length * 2);
                    lineLength = Arrays.copyOf(lineLength, lineLength.length * 2);
                }
                data[position] = line;
                lineLength[position] = positionLine;
            }
        } catch (IOException e) {
            System.err.println("Something go wrong :(");
        }
        for (int i = position - 1; i > -1; --i) {
            for (int j = lineLength[i] - 1; j > -1; --j) {
                System.out.print(data[i][j] + " ");
            }
            System.out.print('\n');
        }
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Something go wrong :(");
        }
    }
}
