package view;

import entities.DurableProducts;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author G
 */
public class DurableTableModel extends AbstractTableModel {

    private String[] columnNames;
    private List<DurableProducts> data;

    public DurableTableModel(List<DurableProducts> data) {
        columnNames = new String[]{"articleNumber", "name", "brand", "family",
            "nettoPrice", "taxId", "quantity", "amountUnits", "criticalQuantity",
            "warantyPeriod", "grossWeight"};
        this.data = data;

    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DurableProducts temp = data.get(rowIndex);
        String result = temp.getArticleNumber();
        switch (columnIndex) {
            case 0:
                result = String.valueOf(temp.getArticleNumber());
                break;
            case 1:
                result = String.valueOf(temp.getName());
                break;
            case 2:
                result = String.valueOf(temp.getBrand());
                break;
            case 3:
                result = String.valueOf(temp.getFamily());
                break;
            case 4:
                result = String.valueOf(temp.getNettoPrice());
                break;
            case 5:
                result = String.valueOf(temp.getTaxId().
                        getTaxKey());
                break;
            case 6:
                result = String.valueOf(temp.getQuantity());
                break;
            case 7:
                result = String.valueOf(temp.getAmountUnits());
                break;
            case 8:
                result = String.valueOf(temp.getCriticalQuantity());
                break;
            case 9:
                result = String.valueOf(temp.getWarantyPeriod());
                break;
            case 10:
                result = String.valueOf(temp.getGrossWeight());
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }

}
