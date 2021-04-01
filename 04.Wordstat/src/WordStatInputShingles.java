import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatInputShingles {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        try {
            String inp = args[0];
            FastScanner reader = new FastScanner(new File(inp));
            try {
                String ans;
                while (true) {
                    int to = 0, from = 0;
                    String cur = reader.next();
                    if (cur == null) {
                        break;
                    }
                    cur += " ";
                    while (to < cur.length()) {
                        if (!isValid(cur.charAt(to))) {
                            from = to + 1;
                        }
                        to++;
                        if (to - from >= 3) {
                            if (isValid(cur.charAt(from))) {
                                ans = cur.substring(to - 3, to).toLowerCase();
                                if (map.containsKey(ans)) {
                                    map.put(ans, map.get(ans) + 1);
                                } else {
                                    map.put(ans, 1);
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("We are sorry, there is some problem with input/output");
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("We are sorry, there is some problem with input/output");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no file to open");
        }


        try {
            String out = args[1];
            Writer writer = new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8);
            try {
                for (Map.Entry<String, Integer> m : map.entrySet()) {
                    writer.write(m.getKey() + " " + m.getValue() + "\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("We are sorry, there is some problem with input/output");
            }
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("We are sorry, there is some problem with input/output");
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no file to open");
        }
    }

    private static boolean isValid(char a) {
        return (Character.isLetter(a) || Character.DASH_PUNCTUATION == Character.getType(a) || (a == '\''));
    }
}






