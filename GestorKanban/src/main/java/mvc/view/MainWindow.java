/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author CasaSFT
 */
public class MainWindow extends JFrame {
    private Menus menus;
    private GrupoLista grupoLista;
    private Quadro quadro;
    
    public MainWindow() {
        super("Gestor Kanban by Benardo e Francisco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        
        menus = new Menus();
        grupoLista = new GrupoLista();
        quadro = new Quadro();
        
        add(menus,BorderLayout.NORTH);
        add(grupoLista,BorderLayout.WEST);
        add(quadro,BorderLayout.CENTER);
        
        setVisible(true);
    }

    public Quadro getQuadro() {
        return quadro;
    }
    
    
}
