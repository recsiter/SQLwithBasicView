package oop.persistance.controller;

import entities.ProductEntity;

/**
 *
 * @author --G--
 */
public interface QuantityEditAble {

    void quantityAdd(int addAmount);

    void quantitySubstract(int minusAmount);
}
