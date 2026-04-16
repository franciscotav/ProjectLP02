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
public class Model{
    
    private List<Projeto> projetos = new ArrayList<>();

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void addProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }
    
    public void removeProjeto(int id) {
        for(Projeto p : projetos){
//            if (p.getId().equals(id)) {
//            this.projetos.remove(p);
//            break;
//            }
        }
    }
    
    public Projeto getProjetoById(int id){
        for(Projeto p : projetos){
//            if (p.getId().equals(id)) {
//                return p;
//            }
        }
        return null;
    }
    
}