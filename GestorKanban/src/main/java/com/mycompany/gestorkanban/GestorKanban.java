/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestorkanban;
import window.frame.*;


/**
 *
 * @author FranciscoTavares
 */
public class GestorKanban {

    public static void main(String[] args) {
      
        Window window = new Window();
        window.add(new Quadro());
        
        window.setVisible(true);

    }
}
