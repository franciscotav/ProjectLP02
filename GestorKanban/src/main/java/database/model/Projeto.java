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

public class Projeto{
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
        return "{" + "\"nome\":" + '"' + nome + "\"" + ", \"grupo\":" + grupo + ", \"estados\":" + output + '}';
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}

class Grupo{
    private List<Pessoa> pessoas;

    public Grupo() {
        this.pessoas = new ArrayList<Pessoa>();
        pessoas.add(new Pessoa("ze"));
        pessoas.add(new Pessoa("to"));
    }

    @Override
    public String toString() {
        String output = new String();
        for (Pessoa p : pessoas) {
            output += p +",";
        }
        output = output.substring(0, output.length() - 1);
        return "[" + output + ']';
    }
    


}

class Pessoa{
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    
    
    @Override
    public String toString() {
        return "{\"nome\":\"" + nome + "\"}";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}


