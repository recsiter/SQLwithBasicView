package oop.persistance.controller;

import entities.ProductEntity;

/**
 * @author G
 * @param <T>
 */
public interface CreateAble<T extends ProductEntity> {

    public void createAndMakeFK(T product);
}
