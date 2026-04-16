/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controller;
import mvc.view.*;
import database.model.*;
import java.util.ArrayList;

/**
 *
 * @author FTCASA
 */
public class Controlador {
    MainWindow view;
    ArrayList<Projeto> model;
    
    public Controlador(MainWindow view, ArrayList<Projeto> model){
        this.view = view;
        this.model = model;
    }
    
    //---Projetos---
    public void novoProjeto(){
        
    }
    public void carregarProjeto(){
        
    }
    public void gravarProjeto(){
        
    }
            
    
    //----Gestao da Equipa----
    //Adicionar membros a equipa do projeto no index 0
    public void addicionarMembro(int projetoID, String Nome){
        
    }
    
    
}
