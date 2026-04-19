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
public class Tarefa {
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
        pessoa.addTarefa(this);
    }
    
    public void removePessoa(String id) {
        for(Pessoa p : pessoas){
            if (p.getId().equals(id)) {
            this.pessoas.remove(p);
            p.removedTarefa(this);
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
