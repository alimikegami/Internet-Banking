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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author alimi
 */
public class PayAmount extends JFrame {
    Connection conn = configDB.configure();
    static NumberFormat indonesianCurrency = NumberFormat.getInstance();
    static NumberFormatter kursIndo;
    JPanel contentPanel = new JPanel(new GridBagLayout());
    String username, password, accID;
    @SuppressWarnings("unchecked")
    public PayAmount(String username, String password, String accID, String amount, String item) {
        this.username = username;
        this.password = password;
        this.accID = accID;
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(2, username, password, accID, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(new Color(41, 54, 63));
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        // Content Panel
        GridBagConstraints gbc = new GridBagConstraints();
        contentPanel.setPreferredSize(new Dimension(500, 350));
        gbc.insets  = new Insets(30, 30, 30, 30);
        mainPanel.add(contentPanel, gbc);
        
        // Component
        JLabel itemLabel = new JLabel("Item");
        JLabel amountLabel = new JLabel("Amount");
        JLabel boughtItem = new JLabel(item);
        JLabel amountToBePaid = new JLabel(CustomerMenu.formatRupiah(amount));
        JPanel payPanel = new JPanel(new GridBagLayout());
        JLabel payLabel = new JLabel("Confirm");
        payPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        payPanel.setBackground(new Color(152, 55, 190));
        payLabel.setForeground(Color.white);
        payPanel.setPreferredSize(new Dimension(150, 25));
        payPanel.add(payLabel);
        payPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                payPanel.setBackground(new Color(127, 30, 165));
                payLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                payPanel.setBackground(new Color(152, 55, 190));
                payLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                payPanel.setBackground(new Color(178, 81, 216));
                payLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                payPanel.setBackground(new Color(127, 30, 165));
                payLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                buyVoucher(accID, amount, username, item);
            }
        
        });
//        JPanel returnPanel = new JPanel(new GridBagLayout());
//        JLabel returnLabel = new JLabel("Return");
//        returnLabel.setForeground(Color.white);
//        returnPanel.setPreferredSize(new Dimension(150, 25));
//        returnPanel.setBackground(new Color(152, 55, 190));
//        returnPanel.add(returnLabel);
//        returnPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        returnPanel.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                returnPanel.setBackground(new Color(127, 30, 165));
//                returnLabel.setForeground(new Color(202, 202, 202));
//            }
//            
//            @Override
//            public void mouseExited(MouseEvent e) {
//                returnPanel.setBackground(new Color(152, 55, 190));
//                returnLabel.setForeground(Color.white);
//            }
//            
//            @Override
//            public void mousePressed(MouseEvent e) {
//                returnPanel.setBackground(new Color(178, 81, 216));
//                returnLabel.setForeground(new Color(253, 253, 253));
//            }
//            
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                returnPanel.setBackground(new Color(127, 30, 165));
//                returnLabel.setForeground(new Color(202, 202, 202));
//            }
//            
//            @Override 
//            public void mouseClicked(MouseEvent e) {
//
//            }
//        });
        JLabel dummyLabel = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(itemLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.weightx = 1;
        contentPanel.add(dummyLabel, gbc);
        gbc.weightx = 0;
        gbc.gridx = 2;
        contentPanel.add(boughtItem, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(amountLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(dummyLabel, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        contentPanel.add(amountToBePaid, gbc);
        JLabel dummy = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1;
        gbc.gridwidth = 3;
        contentPanel.add(dummy, gbc);
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(payPanel, gbc);
//        contentPanel.add(returnPanel, gbc);
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        gbc.anchor = GridBagConstraints.LINE_END;
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void formatRupiah(){
        indonesianCurrency.setMaximumFractionDigits(0);
        kursIndo = new NumberFormatter(indonesianCurrency);
        kursIndo.setAllowsInvalid(false);
    }
    
    private int buyVoucher(String ID, String amount, String username, String item) {
        int flag = 0;
        try {
            Statement st = conn.createStatement();
            String query = "SELECT balance, validation FROM customer_account WHERE account_id = " + ID + ";";
            ResultSet rs;
            rs = st.executeQuery(query);
            rs.next();
            BigDecimal balance = rs.getBigDecimal("balance");
            BigDecimal substract = new BigDecimal(amount);
            String validation = rs.getString("validation");
            if (validation.equals("valid")) {
                int comp = balance.compareTo(substract);
                if (comp == 1 || comp == 0) {
                    int konfirmTrf = JOptionPane.showOptionDialog(null, "Are you sure you want to purchase this item?", "Purchase?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (konfirmTrf == JOptionPane.YES_OPTION) { 
                        balance = balance.subtract(substract);
                        String update = "UPDATE customer_account SET balance = " + balance +" WHERE account_id = " + ID;
                        st.execute(update);
                        System.out.println(update);
                        String code = voucherCodeRandomizer();
                        String history = "INSERT INTO transfer_history (transferFrom, transferTo, amount, time, voucher_code) VALUES (" + "'" + username + "', '" + item + "', " + substract + ", NOW(), '" + code + "')";
                        System.out.println(history);
                        st.execute(history);
                        reworkPanel(code, item);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Not enough balance!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Account is not valid!");
            }
        } catch (SQLException ex){
            System.out.println("Gagal bayar");
        }
        return flag;
    }
    
    private String voucherCodeRandomizer() { 
        String pilihan = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder voucherCode = new StringBuilder(10); 
        for (int i = 0; i < 10; i++) { 
            int index = (int)(pilihan.length() * Math.random()); 
            voucherCode.append(pilihan.charAt(index)); 
        } 
        return voucherCode.toString(); 
    }
    
    private void reworkPanel(String code, String item) {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        JLabel title = new JLabel("Transaction Succesfull!");
        JLabel label1 = new JLabel("Voucher:");
        JLabel dummyLabel = new JLabel(" ");
        JLabel label2 = new JLabel("Voucher number: ");
        JLabel purchasedItem = new JLabel(item);
        JLabel voucherCode = new JLabel(code);
        JPanel returnPanel = new JPanel(new GridBagLayout());
        JLabel returnLabel = new JLabel("Main Menu");
        title.setForeground(new Color(5, 163, 0));
        returnLabel.setForeground(Color.white);
        returnPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnPanel.add(returnLabel);
        returnPanel.setPreferredSize(new Dimension(150, 25));
        returnPanel.setBackground(new Color(152, 55, 190));
        returnPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                returnPanel.setBackground(new Color(127, 30, 165));
                returnLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                returnPanel.setBackground(new Color(152, 55, 190));
                returnLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                returnPanel.setBackground(new Color(178, 81, 216));
                returnLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                returnPanel.setBackground(new Color(127, 30, 165));
                returnLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                CustomerMenu obj1 = new CustomerMenu(username, password, accID);
                dispose();
            }
        
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(title, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 20, 20, 0);
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(label1, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(dummyLabel, gbc);
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(20, 0, 20, 20);
        contentPanel.add(purchasedItem, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.insets = new Insets(20, 20, 20, 0);
        gbc.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(label2, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;        
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(dummyLabel, gbc);
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.insets = new Insets(20, 0, 20, 20);
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(voucherCode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;                
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weighty = 1;
        contentPanel.add(returnPanel, gbc);
    }
}
