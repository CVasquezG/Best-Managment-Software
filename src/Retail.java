import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.StringContent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Camilo on 2015-10-25.
 */
public class Retail extends JFrame{
    private JTabbedPane tabbedPane1;
    private JButton clearButton;
    private JPanel panel1;
    public JButton buyButton;
    private JPanel retailPanel;
    private JButton ManagerToolsButton;
    private JList ListInventory;
    private JList ListCart;
    private JComboBox comboBox1;
    private JComboBox catComboBox;
    private JLabel priceLabel;
    private JLabel descLabel;
    private JButton buttonAddToCart;
    private JLabel totalLabel;
    private JButton buttonRemoveAll;
    private JButton buttonRemove;
    public JComboBox ComboBoxPrevCust;
    private JTextField FirstNameText;
    private JTextField LastNameText;
    private JTextField CompanyText;
    private JTextField PhoneText;
    private JTextField BillToText;
    private JTextField CreditCardText;
    private JTextField ShipToText;
    private JTextField EmailText;
    private JLabel TaxLabel;
    private JLabel GrandTotalLabel;
    private JButton updateClientButton;
    private JLabel idLabel;
    private JLabel idInventoryLabel;
    private JButton addNewButton;
    private JPanel retailPanel5;
    private JButton logOutButton;
    private JScrollBar scrollBar1;

    public  DefaultListModel modelInv= new DefaultListModel();
    public  DefaultListModel modelCart= new DefaultListModel();
    static double total = 0.0;



    Retail(String title){

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950,490);
        setVisible(true);

        buyButton.setEnabled(false);
        ListInventory.setModel(modelInv);
        ListCart.setModel(modelCart);


        if(title.compareTo("Manager")==0){
            ManagerToolsButton.setEnabled(true);
        }else{
            ManagerToolsButton.setEnabled(false);
        }

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(Main.checkCreditCard(CreditCardText.getText())) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String customerID = idLabel.getText();
                String today = dateFormat.format(date);
                Double total = Double.parseDouble(GrandTotalLabel.getText());

                String transID = Main.addNewTrans(customerID, today, total);
               // System.out.println(transID);//---------------------------------------------------------------------------------------------------------***************************

                int lengthList = ListCart.getLastVisibleIndex();

                String items;
                for (int i = 0; i <= lengthList; i++) {
                    items = modelCart.getElementAt(i).toString();
                    String[] arr = items.split(", ");
                    String id = arr[0];
                    String qty = arr[2];
                    //System.out.println(id + " " + qty);
                    Main.addNewOrder(transID, customerID, id, qty);
                }

                Records r = new Records(idLabel.getText(), transID, today, Double.parseDouble(totalLabel.getText()), Double.parseDouble(TaxLabel.getText()), Double.parseDouble(GrandTotalLabel.getText()));
                r.setLocationRelativeTo(null);


