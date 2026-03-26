/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author bernardos
 */
public class Database {
    private List<Projeto> projetos;

    public Database() {
        this.projetos = new ArrayList<Projeto>();
    }

    @Override
    public String toString() {
        
        String output = new String();
        for (Projeto p : projetos) {
            output += p;
        }
        
        if (projetos.isEmpty()) {output="[]";}
        
        return "Database{" + "projetos=" + output + '}';
    }
    
    
}

class Projeto{
    private String nome;
    private Grupo grupo;
    private List<Estado> estados;

    public Projeto(String nome) {
        this.nome = nome;
        this.grupo = new Grupo();
        this.estados = new ArrayList<Estado>();
    }

    @Override
    public String toString() {
        String output = new String();
        for (Estado e : estados) {
            output += e;
        }
        if (estados.isEmpty()) {output="[]";}
        return "Projeto{" + "nome=" + nome + ", grupo=" + grupo + ", estados=" + output + '}';
    }

    
}

class Estado {
    private String nome;
    private List<Tarefa> tarefas;

    @Override
    public String toString() {
        String output = new String();
        for (Tarefa t : tarefas) {
            output += t;
        }
        if (tarefas.isEmpty()) {output="[]";}
        return "Estado{" + "nome=" + nome + ", tarefas=" + output + '}';
    }

}

class Tarefa {

    private String nome;
    private String descricao;
    private List<Pessoa> pessoas;

    public Tarefa(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.pessoas = new ArrayList<Pessoa>();
    }

    @Override
    public String toString() {
        String output = new String();
        for (Pessoa p : pessoas) {
            output += p;
        }
        if (pessoas.isEmpty()) {output="[]";}
        return "Tarefa{" + "nome=" + nome + ", descricao=" + descricao + ", pessoas=" + output + '}';
    }
    
}

class Grupo{
    private List<Pessoa> pessoas;

    public Grupo() {
        this.pessoas = new ArrayList<Pessoa>();
    }

    @Override
    public String toString() {
        String output = new String();
        for (Pessoa p : pessoas) {
            output += p; 
        }
        if (pessoas.isEmpty()) {output="[]";}
        return "Grupo{" + "pessoas=" + output + '}';
    }
    


}

class Pessoa{
    private String nome;

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + '}';
    }
    
}


