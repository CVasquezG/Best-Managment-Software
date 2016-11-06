import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 300211586 on 10/29/2015.
 */
public class Records extends JFrame {
    private JButton showInvoiceButton;
    private JButton showPackingSlipButton;
    private JPanel panelRecord;

   Records(String custID,String transID, String date,double subtotal, double tax, double total){
       setContentPane(panelRecord);
       setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
       setLocationRelativeTo(null);
       pack();

       setVisible(true);


       showInvoiceButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Invoice i = new Invoice(custID,transID,date,subtotal,tax,total);
               i.setLocationRelativeTo(null);
               //setVisible(false);
           }
       });
       showPackingSlipButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               PackingSlip ps = new PackingSlip(custID,transID,date);
               ps.setLocationRelativeTo(null);
           }
       });
   }

}
