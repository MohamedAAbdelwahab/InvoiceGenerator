package model;

public class InvoiceLine {
    int invoiceNumber;
    String itemName;
    String itemPrice;
    int count;    
    InvoiceHeader header;
    public InvoiceLine()
    {
    }
    public InvoiceLine(int invoiceNumber,String itemName, String itemPrice, int count) {
        this.invoiceNumber=invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }
    public double getTotalPrice()
    {
        return Integer.valueOf(this.itemPrice) * this.count;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
      @Override
    public String toString() {
        return header.getInvoiceNum()+ "," + itemName + "," + itemPrice + "," + count;
    }
}
