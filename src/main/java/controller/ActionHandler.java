/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import model.FileOperations;
import model.HeaderTableHandler;
import model.InvoiceHeader;
import model.InvoiceLine;
import view.AddInvoiceHeader;
import view.MainForm;

/**
 *
 * @author moham
 */
public class ActionHandler implements ActionListener {
    MainForm form;
    FileOperations fileOP=new FileOperations();
    AddInvoiceHeader AddingInvoicehandler;
    HeaderTableHandler Tablehandler;
    public ActionHandler(MainForm form) {
        this.form=form;
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Load file"))
        {
            try {
                LoadFile();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand().equals("Save file"))
        {
            SaveFile();
        }
        else if(e.getActionCommand().equals("Create new invoice"))
        {
            CreateInvoice();
        }
        else if(e.getActionCommand().equals("Delete invoice"))
        {
            DeleteInvoice();
        }
        else if(e.getActionCommand().equals("save"))
        {
            Save();
        }
        else if(e.getActionCommand().equals("cancel"))
        {
            Cancel();
        }
        else if(e.getActionCommand().equals("Confirm"))
        {
            InvoiceHeaderDialogConfirm();
        }
        else if(e.getActionCommand().equals("newInvoiceCancel"))
        {
            InvoiceHeaderDialogCancel();
        }
        else 
        {
            System.out.println("action not specified");
        }
    }

    private void LoadFile() throws FileNotFoundException {
        ArrayList<InvoiceHeader> headers=new ArrayList();
        ArrayList<InvoiceLine> Lines=new ArrayList();
        JFileChooser file=new JFileChooser();
        int result = file.showOpenDialog(form);
        if(result == file.APPROVE_OPTION)
        {
            headers=fileOP.readHeaderFile(file.getSelectedFile());
        }
        result = file.showOpenDialog(form);
         if(result == file.APPROVE_OPTION)
         {
            Lines=fileOP.readLinesFile(file.getSelectedFile());
        
         }
         for(InvoiceLine line:Lines)
         {
              for(InvoiceHeader header:headers)
              {
                  if(header.getInvoiceNum()==line.getInvoiceNumber())
                  {  
                      line.setHeader(header);
                      header.setInvoiceLines(line);
                  }
              }
         }
         Tablehandler=new HeaderTableHandler(headers);
         form.getjTable1().setModel(Tablehandler);
         form.setHeaders(headers);
    }

    private void SaveFile() {
    }

    private void CreateInvoice() {
        AddingInvoicehandler=new AddInvoiceHeader(form);
        AddingInvoicehandler.setVisible(true);
    }

    private void DeleteInvoice() {
    }

    private void Save() {
    }

    private void Cancel() {
    }

    private void InvoiceHeaderDialogConfirm() {
       String customerName=AddingInvoicehandler.getCustNameField().getText();
       String invoicDate=AddingInvoicehandler.getInvDateField().getText();
       InvoiceHeader header=new InvoiceHeader();
       header.setCustomerName(customerName);
       header.setInvoiceDate(invoicDate);
       int index=0;
       for(InvoiceHeader invoice:form.getHeaders())
       {
           if(invoice.getInvoiceNum()>index)
               index=invoice.getInvoiceNum();
       }
       header.setInvoiceNum(index+1);
       form.getHeaders().add(header);
       Tablehandler.fireTableDataChanged();
       AddingInvoicehandler.setVisible(false);
       AddingInvoicehandler=null;
    }

    private void InvoiceHeaderDialogCancel() {
        
        AddingInvoicehandler.setVisible(false);
    }
    
}
