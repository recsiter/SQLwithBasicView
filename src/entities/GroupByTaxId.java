package entities;

/**
 * @author G
 */
public class GroupByTaxId {

    public final long nettoSum;
    public final double bruttoSum;
    public final double avgNetto;
    public final long quantitySum;

    public GroupByTaxId(long nettoSum, double bruttoSum, double avgNetto,
            long quantitySum) {
        this.nettoSum = nettoSum;
        this.bruttoSum = bruttoSum;
        this.avgNetto = avgNetto;
        this.quantitySum = quantitySum;
    }

    @Override
    public String toString() {
        return "nettoSum=" + nettoSum + ", bruttoSum=" + bruttoSum + ", avgNetto=" + avgNetto + ", quantitySum=" + quantitySum + '}';
    }

}
