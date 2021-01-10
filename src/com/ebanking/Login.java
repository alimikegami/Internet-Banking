/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import java.sql.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author alimi
 */
public class Login extends JFrame {
    
    Connection conn = configDB.configure();
    
    Login(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("LOG IN");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        title.setForeground(new Color(56, 214, 193));
        titlePanel.add(title);
        titlePanel.setBackground(new Color(41, 54, 63));
        getContentPane().setBackground(new Color(41, 54, 63));
        
        // Login Panel
        JLabel uName = new JLabel("Username");
        uName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        uName.setForeground(new Color(56, 214, 193));
        JLabel password = new JLabel("Password");
        password.setFont(new Font("SansSerif", Font.PLAIN, 14));
        password.setForeground(new Color(56, 214, 193));
        JTextField unField = new JTextField(20);
        unField.setBackground(new Color(201, 201, 201));
        JPasswordField passField = new JPasswordField(20);
        passField.setBackground(new Color(201, 201, 201));
        JPanel accType = new JPanel();
        accType.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton rb1 = new JRadioButton("Customer");
        JRadioButton rb2 = new JRadioButton("Employee");
        ButtonGroup bg1 = new ButtonGroup();
        bg1.add(rb1);
        bg1.add(rb2);
        accType.add(rb1);
        accType.add(rb2);
        accType.setBackground(new Color(41, 54, 63));
        rb1.setBackground(new Color(41, 54, 63));
        rb2.setBackground(new Color(41, 54, 63));
        rb1.setForeground(new Color(56, 214, 193));
        rb2.setForeground(new Color(56, 214, 193));
        rb1.setFont(new Font("SansSerif", Font.BOLD, 14));
        rb2.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        // Notifications
        JLabel status = new JLabel(" ");
        status.setForeground(new Color(248, 114, 125));
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(41, 54, 63));
        statusPanel.add(status);
        
        JPanel loginButtonEx = new JPanel();
        JLabel log = new JLabel("Log In");
        log.setFont(new Font("SansSerif", Font.BOLD, 14));
        log.setForeground(new Color(41, 54, 63));
        JPanel button = new JPanel();
        button.setBackground(new Color(69, 201, 183));
        button.add(log);
        button.setPreferredSize(new Dimension(100, 30));
        button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                button.setBackground(new Color(44, 176, 158));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(69, 201, 183));
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                button.setBackground(new Color(95, 227, 209));
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                button.setBackground(new Color(44, 176, 158));
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    Boolean found = false;
                    ResultSet rs;
                    Statement st = conn.createStatement();
                    String accID = new String();
                    String uName = unField.getText();
                    String pass = String.valueOf(passField.getPassword());
                    
                    if (rb1.isSelected()) {
                        String q1 = "SELECT * FROM customer_account";
                        rs = st.executeQuery(q1);
                        while(rs.next()){
                            if(uName.equals(rs.getString("username")) && pass.equals(rs.getString("password"))){
                                accID = rs.getString("account_id");
                                found = true;
                                break;
                            }
                        }
                        
                        if(found){
                            CustomerMenu obj1 = new CustomerMenu(uName, pass, accID);
                            dispose();

                        }else {
                            unField.setText("");
                            passField.setText("");
                            status.setText("Username or password are incorrect!");
                            unField.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            passField.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        }
                    } else if (rb2.isSelected()){
                        String q2 = "SELECT * FROM employee_account";
                        rs = st.executeQuery(q2);
                        while(rs.next()){
                            if(uName.equals(rs.getString("employee_username")) && pass.equals(rs.getString("employee_password"))){
                                found = true;
                                break;
                            }
                        }
                        if(found){
                            EmployeeMenu obj1 = new EmployeeMenu(uName, pass);
                            dispose();
                            
                        }else {
                            unField.setText("");
                            passField.setText("");
                            status.setText("Username or password are incorrect!");
                            unField.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            passField.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        }
                    } else {
                        if(unField.getText().equals("")){
                            unField.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        }else{
                            unField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        }
                        if(String.valueOf(passField.getPassword()).equals("")){
                            passField.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        }else{
                            passField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        }
                        status.setText("Select your account type!");
                    }
                } catch(SQLException ex){
                    System.out.println("FAILLLLLLL");
                }
            }
        });
        
        loginButtonEx.add(button);
        loginButtonEx.setBackground(new Color(41, 54, 63));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        // Register links
        JLabel labelAsk = new JLabel("New here?");
        labelAsk.setForeground(new Color(56, 214, 193));
        JLabel links = new JLabel("Sign Up");
        JPanel registration  = new JPanel();
        registration.setBackground(new Color(41, 54, 63));
        registration.add(labelAsk);
        registration.add(links);
        links.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        links.setForeground(new Color(78, 123, 239));
        links.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                Register obj = new Register();
            }
        });
        
        gbc.insets = new Insets(5, 10, 5, 10);

        // Adding to JFrame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(titlePanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(uName, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(unField, gbc);
        
        gbc.insets = new Insets(5, 10, 0, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(password, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passField, gbc);
        
        gbc.insets = new Insets(0, 10, 0, 10);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(accType, gbc);
        
        gbc.insets = new Insets(0, 10, 5, 10);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(statusPanel, gbc);
        
        gbc.insets = new Insets(0, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginButtonEx, gbc);
        
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(registration, gbc);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        Login obj = new Login();
    }
}
