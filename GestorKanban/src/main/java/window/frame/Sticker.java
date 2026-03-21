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
public class Sticker extends JPanel{

    Color stickerColor;

    StickerMenu stickerMenu;

    public Sticker() {
        stickerColor = new Color(100,200,200);

        this.setPreferredSize(new Dimension(250,250));
        this.setBackground(stickerColor);
        this.setLayout(new BorderLayout());

        stickerMenu = new StickerMenu(stickerColor);
        this.add(stickerMenu, BorderLayout.SOUTH);
    }

}

class StickerTitle extends JPanel{
    
}

class StickerMenu extends JPanel{
    private final static String deleteString = new String(Character.toChars(0x1F5D1));
    private final static String editString = new String(Character.toChars(0x1F589));
    
    JButton deleteButton;
    JButton editlButton;

    public StickerMenu(Color stickerColor){
        this.setLayout(new FlowLayout());
        this.setBackground(stickerColor);
        
        buildButtons();

        add(editlButton);
        add(deleteButton);
    }


    private void buildButtons(){
        editlButton = new JButton(editString);
        formatButton(editlButton);
        deleteButton = new JButton(deleteString);
        formatButton(deleteButton);
    }

    private void formatButton(JButton button){
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 35));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }
}