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
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author alimi
 */
public class ChangePassword extends JFrame {
    static Connection conn = configDB.configure();
    static String name, nik, accId, address, district, regency, province, gender, email, validation, id;
    
    public ChangePassword(String username, String password){
        getAccountDetails(username, password);
        setLayout(new BorderLayout());
        // Main panel
        JPanel mainPanel = new JPanel(new GridBagLayout()){
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
        
        add(mainPanel);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);
        mainPanel.add(contentPanel, gbc);
        contentPanel.setPreferredSize(new Dimension(400, 250));
        // Label
        JLabel title = new JLabel("Change Your Password");
        JLabel oldPass = new JLabel("Old Password");
        JLabel newPass = new JLabel("New Password");
        JLabel confirmNewPass = new JLabel("Confirm New Password");
        
        // Text field
        JPasswordField tf1 = new JPasswordField (20);
        JPasswordField  tf2 = new JPasswordField (20);
        JPasswordField  tf3 = new JPasswordField (20);
        
        // Add label and text field
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(15, 10, 15, 10);
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.gridwidth = 2;
        contentPanel.add(title, gbc2);
        
        gbc2.gridwidth = 1;
        gbc2.insets = new Insets(10, 10, 10, 5);
        gbc2.anchor = GridBagConstraints.LINE_START;
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        contentPanel.add(oldPass, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(10, 5, 10, 10);
        gbc2.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(tf1, gbc2);
        
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.insets = new Insets(10, 10, 10, 5);
        gbc2.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(newPass, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        gbc2.insets = new Insets(10, 5, 10, 10);
        gbc2.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(tf2, gbc2);
        
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.insets = new Insets(10, 10, 10, 5);
        gbc2.anchor = GridBagConstraints.LINE_START;
        contentPanel.add(confirmNewPass, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 3;
        gbc2.insets = new Insets(10, 5, 10, 10);
        gbc2.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(tf3, gbc2);
        
        // Status
        JPanel statusBar = new JPanel(new GridBagLayout());
        JLabel status = new JLabel(" ");
        statusBar.add(status);
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = 4;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(5, 10, 5, 10);
        contentPanel.add(statusBar, gbc2);
        
        // Button
        JPanel confirm = new JPanel(new GridBagLayout());
        JLabel confirmLabel = new JLabel("Change Password");
        confirmLabel.setForeground(Color.white);
        confirm.add(confirmLabel);
        confirm.setBackground(new Color(152, 55, 190));
        confirm.setPreferredSize(new Dimension(130, 30));
        confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                confirm.setBackground(new Color(127, 30, 165));
                confirmLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                confirm.setBackground(new Color(152, 55, 190));
                confirmLabel.setForeground(Color.white);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                confirm.setBackground(new Color(178, 81, 216));
                confirmLabel.setForeground(new Color(253, 253, 253));
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                confirm.setBackground(new Color(127, 30, 165));
                confirmLabel.setForeground(new Color(202, 202, 202));
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                String oldPassword = String.valueOf(tf1.getPassword());
                String newPassword = String.valueOf(tf2.getPassword());
                String confirmNewPassword = String.valueOf(tf3.getPassword());
                try {
                    Statement st = conn.createStatement();
                    if(oldPassword.equals(password)){
                        if(newPassword.equals(confirmNewPassword)){
                            int konfirmEdit = JOptionPane.showOptionDialog(null, "Are you sure you want to change your password?", "Change Password?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                            if (konfirmEdit == JOptionPane.YES_OPTION) {
                                String query = "UPDATE customer_account SET password = '" + confirmNewPassword + "' WHERE account_id = " + accId + ";";
                                st.execute(query);
                                status.setText("Your password is succesfully changed!");
                                status.setForeground(new Color(21, 183, 24));
                                tf1.setText("");
                                tf2.setText("");
                                tf3.setText("");
                            }
                        } else {
                            System.out.println("sadasdasdasdasdas");
                            status.setText("Please make sure both password match!");
                            status.setForeground(Color.red);
                            tf2.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                            tf3.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                        }
                    } else {
                        System.out.println("asdasdasdadasda");
                        status.setText("Your old password is incorrect!");
                        status.setForeground(Color.red);
                        tf1.setBorder(BorderFactory.createLineBorder(new Color(248, 114, 125),2));
                    }
                } catch (SQLException ex){
                    System.out.println("asdasdasdasd");
                }
            }
        });
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.weighty = 1;
        gbc2.gridx = 0;
        gbc2.gridy = 5;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(10, 10, 10, 10);
        contentPanel.add(confirm, gbc2);
        
        // Utitlity
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocationRelativeTo(null);
    }
    
    public static void getAccountDetails(String username, String password) {
        
        try {
            
            Statement st = conn.createStatement();
            String qr1 = "SELECT * FROM customer_account";
            String qr2 = "SELECT * FROM customer";
            String search = "SELECT customer_id FROM customer_account WHERE (username = '" + username + "') AND (password = '" + password + "');";
            ResultSet rs, rs2, rs3;
            rs = st.executeQuery(qr1);
            while(rs.next()){
                if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                    id = rs.getString("customer_id");    
                    accId = rs.getString("account_id");
                    validation = rs.getString("validation");
                }

            }

            rs2 = st.executeQuery(qr2);
            while(rs2.next()){
                if(rs2.getString("customer_id").equals(id)){
                    if(rs2.getString("middle_name") == null){
                        name = rs2.getString("first_name") + " " + rs2.getString("last_name");
                    } else {
                        name = rs2.getString("first_name") + " " + rs2.getString("middle_name") + " " + rs2.getString("last_name");
                    }
                    nik = rs2.getString("NIK");
                    address = rs2.getString("street_address");
                    district = rs2.getString("sub_district");
                    regency = rs2.getString("regency");
                    province = rs2.getString("province");
                    gender = rs2.getString("gender");
                    email = rs2.getString("email");
                }
            }
            System.out.println(id);
            System.out.println("SUKSESSSS");
            
        } catch (SQLException e) {
            
            System.out.println("agasdasdaskdas");
            
        }
    }
}
