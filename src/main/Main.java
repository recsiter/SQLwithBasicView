package main;

import fundamentals.Fundamental;
import static fundamentals.Fundamental.replaceUnderscores;

/**
 * @author --G--
 */
public class Main {

    public static void main(String[] args) {
        //backup
        System.out.println("Omnem dimittite spem, o vos intrantes!");
        //you can use this to test your solutions
        //        Fundamental.testMySolutions();
        String changed = replaceUnderscores(
                "Szer_tem___kihívásokat_és_a_vizsgákat.", "e", " ", "a", " ");
        System.out.println(
                replaceUnderscores("fe_e_e_ku__a", " "));
    }

}
