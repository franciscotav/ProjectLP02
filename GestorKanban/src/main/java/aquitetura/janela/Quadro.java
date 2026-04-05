/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aquitetura.janela;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author CasaSFT
 */
public class Quadro extends JScrollPane {
    private Estados estados;
    
    public Quadro(){
        estados = new Estados();
        estados.setAlignmentY(Component.TOP_ALIGNMENT);
        this.setViewportView(estados);
        
    }
}

class Estados extends JPanel {
    private BoxLayout estadosLayout;
    
    public Estados(){
        estadosLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(estadosLayout);
        add(Box.createVerticalGlue());
        add(new TarefaColuna("Para fazer"));
        add(new TarefaColuna("A fazer"));
        add(new TarefaColuna("Conluido"));
    }         
}

class TarefaColuna extends JPanel {
    private BoxLayout tarefasLayout;
    private JLabel labelName;
    private StickerAddicionar stickerAddicionar;
            
    public TarefaColuna(String nomeColuna){
        tarefasLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        setLayout(tarefasLayout);
        
        labelName = new JLabel(nomeColuna);
        labelName.setFont(new Font("Arial", Font.BOLD, 20));
        labelName.setForeground(Color.GRAY);
        labelName.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelName);
        
        stickerAddicionar = new StickerAddicionar();
        
        add(stickerAddicionar);
        
    }
    
}

