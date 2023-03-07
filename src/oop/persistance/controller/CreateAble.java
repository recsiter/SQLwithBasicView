package oop.persistance.controller;

import entities.PerishableProducts;
import entities.ProductEntity;
import entities.StateSalesTax;

/**
 * @author G
 */
public interface CreateAble<T extends ProductEntity> {

    public void createAndMakeFK(T product, int tax);
}
