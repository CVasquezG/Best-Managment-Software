/**
 * Created by Camilo on 2015-10-08.
 */
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;


public class Main {

    private static String url = "jdbc:mysql://108.179.232.64:3306/msalema_bestcomm";


    private static String user = "msalema_user1";
    private static String password = "csis3275";

    public static void main(String[] args){
        //Set the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LogIn l = new LogIn();
        l.setLocationRelativeTo(null);
        l.setSize(600,300);
    }




    //return true if password and name match in db else false
    public static String ComparePassword(String lastName,String emppassword){


        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            con = DriverManager.getConnection(url, user, password);

            st = con.createStatement();

            rs = st.executeQuery("select * from Employee where Lname ='"+lastName+"' and Password='"+emppassword+"'");

            if (rs.next()){
                return rs.getString(4);

            }else
                return "";



        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return  "";
    }
/////////////////////////////////////////////MANAGER-INVENTORY////////////////////////////////////////////////////////////////////////////

    //populate at the beginning
    public static void populateInventory(Manager s2){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Inventory");
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(3);
                String qty = rs.getString(5);
                s2.model.addElement(name);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    //once combobox is selected update with category
    public static void updateInventory(String cat,DefaultListModel model){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            if(cat == "ALL")
                rs = st.executeQuery("select * from Inventory");
            else
                rs = st.executeQuery("select * from Inventory where Pcategory ='"+cat+"'");

            model.clear();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(3);
                String qty = rs.getString(5);
                model.addElement(name);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }


    public static void addInventoryText(String searchName, JTextField nameText,JLabel idLabel, JTextField priceText, JTextField qtyText, JTextField descriptionText){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Inventory where Pname ='"+searchName+"'");

            if (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(3);
                String qty = rs.getString(5);
                String price = rs.getString(4);
                String description = rs.getString(6);

                nameText.setText(name);
                idLabel.setText(id);
                priceText.setText(price);
                qtyText.setText(qty);
                descriptionText.setText(description);


            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void addInventoryItems(String name, int qty, double price, String description,String cat){
        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Inventory(Pname,QtyInStock,Price,Description,Pcategory) VALUES('"+name+"','"+qty+"','"+price+"','"+description+"','"+cat+"')");
            JOptionPane.showMessageDialog(null, "Product has been added", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    //delete inventory from selected item from list
    public static void DeleteInventory(int id){

        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("DELETE FROM Inventory WHERE ProductID='"+id+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    public static void EditInventory(int id, int qty, double price, String description){
        // UPDATE `project`.`inventory` SET `Name`='Macbook Airs', `Quantity`='2', `Price`='899.94', `Description`='apple laptops' WHERE `InventoryID`='6';
        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("UPDATE Inventory SET  QtyInStock="+qty+", Price="+price+", Description='"+description+"'  WHERE ProductID='"+id+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    ////////////////////////////MANAGER-STAFF//////////////////////////////////////////////////////////////////////////////
//populate at the beginning
    public static void populateStaff(Manager s2){

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;


        try{
            con = DriverManager.getConnection(url, user, password);

            st = con.createStatement();

            rs = st.executeQuery("select * from Employee ORDER by Lname ASC");


            while (rs.next()){
                String name = rs.getString(2);
                String id = rs.getString(1);
                String last = rs.getString(3);
                s2.modelStaff.addElement(id + "     " + last + ", " + name);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void updateStaff(String title,DefaultListModel modelStaff){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();

            if(title == "ALL")
                rs = st.executeQuery("select * from Employee ORDER BY Lname ASC");
            else
            rs = st.executeQuery("select * from Employee where Title ='"+title+"' ORDER BY Lname ASC");

            modelStaff.clear();
            while (rs.next()){
                String name = rs.getString(2);
                String id = rs.getString(1);
                String last = rs.getString(3);
                modelStaff.addElement(id + "     " + last + ", " + name);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void addStaffText(String searchID, JTextField firstNameText,JTextField lastNameText, JLabel idLabel, JComboBox comboTitle){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try{

            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Employee where EmpID ='"+searchID +"'"); //

            if (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String lname = rs.getString(3);
                String title = rs.getString(4);


                firstNameText.setText(name);
                lastNameText.setText(lname);
                idLabel.setText(id);
                if(title.equals("Sales Associate"))
                    comboTitle.setSelectedIndex(1);
                else
                    comboTitle.setSelectedIndex(0);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void EditStaff(int id, String firstName, String lastName, String staffTitle){
        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("UPDATE Employee SET  Fname='"+firstName+"', Lname='"+lastName+"', Title='"+staffTitle+"'  WHERE EmpID='"+id+"'");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    //from addStaff form
    public static void addNewStaff(String firstName, String lastName, String staffPass, String title){
        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Employee(Fname,Lname,Title,Password) VALUES('"+firstName+"','"+lastName+"','"+title+"','"+staffPass+"')");
            JOptionPane.showMessageDialog(null, "Employee has been added", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    public static void DeleteStaff(int id){

        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("DELETE FROM Employee WHERE EmpID='" + id + "'");
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

//////////////////MANAGER CUSTOMER///////////////////////////////////////////

    public static void populateCustomer(Manager s2){

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;


        try{
            con = DriverManager.getConnection(url, user, password);

            st = con.createStatement();

            rs = st.executeQuery("select * from Customer");

System.out.println("populate customer");
            while (rs.next()){
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String id = rs.getString(1);
                s2.modelCustomer.addElement(fname +" " + lname +  " " + id);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void addCustText(String searchID, JTextField firstNameText,JTextField lastNameText, JLabel idLabel, JTextField companyText, JTextField emailText, JTextField phoneText, JTextField addressText,JTextField MailToText){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try{

            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Customer where CustID ='"+searchID +"'");

            if (rs.next()){
                String id = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String company = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                String address = rs.getString(7);
                String mailTo = rs.getString(8);

                firstNameText.setText(fname);
                lastNameText.setText(lname);
                idLabel.setText(id);
                companyText.setText(company);
                emailText.setText(email);
                phoneText.setText(phone);
                addressText.setText(address);
                MailToText.setText(mailTo);

            }



        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void EditCust(String id, String firstName, String lastName, String custCompanyName, String custEmail, String custPhone, String custAddress, String shipTo){
        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("UPDATE Customer SET  Fname='"+firstName+"', Lname='"+lastName+"', CompanyName='"+custCompanyName+"', Phone='"+custPhone+"', Email='"+custEmail+"', BillTo='"+custAddress+"' , MailTo='"+shipTo+"' WHERE CustID='"+id+"'");
            JOptionPane.showMessageDialog(null, "Customer has been Updated", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    public static void DeleteCust(int id){

        Connection con = null;
        Statement st = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("DELETE FROM Customer WHERE CustID='"+id+"'");
            JOptionPane.showMessageDialog(null, "Customer has been", "Info Message", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }































    ///////////////////////////RETAIL FORM/////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////


    public static void populateInventoryRetail(Retail r){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Inventory");
            while (rs.next()){
                String name = rs.getString(3);
                String id = rs.getString(1);
                r.modelInv.addElement(name);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void populateCustomerRetail(Retail r){   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Customer");

            while (rs.next()){
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String id = rs.getString(1);
                r.ComboBoxPrevCust.addItem(fname +" " + lname +  " " + id);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
///////////////added by michelle//////////////////////////
    public static void populateComboBox(JComboBox cb){   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            cb.removeAllItems();
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Customer ORDER BY Lname ASC");

            while (rs.next()){
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String id = rs.getString(1);
                cb.addItem(id + " - " + lname + ", " + fname);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void addNewCust(String firstName, String lastName, String custCompanyName, String custEmail, String custPhone, String custAddress, String custShipTo, JLabel idLabel){
        Connection con = null;
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
//,'"+custShipTo+"'
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Customer(Fname,Lname,CompanyName,Phone,Email,BillTo,MailTo) VALUES('"+firstName+"','"+lastName+"','"+custCompanyName+"','"+custPhone+"','"+custEmail+"','"+custAddress+"','"+custShipTo+"')");
            st2 = con.createStatement();

            rs = st.executeQuery("select * from Customer where Phone ='"+ custPhone + "'");

            if (rs.next()){
                String id = rs.getString(1);
                idLabel.setText(id);
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }
    //////////////////////////////added by michelle////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void inputNewCust(String firstName, String lastName, String custCompanyName, String custEmail, String custPhone, String custAddress, String custShipTo, JLabel idLabel){
        Connection con = null;
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Customer(Fname,Lname,CompanyName,Phone,Email,BillTo,MailTo) VALUES('" + firstName + "','" + lastName + "','" + custCompanyName + "','" + custPhone + "','" + custEmail + "','" + custAddress + "','" + custShipTo + "')");

            JOptionPane.showMessageDialog(null, "Customer has been Added", "Info Message", JOptionPane.INFORMATION_MESSAGE);
            rs = st.executeQuery("select * from Customer where Phone ='"+ custPhone + "'");
            if (rs.next()){
                String id = rs.getString(1);
                idLabel.setText(id);

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    public static int addInventoryTextRetail(String searchName, JLabel priceText,JLabel descriptionText, JLabel idInventory){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Inventory where Pname ='"+searchName+"'");

            if (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(3);
                String qty = rs.getString(5);
                String price = rs.getString(4);
                String description = rs.getString(6);

                idInventory.setText(id);
                priceText.setText(price);
                descriptionText.setText(description);
                return Integer.parseInt(qty);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }


    public static String addNewTrans(String customerID, String todayDate, Double grandTotal){
        Connection con = null;
        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Transaction(CustID,Date,Total) VALUES('"+customerID+"','"+todayDate+"',"+grandTotal+")");
            st2 = con.createStatement();
            JOptionPane.showMessageDialog(null, "Transaction Successful", "Info Message", JOptionPane.INFORMATION_MESSAGE);
            rs = st.executeQuery("select * from Transaction WHERE CustID ='"+customerID+"' AND Date ='" +todayDate+ "'");
            String lastTransID ="";
            while(rs.next()){
                lastTransID= rs.getString(1);
            }
            return lastTransID;

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return "";
    }


    public static void addNewOrder(String transactionID, String CustID, String prodID, String qtyOrdered){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.executeUpdate("INSERT INTO Orders(TransID,CustID,ProductID, QtyOrdered) VALUES('"+transactionID+"','"+CustID+"','"+prodID+"','"+qtyOrdered+"')");

            rs = st.executeQuery("select * from Inventory WHERE ProductID='" + prodID +"'");
            int oldQty = 0;
            if (rs.next()){
                oldQty = Integer.parseInt(rs.getString(5));
            }
            int newQty = oldQty - Integer.parseInt(qtyOrdered);
            st.executeUpdate("UPDATE Inventory SET  QtyInStock=" + newQty + "  WHERE ProductID='" + prodID +"'");




        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }



///////////////////////////INVOICE//////////////////////////////////////////////////



    public static void addInvoiceText(String custumerID,String transactionID,JTextField iName, JTextPane iBillTo, JTextPane iMailTo, DefaultListModel modelInv){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st2 = null;
        ResultSet rs2 = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Customer WHERE CustID='" + custumerID + "'");

            if (rs.next()){
                iName.setText(rs.getString(2) + " " + rs.getString(3));
                iBillTo.setText(rs.getString(7));
                iMailTo.setText(rs.getString(8));
            }

            rs = st.executeQuery("select * from Orders WHERE TransID='" + transactionID +"'");
            String inv;
            while (rs.next()){
                inv = rs.getString(3);
                System.out.print(inv);
                st2 = con.createStatement();
                rs2 = st2.executeQuery("select * from Inventory WHERE ProductID='" + inv +"'");

                if (rs2.next()){
                    String invName = rs2.getString(3);
                    String qty = rs.getString(4);
                    String price = rs2.getString(4);
                    modelInv.addElement(invName + "  -----  " + qty + " x " + price);
                }

            }




        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }


    /////////////////////////PACKING SLIP/////////////////////////
/*
 private JPanel panelPackingSlip;
    private JTextPane psMailTo;
    private JTextPane psBillTo;
    private JTextField psDate;
    private JTextField psTransID;
    private JList psList;
    private JButton printButton;
    public  DefaultListModel modelPack= new DefaultListModel();
 */

    public static void addPackingText(String custumerID,String transactionID,JTextPane psBillTo, JTextPane psMailTo, DefaultListModel modelPack){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Statement st2 = null;
        ResultSet rs2 = null;
        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Customer WHERE CustID='" + custumerID + "'");

            if (rs.next()){

                psBillTo.setText(rs.getString(7));
                psMailTo.setText(rs.getString(8));
            }

            rs = st.executeQuery("select * from Orders WHERE TransID='" + transactionID +"'");
            String invID;
            while (rs.next()){
                invID = rs.getString(3);
                System.out.print(invID);
                st2 = con.createStatement();
                rs2 = st2.executeQuery("select * from Inventory WHERE ProductID='" + invID +"'");

                if (rs2.next()){
                    String productID = rs2.getString(1);
                    String qty = rs.getString(4);
                    modelPack.addElement("Code: " + productID + "          x         " + qty );
                }

            }




        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }









    //////////////////////////////////////////////////////
    public static void staffReport(String query, DefaultListModel list){

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String header = "Employee ID         First & Last Name";

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery(query);

            list.addElement(header);
            while (rs.next()){

                String id = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                list.addElement(id + "                       " + fname + " " + lname);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
//////////packing slip & Invoice print outs///////////
    /////////////////////////////////////////////////////

    public static void printOuts(JPanel x)
    {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Best Communications Company");
        pj.setCopies(1);
        PageFormat format = pj.defaultPage();
        format.setOrientation(PageFormat.PORTRAIT);

        pj.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum){
                if (pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                x.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false)
            return;

        try {
            pj.print();
        } catch (PrinterException e1) {
            e1.printStackTrace();
        }
    }

    public static void showHistory(String CustID, DefaultListModel model)
    {
        String query = "Select * FROM Transaction WHERE CustID = '" + CustID + "'";

        Connection con = null;
        Statement st = null;
        Statement st2;
        Statement st3;
        ResultSet rs = null;
        ResultSet rs2;
        ResultSet rs3;

        try{
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery(query);
            String product = "";
            while (rs.next()) {
                String TransID = rs.getString(1);
                String Date = rs.getString(3);
                String Amount = rs.getString(4);
                    if(TransID != null)
                    {
                        st2 = con.createStatement();
                        rs2 = st2.executeQuery("SELECT * FROM Orders WHERE TransID = '"+TransID+"'");
                        while (rs2.next()) {
                            //String pID = rs2.getString(3) + ", ";
                            String pID = rs2.getString(3);
                            if(pID != null)
                            {
                                st3 = con.createStatement();
                                rs3 = st3.executeQuery("SELECT * FROM Inventory WHERE ProductID = '"+pID+"'");
                                while(rs3.next())
                                {
                                    String pname = rs3.getString(3) + " / ";
                                    product += pname;
                                }
                                //product += pID;
                            }
                        }
                        model.addElement("TransID: " + TransID + "  Date: " + Date + "  Total: " + Amount);
                        model.addElement("Products Purchased: " + product);
                    }

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    ///////////////////REPORTS by MICHELLE///////////////////////////////////////////////////////////////

    public static void custReport(int x, DefaultListModel model) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("select * from Customer ORDER BY Lname ASC");

            while (rs.next()) {
                //String id = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String phone = rs.getString(5);
                String email = rs.getString(6);

                if (x == 1)
                    model.addElement(lname + ", " + fname + " --- " + phone + " --- " + email);
                if (x == 2)
                    model.addElement(lname + ", " + fname + " --- " + phone);
                if (x == 3)
                    model.addElement(lname + ", " + fname + " --- " + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void inventoryReport(int x, DefaultListModel model) {
        {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            try {

                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                rs = st.executeQuery("select * from Inventory ORDER BY Pname ASC");

                while (rs.next()) {
                    //String id = rs.getString(1);
                    String code = rs.getString(1);
                    String name = rs.getString(3);
                    String price = rs.getString(4);
                    String qty = rs.getString(5);

                    if (x == 1)
                        model.addElement("ID: " + code + "  " + name + " --- " + qty + " @  $" + price);
                    if (x == 2)
                        model.addElement("ID: " + code + "  " + name + " --- $" + price);
                    if (x == 3)
                        model.addElement("ID: " + code + "  " + name + " --- " + qty);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }


    }

        public static void salesReport(String q, DefaultListModel model)
        {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            String header = "DATE                TOTAL";
            //int money=0;
            double runningTotal=0;
            try{
                con = DriverManager.getConnection(url, user, password);
                st = con.createStatement();
                rs = st.executeQuery(q);

                model.addElement(header);
                while (rs.next()){

                    String date = rs.getString(3);
                    String total = rs.getString(4);
                    double money = Double.parseDouble(rs.getString(4));
                    runningTotal += money;
                    model.addElement(date + "      " + total);
                }
                model.addElement("TOTAL:            $" + Math.round(runningTotal*100.0)/100.0);

            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (con != null) {
                        con.close();
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

}

    public static boolean checkCreditCard(String text) {
        if (text.length() == 12)
            return true;
        else
            return false;
    }


}