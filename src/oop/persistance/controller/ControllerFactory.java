package oop.persistance.controller;

import entities.ProductEntity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author G
 */
public class ControllerFactory {

    public static PerishableProductsJpaController PC;
    public static DurableProductsJpaController DC;
    public static StateSalesTaxJpaController SC;
    public static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("Exam2023-03-06PU");
        PC = new PerishableProductsJpaController(emf);
        DC = new DurableProductsJpaController(emf);
        SC = new StateSalesTaxJpaController(emf);
    }

    public ControllerFactory() {

    }

    public static Controller createController(ControllerName name) {
        Controller resultController;
        switch (name) {
            case Perishable:
                resultController = PC;
                break;
            case Durable:
                resultController = DC;
                break;
            case StateSales:
                resultController = SC;
                break;

            default:
                throw new AssertionError();
        }
        return resultController;
    }
}
