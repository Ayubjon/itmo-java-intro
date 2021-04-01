import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatInput {
    public static void main(String[] args) {
        try {
            String inp = args[0];
            String out = args[1];
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inp), StandardCharsets.UTF_8));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8));
            try {
                String ans;
                Map<String, Integer> map = new LinkedHashMap<>();
                while (true) {
                    int to = 0, from = 0;
                    String cur = reader.readLine();
                    if (cur == null) {
                        break;
                    }
                    cur += " ";
                    while (to < cur.length()) {

                        boolean isLetter = Character.isLetter(cur.charAt(to));
                        boolean isDash = Character.DASH_PUNCTUATION == Character.getType(cur.charAt(to));
                        boolean isApos = (cur.charAt(to) == '\'');

                        if (!(isLetter || isDash || isApos)) {

                            boolean isLetter2 = Character.isLetter(cur.charAt(from));
                            boolean isDash2 = Character.DASH_PUNCTUATION == Character.getType(cur.charAt(from));
                            boolean isApos2 = (cur.charAt(from) == '\'');

                            if (isLetter2 || isDash2 || isApos2) {

                                ans = cur.substring(from, to).toLowerCase();

                                if (map.containsKey(ans)) {
                                    map.put(ans, map.get(ans) + 1);
                                } else {
                                    map.put(ans, 1);
                                }
                            }
                            from = to + 1;
                        }
                        to++;
                    }
                }
                for (Map.Entry<String, Integer> e : map.entrySet()) {
                    writer.println(e.getKey() + " " + e.getValue());
                }
            } catch (IOException ignored) {
            } finally {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
