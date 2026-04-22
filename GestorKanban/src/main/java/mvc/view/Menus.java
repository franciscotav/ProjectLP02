/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author CasaSFT
 */

/**
 * Classe principal de organização da interface de menus.
 * Atua como o contentor principal que empilha verticalmente o menu de topo e a lista de projetos.
 * Serve como ponte de comunicação entre o Controller e os subcomponentes da View.
 */
public class Menus extends JPanel {

    //atributos
    private TopMenuPanel topMenuPanel;
    private ProjetoMenuPanel projetoMenuPanel;

    //Construtor
    public Menus() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(Component.LEFT_ALIGNMENT);

        topMenuPanel = new TopMenuPanel();
        projetoMenuPanel = new ProjetoMenuPanel();

        add(topMenuPanel);
        add(projetoMenuPanel);
    }
    
    //listeneres
    public void adicionarNovoProjetoListener(MouseListener listener) {
        topMenuPanel.setNovoProjetoClickListener(listener);
    }

    public void setCarregarProjetoMouseAdapter(MouseListener listener) {
        topMenuPanel.setCarregarProjetoMouseAdapter(listener);
    }
<<<<<<< main
    
    public void highlightProjeto(String projetoId){
        projetoMenuPanel.highlightProjeto(projetoId);
    }
    
    public void addProjeto(String id, String nome){
        projetoMenuPanel.add(new ProjetoPanel(id, nome));
=======

    public void setGuardarProjetoMouseAdapter(MouseListener e) {
        projetoMenuPanel.setGuardarProjetoMouseAdapter(e);
>>>>>>> Berna
    }
    
    public void addProjeto(String id, String nome) {
        projetoMenuPanel.add(new ProjetoPanel(id, nome));
    }
    
    public void setRemoverProjetoMouseAdapter(MouseListener e){
        projetoMenuPanel.setRemoverProjetoMouseAdapter(e);
    }
    
    public void setSelecionarProjetoMouseAdapter(MouseListener e){
        projetoMenuPanel.setSelecionarProjetoMouseAdapter(e);
    }

}

/**
 * Painel de ações globais da aplicação.
 * Contém os controlos principais para criar e carregar projetos.
 */

class TopMenuPanel extends JPanel {

    //atributos
    JButton novoProjetoButton;
    JButton carregarProjetoButton;
    MouseListener novoProjetoClickListener;

    //Construtor
    public TopMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        novoProjetoButton = new JButton("Novo Projeto");
        carregarProjetoButton = new JButton("Carregar Projeto");

        styleButton(novoProjetoButton);
        styleButton(carregarProjetoButton);

        add(novoProjetoButton);
        add(carregarProjetoButton);
    }
    
    //listeners
    public void setNovoProjetoClickListener(MouseListener listener) {

        novoProjetoButton.addMouseListener(listener);

    }

    public void setCarregarProjetoMouseAdapter(MouseListener listener) {
        carregarProjetoButton.addMouseListener(listener);
    }

    public void carregarProjeto() {
    }

    private void styleButton(JButton jbutton) {
        jbutton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jbutton.setForeground(Color.gray);
        jbutton.setFocusPainted(false);
        jbutton.setContentAreaFilled(false);
    }
}

/**
 * Listagem de projetos ativos.
 * Organiza horizontalmente as instâncias de projetos abertos pelo utilizador.
 * Gere a distribuição dos Listeners de eventos para cada painel de projeto individual.
 */
class ProjetoMenuPanel extends JPanel {

    //Construtor
    public ProjetoMenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public void addProjetoPanel(ProjetoPanel projetoPanel) {
        add(projetoPanel);
    }
<<<<<<< main
    
