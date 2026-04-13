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
public class Model{
    
    private List<Projeto> projetos = new ArrayList<>();

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void addProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }
    
    public void removeProjeto(String id) {
        for(Projeto p : projetos){
            if (p.getId().equals(id)) {
            this.projetos.remove(p);
            break;
            }
        }
    }
    
    public Projeto getProjetoById(String id){
        for(Projeto p : projetos){
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
}
class Projeto{
    
    private String nome;
    private Grupo grupo;
    private List<Estado> estados;
    private String id;

    public Projeto(String id, String nome, Grupo grupo) {
        
        this.nome = nome;
        this.grupo = grupo;
        this.estados = new ArrayList<>();
        this.id = id;
        
    }
    
    public void addEstado(Estado estado){
        this.estados.add(estado);
    }
    
    public void removeEstado(String id){
        for(Estado e : estados){
            if (e.getId().equals(id)) {
            this.estados.remove(e);
            break;
            }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}

class Estado {
    
    private String nome;
    private List<Tarefa> tarefas;
    private String id;

    public Estado(String id, String nome) {
        this.nome = nome;
        this.tarefas =  new ArrayList<>();
        this.id = id;
    }
    
    
    public void addTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }
    
    public void removeTarefa(String id){
        for(Tarefa t : tarefas){
            if (t.getId().equals(id)) {
            this.tarefas.remove(t);
            break;
            }
        }
    }
    
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    } 
}

class Tarefa {

    private String nome;
    private String descricao;
    private List<Pessoa> pessoas;
    private String id;

    public Tarefa(String id, String nome, String descricao) {
        
        this.nome = nome;
        this.descricao = descricao;
        this.pessoas = new ArrayList<>();
        this.id = id;
    }
    
    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }
    
    public void removePessoa(String id) {
        for(Pessoa p : pessoas){
            if (p.getId().equals(id)) {
            this.pessoas.remove(p);
            break;
            }
        }
    }
    
    public Pessoa getPessoaById(String id){
        for(Pessoa p : pessoas){
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    public List<Pessoa> getPessoas() {
        return pessoas;
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

    public String getId() {
        return id;
    }
    
}

class Grupo{
    private List<Pessoa> pessoas;
    private String id;

    public Grupo(String id) {
        this.pessoas = new ArrayList<>();
        this.id = id;
    }

    public void addPessoa(Pessoa pessoa){
     pessoas.add(pessoa);
    }
    
    public void removePessoa(String id){
        for(Pessoa p : pessoas){
            if (p.getId().equals(id)) {
            this.pessoas.remove(p);
            break;
            }
        }
    }
    
    public Pessoa getTarefaById(String id){
        for(Pessoa p : pessoas){
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public String getId() {
        return id;
    }

}

class Pessoa{
    
    private String nome;
    private String id;

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

}


