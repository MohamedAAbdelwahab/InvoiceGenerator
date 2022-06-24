package controller;

import model.FileOperations;
import model.InvoiceHeader;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

public class tableHandler {
    FileOperations fileOperations=new FileOperations();
    DefaultTableModel model = new DefaultTableModel();

        public DefaultTableModel getdata() throws FileNotFoundException, ParseException {
            model.addColumn("Id");
            model.addColumn("Date");
            model.addColumn("Customer Name");
            model.addColumn("total");
            ArrayList<InvoiceHeader> HeaderData = fileOperations.readFile();
            String[][] shades = new String[HeaderData.size()][3];
            for (int r = 0; r < shades.length; r++) {
                    shades[r][0] =String.valueOf(HeaderData.get(r).getInvoiceNum());
                    shades[r][1]=HeaderData.get(r).getInvoiceDate();
                    shades[r][2]=HeaderData.get(r).getCustomerName();
            }
            for(String[] sS: shades)
            {
                model.addRow(new Object[] {sS[0],sS[1],sS[2]});
            }
            return model;
        }
}
