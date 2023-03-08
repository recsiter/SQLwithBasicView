package entities;

/**
 * @author G
 */
public class SelectByCriticalQuantity {

    private final String id;
    private final int criticalQuantity;
    private int quantity;

    public SelectByCriticalQuantity(String id, int criticalQuantity,
            int quantity) {
        this.id = id;
        this.criticalQuantity = criticalQuantity;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SelectByCriticalQuantity{" + "id=" + id + ", criticalQuantity=" + criticalQuantity + ", quantity=" + quantity + '}';
    }

}
