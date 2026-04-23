/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestorkanban;

import mvc.repository.Repository;
import mvc.model.Model;
import mvc.controller.Controlador;
import mvc.view.MainWindow;

/**
 *
 * @author FranciscoTavares
 */
public class GestorKanban {
    
    public static void main(String[] args) {
        MainWindow view = new MainWindow();
        Repository repository = new Repository();
        Model model = new Model(repository);
        
        Controlador controlador = new Controlador(view, model);
    }   
}
