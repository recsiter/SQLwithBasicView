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

    public PerishableTableModel(List<PerishableProducts> data) {
        this.data = data;
        columnNames = new String[]{"Article Number", "Name", "Brand", "Family",
            "Netto Price", "Tax Id", "Quantity", "Amount Units", "CriticalQuantity",
            "Expiration Date", "Production Date"};

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
        PerishableProducts temp = data.get(rowIndex);
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
                result = String.valueOf(temp.getExpirationDate());
                break;
            case 10:
                result = String.valueOf(temp.getProductionDate());
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }

}
