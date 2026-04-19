/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.model;

import java.util.List;

/**
 *
 * @author bernardos
 */
public class Pessoa{
    
    private String nome;
    private String id;
    private List<Tarefa> tarefas;

    public Pessoa(String id, String nome) {
        this.nome = nome;
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public List<Tarefa> getTarefa() {
        return tarefas;
    }

    public void addTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }
    
    public void removedTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

}


