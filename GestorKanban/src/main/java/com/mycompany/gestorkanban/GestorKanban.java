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
        Repository repository = new Repository();
        Model model = new Model(repository);
        
        Controlador controlador = new Controlador(view, model);
    }   
    /*
     public static void main(String[] args) {

    // 1. Instanciar o Model principal
        Repository repository = new Repository();
        Model model = new Model(repository);

        String path = "kanban_completo.json";
        int pId = 1;

        System.out.println("--- Iniciando Teste Geral do Model ---");

        // 2. Testes de PROJETO
        model.addProjeto(new Projeto(pId, "Projeto Alpha"));
        model.setNomeProjeto(pId, "Projeto Kanban Profissional"); // Teste editar nome
        System.out.println("Projeto criado: " + model.getNomeProjeto(pId));

        // 3. Testes de ESTADO
        model.addEstado(pId, "E1", "To Do");
        model.addEstado(pId, "E2", "In Progress");
        model.addEstado(pId, "E3", "Review");
        model.addEstado(pId, "E4", "Old State");
        
        model.editarEstado(pId, "E3", "Done"); // Review -> Done
        model.removeEstado(pId, "E4");        // Remove o estado desnecessário
        System.out.println("Estados configurados e validados.");

        // 4. Testes de GRUPO / PESSOA
        model.addPessoaToGrupo(pId, "U1", "Francisco");
        model.addPessoaToGrupo(pId, "U2", "Bernardo");
        model.editarPessoa(pId, "U2", "Bernardo Silva"); // Teste editar pessoa
        System.out.println("Equipa adicionada.");

        // 5. Testes de TAREFA
        // Adicionar tarefas
        model.addTarefa(pId, "E1", "T1", "Configurar Maven", "Instalar dependências GSON");
        model.addTarefa(pId, "E1", "T2", "Criar Entidades", "Desenvolver classes POJO");
        
        // Editar tarefa
        model.editarTarefa(pId, "T1", "Configurar Maven/GSON", "Adicionar GSON ao pom.xml");

        // Atribuir pessoas às tarefas
        model.addPessoaToTarefa(pId, "U1", "T1");
        model.addPessoaToTarefa(pId, "U2", "T1"); // T1 agora tem 2 pessoas

        // Mover tarefa (Simular progresso)
        model.moverTarefa(pId, "E1", "E2", "T1"); // T1 passa de To Do para In Progress
        System.out.println("Tarefas criadas, editadas e movidas.");

        // 6. Teste de REMOÇÃO (Opcional - limpa uma tarefa de teste)
        model.addTarefa(pId, "E1", "T3", "Tarefa Apagar", "Será removida");
        model.removerTarefa(pId, "T3");
        
        // 7. Teste de PERSISTÊNCIA (Escrita)
        System.out.println("\n--- A gravar para JSON ---");
        model.saveProjeto(path, pId);

        // 8. Teste de PERSISTÊNCIA (Leitura)
        System.out.println("--- A carregar do JSON para novo Model ---");
        Model novoModel = new Model(repository);
        novoModel.loadProjeto(path);

        // 9. VALIDAÇÃO FINAL
        Projeto pCarregado = novoModel.getProjetoById(pId);
        if (pCarregado != null) {
            System.out.println("\n[SUCESSO] Dados Recuperados:");
            System.out.println("Nome do Projeto: " + pCarregado.getNome());
            System.out.println("Qtd de Estados: " + pCarregado.getEstados().size());
            System.out.println("Membros no Grupo: " + pCarregado.getGrupo().getPessoas().size());
        } else {
            System.err.println("[ERRO] Falha ao carregar o projeto!");
        }
    }*/
}
