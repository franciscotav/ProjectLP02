/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestorkanban;
import window.frame.*;
import database.model.*;

/**
 *
 * @author FranciscoTavares
 */
public class GestorKanban {

    public static void main(String[] args) {
        
        
        Projeto proj1 = new Projeto("projeto1");
        
        proj1.addEstado("Coluna 1");
        proj1.addEstado("coluna 2");
        
        proj1.addPessoa("To");
        proj1.addPessoa("Ze");
        
        proj1.addTarefa(1, "Tarefa2", "tarefa da coluna 2");
        proj1.addTarefa(0, "Tarefa1", "tarefa da coluna 1");
        
        //To do falta assign de tarefa 
       
        

        System.out.println(proj1);
        
        //Window window = new Window();
        

    }
}
