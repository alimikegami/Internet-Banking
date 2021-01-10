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
public class SteamWalletGiftCard extends JFrame {
    Connection conn = configDB.configure();
    static NumberFormat indonesianCurrency = NumberFormat.getInstance();
    static NumberFormatter kursIndo;
    
    @SuppressWarnings("unchecked")
    public SteamWalletGiftCard(String username, String password, String accID) {
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
        JPanel clickBox7 = new JPanel(new GridBagLayout());
        JPanel clickBox8 = new JPanel(new GridBagLayout());
        JPanel clickBox9 = new JPanel(new GridBagLayout());
        JPanel clickBox10 = new JPanel(new GridBagLayout());
        JPanel returnPanel = new JPanel(new GridBagLayout());
        JLabel returnLabel = new JLabel("Return");
        returnLabel.setForeground(Color.white);
        clickBox1.setBackground(new Color(1, 4, 80));
        clickBox2.setBackground(new Color(1, 4, 80));
        clickBox3.setBackground(new Color(1, 4, 80));
        clickBox4.setBackground(new Color(1, 4, 80));
        clickBox5.setBackground(new Color(1, 4, 80));
        clickBox6.setBackground(new Color(1, 4, 80));
        clickBox7.setBackground(new Color(1, 4, 80));
        clickBox8.setBackground(new Color(1, 4, 80));
        clickBox9.setBackground(new Color(1, 4, 80));
        clickBox10.setBackground(new Color(1, 4, 80));
        returnPanel.setBackground(new Color(152, 55, 190));
        clickBox1.setPreferredSize(new Dimension(150, 100));
        clickBox2.setPreferredSize(new Dimension(150, 100));
        clickBox3.setPreferredSize(new Dimension(150, 100));
        clickBox4.setPreferredSize(new Dimension(150, 100));
        clickBox5.setPreferredSize(new Dimension(150, 100));
        clickBox6.setPreferredSize(new Dimension(150, 100));
        clickBox7.setPreferredSize(new Dimension(150, 100));
        clickBox8.setPreferredSize(new Dimension(150, 100));
        clickBox9.setPreferredSize(new Dimension(150, 100));
        clickBox10.setPreferredSize(new Dimension(150, 100));
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
        gc2.gridwidth = 4;
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
        gc2.gridx = 3;
        gc2.gridy = 1;
        contentPanel.add(clickBox4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 2;
        contentPanel.add(clickBox5, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        contentPanel.add(clickBox6, gc2);
        gc2.gridx = 2;
        gc2.gridy = 2;
        contentPanel.add(clickBox7, gc2);
        gc2.gridx = 3;
        gc2.gridy = 2;
        contentPanel.add(clickBox8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 3;
        contentPanel.add(clickBox9, gc2);
        gc2.gridx = 1;
        gc2.gridy = 3;
        contentPanel.add(clickBox10, gc2);
        gc2.gridx = 0;
        gc2.gridy = 4;
        gc2.gridwidth = 4;
        gc2.weighty = 1;
        contentPanel.add(returnPanel, gc2);
        
        JLabel label1 = new JLabel("IDR 6.000,00");
        JLabel label2 = new JLabel("IDR 8.000,00");
        JLabel label3 = new JLabel("IDR 12.000,00");
        JLabel label4 = new JLabel("IDR 45.000,00");
        JLabel label5 = new JLabel("IDR 60.000,00");
        JLabel label6 = new JLabel("IDR 90.000,00");
        JLabel label7 = new JLabel("IDR 120.000,00");
        JLabel label8 = new JLabel("IDR 250.000,00");
        JLabel label9 = new JLabel("IDR 400.000,00");
        JLabel label10 = new JLabel("IDR 600.000,00");
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);
        label5.setForeground(Color.white);
        label6.setForeground(Color.white);
        label7.setForeground(Color.white);
        label8.setForeground(Color.white);
        label9.setForeground(Color.white);
        label10.setForeground(Color.white);
        ImageIcon img = new ImageIcon(getClass().getResource("/images/steam-logo(small).png"));
        JLabel imageSteam1 = new JLabel(img);
        JLabel imageSteam2 = new JLabel(img);
        JLabel imageSteam3 = new JLabel(img);
        JLabel imageSteam4 = new JLabel(img);
        JLabel imageSteam5 = new JLabel(img);
        JLabel imageSteam6 = new JLabel(img);
        JLabel imageSteam7 = new JLabel(img);
        JLabel imageSteam8 = new JLabel(img);
        JLabel imageSteam9 = new JLabel(img);
        JLabel imageSteam10 = new JLabel(img);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 1;
        gc2.weighty = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox1.add(imageSteam1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox1.add(label1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox2.add(imageSteam2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox2.add(label2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox3.add(imageSteam3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox3.add(label3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox4.add(imageSteam4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox4.add(label4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox5.add(imageSteam5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox5.add(label5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox6.add(imageSteam6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox6.add(label6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox7.add(imageSteam7, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox7.add(label7, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox8.add(imageSteam8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox8.add(label8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox9.add(imageSteam9, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox9.add(label9, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox10.add(imageSteam10, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox10.add(label10, gc2);

        clickBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox1.setBackground(new Color(0, 0, 55));
                label1.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox1.setBackground(new Color(1, 4, 80));
                label1.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox1.setBackground(new Color(78, 81, 157));
                label1.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox1.setBackground(new Color(0, 0, 55));
                label1.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "7100", "Steam Wallet Voucher IDR 6.000,00");
                dispose();
            }
        });
        clickBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox2.setBackground(new Color(0, 0, 55));
                label2.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox2.setBackground(new Color(1, 4, 80));
                label2.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox2.setBackground(new Color(78, 81, 157));
                label2.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox2.setBackground(new Color(0, 0, 55));
                label2.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "9400", "Steam Wallet Voucher IDR 8.000,00");
                dispose();
            }
        });
        clickBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox3.setBackground(new Color(0, 0, 55));
                label3.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox3.setBackground(new Color(1, 4, 80));
                label3.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox3.setBackground(new Color(78, 81, 157));
                label3.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox3.setBackground(new Color(0, 0, 55));
                label3.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "14300", "Steam Wallet Voucher IDR 12.000,00");
                dispose();
            }
        });
        clickBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox4.setBackground(new Color(0, 0, 55));
                label4.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox4.setBackground(new Color(1, 4, 80));
                label4.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox4.setBackground(new Color(78, 81, 157));
                label4.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox4.setBackground(new Color(0, 0, 55));
                label4.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "53500", "Steam Wallet Voucher IDR 45.000,00");
                dispose();
            }
        });
        clickBox5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox5.setBackground(new Color(0, 0, 55));
                label5.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox5.setBackground(new Color(1, 4, 80));
                label5.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox5.setBackground(new Color(78, 81, 157));
                label5.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox5.setBackground(new Color(0, 0, 55));
                label5.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "71000", "Steam Wallet Voucher IDR 60.000,00");
                dispose();
            }
        });
        clickBox6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox6.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox6.setBackground(new Color(0, 0, 55));
                label1.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox6.setBackground(new Color(1, 4, 80));
                label6.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox6.setBackground(new Color(78, 81, 157));
                label6.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox6.setBackground(new Color(0, 0, 55));
                label6.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "107000", "Steam Wallet Voucher IDR 90.000,00");
                dispose();
            }
        });
        clickBox7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox7.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox7.setBackground(new Color(0, 0, 55));
                label7.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox7.setBackground(new Color(1, 4, 80));
                label7.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox7.setBackground(new Color(78, 81, 157));
                label7.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox7.setBackground(new Color(0, 0, 55));
                label7.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "142000", "Steam Wallet Voucher IDR 120.000,00");
                dispose();
            }
        });
        clickBox8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox8.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox8.setBackground(new Color(0, 0, 55));
                label8.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox8.setBackground(new Color(1, 4, 80));
                label8.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox8.setBackground(new Color(78, 81, 157));
                label8.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox8.setBackground(new Color(0, 0, 55));
                label8.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "295000", "Steam Wallet Voucher IDR 250.000,00");
                dispose();
            }
        });
        clickBox9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox9.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox9.setBackground(new Color(0, 0, 55));
                label9.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox9.setBackground(new Color(1, 4, 80));
                label9.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox9.setBackground(new Color(78, 81, 157));
                label9.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox9.setBackground(new Color(0, 0, 55));
                label9.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "475000", "Steam Wallet Voucher IDR 400.000,00");
                dispose();
            }
        });
        clickBox10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox10.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox10.setBackground(new Color(0, 0, 55));
                label10.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox10.setBackground(new Color(1, 4, 80));
                label10.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox10.setBackground(new Color(78, 81, 157));
                label10.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox10.setBackground(new Color(0, 0, 55));
                label10.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "712000", "Steam Wallet Voucher IDR 600.000,00");
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
