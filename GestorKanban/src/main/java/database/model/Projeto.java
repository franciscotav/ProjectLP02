/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bernardos
 */
public class Projeto {
    
    private int id;
    private String nome;
    
    private Grupo grupo;
    private List<Estado> estados;

    public Projeto(int id, String nome) {
        this.id = id;
        this.nome = nome;
        
        this.grupo = new Grupo();
        this.estados = new ArrayList<>();
    }
    
    public void addEstado(String id, String nome){
        this.estados.add(new Estado(id, nome));
    }
    
    public void addPessoa(String id, String nome){
        Pessoa pessoa = new Pessoa(id, nome);
        grupo.addPessoa(pessoa);
    }
    
    public void addTarefa(String idEstado, String idTarefa, String titulo, String descricao){
        Estado estado = getEstadoById(idEstado);
        if(estado != null){
            estado.addTarefa(idTarefa,titulo,descricao);
        }
        
    }
    
    public void moverTarefa(String idEstadoOrigem, String idEstadoDestino, String idTarefaString){
        Estado estadoOrigem = getEstadoById(idEstadoOrigem);
        Estado estadoDestino = getEstadoById(idEstadoDestino);
        
        if(estadoOrigem != null){
            Tarefa tarefa = estadoOrigem.getTarefaById(idTarefaString);
            if(tarefa != null){
                estadoOrigem.removeTarefa(idTarefaString);
                estadoDestino.addTarefa(tarefa);
            }
        }
        
    }
    
    public void addPessoaToTarefa(String idEstado, String idTarefa, String idPessoa){
        Estado estado = getEstadoById(idEstado);
        if(estado != null){
            Tarefa tarefa = estado.getTarefaById(idTarefa);
            tarefa.addPessoa(grupo.getPessoaById(idPessoa));
        }
        
    }
    
    public void editarPessoa(String id, String novoNome){
        grupo.editarPessoa(id, novoNome);
    }
    
    public void editarEstado(String id, String novoNome){
        for(Estado estado: estados){
            if (estado.getId().equals(id)) {
                estado.setNome(novoNome);
                break;
            }
        }
    }
    
    public void editarTarefa(String tarefaID, String novoTitulo, String novaDescricao){
        for(Estado estado : estados){
            for(Tarefa tarefa : estado.getTarefas()){
                if(tarefa.getId().equals(tarefaID)){
                    tarefa.setNome(novoTitulo);
                    tarefa.setDescricao(novaDescricao);
                    break;
                }
            }
        }
    }
    
    public void removerTarefa(String tarefaID){
        for(Estado estado : estados){
            Tarefa tarefa = estado.getTarefaById(tarefaID);
            if(tarefa != null){
                estado.removeTarefa(tarefaID);
                grupo.removeTarefaID(tarefaID);
                break;
            }
        }
    }
    
    public void removerPessoa(String id){
        for(Estado estado : estados){
            for(Tarefa tarefa : estado.getTarefas()){
                for(Pessoa pessoa : tarefa.getPessoas()){
                    if(id == pessoa.getId()){
                        tarefa.getPessoas().remove(pessoa);
                        break;
                    }
                }
            }
        }
        
        grupo.removerPessoa(id);
    }
    
    public void removeEstado(String id){
        Estado estadoToRemove = null;
        for(Estado estado : estados){
            if(estado.getId().equals(id)){
                estadoToRemove = estado;
                break;
            }
        }
        
        if(estadoToRemove != null){
            for(Tarefa tarefa : estadoToRemove.getTarefas()){
                grupo.removeTarefaID(tarefa.getId());
            }
            
            estados.remove(estadoToRemove);
        }
    }
    
    public Estado getEstadoById(String id){
        for(Estado e : estados){
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    public int getId() {
        return id;
    }

    
}