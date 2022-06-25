package model;

import controller.ActionHandler;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import view.MainForm;

public class FileOperations {

    @Test
    public ArrayList<InvoiceHeader> readHeaderFile(File filePath) throws FileNotFoundException {
        ArrayList<String>data=new ArrayList<>();
        ArrayList<InvoiceHeader> headers=new ArrayList<>();
        Scanner sc = new Scanner(filePath);
        while (sc.hasNext()) {
            data.add(sc.nextLine());
        }
        sc.close();
        for (String datum : data) {
            InvoiceHeader header=new InvoiceHeader();
            String[] splittedData = datum.split(",");
            header.setInvoiceNum(Integer.valueOf(splittedData[0]));
            header.setInvoiceDate(splittedData[1]);
            header.setCustomerName(splittedData[2]);
            headers.add(header);
            System.out.println(header.getCustomerName());
            System.out.println(header.getInvoiceDate());
            System.out.println(header.getInvoiceNum());

        }
//
        return headers;
    }
    public ArrayList<InvoiceLine> readLinesFile(File filePath) throws FileNotFoundException {
        ArrayList<String>data=new ArrayList<>();
        ArrayList<InvoiceLine> Lines=new ArrayList<>();
        Scanner sc = new Scanner(filePath);
        while (sc.hasNext()) {
            data.add(sc.nextLine());
        }
        sc.close();
        for (String datum : data) {
            InvoiceLine Line;
            String[] splittedData = datum.split(",");
            Line=new InvoiceLine(Integer.valueOf(splittedData[0]), splittedData[1],splittedData[2],Integer.valueOf(splittedData[3]));
            Lines.add(Line);
            System.out.println(Line.getInvoiceNumber());
            System.out.println(Line.getItemName());
            System.out.println(Line.getItemPrice());
            System.out.println(Line.getCount());

        }
//
        return Lines;
    }

  public void writeFile(JFileChooser fileDilaog,File Headerfile,ArrayList<InvoiceHeader>headers,MainForm form)
    {
                File headerFile = Headerfile;
                FileWriter hfw = null;
            try {
                hfw = new FileWriter(headerFile);
            } catch (IOException ex) {
                Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                String headersName = "";
                String lines = "";
                for (InvoiceHeader invoice : headers) {
                    headersName += invoice.toString();
                    headersName += "\n";
                    for (InvoiceLine line : invoice.getInvoiceLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
                headersName = headersName.substring(0, headersName.length()-1);
                lines = lines.substring(0, lines.length()-1);
                int result = fileDilaog.showSaveDialog(form);
                File lineFile = fileDilaog.getSelectedFile();
                FileWriter lfw;
            try {
                lfw = new FileWriter(lineFile);
                hfw.write(headersName);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            } catch (IOException ex) {
                Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }
}
