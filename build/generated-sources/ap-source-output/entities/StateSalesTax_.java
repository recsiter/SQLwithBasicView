package entities;

import entities.DurableProducts;
import entities.PerishableProducts;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-08T18:38:19", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(StateSalesTax.class)
public class StateSalesTax_ { 

    public static volatile CollectionAttribute<StateSalesTax, DurableProducts> durableProductsCollection;
    public static volatile SingularAttribute<StateSalesTax, Integer> taxKey;
    public static volatile SingularAttribute<StateSalesTax, Double> multiplier;
    public static volatile SingularAttribute<StateSalesTax, String> description;
    public static volatile CollectionAttribute<StateSalesTax, PerishableProducts> perishableProductsCollection;

}