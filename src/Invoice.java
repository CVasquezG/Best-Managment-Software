import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 300211586 on 10/29/2015.
 */
public class Invoice extends JFrame {
    //change names on Invoice.form and they show up here
    private JPanel panel1;
    private JTextField iTransID;
    private JTextField iFname;
    private JTextField iDate;
    private JTextPane iBillTo;
    private JTextPane iMailTo;
    private JList iList;
    private JTextField iSubTotal;
    private JTextField iTax;
    private JTextField iTotalAmount;
    private JButton printButton;
    public  DefaultListModel modelInv= new DefaultListModel();

    Invoice(String custID,String transID, String date,double subtotal, double tax, double total){
        super("Invoice");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600,800);
        //you can use pack() for the size
        setVisible(true);
        modelInv.removeAllElements();
        iList.setModel(modelInv);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main.printOuts(panel1);
            }
        });


        iTransID.setText(transID);
        iDate.setText(date);
        iSubTotal.setText(Double.toString(subtotal));
        iTax.setText(Double.toString(tax));
        iTotalAmount.setText(Double.toString(total));


        Main.addInvoiceText(custID,transID,iFname,iBillTo,iMailTo,modelInv);
        iList.setModel(modelInv);

    }


}
