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
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author alimi
 */

public class Transfer extends JFrame {
    Connection conn = configDB.configure();
    static NumberFormat indonesianCurrency = NumberFormat.getInstance();
    static NumberFormatter kursIndo;
    
    @SuppressWarnings("unchecked")
    public Transfer(String username, String password, String accID) {
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(1, username, password, accID, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(new Color(41, 54, 63));
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        // Content Panel
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(500, 400));
        gbc.insets  = new Insets(30, 30, 30, 30);
        mainPanel.add(contentPanel, gbc);
        
        // Component
        JLabel title = new JLabel("Transfer");
        JLabel amount = new JLabel("Amount (Rp)");        
        JLabel accName = new JLabel("Account username");
        JPanel status = new JPanel(new GridBagLayout());
        JLabel statusLabel = new JLabel(" ");
        JPanel confirm = new JPanel(new GridBagLayout());
        JLabel confirmLabel = new JLabel("Confirm");
        confirm.setPreferredSize(new Dimension(100, 30));
        confirmLabel.setForeground(Color.white);
        confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirm.setBackground(new Color(152, 55, 190));
        confirm.add(confirmLabel);
        JTextField tf2 = new JTextField(20);
        formatRupiah();
        JFormattedTextField tf3 = new JFormattedTextField(kursIndo);
        tf3.setColumns(20);
        
        tf3.addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e) {
                
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    if(tf3.getText().length()==1){
                        tf3.setText("0");
                    }
                }
            }
        });
        
        GridBagConstraints gbc2 = new GridBagConstraints();

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(20, 10, 20, 10);
        contentPanel.add(title, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.anchor = GridBagConstraints.LINE_START;
        gbc2.insets = new Insets(10, 0, 10, 50);
        contentPanel.add(accName, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.anchor = GridBagConstraints.LINE_END;
        gbc2.insets = new Insets(10, 50, 10, 0);
        contentPanel.add(tf2, gbc2);
        
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.anchor = GridBagConstraints.LINE_START;
        gbc2.insets = new Insets(10, 0, 10, 50);
        contentPanel.add(amount, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        gbc2.anchor = GridBagConstraints.LINE_END;
        gbc2.insets = new Insets(10, 50, 10, 0);
        contentPanel.add(tf3, gbc2);
        
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.insets = new Insets(20, 0, 20, 0);
        contentPanel.add(statusLabel, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 4;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(confirm, gbc2);

        // Action
        confirm.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                confirm.setBackground(new Color(127, 30, 165));
                confirmLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                confirm.setBackground(new Color(152, 55, 190));
                confirmLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                confirm.setBackground(new Color(178, 81, 216));
                confirmLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                confirm.setBackground(new Color(127, 30, 165));
                confirmLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    Statement st = conn.createStatement();
                    String accountName = tf2.getText();
                    String query, query2, query3, validSend;
                    ResultSet rs, rs2, rs3, rs4;
                    BigDecimal amount , balance, balanceReceive;
                    
                    if(!tf3.getText().isEmpty()){
                        String take = tf3.getText();
                        String amountStr = take.replaceAll(",", "");
                        amount = new BigDecimal(amountStr);
                        System.out.println(amount);
                    } else {
                        amount = new BigDecimal(-1);
                    }
                    if(!tf2.getText().isEmpty() && !tf3.getText().isEmpty()){
                        if (!accountName.equals(username)){
                            // cari apakah username yang dituju ada atau tidak
                            query2 = "SELECT * FROM customer_account WHERE username = '" + accountName + "'";
                            rs = st.executeQuery(query2);
                            if(rs.next()){
                                // cek validitas akun yang mengirim
                                query2 = "SELECT * FROM customer_account WHERE username = '" + username + "'";
                                ResultSet rsAkun;
                                rsAkun = st.executeQuery(query2);
                                rsAkun.next();
                                validSend = rsAkun.getString("validation");
                                if(validSend.equalsIgnoreCase("valid")){
                                    query2 = "SELECT * FROM customer_account WHERE username = '" + accountName + "'";
                                    ResultSet rsTerima;
                                    rsTerima = st.executeQuery(query2);
                                    rsTerima.next();
                                    String validReceive = rsTerima.getString("validation");
                                    if(validReceive.equalsIgnoreCase("valid")){
                                        query2 = "SELECT * FROM customer_account WHERE username = '" + username + "'";
                                        rsAkun = st.executeQuery(query2);
                                        rsAkun.next();
                                        balance = rsAkun.getBigDecimal("balance");
                                        if(balance.compareTo(amount) >= 0){
                                            int konfirmTrf = JOptionPane.showOptionDialog(null, "Are you sure you want to transfer to this account?", "transfer?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                            if (konfirmTrf == JOptionPane.YES_OPTION) {
                                                query2 = "SELECT * FROM customer_account WHERE username = '" + accountName + "'";
                                                rsTerima = st.executeQuery(query2);
                                                rsTerima.next();
                                                balanceReceive = rsTerima.getBigDecimal("balance");
                                                balance = balance.subtract(amount);
                                                String update = "UPDATE customer_account SET balance = " + balance +" WHERE account_id = " + accID;
                                                System.out.println(update);
                                                st.execute(update);
                                                System.out.println("Helooo");
                                                balanceReceive = balanceReceive.add(amount);
                                                update = "UPDATE customer_account SET balance = " + balanceReceive +" WHERE username = '" + accountName + "'";
                                                st.execute(update);
                                                Calendar calendar = Calendar.getInstance();
                                                java.sql.Timestamp time = new java.sql.Timestamp(calendar.getTime().getTime());
                                                String history = "INSERT INTO transfer_history (transferFrom, transferTo, amount, time) VALUES (" + "'" + username + "', '" + accountName + "', " + amount + ", '" + time + "')";
                                                st.execute(history);
                                                statusLabel.setText("Transfer succesfull!");
                                                statusLabel.setForeground(Color.green);
                                                tf2.setText("");
                                                tf3.setText("0");
                                            }
                                        } else {
                                            statusLabel.setText("Your Balance is not enough!");
                                            statusLabel.setForeground(Color.red);
                                            tf2.setText("");
                                            tf3.setText("0");
                                        }

                                    }else {
                                        statusLabel.setText("That customer is not validated yet!");
                                        statusLabel.setForeground(Color.red);
                                        tf2.setText("");
                                        tf3.setText("0");
                                    }
                                } else {
                                    statusLabel.setText("Your account is not validated yet!");
                                    statusLabel.setForeground(Color.red);
                                    tf2.setText("");
                                    tf3.setText("0");
                                } 
                            } else {
                                statusLabel.setText("Account is not available!");
                                statusLabel.setForeground(Color.red);
                                tf2.setText("");
                                tf3.setText("0");
                            }
                              
                        } else {
                            statusLabel.setText("Cant transfer to your account!");
                            statusLabel.setForeground(Color.red);
                            tf2.setText("");
                            tf3.setText("0");
                        }       
                    } else {
                        statusLabel.setText("Please enter the empty field!");
                        statusLabel.setForeground(Color.red);
                    }
                } catch (SQLException ex){
                    System.out.println("Gagal koneksi");
                } 
            }
            
        });
        
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
}
