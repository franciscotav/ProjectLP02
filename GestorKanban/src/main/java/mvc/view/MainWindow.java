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
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author CasaSFT
 */
/*
 * Esta é Classe principal da interface gráfica 
 * Atua como o contentor principal que organiza os Menus, a Lista de Grupos e o Quadro Kanban.
 * Implementa o padrão de delegação para conectar eventos da interface ao Controller
*/

public class MainWindow extends JFrame {

    //Atributos
    private Menus menus;
    private GrupoLista grupoLista;
    private Quadro quadro;

    //Construtor
    public MainWindow() {
        super("Gestor Kanban by Benardo e Francisco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        menus = new Menus();

        add(menus, BorderLayout.NORTH);

        setVisible(true);
    }
    
    //Setters
    public void setMembroAddicionarMouseAdapter(MouseListener e) {
        grupoLista.setBotaoAdicionarMembroMouseAdapter(e);
    }

    public void setEditarMembroMouseAdapter(MouseListener e) {
        grupoLista.setEditarMembroMouseAdapter(e);
    }

    public void setRemoverMembroMouseAdapter(MouseListener e) {
        grupoLista.setRemoverMembroMouseAdapter(e);
    }

    public void setResponsavelListener(MouseListener e) {
        grupoLista.setResponsavelListener(e);
    }

    public void setAddicionarColunaButtonMouseAdapter(MouseListener e) {
        quadro.setAddicionarColunaButtonMouseAdapter(e);
    }

    public void setGuardarProjetoMouseAdapter(MouseListener e) {
        menus.setGuardarProjetoMouseAdapter(e);
    }
<<<<<<< main
    
    public void highlightProjeto(String projetoId){
        menus.highlightProjeto(projetoId);
    }
    
    public void setRemoverProjetoMouseAdapter(MouseListener e){
        menus.setRemoverProjetoMouseAdapter(e);
    }
    
    public void setSelecionarProjetoMouseAdapter(MouseListener e){
        menus.setSelecionarProjetoMouseAdapter(e);
    }
    
    public void setEditarColunaButtonMouseAdapter(MouseListener e){
=======

    public void setEditarColunaButtonMouseAdapter(MouseListener e) {
>>>>>>> Berna
        quadro.setEditarColunaButtonMouseAdapter(e);
    }

    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e) {
        quadro.setAddicionarTarefaButtonMouseAdapter(e);
    }

    public void setRemoverColunaButtonMouseAdapter(MouseListener e) {
        quadro.setRemoverColunaButtonMouseAdapter(e);
    }

    public void setTarefaMouseAdapter(MouseListener e) {
        StikerTarefa targetSticker = null;
        TarefaColuna coluna = quadro.getlastTarefaColuna();

        for (int i = 0; i < coluna.getComponentCount(); i++) {
            if (coluna.getComponent(i) instanceof StikerTarefa) {
                targetSticker = (StikerTarefa) coluna.getComponent(i);
                break;
            }
        }

        if (targetSticker != null) {
            targetSticker.addMouseListener(e);
            targetSticker.addMouseMotionListener((MouseMotionListener) e);
        }
    }

    public void setTarefaMouseAdapter(MouseEvent sourceEvent, MouseListener e) {
        Component source = (Component) sourceEvent.getSource();
        StikerTarefa targetSticker = null;

        if (source instanceof StikerTarefa) {
            targetSticker = (StikerTarefa) source;
        } else if (source instanceof StickerAddicionar) {
            StickerAddicionar stickerAdd = (StickerAddicionar) source;
            TarefaColuna coluna = (TarefaColuna) stickerAdd.getParent();

            for (int i = 0; i < coluna.getComponentCount(); i++) {
                if (coluna.getComponent(i) instanceof StikerTarefa) {
                    targetSticker = (StikerTarefa) coluna.getComponent(i);
                    break;
                }
            }
        }

        if (targetSticker != null) {
            targetSticker.addMouseListener(e);
            targetSticker.addMouseMotionListener((MouseMotionListener) e);
        }
    }

    public void setEditarTarefaButtonMouseAdapter(MouseListener e) {
        StikerTarefa targetSticker = null;
        TarefaColuna coluna = quadro.getlastTarefaColuna();

        for (int i = 0; i < coluna.getComponentCount(); i++) {
            if (coluna.getComponent(i) instanceof StikerTarefa) {
                targetSticker = (StikerTarefa) coluna.getComponent(i);
                break;
            }
        }

        if (targetSticker != null) {
            targetSticker.setEditarTarefaButtonMouseAdapter(e);
        }
    }

    public void setEditarTarefaButtonMouseAdapter(MouseEvent sourceEvent, MouseListener e) {
        Component source = (Component) sourceEvent.getSource();
        StikerTarefa targetSticker = null;

        if (source instanceof StikerTarefa) {
            targetSticker = (StikerTarefa) source;
        } else if (source instanceof StickerAddicionar) {
            StickerAddicionar stickerAdd = (StickerAddicionar) source;
            TarefaColuna coluna = (TarefaColuna) stickerAdd.getParent();
            for (int i = 0; i < coluna.getComponentCount(); i++) {
                if (coluna.getComponent(i) instanceof StikerTarefa) {
                    targetSticker = (StikerTarefa) coluna.getComponent(i);
                    break;
                }
            }
        }

        if (targetSticker != null) {
            targetSticker.setEditarTarefaButtonMouseAdapter(e);
        }
    }

    public void setRemoverTarefaButtonMouseAdapter(MouseListener e) {
        StikerTarefa targetSticker = null;
        TarefaColuna coluna = quadro.getlastTarefaColuna();

        for (int i = 0; i < coluna.getComponentCount(); i++) {
            if (coluna.getComponent(i) instanceof StikerTarefa) {
                targetSticker = (StikerTarefa) coluna.getComponent(i);
                break;
            }
        }

        if (targetSticker != null) {
            targetSticker.setRemoverTarefaButtonMouseAdapter(e);
        }
    }

    public void setRemoverTarefaButtonMouseAdapter(MouseEvent sourceEvent, MouseListener e) {
        Component source = (Component) sourceEvent.getSource();
        StikerTarefa targetSticker = null;

        if (source instanceof StikerTarefa) {
            targetSticker = (StikerTarefa) source;
        } else if (source instanceof StickerAddicionar) {
            StickerAddicionar stickerAdd = (StickerAddicionar) source;
            TarefaColuna coluna = (TarefaColuna) stickerAdd.getParent();
            for (int i = 0; i < coluna.getComponentCount(); i++) {
                if (coluna.getComponent(i) instanceof StikerTarefa) {
                    targetSticker = (StikerTarefa) coluna.getComponent(i);
                    break;
                }
            }
        }

        if (targetSticker != null) {
            targetSticker.setRemoverTarefaButtonMouseAdapter(e);
        }
    }

    public void setNovoProjetoListener(MouseListener e) {
        menus.adicionarNovoProjetoListener(e);
    }

    public void setCarregarProjetoMouseAdapter(MouseListener e) {
        menus.setCarregarProjetoMouseAdapter(e);
    }
    
    //Responsavel
    
<<<<<<< main
    public void adicionarComponentesProjeto() {
        limparAreaProjeto();
        
        grupoLista = new GrupoLista();
        quadro = new Quadro();

        add(grupoLista, BorderLayout.WEST);
        add(quadro, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
    
    
    public void adicionarComponentesProjeto(String id, String projetoNome) {
        limparAreaProjeto();
        
        menus.addProjeto(id, projetoNome);
        grupoLista = new GrupoLista();
        quadro = new Quadro();

        add(grupoLista, BorderLayout.WEST);
        add(quadro, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
    
    public String getProjetoId(MouseEvent e){
        Component source = (Component) e.getSource();
        while (source != null && !(source instanceof ProjetoPanel)){
            source = source.getParent();
        }
        
        if (source instanceof ProjetoPanel) {
            ProjetoPanel projeto = (ProjetoPanel) source;
            return projeto.getId();
        }
        
        return null;
    }
    
    public String getPath(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Carregar");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files (*.json)", "json");
        fileChooser.setFileFilter(filter);
        
        int response = fileChooser.showOpenDialog(null);
        
        if (response == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            return selectedFile;
        }
        
        return "";
    }
    
    public String setPath(String filename){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(filename));
        fileChooser.setDialogTitle("Guardar");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files (*.json)", "json");
        fileChooser.setFileFilter(filter);
        
        int response = fileChooser.showSaveDialog(null);
        
        if (response == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            return selectedFile;
        }
        
        return "";
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
=======
    public void criarNovoResponsavel(String id, String nome) {
        grupoLista.criarNovoResponsavel(id, nome);
    }
    
    public void editarResponsavel(MouseEvent e) {
>>>>>>> Berna
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        responsavel.editar();
    }

    public void removerResponsavel(MouseEvent e) {
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();
        responsavel.remover();
    }
    
    //drag and drop membros
    public void membroMousePressed(MouseEvent e) {
        Responsavel responsavel = getResponsavelFromEvent(e);
        if (responsavel != null) {
            responsavel.mousePressed(e);
        }
    }

    public boolean membroMouseReleased(MouseEvent e) {
        Responsavel responsavel = getResponsavelFromEvent(e);
        if (responsavel != null) {
            return responsavel.mouseReleased(e);
        }

        return false;
    }

    public void membroMouseDragged(MouseEvent e) {
        Responsavel responsavel = getResponsavelFromEvent(e);
        if (responsavel != null) {
            responsavel.mouseDragged(e);
        }
    }

    //Coluna
    public void editarColuna(MouseEvent e) {
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        colunaMenu.buttonEditarClicked();
    }
    
    public void removerColuna(MouseEvent e) {
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        colunaMenu.buttonRemoverClicked();
    }
    
    public void addicionarColunaTarefa(String id, String nome) {
        ColunaAddicionar colunaAdd = quadro.getEstados().getColunaAddicionar();
        colunaAdd.mouseClicked(id, nome);
    }

    public void addicionarColunaTarefa(MouseEvent e, String id, String nome) {
        ColunaAddicionar colunaAdd = (ColunaAddicionar) e.getSource();
        colunaAdd.mouseClicked(id, nome);
    }
    
<<<<<<< main
    public void atualizar(){
        revalidate();
        repaint();
    }
    
    //addiciona no ultimo loaded Sticker
    public void addicionarTarefa(String idTarefa, String titulo, String descricao){
        StickerAddicionar stickerAddicionar = quadro.getlastTarefaColuna().getStickerAddicionar();
        stickerAddicionar.mouseClicked(idTarefa, titulo, descricao);
=======
    //drag and drop tarefas 
    public void tarefaMousePressed(MouseEvent e) {
        Component source = (Component) e.getSource();
        StikerTarefa colunaMenu = (StikerTarefa) source;

        colunaMenu.mousePressed(e);
    }

    public void tarefaMouseReleased(MouseEvent e) {
        Component source = (Component) e.getSource();
        StikerTarefa colunaMenu = (StikerTarefa) source;

        colunaMenu.mouseReleased(e);
    }

    public void tarefaMouseDragged(MouseEvent e) {
        Component source = (Component) e.getSource();
        StikerTarefa colunaMenu = (StikerTarefa) source;

        colunaMenu.mouseDragged(e);
    }
    
    //tarefas
    public void editarTarefa(MouseEvent e) {
        Component source = (Component) e.getSource();
        StikerTarefa stickerTarefa = null;
        while (source != null) {
            if (source instanceof StikerTarefa) {
                stickerTarefa = (StikerTarefa) source;
                break;
            }
            source = source.getParent();
        }

        if (stickerTarefa != null) {
            stickerTarefa.editarMousePressed();
        }

    }

    public void removerTarefa(MouseEvent e) {
        Component source = (Component) e.getSource();
        StikerTarefa stickerTarefa = null;
        while (source != null) {
            if (source instanceof StikerTarefa) {
                stickerTarefa = (StikerTarefa) source;
                break;
            }
            source = source.getParent();
        }

        if (stickerTarefa != null) {
            stickerTarefa.removerMousePressed();
        }

>>>>>>> Berna
    }
    
    public void addicionarTarefa(MouseEvent e, String idTarefa, String titulo, String descricao) {
        StickerAddicionar stickerAddicionar = (StickerAddicionar) e.getSource();
        stickerAddicionar.mouseClicked(idTarefa, titulo, descricao);
    }

    //addiciona no ultimo loaded Sticker
    public void addicionarTarefa(String idTarefa, String titulo, String descricao) {
        StickerAddicionar stickerAddicionar = quadro.getlastTarefaColuna().getStickerAddicionar();
        stickerAddicionar.mouseClicked(idTarefa, titulo, descricao);
    }
    
    //Getters
    public String getColunaID(MouseEvent e) {
        Component source = (Component) e.getSource();

        TarefaColuna tarefaColuna = null;
        while (source != null) {
            if (source instanceof TarefaColuna) {
                tarefaColuna = (TarefaColuna) source;
            }
            source = source.getParent();
        }

        if (tarefaColuna != null) {
            return tarefaColuna.getId();
        } else {
            return "";
        }
    }

    public String getTarefaID(MouseEvent e) {
        Component source = (Component) e.getSource();

        StikerTarefa stikerTarefa = null;
        while (source != null) {
            if (source instanceof StikerTarefa) {
                stikerTarefa = (StikerTarefa) source;
            }
            source = source.getParent();
        }

        if (stikerTarefa != null) {
            return stikerTarefa.getID();
        } else {
            return "";
        }
    }

    public String getColunabyTarefaID(String ID) {
        for (int i = 0; i < quadro.getEstados().getComponentCount(); i++) {
            if (quadro.getEstados().getComponent(i) instanceof TarefaColuna) {
                TarefaColuna tarefaColuna = (TarefaColuna) quadro.getEstados().getComponent(i);
                for (int j = 0; j < tarefaColuna.getComponentCount(); j++) {
                    if (tarefaColuna.getComponent(j) instanceof StikerTarefa) {
                        StikerTarefa tarefa = (StikerTarefa) tarefaColuna.getComponent(j);
                        if (tarefa.getID().equals(ID)) {
                            return tarefaColuna.getId();
                        }
                    }
                }
            }
        }

        return "";
    }

    public String getResponsavelLastStikerID(MouseEvent e) {
        Component source = (Component) e.getSource();

        Responsavel responsavel = null;
        while (source != null) {
            if (source instanceof Responsavel) {
                responsavel = (Responsavel) source;
                break;
            }
            source = source.getParent();
        }

        if (responsavel != null) {
            return responsavel.getResponsavelLastStikerID();
        } else {
            return "";
        }
    }

    public String getTarefaTitulo(MouseEvent e) {
        Component source = (Component) e.getSource();

        StikerTarefa stikerTarefa = null;
        while (source != null) {
            if (source instanceof StikerTarefa) {
                stikerTarefa = (StikerTarefa) source;
            }
            source = source.getParent();
        }

        if (stikerTarefa != null) {
            return stikerTarefa.getTitulo();
        } else {
            return "";
        }
    }

    public String getTarefaDescricao(MouseEvent e) {
        Component source = (Component) e.getSource();

        StikerTarefa stikerTarefa = null;
        while (source != null) {
            if (source instanceof StikerTarefa) {
                stikerTarefa = (StikerTarefa) source;
            }
            source = source.getParent();
        }

        if (stikerTarefa != null) {
            return stikerTarefa.getDescricao();
        } else {
            return "";
        }
    }

    public String getColunaNome(MouseEvent e) {
        Component source = (Component) e.getSource();
        ColunaMenu colunaMenu = (ColunaMenu) source.getParent();
        TarefaColuna tarefaColuna = (TarefaColuna) colunaMenu.getParent();

        return tarefaColuna.getName();
    }
    
    public String getResponsavelID(MouseEvent e) {
        Responsavel responsavel = getResponsavelFromEvent(e);

        return responsavel.getId();
    }

    public String getResponsavelNome(MouseEvent e) {
        JButton buttonEditar = (JButton) e.getSource();
        JPanel panelButtons = (JPanel) buttonEditar.getParent();
        Responsavel responsavel = (Responsavel) panelButtons.getParent();

        return responsavel.getNome();
    }
    
    private Responsavel getResponsavelFromEvent(MouseEvent e) {
        Component source = (Component) e.getSource();

        if (source instanceof Responsavel) {
            return (Responsavel) source;
        }

        Component parent = source.getParent();
        while (parent != null) {
            if (parent instanceof Responsavel) {
                return (Responsavel) parent;
            }
            parent = parent.getParent();
        }

        return null;
    }
    
    public Menus getMenus() {
        return menus;
    }
    
    public String getPath() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files (*.json)", "json");
        fileChooser.setFileFilter(filter);

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
            return selectedFile;
        }

        return "";
    }
    
    //auxiliares 
    public void adicionarComponentesProjeto(String id, String projetoNome) {
        limparAreaProjeto();

        menus.addProjeto(id, projetoNome);
        grupoLista = new GrupoLista();
        quadro = new Quadro();

        add(grupoLista, BorderLayout.WEST);
        add(quadro, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void limparAreaProjeto() {
        if (grupoLista != null) {
            remove(grupoLista);
        }
        if (quadro != null) {
            remove(quadro);
        }

        revalidate();
        repaint();
    }

    public void sincronizarMembrosTarefas(String pessoaID, String tarefaID) {
        Responsavel responsavel = null;
        StikerTarefa stickerTarefa = null;
        for (int i = 0; i < grupoLista.getGrupoListaPanel().getComponentCount(); i++) {
            if (grupoLista.getGrupoListaPanel().getComponent(i) instanceof Responsavel) {
                Responsavel res = (Responsavel) grupoLista.getGrupoListaPanel().getComponent(i);
                if (res.getId().equals(pessoaID)) {
                    responsavel = res;
                    break;
                }
            }
        }

        for (int i = 0; i < quadro.getEstados().getComponentCount(); i++) {
            if (quadro.getEstados().getComponent(i) instanceof TarefaColuna) {
                TarefaColuna tarefaColuna = (TarefaColuna) quadro.getEstados().getComponent(i);
                for (int j = 0; j < tarefaColuna.getComponentCount(); j++) {
                    if (tarefaColuna.getComponent(j) instanceof StikerTarefa) {
                        StikerTarefa tarefa = (StikerTarefa) tarefaColuna.getComponent(j);
                        if (tarefa.getID().equals(tarefaID)) {
                            stickerTarefa = tarefa;
                        }
                    }
                }
            }
        }

        if (responsavel != null && stickerTarefa != null) {
            responsavel.setTarefa(stickerTarefa);
            stickerTarefa.setResponsavel(responsavel);
        }
    }
}
