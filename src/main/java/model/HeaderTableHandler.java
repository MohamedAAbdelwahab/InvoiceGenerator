
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author moham
 */
public class HeaderTableHandler extends AbstractTableModel {
    ArrayList<InvoiceHeader> headers=new ArrayList();

    public HeaderTableHandler(ArrayList<InvoiceHeader> headers) {
        this.headers=headers;
    }
    
    @Override
    public int getRowCount() {
        return headers.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice=headers.get(rowIndex);
        switch (columnIndex)
        {
            case 0:return invoice.getInvoiceNum();
            case 1:return invoice.getInvoiceDate();
            case 2:return invoice.getCustomerName();
            case 3:return invoice.getTotalOfInvoice();
        }
           return ""; 
    }
    
    @Override
    public String getColumnName(int column) {
         switch (column) {
            case 0: return "Invoice Number";
            case 1: return "Invoice Date";
            case 2: return "Customer Name";
            case 3: return "Total price";
        }
         return "";
    }
    
}
