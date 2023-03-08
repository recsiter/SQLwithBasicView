package oop.persistance.controller;

import oop.persistance.controller.Controller;
import oop.persistance.controller.ControllerName;

/**
 * @author G
 */
public class HandlerFactory {

    static PerishableHandler PH;
    static DurableHandler DH;
    static StateSalesHandler SSH;

    public static Handler createHandler(ControllerName name) {

        Handler resultHandler;
        switch (name) {
            case Perishable:
                resultHandler = PH;
                break;
            case Durable:
                resultHandler = DH;
                break;
            case StateSales:
                resultHandler = SSH;
                break;

            default:
                throw new AssertionError();
        }
        return resultHandler;
    }
}
