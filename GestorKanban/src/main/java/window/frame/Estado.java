/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package window.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CasaSFT
 */
public class Estado extends JPanel {
    private FlowLayout estadoLayout;
    private Color estadoBackgroundColor;
    //private List<Sticker> listArray; 
    
    public Estado(){
        estadoLayout = new FlowLayout();
        estadoBackgroundColor = new Color(200,100,100);
        
        this.setPreferredSize(new Dimension(300,950));
        //this.setBackground(estadoBackgroundColor);
        this.setLayout(estadoLayout);
        this.setBorder(BorderFactory.createEtchedBorder());
        
    }
    
}
