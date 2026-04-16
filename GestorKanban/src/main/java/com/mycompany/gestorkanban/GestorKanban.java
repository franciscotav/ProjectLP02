/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestorkanban;

import java.util.ArrayList;
import repository.data.Repository;
import database.model.*;
import mvc.controller.Controlador;
import mvc.view.MainWindow;

/**
 *
 * @author FranciscoTavares
 */
public class GestorKanban {

    public static void main(String[] args) {
        MainWindow view = new MainWindow();
        ArrayList<Projeto> model = new ArrayList<>();
        Controlador controlador = new Controlador(view, model);
    }    
}
