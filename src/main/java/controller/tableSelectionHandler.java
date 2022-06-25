package controller;

import model.FileOperations;
import model.InvoiceHeader;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvoiceLine;
import model.LinesTableHandler;
import view.MainForm;

public class tableSelectionHandler implements ListSelectionListener{

    private MainForm form;

    public tableSelectionHandler(MainForm form) {
        this.form = form;
    }
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        if(form.getHeaders().isEmpty())
        {
            
            return;
        }
        if(form.getjTable1().getSelectedRow()== -1)
        {
             return;
        }
       int selectedIndex=form.getjTable1().getSelectedRow();
       InvoiceHeader header=form.getHeaders().get(selectedIndex);
       ArrayList<InvoiceLine>Lines =header.getInvoiceLines();
        LinesTableHandler handler=new LinesTableHandler(form,Lines);
        form.getjTable2().setModel(handler);
        form.getjLabel6().setText(String.valueOf(header.getInvoiceNum()));
        form.getjLabel5().setText(String.valueOf(header.getTotalOfInvoice()));
        form.getjTextField1().setText(header.getInvoiceDate());
        form.getjTextField2().setText(header.getCustomerName());
        
    }
}
