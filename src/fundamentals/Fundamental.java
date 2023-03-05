package fundamentals;

import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;

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
        String result = "";
        for (int i = 0; i < original.length(); i++) {
            char actualChar = original.charAt(i);
            if (Character.isUpperCase(actualChar)) {
                result = result + Character.toLowerCase(actualChar);
            } else if (Character.isLowerCase(actualChar)) {
                result = result + Character.toUpperCase(actualChar);
            }
        }
//        System.out.println(result);
        return result;
    }

    public static String orderChars(String unorderedText) {
        char[] charArray = unorderedText.toCharArray();
        String result = "";
        for (int i = 0; i < charArray.length - 1; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (charArray[i] > charArray[j]) {
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;

                }
            }

        }
        for (int i = 0; i < charArray.length; i++) {
            result += charArray[i];

        }

//        System.out.println(result);
        return result;
    }

    public static int countDifferentChars(String ordered) {
// nem orderedre:
//        int counter = 0;
//        List temp = new ArrayList<Character>();
//        for (int i = 0; i < ordered.length(); i++) {
//            if (!temp.contains(ordered.charAt(i))) {
//                counter++;
//                temp.add(ordered.charAt(i));
//            }
//        }
//        System.out.println(counter);
//        return counter;

// orderedre:
        int counter;
        if (ordered.isEmpty()) {
            counter = 0;
        }
        counter = 1;
        for (int i = 0; i < ordered.length() - 1; i++) {
            if (ordered.charAt(i) != ordered.charAt(i + 1)) {
                counter++;
            }
        }
        System.out.println(counter);
        return counter;
    }

    public static boolean containsCertainDigit(int number, int digit) {
        int abs = Math.abs(number);
        boolean result = false;
        int temp;
        if (number == 0 && digit == 0) {
            result = true;
        }
        while (abs > 0 && result == false) {
            temp = abs % 10;

            if (temp == digit) {
                result = true;
            }
            abs /= 10;
        }
//        System.out.println(result);
        return result;
    }

    public static String replaceUnderscores(
            String original, String... swapChars) {

    }

}
