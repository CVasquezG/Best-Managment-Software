import javax.swing.*;
import javax.swing.JList;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by Camilo on 2015-10-17.
 */
public class Manager extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton addButton;
    private JButton deleteButton;
    public  DefaultListModel model = new DefaultListModel();
    private JList list1;
    private JButton editButton;
    public JLabel listLabel;
    private JPanel allPanel;
    public JLabel sizeOfList;
    private JTextField priceTextField;
    private JTextField qtyTextfield;
    private JTextField descriptionTextField;
    private JComboBox categoryComboBox;
    private JButton confirmButton;
    private JLabel NameLabel;
    private JTextField nameTextField;
    private JLabel IDLabel;
    private JList StaffList;
    public  DefaultListModel modelStaff = new DefaultListModel();
    public  DefaultListModel modelHistory = new DefaultListModel();
    private JButton addButton1;
    private JButton deleteButton2;
    private JButton editButton1;
    private JTextField FirstNameTextFieldEmp;
    private JButton updateButton;
    private JTextField LastNameTextFieldEmp;
    private JLabel IDempLabel;
    private JComboBox comboBoxTitle;
    private JComboBox comboBoxStaff;
    private JList ListCustomer;
    private JButton deleteButton1;
    private JButton editButton2;
    private JTextField firtNameTextCust;
    private JTextField lastNameTextCust;
    private JTextField companyNameText;
    private JTextField phoneText;
    private JTextField addressText;
    private JPanel panelReport;
    private JButton employeeReportButton;
    private JCheckBox managersCheckBox;
    private JCheckBox salesAssociatesCheckBox;
    private JButton customerReportButton1;
    private JCheckBox phoneNumberCheckBox;
    private JCheckBox eMailAddressCheckBox;
    private JButton inventoryReportButton2;
    private JCheckBox priceCheckBox;
    private JCheckBox quantityCheckBox;
    private JComboBox monthlyComboBox;
    private JComboBox dailyComboBox;
    private JButton salesReportButton3;
    private JPanel panelHistory;
    private JComboBox comboBox1;
    private JTextField emailText;
    private JLabel custIDLabel;
    private JButton confirmButton1;
    private JTextField shipToText;
    public JList list2;
    public  DefaultListModel modelCustomer = new DefaultListModel();

    public Manager() {


        setContentPane(panel1);
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        list1.setModel(model);
        StaffList.setModel(modelStaff);
        ListCustomer.setModel(modelCustomer);

        Main.populateComboBox(comboBox1);
        /////set up combo boxes////

        /////////INVENTORY///////////////////
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new AddInventory();
                AddInventory ai = new AddInventory();
                ai.setSize(400, 200);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                model.removeElementAt(list1.getSelectedIndex());
                int id =Integer.parseInt(IDLabel.getText());
                Main.DeleteInventory(id);


            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    priceTextField.setEditable(true);
                    qtyTextfield.setEditable(true);
                    descriptionTextField.setEditable(true);
                    confirmButton.setEnabled(true);

            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                priceTextField.setEditable(false);
                qtyTextfield.setEditable(false);
                descriptionTextField.setEditable(false);
                confirmButton.setEnabled(false);

                String selectedVal = String.valueOf(list1.getSelectedValue());
                Main.addInventoryText(selectedVal, nameTextField, IDLabel, priceTextField, qtyTextfield, descriptionTextField);

            }
        });


        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String cat = categoryComboBox.getSelectedItem().toString();
                Main.updateInventory(cat,model);



            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(IDLabel.getText());
                double price = Double.parseDouble(priceTextField.getText());
                int qty = Integer.parseInt(qtyTextfield.getText());
                String desc = descriptionTextField.getText();

                Main.EditInventory(id,qty,price,desc);

                priceTextField.setEditable(false);
                qtyTextfield.setEditable(false);
                descriptionTextField.setEditable(false);
                confirmButton.setEnabled(false);

            }
        });

        /////////STAFF////////////////////////
        editButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstNameTextFieldEmp.setEditable(true);
                LastNameTextFieldEmp.setEditable(true);
                comboBoxTitle.setEnabled(true);
                updateButton.setEnabled(true);
            }
        });


        StaffList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                FirstNameTextFieldEmp.setEditable(false);
                LastNameTextFieldEmp.setEditable(false);
                comboBoxTitle.setEnabled(false);
                updateButton.setEnabled(false);

                String selectedVal = String.valueOf(StaffList.getSelectedValue());
                String searchID = selectedVal.replaceAll("[\\D]", "");
                Main.addStaffText(searchID, FirstNameTextFieldEmp, LastNameTextFieldEmp, IDempLabel, comboBoxTitle);

            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstNameTextFieldEmp.setEditable(false);
                LastNameTextFieldEmp.setEditable(false);
                comboBoxTitle.setEnabled(false);
                updateButton.setEnabled(false);

                int id = Integer.parseInt(IDempLabel.getText());
                String firstName = FirstNameTextFieldEmp.getText();
                String lastName = LastNameTextFieldEmp.getText();
                String title = comboBoxTitle.getSelectedItem().toString();

                Main.EditStaff(id,firstName,lastName,title);



            }
        });
        comboBoxStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = comboBoxStaff.getSelectedItem().toString();
                Main.updateStaff(title,modelStaff);
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStaff a = new AddStaff();
                a.setSize(400,200);
                a.setLocationRelativeTo(null);
            }
        });
        deleteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                modelStaff.removeElementAt(StaffList.getSelectedIndex());
                int id =Integer.parseInt(IDempLabel.getText());
                Main.DeleteStaff(id);


            }
        });
        employeeReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(managersCheckBox.isSelected() == true && salesAssociatesCheckBox.isSelected() == true)
                {
                    //display all employee info
                    String qry = "Select * from Employee";
                    EmployeeReport dr = new EmployeeReport(qry);
                }
                else if(managersCheckBox.isSelected() == true)
                {
                    String qry = "Select * from Employee WHERE title='Manager'";
                    EmployeeReport dr = new EmployeeReport(qry);
                }
                else if(salesAssociatesCheckBox.isSelected() == true)
                {
                    //display just sales associates info
                    String qry = "Select * from Employee WHERE title='Sales Associate'";
                    EmployeeReport dr = new EmployeeReport(qry);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select at least ONE of the checkboxes", "Error Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        customerReportButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(phoneNumberCheckBox.isSelected() == true && eMailAddressCheckBox.isSelected() == true)
                {
                    //display employee first and last name and phone and email
                    int y = 1;
                    CustomerReports cr = new CustomerReports(y);
                }
                else if(phoneNumberCheckBox.isSelected() == true)
                {
                    //display first and last and phone
                    int y = 2;
                    CustomerReports cr = new CustomerReports(y);
                }
                else if(eMailAddressCheckBox.isSelected() == true)
                {
                    //display first and last and email
                    int y = 3;
                    CustomerReports cr = new CustomerReports(y);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select at least ONE of the checkboxes", "Error Message", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        inventoryReportButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(priceCheckBox.isSelected() == true && quantityCheckBox.isSelected() == true)
                {
                    //display product code and name with price and quantity
                    int qty = 1;
                    InventoryReport ir = new InventoryReport(qty);
                }
                else if(priceCheckBox.isSelected() == true)
                {
                    //display product code and name with price
                    int qty = 2;
                    InventoryReport ir = new InventoryReport(qty);
                }
                else if(quantityCheckBox.isSelected() == true)
                {
                    //display product code and name with  quantity
                    int qty = 3;
                    InventoryReport ir = new InventoryReport(qty);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select at least ONE of the checkboxes", "Error Message", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        salesReportButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String month = (String) monthlyComboBox.getSelectedItem();
                String day = (String) dailyComboBox.getSelectedItem();

                if(day.equals("Select Day"))
                {
                    System.out.println("First");
                    String qry="Select * from Transaction WHERE MONTH(Date)='"+month+"'";
                    SalesReport sr = new SalesReport(qry);
                }
                else
                {
                    //total all the costs with the date having [selected month] and [selected day]
                    String qry="Select * from Transaction WHERE MONTH(Date)='"+month+"' AND DAY(Date)='"+day+"'";
                    SalesReport sr = new SalesReport(qry);
                }
            }
        });


        editButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firtNameTextCust.setEditable(true);
                lastNameTextCust.setEditable(true);
                companyNameText.setEditable(true);
                emailText.setEditable(true);
                phoneText.setEditable(true);
                addressText.setEditable(true);
                confirmButton1.setEnabled(true);
            }
        });



        confirmButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firtNameTextCust.setEditable(false);
                lastNameTextCust.setEditable(false);
                companyNameText.setEditable(false);
                emailText.setEditable(false);
                phoneText.setEditable(false);
                addressText.setEditable(false);
                confirmButton1.setEnabled(false);

                String id = custIDLabel.getText();
                String firstName = firtNameTextCust.getText();
                String lastName = lastNameTextCust.getText();
                String company = companyNameText.getText();
                String email = emailText.getText();
                String phone = phoneText.getText();
                String address = addressText.getText();
                String ship = shipToText.getText();
                Main.EditCust(id,firstName,lastName,company,email,phone,address,ship);

                clearAddCustomer();
            }
        });
        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelCustomer.removeElementAt(ListCustomer.getSelectedIndex());
                int id =Integer.parseInt(custIDLabel.getText());
                Main.DeleteCust(id);
                clearAddCustomer();
            }
        });

        ListCustomer.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                firtNameTextCust.setEditable(false);
                lastNameTextCust.setEditable(false);
                companyNameText.setEditable(false);
                emailText.setEditable(false);
                phoneText.setEditable(false);
                addressText.setEditable(false);
                confirmButton1.setEnabled(false);

                String selectedVal = String.valueOf(ListCustomer.getSelectedValue());
                String searchID = selectedVal.replaceAll("[\\D]", "");
                Main.addCustText(searchID,firtNameTextCust,lastNameTextCust,custIDLabel,companyNameText,emailText,phoneText,addressText,shipToText);

            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                modelHistory.clear();
                String selectedCustomer = String.valueOf(comboBox1.getSelectedItem());
                String searchID = selectedCustomer.replaceAll("[\\D]", "");
                if (e.getStateChange() == 1)
                    Main.showHistory(searchID, modelHistory);
                list2.setModel(modelHistory);
            }
        });
    }

        public void clearAddCustomer()
        {
            firtNameTextCust.setText("");
            lastNameTextCust.setText("");
            companyNameText.setText("");
            phoneText.setText("");
            emailText.setText("");
            addressText.setText("");
            shipToText.setText("");


        }



}
