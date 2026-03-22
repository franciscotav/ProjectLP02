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

    
    //vars
    private Color stickerColor;
    private StickerMenu stickerMenu;
    private StickerBody stickerBody;

    //construtor
    public Sticker() {
        
        stickerColor = new Color(100,200,200);
        
        
        this.setPreferredSize(new Dimension(250,250));
        this.setBackground(stickerColor);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.mouseEventos();
        
        
        
        stickerMenu = new StickerMenu(stickerColor);
        this.add(stickerMenu, BorderLayout.SOUTH);
        
        stickerBody = new StickerBody(stickerColor);
        this.add(stickerBody, BorderLayout.CENTER);
        
        configurarComportamentoMenu();

        
        stickerMenu.setVisible(false);
    }

    
    private void mouseEventos(){

        // Create the adapter and store it in a variable
        MouseAdapter stickerMouseAdapter = new MouseAdapter() {

            Point origem;

            @Override
            public void mousePressed(MouseEvent e) {
                // Save the exact pixel grabbed
                origem = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // 1. Get the Sticker's current location in the parent window
                Point currentLocation = getLocation();

                // 2. Calculate the movement delta (current mouse pos - origin)
                int deltax = e.getX() - origem.x;
                int deltay = e.getY() - origem.y;

                // 3. Add the delta to the current location
                setLocation(currentLocation.x + deltax, currentLocation.y + deltay);
            }

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
        };

        // IMPORTANT: Add the adapter to BOTH listener types
        this.addMouseListener(stickerMouseAdapter);
        this.addMouseMotionListener(stickerMouseAdapter);
    }
    
    
    private void configurarComportamentoMenu(){
        
        stickerMenu.getEditlButton().addActionListener(e -> {
            //stickerBody.changeLabels();
            JTextField campoTitulo = new JTextField(15);
            JTextField campoDescricao = new JTextField(15);
            
            JPanel editPanel = new JPanel(new GridLayout(0,1));
            editPanel.add(new JLabel("Titulo:"));
            editPanel.add(campoTitulo);
            editPanel.add(new JLabel("Descrição:"));
            editPanel.add(campoDescricao);
            
            int result = JOptionPane.showConfirmDialog(null, editPanel, "Editar Sticker", JOptionPane.OK_CANCEL_OPTION);
            
            if(result == JOptionPane.OK_OPTION){
                stickerBody.atualizarDados(campoTitulo.getText(), campoDescricao.getText());
            }
            
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
   
    public void atualizarDados(String titulo, String descricao){
        tituloString = titulo;
        descricaoString = descricao;
        
        String bodyString = "<html>" +
                                "<p style='text-align: center'>" + 
                                    "<b>" + tituloString + "</b>" +
                                    "<br />" +
                                    descricaoString +
                                "</p>" +
                            "</html>";
                
        bodyLabel.setText(bodyString);
                
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