import java.util.*;

public class StringList {
    private String[] a = new String[1];
    private int currentPos = 0, currentSize = 1;

    public void add(String s) {
        a[currentPos++] = s;
        if (currentPos == currentSize) {
            currentSize *= 2;
            a = Arrays.copyOf(a, currentSize);
        }
    }

    public String get(int s) {
        if (s < currentPos) return a[s];
        else return null;
    }
}
