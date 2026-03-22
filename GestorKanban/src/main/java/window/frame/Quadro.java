/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package window.frame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author bernardos
 */
public class Quadro extends JPanel{
    
    
    public Quadro () {
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        this.setLayout(new BorderLayout());
        
        
        add(new QuadroCentral(), BorderLayout.CENTER);
        
    }
    
}

class QuadroCentral extends JPanel{

    QuadroCentral(){
        // 1. Set the layout manager
        this.setLayout(new GridBagLayout());

        // 2. Create the constraints object
        GridBagConstraints gbc = new GridBagConstraints();

        // 3. Give the component 100% of the extra space (Weight)
        gbc.weightx = 1.0; // horizontal space
        gbc.weighty = 1.0; // vertical space

        // 4. Tell it to stretch in both directions (Fill)
        gbc.fill = GridBagConstraints.BOTH;
        this.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        
        
        add(new FlowLayoutCenter());
        //this.setLayout(new FlowLayout());
        //add(new Sticker());
        //add(new Sticker());

    }
    
public class FlowLayoutCenter extends JFrame{
    
    public FlowLayoutCenter () {
        
    this.setLayout(new GridLayout(1, 2, 5, 0)); 
    add(new Sticker());
    add(new Sticker());
    }
}
}