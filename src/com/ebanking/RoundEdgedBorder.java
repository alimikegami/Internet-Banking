/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Graphics;
public class RoundEdgedBorder extends LineBorder{
    int arcWidth=35,arcHeight=35;
    Color fillColor;
    public RoundEdgedBorder(Color fillColor) {
        super(Color.red);
        this.fillColor = fillColor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        g.setColor(fillColor);
        g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }
}
