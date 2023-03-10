package view;

import entities.ProductEntity;

/**
 *
 * @author --G--
 */
public interface ProductCreatedEventListener {

    void productCreated(ProductEntity product);

}
