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
public class Projeto {
    

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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    public String getId() {
        return id;
    }

    
}