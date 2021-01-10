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
public class Pulsa extends JFrame {
    Connection conn = configDB.configure();
    String username, password, accID;
    @SuppressWarnings("unchecked")
    public Pulsa(String username, String password, String accID) {
        this.username = username;
        this.password = password;
        this.accID = accID;
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
        JPanel returnPanel = new JPanel(new GridBagLayout());
        JLabel returnLabel = new JLabel("Return");
        returnLabel.setForeground(Color.white);
        clickBox1.setBackground(new Color(152, 55, 190));
        clickBox2.setBackground(new Color(152, 55, 190));
        clickBox3.setBackground(new Color(152, 55, 190));
        clickBox4.setBackground(new Color(152, 55, 190));
        clickBox5.setBackground(new Color(152, 55, 190));
        returnPanel.setBackground(new Color(152, 55, 190));
        clickBox1.setPreferredSize(new Dimension(150, 100));
        clickBox2.setPreferredSize(new Dimension(150, 100));
        clickBox3.setPreferredSize(new Dimension(150, 100));
        clickBox4.setPreferredSize(new Dimension(150, 100));
        clickBox5.setPreferredSize(new Dimension(150, 100));
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
        JLabel title = new JLabel("Select Provider");
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
        gc2.gridx = 0;
        gc2.gridy = 4;
        gc2.gridwidth = 3;
        gc2.weighty = 1;
        contentPanel.add(returnPanel, gc2);
        
        JLabel label1 = new JLabel("Telkomsel");
        JLabel label2 = new JLabel("Indosat");
        JLabel label3 = new JLabel("XL");
        JLabel label4 = new JLabel("Smartfren");
        JLabel label5 = new JLabel("3");
        label1.setForeground(Color.white);
        label2.setForeground(Color.white);
        label3.setForeground(Color.white);
        label4.setForeground(Color.white);
        label5.setForeground(Color.white);
        ImageIcon imgTelkom = new ImageIcon(getClass().getResource("/images/logo-telkom.png"));
        ImageIcon imgIndosat = new ImageIcon(getClass().getResource("/images/indosat.png"));
        ImageIcon img3 = new ImageIcon(getClass().getResource("/images/xl.png"));
        ImageIcon imgSmartfren = new ImageIcon(getClass().getResource("/images/smartfren.png"));
        ImageIcon imgXl = new ImageIcon(getClass().getResource("/images/3.png"));
        JLabel imageProvider1 = new JLabel(imgTelkom);
        JLabel imageProvider2 = new JLabel(imgIndosat);
        JLabel imageProvider3 = new JLabel(img3);
        JLabel imageProvider4 = new JLabel(imgSmartfren);
        JLabel imageProvider5 = new JLabel(imgXl);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 1;
        gc2.weighty = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox1.add(imageProvider1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox1.add(label1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox2.add(imageProvider2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox2.add(label2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox3.add(imageProvider3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox3.add(label3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox4.add(imageProvider4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox4.add(label4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox5.add(imageProvider5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox5.add(label5, gc2);

        clickBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox1.setBackground(new Color(127, 30, 165));
                label1.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox1.setBackground(new Color(152, 55, 190));
                label1.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox1.setBackground(new Color(178, 81, 216));
                label1.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox1.setBackground(new Color(127, 30, 165));
                label1.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                pulsaAmountPanel(contentPanel, "Telkomsel");
            }
        });
        clickBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox2.setBackground(new Color(127, 30, 165));
                label2.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox2.setBackground(new Color(152, 55, 190));
                label2.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox2.setBackground(new Color(178, 81, 216));
                label2.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox2.setBackground(new Color(127, 30, 165));
                label2.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                pulsaAmountPanel(contentPanel, "Indosat");
            }
        });
        clickBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox3.setBackground(new Color(127, 30, 165));
                label3.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox3.setBackground(new Color(152, 55, 190));
                label3.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox3.setBackground(new Color(178, 81, 216));
                label3.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox3.setBackground(new Color(127, 30, 165));
                label3.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                pulsaAmountPanel(contentPanel, "XL");
            }
        });
        clickBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox4.setBackground(new Color(127, 30, 165));
                label4.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox4.setBackground(new Color(152, 55, 190));
                label4.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox4.setBackground(new Color(178, 81, 216));
                label4.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox4.setBackground(new Color(127, 30, 165));
                label4.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                pulsaAmountPanel(contentPanel, "Smartfren");
            }
        });
        clickBox5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox5.setBackground(new Color(127, 30, 165));
                label5.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox5.setBackground(new Color(152, 55, 190));
                label5.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox5.setBackground(new Color(178, 81, 216));
                label5.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox5.setBackground(new Color(127, 30, 165));
                label5.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                pulsaAmountPanel(contentPanel, "3");
            }
        });
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void pulsaAmountPanel(JPanel contentPanel, String providerPilihan) {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        contentPanel.setPreferredSize(new Dimension(700, 500));
        JLabel amount1 = new JLabel("IDR 5.000");
        JLabel amount2 = new JLabel("IDR 10.000");
        JLabel amount3 = new JLabel("IDR 15.000");
        JLabel amount4 = new JLabel("IDR 20.000");
        JLabel amount5 = new JLabel("IDR 25.000");
        JLabel amount6 = new JLabel("IDR 30.000");
        JLabel amount7 = new JLabel("IDR 40.000");
        JLabel amount8 = new JLabel("IDR 50.000");
        JLabel amount9 = new JLabel("IDR 75.000");
        JLabel amount10 = new JLabel("IDR 100.000");
        amount1.setForeground(Color.white);
        amount2.setForeground(Color.white);
        amount3.setForeground(Color.white);
        amount4.setForeground(Color.white);
        amount5.setForeground(Color.white);
        amount6.setForeground(Color.white);
        amount7.setForeground(Color.white);
        amount8.setForeground(Color.white);
        amount9.setForeground(Color.white);
        amount10.setForeground(Color.white);
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
        clickBox1.setBackground(new Color(152, 55, 190));
        clickBox2.setBackground(new Color(152, 55, 190));
        clickBox3.setBackground(new Color(152, 55, 190));
        clickBox4.setBackground(new Color(152, 55, 190));
        clickBox5.setBackground(new Color(152, 55, 190));
        clickBox6.setBackground(new Color(152, 55, 190));
        clickBox7.setBackground(new Color(152, 55, 190));
        clickBox8.setBackground(new Color(152, 55, 190));
        clickBox9.setBackground(new Color(152, 55, 190));
        clickBox10.setBackground(new Color(152, 55, 190));
        returnPanel.setBackground(new Color(152, 55, 190));
        clickBox1.setPreferredSize(new Dimension(130, 100));
        clickBox2.setPreferredSize(new Dimension(130, 100));
        clickBox3.setPreferredSize(new Dimension(130, 100));
        clickBox4.setPreferredSize(new Dimension(130, 100));
        clickBox5.setPreferredSize(new Dimension(130, 100));
        clickBox6.setPreferredSize(new Dimension(130, 100));
        clickBox7.setPreferredSize(new Dimension(130, 100));
        clickBox8.setPreferredSize(new Dimension(130, 100));
        clickBox9.setPreferredSize(new Dimension(130, 100));
        clickBox10.setPreferredSize(new Dimension(130, 100));
        ImageIcon img1 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img2 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img3 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img4 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img5 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img6 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img7 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img8 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img9 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        ImageIcon img10 = new ImageIcon(getClass().getResource("/images/smartphonesmall.png"));
        JLabel labelBox1 = new JLabel(img1);
        JLabel labelBox2 = new JLabel(img2);
        JLabel labelBox3 = new JLabel(img3);
        JLabel labelBox4 = new JLabel(img4);
        JLabel labelBox5 = new JLabel(img5);
        JLabel labelBox6 = new JLabel(img6);
        JLabel labelBox7 = new JLabel(img7);
        JLabel labelBox8 = new JLabel(img8);
        JLabel labelBox9 = new JLabel(img9);
        JLabel labelBox10 = new JLabel(img10);
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
        JLabel title = new JLabel("Select Amount");
        title.setForeground(Color.white);
        GridBagConstraints gc2 = new GridBagConstraints();
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 5;
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
        gc2.gridx = 4;
        gc2.gridy = 1;
        contentPanel.add(clickBox5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 2;
        contentPanel.add(clickBox6, gc2);
        gc2.gridx = 1;
        gc2.gridy = 2;
        contentPanel.add(clickBox7, gc2);
        gc2.gridx = 2;
        gc2.gridy = 2;
        contentPanel.add(clickBox8, gc2);
        gc2.gridx = 3;
        gc2.gridy = 2;
        contentPanel.add(clickBox9, gc2);
        gc2.gridx = 4;
        gc2.gridy = 2;
        contentPanel.add(clickBox10, gc2);
        gc2.gridx = 0;
        gc2.gridy = 3;
        gc2.gridwidth = 5;
        gc2.weighty = 1;
        contentPanel.add(returnPanel, gc2);
        
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.gridwidth = 1;
        gc2.weighty = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox1.add(labelBox1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox1.add(amount1, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox2.add(labelBox2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox2.add(amount2, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox3.add(labelBox3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox3.add(amount3, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox4.add(labelBox4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox4.add(amount4, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox5.add(labelBox5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox5.add(amount5, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox6.add(labelBox6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox6.add(amount6, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox7.add(labelBox7, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox7.add(amount7, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox8.add(labelBox8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox8.add(amount8, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox9.add(labelBox9, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox9.add(amount9, gc2);
        gc2.gridx = 0;
        gc2.gridy = 0;
        gc2.insets = new Insets(0, 0, 13, 0);
        clickBox10.add(labelBox10, gc2);
        gc2.gridx = 0;
        gc2.gridy = 1;
        gc2.insets = new Insets(0, 0, 0, 0);
        clickBox10.add(amount10, gc2);
        clickBox1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox1.setBackground(new Color(127, 30, 165));
                labelBox1.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox1.setBackground(new Color(152, 55, 190));
                labelBox1.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox1.setBackground(new Color(178, 81, 216));
                labelBox1.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox1.setBackground(new Color(127, 30, 165));
                labelBox1.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "7000", "Pulsa " + providerPilihan + " IDR 5.000");
                dispose();
            }
        });
        clickBox2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox2.setBackground(new Color(127, 30, 165));
                labelBox2.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox2.setBackground(new Color(152, 55, 190));
                labelBox2.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox2.setBackground(new Color(178, 81, 216));
                labelBox2.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox2.setBackground(new Color(127, 30, 165));
                labelBox2.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "12000", "Pulsa " + providerPilihan + " IDR 10.000");
                dispose();
            }
        });
        clickBox3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox3.setBackground(new Color(127, 30, 165));
                labelBox3.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox3.setBackground(new Color(152, 55, 190));
                labelBox3.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox3.setBackground(new Color(178, 81, 216));
                labelBox3.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox3.setBackground(new Color(127, 30, 165));
                labelBox3.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "17000", "Pulsa " + providerPilihan + " IDR 15.000");
                dispose();
            }
        });
        clickBox4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox4.setBackground(new Color(127, 30, 165));
                labelBox4.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox4.setBackground(new Color(152, 55, 190));
                labelBox4.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox4.setBackground(new Color(178, 81, 216));
                labelBox4.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox4.setBackground(new Color(127, 30, 165));
                labelBox4.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "22000", "Pulsa " + providerPilihan + " IDR 20.000");
                dispose();
            }
        });
        clickBox5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox5.setBackground(new Color(127, 30, 165));
                labelBox5.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox5.setBackground(new Color(152, 55, 190));
                labelBox5.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox5.setBackground(new Color(178, 81, 216));
                labelBox5.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox5.setBackground(new Color(127, 30, 165));
                labelBox5.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "27000", "Pulsa " + providerPilihan + " IDR 25.000");
                dispose();
            }
        });
        clickBox6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox6.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox6.setBackground(new Color(127, 30, 165));
                labelBox6.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox6.setBackground(new Color(152, 55, 190));
                labelBox6.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox6.setBackground(new Color(178, 81, 216));
                labelBox6.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox6.setBackground(new Color(127, 30, 165));
                labelBox6.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "32000", "Pulsa " + providerPilihan + " IDR 30.000");
                dispose();
            }
        });
        clickBox7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox7.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox7.setBackground(new Color(127, 30, 165));
                labelBox7.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox7.setBackground(new Color(152, 55, 190));
                labelBox7.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox7.setBackground(new Color(178, 81, 216));
                labelBox7.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox7.setBackground(new Color(127, 30, 165));
                labelBox7.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "42000", "Pulsa " + providerPilihan + " IDR 40.000");
                dispose();
            }
        });
        
        clickBox8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox8.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox8.setBackground(new Color(127, 30, 165));
                labelBox8.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox8.setBackground(new Color(152, 55, 190));
                labelBox8.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox8.setBackground(new Color(178, 81, 216));
                labelBox8.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox8.setBackground(new Color(127, 30, 165));
                labelBox8.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "52000", "Pulsa " + providerPilihan + " IDR 50.000");
                dispose();
            }
        });
        
        clickBox9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox9.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox9.setBackground(new Color(127, 30, 165));
                labelBox9.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox9.setBackground(new Color(152, 55, 190));
                labelBox9.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox9.setBackground(new Color(178, 81, 216));
                labelBox9.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox9.setBackground(new Color(127, 30, 165));
                labelBox9.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "77000", "Pulsa " + providerPilihan + " IDR 75.000");
                dispose();
            }
        });
        clickBox10.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clickBox10.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                clickBox10.setBackground(new Color(127, 30, 165));
                labelBox10.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                clickBox10.setBackground(new Color(152, 55, 190));
                labelBox10.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                clickBox10.setBackground(new Color(178, 81, 216));
                labelBox10.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                clickBox10.setBackground(new Color(127, 30, 165));
                labelBox10.setForeground(new Color(202, 202, 202));
            }
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                PayAmount bayar = new PayAmount(username, password, accID, "102000", "Pulsa " + providerPilihan + " IDR 100.000");
                dispose();
            }
        });
        
    }
}