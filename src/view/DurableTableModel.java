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
        columnNames = new String[]{"Article Number", "Name", "Brand", "Family",
            "Netto Price", "Tax Id", "Quantity", "Amount Units", "CriticalQuantity",
            "Waranty Period", "Gross Weight"};
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        DurableProducts temp = data.get(rowIndex);
        String result = temp.getArticleNumber();
        return result;
    }

}
