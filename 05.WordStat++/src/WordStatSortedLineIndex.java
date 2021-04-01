import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WordStatSortedLineIndex {
    public static void main(String[] args) {
        Map<String, IntList> cLines = new HashMap<>();
        Map<String, IntList> cPoss = new HashMap<>();
        try {
            String inp = args[0];
            FastScanner reader = new FastScanner(new File(inp));
            try {
                int curPos = 1;
                int curLine = 1;
                String ans;
                while (true) {
                    int to = 0, from = 0;
                    if (reader.isNewLine()) {
                        curPos = 1;
                        curLine++;
                    }
                    String cur = reader.next();
                    if (cur == null) {
                        break;
                    }
                    cur += " ";
                    while (to < cur.length()) {
                        if (!isValid(cur.charAt(to))) {
                            if (isValid(cur.charAt(from))) {
                                ans = cur.substring(from, to).toLowerCase();
                                IntList m = cLines.getOrDefault(ans, new IntList());
                                m.add(curLine);
                                cLines.put(ans, m);
                                m = cPoss.getOrDefault(ans, new IntList());
                                m.add(curPos);
                                cPoss.put(ans, m);
                                curPos++;
                            }
                            from = to + 1;
                        }
                        to++;
                    }
                    if (!reader.hasNext()) {
                        break;
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
        } catch (UnsupportedEncodingException e) {
            System.out.println("There is another Encoding");
        }

        ArrayList<String> keys = new ArrayList<>(cLines.keySet());
        Collections.sort(keys);
        try {
            String out = args[1];
            Writer writer = new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8);
            try {
                for (String s : keys) {
                    IntList m1 = cLines.getOrDefault(s, new IntList());
                    IntList m2 = cPoss.getOrDefault(s, new IntList());
                    writer.write(s + " " + m1.size() + " ");
                    for (int j = 0; j < m1.size(); ++j) {
                        writer.write(m1.get(j) + ":" + m2.get(j));
                        if (j + 1 != m1.size()) writer.write(" ");
                    }
                    writer.write('\n');
                }
            } catch (IOException e) {
                System.out.println("We are sorry, there is some problem with input/output");
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("We are sorry, there is some problem with input/output");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no file to open");
        }
    }

    private static boolean isValid(char a) {
        return (Character.isLetter(a) || Character.DASH_PUNCTUATION == Character.getType(a) || (a == '\''));
    }
}