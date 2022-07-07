/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.sun.tools.javac.Main;
import model.FileOperations;
import model.HeaderTableHandler;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.LinesTableHandler;
import view.AddInvoiceHeader;
import view.AddInvoiceLine;
import view.MainForm;

/**
 *
 * @author moham
 */
public class ActionHandler implements ActionListener {
    MainForm form;
    FileOperations fileOP=new FileOperations();
    ArrayList<InvoiceHeader> headers;
    AddInvoiceHeader AddingInvoicehandler;
    AddInvoiceLine AddingInvoiceLineHandler;
    InvoiceHeader recentlyAddedHeader;
    public static HeaderTableHandler Tablehandler;
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
        else if(e.getActionCommand().equals("Create New Line"))
        {
            Save();
        }
        else if(e.getActionCommand().equals("Delete Line"))
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
        else if(e.getActionCommand().equals("AddNewLineConfirm"))
        {
            InvoiceLineDialogConfirm();
        }
        else if(e.getActionCommand().equals("AddNewLineCancel"))
        {
            InvoiceLineDialogCancel();
        }
        else 
        {
            System.out.println("action not specified");
        }
    }

    private void LoadFile() throws FileNotFoundException {
        headers=new ArrayList();
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
        JFileChooser file=new JFileChooser();
        int result = file.showOpenDialog(form);
        if(result == file.APPROVE_OPTION)
        {
                fileOP.writeFile(file,file.getSelectedFile(),headers,form);
        }
      
      }

    private void CreateInvoice() {
        AddingInvoicehandler=new AddInvoiceHeader(form);
        AddingInvoicehandler.setVisible(true);
    }

    private void DeleteInvoice() {
        int index=form.getjTable1().getSelectedRow();
        if(index==-1)
        {
            return;
        }
        form.getHeaders().remove(index);
        Tablehandler.fireTableDataChanged();
        LinesTableHandler handler=new LinesTableHandler(form,new ArrayList<InvoiceLine>());
        form.getjTable2().setModel(handler);
        form.getjLabel6().setText("");
        form.getjLabel5().setText("");
        form.getjTextField1().setText("");
        form.getjTextField2().setText("");
    }

    private void Save() {
        AddingInvoiceLineHandler=new AddInvoiceLine(form);
        AddingInvoiceLineHandler.setVisible(true);
//
//        form.getjTable1().getSelectedRow();
//        if(form.getjTable1().getSelectedRow()==-1)
//        {
//        return;
//        }
//        if (headers==null)
//        {
//            headers=MainForm.headers;
//        }
//        else {
//            headers.set(form.getjTable1().getSelectedRow(),form.getHeader());
//        }
//         LinesTableHandler handler=new LinesTableHandler(form,form.getHeader().getInvoiceLines());
//        form.getjTable2().setModel(handler);
//        Tablehandler.fireTableDataChanged();
//        form.getjLabel6().setText(String.valueOf(form.getHeader().getInvoiceNum()));
//        form.getjLabel5().setText(String.valueOf(form.getHeader().getTotalOfInvoice()));
//        form.getjTextField1().setText(form.getHeader().getInvoiceDate());
//        form.getjTextField2().setText(form.getHeader().getCustomerName());
//        form.setHeaders(headers);
    }

    private void Cancel() {
        int InvoiceLineIndex = form.getjTable2().getSelectedRow();
        int InvoiceHeaderIndex = form.getjTable1().getSelectedRow();
        if(InvoiceHeaderIndex==-1)
        {
            JOptionPane.showMessageDialog(form, "Please Select invoice header first ","Error",JOptionPane.ERROR_MESSAGE);
                return;
        }
        if(InvoiceLineIndex==-1)
        {
            JOptionPane.showMessageDialog(form, "Please Select invoice Line first ","Error",JOptionPane.ERROR_MESSAGE);
            return;

        }
        if (InvoiceLineIndex != -1) {

            if(headers==null)
            {
                form.getHeaders().get(InvoiceHeaderIndex).getInvoiceLines().remove(InvoiceLineIndex);
                Tablehandler.fireTableDataChanged();
                LinesTableHandler handler=new LinesTableHandler(form,form.getHeaders().get(InvoiceHeaderIndex).getInvoiceLines());
                form.getjTable2().setModel(handler);
                handler.fireTableDataChanged();
                form.getjLabel5().setText(String.valueOf(form.getHeaders().get(InvoiceHeaderIndex).getTotalOfInvoice()));
            }
            else
            {
                headers.get(InvoiceHeaderIndex).getInvoiceLines().remove(InvoiceLineIndex);
                Tablehandler.fireTableDataChanged();

                LinesTableHandler handler=new LinesTableHandler(form,headers.get(InvoiceHeaderIndex).getInvoiceLines());
                form.getjTable2().setModel(handler);
                handler.fireTableDataChanged();
                form.getjLabel5().setText(String.valueOf(headers.get(InvoiceHeaderIndex).getTotalOfInvoice()));
            }
        }
//         Tablehandler=new HeaderTableHandler(headers);
//         form.getjTable1().setModel(Tablehandler);
//         form.setHeaders(headers);
//        LinesTableHandler handler=new LinesTableHandler(form,new ArrayList<InvoiceLine>());
//        form.getjTable2().setModel(handler);
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
       AddingInvoiceLineHandler=new AddInvoiceLine(form);
       AddingInvoiceLineHandler.setVisible(true);
       recentlyAddedHeader=new InvoiceHeader();
       recentlyAddedHeader=header;
    }

    private void InvoiceHeaderDialogCancel() {
        
        AddingInvoicehandler.setVisible(false);
    }

    private void InvoiceLineDialogConfirm() {
        String ItemName=AddingInvoiceLineHandler.getItemNameTextField().getText();
        String ItemCount=AddingInvoiceLineHandler.getItemCountTextField().getText();
        String ItemPrice=AddingInvoiceLineHandler.getItemPriceTextField().getText();
        if (recentlyAddedHeader==null) {
            if (headers != null) {


                try{
                    int itemcount= Integer.parseInt(ItemCount);
                    InvoiceLine Line=new InvoiceLine(headers.get(form.getjTable1().getSelectedRow()).getInvoiceNum(),ItemName,ItemPrice,itemcount);
                    Line.setHeader(headers.get(form.getjTable1().getSelectedRow()));
                    InvoiceHeader header= headers.get(form.getjTable1().getSelectedRow());
                    headers.get(form.getjTable1().getSelectedRow()).setInvoiceLines(Line);
                    Tablehandler.fireTableDataChanged();
                    LinesTableHandler handler=new LinesTableHandler(form,header.getInvoiceLines());
                    form.getjTable2().setModel(handler);
                    handler.fireTableDataChanged();
                    form.getjLabel5().setText(String.valueOf(header.getTotalOfInvoice()));

                }
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(form, e.getMessage(),"Invalid Number format",JOptionPane.ERROR_MESSAGE);
                }
            }

         else
            {

                try{
                    int itemcount= Integer.parseInt(ItemCount);
                    InvoiceLine Line=new InvoiceLine(form.getHeaders().get(form.getjTable1().getSelectedRow()).getInvoiceNum(),ItemName,ItemPrice,itemcount);
                    Line.setHeader(form.getHeaders().get(form.getjTable1().getSelectedRow()));

                   InvoiceHeader header= form.getHeaders().get(form.getjTable1().getSelectedRow());
                    form.getHeaders().get(form.getjTable1().getSelectedRow()).setInvoiceLines(Line);
                    Tablehandler.fireTableDataChanged();
                    LinesTableHandler handler=new LinesTableHandler(form,header.getInvoiceLines());
                    form.getjTable2().setModel(handler);
                    handler.fireTableDataChanged();
                    form.getjLabel5().setText(String.valueOf(header.getTotalOfInvoice()));

                }
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(form, e.getMessage(),"Invalid Number format",JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        else {
            try{
                int itemcount= Integer.parseInt(ItemCount);
                InvoiceLine Line=new InvoiceLine(recentlyAddedHeader.getInvoiceNum(),ItemName,ItemPrice,itemcount);
                Line.setHeader(recentlyAddedHeader);
                recentlyAddedHeader.setInvoiceLines(Line);
                Tablehandler.fireTableDataChanged();
                LinesTableHandler handler=new LinesTableHandler(form,recentlyAddedHeader.getInvoiceLines());
                form.getjTable2().setModel(handler);
                handler.fireTableDataChanged();
                form.getjLabel5().setText(String.valueOf(recentlyAddedHeader.getTotalOfInvoice()));

            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(form, e.getMessage(),"Invalid Number format",JOptionPane.ERROR_MESSAGE);
            }
        }
        Tablehandler.fireTableDataChanged();
        AddingInvoiceLineHandler.setVisible(false);
        AddingInvoiceLineHandler=null;
    }

    private void InvoiceLineDialogCancel() {
        AddingInvoiceLineHandler.setVisible(false);
    }
    
}
