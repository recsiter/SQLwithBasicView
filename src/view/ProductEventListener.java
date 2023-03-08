package view;

import entities.ProductEntity;

/**
 *
 * @author --G--
 */
public interface ProductEventListener<T extends ProductEntity> {

    void productCreated(T product);

}
