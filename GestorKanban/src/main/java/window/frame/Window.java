/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package window.frame;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author CasaSFT
 */


//maini JFRAME
public class Window extends JFrame{
    public Window(){
        super("Sticker Teste");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }
}

