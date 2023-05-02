package fundamentaltask;

import Util.ControllerName;
import entities.DurableProducts;
import fundamentals.Fundamental;
import fundamentals.FundamentalHidden;
import java.util.Random;
import oop.persistance.controller.DurableHandler;
import oop.persistance.controller.HandlerFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.Suite;

/**
 *
 * @author --G--
 */
@Suite.SuiteClasses({
    UnitTests.class,})
public class FundamentalTest {

    private static int scores = 0;

    @AfterClass
    public static void afterClass() {
        System.out.println("You might have: " + scores + " scores :-)");
    }

    @Test
    public void testInvertCase() {
        System.out.println("invertCase");
        String original = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        String expResult = "abcdefghijklmnopqrstuvxyz";
        String result = Fundamental.invertCase(original);
        assertEquals(expResult, result);
        original = "abcdefghijklmnopqrstuvxyz";
        expResult = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        result = Fundamental.invertCase(original);
        assertEquals(expResult, result);
        original = "";
        expResult = "";
        result = Fundamental.invertCase(original);
        assertEquals(expResult, result);
        scores += 10;

    }

    /**
     * Test of testMySolutions method, of class Fundamental.
     */
    /**
     * Test of orderChars method, of class Fundamental.
     */
    @Test
    public void testOrderChars() {
        System.out.println("orderChars");
        String unorderedText = "tewoiprvqcaaaporvaourefal";
        String expResult = FundamentalHidden.orderChars(unorderedText);
        String result = Fundamental.orderChars(unorderedText);
        assertEquals(expResult, result);
        scores += 10;
    }

    /**
     * Test of countDifferentChars method, of class Fundamental.
     */
    @Test
    @Ignore
    public void testCountDifferentChars() {
        System.out.println("countDifferentChars");
        String ordered = "abcdefghijklmnopqrstuvxyz";
        int expResult = ordered.length();
        int result = Fundamental.countDifferentChars(ordered);
        assertEquals(expResult, result);
        ordered = "aaabbbcdde";
        expResult = FundamentalHidden.countDifferentChars(ordered);
        result = Fundamental.countDifferentChars(ordered);
        assertEquals(expResult, result);
        ordered = "";
        expResult = 0;
        result = Fundamental.countDifferentChars(ordered);
        assertEquals(expResult, result);
        ordered = "z";
        expResult = 1;
        result = Fundamental.countDifferentChars(ordered);
        assertEquals(expResult, result);
        ordered = "aaabbbcddrr";
        expResult = FundamentalHidden.countDifferentChars(ordered);
        result = Fundamental.countDifferentChars(ordered);
        assertEquals(expResult, result);
        ordered = FundamentalHidden.orderChars(
                "alsjropwhgnfoqproghqorhgjqoprjpodgpjquroqjfgpzur");
        expResult = FundamentalHidden.countDifferentChars(ordered);
        result = Fundamental.countDifferentChars(ordered);
        assertEquals(expResult, result);
        scores += 10;
    }

    /**
     * Test of containsCertainDigit method, of class Fundamental.
     */
    @Test
    public void testContainsCertainDigit() {
        System.out.println("containsCertainDigit");
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int number = random.nextInt(-1000000, 1000000);
            int digit = random.nextInt(10);
            boolean expResult = FundamentalHidden.containsCertainDigit(number,
                    digit);
            boolean result = Fundamental.containsCertainDigit(number, digit);
            assertEquals(expResult, result);
        }
        assertEquals(true, Fundamental.containsCertainDigit(0, 0));
        assertEquals(true, Fundamental.containsCertainDigit(-1, 1));
        assertEquals(false, Fundamental.containsCertainDigit(0, 1));
        scores += 10;
    }

    /**
     * Test of replaceUnderscores method, of class Fundamental.
     */
    @Test
    public void testReplaceUnderscores() {
        System.out.println("replaceUnderscores");
        String original = "";
        String swapChars = "a";
        String expResult = "";
        String result = Fundamental.replaceUnderscores(original, swapChars);
        assertEquals(expResult, result);
        original = "___";
        expResult = "abc";
        result = Fundamental.replaceUnderscores(original, "a", "b", "c");
        assertEquals(expResult, result);
        original = "alak";
        expResult = "alak";
        result = Fundamental.replaceUnderscores(original, "a", "b", "c");
        assertEquals(expResult, result);
        original = "A kígyó _lb_völ__az_áldozatát_mielőtt_megöli_azt.";
        result = Fundamental.replaceUnderscores(original, "e", "ű", "i", " ");
        expResult = "A kígyó elbűvöli az áldozatát mielőtt megöli azt.";
        assertEquals(expResult, result);
        original = "t_tű";
        swapChars = "eűi ";
        result = Fundamental.replaceUnderscores(original, "e", "ű", "i", " ");
        expResult = "tetű";
        assertEquals(expResult, result);
        scores += 10;
    }

}
