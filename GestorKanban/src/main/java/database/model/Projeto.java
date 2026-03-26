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
            output += e + ",";
        }
        
        if (output.isEmpty()){output = "";}else{output = output.substring(0, output.length() - 1);}
        
        return "{\"nome\": \"" + nome + "\", \"grupo\":" + grupo + ", \"estados\":[" + output + "]}";
    }


    public void addEstado(String nome){
        this.estados.add(new Estado(nome));
    }
    
    public void delEstado(int index){
        this.estados.remove(index);
    }
    public String getNomeEstado(int index){
        return this.estados.get(index).getNome();
    }
    public void changeNomeEstado(int index, String nome){
        this.estados.get(index).setNome(nome);
    }
    
    public void addTarefa(int indexEstado, String tarefa, String descricao){
        estados.get(indexEstado).addTarefa(tarefa, descricao);
    }
    
    public void delTarefa(int indexEstado, int indexTarefa){
        estados.get(indexEstado).delTarefa(indexTarefa);
    }
    
    public String getNomeTarefa(int indexEstado, int indexTarefa){
        return estados.get(indexEstado).getTarefa(indexTarefa).getNome();
    }
    
    public void changeNomeTarefa(int indexEstado, int indexTarefa, String nome){
        estados.get(indexEstado).getTarefa(indexTarefa).setNome(nome);
    }
    
    public String getDescricaoTarefa(int indexEstado, int indexTarefa){
        return estados.get(indexEstado).getTarefa(indexTarefa).getDescricao();
    }
    
    public void changeDescricaoTarefa(int indexEstado, int indexTarefa, String descricao){
        estados.get(indexEstado).getTarefa(indexTarefa).setDescricao(descricao);
    }
    
    public void addPessoa(String nome){
        grupo.addPessoa(nome);
    }
    
    public void delPessoa(int indexPessoa){
        grupo.delPessoa(indexPessoa);
    }
    
    public String getNomePessoa(int indexPessoa){
        return grupo.getNomePessoa(indexPessoa);
    }
   
    public void changeNomePessoa(int indexPessoa, String nome){
        grupo.changeNomePessoa(indexPessoa, nome);
    }
    public void assignPessoaToTarefa(int indexPessoa, int indexTarefa){
        // TO DO 
    }
    
}

class Estado {
    
    private String nome;
    private List<Tarefa> tarefas;

    public Estado(String nome) {
        this.nome = nome;
        this.tarefas =  new ArrayList<Tarefa>();
    }
    
    @Override
    public String toString() {
        String output = new String();
        for (Tarefa t : tarefas) {
            output += t + ",";
        }
        if (output.isEmpty()){output = "";}else{output = output.substring(0, output.length() - 1);}
        return "{\"nome\": \"" + nome + "\" , \"tarefas\":[" + output + "]}";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addTarefa(String tarefa, String descricao){
        this.tarefas.add(new Tarefa(tarefa,descricao));
    }
    
    public Tarefa delTarefa(int index){
        return tarefas.remove(index);
    }
    
    public Tarefa getTarefa(int index){
        return tarefas.get(index);
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
            output += p+",";
        }
        
        if (output.isEmpty()){output = "";}else{output = output.substring(0, output.length() - 1);}
        
        return "{ \"nome\": \"" + nome + "\", \"descricao\": \"" + descricao + "\", \"pessoas\":[" + output + "]}";
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
    
    public void addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
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
            output += p +",";
        }
        
        if (output.isEmpty()){output = "";}else{output = output.substring(0, output.length() - 1);}
        
        return "[" + output + ']';
    }
    
    public void addPessoa(String nome){
     pessoas.add(new Pessoa(nome));
    }
    
    public void delPessoa(int indexPessoa){
     pessoas.remove(indexPessoa);
    }
    
    public String getNomePessoa(int indexPessoa){
     return pessoas.get(indexPessoa).getNome();
    }
    
    public void changeNomePessoa(int indexPessoa, String nome){
     pessoas.get(indexPessoa).setNome(nome);
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


