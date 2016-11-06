import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 300211586 on 10/29/2015.
 */
public class PackingSlip extends JFrame {
    private JPanel panelPackingSlip;
    private JTextPane psMailTo;
    private JTextPane psBillTo;
    private JTextField psDate;
    private JTextField psTransID;
    private JList psList;
    private JButton printButton;
    public  DefaultListModel modelPack= new DefaultListModel();
    PackingSlip(String custID, String transID, String date) {
        super("Packing Slip");
        setContentPane(panelPackingSlip);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600,800);
        setVisible(true);
        modelPack.removeAllElements();
        psList.setModel(modelPack);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    Main.printOuts(panelPackingSlip);

            }
        });


        psDate.setText(date);
        psTransID.setText(transID);

        Main.addPackingText(custID,transID,psBillTo,psMailTo,modelPack);
        psList.setModel(modelPack);



    }
}






