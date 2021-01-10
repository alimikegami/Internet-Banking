/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import static com.ebanking.CustomerMenu.formatRupiah;
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
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 *
 * @author alimi
 */
public class TransactionHistory extends JFrame {
    Connection conn = configDB.configure();
    private final int total = getTransactionDataAmount();
    private String[][] eachTrans;
    private final String accID;
    JPanel historyPanel = new JPanel(new GridBagLayout());
    String splitSave[];
    String userName;
    TransactionHistory(String username, String password, String accID) {
        this.userName = username;
        this.accID = accID;
        this.eachTrans = new String[total][5];
        getTransactionData("SELECT * FROM transfer_history");
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(3, username, password, accID, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(41, 54, 63));
        add(mainPanel, BorderLayout.CENTER);
        // Content Panel
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(800, 600));
        gbc.insets  = new Insets(30, 30, 30, 30);
        mainPanel.add(contentPanel, gbc);
        //contentPanel.setBackground(new Color(41, 54, 63));
        //contentPanel.setBackground(new Color(41, 54, 63));
        createTransPanel();
        
        // Component
        JLabel title = new JLabel("TRANSACTION HISTORY");
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.insets  = new Insets(30, 30, 0, 30);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        //gbc.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(title, gbc);
        
        JScrollPane scroll = new JScrollPane(historyPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        //historyPanel.setBackground(new Color(41, 54, 63));
        scroll.setBorder(createEmptyBorder());
        scroll.setPreferredSize(new Dimension(700, 400));
        scroll.setMinimumSize(new Dimension(700, 400));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.insets  = new Insets(0, 30, 0, 30);
        contentPanel.add(scroll, gbc);
        
        // History panel scroll bar
        createTransPanel();
        
        JPanel orderPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(0, 30, 0, 30);
        gbc2.anchor = GridBagConstraints.LINE_START;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1;
        JLabel order = new JLabel("ORDER BY");
        orderPanel.add(order, gbc2);
        
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        JPanel dateButton = new JPanel(new GridBagLayout());
        dateButton.setBackground(new Color(152, 55, 190));
        dateButton.setPreferredSize(new Dimension(100, 30));
        JLabel date = new JLabel("DATE (Newest)");
        date.setForeground(Color.white);
        dateButton.add(date);
        dateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dateButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                dateButton.setBackground(new Color(127, 30, 165));
                date.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                dateButton.setBackground(new Color(152, 55, 190));
                date.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                dateButton.setBackground(new Color(178, 81, 216));
                date.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                dateButton.setBackground(new Color(127, 30, 165));
                date.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                historyPanel.removeAll();
                historyPanel.revalidate();
                historyPanel.repaint();
                getTransactionData("SELECT * FROM transfer_history ORDER BY time DESC;");
                createTransPanel();
            }
        });
        gbc2.anchor = GridBagConstraints.CENTER;
        orderPanel.add(dateButton, gbc2);
        
        gbc2.gridx = 2;
        gbc2.gridy = 0;
        JPanel amountButton = new JPanel(new GridBagLayout());
        JLabel amount = new JLabel("AMOUNT (DESC)");
        amount.setForeground(Color.white);
        amountButton.add(amount);
        amountButton.setBackground(new Color(152, 55, 190));
        amountButton.setPreferredSize(new Dimension(100, 30));
        amountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        amountButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                amountButton.setBackground(new Color(127, 30, 165));
                amount.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                amountButton.setBackground(new Color(152, 55, 190));
                amount.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                amountButton.setBackground(new Color(178, 81, 216));
                amount.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                amountButton.setBackground(new Color(127, 30, 165));
                amount.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                historyPanel.removeAll();
                historyPanel.revalidate();
                historyPanel.repaint();
                getTransactionData("SELECT * FROM transfer_history ORDER BY amount DESC;");
                createTransPanel();
            }
        });
        orderPanel.add(amountButton, gbc2);
        gbc.insets  = new Insets(30, 30, 30, 30);        
        contentPanel.add(orderPanel, gbc);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private int getTransactionDataAmount(){
        int x = 0;
        int count = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM transfer_history");
            while(rs.next()){
               count++;
            }
        } catch (SQLException e){
            System.out.println("Major fail");
        }
        
        return count;
    }
    
    private void getTransactionData(String query){
        int counter = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
               eachTrans[counter][0] = rs.getString("transferFrom");
               eachTrans[counter][1] = rs.getString("transferTo");
               eachTrans[counter][2] = rs.getString("amount");
               eachTrans[counter][3] = rs.getString("time");
               eachTrans[counter][4] = rs.getString("voucher_code");
               counter++;
            }
        } catch (SQLException e){
            System.out.println("Major fail");
        }
        
    }
    
    private void createTransPanel(){
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
            splitSave = eachTrans[counter][3].split(" ");
            if(eachTrans[counter][0].equals(userName)) {
                historyPanel.add(createPanelTrf(counter, eachTrans, 600, 50), gbc3);
            } else if (eachTrans[counter][1].equals(userName)) {
                historyPanel.add(createPanelRcv(counter, eachTrans, 600, 50), gbc3);
            }
            counter++;
        }
        JLabel dummyLabel = new JLabel(" ");
        gbc3.gridy = counter;
        gbc3.weighty = 1;
        historyPanel.add(dummyLabel, gbc3);
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
        panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(127,30,165)));
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
        JLabel transferTitle;
        if (transferData[counter][4] != null) {
            transferTitle = new JLabel(transferData[counter][1] + " (" + transferData[counter][4] + ")");
        } else {
            transferTitle = new JLabel("Transfered Money To " + transferData[counter][1]);
        }
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
        panel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(127,30,165)));
        //panel.setBorder(new RoundEdgedBorder(new Color(127,30,165)));

        GridBagConstraints gbc = new GridBagConstraints();
       
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(createLabelPlus(), gbc);

        JLabel transferTitle = new JLabel("Received Money From " + transferData[counter][0]);
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
