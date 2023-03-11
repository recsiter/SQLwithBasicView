package oop.persistance.controller;

import oop.persistance.controller.Handler;
import entities.DurableProducts;
import entities.DurableProducts;
import entities.PerishableProducts;
import entities.SelectByCriticalQuantity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaQuery;
import oop.persistance.controller.Controller;
import oop.persistance.controller.ControllerFactory;
import oop.persistance.controller.ControllerName;
import oop.persistance.controller.DurableProductsJpaController;
import static oop.persistance.controller.PerishableHandler.PC;

/**
 * @author G
 */
public class DurableHandler implements Handler {

    private static final DurableProductsJpaController DC;

    static {
        DC = (DurableProductsJpaController) ControllerFactory.createController(
                ControllerName.Durable);
    }

    private DurableHandler() {
    }

    public static void create(DurableProducts product) {
        try {
            DC.createAndMakeFK(product);
        } catch (Exception ex) {
            Logger.getLogger(DurableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public static void update(DurableProducts product) {
        try {
            DC.edit(product);
        } catch (Exception ex) {
            Logger.getLogger(PerishableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    public static List<DurableProducts> searchByIdPart(String idPart) {
        return DC.searchByIdPart(idPart);
    }

    public static List<SelectByCriticalQuantity> selectByCriticalQuantity() {
        return DC.selectByCriticalQuantity();
    }

    public static List<DurableProducts> findAll() {
        return DC.findDurableProductsEntities();
    }

    public static List<DurableProducts> orderByColumnName(String columnName) {
        return DC.orderByQuery(columnName);
    }
}
