package main;

import entities.DurableProducts;
import entities.GroupByTaxId;
import fundamentals.Fundamental;
import static fundamentals.Fundamental.replaceUnderscores;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entities.PerishableProducts;
import entities.SelectByCriticalQuantity;
import java.math.BigDecimal;
import java.util.Date;

import oop.persistance.controller.StateSalesTaxJpaController;
import view.MainForm;

/**
 * @author --G--
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //backup
        System.out.println("Omnem dimittite spem, o vos intrantes!");
        //you can use this to test your solutions
        //        Fundamental.testMySolutions();
        //        String changed = replaceUnderscores(
        //                "Szer_tem___kihívásokat_és_a_vizsgákat.", "e", " ", "a", " ");
        //        System.out.println(
        //                replaceUnderscores("fe_e_e_ku__a", " "));
        //        System.out.println(numberChecker("12345678"));
//        LocalDate exp = LocalDate.of(2023, Month.MARCH, 20);
//        System.out.println(getDaysTillPerishing(exp));
        MainForm form = new MainForm();

    }
}
