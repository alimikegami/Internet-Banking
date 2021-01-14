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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author alimi
 */
public class Searching extends JFrame {
    Connection conn = configDB.configure();
    JPanel searchingPanel = new JPanel(new GridBagLayout());
    String[][] allAccount;
    boolean flag;
    private final int userAmount = getUserAmount();
    Searching(String username, String password){
        this.addWindowFocusListener(new WindowFocusListener(){
            @Override
            public void windowGainedFocus(WindowEvent e) {
                searchingPanel.removeAll();
                searchingPanel.revalidate();
                searchingPanel.repaint();
                getAccountData();
                try {
                    createSearchPanel(userAmount);
                } catch (SQLException sqle) {
                    System.out.println("gagal di load data");
                }
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
            
            }
        });
        EmployeeMenu.username = username;
        EmployeeMenu.password = password;
        this.allAccount = new String[userAmount][8];
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
        panel3.setBackground(new Color(64, 37, 91));
        panel5.setBackground(new Color(89, 62, 116));
        
        panel1.setBorder(new MatteBorder(2, 0, 2, 0, new Color(64, 37, 91)));
        panel2.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        panel3.setBorder(new MatteBorder(0, 0, 0, 3, Color.white));
        panel5.setBorder(new MatteBorder(0, 0, 2, 0, new Color(64, 37, 91)));
        
        JLabel label1 = new JLabel("Home");
        JLabel label2 = new JLabel("Validate Account");
        JLabel label3 = new JLabel("Search Account");
        JLabel label5 = new JLabel("Add funds");
        
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
                panel3.setBackground(new Color(64, 37, 91));
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
                panel5.setBackground(new Color(89, 62, 116));
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
        logoLabel.setForeground(Color.white);
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
        JTextField searchBar = new JTextField(20);
        JPanel searchButton = new JPanel(new GridBagLayout());
        searchButton.setPreferredSize(new Dimension(100, 20));
        searchButton.setBackground(new Color(89, 62, 116));
        JLabel search = new JLabel("Search");
        search.setForeground(Color.white);
        searchButton.add(search);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                searchButton.setBackground(new Color(135, 85, 185));
                search.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                searchButton.setBackground(new Color(89, 62, 116));
                search.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                searchButton.setBackground(new Color(178, 81, 216));
                search.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                searchButton.setBackground(new Color(135, 85, 185));
                search.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                if(!searchBar.getText().isEmpty()) {
                    searchingPanel.removeAll();
                    searchingPanel.revalidate();
                    searchingPanel.repaint();
                    flag = false;
                    getAccountMatchedData(searchBar.getText());
                    try {
                        createSearchPanel(getAccountMatchedAmount(searchBar.getText()));
                        if(!flag) {
                            JLabel notFound = new JLabel("Account/Customer not found!");
                            notFound.setForeground(Color.red);
                            searchingPanel.add(notFound);
                        }
                    } catch (SQLException sqle) {
                        System.out.println("gagal di mouse click all data");
                    }
                } else {
                    
                }
                
            }
        });
        
        JPanel searchAll = new JPanel(new GridBagLayout());
        JLabel searchEverything = new JLabel("View All");
        searchEverything.setForeground(Color.white);
        searchAll.add(searchEverything);
        searchAll.setPreferredSize(new Dimension(100, 20));;
        searchAll.setBackground(new Color(89, 62, 116));
        searchAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchAll.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                searchAll.setBackground(new Color(135, 85, 185));
                searchEverything.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                searchAll.setBackground(new Color(89, 62, 116));
                searchEverything.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                searchAll.setBackground(new Color(178, 81, 216));
                searchEverything.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                searchAll.setBackground(new Color(135, 85, 185));
                searchEverything.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                searchingPanel.removeAll();
                searchingPanel.revalidate();
                searchingPanel.repaint();
                getAccountData();
                try {
                    createSearchPanel(userAmount);
                } catch (SQLException sqle) {
                    System.out.println("gagal di mouse click all data");
                }
                
            }
        });
        JScrollPane scrollablePanel = new JScrollPane(searchingPanel, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.setPreferredSize(new Dimension(600, 400));
        scrollablePanel.getVerticalScrollBar().setUnitIncrement(16);
        
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 0;
        gbc4.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(searchBar, gbc4);
        gbc4.gridx = 1;
        gbc4.gridy = 0;
        gbc4.insets = new Insets(0, 40, 20, 0);
        contentPanel.add(searchButton, gbc4);
        gbc4.gridx = 2;
        gbc4.gridy = 0;
        gbc4.insets = new Insets(0, 0, 20, 0);
        contentPanel.add(searchAll, gbc4);
        gbc4.gridx = 0;
        gbc4.gridy = 1;
        gbc4.gridwidth = 3;
        gbc4.insets = new Insets(0, 0, 0, 0);
        contentPanel.add(scrollablePanel, gbc4);
        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 0;
        gbc5.gridy = 1;
        gbc5.weighty = 1;
        gbc5.fill = GridBagConstraints.NONE;
        mainPanel.add(contentPanel, gbc5);
        add(mainPanel);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void createSearchPanel(int total) throws SQLException {
        int counter = 0;
        GridBagConstraints gbc3 = new GridBagConstraints();
        while(counter < total){
            gbc3.gridx = 0;
            gbc3.gridy = counter;
            if(counter == total-1){
                gbc3.insets = new Insets(5, 0, 10, 0);
            } else {
                gbc3.insets = new Insets(10, 0, 5, 0);
            }
            searchingPanel.add(createPanelSearch(counter, 550, 130), gbc3);
            counter++;
        }
        JLabel dummyLabel = new JLabel(" ");
        gbc3.gridy = counter;
        gbc3.weighty = 1;
        searchingPanel.add(dummyLabel, gbc3);
    }
    
    public JPanel createPanelSearch(int counter, int panjang, int lebar) {
        String name;
        // Panel utama
        int saveIdx = counter;
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(95, 47, 157));
        panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(127,30,165)));
        panel.setPreferredSize(new Dimension(panjang, lebar));
        
        // Buat nama berdasarkan ada tidaknya middle name
        if (allAccount[counter][3] != null && !allAccount[counter][3].equals("")) {
            name = allAccount[counter][2] + " " + allAccount[counter][3] + " " + allAccount[counter][4];
        } else {
            name = allAccount[counter][2] + " " + allAccount[counter][4];
        }
        
        // Membuat label-label
        JLabel ket1 = new JLabel("Full Name: ");
        JLabel ket2 = new JLabel("Username: ");
        JLabel ket3 = new JLabel("NIK: ");
        JLabel ket4 = new JLabel("Address: ");
        JLabel ket5 = new JLabel("Account State: ");
        JLabel fullName = new JLabel(name);
        JLabel uname = new JLabel(allAccount[counter][0]);
        JLabel address = new JLabel(allAccount[counter][6]);
        JLabel nomorInduk = new JLabel(allAccount[counter][1]);
        JLabel accState = new JLabel(allAccount[counter][7]);
        ket1.setForeground(Color.white);
        ket2.setForeground(Color.white);
        ket3.setForeground(Color.white);
        ket4.setForeground(Color.white);
        ket5.setForeground(Color.white);
        fullName.setForeground(Color.white);
        uname.setForeground(Color.white);
        address.setForeground(Color.white);
        nomorInduk.setForeground(Color.white);
        accState.setForeground(Color.white);
        // Meletakkan label dan komponennya pada panel utama
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        if(allAccount[counter][5].equals("male")) {
            panel.add(createLabelMale(), gbc);
        } else {
            panel.add(createLabelFemale(), gbc);
        }
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(ket1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(ket2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(ket3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(ket4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(ket5, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(fullName, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(uname, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(nomorInduk, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(address, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(accState, gbc);
        
        // Membuat button accept dan block
        JLabel dummy = new JLabel("");
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridheight = 4;
        panel.add(dummy, gbc);
        
        
        JPanel delete = new JPanel(new GridBagLayout());
        JPanel unblock = new JPanel(new GridBagLayout());
        JPanel block = new JPanel(new GridBagLayout());
        delete.setBackground(new Color(110, 60, 160));
        delete.add(createLabelDelete());
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(20, 0, 0, 20);
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(delete, gbc);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addMouseListener(new MouseAdapter(){
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
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this account?", "Delete Account", JOptionPane.YES_NO_OPTION);
                if(answer == JOptionPane.YES_OPTION) {
                    panel.remove(delete);
                    if(allAccount[counter][7].equals("blocked")) {
                        panel.remove(unblock);
                    }
                    panel.revalidate();
                    panel.repaint();
                    String qr = "DELETE FROM customer_account WHERE username = '" + allAccount[counter][0] + "'";
                    JLabel deleted = new JLabel("Account deleted!");
                    try {
                        Statement st = conn.createStatement();
                        st.execute(qr);
                    }catch (SQLException sqlex) {
                        System.out.println("Gagal di delete");
                    }
                    deleted.setForeground(new Color(235, 0, 4));
                    gbc.gridx = 4;
                    gbc.gridy = 0;
                    gbc.weightx = 0;
                    gbc.gridheight = 2;
                    gbc.insets = new Insets(20, 0, 0, 20);
                    gbc.anchor = GridBagConstraints.LINE_START;
                    panel.add(deleted, gbc);
                }
            }
        });
        
        if(allAccount[counter][7].equals("blocked")) {
            unblock.setBackground(new Color(110, 60, 160));
            unblock.add(createLabelUnblock());
            gbc.gridx = 4;
            gbc.gridy = 2;
            gbc.insets = new Insets(20, 0, 0, 20);
            panel.add(unblock, gbc);
            unblock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            unblock.addMouseListener(new MouseAdapter(){
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
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to unblock this account?", "Unblock Account", JOptionPane.YES_NO_OPTION);
                    if(answer == JOptionPane.YES_OPTION) {
                        String query = "UPDATE customer_account SET validation = 'valid' WHERE username = '" + allAccount[counter][0] + "'";
                        try {
                            Statement st = conn.createStatement();
                            st.execute(query);
                        }catch (SQLException sqlex) {
                            System.out.println("Gagal di unblock");
                        }
                    }
                }
            });
        } else {
            block.setBackground(new Color(110, 60, 160));
            block.add(createLabelBlock());
            gbc.gridx = 4;
            gbc.gridy = 2;
            gbc.insets = new Insets(20, 0, 0, 20);
            panel.add(block, gbc);
            block.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            block.addMouseListener(new MouseAdapter(){
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
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to block this account?", "Block Account", JOptionPane.YES_NO_OPTION);
                    if(answer == JOptionPane.YES_OPTION) {
                        String query = "UPDATE customer_account SET validation = 'blocked' WHERE username = '" + allAccount[counter][0] + "'";
                        try {
                            Statement st = conn.createStatement();
                            st.execute(query);
                        }catch (SQLException sqlex) {
                            System.out.println("Gagal di block");
                        }
                    }
                }
            });
        
        }
        
        return panel;
    }
    
    private int getUserAmount() {
        int count = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customer_account");
            while(rs.next()){
                String cek = rs.getString("validation");
                if(cek.equals("valid") || cek.equals("blocked")) {
                    count++;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Gagal sql di userAmount");
        }
        return count;
    }
    
    private void getAccountData() {
        int counter=0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customer_account");
            while(rs.next()){
                String cek = rs.getString("validation");
                if(cek.equals("valid") || cek.equals("blocked")) {
                    String cariId = rs.getString("customer_id");
                    allAccount[counter][0] = rs.getString("username");
                    Statement st2 = conn.createStatement();
                    String qr1 = "SELECT * FROM customer WHERE customer_id = " +cariId +";";
                    ResultSet rs1 = st2.executeQuery(qr1);
                    rs1.next();
                    allAccount[counter][1] = rs1.getString("NIK");
                    allAccount[counter][2] = rs1.getString("first_name");
                    allAccount[counter][3] = rs1.getString("middle_name");
                    allAccount[counter][4] = rs1.getString("last_name");
                    allAccount[counter][5] = rs1.getString("gender");
                    allAccount[counter][6] = rs1.getString("street_address");
                    allAccount[counter][7] = cek;
                    counter++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal sql di getAccountData");
        }
    }
    
    private int getAccountMatchedAmount(String search) {
        int count = 0;
        String name;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customer_account");
            while(rs.next()){
                String cariId = rs.getString("customer_id");
                String un = rs.getString("username");
                Statement st2 = conn.createStatement();
                String qr1 = "SELECT * FROM customer WHERE customer_id = " +cariId +";";
                ResultSet rs1 = st2.executeQuery(qr1);
                rs1.next();
                String fn = rs1.getString("first_name");
                String mn = rs1.getString("middle_name");
                String ln = rs1.getString("last_name");
                if (mn != null && !mn.equals("")) {
                    name = fn + " " + mn + " " + ln;
                } else {
                    name = fn + " " + ln;
                }
                Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(name);
                boolean found = matcher.find();
                if(found || un.equals(search)) {
                    count++;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Gagal sql di getAccountMatchedAmount");
        }
        return count;
    }
    
    private void getAccountMatchedData(String search) {
        int counter=0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customer_account");
            while(rs.next()){
                String name;
                String cariId = rs.getString("customer_id");
                String un = rs.getString("username");
                Statement st2 = conn.createStatement();
                String qr1 = "SELECT * FROM customer WHERE customer_id = " +cariId +";";
                ResultSet rs1 = st2.executeQuery(qr1);
                rs1.next();
                String fn = rs1.getString("first_name");
                String mn = rs1.getString("middle_name");
                String ln = rs1.getString("last_name");
                if (mn != null && !mn.equals("")) {
                    name = fn + " " + mn + " " + ln;
                } else {
                    name = fn + " " + ln;
                }
                Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(name);
                boolean found = matcher.find();
                if(found || un.equals(search)) {
                    flag = true;
                    allAccount[counter][0] = un;
                    allAccount[counter][1] = rs1.getString("NIK");
                    allAccount[counter][2] = fn;
                    allAccount[counter][3] = mn;
                    allAccount[counter][4] = ln;
                    allAccount[counter][5] = rs1.getString("gender");
                    allAccount[counter][6] = rs1.getString("street_address");
                    allAccount[counter][7] = rs.getString("validation");
                    counter++;
                }
                
            }
        } catch (SQLException e) {
            System.out.println("Gagal sql di getAccountMatchedData");
        }
    }
    
    public JLabel createLabelMale(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/man.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelFemale(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/woman.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelDelete(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/delete.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelUnblock(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/unblock.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelBlock(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/block.png"));
        return new JLabel(img);
    }
}