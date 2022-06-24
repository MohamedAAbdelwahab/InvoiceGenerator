/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import model.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;
import view.MainForm;

/**
 *
 * @author moham
 */
public class ActionHandler implements ActionListener {
    MainForm form;
    FileOperations fileOP=new FileOperations();
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
    }

    private void SaveFile() {
    }

    private void CreateInvoice() {
    }

    private void DeleteInvoice() {
    }

    private void Save() {
    }

    private void Cancel() {
    }
    
}
