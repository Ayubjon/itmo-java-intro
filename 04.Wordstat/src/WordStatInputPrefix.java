import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatInputPrefix {
    public static void main(String[] args) {
        String inp = args[0];
        String out = args[1];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inp), StandardCharsets.UTF_8)); PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8))) {
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
                        from = to + 1;
                    }
                    to++;
                    if (to - from == 3) {
                        boolean isLetter2 = Character.isLetter(cur.charAt(from));
                        boolean isDash2 = Character.DASH_PUNCTUATION == Character.getType(cur.charAt(from));
                        boolean isApos2 = (cur.charAt(from) == '\'');
                        if (isLetter2 || isDash2 || isApos2) {
                            ans = cur.substring(from, to);
                            ans = ans.toLowerCase();
                            if (map.containsKey(ans)) {
                                map.put(ans, map.get(ans) + 1);
                            } else {
                                map.put(ans, 1);
                            }
                        }
                    }
                }
            }
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                writer.println(e.getKey() + " " + e.getValue());
            }
        } catch (IOException ignored) {
        }
    }
}
