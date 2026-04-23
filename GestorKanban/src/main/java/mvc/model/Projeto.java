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
public class Projeto {
    
    private String id;
    private String nome;
    private Grupo grupo;
    private List<Estado> estados;

    public Projeto(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.grupo = new Grupo();
        this.estados = new ArrayList<>();
    }
    
    public void addEstado(String id, String nome){
        this.estados.add(new Estado(id, nome));
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
    
    public void setId(String id){
        this.id = id;
    }
    
}