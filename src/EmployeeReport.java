import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Michelle on 2015-11-18.
 */
public class EmployeeReport extends JFrame{
    private JPanel empReportPanel;
    private JList list1;
    private JButton printButton;
    public  DefaultListModel reportOutput = new DefaultListModel();

    EmployeeReport(String q) {
        super("Reports");
        setContentPane(empReportPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 800);
        setVisible(true);
        String query = q;


        Main.staffReport(query, reportOutput);
        list1.setModel(reportOutput);


        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.printOuts(empReportPanel);
            }
        });
    }


}
