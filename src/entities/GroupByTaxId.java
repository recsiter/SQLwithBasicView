package entities;

/**
 * @author G
 */
public class GroupByTaxId {

    public final int taxId;
    public final long nettoSum;
    public final double bruttoSum;
    public final double avgNetto;
    public final long quantitySum;

    public GroupByTaxId(int taxId, long nettoSum, double bruttoSum,
            double avgNetto, long quantitySum) {
        this.taxId = taxId;
        this.nettoSum = nettoSum;
        this.bruttoSum = bruttoSum;
        this.avgNetto = avgNetto;
        this.quantitySum = quantitySum;
    }

    public int getTaxId() {
        return taxId;
    }

    public long getNettoSum() {
        return nettoSum;
    }

    public double getBruttoSum() {
        return bruttoSum;
    }

    public double getAvgNetto() {
        return avgNetto;
    }

    public long getQuantitySum() {
        return quantitySum;
    }

    @Override
    public String toString() {
        return "taxId=" + taxId + ", nettoSum=" + nettoSum + ", bruttoSum=" + bruttoSum + ", avgNetto=" + avgNetto + ", quantitySum=" + quantitySum + '}';
    }

}
