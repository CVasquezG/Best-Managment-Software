import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michelle on 2015-11-21.
 */
public class InventoryReport extends JFrame{
    private JPanel inventoryReportPanel;
    private JButton printButton;
    private JList list1;
    public  DefaultListModel reportOutput = new DefaultListModel();

    public InventoryReport(int q)
    {
        super("Reports");
        setContentPane(inventoryReportPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 800);
        setVisible(true);
        int query = q;

        Main.inventoryReport(query, reportOutput);  //make
        list1.setModel(reportOutput);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.printOuts(inventoryReportPanel);
            }
        });
    }


}


