package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author G
 */
@Entity
@Table(name = "state_sales_tax")
@NamedQueries({
    @NamedQuery(name = "StateSalesTax.findAll", query
            = "SELECT s FROM StateSalesTax s"),
    @NamedQuery(name
            = "StateSalesTax.findByTaxKey", query
            = "SELECT s FROM StateSalesTax s WHERE s.taxKey = :taxKey"),
    @NamedQuery(name
            = "StateSalesTax.findByDescription", query
            = "SELECT s FROM StateSalesTax s WHERE s.description = :description"),
    @NamedQuery(name
            = "StateSalesTax.findByMultiplier", query
            = "SELECT s FROM StateSalesTax s WHERE s.multiplier = :multiplier")})
public class StateSalesTax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tax_key")
    private Integer taxKey;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "multiplier")
    private double multiplier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxId", fetch
            = FetchType.EAGER)
    private Collection<PerishableProducts> perishableProductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxId", fetch
            = FetchType.EAGER)
    private Collection<DurableProducts> durableProductsCollection;

    public StateSalesTax() {
    }

    public StateSalesTax(Integer taxKey) {
        this.taxKey = taxKey;
    }

    public StateSalesTax(Integer taxKey, String description, double multiplier) {
        this.taxKey = taxKey;
        this.description = description;
        this.multiplier = multiplier;
    }

    public Integer getTaxKey() {
        return taxKey;
    }

    public void setTaxKey(Integer taxKey) {
        if (taxKey >= 0 && taxKey <= 100) {
            this.taxKey = taxKey;
        } else {
            throw new IllegalArgumentException("Tax key must between 0 and 100!");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public Collection<PerishableProducts> getPerishableProductsCollection() {
        return perishableProductsCollection;
    }

    public void setPerishableProductsCollection(
            Collection<PerishableProducts> perishableProductsCollection) {
        this.perishableProductsCollection = perishableProductsCollection;
    }

    public Collection<DurableProducts> getDurableProductsCollection() {
        return durableProductsCollection;
    }

    public void setDurableProductsCollection(
            Collection<DurableProducts> durableProductsCollection) {
        this.durableProductsCollection = durableProductsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxKey != null ? taxKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StateSalesTax)) {
            return false;
        }
        StateSalesTax other = (StateSalesTax) object;
        if ((this.taxKey == null && other.taxKey != null) || (this.taxKey != null && !this.taxKey.
                equals(other.taxKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oop.persistance.StateSalesTax[ taxKey=" + taxKey + " ]";
    }

}