                idLabel.setText("");
                FirstNameText.setText("");
                LastNameText.setText("");
                PhoneText.setText("");
                EmailText.setText("");
                BillToText.setText("");
                ShipToText.setText("same as billing address");
                CompanyText.setText("");
                CreditCardText.setText("");
                TaxLabel.setText("0.0");
                GrandTotalLabel.setText("0.0");
                ListCart.removeAll();
                modelCart.removeAllElements();
                ListCart.setModel(modelCart);
                total = 0.0;
                totalLabel.setText(String.valueOf(total));
                buyButton.setEnabled(false);
            }
                else
                JOptionPane.showMessageDialog(null, "Please enter 12-digit Card Number", "Info Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ManagerToolsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager m = new Manager();
                Main.populateInventory(m);
                Main.populateStaff(m);
                Main.populateCustomer(m);

            }
        });
        catComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cat = catComboBox.getSelectedItem().toString();
                Main.updateInventory(cat,modelInv);
            }
        });

        ListInventory.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                comboBox1.removeAllItems();
                String selectedVal = String.valueOf(ListInventory.getSelectedValue());
                int qty =  Main.addInventoryTextRetail(selectedVal,priceLabel, descLabel,idInventoryLabel );


                for(int i = 1; i<=qty;i++){
                    comboBox1.addItem(i);
                }


            }
        });


        buttonAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVal = String.valueOf(ListInventory.getSelectedValue());
                int qtySelected = Integer.parseInt(comboBox1.getSelectedItem().toString());

                //total += (qtySelected * Integer.parseInt(priceLabel.getText().toString()));
                double price = Double.parseDouble(priceLabel.getText());
                double product = price * qtySelected;
                modelCart.addElement(idInventoryLabel.getText()+", "+selectedVal + ", " + qtySelected + ", " + Math.round(product*100.0) / 100.0);
                total += price * qtySelected;
                totalLabel.setText(String.valueOf(Math.round(total * 100.0)/100.0));


                double tax = total * 0.12;
                double taxRounded = Math.round(tax * 100.0)/100.0;
                TaxLabel.setText(String.valueOf(taxRounded));

                double grandTotal = total * 1.12;
                double roundedGrandTotal = Math.round(grandTotal * 100.0)/100.0;
                GrandTotalLabel.setText(String.valueOf(roundedGrandTotal));



            }
        });
        buttonRemoveAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelCart.removeAllElements();
                total = 0.0;
                totalLabel.setText(String.valueOf(total));
                TaxLabel.setText("0.0");
                GrandTotalLabel.setText("0.0");
            }
        });
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text =String.valueOf(ListCart.getSelectedValue());
                String[] arr= text.split(", ");
                double price = Double.parseDouble(arr[3]);

                total -= price;
                totalLabel.setText(String.valueOf(Math.round(total * 100.0)/100.0));

                double tax = total * 0.12;
                double taxRounded = Math.round(tax * 100.0)/100.0;
                TaxLabel.setText(String.valueOf(taxRounded));

                double grandTotal = total * 1.12;
                double roundedGrandTotal = Math.round(grandTotal * 100.0)/100.0;
                GrandTotalLabel.setText(String.valueOf(roundedGrandTotal));

                modelCart.removeElementAt(ListCart.getSelectedIndex());




            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstNameText.setText("");
                LastNameText.setText("");
                CompanyText.setText("");
                PhoneText.setText("");
                BillToText.setText("");
                ShipToText.setText("same as billing address");
                CreditCardText.setText("");
                EmailText.setText("");
                idLabel.setText("");
                buyButton.setEnabled(false);
            }
        });




        ComboBoxPrevCust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVal =  ComboBoxPrevCust.getSelectedItem().toString();
                String searchID = selectedVal.replaceAll("[\\D]", "");

               Main.addCustText(searchID,FirstNameText,LastNameText,idLabel,CompanyText,EmailText,PhoneText,BillToText,ShipToText);
               buyButton.setEnabled(true);

            }
        });
        updateClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //add if label is "" and if its not then show that the client already exists
                String firstName = FirstNameText.getText();
                String lastName = LastNameText.getText();
                String phone = PhoneText.getText();
                String email = EmailText.getText();
                String company = CompanyText.getText();
                String billTo = BillToText.getText();
                String shipTo = ShipToText.getText();

               // Main.addNewCust(firstName,lastName,company,email,phone,billTo,shipTo, idLabel);
               // EditCust(String id, String firstName, String lastName, String custCompanyName, String custEmail, String custPhone, String custAddress, String shipTo);
                Main.EditCust(idLabel.getText(),firstName,lastName,company,email,phone,billTo,shipTo);
                Main.populateComboBox(ComboBoxPrevCust);
            }
        });
        ComboBoxPrevCust.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                /*
                ComboBoxPrevCust.setSelectedIndex(-1);
                if(ComboBoxPrevCust.getSelectedIndex() != -1) {
                    String selectedVal = ComboBoxPrevCust.getSelectedItem().toString();
                    String searchID = selectedVal.replaceAll("[\\D]", "");
                    Main.addCustText(searchID, FirstNameText, LastNameText, idLabel, CompanyText, EmailText, PhoneText, BillToText, ShipToText);

                }
               */

            }
        });
        addNewButton.addActionListener(new ActionListener() {
            @Override

                public void actionPerformed(ActionEvent e) {
                    //add if label is "" and if its not then show that the client already exists
                    String firstName = FirstNameText.getText();
                    String lastName = LastNameText.getText();
                    String phone = PhoneText.getText();
                    String email = EmailText.getText();
                    String company = CompanyText.getText();
                    String billTo = BillToText.getText();
                    String shipTo = ShipToText.getText();

                    Main.inputNewCust(firstName, lastName, company, email, phone, billTo, shipTo, idLabel);
                    Main.populateComboBox(ComboBoxPrevCust);
                    buyButton.setEnabled(true);
            }
        });


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                java.awt.Window win[] = java.awt.Window.getWindows();
                for(int i=0;i<win.length;i++)
                {
                    win[i].dispose();
                }
                LogIn nlogin = new LogIn();
                nlogin.setLocationRelativeTo(null);
                nlogin.setSize(600, 300);
            }
        });
    }






}
