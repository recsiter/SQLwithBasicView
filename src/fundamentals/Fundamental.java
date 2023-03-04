package fundamentals;

/**
 * @author G
 */
public class Fundamental {

    public static void testMySolutions() {
        String invertedText = invertCase("AsewQzE"); // result: aSEWqZe
        System.out.println(invertedText);
        String ordered = orderChars("tewoiprvqcaaaporvwaourefal"); // aaaaaceefiloooppqrrrtuvvww
        System.out.println(ordered);
        int differentCharCount = countDifferentChars(ordered); // 14
        System.out.println("number of different chars:" + differentCharCount);
        boolean contains = containsCertainDigit(4567, 6); // true
        System.out.println("4567 contains 6: " + contains);
        String changed = replaceUnderscores(
                "Szer_tem___kihívásokat_és_a_vizsgákat.", "e", " ", "a", " ");// Szeretem a kihívásokat és a vizsgákat.
        System.out.println("A nap mondása: " + changed);

    }

    public static String invertCase(String original) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String orderChars(String unorderedText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static int countDifferentChars(String ordered) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static boolean containsCertainDigit(int number, int digit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String replaceUnderscores(
            String original, String... swapChars) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
