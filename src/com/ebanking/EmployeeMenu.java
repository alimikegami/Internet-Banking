/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author alimi
 */
public class EmployeeMenu extends JFrame {
    static Connection conn = configDB.configure();
    static String username, password;
    static String blocked="0", newAcc1="0", registered="0";
    EmployeeMenu(String username, String password){
        EmployeeMenu.username = username;
        EmployeeMenu.password = password;
        
        setLayout(new BorderLayout());
        
        JPanel sideBar = new JPanel(new GridBagLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(800, 700));
        sideBar.setBackground(new Color(89, 62, 116));
        mainPanel.setBackground(new Color(41, 54, 63));
        
        JPanel panel1 = new JPanel(new GridBagLayout());
        JPanel panel2 = new JPanel(new GridBagLayout());
        JPanel panel3 = new JPanel(new GridBagLayout());
        JPanel panel5 = new JPanel(new GridBagLayout());
        JPanel optionPanel = new JPanel(new GridBagLayout());
        
        panel1.setPreferredSize(new Dimension(200, 50));
        panel2.setPreferredSize(new Dimension(200, 50));
        panel3.setPreferredSize(new Dimension(200, 50));
        panel5.setPreferredSize(new Dimension(200, 50));
        panel1.setBackground(new Color(64, 37, 91));
        panel2.setBackground(new Color(89, 62, 116));
        panel3.setBackground(new Color(89, 62, 116));
        panel5.setBackground(new Color(89, 62, 116));
        
        panel1.setBorder(new MatteBorder(0, 0, 0, 3, Color.white));
        panel2.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        panel3.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        panel5.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        
        JLabel label1 = new JLabel("Home");
        JLabel label2 = new JLabel("Validate Account");
        JLabel label3 = new JLabel("Search Account");
        JLabel label5 = new JLabel("Add Funds");
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
                panel1.setBackground(new Color(64, 37, 91));
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
                Searching obj2 = new Searching(username, password);
                dispose();
            }
        });
       
        
        panel5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panel5.setBackground(new Color(135, 85, 185));
                panel5.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panel5.setBackground(new Color(89, 62, 116));
                panel5.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panel5.setBackground(new Color(178, 81, 216));
                panel5.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panel5.setBackground(new Color(135, 85, 185));
                panel5.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                Addfunds obj5 = new Addfunds(username, password);
                dispose();
            }
        });
        
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

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        sideBar.add(optionPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        sideBar.add(dummyLabel, gbc);
        
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
        add(topBar, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(700, 600));
        //contentPanel.setBackground(new Color(41, 54, 63));
        GridBagConstraints gbc4 = new GridBagConstraints();
//        gbc4.gridx = 0;
//        gbc4.gridy = 0;
//        gbc4.weighty = 0;
//        gbc4.fill = GridBagConstraints.HORIZONTAL;
//        mainPanel.add(topBar, gbc4);
        gbc4.gridx = 0;
        gbc4.gridy = 1;
        gbc4.weighty = 1;
        gbc4.fill = GridBagConstraints.NONE;
        mainPanel.add(contentPanel, gbc4);
        
        JPanel accInfo = new JPanel(new GridBagLayout());
        accInfo.setBackground(new Color(41, 54, 63));
        accInfo.setPreferredSize(new Dimension(700, 70));
        contentPanel.add(accInfo, BorderLayout.NORTH);
        contentPanel.setBackground(new Color(41, 54, 63));
        JPanel registeredAcc = new JPanel(new GridBagLayout());
        JPanel newAcc = new JPanel(new GridBagLayout());
        JPanel blockedAcc = new JPanel(new GridBagLayout());
        ImageIcon registeredImg = new ImageIcon(getClass().getResource("/images/registered.png"));
        JLabel registeredLabel = new JLabel(registeredImg);
        ImageIcon newAccImg = new ImageIcon(getClass().getResource("/images/newAcc.png"));
        JLabel newAccLabel = new JLabel(newAccImg);
        ImageIcon blockedAccImg = new ImageIcon(getClass().getResource("/images/block-user.png"));
        JLabel blockedAccLabel = new JLabel(blockedAccImg);
        getUserDetails();
        JLabel labelInfo1 = new JLabel("Registered Accounts");
        JLabel amount1 = new JLabel();
        amount1.setText(registered);
        JLabel labelInfo2 = new JLabel("New Accounts");
        JLabel amount2 = new JLabel();
        amount2.setText(newAcc1);
        JLabel labelInfo3 = new JLabel("Blocked Accounts");
        JLabel amount3 = new JLabel();
        amount3.setText(blocked);

        amount1.setFont(new Font("SansSerif", Font.BOLD, 16));
        amount2.setFont(new Font("SansSerif", Font.BOLD, 16));
        amount3.setFont(new Font("SansSerif", Font.BOLD, 16));
        JPanel panel6 = new JPanel(new GridBagLayout());
        panel6.setPreferredSize(new Dimension(200, 50));
        JPanel panel7 = new JPanel(new GridBagLayout());
        panel7.setPreferredSize(new Dimension(200, 50));
        JPanel panel8 = new JPanel(new GridBagLayout());
        panel8.setPreferredSize(new Dimension(200, 50));

        GridBagConstraints gbc5 = new GridBagConstraints();
        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 0;
        gbc6.gridy = 0;
        gbc6.gridheight = 2;
        gbc6.insets = new Insets(0, 5, 0, 0);
        panel6.add(registeredLabel, gbc6);
        panel7.add(newAccLabel, gbc6);
        panel8.add(blockedAccLabel, gbc6);
        
        gbc6.gridx = 1;
        gbc6.gridy = 0;
        gbc6.gridheight = 1;
        gbc6.insets = new Insets(10, 20, 0, 20);
        panel6.add(labelInfo1, gbc6);
        panel7.add(labelInfo2, gbc6);
        panel8.add(labelInfo3, gbc6);
        gbc6.gridx = 1;
        gbc6.gridy = 1;
        gbc6.insets = new Insets(5, 20, 10, 20);
        panel6.add(amount1, gbc6);
        panel7.add(amount2, gbc6);
        panel8.add(amount3, gbc6);
        
        gbc5.gridx = 0;
        gbc5.gridy = 0;
        gbc5.insets = new Insets(0, 10, 0, 10);
        accInfo.add(panel6, gbc5);
        gbc5.gridx = 1;
        gbc5.gridy = 0;
        gbc5.insets = new Insets(0, 10, 0, 10);
        accInfo.add(panel7, gbc5);
        gbc5.gridx = 2;
        gbc5.gridy = 0;
        gbc5.insets = new Insets(0, 10, 0, 10);
        accInfo.add(panel8, gbc5);
        add(mainPanel);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void getAccountDetails(){
        try {
            Statement st = conn.createStatement();
            System.out.println("sukses");
            System.out.println(username);
            System.out.println(password);
        } catch(SQLException e){
            System.out.println("agasadadada");
        }
    }
    
    public static void getUserDetails() {
        int count=0, count2=0, count3=0;
        try {
            String q1= "SELECT * FROM customer_account";
            String nv="not valid";
            String v="valid";
            String b="blocked";
            ResultSet rs;
            Statement st = conn.createStatement();
            rs = st.executeQuery(q1);
            while(rs.next()){
                if(nv.equalsIgnoreCase(rs.getString("validation"))){
                    count++;
                }else if(v.equalsIgnoreCase(rs.getString("validation"))){
                    count2++;
                } else {
                    count3++;
                }
            }
            newAcc1 = Integer.toString(count);
            registered = Integer.toString(count2);
            blocked = Integer.toString(count3);
        } catch (SQLException e) {
            System.out.println("gagal cuk astaga");
        }
    }
}
