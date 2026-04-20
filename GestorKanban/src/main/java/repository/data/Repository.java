/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.data;
import database.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import trabalho2.IODataClass;
/**
 * Data Layer / Repository
 * Faz a ponte entre os objetos em memória (árvore do 'Projeto') e o formato JSON.
 * Usa a lib Gson para o parsing e o IODataClass para I/O no file system.
 * * @author bernardos
 * * Breakdown técnico:
 * - saveToFile(): Faz o dump do Model para uma string JSON escreve no ficherio atraves de IODataClass.
 * - loadFromFile(): Faz o fetch do data array, o parse via Gson e arranca o post-processing.
 * - removerClone() [Internal]: Garante a referential integrity. 
 * Como o Gson por default cria clones para o mesmo ID, este método usa o 'Grupo' 
 * como Single Source of Truth. Faz um loop pelas 'Tarefas' e dá replace aos clones 
 * pelas Pessoas reais.
 * Exemplo de uso:
 * 
 * repo.saveToFile("projeto_1.json", Projeto projeto1)
 * Projeto projeto1 = repo.loadFromFile("projeto_1.json")
 * 
 */

public class Repository {
    
    private Gson gson;
    private IODataClass ioData;
   
    public Repository() {
        this.gson = new GsonBuilder().create();
        this.ioData = new IODataClass();
    }
    
    public void saveToFile(String filePath, Projeto projeto) {
        String output = gson.toJson(projeto);
        String[] data = new String[]{ output };
        ioData.writeData(filePath, data);
    }
    
    public Projeto loadFromFile(String filePath) {
        
        String[] data;
        Projeto projetoCarregado = null;
        
        data = ioData.loadData(filePath);
        String input = data[0];
        projetoCarregado = gson.fromJson(input, Projeto.class);
        
        //remover objetos clone NÃO É PRECISO MAIS 
        //if (projetoCarregado != null) {
        //    removerClone(projetoCarregado);
        //}

        return projetoCarregado;
    }
    /*
    private void removerClone(Projeto projeto){ // TO BE REMOVED
        Map<String, Pessoa> dicionarioPessoas = new HashMap<>();
            
        if (projeto.getGrupo() != null) {
            for (Pessoa pessoaVerdadeira : projeto.getGrupo().getPessoas()) {
                dicionarioPessoas.put(pessoaVerdadeira.getId(), pessoaVerdadeira);
            }
        }

        // 2. Navegar pelas Tarefas e trocar os clones pelas pessoas verdadeiras
        if (projeto.getEstados() != null) {
            for (Estado estado : projeto.getEstados()) {
                
                if (estado.getTarefas() != null) {
                    for (Tarefa tarefa : estado.getTarefas()) {
                        
                        List<Pessoa> clonesParaLer= new ArrayList<>(tarefa.getPessoas());
                        
                        for (Pessoa clone : clonesParaLer) {
                            Pessoa real = dicionarioPessoas.get(clone.getId());
                            if (real != null) {
                                tarefa.removePessoa(clone.getId());
                                tarefa.addPessoa(real);
                            }
                        }
                    }
                }
            }
        }
    }*/
}
