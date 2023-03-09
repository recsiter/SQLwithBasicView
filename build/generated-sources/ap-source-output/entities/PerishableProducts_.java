package entities;

import entities.StateSalesTax;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-09T20:32:29", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(PerishableProducts.class)
public class PerishableProducts_ { 

    public static volatile SingularAttribute<PerishableProducts, Integer> nettoPrice;
    public static volatile SingularAttribute<PerishableProducts, Integer> criticalQuantity;
    public static volatile SingularAttribute<PerishableProducts, Integer> quantity;
    public static volatile SingularAttribute<PerishableProducts, Date> productionDate;
    public static volatile SingularAttribute<PerishableProducts, String> amountUnits;
    public static volatile SingularAttribute<PerishableProducts, String> articleNumber;
    public static volatile SingularAttribute<PerishableProducts, StateSalesTax> taxId;
    public static volatile SingularAttribute<PerishableProducts, String> name;
    public static volatile SingularAttribute<PerishableProducts, String> family;
    public static volatile SingularAttribute<PerishableProducts, String> brand;
    public static volatile SingularAttribute<PerishableProducts, Date> expirationDate;

}