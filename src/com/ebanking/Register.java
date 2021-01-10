/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
/**
 *
 * @author alimi
 */
public class Register extends JFrame {
    Connection conn = configDB.configure();
    
    public Register(){
        
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        GridBagConstraints gcb = new GridBagConstraints();
        getContentPane().setBackground(new Color(41, 54, 63));
        
        // JLabel
        JLabel label1 = new JLabel("First Name");
        label1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label1.setForeground(new Color(56, 214, 193));
        JLabel label2 = new JLabel("Middle Name");
        label2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label2.setForeground(new Color(56, 214, 193));
        JLabel label3 = new JLabel("Last Name");
        label3.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label3.setForeground(new Color(56, 214, 193));
        JLabel label4 = new JLabel("Nomor Induk Kependudukan");
        label4.setForeground(new Color(56, 214, 193));
        label4.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel label5 = new JLabel("Address");
        label5.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label5.setForeground(new Color(56, 214, 193));
        JLabel label6 = new JLabel("Sub-district");
        label6.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label6.setForeground(new Color(56, 214, 193));
        JLabel label7 = new JLabel("Regency");
        label7.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label7.setForeground(new Color(56, 214, 193));
        JLabel label8 = new JLabel("Province");
        label8.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label8.setForeground(new Color(56, 214, 193));
        JLabel label9 = new JLabel("Gender");
        label9.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label9.setForeground(new Color(56, 214, 193));
        JLabel label10 = new JLabel("E-mail");
        label10.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label10.setForeground(new Color(56, 214, 193));
        JLabel label11 = new JLabel("Username");
        label11.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label11.setForeground(new Color(56, 214, 193));
        JLabel label12 = new JLabel("Password");
        label12.setForeground(new Color(56, 214, 193));
        label12.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // JtextField
        JTextField tf1 = new JTextField(20); 
        JTextField tf2 = new JTextField(20); 
        JTextField tf3 = new JTextField(20); 
        JTextField tf4 = new JTextField(20); 
        JTextField tf5 = new JTextField(20); 
        JTextField tf6 = new JTextField(20); 
        JTextField tf7 = new JTextField(20); 
        JTextField tf8 = new JTextField(20); 
        JTextField tf9 = new JTextField(20); 
        JTextField tf10 = new JTextField(20);
        tf1.setBackground(new Color(201, 201, 201));
        tf2.setBackground(new Color(201, 201, 201));
        tf3.setBackground(new Color(201, 201, 201));
        tf4.setBackground(new Color(201, 201, 201));
        tf5.setBackground(new Color(201, 201, 201));
        tf6.setBackground(new Color(201, 201, 201));
        tf7.setBackground(new Color(201, 201, 201));
        tf8.setBackground(new Color(201, 201, 201));
        tf9.setBackground(new Color(201, 201, 201));
        tf10.setBackground(new Color(201, 201, 201));
        JPasswordField tf11 = new JPasswordField(20);
        tf11.setBackground(new Color(201, 201, 201));
        
        tf1.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isAlphabetic(test))){
                    e.consume();
                }
            }
        });
        
        tf2.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isAlphabetic(test))){
                    e.consume();
                }
            }
        });
        
        tf3.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isAlphabetic(test))){
                    e.consume();
                }
            }
        });
        
        tf4.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isDigit(test))){
                    e.consume();
                }
            }
        });
        
        tf6.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isAlphabetic(test))){
                    e.consume();
                }
            }
        });
        
        tf7.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isAlphabetic(test))){
                    e.consume();
                }
            }
        });
        
        tf8.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char test = e.getKeyChar();
                if(!(Character.isAlphabetic(test))){
                    e.consume();
                }
            }
        });
        
        // JRadioButton
        JRadioButton jrb1 = new JRadioButton("Male");
        jrb1.setFont(new Font("SansSerif", Font.BOLD, 14));
        jrb1.setForeground(new Color(56, 214, 193));
        jrb1.setBackground(new Color(41, 54, 63));
        JRadioButton jrb2 = new JRadioButton("Female");
        jrb2.setFont(new Font("SansSerif", Font.BOLD, 14));
        jrb2.setForeground(new Color(56, 214, 193));
        jrb2.setBackground(new Color(41, 54, 63));

        ButtonGroup gender = new ButtonGroup();
        gender.add(jrb1);
        gender.add(jrb2);
        JPanel panelGender = new JPanel();
        panelGender.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelGender.add(jrb1);
        panelGender.add(jrb2);
        panelGender.setBackground(new Color(41, 54, 63));
        
        // Title
        JPanel panelTitle = new JPanel();
        JLabel labelTitle = new JLabel("Register Form");
        labelTitle.setForeground(new Color(56, 214, 193));
        labelTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelTitle.add(labelTitle);
        panelTitle.setBackground(new Color(41, 54, 63));
        
        // Notifications
        JPanel panelNoti = new JPanel();
        panelNoti.setBackground(new Color(41, 54, 63));
        JLabel labelNoti = new JLabel(" ");
        panelNoti.setPreferredSize(new Dimension(450, 30));
        labelNoti.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelNoti.setForeground(new Color(248, 22, 22));
        panelNoti.add(labelNoti);
        
        // JButton
        JPanel panelButton = new JPanel();
        JPanel panelButtonEx = new JPanel();
        panelButtonEx.setBackground(new Color(41, 54, 63));
        JLabel buttonLabel = new JLabel("Sign Up");
        panelButton.setBackground(new Color(56, 214, 193));
        buttonLabel.setForeground(Color.white);
        buttonLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelButton.add(buttonLabel);
        panelButtonEx.add(panelButton);
        panelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelButton.setPreferredSize(new Dimension(100, 30));
        panelButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelButton.setBackground(new Color(44, 176, 158));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                panelButton.setBackground(new Color(69, 201, 183));
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                panelButton.setBackground(new Color(95, 227, 209));
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                panelButton.setBackground(new Color(44, 176, 158));
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    String qr = "SELECT * FROM customer_account";
                    String qr2 = "SELECT * FROM customer";
                    String fn, mn, ln, nik, address, subD, reg, prov, gender, email, uName, passW;
                    
                    fn = tf1.getText();
                    mn = tf2.getText();
                    ln = tf3.getText();
                    nik = tf4.getText();
                    address = tf5.getText();
                    subD = tf6.getText();
                    reg = tf7.getText();
                    prov = tf8.getText();
                    email = tf9.getText();
                    uName = tf10.getText();
                    passW = String.valueOf(tf11.getPassword());
                    
                    ResultSet rs, rs2;
                    Statement st = conn.createStatement();
                    rs = st.executeQuery(qr);
                    if ((jrb1.isSelected() || jrb2.isSelected()) && !fn.equals("") && !ln.equals("") && !nik.equals("") && !address.equals("") && !subD.equals("") && !reg.equals("") && !prov.equals("") && !email.equals("") && !uName.equals("") && !passW.equals("")){
                        String name = fn, cariUsername, cariNama;
                        int cariID;
                        boolean cariUname = false, cariIDbool = false, cariNIKBool = false;
                        
                        tf1.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf2.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf3.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf4.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf5.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf6.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf7.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf8.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf9.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf10.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        tf11.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

                        if(jrb1.isSelected()){
                            gender = "male";
                        }else {
                            gender = "female";
                        }
                        
                        while(rs.next()){
                            cariUsername = rs.getString("username");
                            if(cariUsername.equals(uName)){
                                cariUname = true;
                                break;
                            }
                        }
                        
                        if(cariUname){
                            tf10.setText("");
                            tf10.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            labelNoti.setText("Username is already taken!");
                        }else{
                            
                            rs2 = st.executeQuery(qr2);
                            // Cari NIK
                            while(rs2.next()){
                                if(nik.equals(rs2.getString("NIK"))){
                                    cariNIKBool = true;
                                    cariID = rs2.getInt("customer_id");
                                    break;
                                }
                            }
                            
                            if(cariNIKBool){
                                System.out.println("nama ada");
                                if(mn.equals("")){
                                    String querryIn = "INSERT INTO customer_account (customer_id, username, password, balance, validation) VALUES ((SELECT customer_id FROM customer WHERE first_name = '" + fn + "' AND last_name = '" + ln + "'), '" + uName + "', '" + passW + "', " + "0, 'not valid')";
                                    System.out.println(querryIn);
                                    System.out.println("Dah ada ga ada mn");
                                    st.execute(querryIn);
                                    
                                } else {
                                    String querryIn = "INSERT INTO customer_account (customer_id, username, password, balance, validation) VALUES ((SELECT customer_id FROM customer WHERE first_name = '" + fn + "' AND middle_name = '" + mn + "' AND last_name = '" + ln + "'), '" + uName + "', '" + passW + "', " + "0, 'not valid')";
                                    System.out.println(querryIn);
                                    System.out.println("dah ada ada mn");
                                    st.execute(querryIn);

                                }
                                labelNoti.setText("Succesfully registered, now wait for your account's validation!");
                                labelNoti.setForeground(new Color(56, 214, 193));
                            }else {
                                System.out.println("nama belum ada");

                                if(mn.equals("")){
                                    String querryIn = "INSERT INTO customer (first_name, last_name, NIK, street_address, sub_district, regency, province, gender, email) VALUES ('" + fn + "', '" + ln + "', '" + nik + "', '"+ address + "', '" + subD + "', '" + reg + "', '"+ prov + "', '" + gender + "', '" + email + "')";
                                    String queryIn2 = "INSERT INTO customer_account (customer_id, username, password, balance, validation) VALUES (LAST_INSERT_ID(), '" + uName + "', '" + passW + "', 0, 'not valid')"; 
                                    st.execute(querryIn);
                                    st.execute(queryIn2);
                                    System.out.println(querryIn);
                                    System.out.println(queryIn2);
                                } else {
                                    String querryIn = "INSERT INTO customer (first_name, middle_name, last_name, NIK, street_address, sub_district, regency, province, gender, email) VALUES ('" + fn + "', '" + mn + "', '" + ln + "', '" + nik + "', '"+ address + "', '" + subD + "', '" + reg + "', '"+ prov + "', '" + gender + "', '" + email + "')";
                                    String queryIn2 = "INSERT INTO customer_account (customer_id, username, password, balance, validation) VALUES (LAST_INSERT_ID(), '" + uName + "', '" + passW + "', 0, 'not valid')"; 
                                    st.execute(querryIn);
                                    st.execute(queryIn2);
                                    System.out.println(querryIn);
                                    System.out.println(queryIn2);
                                }
                                labelNoti.setText("Succesfully registered, now wait for your account's validation!");
                                labelNoti.setForeground(new Color(56, 214, 193));
                            }
                        }
                        
                    } else {
                        if(jrb1.isSelected() || jrb2.isSelected()){
                            if(fn.equals("")){
                                tf1.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf1.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(ln.equals("")){
                                tf3.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf3.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(nik.equals("")){
                                tf4.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf4.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(address.equals("")){
                                tf5.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf5.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(subD.equals("")){
                                tf6.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf6.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(reg.equals("")){
                                tf7.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf7.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(prov.equals("")){
                                tf8.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf8.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(email.equals("")){
                                tf9.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf9.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(uName.equals("")){
                                tf10.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf10.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(passW.equals("")){
                                tf11.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf11.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            jrb1.setBorderPainted(false);
                            jrb2.setBorderPainted(false);
                            labelNoti.setText("Insert the empty field!");
                        } else {
                            if(fn.equals("")){
                                tf1.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf1.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(ln.equals("")){
                                tf3.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf3.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(nik.equals("")){
                                tf4.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf4.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(address.equals("")){
                                tf5.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf5.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(subD.equals("")){
                                tf6.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf6.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(reg.equals("")){
                                tf7.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf7.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(prov.equals("")){
                                tf8.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf8.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(email.equals("")){
                                tf9.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf9.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(uName.equals("")){
                                tf10.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf10.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            if(passW.equals("")){
                                tf11.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            }else{
                                tf11.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                            }
                            
                            jrb1.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            jrb2.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            jrb1.setBorderPainted(true);
                            jrb2.setBorderPainted(true);
                            labelNoti.setText("Insert the empty field!");
                            labelNoti.setForeground(new Color(248, 114, 125));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Gagallllll");
                }
                
            }
        });
        gc.insets = new Insets(5, 10, 5, 10);
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(panelTitle, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label1, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf1, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label2, gc);
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf2, gc);
        
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label3, gc);
        
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf3, gc);
        
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label4, gc);
        
        gc.gridx = 1;
        gc.gridy = 4;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf4, gc);
        
        gc.gridx = 0;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label5, gc);
        
        gc.gridx = 1;
        gc.gridy = 5;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf5, gc);
        
        gc.gridx = 0;
        gc.gridy = 6;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label6, gc);
        
        gc.gridx = 1;
        gc.gridy = 6;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf6, gc);
        
        gc.gridx = 0;
        gc.gridy = 7;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label7, gc);
        
        gc.gridx = 1;
        gc.gridy = 7;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf7, gc);
        
        gc.insets = new Insets(5, 10, 0, 10);
        
        gc.gridx = 0;
        gc.gridy = 8;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label8, gc);
        
        gc.gridx = 1;
        gc.gridy = 8;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf8, gc);
        
        gc.insets = new Insets(0, 10, 0, 10);
        
        gc.gridx = 0;
        gc.gridy = 9;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label9, gc);
        
        gc.gridx = 1;
        gc.gridy = 9;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(panelGender, gc);
        
        gc.gridx = 0;
        gc.gridy = 10;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label10, gc);
        
        gc.gridx = 1;
        gc.gridy = 10;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf9, gc);
        
        gc.insets = new Insets(10, 10, 5, 10);
        
        gc.gridx = 0;
        gc.gridy = 11;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label11, gc);
        
        gc.gridx = 1;
        gc.gridy = 11;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf10, gc);
        
        gc.insets = new Insets(5, 10, 5, 10);
        
        gc.gridx = 0;
        gc.gridy = 12;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(label12, gc);
        
        gc.gridx = 1;
        gc.gridy = 12;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tf11, gc);
        
        gc.gridx = 0;
        gc.gridy = 13;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(panelNoti, gc);
        
        gc.insets = new Insets(5, 10, 10, 10);
        
        gc.gridx = 0;
        gc.gridy = 14;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(panelButtonEx, gc);
        
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
    }
    
}
