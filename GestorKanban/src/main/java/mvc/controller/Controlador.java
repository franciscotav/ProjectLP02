/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controller;

import mvc.view.*;
import database.model.*;
import repository.data.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author FTCASA
 */
public class Controlador {
    private Model model;
    private MainWindow view;
    
    private int projectSelectedID = 0;
    private int idPessoa = 0;
    private int idEstado = 0;
    private int idTarefa = 0;
    private int idProjeto = 0;
    
    public Controlador(MainWindow view, Model model) {
        this.view = view;
        this.model = model;
        this.view.setNovoProjetoListener(new NovoProjetoMouseAdapter());
        this.view.setCarregarProjetoMouseAdapter(new CarregarProjetoMouseAdapter());
    }

    class NovoProjetoMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String filePath = view.getPath();
            if(filePath.equals("")) return;
            
            idProjeto++;
            projectSelectedID = idProjeto;
            
            String[] split = filePath.split("\\\\");
            
            model.addProjeto(String.valueOf(projectSelectedID), split[split.length - 1]);
            
            view.adicionarComponentesProjeto(String.valueOf(projectSelectedID), model.getNomeProjeto(String.valueOf(projectSelectedID)));
            view.setMembroAddicionarMouseAdapter(new AddicionarMembroMouseAdapter());
            view.setAddicionarColunaButtonMouseAdapter(new AddicionarColunaButtonMouseAdapter());
            
            view.setGuardarProjetoMouseAdapter(new GuardarProjetoMouseAdapter());
            model.saveProjeto(filePath, String.valueOf(projectSelectedID));
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
    
    class CarregarProjetoMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String filePath = view.getPath();            
            model.loadProjeto(filePath);
            
            idProjeto++;
            projectSelectedID = idProjeto;
            
            model.setLastProjetID(String.valueOf(projectSelectedID));
            
            view.adicionarComponentesProjeto(String.valueOf(projectSelectedID), model.getNomeProjeto(String.valueOf(projectSelectedID)));
            view.setMembroAddicionarMouseAdapter(new AddicionarMembroMouseAdapter());
            view.setAddicionarColunaButtonMouseAdapter(new AddicionarColunaButtonMouseAdapter());
            
            view.setGuardarProjetoMouseAdapter(new GuardarProjetoMouseAdapter());
            
            int idPessoaTemp = 0;
            for(int i = 0; i < model.getGrupo(String.valueOf(projectSelectedID)).size(); i++){
                String nomeDefault = model.getGrupo(String.valueOf(projectSelectedID)).get(i).getNome();
                String idPessoaString = model.getGrupo(String.valueOf(projectSelectedID)).get(i).getId();
                
                String[] splitIdString = idPessoaString.split("-");
                
                int idFromPessoa = Integer.parseInt(splitIdString[splitIdString.length - 1]);
                if(idPessoaTemp < idFromPessoa){
                    idPessoaTemp = idFromPessoa; 
                }
                
                view.criarNovoResponsavel(idPessoaString, nomeDefault);
                view.setMembroMouseAdapter(new MembroMouseAdapter());
                view.setEditarMembroMouseAdapter(new EditarMembroMouseAdapter());
                view.setRemoverMembroMouseAdapter(new RemoverMembroMouseAdapter());
            }
            
            idPessoa = idPessoaTemp;
            
