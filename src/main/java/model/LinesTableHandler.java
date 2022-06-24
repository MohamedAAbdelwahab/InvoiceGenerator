/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author moham
 */
public class LinesTableHandler extends AbstractTableModel {
    ArrayList<InvoiceLine> Lines=new ArrayList();

    public LinesTableHandler(ArrayList<InvoiceLine> Lines) {
        this.Lines=Lines;
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
            case 0:return invoice.getItemName();
            case 1:return invoice.getItemPrice();
            case 2:return invoice.getCount();
            case 3:return invoice.getTotalPrice();
        }
           return ""; 
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
