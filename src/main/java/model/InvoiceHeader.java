package model;

import java.util.ArrayList;

public class InvoiceHeader {

    int invoiceNum;
    String invoiceDate;
    String customerName;
    ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(InvoiceLine invoiceLines) {
        this.invoiceLines.add(invoiceLines);
    }
    public void setNewInvoiceLines(ArrayList<InvoiceLine> invoiceLines)
    {
        this.invoiceLines=invoiceLines;
    }
    public double getTotalOfInvoice()
    {
        double total=0.0;
        for(InvoiceLine line:invoiceLines)
        {
            total+=line.getTotalPrice();
        }
        return total;
    }
    @Override
    public String toString() {
        return invoiceNum + "," + invoiceDate + "," + customerName;
    }
}
