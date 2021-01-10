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
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author alimi
 */
public class AccountDetails extends JFrame {
    static Connection conn = configDB.configure();

    static String accID, first_name, middle_name, last_name, nik, address, district, regency, province, gender, email, validation, id;
    public AccountDetails(String username, String password){
        getAccountDetails(username, password);
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(4, username, password, accID, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(41, 54, 63));
        add(mainPanel, BorderLayout.CENTER);
        // White background
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gc1 = new GridBagConstraints();
        gc1.insets = new Insets(30, 30, 30, 30);
        infoPanel.setPreferredSize(new Dimension(500, 700));
        mainPanel.add(infoPanel, gc1);
        JLabel label1 = new JLabel("First name");
        JLabel label2 = new JLabel("NIK");
        JLabel label3 = new JLabel("Street Address");
        JLabel label4 = new JLabel("Sub-district");
        JLabel label5 = new JLabel("Regency");
        JLabel label6 = new JLabel("Province");
        JLabel label7 = new JLabel("Gender");
        JLabel label8 = new JLabel("E-mail");
        JLabel label17 = new JLabel("Validation");
        JLabel label19 = new JLabel("Username");
        JLabel label9 = new JLabel(first_name);
        JLabel label21 = new JLabel("Middle Name");
        JLabel label22 = new JLabel(middle_name);
        JLabel label23 = new JLabel("Last Name");
        JLabel label24 = new JLabel(last_name);
        JLabel label10 = new JLabel(nik);
        JLabel label11 = new JLabel(address);
        JLabel label12 = new JLabel(district);
        JLabel label13 = new JLabel(regency);
        JLabel label14 = new JLabel(province);
        JLabel label15 = new JLabel(gender);
        JLabel label16 = new JLabel(email);
        JLabel label18 = new JLabel(validation);
        JLabel label20 = new JLabel(username);
        JPanel buttonBack = new JPanel(new GridBagLayout());
        JLabel back = new JLabel("Go Back");
        back.setForeground(Color.white);
        buttonBack.add(back);
        buttonBack.setBackground(new Color(152, 55, 190));
        buttonBack.setPreferredSize(new Dimension(100, 30));
        buttonBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonBack.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                buttonBack.setBackground(new Color(127, 30, 165));
                back.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                buttonBack.setBackground(new Color(152, 55, 190));
                back.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                buttonBack.setBackground(new Color(178, 81, 216));
                back.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                buttonBack.setBackground(new Color(127, 30, 165));
                back.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerMenu obj = new CustomerMenu(username, password, accID);
                dispose();
            }
        });
        
        JPanel buttonUpdate = new JPanel(new GridBagLayout());
        JLabel update = new JLabel("Update");
        update.setForeground(Color.white);
        buttonUpdate.add(update);
        buttonUpdate.setBackground(new Color(152, 55, 190));
        buttonUpdate.setPreferredSize(new Dimension(100, 30));
        buttonUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonUpdate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                buttonUpdate.setBackground(new Color(127, 30, 165));
                update.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                buttonUpdate.setBackground(new Color(152, 55, 190));
                update.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                buttonUpdate.setBackground(new Color(178, 81, 216));
                update.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                buttonUpdate.setBackground(new Color(127, 30, 165));
                update.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                UpdateAccountDetails obj2 = new UpdateAccountDetails(username, password);
                dispose();
            }
        });
        GridBagConstraints gc2 = new GridBagConstraints();
        GridBagConstraints gc3 = new GridBagConstraints();
        
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 2;
        gc2.insets = new Insets(20, 10, 10, 10);
        ImageIcon image1 = new ImageIcon(getClass().getResource("/images/account.png"));
        JLabel labelImage1 = new JLabel(image1);
        JLabel detail = new JLabel("Account Details");
        infoPanel.add(labelImage1, gc2);
        
        gc2.insets = new Insets(10, 10, 20, 10);
        gc2.gridx = 0;
        gc2.gridy = 1;
        infoPanel.add(detail, gc2);

        
        gc2.anchor = GridBagConstraints.LINE_START;
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 2;
        gc2.gridwidth = 1;
        infoPanel.add(label1, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label9, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 3;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label21, gc2);
        gc2.gridx = 1;
        gc2.gridy = 3;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label22, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 4;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label23, gc2);
        gc2.gridx = 1;
        gc2.gridy = 4;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label24, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 5;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label2, gc2);
        gc2.gridx = 1;
        gc2.gridy = 5;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label10, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 6;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label3, gc2);
        gc2.gridx = 1;
        gc2.gridy = 6;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label11, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 7;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label4, gc2);
        gc2.gridx = 1;
        gc2.gridy = 7;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label12, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 8;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label5, gc2);
        gc2.gridx = 1;
        gc2.gridy = 8;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label13, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 9;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label6, gc2);
        gc2.gridx = 1;
        gc2.gridy = 9;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label14, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 10;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label7, gc2);
        gc2.gridx = 1;
        gc2.gridy = 10;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label15, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 11;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label8, gc2);
        gc2.gridx = 1;
        gc2.gridy = 11;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label16, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 12;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label17, gc2);
        gc2.gridx = 1;
        gc2.gridy = 12;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label18, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.gridx = 0;
        gc2.gridy = 13;
        gc2.gridwidth = 1;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(label19, gc2);
        gc2.gridx = 1;
        gc2.gridy = 13;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;
        infoPanel.add(label20, gc2);
        
        gc2.insets = new Insets(10, 0, 10, 15);
        gc2.weighty = 1;
        gc2.gridx = 0;
        gc2.gridy = 14;
        gc2.anchor = GridBagConstraints.LINE_START;
        infoPanel.add(buttonBack, gc2);
        gc2.gridx = 1;
        gc2.gridy = 14;
        gc2.insets = new Insets(10, 15, 10, 0);
        gc2.anchor = GridBagConstraints.LINE_END;

        infoPanel.add(buttonUpdate, gc2);

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
                    accID = rs.getString("account_id");                    
                    validation = rs.getString("validation");
                }
                
            }
            
            rs2 = st.executeQuery(qr2);
            while(rs2.next()){
                if(rs2.getString("customer_id").equals(id)){
                    first_name = rs2.getString("first_name");
                    last_name = rs2.getString("last_name");
                    if(rs2.getString("middle_name") == null){
                        middle_name = null;
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
