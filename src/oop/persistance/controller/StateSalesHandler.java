package oop.persistance.controller;

import oop.persistance.controller.Handler;
import oop.persistance.controller.Controller;
import oop.persistance.controller.ControllerFactory;
import oop.persistance.controller.ControllerName;

/**
 * @author G
 */
public class StateSalesHandler implements Handler {

    static Controller SSC;

    static {
        SSC = ControllerFactory.createController(ControllerName.StateSales);
    }
}
