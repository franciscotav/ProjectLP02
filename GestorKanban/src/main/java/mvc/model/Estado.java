/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bernardos
 */
public class Estado {
    
    //atributos
    private String nome;
    private List<Tarefa> tarefas;
    private String id;
    
    //construtor
    public Estado(String id, String nome) {
        this.nome = nome;
        this.tarefas =  new ArrayList<>();
        this.id = id;
    }
    
    //actions
    public void addTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }
    
    public void addTarefa(String idTarefa, String titulo, String descricao){
        this.tarefas.add(new Tarefa(idTarefa, titulo, descricao));
    }
    
    public void removeTarefa(String id){
        for(Tarefa t : tarefas){
            if (t.getId().equals(id)) {
                this.tarefas.remove(t);
                break;
            }
        }
    }
    
    //getters
    public Tarefa getTarefaById(String id){
        for(Tarefa t : tarefas){
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    } 
    
    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
}