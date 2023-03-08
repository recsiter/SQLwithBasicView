package oop.persistance.controller;

import oop.persistance.controller.Handler;
import entities.DurableProducts;
import entities.DurableProducts;
import entities.SelectByCriticalQuantity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.CriteriaQuery;
import oop.persistance.controller.Controller;
import oop.persistance.controller.ControllerFactory;
import oop.persistance.controller.ControllerName;
import oop.persistance.controller.DurableProductsJpaController;

/**
 * @author G
 */
public class DurableHandler implements Handler {

    private static final DurableProductsJpaController DP;

    private DurableHandler() {
    }

    static {
        DP = (DurableProductsJpaController) ControllerFactory.createController(
                ControllerName.Durable);
    }

    public static void create(DurableProducts product, int tax) {
        try {
            DP.createAndMakeFK(product, tax);
        } catch (Exception ex) {
            Logger.getLogger(DurableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public static void searchByIdPart(String idPart) {
        DP.searchByIdPart(idPart);
    }

    public static List<SelectByCriticalQuantity> selectByCriticalQuantity() {
        return DP.selectByCriticalQuantity();
    }

    public static List<DurableProducts> findAll() {
        return DP.findDurableProductsEntities();
    }
}
