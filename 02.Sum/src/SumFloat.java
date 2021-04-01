public class SumFloat {
    public static void main(String[] args) {
        float Sum = 0;
        for (String arg : args) {
            int f = -1;
            int Length = 0;
            for (int to = 0; to < arg.length(); to++) {
                if (!Character.isWhitespace(arg.charAt(to))) {
                    if (f == -1) {
                        f = to;
                    }
                    Length++;
                } else {
                    if (Length > 0) {
                        Sum += Float.parseFloat(arg.substring(f, to));
                        Length = 0;
                        f = -1;
                    }
                }
            }
            if (Length != 0) {
                Sum += Float.parseFloat(arg.substring(arg.length() - Length));
            }
        }
        System.out.println(Sum);
    }
}
