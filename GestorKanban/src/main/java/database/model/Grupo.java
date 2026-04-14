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
public class Grupo{
    private List<Pessoa> pessoas;

    public Grupo() {
        this.pessoas = new ArrayList<>();
        
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
}