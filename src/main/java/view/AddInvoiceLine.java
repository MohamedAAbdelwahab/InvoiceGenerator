
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import view.MainForm;

public class AddInvoiceLine extends JDialog{
    private JTextField itemNameTextField;
    private JTextField itemCountTextField;
    private JTextField itemPriceTextField;
    private JLabel itemNameLabel1;
    private JLabel itemCountLabel2;
    private JLabel itemPriceLabel3;
    private JButton ConfirmBtn;
    private JButton cancelBtn;
    
    public AddInvoiceLine(MainForm form) {
        itemNameTextField = new JTextField(20);
        itemNameLabel1 = new JLabel("Item Name");
        
        itemCountTextField = new JTextField(20);
        itemCountLabel2 = new JLabel("Item Count");
        
        itemPriceTextField = new JTextField(20);
        itemPriceLabel3 = new JLabel("Item Price");
        
        ConfirmBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        ConfirmBtn.setActionCommand("AddNewLineConfirm");
        cancelBtn.setActionCommand("AddNewLineCancel");
        
        ConfirmBtn.addActionListener(form.getHandler());
        cancelBtn.addActionListener(form.getHandler());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel1);
        add(itemNameTextField);
        add(itemCountLabel2);
        add(itemCountTextField);
        add(itemPriceLabel3);
        add(itemPriceTextField);
        add(ConfirmBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameTextField() {
        return itemNameTextField;
    }

    public JTextField getItemCountTextField() {
        return itemCountTextField;
    }

    public JTextField getItemPriceTextField() {
        return itemPriceTextField;
    }

    public JLabel getItemNameLabel1() {
        return itemNameLabel1;
    }

    public JLabel getItemCountLabel2() {
        return itemCountLabel2;
    }

    public JLabel getItemPriceLabel3() {
        return itemPriceLabel3;
    }

    public JButton getConfirmBtn() {
        return ConfirmBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    
}
