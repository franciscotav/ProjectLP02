/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package window.frame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author CasaSFT
 */



public class Window extends JFrame{
    private GridBagLayout mainWindowLayout;
    private GridBagConstraints mainWindowConstraints;
    
    private NavMenu navMenu;
    private Quadro quadro;

    public Window(){
        super("Gestor Kanban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        
        mainWindowLayout = new GridBagLayout();
        mainWindowConstraints = new GridBagConstraints();
        
        setLayout(mainWindowLayout);
        
        //Definir GridBagConstraints() para navMenu
        mainWindowConstraints.fill = GridBagConstraints.BOTH;
        mainWindowConstraints.gridx = 0;
        mainWindowConstraints.gridy = 0;
        mainWindowConstraints.weightx = 1;
        mainWindowConstraints.weighty = 0.1;
        

        navMenu = new NavMenu();
        add(navMenu, mainWindowConstraints);
        
        //Definir GridBagConstraints() para quadro
        mainWindowConstraints.fill = GridBagConstraints.BOTH;
        mainWindowConstraints.gridx = 0;
        mainWindowConstraints.gridy = 1;
        mainWindowConstraints.weightx = 1;
        mainWindowConstraints.weighty = 0.9;
        
        quadro = new Quadro();
        add(quadro, mainWindowConstraints);

        setVisible(true);
    }

}

class NavMenu extends JPanel{
    private GridBagLayout navMenuLayout;
    private GridBagConstraints navMenuConstraints;
    
    private TabMainMenu tabMainMenu;
    private TabProjetoMenu tabProjetoMenu;
    

    public NavMenu(){
        navMenuLayout = new GridBagLayout();
        navMenuConstraints = new GridBagConstraints();
        
        setLayout(navMenuLayout);
        
        //Debug Area
        Border borda = BorderFactory.createLineBorder(Color.BLUE, 2);
        setBorder(borda);
        //-----
        
        //Definir GridBagConstraints() para tabMainMenu
        navMenuConstraints.fill = GridBagConstraints.BOTH;
        navMenuConstraints.gridx = 0;
        navMenuConstraints.gridy = 0;
        navMenuConstraints.weightx = 1;
        navMenuConstraints.weighty = 0.5;

        tabMainMenu = new TabMainMenu();
        add(tabMainMenu,navMenuConstraints);
        
        //Definir GridBagConstraints() para tabProjetoMenu
        navMenuConstraints.fill = GridBagConstraints.BOTH;
        navMenuConstraints.gridx = 0;
        navMenuConstraints.gridy = 1;
        navMenuConstraints.weightx = 1;
        navMenuConstraints.weighty = 0.5;

        tabProjetoMenu = new TabProjetoMenu();
        add(tabProjetoMenu,navMenuConstraints);

        
    }

}

class TabMainMenu extends JPanel{
    private FlowLayout tabMainMenuLayout;

    public TabMainMenu(){
        tabMainMenuLayout = new FlowLayout();
        setLayout(tabMainMenuLayout);
        
        //Debug Area
        Border borda = BorderFactory.createLineBorder(Color.RED, 2);
        setBorder(borda);
        //---
    }

}

class TabProjetoMenu extends JPanel{
    private FlowLayout tabProjetoMenuLayout;

    public TabProjetoMenu(){
        tabProjetoMenuLayout = new FlowLayout();
        setLayout(tabProjetoMenuLayout);
        
        //Debug Area
        Border borda = BorderFactory.createLineBorder(Color.GREEN, 2);
        setBorder(borda);
    }

}

class Quadro extends JPanel{
    private GridBagLayout quadroLayout;
    private GridBagConstraints quadroConstraints;
    
    private SideQuadro sideQuadro;
    private MainQuadro mainQuadro;
    
    public Quadro(){
        quadroLayout = new GridBagLayout();
        quadroConstraints = new GridBagConstraints();
        setLayout(quadroLayout);
        
        //Debug Area
        Border borda = BorderFactory.createLineBorder(Color.MAGENTA, 2);
        setBorder(borda);
        //-------

        //Definir GridBagConstraints() para sideQuadro
        quadroConstraints.fill = GridBagConstraints.BOTH;
        quadroConstraints.gridx = 0;
        quadroConstraints.gridy = 0;
        quadroConstraints.weightx = 0.2;
        quadroConstraints.weighty = 1;
        
        sideQuadro = new SideQuadro();
        add(sideQuadro,quadroConstraints);
        
        
        //Definir GridBagConstraints() para sideQuadro
        quadroConstraints.fill = GridBagConstraints.BOTH;
        quadroConstraints.gridx = 1;
        quadroConstraints.gridy = 0;
        quadroConstraints.weightx = 0.8;
        quadroConstraints.weighty = 1;
        
        mainQuadro = new MainQuadro();
        add(mainQuadro,quadroConstraints);
        
        
    }
    
}

class SideQuadro extends JScrollPane {
    
    private ScrollPaneLayout sideQuadroLayout;

    public SideQuadro(){
        sideQuadroLayout = new ScrollPaneLayout();
        setLayout(sideQuadroLayout);

        //Debug Area
        Border borda = BorderFactory.createLineBorder(Color.MAGENTA, 2);
        setBorder(borda);
        //---
        
    }
}

class MainQuadro extends JPanel {
    private FlowLayout mainQuadroLayout;
    

    public MainQuadro(){
        mainQuadroLayout = new FlowLayout();
        setLayout(mainQuadroLayout);

        //Debug Area
        Border borda = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
        setBorder(borda);
        
        Sticker stickerA = new Sticker();
        add(stickerA);
        
        //-----
    }
}