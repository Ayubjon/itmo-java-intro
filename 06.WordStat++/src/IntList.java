import java.util.*;

public class IntList {
    private int[] a = new int[1];
    private int currentPos = 0;
    private int currentSize = 1;

    public void add(int s) {
        a[currentPos++] = s;
        if (currentPos == currentSize) {
            currentSize *= 2;
            a = Arrays.copyOf(a, currentSize);
        }
    }

    public int get(int s) {
        if (s < currentPos) return a[s];
        else return -1;
    }

    public int size() {
        return currentPos;
    }
}
