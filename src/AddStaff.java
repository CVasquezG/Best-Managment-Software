import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by camiv on 2015-11-11.
 */
public class AddStaff extends JFrame {
    private JTextField firstNameText;
    private JTextField LastNameText;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JPanel panel1;

    public AddStaff()  {
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,200);
        pack();
        setVisible(true);


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              //  if(passwordField1.getText().equals(passwordField2.getText())){
                    String firstName = firstNameText.getText();
                    String lastName = LastNameText.getText();
                    String password = passwordField1.getText();
                    String title = comboBox1.getSelectedItem().toString();

                    Main.addNewStaff(firstName,lastName,password,title);

               // }
              //  else{
               //     JOptionPane.showMessageDialog(null,"Passwords do not match");
               // }

                firstNameText.setText("");
                LastNameText.setText("");
                passwordField1.setText("");

            }
        });
    }


}
