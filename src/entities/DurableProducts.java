package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import oop.persistance.controller.QuantityEditAble;

/**
 * @author G
 */
@Entity
@Table(name = "durable_products")
@NamedQueries({
    @NamedQuery(name = "DurableProducts.findAll", query
            = "SELECT d FROM DurableProducts d"),
    @NamedQuery(name
            = "DurableProducts.findByArticleNumber", query
            = "SELECT d FROM DurableProducts d WHERE d.articleNumber = :articleNumber"),
    @NamedQuery(name
            = "DurableProducts.findByName", query
            = "SELECT d FROM DurableProducts d WHERE d.name = :name"),
    @NamedQuery(name
            = "DurableProducts.findByBrand", query
            = "SELECT d FROM DurableProducts d WHERE d.brand = :brand"),
    @NamedQuery(name
            = "DurableProducts.findByFamily", query
            = "SELECT d FROM DurableProducts d WHERE d.family = :family"),
    @NamedQuery(name
            = "DurableProducts.findByNettoPrice", query
            = "SELECT d FROM DurableProducts d WHERE d.nettoPrice = :nettoPrice"),
    @NamedQuery(name
            = "DurableProducts.findByQuantity", query
            = "SELECT d FROM DurableProducts d WHERE d.quantity = :quantity"),
    @NamedQuery(name
            = "DurableProducts.findByAmountUnits", query
            = "SELECT d FROM DurableProducts d WHERE d.amountUnits = :amountUnits"),
    @NamedQuery(name
            = "DurableProducts.findByCriticalQuantity", query
            = "SELECT d FROM DurableProducts d WHERE d.criticalQuantity = :criticalQuantity"),
    @NamedQuery(name
            = "DurableProducts.findByWarantyPeriod", query
            = "SELECT d FROM DurableProducts d WHERE d.warantyPeriod = :warantyPeriod"),
    @NamedQuery(name
            = "DurableProducts.findByGrossWeight", query
            = "SELECT d FROM DurableProducts d WHERE d.grossWeight = :grossWeight"),
    @NamedQuery(name = "DurableProducts.searchByIdPart", query
            = "SELECT d FROM DurableProducts d WHERE d.articleNumber LIKE CONCAT('%',:wordPiece,'%')"),
    @NamedQuery(name = "DurableProducts.selectByCriticalQuantity", query
            = "SELECT new entities.SelectByCriticalQuantity(d.articleNumber,d.criticalQuantity,d.quantity) FROM DurableProducts d WHERE d.quantity<d.criticalQuantity")
})
public class DurableProducts implements Serializable, GrossPriceCalculator, ProductEntity, QuantityEditAble {

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
    @Column(name = "waranty_period")
    private int warantyPeriod;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "gross_weight")
    private BigDecimal grossWeight;
    @JoinColumn(name = "tax_id", referencedColumnName = "tax_key")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private StateSalesTax taxId;

    public DurableProducts() {
    }

    public DurableProducts(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public DurableProducts(String articleNumber, String name, String family,
            int nettoPrice, int quantity, String amountUnits,
            int criticalQuantity, int warantyPeriod, BigDecimal grossWeight) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.family = family;
        this.nettoPrice = nettoPrice;
        this.quantity = quantity;
        this.amountUnits = amountUnits;
        this.criticalQuantity = criticalQuantity;
        this.warantyPeriod = warantyPeriod;
        this.grossWeight = grossWeight;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
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
        this.nettoPrice = nettoPrice;
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

    public int getWarantyPeriod() {
        return warantyPeriod;
    }

    public void setWarantyPeriod(int warantyPeriod) {
        this.warantyPeriod = warantyPeriod;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public StateSalesTax getTaxId() {
        return taxId;
    }

    public void setTaxId(StateSalesTax taxId) {
        this.taxId = taxId;
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
        if (!(object instanceof DurableProducts)) {
            return false;
        }
        DurableProducts other = (DurableProducts) object;
        if ((this.articleNumber == null && other.articleNumber != null) || (this.articleNumber != null && !this.articleNumber.
                equals(other.articleNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oop.persistance.DurableProducts[ articleNumber=" + articleNumber + " ]";
    }

    @Override
    public double calculateGrossPrice() {
        return this.getNettoPrice() * this.getTaxId().
                getMultiplier();
    }

    @Override
    public void quantityAdd(int addAmount) {
        if (quantity > 0) {
            this.setQuantity(this.getQuantity() + quantity);
        } else {
            throw new IllegalArgumentException(
                    "Can't add minus to durable amount.");
        }

    }

    @Override
    public void quantitySubstract(int minusAmount) {
        if (this.getQuantity() - quantity >= 0) {
            this.setQuantity(this.getQuantity() - quantity);
        } else {
            throw new IllegalArgumentException(
                    "Can't add minus to durable amount.");
        }
    }

}
