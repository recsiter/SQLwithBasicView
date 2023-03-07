package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Pattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author G
 */
@Entity
@Table(name = "perishable_products")
@NamedQueries({
    @NamedQuery(name = "PerishableProducts.findAll", query
            = "SELECT p FROM PerishableProducts p"),
    @NamedQuery(name
            = "PerishableProducts.findByArticleNumber", query
            = "SELECT p FROM PerishableProducts p WHERE p.articleNumber = :articleNumber"),
    @NamedQuery(name
            = "PerishableProducts.findByName", query
            = "SELECT p FROM PerishableProducts p WHERE p.name = :name"),
    @NamedQuery(name
            = "PerishableProducts.findByBrand", query
            = "SELECT p FROM PerishableProducts p WHERE p.brand = :brand"),
    @NamedQuery(name
            = "PerishableProducts.findByFamily", query
            = "SELECT p FROM PerishableProducts p WHERE p.family = :family"),
    @NamedQuery(name
            = "PerishableProducts.findByNettoPrice", query
            = "SELECT p FROM PerishableProducts p WHERE p.nettoPrice = :nettoPrice"),
    @NamedQuery(name
            = "PerishableProducts.findByQuantity", query
            = "SELECT p FROM PerishableProducts p WHERE p.quantity = :quantity"),
    @NamedQuery(name
            = "PerishableProducts.findByAmountUnits", query
            = "SELECT p FROM PerishableProducts p WHERE p.amountUnits = :amountUnits"),
    @NamedQuery(name
            = "PerishableProducts.findByCriticalQuantity", query
            = "SELECT p FROM PerishableProducts p WHERE p.criticalQuantity = :criticalQuantity"),
    @NamedQuery(name
            = "PerishableProducts.findByExpirationDate", query
            = "SELECT p FROM PerishableProducts p WHERE p.expirationDate = :expirationDate"),
    @NamedQuery(name
            = "PerishableProducts.findByProductionDate", query
            = "SELECT p FROM PerishableProducts p WHERE p.productionDate = :productionDate")})
public class PerishableProducts implements Serializable, GrossPriceCalculator, ProductEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "article_number")
    private String articleNumber;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @Column(name = "family")
    private String family;
    @Basic(optional = false)
    @Column(name = "netto_price")
    private int nettoPrice;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "amount_units")
    private String amountUnits;
    @Basic(optional = false)
    @Column(name = "critical_quantity")
    private int criticalQuantity;
    @Basic(optional = false)
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Basic(optional = false)
    @Column(name = "production_date")
    @Temporal(TemporalType.DATE)
    private Date productionDate;
    @JoinColumn(name = "tax_id", referencedColumnName = "tax_key")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private StateSalesTax taxId;

    public PerishableProducts() {
    }

    public PerishableProducts(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public PerishableProducts(String articleNumber, String name, String family,
            int nettoPrice, int quantity, String amountUnits,
            int criticalQuantity, Date expirationDate,
            Date productionDate) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.family = family;
        this.nettoPrice = nettoPrice;
        this.quantity = quantity;
        this.amountUnits = amountUnits;
        this.criticalQuantity = criticalQuantity;
        this.expirationDate = expirationDate;
        this.productionDate = productionDate;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        if (Pattern.matches("[P]{2}[0-9]{8}", articleNumber)) {
            this.articleNumber = articleNumber;
        } else {
            throw new IllegalArgumentException(
                    "Article number must start PP, than 8 digit comes!");

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getNettoPrice() {
        return nettoPrice;
    }

    public void setNettoPrice(int nettoPrice) {
        if (nettoPrice >= 0) {
            this.nettoPrice = nettoPrice;
        } else {
            throw new IllegalArgumentException("Must be a positive number!");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAmountUnits() {
        return amountUnits;
    }

    public void setAmountUnits(String amountUnits) {
        this.amountUnits = amountUnits;
    }

    public int getCriticalQuantity() {
        return criticalQuantity;
    }

    public void setCriticalQuantity(int criticalQuantity) {
        this.criticalQuantity = criticalQuantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public StateSalesTax getTaxId() {
        return taxId;
    }

    public void setTaxId(StateSalesTax taxId) {
        this.taxId = taxId;
    }

    public int getDaysLeftToPerishing() {
        LocalDate exp = this.getExpirationDate().
                toInstant().
                atZone(ZoneId.systemDefault()).
                toLocalDate();
        LocalDate now = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(now, exp);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleNumber != null ? articleNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerishableProducts)) {
            return false;
        }
        PerishableProducts other = (PerishableProducts) object;
        if ((this.articleNumber == null && other.articleNumber != null) || (this.articleNumber != null && !this.articleNumber.
                equals(other.articleNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oop.persistance.PerishableProducts[ articleNumber=" + articleNumber + " ]";
    }

    @Override
    public double calculateGrossPrice() {
        return this.getNettoPrice() * this.getTaxId().
                getMultiplier();
    }

}
