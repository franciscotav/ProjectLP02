/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.model;

import repository.data.Repository;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author bernardos
*/
public class Model{
    
    private List<Projeto> projetos;
    private Repository repository;

    public Model( Repository repository) {
        this.projetos = new ArrayList<>();
        this.repository = repository;
    }
    
    
    // METODOS DO Model
    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void addProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }
    
    public void removeProjeto(int id) {
        for(Projeto p : projetos){
            if (p.getId()== id) {
            this.projetos.remove(p);
            break;
            }
        }
    }
    
    public Projeto getProjetoById(int id){
        for(Projeto p : projetos){
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
   
    
    // METODOS USADOS NO CONTROLADOR
    
    public void saveProjeto(String filePath, int id){
        Projeto projeto = getProjetoById(id);
        repository.saveToFile(filePath, projeto);
    }
    
    public void loadProjeto(String filePath){
        Projeto projeto = repository.loadFromFile(filePath);
        addProjeto(projeto);
       
    }
    
    
    //projeto
    public String getNomeProjeto(Integer idProjeto) {
        return this.getProjetoById(idProjeto).getNome();
    }

    public void setNomeProjeto(Integer idProjeto,String nome) {
        this.getProjetoById(idProjeto).setNome(nome);
    }
    
    //grupo
    
    public void addPessoaToGrupo(Integer idProjeto ,String idPessoa, String nome){
        Projeto projeto = this.getProjetoById(idProjeto);
        Pessoa pessoa = new Pessoa(idPessoa, nome);
        
        Grupo grupo = projeto.getGrupo();
        grupo.addPessoa(pessoa);

    }
    
    public void removerPessoaFromGrupo(Integer idProjeto ,String idPessoa){
        Projeto projeto = this.getProjetoById(idProjeto);
        
        for(Estado estado : projeto.getEstados()){
            for(Tarefa tarefa : estado.getTarefas()){
                for(String pessoa : tarefa.getIdPessoas()){
                    if(pessoa.equals(idPessoa)){
                        tarefa.getIdPessoas().remove(idPessoa);
                        break;
                    }
                }
            }
        }
        
        projeto.getGrupo().removerPessoa(idPessoa);
    }
    
    //tarefa
    
    public void addTarefa(Integer idProjeto ,String idEstado, String idTarefa, String titulo, String descricao){
        Projeto projeto = this.getProjetoById(idProjeto);    
        
        Estado estado = projeto.getEstadoById(idEstado);
        if(estado != null){
            estado.addTarefa(idTarefa,titulo,descricao);
        }
        
    }

    public void moverTarefa(Integer idProjeto ,String idEstadoOrigem, String idEstadoDestino, String idTarefaString){
        
        Projeto projeto = this.getProjetoById(idProjeto);    
        
        Estado estadoOrigem = projeto.getEstadoById(idEstadoOrigem);
        Estado estadoDestino = projeto.getEstadoById(idEstadoDestino);
        
        if(estadoOrigem != null){
            Tarefa tarefa = estadoOrigem.getTarefaById(idTarefaString);
            if(tarefa != null){
                estadoOrigem.removeTarefa(idTarefaString);
                estadoDestino.addTarefa(tarefa);
            }
        }
        
    }
    
    public void addPessoaToTarefa(Integer idProjeto ,String idPessoa, String idTarefa){
        Projeto projeto = this.getProjetoById(idProjeto);
        
        Pessoa pessoa = projeto.getGrupo().getPessoaById(idPessoa);
        if(pessoa != null)
            pessoa.addIdTarefa(idTarefa);
        for(Estado estado : projeto.getEstados()){
            Tarefa tarefa = estado.getTarefaById(idTarefa);
            if(tarefa != null){
                tarefa.addIdPessoa(idPessoa);
                break;
            }
        }
    }
    
    public void removePessoaFromTarefa(Integer idProjeto ,String idPessoa, String idTarefa){
        Projeto projeto = this.getProjetoById(idProjeto);
        
        Pessoa pessoa = projeto.getGrupo().getPessoaById(idPessoa);
        if(pessoa != null)
            pessoa.removeTarefa(idTarefa);
        for(Estado estado : projeto.getEstados()){
            Tarefa tarefa = estado.getTarefaById(idTarefa);
            if(tarefa != null){
                tarefa.removePessoa(idPessoa);
                break;
            }
        }
    }
    
    
    
    public void editarTarefa(Integer idProjeto ,String tarefaID, String novoTitulo, String novaDescricao){
        Projeto projeto = this.getProjetoById(idProjeto);
        
        for(Estado estado : projeto.getEstados()){
            for(Tarefa tarefa : estado.getTarefas()){
                if(tarefa.getId().equals(tarefaID)){
                    tarefa.setNome(novoTitulo);
                    tarefa.setDescricao(novaDescricao);
                    break;
                }
            }
        }
    }
    
    public void removerTarefa(Integer idProjeto ,String tarefaIf){
        Projeto projeto = this.getProjetoById(idProjeto);
        
        
        for(Estado estado : projeto.getEstados()){
            Tarefa tarefa = estado.getTarefaById(tarefaIf);
            if(tarefa != null){
                estado.removeTarefa(tarefaIf);
                projeto.getGrupo().removeTarefaID(tarefaIf);
                break;
            }
        }
    }
    
    //pessoa
    
    public void editarPessoa(Integer idProjeto ,String idPessoa, String novoNome){
        Projeto projeto = this.getProjetoById(idProjeto);
        projeto.getGrupo().editarPessoa(idPessoa, novoNome);
    }
    
     // estado
    public void addEstado(Integer idProjeto, String id, String nome){
        Projeto projeto = this.getProjetoById(idProjeto);
        projeto.addEstado(id, nome);
    }
    
    public void editarEstado(Integer idProjeto, String id, String novoNome){
        Projeto projeto = this.getProjetoById(idProjeto);
        for(Estado estado: projeto.getEstados()){
        if (estado.getId().equals(id)) {
            estado.setNome(novoNome);
            break;
        }
        }
    }
    
    public void removeEstado(Integer idProjeto, String id){
        Projeto projeto = this.getProjetoById(idProjeto);
        Estado estadoToRemove = null;
        
        for(Estado estado : projeto.getEstados()){
            if(estado.getId().equals(id)){
                estadoToRemove = estado;
                break;
            }
        }
        
        if(estadoToRemove != null){
            for(Tarefa tarefa : estadoToRemove.getTarefas()){
                projeto.getGrupo().removeTarefaID(tarefa.getId());
            }
            
            projeto.getEstados().remove(estadoToRemove);
        }
    }
}