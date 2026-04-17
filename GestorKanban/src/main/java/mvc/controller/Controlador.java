/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controller;
import mvc.view.*;
import database.model.*;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

/**
 *
 * @author FTCASA
 */
public class Controlador {
    MainWindow view;
    Projeto model;
    
    static int id = 0;
    
    public Controlador(MainWindow view, Projeto model){
        this.view = view;
        this.model = model;
        
        this.view.setMembroAddicionarMouseAdapter(new AddicionarMembroMouseAdapter());
    }
            
    class AddicionarMembroMouseAdapter implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e){
            id++;
            //addicionar metodos para fazer update do model;
            view.criarNovoResponsavel(id,"Responsavel " + id);
            view.setEditarMembroMouseAdapter(new EditarMembroMouseAdapter());
            //System.out.println("AddicionarMembroMouseAdapter");
            
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }
    }
    
    class EditarMembroMouseAdapter implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e){
            
            view.editarResponsavel(e);
            //addicionar metodos para fazer update do model;
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
        }
    }
    
}