            int idEstadoTemp = 0;
            int idTarefaTemp = 0;
            for(int i = 0; i < model.getEstados(String.valueOf(projectSelectedID)).size(); i++){
                
                String nomeDefault = model.getEstados(String.valueOf(projectSelectedID)).get(i).getNome();
                String idEstadoString = model.getEstados(String.valueOf(projectSelectedID)).get(i).getId();
                
                String[] splitEstadosIdString = idEstadoString.split("-");
                
                int idFromEstado = Integer.parseInt(splitEstadosIdString[splitEstadosIdString.length - 1]);
                if(idEstadoTemp < idFromEstado){
                    idEstadoTemp = idFromEstado; 
                }
                
                view.addicionarColunaTarefa(idEstadoString, nomeDefault);
                view.setAddicionarTarefaButtonMouseAdapter(new AddicionarTarefaButtonMouseAdapter());
                view.setEditarColunaButtonMouseAdapter(new EditarColunaButtonMouseAdapter());
                view.setRemoverColunaButtonMouseAdapter(new RemoverColunaButtonMouseAdapter());
                
                for(int j = 0; j < model.getEstados(String.valueOf(projectSelectedID)).get(i).getTarefas().size(); j++){
                    
                    String tituloDefault = model.getEstados(String.valueOf(projectSelectedID)).get(i).getTarefas().get(j).getNome();
                    String descricaoDefault = model.getEstados(String.valueOf(projectSelectedID)).get(i).getTarefas().get(j).getDescricao();
                    String idTarefaString = model.getEstados(String.valueOf(projectSelectedID)).get(i).getTarefas().get(j).getId();
                    String idColunaString = idEstadoString; 
                    
                    String[] splitTarefaIdString = idTarefaString.split("-");
                
                    int idFromTarefa = Integer.parseInt(splitTarefaIdString[splitTarefaIdString.length - 1]);
                    if(idTarefaTemp < idFromTarefa){
                        idTarefaTemp = idFromTarefa; 
                    }

                    view.addicionarTarefa(idTarefaString, tituloDefault, descricaoDefault);
                    view.setTarefaMouseAdapter(new TarefaMouseAdapter());
                    view.setEditarTarefaButtonMouseAdapter(new EditarTarefaButtonMouseAdapter());
                    view.setRemoverTarefaButtonMouseAdapter(new RemoverTarefaButtonMouseAdapter());
                }
            }
            
            idEstado = idEstadoTemp;
            idTarefa = idTarefaTemp;
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
    
    //-----MENUPROJETOS
    //----
    class GuardarProjetoMouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String filePath = view.getPath();
            if(filePath.equals("")) return;
                        
            model.saveProjeto(filePath, String.valueOf(projectSelectedID));
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
            
            model.addPessoaToGrupo(String.valueOf(projectSelectedID), idPessoaString, nomeDefault);
            
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
            model.editarPessoa(String.valueOf(projectSelectedID), id, novoNome);
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
            model.removerPessoaFromGrupo(String.valueOf(projectSelectedID), id);
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
            boolean associarPessoaATarefa = view.membroMouseReleased(e);
            
            if(associarPessoaATarefa){
                String idPessoa = view.getResponsavelID(e);
                String idTarefa = view.getResponsavelLastStikerID(e);
                
                model.addPessoaToTarefa(String.valueOf(projectSelectedID), idPessoa,idTarefa);
            }
            
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
            
            model.addEstado(String.valueOf(projectSelectedID), idEstadoString, nomeDefault);
            
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
            model.editarEstado(String.valueOf(projectSelectedID), id, novoNome);
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
            model.removeEstado(String.valueOf(projectSelectedID), id);
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
            
            model.addTarefa(String.valueOf(projectSelectedID), idColunaString, idTarefaString, tituloDefault, descricaoDefault);
            
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
        String idEstadoOrigem;
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            idEstadoOrigem = view.getColunaID(e);
            view.tarefaMousePressed(e);
        }
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            String idTarefa = view.getTarefaID(e);

            view.tarefaMouseReleased(e);
                    
            String idEstadoDestino = view.getColunabyTarefaID(idTarefa);
            
            if(!(idEstadoOrigem.equals(idEstadoDestino))){
                String idTarefaString = view.getTarefaID(e);
                model.moverTarefa(String.valueOf(projectSelectedID), idEstadoOrigem, idEstadoDestino, idTarefaString);
            }
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
            
            String idTarefaString = view.getTarefaID(e);
            String novoTitulo = view.getTarefaTitulo(e);
            String novaDescricao = view.getTarefaDescricao(e);
            model.editarTarefa(String.valueOf(projectSelectedID), idTarefaString, novoTitulo, novaDescricao);
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
            String idTarefaString = view.getTarefaID(e);
            model.removerTarefa(String.valueOf(projectSelectedID), idTarefaString);
            
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
