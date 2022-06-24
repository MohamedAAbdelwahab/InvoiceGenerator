package model;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperations {

    @Test
    public ArrayList<InvoiceHeader> readFile() throws FileNotFoundException {
        ArrayList<String>data=new ArrayList<>();
        ArrayList<InvoiceHeader> headers=new ArrayList<>();
        Scanner sc = new Scanner(new File("C:\\Users\\moham\\Desktop\\SalesInvoiceGeneratordData\\InvoiceHeader.csv"));
        while (sc.hasNext()) {
            data.add(sc.nextLine());
        }
        sc.close();
        for (String datum : data) {
            InvoiceHeader header=new InvoiceHeader();
            String[] splittedData = datum.split(",");
            header.setInvoiceNum(splittedData[0]);
            header.setInvoiceDate(splittedData[1]);
            header.setCustomerName(splittedData[2]);
            headers.add(header);
        }
//
        return headers;
    }

    void writeFile(ArrayList<InvoiceHeader>headers)
    {

    }
}
