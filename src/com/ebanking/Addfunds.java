/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import static com.ebanking.Transfer.formatRupiah;
import static com.ebanking.Transfer.kursIndo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 *
 * @author alimi
 */
public class Addfunds extends JFrame {
    Connection conn = configDB.configure();
    
    Addfunds(String username, String password){
        
        EmployeeMenu.username = username;
        EmployeeMenu.password = password;
        setLayout(new BorderLayout());
        // Sidebar untuk panel kiri, yaitu home dll
        JPanel sideBar = new JPanel(new GridBagLayout());
        sideBar.setBackground(new Color(89, 62, 116));
        
        // Main panel itu panel paling gede warnanya abu gelap
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(800, 700));
        mainPanel.setBackground(new Color(41, 54, 63));
        
        // Panel home dll
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridBagLayout());
        JPanel panel3 = new JPanel(new GridBagLayout());
        JPanel panel5 = new JPanel(new GridBagLayout());
        JPanel optionPanel = new JPanel(new GridBagLayout());
        
        panel1.setPreferredSize(new Dimension(200, 50));
        panel2.setPreferredSize(new Dimension(200, 50));
        panel3.setPreferredSize(new Dimension(200, 50));
        panel5.setPreferredSize(new Dimension(200, 50));
        panel1.setBackground(new Color(89, 62, 116));
        panel2.setBackground(new Color(89, 62, 116));
        panel3.setBackground(new Color(89, 62, 116));
        panel5.setBackground(new Color(64, 37, 91));
        
        panel1.setBorder(new MatteBorder(2, 0, 2, 0, new Color(64, 37, 91)));
        panel2.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        panel3.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        panel5.setBorder(new MatteBorder(0, 0, 0, 3, Color.white));
        
        JLabel label1 = new JLabel("Home");
        JLabel label2 = new JLabel("Validate Account");
        JLabel label3 = new JLabel("Search Account");
        JLabel label5 = new JLabel("Add Funds");
        
        // dummyLabel supaya panel info bisa nempel di atas
        JLabel dummyLabel = new JLabel(" ");
        
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label5.setForeground(Color.white);
        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        panel5.add(label5);
        panel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panel1.setBackground(new Color(135, 85, 185));
                label1.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panel1.setBackground(new Color(89, 62, 116));
                label1.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panel1.setBackground(new Color(178, 81, 216));
                label1.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panel1.setBackground(new Color(135, 85, 185));
                label1.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                EmployeeMenu obj1 = new EmployeeMenu(username, password);
                dispose();
            }
        });
        panel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panel2.setBackground(new Color(135, 85, 185));
                label2.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panel2.setBackground(new Color(89, 62, 116));
                label2.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panel2.setBackground(new Color(178, 81, 216));
                label2.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panel2.setBackground(new Color(135, 85, 185));
                label2.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                ValidateAccount obj1 = new ValidateAccount(username, password);
                dispose();
            }
        });
        
        panel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panel3.setBackground(new Color(135, 85, 185));
                label3.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panel3.setBackground(new Color(89, 62, 116));
                label3.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panel3.setBackground(new Color(178, 81, 216));
                label3.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panel3.setBackground(new Color(135, 85, 185));
                label3.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                Searching obj3 = new Searching(username, password);
                dispose();
            }
        });
        
        panel5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panel5.setBackground(new Color(135, 85, 185));
                label5.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panel5.setBackground(new Color(64, 37, 91));
                label5.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panel5.setBackground(new Color(178, 81, 216));
                label5.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panel5.setBackground(new Color(135, 85, 185));
                label5.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                Addfunds obj5 = new Addfunds(username, password);
                dispose();
            }
        });
        
        // Buat option panel di bagian kiri yang isinya home sampe all transaction
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        optionPanel.add(panel1, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        optionPanel.add(panel2, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        optionPanel.add(panel3, gbc2);
        gbc2.gridx = 0;
        gbc2.gridy = 4;
        optionPanel.add(panel5, gbc2);

        // masang panel sama dummy label di sidebarnya
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        sideBar.add(optionPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        sideBar.add(dummyLabel, gbc);
        // Side bar sudah terpasang
        add(sideBar, BorderLayout.WEST);
        
        // topbar ini buat LOGO sama ADMINNYA
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(140, 113, 167));
        JPanel logo = new JPanel(new GridBagLayout());
        ImageIcon logoImg = new ImageIcon(getClass().getResource("/images/logo.png"));
        JLabel logoLabel = new JLabel(logoImg);
        logo.add(logoLabel);
        logo.setBackground(new Color(89, 62, 116));
        logo.setPreferredSize(new Dimension(200, 50));
        // Button Logout
        JPanel logOutButton = new JPanel(new GridBagLayout());
        ImageIcon img = new ImageIcon(getClass().getResource("/images/logout.png"));
        JLabel logOut = new JLabel(img);
        logOutButton.add(logOut);
        logOutButton.setBackground(new Color(140, 113, 167));
        logOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logOutButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){

            }
            @Override
            public void mouseExited(MouseEvent e){

            }
            @Override
            public void mousePressed(MouseEvent e){

            }
            @Override
            public void mouseReleased(MouseEvent e){

            }
            @Override
            public void mouseClicked(MouseEvent e){
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if(answer == JOptionPane.YES_OPTION) {
                    Login menulogin = new Login();
                    dispose();
                }
            }
        });
        // Panel nama admin
        JLabel adminAcc = new JLabel("ADMIN");
        JPanel accPanel = new JPanel(new GridBagLayout());
        accPanel.setPreferredSize(new Dimension(100, 50));
        accPanel.setBackground(new Color(140, 113, 167));
        accPanel.add(adminAcc);
        
        // Gabungin admin sama logout
        JPanel combine = new JPanel(new GridBagLayout());
        combine.setPreferredSize(new Dimension(150, 50));
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        combine.add(accPanel, gbc1);
        gbc1.gridy = 0;
        gbc1.gridx = 1;
        gbc1.insets = new Insets(0, 0, 0, 10);
        combine.add(logOutButton, gbc1);
        combine.setBackground(new Color(140, 113, 167));
        
        // accPanel -> ADMIN, logo -> LOGO
        topBar.add(combine, BorderLayout.EAST);
        topBar.add(logo, BorderLayout.WEST);
        
        // Top bar dipasang pake borderlayout di NORTH
        add(topBar, BorderLayout.NORTH);
        
        // Content panel -> tempat ngisi konten
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(700, 600));
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 1;
        gbc4.weighty = 1;
        gbc4.fill = GridBagConstraints.NONE;
        mainPanel.add(contentPanel, gbc4);
        
        // Panel yang isi textfield dll
        JPanel wholeAddFundsPanel = new JPanel(new GridBagLayout());
        JTextField uName = new JTextField(20);
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
        JLabel label6 = new JLabel("Username: ");
        JLabel label7 = new JLabel("Amount: ");
        JLabel statusLabel = new JLabel (" ");
        JLabel confirm = new JLabel("Confirm");
        confirm.setForeground(Color.white);
        JPanel confirmButton = new JPanel(new GridBagLayout());
        JPanel status = new JPanel(new GridBagLayout());
        confirmButton.add(confirm);
        confirmButton.setBackground(new Color(89, 62, 116));
        confirmButton.setPreferredSize(new Dimension(100, 30));
        status.add(statusLabel);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.anchor = GridBagConstraints.LINE_START;
        gbc3.insets = new Insets(0, 0, 10, 10);
        contentPanel.add(label6, gbc3);
        gbc3.gridx = 1;
        gbc3.gridy = 0;
        gbc3.insets = new Insets(0, 0, 10, 0);
        gbc3.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(uName, gbc3);
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.insets = new Insets(0, 0, 10, 10);
        gbc3.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(label7, gbc3);
        gbc3.gridx = 1;
        gbc3.gridy = 1;
        gbc3.insets = new Insets(0, 0, 10, 0);
        gbc3.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(tf3, gbc3);
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 2;
        gbc3.anchor = GridBagConstraints.CENTER;
        contentPanel.add(status, gbc3);
        gbc3.gridx = 0;
        gbc3.gridy = 3;
        gbc3.gridwidth = 2;
        contentPanel.add(confirmButton, gbc3);
        
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                confirmButton.setBackground(new Color(135, 85, 185));
                confirmButton.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                confirmButton.setBackground(new Color(89, 62, 116));
                confirmButton.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                confirmButton.setBackground(new Color(178, 81, 216));
                confirmButton.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                confirmButton.setBackground(new Color(135, 85, 185));
                confirmButton.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    Statement st = conn.createStatement();
                    String userName = uName.getText();
                    String query;
                    ResultSet rs;
                    BigDecimal amount, balance;
                    if(!tf3.getText().isEmpty()){
                        String take = tf3.getText();
                        String amountStr = take.replaceAll(",", "");
                        amount = new BigDecimal(amountStr);
                        System.out.println(amount);
                    } else {
                        amount = new BigDecimal(-1);
                    }
                    if(!uName.getText().isEmpty() && !tf3.getText().isEmpty()) {
                        query = "SELECT * FROM customer_account WHERE username = '" + userName +"'";
                        rs = st.executeQuery(query);
                        if(rs.next()) {
                            String validation = rs.getString("validation");
                            if(validation.equalsIgnoreCase("valid")) {
                                int konfirmAddFunds = JOptionPane.showOptionDialog(null, "Are you sure you want to add funds to this account?", "Add Funds?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                                if (konfirmAddFunds == JOptionPane.YES_OPTION) {
                                    balance = rs.getBigDecimal("balance");
                                    balance = balance.add(amount);
                                    String update = "UPDATE customer_account SET balance = " + balance +" WHERE username = '" + userName + "'";
                                    st.execute(update);
                                    statusLabel.setText("Funds added succesfully!");
                                    statusLabel.setForeground(Color.green);
                                    uName.setText("");
                                    tf3.setText("0");
                                }
                            } else {
                                statusLabel.setText("Funds cannot be added because account is not valid yet!");
                                statusLabel.setForeground(Color.red);
                                uName.setText("");
                                tf3.setText("0");
                            }
                        } else {
                            statusLabel.setText("Account not found!");
                            statusLabel.setForeground(Color.red);
                            uName.setText("");
                            tf3.setText("0");
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Gagal di add funds!");
                }
            }
        });
        
        add(mainPanel);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
