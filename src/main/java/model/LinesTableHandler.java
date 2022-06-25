
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import view.MainForm;

/**
 *
 * @author moham
 */
public class LinesTableHandler extends AbstractTableModel {
    ArrayList<InvoiceLine> Lines=new ArrayList();
    ArrayList<InvoiceLine> OldLines=new ArrayList();
    MainForm form;
    public LinesTableHandler(MainForm form,ArrayList<InvoiceLine> Lines) {
        this.Lines=Lines;
        this.form=form;
    }
    
    @Override
    public int getRowCount() {
        return Lines.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invoice=Lines.get(rowIndex);
        switch (columnIndex)
        {
            case 0 -> {
                return invoice.getItemName();
            }
            case 1 -> {
                return invoice.getItemPrice();
            }
            case 2 -> {
                return invoice.getCount();
            }
            case 3 -> {
                return invoice.getTotalPrice();
            }
        }
           return ""; 
    }
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
     {
        ArrayList<InvoiceLine> OldLines=new ArrayList();
            for(InvoiceLine line:Lines)
            {
               if (!line.equals(Lines.get(rowIndex)))
                OldLines.add(line);
            }
            InvoiceLine newLine=new InvoiceLine();
         if(0 == columnIndex) {
           newLine.setCount(Lines.get(rowIndex).getCount());
           newLine.setInvoiceNumber(Lines.get(rowIndex).getInvoiceNumber());
           newLine.setItemPrice(Lines.get(rowIndex).getItemPrice());
           newLine.setItemName(String.valueOf(aValue));
        }
        else if(1 == columnIndex) {
           newLine.setItemPrice(String.valueOf(aValue));
            newLine.setCount(Lines.get(rowIndex).getCount());
           newLine.setInvoiceNumber(Lines.get(rowIndex).getInvoiceNumber());
           newLine.setItemName(Lines.get(rowIndex).getItemName());
            }
        else if(2 == columnIndex) {
          newLine.setItemName(Lines.get(rowIndex).getItemName());
          newLine.setCount(Integer.valueOf((String)aValue));
           newLine.setInvoiceNumber(Lines.get(rowIndex).getInvoiceNumber());
           newLine.setItemPrice(Lines.get(rowIndex).getItemPrice());

            }
         OldLines.add(newLine);
         Lines=OldLines;
         this.fireTableDataChanged();
         InvoiceHeader temp=new InvoiceHeader();
         temp.setInvoiceNum(form.getHeaders().get(form.getjTable1().getSelectedRow()).getInvoiceNum());
         temp.setCustomerName(form.getHeaders().get(form.getjTable1().getSelectedRow()).getCustomerName());
         temp.setInvoiceDate(form.getHeaders().get(form.getjTable1().getSelectedRow()).getInvoiceDate());
         temp.setNewInvoiceLines(OldLines);
         form.setHeader(temp);
        }
    @Override
    public String getColumnName(int column) {
         switch (column) {
            case 0: return "Item Name";
            case 1: return "Item Price";
            case 2: return "Count";
            case 3: return "Total price";
        }
         return "";
    }
}
