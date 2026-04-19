/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
    
//    public void setTarefaMouseAdapter(MouseEvent sourceEvent, MouseListener e){
//        StickerAddicionar sticker = (StickerAddicionar) sourceEvent.getSource();
//        TarefaColuna coluna = (TarefaColuna) sticker.getParent();
//        
//        StikerTarefa lastStickerCreated = null;
//        for(int i = 0; i < coluna.getComponentCount(); i++){
//            if(coluna.getComponent(i) instanceof StikerTarefa){
//                lastStickerCreated = (StikerTarefa) coluna.getComponent(i);
//                break;
//            }
//        }
//        
//        if(lastStickerCreated != null){
//            lastStickerCreated.addMouseListener(e);
//            lastStickerCreated.addMouseMotionListener((MouseMotionListener) e);
//        }
//        
//    }
    
    public void setTarefaMouseAdapter(MouseEvent sourceEvent, MouseListener e) {
        Component source = (Component) sourceEvent.getSource();
        StikerTarefa targetSticker = null;

        if(source instanceof StikerTarefa) {
            targetSticker = (StikerTarefa) source;
        }else if(source instanceof StickerAddicionar){
            StickerAddicionar stickerAdd = (StickerAddicionar) source;
            TarefaColuna coluna = (TarefaColuna) stickerAdd.getParent();

            for(int i = 0; i < coluna.getComponentCount(); i++){
                if(coluna.getComponent(i) instanceof StikerTarefa){
                    targetSticker = (StikerTarefa) coluna.getComponent(i);
                    break;
                }
            }
        }

        if(targetSticker != null){
            targetSticker.addMouseListener(e);
            targetSticker.addMouseMotionListener((MouseMotionListener) e);
        }
    }
    
    public void setEditarTarefaButtonMouseAdapter(MouseEvent sourceEvent, MouseListener e) {
        Component source = (Component) sourceEvent.getSource();
        StikerTarefa targetSticker = null;

        if(source instanceof StikerTarefa){
            targetSticker = (StikerTarefa) source;
        }else if(source instanceof StickerAddicionar) {
            StickerAddicionar stickerAdd = (StickerAddicionar) source;
            TarefaColuna coluna = (TarefaColuna) stickerAdd.getParent();
            for(int i = 0; i < coluna.getComponentCount(); i++){
                if(coluna.getComponent(i) instanceof StikerTarefa){
                    targetSticker = (StikerTarefa) coluna.getComponent(i);
                    break;
                }
            }
        }

        if(targetSticker != null){
            targetSticker.setEditarTarefaButtonMouseAdapter(e);
        }
    }
    
    public void setRemoverTarefaButtonMouseAdapter(MouseEvent sourceEvent, MouseListener e) {
        Component source = (Component) sourceEvent.getSource();
        StikerTarefa targetSticker = null;

        if(source instanceof StikerTarefa){
            targetSticker = (StikerTarefa) source;
        }else if(source instanceof StickerAddicionar) {
            StickerAddicionar stickerAdd = (StickerAddicionar) source;
            TarefaColuna coluna = (TarefaColuna) stickerAdd.getParent();
            for(int i = 0; i < coluna.getComponentCount(); i++){
                if(coluna.getComponent(i) instanceof StikerTarefa){
                    targetSticker = (StikerTarefa) coluna.getComponent(i);
                    break;
                }
            }
        }

        if(targetSticker != null){
            targetSticker.setRemoverTarefaButtonMouseAdapter(e);
        }
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
    
    public void criarNovoResponsavel(String id, String nome){
        grupoLista.criarNovoResponsavel(id,nome);
    }
    
    public void editarResponsavel(MouseEvent e){
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        responsavel.editar();
    }
    
    public String getResponsavelID(MouseEvent e){
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        
        return responsavel.getId();
    }
    
    public String getResponsavelNome(MouseEvent e){
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        
        return responsavel.getNome();
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
        Responsavel responsavel = getResponsavelFromEvent(e);
        if(responsavel != null)
            responsavel.mouseReleased(e);
    }
    
    public void membroMouseDragged(MouseEvent e){
        Responsavel responsavel = getResponsavelFromEvent(e);
        if(responsavel != null)
            responsavel.mouseDragged(e);
    }
    
    public void addicionarColunaTarefa(MouseEvent e, String id, String nome){
        ColunaAddicionar colunaAdd = (ColunaAddicionar) e.getSource();
        colunaAdd.mouseClicked(id,nome);
    }
    
    public void addicionarTarefa(MouseEvent e, String idTarefa, String titulo, String descricao){
        StickerAddicionar stickerAddicionar = (StickerAddicionar) e.getSource();
        stickerAddicionar.mouseClicked(idTarefa, titulo, descricao);
    }
    
    public void editarColuna(MouseEvent e){
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        colunaMenu.buttonEditarClicked();
    }
    
    public String getColunaID(MouseEvent e){
        Component source = (Component) e.getSource();
        
        TarefaColuna tarefaColuna = null;
        while(source != null){
            if(source instanceof TarefaColuna){
                tarefaColuna = (TarefaColuna) source;
            }
            source = source.getParent();
        }
        
        if(tarefaColuna != null)
            return tarefaColuna.getId();
        else
            return "";
    }
    
    public String getTarefaID(MouseEvent e){
        Component source = (Component) e.getSource();
        
        StikerTarefa stikerTarefa = null;
        while(source != null){
            if(source instanceof StikerTarefa){
                stikerTarefa = (StikerTarefa) source;
            }
            source = source.getParent();
        }
        
        if(stikerTarefa != null)
            return stikerTarefa.getID();
        else
            return "";
    }
    
    public String getTarefaTitulo(MouseEvent e){
        Component source = (Component) e.getSource();
        
        StikerTarefa stikerTarefa = null;
        while(source != null){
            if(source instanceof StikerTarefa){
                stikerTarefa = (StikerTarefa) source;
            }
            source = source.getParent();
        }
        
        if(stikerTarefa != null)
            return stikerTarefa.getTitulo();
        else
            return "";
    }
    
    public String getTarefaDescricao(MouseEvent e){
        Component source = (Component) e.getSource();
        
        StikerTarefa stikerTarefa = null;
        while(source != null){
            if(source instanceof StikerTarefa){
                stikerTarefa = (StikerTarefa) source;
            }
            source = source.getParent();
        }
        
        if(stikerTarefa != null)
            return stikerTarefa.getDescricao();
        else
            return "";
    }
    
    public String getColunaNome(MouseEvent e){
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        TarefaColuna tarefaColuna = (TarefaColuna) colunaMenu.getParent();
        
        return tarefaColuna.getName();
    }
    
    public void removerColuna(MouseEvent e){
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        colunaMenu.buttonRemoverClicked();
    }
    
    public void tarefaMousePressed(MouseEvent e){
        Component source = (Component) e.getSource();
        StikerTarefa colunaMenu = (StikerTarefa) source;
        
        colunaMenu.mousePressed(e);
    }
    
    public void tarefaMouseReleased(MouseEvent e){
        Component source = (Component) e.getSource();
        StikerTarefa colunaMenu = (StikerTarefa) source;
        
        colunaMenu.mouseReleased(e);
    }
    
    public void tarefaMouseDragged(MouseEvent e){
        Component source = (Component) e.getSource();
        StikerTarefa colunaMenu = (StikerTarefa) source;
        
        colunaMenu.mouseDragged(e);
    } 
     
    public void editarTarefa(MouseEvent e){
        Component source = (Component) e.getSource();
        StikerTarefa stickerTarefa = null;
        while(source != null){
            if(source instanceof StikerTarefa){
                stickerTarefa = (StikerTarefa) source;
                break;
            }
            source = source.getParent();
        }

        if(stickerTarefa != null) {
            stickerTarefa.editarMousePressed();
        }
       
    }
    
    public void removerTarefa(MouseEvent e){
        Component source = (Component) e.getSource();
        StikerTarefa stickerTarefa = null;
        while(source != null){
            if(source instanceof StikerTarefa){
                stickerTarefa = (StikerTarefa) source;
                break;
            }
            source = source.getParent();
        }

        if(stickerTarefa != null) {
            stickerTarefa.removerMousePressed();
        }
       
    }
    
    
}
