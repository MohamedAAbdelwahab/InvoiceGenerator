package model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    void writeFile(ArrayList<InvoiceHeader>headers)
    {

    }
}
