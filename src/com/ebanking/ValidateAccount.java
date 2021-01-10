/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import static com.ebanking.EmployeeMenu.blocked;
import static com.ebanking.EmployeeMenu.getUserDetails;
import static com.ebanking.EmployeeMenu.newAcc1;
import static com.ebanking.EmployeeMenu.registered;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author alimi
 */
public class ValidateAccount extends JFrame {
    String[][] eachValidation;
    Connection conn = configDB.configure();
    private final int total = getValidationAmount();
    // Panel yang bakal diisi panel kecil buat validasi 
    JPanel validationPanel = new JPanel(new GridBagLayout());
    
    ValidateAccount(String username, String password){
        
        EmployeeMenu.username = username;
        EmployeeMenu.password = password;
        this.eachValidation = new String[total][7];
        getValidationData();
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
        panel2.setBackground(new Color(64, 37, 91));
        panel3.setBackground(new Color(89, 62, 116));
        panel5.setBackground(new Color(89, 62, 116));
        
        panel1.setBorder(new MatteBorder(2, 0, 2, 0, new Color(64, 37, 91)));
        panel2.setBorder(new MatteBorder(0, 0, 0, 3, Color.white));
        panel3.setBorder(new MatteBorder(2, 0, 2, 0, new Color(64, 37, 91)));
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
                panel2.setBackground(new Color(64, 37, 91));
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
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(700, 600));
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 1;
        gbc4.weighty = 1;
        gbc4.fill = GridBagConstraints.NONE;
        mainPanel.add(contentPanel, gbc4);
        
        JScrollPane scrollablePanel = new JScrollPane(validationPanel, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablePanel.setPreferredSize(new Dimension(600, 500));
        scrollablePanel.setMinimumSize(new Dimension(600, 500));
        scrollablePanel.getVerticalScrollBar().setUnitIncrement(16);
        createValidationPanel();
        contentPanel.add(scrollablePanel);
        add(mainPanel);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private int getValidationAmount() {
        int count = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customer_account");
            while(rs.next()){
                String cek = rs.getString("validation");
                if(cek.equals("not valid")) {
                    count++;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Gagal sql di getvalidation");
        }
        return count;
    }
    
    private void getValidationData() {
        int counter=0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customer_account");
            while(rs.next()){
                String cek = rs.getString("validation");
                if(cek.equals("not valid")) {
                    String cariId = rs.getString("customer_id");
                    eachValidation[counter][0] = rs.getString("username");
                    Statement st2 = conn.createStatement();
                    String qr1 = "SELECT * FROM customer WHERE customer_id = " +cariId +";";
                    ResultSet rs1 = st2.executeQuery(qr1);
                    rs1.next();
                    eachValidation[counter][1] = rs1.getString("NIK");
                    eachValidation[counter][2] = rs1.getString("first_name");
                    eachValidation[counter][3] = rs1.getString("middle_name");
                    eachValidation[counter][4] = rs1.getString("last_name");
                    eachValidation[counter][5] = rs1.getString("gender");
                    eachValidation[counter][6] = rs1.getString("street_address");
                    counter++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal sql di getValidationData");
        }
    }
    
    private void createValidationPanel() {
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
            validationPanel.add(createPanelValid(counter, 600, 110), gbc3);
            counter++;
        }
        JLabel dummyLabel = new JLabel(" ");
        gbc3.gridy = counter;
        gbc3.weighty = 1;
        validationPanel.add(dummyLabel, gbc3);
    }
    
    public JPanel createPanelValid(int counter, int panjang, int lebar) {
        String name;
        // Panel utama
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(110, 60, 160));
        panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(127,30,165)));
        panel.setPreferredSize(new Dimension(panjang, lebar));
        
        // Buat nama berdasarkan ada tidaknya middle name
        if (eachValidation[counter][3] != null && !eachValidation[counter][3].equals("")) {
            name = eachValidation[counter][2] + " " + eachValidation[counter][3] + " " + eachValidation[counter][4];
        } else {
            name = eachValidation[counter][2] + " " + eachValidation[counter][4];
        }
        
        // Membuat label-label
        JLabel ket1 = new JLabel("Full Name: ");
        JLabel ket2 = new JLabel("Username: ");
        JLabel ket3 = new JLabel("NIK: ");
        JLabel ket4 = new JLabel("Address: ");
        JLabel fullName = new JLabel(name);
        JLabel uname = new JLabel(eachValidation[counter][0]);
        JLabel address = new JLabel(eachValidation[counter][6]);
        JLabel nomorInduk = new JLabel(eachValidation[counter][1]);
        ket1.setForeground(Color.white);
        ket2.setForeground(Color.white);
        ket3.setForeground(Color.white);
        ket4.setForeground(Color.white);
        fullName.setForeground(Color.white);
        uname.setForeground(Color.white);
        address.setForeground(Color.white);
        nomorInduk.setForeground(Color.white);
        // Meletakkan label dan komponennya pada panel utama
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        if(eachValidation[counter][5].equals("male")) {
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
        
        // Membuat button accept dan block
        JLabel dummy = new JLabel("");
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridheight = 4;
        panel.add(dummy, gbc);
        
        JPanel accept = new JPanel(new GridBagLayout());
        JPanel block = new JPanel(new GridBagLayout());
        accept.setBackground(new Color(110, 60, 160));
        block.setBackground(new Color(110, 60, 160));
        accept.add(createLabelAccept());
        block.add(createLabelBlock());
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 0, 0, 20);
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(accept, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        panel.add(block, gbc);
        accept.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accept.addMouseListener(new MouseAdapter(){
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
                try {
                    int konfirmValid = JOptionPane.showOptionDialog(null, "Are you sure this account is valid?", "valid?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (konfirmValid == JOptionPane.YES_OPTION) {
                        String query = "UPDATE customer_account SET validation = 'valid' WHERE username = '" + eachValidation[counter][0] + "'";
                        Statement st = conn.createStatement();
                        st.execute(query);
                        panel.setBackground(new Color(37, 142, 82));
                        panel.remove(accept);
                        panel.remove(block);
                        panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(37, 142, 82)));
                    }
                    
                    
                } catch (SQLException ex) {
                    System.out.println("gAGAL di validate");
                }
            }
        });
        
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
                try {
                    int konfirmBlock = JOptionPane.showOptionDialog(null, "Are you sure this account is not valid?", "not valid?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (konfirmBlock == JOptionPane.YES_OPTION) { 
                        String query = "UPDATE customer_account SET validation = 'blocked' WHERE username = '" + eachValidation[counter][0] + "'";
                        Statement st = conn.createStatement();
                        st.execute(query);
                        panel.setBackground(new Color(163, 24, 0));
                        panel.remove(accept);
                        panel.remove(block);
                        panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(163, 24, 0)));
                    }
                } catch (SQLException ex) {
                    System.out.println("gAGAL di validate");
                }
            }
        });
        return panel;
    }
    
    public JLabel createLabelMale(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/man.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelFemale(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/woman.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelAccept(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/check.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelBlock(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/block.png"));
        return new JLabel(img);
    }
}
