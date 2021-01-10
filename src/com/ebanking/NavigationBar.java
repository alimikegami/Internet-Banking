/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
public class NavigationBar extends JFrame {
    public static JPanel createNavBar(int option, String uName, String password, String accId, JFrame obj){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(1200, 80));
        panel.setBackground(new Color(152, 55, 190));
        
        JPanel panelLogo = new JPanel(new GridBagLayout());
        JPanel panelHome = new JPanel(new GridBagLayout());
        JPanel panelTransfer = new JPanel(new GridBagLayout());
        JPanel panelPayBills = new JPanel(new GridBagLayout());
        JPanel panelTransDetails = new JPanel(new GridBagLayout());
        JPanel panelAccDetails = new JPanel(new GridBagLayout());
        JPanel account = new JPanel(new GridBagLayout());
        ImageIcon logOut = new ImageIcon(NavigationBar.class.getResource("/images/arrow.png"));
        JLabel logOutLabel = new JLabel(logOut);
        logOutLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logOutLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if(answer == JOptionPane.YES_OPTION) {
                    Login menulogin = new Login();
                    obj.dispose();
                }
            }
        });
        JLabel home = new JLabel("HOME");
        JLabel transfer = new JLabel("TRANSFER");
        JLabel payBills = new JLabel("PAY BILLS & ENTERTAINMENT");
        JLabel transDetails = new JLabel("TRANSACTION DETAILS");
        JLabel accDetails = new JLabel("ACCOUNT DETAILS");
        
        JLabel acc = new JLabel((uName));
        home.setForeground(Color.white);
        transfer.setForeground(Color.white);
        payBills.setForeground(Color.white);
        transDetails.setForeground(Color.white);
        accDetails.setForeground(Color.white);
        acc.setForeground(Color.white);
        
        panelLogo.setBackground(new Color(152, 55, 190));
        panelHome.setBackground(new Color(152, 55, 190));
        panelTransfer.setBackground(new Color(152, 55, 190));
        panelPayBills.setBackground(new Color(152, 55, 190));
        panelTransDetails.setBackground(new Color(152, 55, 190));
        panelAccDetails.setBackground(new Color(152, 55, 190));
        panel.setBackground(new Color(152, 55, 190));
        account.setBackground(new Color(152, 55, 190));
        
        panelLogo.setPreferredSize(new Dimension(50, 80));
        panelHome.add(home);
        panelHome.setPreferredSize(new Dimension(150, 80));
        panelTransfer.add(transfer);
        panelTransfer.setPreferredSize(new Dimension(150, 80));
        panelPayBills.add(payBills);
        panelPayBills.setPreferredSize(new Dimension(200, 80));
        panelTransDetails.add(transDetails);
        panelTransDetails.setPreferredSize(new Dimension(150, 80));
        panelAccDetails.add(accDetails);
        panelAccDetails.setPreferredSize(new Dimension(150, 80));
        account.setPreferredSize(new Dimension(150, 80));
        GridBagConstraints gbcLogout = new GridBagConstraints();
        gbcLogout.insets = new Insets(0, 0, 0, 20);
        account.add(acc, gbcLogout);
        gbcLogout.insets = new Insets(0, 0, 0, 0);
        account.add(logOutLabel, gbcLogout);
        //account.add(logOutButton);
        
        panelHome.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelHome.setBackground(new Color(127, 30, 165));
                home.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panelHome.setBackground(new Color(152, 55, 190));
                home.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panelHome.setBackground(new Color(178, 81, 216));
                home.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panelHome.setBackground(new Color(127, 30, 165));
                home.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                CustomerMenu obj1 = new CustomerMenu(uName, password, accId);
                obj.dispose();
            }
        });
        
        panelTransfer.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelTransfer.setBackground(new Color(127, 30, 165));
                transfer.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panelTransfer.setBackground(new Color(152, 55, 190));
                transfer.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panelTransfer.setBackground(new Color(178, 81, 216));
                transfer.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panelTransfer.setBackground(new Color(127, 30, 165));
                transfer.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                Transfer obj1 = new Transfer(uName, password, accId);
                obj.dispose();
            }
        });
        
        panelPayBills.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelPayBills.setBackground(new Color(127, 30, 165));
                payBills.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panelPayBills.setBackground(new Color(152, 55, 190));
                payBills.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panelPayBills.setBackground(new Color(178, 81, 216));
                payBills.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panelPayBills.setBackground(new Color(127, 30, 165));
                payBills.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                PayBills obj8 = new PayBills(uName, password, accId);
                obj.dispose();
            }
        });
        
        panelTransDetails.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelTransDetails.setBackground(new Color(127, 30, 165));
                transDetails.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panelTransDetails.setBackground(new Color(152, 55, 190));
                transDetails.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panelTransDetails.setBackground(new Color(178, 81, 216));
                transDetails.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panelTransDetails.setBackground(new Color(127, 30, 165));
                transDetails.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                TransactionHistory obj1 = new TransactionHistory(uName, password, accId);
                obj.dispose();
            }
        });
        
        panelAccDetails.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                panelAccDetails.setBackground(new Color(127, 30, 165));
                accDetails.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseExited(MouseEvent e){
                panelAccDetails.setBackground(new Color(152, 55, 190));
                accDetails.setForeground(Color.white);
            }
            @Override
            public void mousePressed(MouseEvent e){
                panelAccDetails.setBackground(new Color(178, 81, 216));
                accDetails.setForeground(new Color(253, 253, 253));
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panelAccDetails.setBackground(new Color(127, 30, 165));
                accDetails.setForeground(new Color(202, 202, 202));
            }
            @Override
            public void mouseClicked(MouseEvent e){
                AccountDetails obj1 = new AccountDetails(uName, password);
                obj.dispose();
            }
        });
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(panelLogo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(panelHome, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(panelTransfer, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(panelPayBills, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(panelTransDetails, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        panel.add(panelAccDetails, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panel.add(account, gbc);

        switch(option){
            case 1:
                panelTransfer.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
                break;
            case 2:
                panelPayBills.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
                break;
            case 3:
                panelTransDetails.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
                break;
            case 4:
                panelAccDetails.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
                break;
        }
        
        return panel;    
    }
}
