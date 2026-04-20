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
        Projeto model = new Projeto(0,"");
        Controlador controlador = new Controlador(view, model);
    }    
    /*
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

    // 5. Criar o Topo da Pirâmide: O Projeto
    Projeto meuProjeto = new Projeto(1, "App Gestão MVC");
    meuProjeto.setGrupo(grupoBackend);
    
    meuProjeto.addEstado("EST-01", "A Fazer");
    meuProjeto.addEstado("EST-02", "Em Progresso");
    meuProjeto.addEstado("EST-03", "Concluído");
    meuProjeto.getEstadoById("EST-01").addTarefa(t3);
    meuProjeto.getEstadoById("EST-02").addTarefa(t2);
    meuProjeto.getEstadoById("EST-03").addTarefa(t1);

    // --- ZONA DE TESTE ---
    
    // Agora que tens a "árvore" toda montada no objeto 'meuProjeto', 
    // podes entregá-lo ao teu Repository para ver a magia a acontecer:
    
     Repository repo = new Repository();
     repo.saveToFile("dados.json",meuProjeto);
    
    System.out.println("Dados carregados em memória com sucesso. Pronto para enviar para o Repository!");

    // ==========================================
    // --- ZONA DE TESTE DO LOAD ---
    // ==========================================
    System.out.println("--- INICIAR CARREGAMENTO PARA NOVO OBJETO ---");
    
    // 1. Carregar o ficheiro do zero para um objeto completamente novo
    Projeto projetoCarregado = repo.loadFromFile("dados.json");
    
    // 2. Testes de sanidade (Verificar se a estrutura base sobreviveu)
    System.out.println("Nome do Projeto Carregado: " + projetoCarregado.getNome());
    System.out.println("Total de Estados lidos: " + projetoCarregado.getEstados().size());
    System.out.println("Total de Pessoas no Grupo: " + projetoCarregado.getGrupo().getPessoas().size());

    // 3. O TESTE DE FOGO DAS REFERÊNCIAS
    System.out.println("\n--- TESTE DE INTEGRIDADE DE MEMÓRIA ---");
    
    // Vamos isolar a pessoa 'Bernardo' (PES-01) que está no Grupo Central
    // (Assumindo que foi o primeiro a ser adicionado, está no índice 0)
    Pessoa bernardoNoGrupo = projetoCarregado.getGrupo().getPessoas().get(0);
    
    // Vamos isolar o 'Bernardo' que está associado à "TAR-01" (Modelar Base de Dados)
    // Essa tarefa estava no estado "Concluído" (índice 2 dos estados, índice 0 das tarefas)
    Tarefa tarefaDaBd = projetoCarregado.getEstados().get(2).getTarefas().get(0);
    Pessoa bernardoNaTarefa = tarefaDaBd.getPessoas().get(0);

    System.out.println("ID do Bernardo no Grupo: " + bernardoNoGrupo.getId());
    System.out.println("ID do Bernardo na Tarefa: " + bernardoNaTarefa.getId());
    
    // O momento da verdade: Usamos '==' em vez de '.equals()' para testar 
    // se ocupam o mesmo exato espaço de memória (o mesmo objeto físico).
    if (bernardoNoGrupo == bernardoNaTarefa) {
        System.out.println("[RESULTADO] SUCESSO ABSOLUTO! As referências foram reconectadas. O Gson não enganou a arquitetura!");
        
        // Prova final: Se mudarmos o nome no grupo, muda na tarefa automaticamente
        bernardoNoGrupo.setNome("Bernardo Silva (Modificado)");
        System.out.println("Nome do Bernardo visto pela Tarefa: " + bernardoNaTarefa.getNome());
        
    } else {
        System.err.println("[RESULTADO] FALHOU. O Gson criou clones na memória. O dicionário de reconexão não funcionou corretamente.");
    }

    }
    */
}
