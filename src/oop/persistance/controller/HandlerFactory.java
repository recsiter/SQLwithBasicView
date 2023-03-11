package oop.persistance.controller;

import Util.ControllerName;

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
            case Perishable ->
                resultHandler = PH;
            case Durable ->
                resultHandler = DH;
            case StateSales ->
                resultHandler = SSH;

            default ->
                throw new AssertionError();
        }
        return resultHandler;
    }
}
