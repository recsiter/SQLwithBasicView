package oop.persistance.controller;

import entities.GroupByTaxId;
import oop.persistance.controller.Handler;
import entities.PerishableProducts;
import entities.PerishableProducts;
import entities.SelectByCriticalQuantity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oop.persistance.controller.Controller;
import oop.persistance.controller.ControllerFactory;
import oop.persistance.controller.ControllerName;
import oop.persistance.controller.PerishableProductsJpaController;

/**
 * @author G
 */
public class PerishableHandler implements Handler {

    static final PerishableProductsJpaController PC;

    static {
        PC = (PerishableProductsJpaController) ControllerFactory.
                createController(ControllerName.Perishable);
    }

    private PerishableHandler() {
    }

    public static void create(PerishableProducts product, int tax) {
        try {
            PC.createAndMakeFK(product, tax);
        } catch (Exception ex) {
            Logger.getLogger(PerishableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public static void update(PerishableProducts product) {
        try {
            PC.edit(product);
        } catch (Exception ex) {
            Logger.getLogger(PerishableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    public static void statsByTax() {
        PC.groupingByTaxId();
    }

    public static List<PerishableProducts> searchByNamePart(String namePart) {
        return PC.searchByNamePart(namePart);
    }

    public static List<SelectByCriticalQuantity> selectByCriticalQuantity() {

        return PC.selectByCriticalQuantity();
    }

    public static List<PerishableProducts> findAll() {
        return PC.findPerishableProductsEntities();
    }

    public static List<GroupByTaxId> groupByTaxId() {
        return PC.groupingByTaxId();
    }

    public static List<PerishableProducts> orderByColumnName(String columnName) {
        return PC.orderByQuery(columnName);
    }

}
