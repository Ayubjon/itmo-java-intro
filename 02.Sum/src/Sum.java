public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (String arg : args) {
            int m = arg.length();
            int s = 1;
            StringBuilder a = new StringBuilder();
            for (int j = 0; j < m; ++j) {
                if (!Character.isWhitespace(arg.charAt(j))) {
                    if (arg.charAt(j) == '-') s = -1;
                    else if (Character.isDigit(arg.charAt(j))) {
                        a.append(arg.charAt(j));
                    }
                } else {
                    if (!a.toString().equals("")) {
                        int cur = Integer.parseInt(a.toString());
                        sum += s * cur;
                        s = 1;
                        a = new StringBuilder();
                    }
                }
            }
            if (!a.toString().equals("")) {
                int cur = Integer.parseInt(a.toString());
                sum += s * cur;
            }
        }
        System.out.println(sum);
    }
}
