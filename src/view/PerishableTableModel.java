package view;

import entities.PerishableProducts;
import java.util.List;
import javax.sound.sampled.DataLine;
import javax.swing.table.AbstractTableModel;

/**
 * @author G
 */
public class PerishableTableModel extends AbstractTableModel {

    private String[] columnNames;
    private List<PerishableProducts> data;

    public PerishableTableModel(List<PerishableProducts> DataLine) {
        this.data = DataLine;
        columnNames = new String[]{"Article Number", "Name", "Brand", "Family",
            "Netto Price", "Tax Id", "Quantity", "Amount Units", "CriticalQuantity",
            "Expiration Date", "Production Date"};
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
        PerishableProducts temp = data.get(rowIndex);
        String result = temp.getArticleNumber();
        return result;
    }

}
