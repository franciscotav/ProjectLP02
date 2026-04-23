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
public class Pessoa{
    
    //atributos
    private String nome;
    private String id;
    private List<String> idTarefas;

    //construtor
    public Pessoa(String id, String nome) {
        this.nome = nome;
        this.id = id;
        this.idTarefas = new ArrayList<>();
    }
    
    //getters
    public String getNome() {
        return nome;
    }
    
    public String getId() {
        return id;
    }

    public List<String> getTarefasID() {
        return idTarefas;
    }
    
    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //actions
    public void addIdTarefa(String tarefaID) {
        idTarefas.add(tarefaID);
    }
    
    public void removeTarefa(String tarefaID) {
        idTarefas.remove(tarefaID);
    }

}


