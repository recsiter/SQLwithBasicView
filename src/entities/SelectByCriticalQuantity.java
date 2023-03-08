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

    public String getId() {
        return id;
    }

    public int getCriticalQuantity() {
        return criticalQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "SelectByCriticalQuantity{" + "id=" + id + ", criticalQuantity=" + criticalQuantity + ", quantity=" + quantity + '}';
    }

}
