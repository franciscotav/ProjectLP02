/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controller;

import mvc.view.*;
import database.model.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author FTCASA
 */
public class Controlador {
    private MainWindow view;
    private Projeto model;
    
    private static int idPessoa = 0;
    private static int idEstado = 0;
    private static int idTarefa = 0;

    public Controlador(MainWindow view, Projeto model) {
        this.view = view;
        this.model = model;
        this.view.adicionarNovoProjetoListener(new NovoProjetoMouseAdapter());
    }

    class NovoProjetoMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.adicionarComponentesProjeto();
            view.setMembroAddicionarMouseAdapter(new AddicionarMembroMouseAdapter());
            view.setAddicionarColunaButtonMouseAdapter(new AddicionarColunaButtonMouseAdapter());
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    //-------GRUPOLISTA
    //---------------------------
    class AddicionarMembroMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            idPessoa++;
            String nomeDefault = "Responsavel";
            String idPessoaString = "PES-" + idPessoa;
            
            model.addPessoa(idPessoaString, nomeDefault);
            
            view.criarNovoResponsavel(idPessoaString, nomeDefault);
            view.setMembroMouseAdapter(new MembroMouseAdapter());
            view.setEditarMembroMouseAdapter(new EditarMembroMouseAdapter());
            view.setRemoverMembroMouseAdapter(new RemoverMembroMouseAdapter());
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    class EditarMembroMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.editarResponsavel(e);
            String id = view.getResponsavelID(e);
            String novoNome = view.getResponsavelNome(e);
            model.editarPessoa(id, novoNome);
            //falta procurar se existe em tarefas para corregir o nome tambem?
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    class RemoverMembroMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.removerResponsavel(e);
            
            String id = view.getResponsavelID(e);
            model.removerPessoa(id);
            
            //falta procurar para remover das tarefas tambem?
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    class MembroMouseAdapter implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            view.membroMousePressed(e);
        }
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            view.membroMouseReleased(e);
        }
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
        //MouseMotionListener
        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
            view.membroMouseDragged(e);
        }
        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {}
    }

    //-----Quadro
    //---
    class AddicionarColunaButtonMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            idEstado++;
            String nomeDefault = "Estado";
            String idEstadoString = "EST-" + idEstado; 
            
            model.addEstado(idEstadoString, nomeDefault);
            
            view.addicionarColunaTarefa(e, idEstadoString, nomeDefault);
            view.setAddicionarTarefaButtonMouseAdapter(new AddicionarTarefaButtonMouseAdapter());
            view.setEditarColunaButtonMouseAdapter(new EditarColunaButtonMouseAdapter());
            view.setRemoverColunaButtonMouseAdapter(new RemoverColunaButtonMouseAdapter());
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    class EditarColunaButtonMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.editarColuna(e);
            
            String id = view.getColunaID(e);
            String novoNome = view.getColunaNome(e);
            model.editarEstado(id, novoNome);
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }
    
    class RemoverColunaButtonMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.removerColuna(e);
            
            String id = view.getColunaID(e);
            String novoNome = view.getColunaNome(e);
            model.removeEstado(id);
            //procurar nos membros as tarefas associadas a este estado para remover as tarefas associadas
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    class AddicionarTarefaButtonMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            idTarefa++;
            String tituloDefault = "Tarefa";
            String descricaoDefault = "Descrição da Tarefa";
            String idTarefaString = "TAR-" + idTarefa; 
            String idColunaString = view.getColunaID(e); 
            
            model.addTarefa(idColunaString, idTarefaString, tituloDefault, descricaoDefault);
            
            view.addicionarTarefa(e, idTarefaString, tituloDefault, descricaoDefault);
            view.setTarefaMouseAdapter(e, new TarefaMouseAdapter());
            view.setEditarTarefaButtonMouseAdapter(e, new EditarTarefaButtonMouseAdapter());
            view.setRemoverTarefaButtonMouseAdapter(e, new RemoverTarefaButtonMouseAdapter());
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    //----StikerTarefa
    //---
    class TarefaMouseAdapter implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            view.tarefaMousePressed(e);
        }
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            view.tarefaMouseReleased(e);
            //
            ///
            ///
            ///continuar aqui!!!
        }
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
        //MouseMotionListener
        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
            view.tarefaMouseDragged(e);
        }
        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {}
    }
    
    class EditarTarefaButtonMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.editarTarefa(e);
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }
    
    class RemoverTarefaButtonMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            view.removerTarefa(e);
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }
}
