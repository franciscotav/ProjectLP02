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
 * @author CasaSFT
 */
public class Sticker extends JPanel{

    private Color stickerColor;

    private StickerMenu stickerMenu;
    private StickerBody stickerBody;

    public Sticker() {
        stickerColor = new Color(100,200,200);

        this.setPreferredSize(new Dimension(250,250));
        this.setBackground(stickerColor);
        this.setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                stickerMenu.setVisible(true);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!contains(e.getPoint())) {
                    stickerMenu.setVisible(false);
                }
            }
            
        });
        
        
        stickerMenu = new StickerMenu(stickerColor);
        this.add(stickerMenu, BorderLayout.SOUTH);
        
        stickerBody = new StickerBody(stickerColor);
        this.add(stickerBody, BorderLayout.CENTER);
        
        configurarComportamentoMenu();

        
        stickerMenu.setVisible(false);
    }

    private void configurarComportamentoMenu(){
        
        stickerMenu.getEditlButton().addActionListener(e -> {
            stickerBody.changeLabels();
        });
    }

}

class StickerBody extends JPanel{
    
    private String tituloString;
    private String descricaoString;
    
    private JLabel bodyLabel;
    
    public StickerBody(Color stickerColor){
        this.setBackground(stickerColor);
        
        initLabels();
        
        add(bodyLabel);
    }
    
    private void initLabels(){
        tituloString = new String("Tarefa");
        descricaoString = new String("Descrição de Tarefa");
        
        String bodyString = "<html>" +
                                "<p style='text-align: center'>" + 
                                    "<b>" + tituloString + "</b>" +
                                    "<br />" +
                                    descricaoString +
                                "</p>" +
                            "</html>";
                
        bodyLabel = new JLabel(bodyString);
        formatLabel(bodyLabel);
    }
    
    public void changeLabels(){
        tituloString = new String("Test");
        descricaoString = new String("aaaaaa");
        
        String bodyString = "<html>" +
                                "<p style='text-align: center'>" + 
                                    "<b>" + tituloString + "</b>" +
                                    "<br />" +
                                    descricaoString +
                                "</p>" +
                            "</html>";
                
        bodyLabel.setText(bodyString);
        formatLabel(bodyLabel);
        revalidate();
        repaint();
    }
    
    private void formatLabel(JLabel label){
        label.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
        label.setForeground(Color.BLACK);
    }
}

class StickerMenu extends JPanel{
    private final static String deleteString = new String(Character.toChars(0x1F5D1));
    private final static String editString = new String(Character.toChars(0x1F589));
    
    private JButton deleteButton;
    private JButton editlButton;

    public StickerMenu(Color stickerColor){
        this.setLayout(new FlowLayout());
        this.setBackground(stickerColor);
        
        initButtons();
        
        add(editlButton);
        add(deleteButton);
        
    }


    private void initButtons(){
        editlButton = new JButton(editString);
        editlButton.setName("StickerMenuEdit");
        formatButton(editlButton);
        deleteButton = new JButton(deleteString);
        deleteButton.setName("StickerMenuDelete");
        formatButton(deleteButton);
    }

    private void formatButton(JButton button){
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 35));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditlButton() {
        return editlButton;
    }
    
}