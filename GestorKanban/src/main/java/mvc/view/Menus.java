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
public class Menus extends JPanel{
    private TopMenuPanel topMenuPanel;
    private ProjetoMenuPanel projetoMenuPanel;
    
    public Menus(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(Component.LEFT_ALIGNMENT);
        
        topMenuPanel = new TopMenuPanel();
        projetoMenuPanel = new ProjetoMenuPanel();
        
        add(topMenuPanel);
        add(projetoMenuPanel);
    }
    
    public void adicionarNovoProjetoListener(MouseListener listener) {
        topMenuPanel.setNovoProjetoClickListener(listener);
    }
    
    public void setCarregarProjetoMouseAdapter(MouseListener listener) {
        topMenuPanel.setCarregarProjetoMouseAdapter(listener);
    }
    
    
    public void addProjeto(String id, String nome){
        projetoMenuPanel.add(new ProjetoPanel(id, nome));
    }
    
    public void setGuardarProjetoMouseAdapter(MouseListener e){
        projetoMenuPanel.setGuardarProjetoMouseAdapter(e);
    }
}

class TopMenuPanel extends JPanel{
    JButton novoProjetoButton;
    JButton carregarProjetoButton;
    MouseListener novoProjetoClickListener;
    
    public TopMenuPanel(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        
        novoProjetoButton = new JButton("Novo Projeto");
        carregarProjetoButton = new JButton("Carregar Projeto");
        
        styleButton(novoProjetoButton);
        styleButton(carregarProjetoButton);
        
        add(novoProjetoButton);
        add(carregarProjetoButton);
    }
    
    public void setNovoProjetoClickListener(MouseListener listener){
//        novoProjetoButton.removeMouseListener(novoProjetoClickListener);
//        novoProjetoButton = new JButton("Novo Projeto");
//        styleButton(novoProjetoButton);
        novoProjetoButton.addMouseListener(listener);
//        removeAll();
//        add(novoProjetoButton);
//        add(carregarProjetoButton);
        //revalidate();
    }
    
    public void setCarregarProjetoMouseAdapter(MouseListener listener){
        carregarProjetoButton.addMouseListener(listener);
    }
    
    public void carregarProjeto(){
//        JFileChooser fileChooser = new JFileChooser();
//        int response = fileChooser.showOpenDialog(null);
//
//        if (response == JFileChooser.APPROVE_OPTION) {
//            String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
//            System.out.println(selectedFile);
//        }
    }
    
    private void styleButton(JButton jbutton){
        jbutton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jbutton.setForeground(Color.gray);
        jbutton.setFocusPainted(false);
        jbutton.setContentAreaFilled(false);
    }
}

class ProjetoMenuPanel extends JPanel{
    public ProjetoMenuPanel(){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
//        addProjetoPanel(new ProjetoPanel("Projeto A"));
//        addProjetoPanel(new ProjetoPanel("Projeto B"));
    }
    
    public void addProjetoPanel(ProjetoPanel projetoPanel){
        add(projetoPanel);
    }
    
    public void setGuardarProjetoMouseAdapter(MouseListener e){
        ProjetoPanel projetoPanel = null;
        for(int i = 0; i < getComponentCount(); i++){
            if(getComponent(i) instanceof ProjetoPanel){
                projetoPanel = (ProjetoPanel) getComponent(i);
            }
        }
        
        projetoPanel.setGuardarProjetoMouseAdapter(e);
    }
}

class ProjetoPanel extends JPanel{
    private String projetoNome;
    private JLabel projetoNomeLabel;
    private String id;
    
    JButton buttonEditar;
    JButton buttonGuardar;
    JButton buttonFechar;
    
    public void setGuardarProjetoMouseAdapter(MouseListener e){
        buttonGuardar.addMouseListener(e);
    }
    
    public ProjetoPanel(String id, String projetoNome){
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
        
        buttonEditar = new JButton("🖉");
        buttonStyle(buttonEditar);
        
        buttonGuardar = new JButton("💾");
        buttonStyle(buttonGuardar);
        
        buttonFechar = new JButton("𝗫");
        buttonStyle(buttonFechar);
        
        add(projetoNomeLabel);
        add(buttonEditar);
        add(buttonGuardar);
        add(buttonFechar);
    }
    
    private void styleLabel(JLabel jlabel){
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
