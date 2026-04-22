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
public class Grupo{
    private List<Pessoa> pessoas;

    public Grupo() {
        this.pessoas = new ArrayList<>();
    }

    public void addPessoa(Pessoa pessoa){
        pessoas.add(pessoa);
    }
    
    public void editarPessoa(String id, String novoNome){
        for(Pessoa pessoa : pessoas){
            if(pessoa.getId().equals(id)){
                pessoa.setNome(novoNome);
                break;
            }
        }
    }
    
    public void removeTarefaID(String tarefaRemoverID){
        for(Pessoa pessoa : pessoas){
            for(String tarefaID : pessoa.getTarefasID()){
                if(tarefaID.equals(tarefaRemoverID)){
                    pessoa.getTarefasID().remove(tarefaID);
                    break;
                }
            }
        }
    }
    
    public void removerPessoa(String id){
        for(Pessoa pessoa : pessoas){
            if(pessoa.getId().equals(id)){
                pessoas.remove(pessoa);
                break;
            }
        }
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