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
            case 0 ->
                result = String.valueOf(temp.getArticleNumber());
            case 1 ->
                result = String.valueOf(temp.getName());
            case 2 ->
                result = String.valueOf(temp.getBrand());
            case 3 ->
                result = String.valueOf(temp.getFamily());
            case 4 ->
                result = String.valueOf(temp.getNettoPrice());
            case 5 ->
                result = String.valueOf(temp.getTaxId().
                        getTaxKey());
            case 6 ->
                result = String.valueOf(temp.getQuantity());
            case 7 ->
                result = String.valueOf(temp.getAmountUnits());
            case 8 ->
                result = String.valueOf(temp.getCriticalQuantity());
            case 9 ->
                result = String.valueOf(temp.getWarantyPeriod());
            case 10 ->
                result = String.valueOf(temp.getGrossWeight());
            default ->
                throw new AssertionError();
        }
        return result;
    }

}
