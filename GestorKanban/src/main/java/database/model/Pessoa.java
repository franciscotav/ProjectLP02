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
public class Pessoa{
    
    private String nome;
    private String id;
    private List<String> idTarefas;

    public Pessoa(String id, String nome) {
        this.nome = nome;
        this.id = id;
        this.idTarefas = new ArrayList<>();
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

    public List<String> getTarefaID() {
        return idTarefas;
    }

    public void addTarefa(String tarefaID) {
        idTarefas.add(tarefaID);
    }
    
    public void removedTarefa(String tarefaID) {
        idTarefas.remove(tarefaID);
    }

}


