/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    
    public void setMembroAddicionarMouseAdapter(MouseListener e){
        grupoLista.setMembroAddicionarMouseAdapter(e);
    }
    
    public void setEditarMembroMouseAdapter(MouseListener e){
        grupoLista.setEditarMembroMouseAdapter(e);
    }
    
    public void criarNovoResponsavel(int id, String nome){
        grupoLista.criarNovoResponsavel(id,nome);
    }
    
    public void editarResponsavel(MouseEvent e){
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        grupoLista.editarResponsavel(responsavel);
        
    }
    
}
