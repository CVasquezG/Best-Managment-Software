import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by camiv_000 on 2015-10-23.
 */
public class LogIn extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton goButton;


    LogIn(){

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);

        Main m = new Main();

        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String title =  Main.ComparePassword(textField1.getText(),passwordField1.getText());
               System.out.println(title);
               // if(title==""){
                //    JOptionPane.showMessageDialog(null,"Wrong password or Last name");
                //}else {
                    Retail r = new Retail(title);
                    r.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    Main.populateInventoryRetail(r);
                    Main.populateCustomerRetail(r);
                    panel1.setVisible(false);

                //}

            }
        });
    }


}
