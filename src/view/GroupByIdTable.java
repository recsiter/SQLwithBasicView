package view;

import entities.GroupByTaxId;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author G
 */
public class GroupByIdTable extends AbstractTableModel {

    private final String[] columnNames;
    private final List<GroupByTaxId> data;

    public GroupByIdTable(List<GroupByTaxId> data) {
        this.data = data;
        columnNames
                = new String[]{"TaxId", "NettoPriceSum", "BruttoPriceSum", "AvgNetto",
                    "QuantitySum"};
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
        GroupByTaxId temp = data.get(rowIndex);
        String result = String.valueOf(temp.getTaxId());
        switch (columnIndex) {
            case 0 ->
                result = String.valueOf(temp.getTaxId());
            case 1 ->
                result = String.valueOf(temp.getNettoSum());
            case 2 ->
                result = String.valueOf(temp.getBruttoSum());
            case 3 ->
                result = String.valueOf(temp.getAvgNetto());
            case 4 ->
                result = String.valueOf(temp.getQuantitySum());

            default ->
                throw new AssertionError();
        }
        return result;
    }
}
