package game;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadMe {
    final Scanner in;
    final PrintStream out;

    public ReadMe(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String ReadNextLine(String message) {
        out.printf("Enter %s: ", message);
        try {
            return in.nextLine();
        } catch (NoSuchElementException e) {
            out.print("The input ended, but you didn't enter all of values");
            System.exit(0);
        } catch (IllegalStateException e) {
            out.printf("Something went wrong:  %s. Please, restart the program", e.getMessage());
            System.exit(0);
        }
        return null;
    }

    public int ReadNextInt(String message) {
        while (true) {
            String string = ReadNextLine(message);
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e) {
                out.print("It's not a number! Please, try one more");
            }
        }
    }
}
