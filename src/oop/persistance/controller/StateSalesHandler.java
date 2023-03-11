package oop.persistance.controller;

import Util.ControllerName;

/**
 * @author G
 */
public class StateSalesHandler implements Handler {

    static Controller SSC;

    static {
        SSC = ControllerFactory.createController(ControllerName.StateSales);
    }
}
