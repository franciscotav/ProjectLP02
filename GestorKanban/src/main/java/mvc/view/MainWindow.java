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
        
        add(menus, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    public void setMembroAddicionarMouseAdapter(MouseListener e){
        grupoLista.setMembroAddicionarMouseAdapter(e);
    }
    
    public void setEditarMembroMouseAdapter(MouseListener e){
        grupoLista.setEditarMembroMouseAdapter(e);
    }
    
    public void setRemoverMembroMouseAdapter(MouseListener e){
        grupoLista.setRemoverMembroMouseAdapter(e);
    }
    
    public void setMembroMouseAdapter(MouseListener e){
        grupoLista.setMembroMouseAdapter(e);
    }
    
    public void setAddicionarColunaButtonMouseAdapter(MouseListener e){
        quadro.setAddicionarColunaButtonMouseAdapter(e);
    }
    
    public void setEditarColunaButtonMouseAdapter(MouseListener e){
        quadro.setEditarColunaButtonMouseAdapter(e);
    }
    
    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e){
        quadro.setAddicionarTarefaButtonMouseAdapter(e);
    }
    
    public void setRemoverColunaButtonMouseAdapter(MouseListener e){
        quadro.setRemoverColunaButtonMouseAdapter(e);
    }
    
    public Menus getMenus() {
        return menus;
    }
    
    public void adicionarNovoProjetoListener(MouseListener e) {
        menus.adicionarNovoProjetoListener(e);
    }
    
    public void adicionarComponentesProjeto() {
        limparAreaProjeto();
        
        menus.addProjeto("Projeto");
        grupoLista = new GrupoLista();
        quadro = new Quadro();

        add(grupoLista, BorderLayout.WEST);
        add(quadro, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
 
    
    private void limparAreaProjeto(){
        if(grupoLista != null)
            remove(grupoLista);
        if(quadro != null)
            remove(quadro);
        
        revalidate();
        repaint();
    }
    
    public void criarNovoResponsavel(int id, String nome){
        grupoLista.criarNovoResponsavel(id,nome);
    }
    
    public void editarResponsavel(MouseEvent e){
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        responsavel.editar();
    }
    
    public void removerResponsavel(MouseEvent e){
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        responsavel.remover();
    }
    
    private Responsavel getResponsavelFromEvent(MouseEvent e) {
        Component source = (Component) e.getSource();
        
        if(source instanceof Responsavel) {
            return (Responsavel) source;
        }

        Component parent = source.getParent();
        while (parent != null) {
            if(parent instanceof Responsavel) {
                return (Responsavel) parent;
            }
            parent = parent.getParent();
        }

        return null;
    }
    
    public void membroMousePressed(MouseEvent e){
        Responsavel responsavel = getResponsavelFromEvent(e);
        if(responsavel != null)
            responsavel.mousePressed(e);
    }
    
    public void membroMouseReleased(MouseEvent e){
        //System.out.println(e.getSource().getClass().getName());
        Responsavel responsavel = getResponsavelFromEvent(e);
        if(responsavel != null)
            responsavel.mouseReleased(e);
    }
    
    public void membroMouseDragged(MouseEvent e){
        Responsavel responsavel = getResponsavelFromEvent(e);
        if(responsavel != null)
            responsavel.mouseDragged(e);
    }
    
    public void addicionarColunaTarefa(MouseEvent e){
        ColunaAddicionar colunaAdd = (ColunaAddicionar) e.getSource();
        colunaAdd.mouseClicked();
    }
    
    public void addicionarTarefa(MouseEvent e){
        StickerAddicionar stickerAddicionar = (StickerAddicionar) e.getSource();
        stickerAddicionar.mouseClicked();
    }
    
    public void editarColuna(MouseEvent e){
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        colunaMenu.buttonEditarClicked();
    }
    
    public void removerColuna(MouseEvent e){
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        colunaMenu.buttonRemoverClicked();
    }
}
