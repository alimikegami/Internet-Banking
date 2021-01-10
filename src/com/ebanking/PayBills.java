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

public class PayBills extends JFrame {
    Connection conn = configDB.configure();
    static NumberFormat indonesianCurrency = NumberFormat.getInstance();
    static NumberFormatter kursIndo;
    
    @SuppressWarnings("unchecked")
    public PayBills(String username, String password, String accID) {
        setLayout(new BorderLayout());
        add(NavigationBar.createNavBar(2, username, password, accID, this), BorderLayout.NORTH);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBackground(new Color(41, 54, 63));
        mainPanel.setPreferredSize(new Dimension(1200, 600));
        // Content Panel
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(700, 500));
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
        ImageIcon image1 = new ImageIcon(getClass().getResource("/images/energy.png"));
        JLabel label1 = new JLabel(image1);
        ImageIcon image2 = new ImageIcon(getClass().getResource("/images/smartphone.png"));
        JLabel label2 = new JLabel(image2);
        ImageIcon image3 = new ImageIcon(getClass().getResource("/images/google-play.png"));
        JLabel label3 = new JLabel(image3);
        ImageIcon image4 = new ImageIcon(getClass().getResource("/images/apple.png"));
        JLabel label4 = new JLabel(image4);
        ImageIcon image5 = new ImageIcon(getClass().getResource("/images/steam-logo.png"));
        JLabel label5 = new JLabel(image5);
        ImageIcon image6 = new ImageIcon(getClass().getResource("/images/netflix.png"));
        JLabel label6 = new JLabel(image6);
        
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        clickBox1.add(label1, gbc1);
        clickBox2.add(label2, gbc1);
        clickBox3.add(label3, gbc1);
        clickBox4.add(label4, gbc1);
        clickBox5.add(label5, gbc1);
        clickBox6.add(label6, gbc1);
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        JLabel label7 = new JLabel("Electricity Token");
        JLabel label8 = new JLabel("Pulsa");
        JLabel label9 = new JLabel("Google Play");
        JLabel label10 = new JLabel("Apple Gift Card");
        JLabel label11 = new JLabel("Steam Gift Card");
        JLabel label12 = new JLabel("Netflix Gift Card");
        
        label7.setForeground(Color.white);
        label8.setForeground(Color.white);
        label9.setForeground(Color.white);
        label10.setForeground(Color.white);
        label11.setForeground(Color.white);
        label12.setForeground(Color.white);
        
        gbc1.insets = new Insets(20, 0, 0, 0);
        clickBox1.add(label7, gbc1);
        clickBox2.add(label8, gbc1);
        clickBox3.add(label9, gbc1);
        clickBox4.add(label10, gbc1);
        clickBox5.add(label11, gbc1);
        clickBox6.add(label12, gbc1);
        
        clickBox1.setBackground(new Color(41, 54, 63));
        clickBox2.setBackground(new Color(41, 54, 63));
        clickBox3.setBackground(new Color(41, 54, 63));
        clickBox4.setBackground(new Color(41, 54, 63));
        clickBox5.setBackground(new Color(41, 54, 63));
        clickBox6.setBackground(new Color(41, 54, 63));
        clickBox1.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox2.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox3.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox4.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox5.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox6.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
        clickBox1.setPreferredSize(new Dimension(200, 200));
        clickBox2.setPreferredSize(new Dimension(200, 200));
        clickBox3.setPreferredSize(new Dimension(200, 200));
        clickBox4.setPreferredSize(new Dimension(200, 200));
        clickBox5.setPreferredSize(new Dimension(200, 200));
        clickBox6.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints gc2 = new GridBagConstraints();
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.weightx = 0.33;
        gc2.weighty = 0.5;
        contentPanel.add(clickBox1, gc2);
        
        gc2.gridx = 1;
        gc2.gridy = 0;
        contentPanel.add(clickBox2, gc2);
        
        gc2.gridx = 2;
        gc2.gridy = 0;
        contentPanel.add(clickBox3, gc2);
        
        gc2.gridx = 0;
        gc2.gridy = 1;
        contentPanel.add(clickBox4, gc2);
        
        gc2.gridx = 1;
        gc2.gridy = 1;
        contentPanel.add(clickBox5, gc2);
        
        gc2.gridx = 2;
        gc2.gridy = 1;
        contentPanel.add(clickBox6, gc2);
        
        clickBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label7.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label7.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label7.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox1.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label7.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                ElectricityToken beliTokenListrik = new ElectricityToken(username, password, accID);
                dispose();
            }
        });
        clickBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label8.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label8.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label8.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox2.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label8.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                Pulsa beliPulsa = new Pulsa(username, password, accID);
                dispose();
            }
        });
        clickBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label9.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label9.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label9.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox3.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label9.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                GooglePlay beliGooglePlay = new GooglePlay(username, password, accID);
                dispose();
            }
            
        });
        clickBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label10.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label10.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label10.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox4.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label10.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                AppleGiftCard beliAppleGiftCard = new AppleGiftCard(username, password, accID);
                dispose();
            }
        });
        clickBox5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox5.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label11.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox5.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label11.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox5.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label11.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox5.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label11.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                SteamWalletGiftCard beliSteamWallet = new SteamWalletGiftCard(username, password, accID);
                dispose();
            }
        });
        clickBox6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox6.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox6.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label12.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox6.setBorder(new RoundEdgedBorder(new Color(152, 55, 190)));
                label12.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox6.setBorder(new RoundEdgedBorder(new Color(178, 81, 216)));
                label12.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox6.setBorder(new RoundEdgedBorder(new Color(127, 30, 165)));
                label12.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                NetflixGiftCard beliNetflixGiftCard = new NetflixGiftCard(username, password, accID);
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
