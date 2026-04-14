/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestorkanban;

import aquitetura.janela.MainWindow;
import database.model.*;
import repository.data.*;
/**
 *
 * @author FranciscoTavares
 */
public class GestorKanban {

    public static void main(String[] args) {
        
        // 1. Criar a Base: Pessoas
        Pessoa p1 = new Pessoa("PES-01", "Bernardo Silva");
        Pessoa p2 = new Pessoa("PES-02", "Ana Rita");
        Pessoa p3 = new Pessoa("PES-03", "Carlos Mendes");

        // 2. Criar o Grupo do Projeto
        Grupo grupoBackend = new Grupo();
        grupoBackend.addPessoa(p1);
        grupoBackend.addPessoa(p2);

        // 3. Criar as Tarefas e atribuir responsáveis
        Tarefa t1 = new Tarefa("TAR-01", "Modelar Base de Dados", "Criar as tabelas e relações em SQL");
        t1.addPessoa(p1); // Bernardo está encarregue desta

        Tarefa t2 = new Tarefa("TAR-02", "Desenvolver o Repository", "Fazer a conversão para JSON manual");
        t2.addPessoa(p1);
        t2.addPessoa(p2); // Bernardo e Ana trabalham juntos nesta

        Tarefa t3 = new Tarefa("TAR-03", "Criar Interface Gráfica", "Desenhar os botões e janelas");
        t3.addPessoa(p3); // Carlos fica com o design

        // 4. Criar os Estados e distribuir as tarefas
        Estado estadoFazer = new Estado("EST-01", "A Fazer");
        estadoFazer.addTarefa(t3); // Interface ainda não começou

        Estado estadoAdecorrer = new Estado("EST-02", "Em Progresso");
        estadoAdecorrer.addTarefa(t2); // Repository está a ser feito agora

        Estado estadoConcluido = new Estado("EST-03", "Concluído");
        estadoConcluido.addTarefa(t1); // BD já está pronta

        // 5. Criar o Topo da Pirâmide: O Projeto
        Projeto meuProjeto = new Projeto("PROJ-01", "App Gestão MVC", grupoBackend);
        meuProjeto.addEstado(estadoFazer);
        meuProjeto.addEstado(estadoAdecorrer);
        meuProjeto.addEstado(estadoConcluido);
        
        Repository repo = new Repository();
        System.out.println(repo.formatarProjeto(meuProjeto));

        //To do falta assign de tarefa 
       
//        System.out.println(proj1);
//        
//        Window window = new Window();
        
//          MainWindow window = new MainWindow();
    }
}