    public void highlightProjeto(String projetoId){
        for(int i = 0; i < getComponentCount(); i++){
            if(getComponent(i) instanceof ProjetoPanel){
                ProjetoPanel projetoPanel = (ProjetoPanel) getComponent(i);
                if(projetoPanel.getId().equals(projetoId)){
                    projetoPanel.setBackground(Color.LIGHT_GRAY);
                    projetoPanel.setOpaque(true);
                    projetoPanel.repaint();
                }else{
                    projetoPanel.setOpaque(false);
                    projetoPanel.repaint();
                }
            }
        }
    }
    
    public void setGuardarProjetoMouseAdapter(MouseListener e){
=======

    public void setGuardarProjetoMouseAdapter(MouseListener e) {
>>>>>>> Berna
        ProjetoPanel projetoPanel = null;
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof ProjetoPanel) {
                projetoPanel = (ProjetoPanel) getComponent(i);
            }
        }

        projetoPanel.setGuardarProjetoMouseAdapter(e);
    }
    
    public void setSelecionarProjetoMouseAdapter(MouseListener e){
        ProjetoPanel projetoPanel = null;
        for(int i = 0; i < getComponentCount(); i++){
            if(getComponent(i) instanceof ProjetoPanel){
                projetoPanel = (ProjetoPanel) getComponent(i);
            }
        }
        
        projetoPanel.addMouseListener(e);
    }
    
    public void setRemoverProjetoMouseAdapter(MouseListener e){
        ProjetoPanel projetoPanel = null;
        for(int i = 0; i < getComponentCount(); i++){
            if(getComponent(i) instanceof ProjetoPanel){
                projetoPanel = (ProjetoPanel) getComponent(i);
            }
        }
        
        projetoPanel.setRemoverProjetoMouseAdapter(e);
    }
}
/**
 * Representação visual individual de um projeto.
 * Exibe o nome do projeto e disponibiliza ações específicas de contexto (Editar, Guardar e Fechar).
 */
class ProjetoPanel extends JPanel {

    //atributos
    private String projetoNome;
    private JLabel projetoNomeLabel;
    private String id;
<<<<<<< main
    
//    JButton buttonEditar;
    JButton buttonGuardar;
    JButton buttonFechar;
    
    public void setGuardarProjetoMouseAdapter(MouseListener e){
        buttonGuardar.addMouseListener(e);
    }
   
    public ProjetoPanel(String id, String projetoNome){
=======
    JButton buttonEditar;
    JButton buttonGuardar;
    JButton buttonFechar;

    //Construtor
    public ProjetoPanel(String id, String projetoNome) {
>>>>>>> Berna
        this.id = id;
        this.projetoNome = projetoNome;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);

        Border linha = BorderFactory.createLineBorder(Color.GRAY);
        Border empty = BorderFactory.createEmptyBorder(5, 20, 5, 20);
        Border compound = BorderFactory.createCompoundBorder(linha, empty);
        setBorder(compound);

        projetoNomeLabel = new JLabel(projetoNome);
        styleLabel(projetoNomeLabel);
<<<<<<< main
        
//        buttonEditar = new JButton("🖉");
//        buttonStyle(buttonEditar);
        
=======

        buttonEditar = new JButton("🖉");
        buttonStyle(buttonEditar);

>>>>>>> Berna
        buttonGuardar = new JButton("💾");
        buttonStyle(buttonGuardar);

        buttonFechar = new JButton("𝗫");
        buttonStyle(buttonFechar);

        add(projetoNomeLabel);
//        add(buttonEditar);
        add(buttonGuardar);
        add(buttonFechar);
    }
<<<<<<< main
    
    public void setRemoverProjetoMouseAdapter(MouseListener e){
        buttonFechar.addMouseListener(e);
    }
    
    private void styleLabel(JLabel jlabel){
=======

    public void setGuardarProjetoMouseAdapter(MouseListener e) {
        buttonGuardar.addMouseListener(e);
    }

    private void styleLabel(JLabel jlabel) {
>>>>>>> Berna
        jlabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jlabel.setForeground(Color.gray);
    }

    private void buttonStyle(JButton button) {
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
