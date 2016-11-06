import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Camilo on 2015-10-25.
 */
public class AddInventory extends JFrame{
    private JPanel panel1;
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField pricetextField;
    private JTextField descriptiontextField;
    private JComboBox comboBox1;
    private JButton confirmButton;

    AddInventory(){
        super("New Item");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,200);
        pack();
        setVisible(true);
        Main m = new Main();

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                int qty = Integer.parseInt(quantityTextField.getText());
                double price= Double.parseDouble(pricetextField.getText());
                String description= descriptiontextField.getText();
                String category = comboBox1.getSelectedItem().toString();

                Main.addInventoryItems(name,qty,price,description,category);

                nameTextField.setText("");
                quantityTextField.setText("");
                pricetextField.setText("");
                descriptiontextField.setText("");
            }
        });
    }

}
