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
import oop.persistance.controller.DurableProductsJpaController;
import oop.persistance.controller.PerishableProductsJpaController;
import oop.persistance.controller.StateSalesTaxJpaController;

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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "Exam2023-03-06PU");
        PerishableProductsJpaController PP
                = new PerishableProductsJpaController(factory);
//        List<PerishableProduct> employees = PP.findPerishableProductsEntities();
//        employees.forEach(e -> {
//            System.out.println(e.calculateGrossPrice());
//        });
//        PerishableProducts product = new PerishableProducts("PP00000001",
//                "Tarja", "Hentesáru",
//                2800,
//                11, "kg", 3, new Date(123, 03, 14), new Date(123, 03,
//                        07));
//        StateSalesTaxJpaController sstc
//                = new StateSalesTaxJpaController(factory);
//        PP.createAndMakeFK(product, 28);
        DurableProductsJpaController DP = new DurableProductsJpaController(
                factory);
//        DurableProducts product
//                = new DurableProducts("DP00000000", "Babkonzerv", "konzerv",
//                        2100, 20, "kg", 4, 23, new BigDecimal(2.3));
//        DP.createAndMakeFK(product, 27);
//        System.out.println(DP.searchByIdPart("k").
//                get(0));
//        System.out.println(PP.searchByIdPart("sdf").
//                get(0));
//        List<GroupByTaxId> group = PP.groupingByTaxId();
//
//        for (GroupByTaxId g : group) {
//            System.out.println(g.toString());
//
//        }
//        List<SelectByCriticalQuantity> cq = PP.selectByCriticalQuantity();
//        for (SelectByCriticalQuantity selectByC : cq) {
//            System.out.println(selectByC);
//        }
        List<SelectByCriticalQuantity> list = DP.selectByCriticalQuantity();
        for (SelectByCriticalQuantity selectByCr : list) {
            System.out.println(selectByCr);
        }
    }

    public static int getDaysTillPerishing(LocalDate exp) {
        return exp.
                getDayOfYear() - LocalDate.now().
                        getDayOfYear();
    }

}
