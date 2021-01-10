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
import javax.swing.text.NumberFormatter;

/**
 *
 * @author alimi
 */
public class GooglePlay extends JFrame {
    Connection conn = configDB.configure();
    static NumberFormat indonesianCurrency = NumberFormat.getInstance();
    static NumberFormatter kursIndo;
    
    @SuppressWarnings("unchecked")
    public GooglePlay(String username, String password, String accID) {
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(2, username, password, accID, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(new Color(41, 54, 63));
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        // Content Panel
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(550, 500));
        contentPanel.setBackground(new Color(41, 54, 63));
        gbc.insets  = new Insets(30, 30, 30, 30);
        mainPanel.add(contentPanel, gbc);
        
        // Component
        JPanel clickBox1 = new JPanel(new GridBagLayout());
        JPanel clickBox2 = new JPanel(new GridBagLayout());
        JPanel clickBox3 = new JPanel(new GridBagLayout());
        JPanel clickBox4 = new JPanel(new GridBagLayout());
        JPanel clickBox5 = new JPanel(new GridBagLayout());
        JPanel clickBox6 = new JPanel(new GridBagLayout());
        JPanel clickBox7 = new JPanel(new GridBagLayout());
        JPanel clickBox8 = new JPanel(new GridBagLayout());
        JPanel returnPanel = new JPanel(new GridBagLayout());
        JLabel returnLabel = new JLabel("Return");
        returnLabel.setForeground(Color.white);
        clickBox1.setBackground(Color.WHITE);
        clickBox2.setBackground(Color.WHITE);
        clickBox3.setBackground(Color.WHITE);
        clickBox4.setBackground(Color.WHITE);
        clickBox5.setBackground(Color.WHITE);
        clickBox6.setBackground(Color.WHITE);
        clickBox7.setBackground(Color.WHITE);
        clickBox8.setBackground(Color.WHITE);
        returnPanel.setBackground(new Color(152, 55, 190));
        clickBox1.setPreferredSize(new Dimension(150, 100));
        clickBox2.setPreferredSize(new Dimension(150, 100));
        clickBox3.setPreferredSize(new Dimension(150, 100));
        clickBox4.setPreferredSize(new Dimension(150, 100));
        clickBox5.setPreferredSize(new Dimension(150, 100));
        clickBox6.setPreferredSize(new Dimension(150, 100));
        clickBox7.setPreferredSize(new Dimension(150, 100));
        clickBox8.setPreferredSize(new Dimension(150, 100));
        returnPanel.setPreferredSize(new Dimension(150, 25));
        returnPanel.add(returnLabel);
        returnPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
                PayBills bayarBill = new PayBills(username, password, accID);
                dispose();
            }
        });
        JLabel title = new JLabel("Select Voucher");
        title.setForeground(Color.white);
        GridBagConstraints gc2 = new GridBagConstraints();
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 3;
        gc2.weighty = 1;
        contentPanel.add(title, gc2);
        gc2.gridwidth = 1;
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.weightx = 0.33;
        gc2.weighty = 0.5;
        contentPanel.add(clickBox1, gc2);
        gc2.gridx = 1;
        gc2.gridy = 1;
        contentPanel.add(clickBox2, gc2);
        gc2.gridx = 2;
        gc2.gridy = 1;
        contentPanel.add(clickBox3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 2;
        contentPanel.add(clickBox4, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        contentPanel.add(clickBox5, gc2);
        gc2.gridx = 2;
        gc2.gridy = 2;
        contentPanel.add(clickBox6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 3;
        contentPanel.add(clickBox7, gc2);
        gc2.gridx = 1;
        gc2.gridy = 3;
        contentPanel.add(clickBox8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 4;
        gc2.gridwidth = 3;
        gc2.weighty = 1;
        contentPanel.add(returnPanel, gc2);
        
        JLabel label1 = new JLabel("IDR 5.000,00");
        JLabel label2 = new JLabel("IDR 10.000,00");
        JLabel label3 = new JLabel("IDR 20.000,00");
        JLabel label4 = new JLabel("IDR 50.000,00");
        JLabel label5 = new JLabel("IDR 100.000,00");
        JLabel label6 = new JLabel("IDR 150.000,00");
        JLabel label7 = new JLabel("IDR 300.000,00");
        JLabel label8 = new JLabel("IDR 500.000,00");
        
        ImageIcon img = new ImageIcon(getClass().getResource("/images/smallgoogleplay.png"));
        JLabel imageGooglePlayLabel1 = new JLabel(img);
        JLabel imageGooglePlayLabel2 = new JLabel(img);
        JLabel imageGooglePlayLabel3 = new JLabel(img);
        JLabel imageGooglePlayLabel4 = new JLabel(img);
        JLabel imageGooglePlayLabel5 = new JLabel(img);
        JLabel imageGooglePlayLabel6 = new JLabel(img);
        JLabel imageGooglePlayLabel7 = new JLabel(img);
        JLabel imageGooglePlayLabel8 = new JLabel(img);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 1;
        gc2.weighty = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox1.add(imageGooglePlayLabel1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox1.add(label1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox2.add(imageGooglePlayLabel2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox2.add(label2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox3.add(imageGooglePlayLabel3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox3.add(label3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox4.add(imageGooglePlayLabel4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox4.add(label4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox5.add(imageGooglePlayLabel5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox5.add(label5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox6.add(imageGooglePlayLabel6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox6.add(label6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox7.add(imageGooglePlayLabel7, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox7.add(label7, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox8.add(imageGooglePlayLabel8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox8.add(label8, gc2);

        clickBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox1.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox1.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox1.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox1.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "5000", "Google Play Voucher IDR 5.000,00");
                dispose();
            }
        });
        clickBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox2.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox2.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox2.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox2.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "10000", "Google Play Voucher IDR 10.000,00");
                dispose();
            }
        });
        clickBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox3.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox3.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox3.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox3.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "20000", "Google Play Voucher IDR 20.000,00");
                dispose();
            }
            
        });
        clickBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox4.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox4.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox4.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox4.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "50000", "Google Play Voucher IDR 50.000,00");
                dispose();
            }
        });
        clickBox5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox5.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox5.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox5.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox5.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "100000", "Google Play Voucher IDR 100.000,00");
                dispose();
            }
        });
        clickBox6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox6.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox6.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox6.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox6.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox6.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "150000", "Google Play Voucher IDR 150.000,00");
                dispose();
            }
        });
        clickBox7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox7.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox7.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox7.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox7.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox7.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "300000", "Google Play Voucher IDR 300.000,00");
                dispose();
            }
        });
        clickBox8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox8.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox8.setBackground(new Color(204, 204, 204));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox8.setBackground(Color.WHITE);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox8.setBackground(Color.white);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox8.setBackground(new Color(204, 204, 204));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "500000", "Google Play Voucher IDR 500.000,00");
                dispose();
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
