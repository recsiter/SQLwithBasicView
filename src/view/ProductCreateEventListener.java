package view;

import entities.ProductEntity;

/**
 *
 * @author --G--
 */
public interface ProductCreateEventListener<T extends ProductEntity> {

    void productCreated(T product);

}
