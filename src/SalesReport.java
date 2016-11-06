import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michelle on 2015-11-21.
 */
public class SalesReport extends JFrame {
    private JButton printButton;
    private JList list1;
    private JPanel saleReportPanel;
    public  DefaultListModel reportOutput = new DefaultListModel();

    public SalesReport(String q)
    {
        super("Reports");
        setContentPane(saleReportPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 800);
        setVisible(true);
        String query = q;

        Main.salesReport(query, reportOutput);  //make
        list1.setModel(reportOutput);


        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.printOuts(saleReportPanel);
            }
        });
    }


}
