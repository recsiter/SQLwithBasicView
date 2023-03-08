package view;

import entities.ProductEntity;

/**
 *
 * @author --G--
 */
public interface ProductQuantityChangeListener<T extends ProductEntity> {

    public void changeQuantity(T product);
}
