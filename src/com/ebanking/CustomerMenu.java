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
import java.awt.Font;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;
/**
 *
 * @author alimi
 */
public class CustomerMenu extends JFrame {
    static Connection conn = configDB.configure();
    private static String userName, password, balance, accID;
    private static String[][] transferData = new String[6][4];
    JPanel clickBox5 = new JPanel(new GridBagLayout());
    GridBagConstraints gbc2 = new GridBagConstraints();
    String[] splitSave = new String[2];
    
    CustomerMenu(String userName, String password, String accID){
        CustomerMenu.userName = userName;
        CustomerMenu.password = password;
        CustomerMenu.accID = accID;
        
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(41, 54, 63));
        getAccountDetails(userName, password);
        int count = 0, counter = 0;
        count = getRecentTransfer(count);
        System.out.println(count);
        createRecentTransactionPanel(count, this);
        // TOP PANEL
        JPanel topPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(46, 204, 183);
                Color color2 = new Color(199, 79, 201);
                GradientPaint gp = new GradientPaint(0, 0, color2, w, h, color1);
                //GradientPaint gp = new GradientPaint(0, color2, , 0, h, color1);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        topPanel.setLayout(new GridBagLayout());
        topPanel.setPreferredSize(new Dimension(1000, 150));
        //topPanel.setBackground(new Color(46, 204, 183));
        add(topPanel, BorderLayout.NORTH);
        double x = 50;
        double y = 50;
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 0.5;
        gc.weighty = 0.05;
        gc.insets = new Insets(30, 60, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        
        ImageIcon logoImg = new ImageIcon(getClass().getResource("/images/logo.png"));
        JLabel logo = new JLabel(logoImg);
        topPanel.add(logo, gc);
        
        gc.weightx = 0.5;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(30, 0, 0, 60);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        JLabel uName = new JLabel(userName);
        uName.setForeground(new Color(41, 54, 63));
        JPanel logoutAndName = new JPanel(new GridBagLayout());
        logoutAndName.setOpaque(false);
        GridBagConstraints gbclogoutAndName = new GridBagConstraints();
        ImageIcon imgLogout = new ImageIcon(getClass().getResource("/images/arrow.png"));
        JLabel logOutButton = new JLabel(imgLogout);
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
        gbclogoutAndName.insets = new Insets(0, 0, 0, 15);
        logoutAndName.add(uName, gbclogoutAndName);
        gbclogoutAndName.insets = new Insets(0, 0, 0, 0);
        logoutAndName.add(logOutButton, gbclogoutAndName);
        topPanel.add(logoutAndName, gc);
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(0, 60, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        JLabel currentBalance = new JLabel("Current Balance: ");
        currentBalance.setForeground(Color.black);
        topPanel.add(currentBalance, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(0, 60, 30, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        
        // Create indonesian currency
        String balanceRupiah = formatRupiah(balance);
        JLabel balanceValue = new JLabel(balanceRupiah);
        balanceValue.setFont(new Font("SansSerif", Font.PLAIN, 28));
        balanceValue.setForeground(Color.black);
        topPanel.add(balanceValue, gc);
        
        // CENTER PANEL
        ImageIcon image1 = new ImageIcon(getClass().getResource("/images/bank-transfer.png"));
        JLabel label1 = new JLabel(image1);
        ImageIcon image2 = new ImageIcon(getClass().getResource("/images/pay.png"));
        JLabel label2 = new JLabel(image2);
        ImageIcon image3 = new ImageIcon(getClass().getResource("/images/money.png"));
        JLabel label3 = new JLabel(image3);
        ImageIcon image4 = new ImageIcon(getClass().getResource("/images/bank.png"));
        JLabel label4 = new JLabel(image4);
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel centerPanelBig = new JPanel(new GridBagLayout());
        add(centerPanelBig, BorderLayout.CENTER);
        centerPanel.setBackground(new Color(41, 54, 63));
        JPanel clickBox1 = new JPanel(new GridBagLayout());
        JPanel clickBox2 = new JPanel(new GridBagLayout());
        JPanel clickBox3 = new JPanel(new GridBagLayout());
        JPanel clickBox4 = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        clickBox1.add(label1, gbc1);
        clickBox2.add(label2, gbc1);
        clickBox3.add(label3, gbc1);
        clickBox4.add(label4, gbc1);
        
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        JLabel label5 = new JLabel("Transfer");
        JLabel label6 = new JLabel("Pay Bills & Entertainment");
        JLabel label7 = new JLabel("Transaction Details");
        JLabel label8 = new JLabel("Account Details");
        label5.setForeground(Color.white);
        label6.setForeground(Color.white);
        label7.setForeground(Color.white);
        label8.setForeground(Color.white);
        gbc1.insets = new Insets(20, 0, 0, 0);
        clickBox1.add(label5, gbc1);
        clickBox2.add(label6, gbc1);
        clickBox3.add(label7, gbc1);
        clickBox4.add(label8, gbc1);
        
        clickBox1.setBackground(new Color(41, 54, 63));
        clickBox2.setBackground(new Color(41, 54, 63));
        clickBox3.setBackground(new Color(41, 54, 63));
        clickBox4.setBackground(new Color(41, 54, 63));
        clickBox5.setBackground(new Color(41, 54, 63));
        clickBox1.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox2.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox3.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox4.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox5.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox1.setPreferredSize(new Dimension(200, 200));
        clickBox2.setPreferredSize(new Dimension(200, 200));
        clickBox3.setPreferredSize(new Dimension(200, 200));
        clickBox4.setPreferredSize(new Dimension(200, 200));
        clickBox5.setPreferredSize(new Dimension(400, 400));
        clickBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label5.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label5.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label5.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label5.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                Transfer obj5 = new Transfer(userName, password, accID);
                dispose();
            }
        });
        clickBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label6.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label6.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label6.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label6.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayBills obj8 = new PayBills(userName, password, accID);
                dispose();
            }
        });
        clickBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label7.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label7.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label7.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label7.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                TransactionHistory obj3 = new TransactionHistory(userName, password, accID);
                dispose();
            }
            
        });
        clickBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label8.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label8.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label8.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label8.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                AccountDetails obj4 = new AccountDetails(userName, password);
                dispose();
            }
        });
        
        //clickBox5.setMaximumSize(new Dimension(200, 800));
        GridBagConstraints gc2 = new GridBagConstraints();

        //gc2.fill = GridBagConstraints.BOTH;
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.weightx = 0.33;
        gc2.weighty = 0.5;
        gc2.insets = new Insets(30, 30, 15, 15);
        centerPanel.add(clickBox1, gc2);
        
        gc2.insets = new Insets(30, 15, 15, 15);
        gc2.gridx = 1;
        gc2.gridy = 0;
        centerPanel.add(clickBox2, gc2);
        
        gc2.insets = new Insets(15, 30, 30, 15);
        gc2.gridx = 0;
        gc2.gridy = 1;
        centerPanel.add(clickBox3, gc2);
        
        gc2.insets = new Insets(15, 15, 30, 15);
        gc2.gridx = 1;
        gc2.gridy = 1;
        centerPanel.add(clickBox4, gc2);
        
        GridBagConstraints gc3 = new GridBagConstraints();
        
        gc3.insets = new Insets(30, 15, 30, 30);
        gc3.gridx = 2;
        gc3.gridy = 0;
        gc3.gridheight = 2;
        //gc2.anchor = GridBagConstraints.LINE_END;
        gc3.weightx = 0.33;
        gc3.weighty = 0;
        gc3.fill = GridBagConstraints.VERTICAL;
        centerPanel.add(clickBox5, gc3);
        
        JLabel label9 = new JLabel("Recent Transaction History");
        
        // Recent transaction history
        JPanel panelLabel9 = new JPanel(new GridBagLayout());
        panelLabel9.setBorder(new MatteBorder(0, 0, 2, 0, new Color(127,30,165)));
        panelLabel9.add(label9);
        label9.setForeground(Color.white);
        panelLabel9.setBackground(new Color(152, 55, 190));
        gbc2.insets = new Insets(0, 0, 0, 0);
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.ipady = 30;
        gbc2.ipadx = 200;
        gbc2.weighty = 0;
        gbc2.anchor = GridBagConstraints.CENTER;
        clickBox5.add(panelLabel9, gbc2);
        
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.anchor = GridBagConstraints.PAGE_START;
        gbc3.weighty = 0;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        centerPanelBig.add(centerPanel, gbc3);
        JLabel labelDummy = new JLabel(" ");
        gbc3.anchor = GridBagConstraints.CENTER;
        gbc3.weighty = 1;
        gbc3.gridx = 0;
        gbc3.gridy = 1;
        centerPanelBig.add(labelDummy, gbc3);
        centerPanelBig.setBackground(new Color(41, 54, 63));
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createRecentTransactionPanel(int count, CustomerMenu objMenu){
        int counter = 0;
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel transferTitle = new JLabel();
        
        while(counter < count) {
            splitSave = transferData[counter][3].split(" ");
            gbc2.insets = new Insets(5, 20, 5, 0);
            gbc2.gridx = 0;
            gbc2.gridy = counter + 1;
            gbc2.ipady = 0;
            gbc2.ipadx = 0;

            gbc2.anchor = GridBagConstraints.LINE_START;
            if(transferData[counter][0].equals(userName)){
                // Negatif
                clickBox5.add(createPanelTrf(counter, transferData, 350, 40), gbc2);
               
            } else if (transferData[counter][1].equals(userName)){
                // Positif
                clickBox5.add(createPanelRcv(counter, transferData, 350, 40), gbc2);
                  
            }
            
            counter++;
        }
        
        JLabel lastLine = new JLabel("View More");
        JPanel panelLast = new JPanel(new GridBagLayout());
        panelLast.add(lastLine);
        panelLast.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
        panelLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelLast.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                panelLast.setBorder(new RoundEdgedBorder(new Color(76, 0, 114)));
                lastLine.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                panelLast.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                lastLine.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                panelLast.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                lastLine.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                panelLast.setBorder(new RoundEdgedBorder(new Color(76, 0, 114)));
                lastLine.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                TransactionHistory obj6 = new TransactionHistory(userName, password, accID);
                objMenu.dispose();
            }
        });
        
        lastLine.setForeground(Color.white);
        panelLast.setBackground(new Color(152, 55, 190));
        panelLast.setPreferredSize(new Dimension(200, 17));
        gbc2.ipady = 20;
        gbc2.ipadx = 180;        
        gbc2.gridx = 0;
        gbc2.insets = new Insets(5, 5, 5, 5);

        gbc2.gridy = counter + 1;
        gbc2.anchor = GridBagConstraints.PAGE_END;
        gbc2.weighty = 1;
        clickBox5.add(panelLast, gbc2);
    }
    
    public static String formatRupiah(String uang) {
        DecimalFormat indonesianCurrency = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols rupiah = new DecimalFormatSymbols();
        
        rupiah.setCurrencySymbol("Rp");
        rupiah.setMonetaryDecimalSeparator(',');
        rupiah.setGroupingSeparator('.');
        indonesianCurrency.setDecimalFormatSymbols(rupiah);
        
        return indonesianCurrency.format(Double.parseDouble(uang));
    }
    
    private static void getAccountDetails(String username, String password) {
        try {
            Statement st = conn.createStatement();
            String qr1 = "SELECT * FROM customer_account";
            String qr2 = "SELECT * FROM customer";
            String search = "SELECT customer_id FROM customer_account WHERE (username = '" + username + "') AND (password = '" + password + "');";
            ResultSet rs, rs2, rs3;
            rs = st.executeQuery(qr1);
            while(rs.next()){
                if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                    balance = rs.getString("balance");
                }
                
            }
            
        } catch (SQLException e) {
            System.out.println("agasdasdaskdas");
        }
        
        
    }
    
    public static int getRecentTransfer(int count){
        try {
            Statement st = conn.createStatement();
            String query = "SELECT * FROM transfer_history WHERE transferFrom = '" + userName + "' OR transferTo = '" + userName + "' ORDER BY time DESC;";
            ResultSet rs = st.executeQuery(query);
            System.out.println(query);
            int x = 0, y = 0;
            while(rs.next()){
               transferData[x][y] = rs.getString("transferFrom");
               y++;
               transferData[x][y] = rs.getString("transferTo");
               y++;
               transferData[x][y] = rs.getString("amount");
               y++;
               transferData[x][y] = rs.getString("time");
               y++;
               y = 0;
               x++;
               count++;
               if(x == 6){
                   break;
               }
            }
        
        } catch (SQLException e){
            System.out.println("Gagal hitung transfer");
        }
        return count;
    }
    
    public JLabel createLabelPlus(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/plus.png"));
        return new JLabel(img);
    }
    
    public JLabel createLabelMinus(){
        ImageIcon img = new ImageIcon(getClass().getResource("/images/minus.png"));
        return new JLabel(img);
    }
    
    public JPanel createPanelTrf(int counter, String[][] transferData, int x, int y){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(152, 55, 190));
        panel.setPreferredSize(new Dimension(x, y));
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(createLabelMinus(), gbc);

        JLabel transferTitle = new JLabel("Transfered Money");
        transferTitle.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(transferTitle, gbc);

        transferTitle = new JLabel(splitSave[0]);
        transferTitle.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 5, 5);
        panel.add(transferTitle, gbc);


        transferTitle = new JLabel(formatRupiah(transferData[counter][2]));
        gbc.anchor = GridBagConstraints.LINE_END;
        transferTitle.setForeground(Color.white);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 10, 0, 10);
        panel.add(transferTitle, gbc);
        return panel;
    }
    
    private JPanel createPanelRcv(int counter, String[][] transferData, int x, int y){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(x, y));
        panel.setBackground(new Color(152, 55, 190));
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(createLabelPlus(), gbc);

        JLabel transferTitle = new JLabel("Received Money");
        transferTitle.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        panel.add(transferTitle, gbc);

        transferTitle = new JLabel(splitSave[0]);
        transferTitle.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 5, 5);
        panel.add(transferTitle, gbc);


        transferTitle = new JLabel(formatRupiah(transferData[counter][2]));
        gbc.anchor = GridBagConstraints.LINE_END;
        transferTitle.setForeground(Color.white);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 20, 0, 10);
        panel.add(transferTitle, gbc);
        
        return panel;
    }
    
}
