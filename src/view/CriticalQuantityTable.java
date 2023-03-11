package view;

import entities.SelectByCriticalQuantity;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author G
 */
public class CriticalQuantityTable extends AbstractTableModel {

    private String[] columnNames;
    private List<SelectByCriticalQuantity> data;

    public CriticalQuantityTable(List<SelectByCriticalQuantity> data) {
        this.data = data;
        columnNames
                = new String[]{"Arifical Number", "Critical quantity", "Quantity"};

    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        SelectByCriticalQuantity temp = data.get(rowIndex);
        String result = temp.getId();
        switch (columnIndex) {
            case 0 ->
                result = temp.getId();
            case 1 ->
                result = String.valueOf(temp.getCriticalQuantity());
            case 2 ->
                result = String.valueOf(temp.getQuantity());
            default ->
                throw new AssertionError();
        }
        return result;
    }

}
