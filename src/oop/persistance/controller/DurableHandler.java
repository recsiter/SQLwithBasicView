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
import static oop.persistance.controller.PerishableHandler.PP;

/**
 * @author G
 */
public class DurableHandler implements Handler {

    private static final DurableProductsJpaController DH;

    private DurableHandler() {
    }

    static {
        DH = (DurableProductsJpaController) ControllerFactory.createController(
                ControllerName.Durable);
    }

    public static void create(DurableProducts product, int tax) {
        try {
            DH.createAndMakeFK(product, tax);
        } catch (Exception ex) {
            Logger.getLogger(DurableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    public static void update(DurableProducts product) {
        try {
            DH.edit(product);
        } catch (Exception ex) {
            Logger.getLogger(PerishableHandler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    public static List<DurableProducts> searchByIdPart(String idPart) {
        return DH.searchByIdPart(idPart);
    }

    public static List<SelectByCriticalQuantity> selectByCriticalQuantity() {
        return DH.selectByCriticalQuantity();
    }

    public static List<DurableProducts> findAll() {
        return DH.findDurableProductsEntities();
    }

    public List<DurableProducts> orderByColumnName(String columnName) {
        return DH.orderByQuery(columnName);
    }
}
