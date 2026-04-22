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
public class Tarefa {
    private String nome;
    private String descricao;
    private List<String> idPessoas;
    private String id;

    public Tarefa(String id, String nome, String descricao) {
        
        this.nome = nome;
        this.descricao = descricao;
        this.idPessoas = new ArrayList<>();
        this.id = id;
    }
    
    public void addIdPessoa(String idPessoa) {
        this.idPessoas.add(idPessoa);
    }
    
    public void removePessoa(String id) {
        for(String p : idPessoas){
            if (p.equals(id)) {
            this.idPessoas.remove(p);
            break;
            }
        }
    }
    
    public List<String> getIdPessoas() {
        return idPessoas;
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
