/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.data;
import database.model.*;
import java.util.ArrayList;
import java.util.Map;
//import trabalho2.IODataClass;
/**
 *
 * @author bernardos
 * 
 * class Repository
 * Classe encarregue de Escrever/Ler Ficheiros e Criar o model 
 * 
 * Atributos
 * String file_path do csv
 * 
 * Construtor(file path)
 * 
 *metodos
 * 
 * load
 * - lê os dados
 * - cria o model
 * - da load dos dados para o model
 * - return model
 * 
 * save (model)
 *  - pega no model e escreve no file

 *  
 * 
      
        
 * 
 */

public class Repository {
    
    Map<String,Pessoa> pessoasObj;

    public Repository() {
    }
    
    public Projeto extrairProjeto(String string){ 

        return null;
    }
    public Estado extrairEstado(String string){ //"id": "EST-02","nome": "Em Progresso", "tarefas": [
        /*TO-DO 
        
        - adicionar start e end o numero de car da string identificada
        - refazer o extrair estado usando um cursor de incio ao fim 
        - 
        
        
        */ 
        int start = string.indexOf("\"id\": \"");
        int end = string.indexOf("\"", start);
        String id = string.substring(start, end);
        
        start = string.indexOf("\"nome\": ");
        end = string.indexOf("\"", start);
        String nome = string.substring(start, end);
        
        Estado estado = new Estado(id,nome);
        
        start = string.indexOf("\"tarefas\": [");
        String resto = string.substring(start);
        
        int chavetas = 0;
        int inicioResto = resto.indexOf("{");
        int posicaoAtual = inicioResto;
        
        while (posicaoAtual < resto.length()) {
            char letra = resto.charAt(posicaoAtual);
                if (letra == '{') chavetas++;
                if (letra == '}') chavetas--;
            if (chavetas == 0) {
                String tarefa = resto.substring(start, posicaoAtual + 1);
                estado.addTarefa(this.extrairTarefa(tarefa));
                if (start == -1){
                break;}
            }
        }
    return null;
    }
    public Grupo extrairGrupo(String string){
        Grupo grupo = new Grupo(); // [ {pessoa1} , {pessoa2} ]]
        int indicador = 0;
        while (true) {
            int start = string.indexOf("{", indicador);
            if (start == -1){
                break;
            }
            else{

                int end = string.indexOf("}", start);
                String pessoa = string.substring(start, end);

                indicador += end;
                grupo.addPessoa(this.extrairPessoa(pessoa));
            }   
        }
        return grupo;
    }
    public Tarefa extrairTarefa(String string){
   
        int start = string.indexOf("\"id\": \"");
        int end = string.indexOf("\"", start);
        String id = string.substring(start, end);
        
        start = string.indexOf("\"nome\": ");
        end = string.indexOf("\"", start);
        String nome = string.substring(start, end);
        
        
        start = string.indexOf("\"descricao\" :");
        end = string.indexOf("\"", start);
        String descricao = string.substring(start, end);
        
        Tarefa tarefa = new Tarefa(id,nome,descricao);
        
        start = string.indexOf(": [");
        end = string.indexOf("]", start);
        String pessoas = string.substring(start, end);
        
        int indicador = 0;
        while (true) {
            start = pessoas.indexOf("{", indicador);
            if (start == -1){
                break;
            }
            else{

                end = pessoas.indexOf("}", start);
                String pessoa = string.substring(start, end);
                
                start = pessoa.indexOf("\"id\": \"");
                end = pessoa.indexOf("\"", start);
                String idPessoa = string.substring(start, end);
                
                indicador += end;
                tarefa.addPessoa(pessoasObj.get(idPessoa));
            }
        }
        return tarefa;
    }   
        

    public Pessoa extrairPessoa(String string){ //{"id": "PES-01","nome": "Bernardo Silva"}
        int start = string.indexOf("\"id\": \"");
        int end = string.indexOf("\"", start);
        String id = string.substring(start, end);
        
        start = string.indexOf("\"nome\": ");
        end = string.indexOf("\"", start);
        String nome = string.substring(start, end);
        
        Pessoa pessoa = new Pessoa(id,nome);
        this.pessoasObj.put(id, pessoa);
        return pessoa;
        
    }
    
    
    private String formatarPessoa(Pessoa p) {
        return "{\"id\":\"" + p.getId() + "\", \"nome\":\"" + p.getNome() + "\"}";
    }

    private String formatarGrupo(Grupo g) {
        
        StringBuilder sb = new StringBuilder();
        ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) g.getPessoas();
    
         for (Pessoa p : pessoas){
            sb.append(formatarPessoa(p)).append(",");
        } 
        
        if (sb.length() > 0) {sb.deleteCharAt(sb.length() - 1);}
        
        return "[" + sb.toString() + "]";
        
    }

    private String formatarTarefa(Tarefa t) {
        
        StringBuilder sb = new StringBuilder();
        ArrayList<Pessoa> pessoas = (ArrayList<Pessoa>) t.getPessoas();
        
        for (Pessoa p : pessoas){
            sb.append(formatarPessoa(p)).append(",");
        } 
        
        if (sb.length() > 0) {sb.deleteCharAt(sb.length() - 1);}
        
        return "{\"id\":\"" + t.getId() + "\", \"nome\": \"" + t.getNome() + "\", \"descricao\": \"" + t.getDescricao() + "\"pessoas\":[" + sb.toString() + "]}";
    }

    private String formatarEstado(Estado e) {
        
        StringBuilder sb = new StringBuilder();
        ArrayList<Tarefa> tarefas = (ArrayList<Tarefa>) e.getTarefas();
    
         for (Tarefa t : tarefas){
            sb.append(formatarTarefa(t)).append(",");
        } 
        
        if (sb.length() > 0) {sb.deleteCharAt(sb.length() - 1);}
        
        return "{\"id\":\"" + e.getId() + "\", \"nome\": \"" + e.getNome() + "\" , \"tarefas\":[" + sb.toString() + "]}";
    }

    public String formatarProjeto(Projeto p) {
        
        StringBuilder sbEstado = new StringBuilder();
        StringBuilder sbGrupo = new StringBuilder();
        
        sbGrupo.append(formatarGrupo(p.getGrupo())).append(",");
        
        ArrayList<Estado> estados = (ArrayList<Estado>) p.getEstados();
    
         for (Estado e : estados){
            sbEstado.append(formatarEstado(e)).append(",");
        } 
        
        if (sbEstado.length() > 0) {sbEstado.deleteCharAt(sbEstado.length() - 1);}
        if (sbGrupo.length() > 0) {sbGrupo.deleteCharAt(sbGrupo.length() - 1);}
        
        return "{\"id\":\"" + p.getId() + "\", \"nome\": \"" + p.getNome()+ "\", \"grupo\":" + sbGrupo.toString() + ", \"estados\":[" + sbEstado.toString() + "]}";
    }

}
