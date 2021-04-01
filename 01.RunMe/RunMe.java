import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Run this code with provided arguments.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RunMe {

    public static void main(String[] args) {
        final byte[] password = parseArgs(args);
        ///key0(password);
        ///System.out.println("The first key was low-hanging fruit, can you found others?");
        ///System.out.println("Try to read, understand and modify code in keyX(...) functions");
        ///key1(password);
        ///key2(password);
        ///key3(password);
        ///key4(password);
        ///key5(password);
        ///key6(password);
        ///key7(password);
        ///key8(password);
        ///key9(password);
        ///key10(password);
        ///key11(password);
        ///key12(password);
        ///key13(password);
        ///key14(password);
    }

    private static void key0(final byte[] password) {
        // The result of print(...) function depends only by explicit arguments
        print(0, 0, password);
    }


    private static void key1(final byte[] password) {
        /*while ("1".length() == 1) {
        }*/

        print(1, 6355708432750832524L, password);
    }


    private static void key2(final byte[] password) {
        int result = 0;
        /*for (int i = 0; i < 300_000; i++) {
            for (int j = 0; j < 300_000; j++) {
                for (int k = 0; k < 300_000; k++) {
                    result ^= (i * 7) | (j + k);
                    result ^= result << 1;
                }
            }
        }*/

        print(2, 8751843538458437584L, password);
    }


    private static void key3(final byte[] password) {
        int result = 0;
        for (int i = 0; i < 2020; i++) {
            for (int j = 0; j < 2020; j++) {
                for (int k = 0; k < 2020; k++) {
                    for (int p = 0; p < 10; p++) {
                        result ^= (i * 3) | (j * 2 + k) & ~p;
                        result ^= result << 1;
                    }
                }
            }
        }

        print(3, result, password);
    }


    private static void key4(final byte[] password) {
        /*for (long i = Long.MIN_VALUE; i < Long.MAX_VALUE; i++) {
            if ((i ^ (i >> 32)) == 6095803860860580956L) {
                print(4, i, password);
            }
        }*/
        print(4, 6095803862278738188L, password);
    }


    private static final long PRIME = 1073741789;

    private static void key5(final byte[] password) {
        ///System.out.println((password[0] << 8) + password[1]);
        final long n = 1000_000_000_000_000L ^ ((password[0] << 8) + password[1]);
        long m = 0L;
        final long k = n - 1;
        long a1 = k % PRIME;
        System.out.println(n);
        System.out.println(k);
        long a2 = (k / 2) % PRIME;
        long a3 = (k / 3) % PRIME;
        long a4 = (k / 4) % PRIME;
        System.out.println(k%2);
        System.out.println(k%3);
        System.out.println(k%4);
        m += (a1 * (a1 + 1) ) / 2;
        m %= PRIME;
        m += (a2 * (a2 - 1) + a2);
        m %= PRIME;
        m += 3 * (a3 * (a3 + 1) ) / 2;
        m %= PRIME;
        m += 2 * (a4) * (a4 - 1) + a4 + a4 + a4;
        m %= PRIME;
        long result = m;
        /*for (long i = 0; i < n; i++) {
            result = (result + i + i / 2 + i / 3 + i / 4) % PRIME;
        }*/
        print(5, result, password);
    }


    private static void key6(final byte[] password) {

    /*
        \u002a\u002f\u0077\u0068\u0069\u006c\u0065\u0020\u0028\u0022\u0031\u0022
        \u002e\u006c\u0065\u006e\u0067\u0074\u0068\u0028\u0029\u0020\u003d\u003d
        \u0020\u0031\u0029\u003b\u0020\u0020\u006c\u006f\u006e\u0067\u0020\u0009
        \u0020\u0020\u0072\u0065\u0073\u0075\u006c\u0074\u0020\u003d\u0020\u000a
        \u0037\u0036\u0039\u0038\u0037\u0036\u0038\u0035\u0034\u0036\u0034\u004c
        \u002b\u0070\u0061\u0073\u0073\u0077\u006f\u0072\u0064\u005b\u0033\u005d
        \u003b\u002f\u002a
    */

    ///while ("1".length() == 1);
    /*long */ result = 76987685464L + password[3];
        print(6, 76987685464L + password[3], password);
    }


    private static void key7(final byte[] password) {
        /// Count the number of occurrences of the most frequent noun at the following page:
        /// https://docs.oracle.com/javase/specs/jls/se11/html/jls-6.html
        int result = 317;
        if (result != 0) {
            print(7, result, password);
        }
    }

    private static final String PATTERN = "Hello, World! Привет, Мир!";
    private static final int SMALL_REPEAT_COUNT = 10_000_000;

    private static void key8(final byte[] password) {
        String repeated = "";
        int result = PATTERN.hashCode();
        int k = 1;
        for(int i = 0; i < PATTERN.length(); ++i){
            k *= 31;
        }
        int a = result;
        for (int i = 0; i < SMALL_REPEAT_COUNT - 1; i++) {
            ///repeated += PATTERN;
            result *= k;
            result += a;
        }
        System.out.println(result);
        print(8, result /*repeated.hashCode()*/ , password);
    }


    private static final long LARGE_REPEAT_SHIFT = 27;
    private static final long LARGE_REPEAT_COUNT = 1L << LARGE_REPEAT_SHIFT;

    private static void key9(final byte[] password) {
        String repeated = "";
        int result = PATTERN.hashCode();
        int k = 1;
        for(int i = 0; i < PATTERN.length(); ++i){
            k *= 31;
        }
        int a = result;
        for (long i = 0; i < LARGE_REPEAT_COUNT - 1; i++) {
            ///repeated += PATTERN;
            result *= k;
            result += a;
        }

        print(9, result/*repeated.hashCode()*/, password);
    }


    private static void key10(final byte[] password) {
        print(10, 4508604576084534553L, password);
    }


    private static void key11(final byte[] password) {
        final BigInteger year = BigInteger.valueOf(-2020);
        final BigInteger prime = BigInteger.valueOf(PRIME);
        long result = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add)
                .filter(i -> year.multiply(i).add(prime).multiply(i).compareTo(BigInteger.ZERO) < 0)
                .mapToLong(i -> i.longValue() * password[i.intValue() % password.length])
                .sum();

        long result = 0;
        for(int i = 0; i < 531556; ++i){
            result += i * password[i%6];
        }
        System.out.println(result);
        print(11, result, password);
    }


    private static final long MAX_DEPTH = 100_000_000L;

    private static void key12(final byte[] password) {
        /*try {
            key12(password, 0, 0);
        } catch (StackOverflowError soe) {
            System.err.println("Stack overflow :(");
        }*/

        long result = 0L;
        for(int i = 0; i < MAX_DEPTH; ++i){
            result = (result ^ 656132467) + i * 17;
        }
        System.err.println(result);
        print(12, result, password);
    }

    private static void key12(final byte[] password, long depth, long result) {
        if (depth < MAX_DEPTH) {
            key12(password, depth + 1, (result ^ 656132467) + depth * 17);
        } else {
            print(12, result, password);
        }
    }


    private static void key13(final byte[] password) {
        final BigInteger secondsInFullTurn = BigInteger.valueOf(360 * 60 * 60);

        /*long result = Stream.iterate(BigInteger.ZERO, BigInteger.ONE::add)
                .map(secondsInFullTurn::multiply)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .longValue();*/
        print(13, -108000, password);
    }

     private static void key14(final byte[] data) {
        // History browsing, Yoo-hoo!
        print(14, 2047502843754345435L + data[5], data);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // You may ignore all code below this line.
    // It is not required to any all keys
    // ---------------------------------------------------------------------------------------------------------------

    private static void print(final int no, long result, final byte[] password) {
        final byte[] key = password.clone();
        for (int i = 0; i < 6; i++) {
            key[i] ^= result;
            result >>>= 8;
        }

        System.out.format("Key %d: https://www.kgeorgiy.info/courses/prog-intro/hw1/%s%n", no, key(key));
    }

    private static String key(final byte[] data) {
        DIGEST.update(SALT);
        DIGEST.update(data);
        DIGEST.update(SALT);
        final byte[] digest = DIGEST.digest();

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i != 0) {
                sb.append("-");
            }
            sb.append(KEYWORDS.get(digest[i] & 63));
        }
        return sb.toString();
    }

    private static byte[] parseArgs(final String[] args) {
        if (args.length != 6) {
            throw error("Expected 6 command line arguments, found: %d", args.length);
        }

        final byte[] bytes = new byte[args.length];
        for (int i = 0; i < args.length; i++) {
            final Byte value = VALUES.get(args[i].toLowerCase());
            if (value == null) {
                throw error("Expected keyword, found: %s", args[i]);
            }
            bytes[i] = value;
        }
        return bytes;
    }

    private static AssertionError error(final String format, final Object... args) {
        System.err.format(format, args);
        System.err.println();
        System.exit(1);
        throw new AssertionError();
    }

    private static final MessageDigest DIGEST;
    static {
        try {
            DIGEST = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Cannot create SHA-256 digest", e);
        }
    }

    private static final byte[] SALT = "divAcVuetDerrogWaph7ugLarbyianAvDapquev2Tholyat8KoakGenMysby".getBytes(StandardCharsets.UTF_8);

    private static final List<String> KEYWORDS = List.of(
            "abstract",
            "assert",
            "boolean",
            "break",
            "byte",
            "case",
            "catch",
            "char",
            "class",
            "const",
            "continue",
            "default",
            "do",
            "double",
            "else",
            "enum",
            "extends",
            "false",
            "final",
            "finally",
            "float",
            "for",
            "goto",
            "if",
            "implements",
            "import",
            "instanceof",
            "int",
            "interface",
            "long",
            "native",
            "new",
            "null",
            "package",
            "private",
            "protected",
            "public",
            "return",
            "short",
            "static",
            "strictfp",
            "super",
            "switch",
            "synchronized",
            "this",
            "throw",
            "throws",
            "transient",
            "true",
            "try",
            "var",
            "void",
            "volatile",
            "while",
            "Exception",
            "Error",
            "Object",
            "Number",
            "Integer",
            "Character",
            "String",
            "Math",
            "Runtime",
            "Throwable"
    );

    private static final Map<String, Byte> VALUES = IntStream.range(0, KEYWORDS.size())
            .boxed()
            .collect(Collectors.toMap(index -> KEYWORDS.get(index).toLowerCase(), Integer::byteValue));
}
