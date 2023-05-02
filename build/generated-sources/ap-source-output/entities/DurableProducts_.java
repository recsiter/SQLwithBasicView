package entities;

import entities.StateSalesTax;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-04-11T15:57:15", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(DurableProducts.class)
public class DurableProducts_ { 

    public static volatile SingularAttribute<DurableProducts, Integer> nettoPrice;
    public static volatile SingularAttribute<DurableProducts, Integer> criticalQuantity;
    public static volatile SingularAttribute<DurableProducts, BigDecimal> grossWeight;
    public static volatile SingularAttribute<DurableProducts, Integer> quantity;
    public static volatile SingularAttribute<DurableProducts, String> amountUnits;
    public static volatile SingularAttribute<DurableProducts, String> articleNumber;
    public static volatile SingularAttribute<DurableProducts, Integer> warantyPeriod;
    public static volatile SingularAttribute<DurableProducts, StateSalesTax> taxId;
    public static volatile SingularAttribute<DurableProducts, String> name;
    public static volatile SingularAttribute<DurableProducts, String> family;
    public static volatile SingularAttribute<DurableProducts, String> brand;

}