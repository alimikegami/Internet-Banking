/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
/**
 *
 * @author alimi
 */
public class UpdateAccountDetails extends JFrame {
    static Connection conn = configDB.configure();
    static String accId, first_name, middle_name, last_name, nik, address, district, regency, province, gender, email, validation, id;

    public UpdateAccountDetails(String username, String password) {
        getAccountDetails(username, password);
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(4, username, password, accId, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(41, 54, 63));
        add(mainPanel, BorderLayout.CENTER);
        // White background
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc1 = new GridBagConstraints();
        gc1.insets = new Insets(30, 30, 30, 30);
        infoPanel.setPreferredSize(new Dimension(500, 700));
        mainPanel.add(infoPanel, gc1);
        
        // Update name, address, sub-district, regency, province, gender, email, username, password
        JLabel title = new JLabel("Update Account Information");
        JLabel label1 = new JLabel("First Name");
        JLabel label2 = new JLabel("Middle Name (optional)");
        JLabel label3 = new JLabel("Last Name");
        JLabel label4 = new JLabel("Address");
        JLabel label5 = new JLabel("Sub-district");
        JLabel label6 = new JLabel("Regency");
        JLabel label7 = new JLabel("Province");
        JLabel label8 = new JLabel("Gender");
        JLabel label9 = new JLabel("E-mail");
        JLabel label10 = new JLabel("Username");
        JLabel label11 = new JLabel("Password");
        
        JPanel genderPanel = new JPanel(new GridBagLayout());
        JRadioButton male = new JRadioButton("male");
        JRadioButton female = new JRadioButton("female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderPanel.add(male);
        genderPanel.add(female);
        
        if(gender.equals("male")){
            male.setSelected(true);
        }else if(gender.equals("female")){
            female.setSelected(true);
        }
        
        JPanel passwordWindow = new JPanel();
        JLabel passwordLabel = new JLabel("Change Password");
        passwordLabel.setForeground(Color.white);
        passwordWindow.setBackground(new Color(152, 55, 190));
        passwordWindow.setPreferredSize(new Dimension(130, 30));
        passwordWindow.add(passwordLabel);
        passwordWindow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        passwordWindow.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                passwordWindow.setBackground(new Color(127, 30, 165));
                passwordLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                passwordWindow.setBackground(new Color(152, 55, 190));
                passwordLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                passwordWindow.setBackground(new Color(178, 81, 216));
                passwordLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                passwordWindow.setBackground(new Color(127, 30, 165));
                passwordLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
               ChangePassword obj2 =  new ChangePassword(username, password);
               //dispose();
            }
        });
        JTextField tf1 = new JTextField(20);
        JTextField tf2 = new JTextField(20);
        JTextField tf3 = new JTextField(20);
        JTextField tf4 = new JTextField(20);
        JTextField tf5 = new JTextField(20);
        JTextField tf6 = new JTextField(20);
        JTextField tf7 = new JTextField(20);
        JTextField tf8 = new JTextField(20);
        JTextField tf9 = new JTextField(20);
        
        
        tf1.setText(first_name);
        if(middle_name.equals("NULL")){
            tf2.setText("");
        } else {
            tf2.setText(middle_name);
        }
        tf3.setText(last_name);
        tf4.setText(address);
        tf5.setText(district);
        tf6.setText(regency);
        tf7.setText(province);
        tf8.setText(email);
        tf9.setText(username);
        
        JPanel cancel = new JPanel(new GridBagLayout());
        JPanel save = new JPanel(new GridBagLayout());
        JLabel cancelLabel = new JLabel("Go Back");
        cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                cancel.setBackground(new Color(127, 30, 165));
                cancelLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                cancel.setBackground(new Color(152, 55, 190));
                cancelLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                cancel.setBackground(new Color(178, 81, 216));
                cancelLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                cancel.setBackground(new Color(127, 30, 165));
                cancelLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
               AccountDetails obj1 = new AccountDetails(username, password);
               dispose();
            }
        });
        
        JPanel statusBar = new JPanel(new GridBagLayout());
        JLabel status = new JLabel(" ");
        statusBar.add(status);
        
        JLabel saveLabel = new JLabel("Save Changes");
        save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        save.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                save.setBackground(new Color(127, 30, 165));
                saveLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                save.setBackground(new Color(152, 55, 190));
                saveLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                save.setBackground(new Color(178, 81, 216));
                saveLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                save.setBackground(new Color(127, 30, 165));
                saveLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                String newName, newAddress, newSubDistrict, newRegency, newProvince, newGender, newEmail, newUname, newFirstName, newMiddleName, newLastName;
                boolean cariUname = false, cariEmail = false;
                newFirstName = tf1.getText();
                newMiddleName = tf2.getText();
                newLastName = tf3.getText();
                newAddress = tf4.getText();
                newSubDistrict = tf5.getText();
                newRegency = tf6.getText();
                newProvince = tf7.getText();
                newEmail = tf8.getText();
                newUname = tf9.getText();
                System.out.println(middle_name);
                if(male.isSelected()){
                    newGender = "male";
                } else {
                    newGender = "female";
                }
                try {
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM customer");
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM customer_account");
                    if (newUname.equals(username) && !(newEmail.equals(email))){
                        while(rs.next()){
                            if(newEmail.equals(rs.getString("email"))){
                                if (id.equals(rs.getString("customer_id"))){
                                    cariEmail = false;
                                } else {
                                    cariEmail = true;
                                    break;
                                }

                            }
                        } 
                    } else if (newEmail.equals(email) && !newUname.equals(username)){
                        while(rs1.next()){
                            if(newUname.equals(rs1.getString("username"))){
                                cariUname = true;
                                break;
                            }
                        }   

                    } else if (!(newUname.equals(username)) && !(newEmail.equals(email))){
                        while(rs.next()){
                            if(newEmail.equals(rs.getString("email"))){
                                if (id.equals(rs.getString("customer_id"))){
                                    cariEmail = false;
                                } else {
                                    cariEmail = true;
                                    break;
                                }
                            }
                        }
                        while(rs1.next()){
                            if(newUname.equals(rs1.getString("username"))){
                                cariUname = true;
                                break;
                            }
                        }
                    }
                    
                    if(newMiddleName.equals("")){
                        newMiddleName = "NULL";
                    }
                    
                    
                    if (!(newFirstName.equalsIgnoreCase(first_name) && newMiddleName.equalsIgnoreCase(middle_name) && newLastName.equalsIgnoreCase(last_name) & newAddress.equalsIgnoreCase(address) & newSubDistrict.equalsIgnoreCase(district) & newRegency.equalsIgnoreCase(regency) & newProvince.equalsIgnoreCase(province) & newAddress.equalsIgnoreCase(address) & newGender.equalsIgnoreCase(gender))){
                        String query, query2;
                        if(newMiddleName.equals("NULL")){
                            query = "UPDATE customer SET first_name = '" + newFirstName + "', middle_name = " + newMiddleName + ", last_name = '" + newLastName + "', street_address = '" + newAddress + "', sub_district = '" + newSubDistrict + "', regency = '" + newRegency + "', province = '" + newProvince + "', gender ='" + newGender + "' WHERE customer_id = " + id;
                            query2 = "UPDATE customer_account SET validation = 'not valid' WHERE customer_id = " + id;
                        } else {
                            query = "UPDATE customer SET first_name = '" + newFirstName + "', middle_name = '" + newMiddleName + "', last_name = '" + newLastName + "', street_address = '" + newAddress + "', sub_district = '" + newSubDistrict + "', regency = '" + newRegency + "', province = '" + newProvince + "', gender ='" + newGender + "' WHERE customer_id = " + id;
                            query2 = "UPDATE customer_account SET validation = 'not valid' WHERE customer_id = " + id;

                        }
                        int konfirmEdit = JOptionPane.showOptionDialog(null, "Are you sure you want to save the changes?", "save changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (konfirmEdit == JOptionPane.YES_OPTION) {
                            st.execute(query);
                            st.execute(query2);
                        }
                    }
                    
                    if(cariUname && cariEmail){
                        status.setText("Username and E-mail is already taken! Changes will not be made");
                        tf7.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        tf8.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        
                    } else if(cariUname) {
                        status.setText("Username is already taken! Changes will not be made");
                        tf8.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));

                    } else if (cariEmail) {
                        status.setText("E-mail is already taken! Changes will not be made");
                        tf7.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        
                    } else {
                        int konfirmEdit = JOptionPane.showOptionDialog(null, "Are you sure you want to save the changes?", "save changes?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (konfirmEdit == JOptionPane.YES_OPTION) {
                            String query2 = "UPDATE customer_account SET username = '" + newUname + "' WHERE account_id = " + accId + ";";
                            String query3 = "UPDATE customer SET email = '" + newEmail + "' WHERE customer_id = " + id + ";";
                            st.execute(query2);
                            st.execute(query3);
                            status.setText("Changes succesfully made");
                            status.setForeground(new Color(21, 183, 24));
                            getAccountDetails(username, password);
                        }
                    }
                    
               } catch(SQLException ex1){
                    System.out.println("gagalllll");
               }
               
            }
        });
        cancel.setBackground(new Color(152, 55, 190));
        cancel.setPreferredSize(new Dimension(100, 30));
        save.setBackground(new Color(152, 55, 190));
        save.setPreferredSize(new Dimension(100, 30));
        cancel.add(cancelLabel);
        save.add(saveLabel);
        cancelLabel.setForeground(Color.white);
        saveLabel.setForeground(Color.white);
                
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 15, 30);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        infoPanel.add(title, gbc);
            
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf3, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf4, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label5, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf5, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label6, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(tf6, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label7, gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf7, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label8, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(genderPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label9, gbc);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf8, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label10, gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(tf9, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label11, gbc);
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.insets = new Insets(10, 15, 10, 0);
        gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(passwordWindow, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 15, 15, 30);
        gbc.anchor = GridBagConstraints.CENTER;
        infoPanel.add(statusBar, gbc);
        
        gbc.gridwidth = 1;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.insets = new Insets(10, 0, 10, 15);
        gbc.anchor = GridBagConstraints.LINE_START;
        //gbc.insets = new Insets(15, 60, 15, 30);
        infoPanel.add(cancel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.insets = new Insets(10, 15, 10, 0);

        //gbc.insets = new Insets(15, 30, 15, 60);
        gbc.anchor = GridBagConstraints.LINE_END;
        //gbc.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(save, gbc);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void getAccountDetails(String username, String password) {
        try {
            Statement st = conn.createStatement();
            String qr1 = "SELECT * FROM customer_account";
            String qr2 = "SELECT * FROM customer";
            String search = "SELECT customer_id FROM customer_account WHERE (username = '" + username + "') AND (password = '" + password + "');";
            ResultSet rs, rs2, rs3;
            rs = st.executeQuery(qr1);
            while(rs.next()){
                if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                    id = rs.getString("customer_id");
                    accId = rs.getString("account_id");
                    validation = rs.getString("validation");
                }
                
            }
            
            rs2 = st.executeQuery(qr2);
            while(rs2.next()){
                if(rs2.getString("customer_id").equals(id)){
                    first_name = rs2.getString("first_name");
                    last_name = rs2.getString("last_name");
                    if(rs2.getString("middle_name") == null){
                        System.out.println("masuk null");
                        middle_name = "NULL";
                    } else {
                        middle_name = rs2.getString("middle_name");
                    }
                    nik = rs2.getString("NIK");
                    address = rs2.getString("street_address");
                    district = rs2.getString("sub_district");
                    regency = rs2.getString("regency");
                    province = rs2.getString("province");
                    gender = rs2.getString("gender");
                    email = rs2.getString("email");
                }
            }
            System.out.println(id);
            System.out.println("SUKSESSSS");
        } catch (SQLException e) {
            System.out.println("agasdasdaskdas");
        }
    }
}
