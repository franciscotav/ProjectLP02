/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author CasaSFT
 */
public class Quadro extends JScrollPane {
    private Estados estados;
    
    public Quadro(){
        estados = new Estados();
        estados.setAlignmentY(Component.TOP_ALIGNMENT);
        this.setViewportView(estados);
        
    }
    
    public void setAddicionarColunaButtonMouseAdapter(MouseListener e){
        estados.setAddicionarColunaButtonMouseAdapter(e);
    }
    
    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e){
        estados.setAddicionarTarefaButtonMouseAdapter(e);
    }
    
    public void setEditarColunaButtonMouseAdapter(MouseListener e){
        estados.setEditarColunaButtonMouseAdapter(e);
    }
    
    public void setRemoverColunaButtonMouseAdapter(MouseListener e){
        estados.setRemoverColunaButtonMouseAdapter(e);
    }
}

class Estados extends JPanel {
    private BoxLayout estadosLayout;
    private ColunaAddicionar colunaAddiconar;
    
    public Estados(){
        estadosLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(estadosLayout);
        
        colunaAddiconar = new ColunaAddicionar();
        
        add(Box.createVerticalGlue());
        //add(new TarefaColuna("Para fazer"));
        //add(new TarefaColuna("A fazer"));
        //add(new TarefaColuna("Conluido"));
        add(colunaAddiconar);
    }
    
    public void setAddicionarColunaButtonMouseAdapter(MouseListener e){
        colunaAddiconar.addMouseListener(e);
    }
    
    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e){
        TarefaColuna lastTarefaColuna = null;
        for(int i = 0; i < this.getComponentCount(); i++){
            if(getComponent(i) instanceof TarefaColuna){
                lastTarefaColuna = (TarefaColuna) getComponent(i);
            }
        }
        
        if(lastTarefaColuna != null)
            lastTarefaColuna.setAddicionarTarefaButtonMouseAdapter(e);
    }
    
    public void setEditarColunaButtonMouseAdapter(MouseListener e){
        TarefaColuna lastTarefaColuna = null;
        for(int i = 0; i < this.getComponentCount(); i++){
            if(getComponent(i) instanceof TarefaColuna){
                lastTarefaColuna = (TarefaColuna) getComponent(i);
            }
        }
        
        if(lastTarefaColuna != null)
            lastTarefaColuna.setEditarColunaButtonMouseAdapter(e);
    }
    
    public void setRemoverColunaButtonMouseAdapter(MouseListener e){
        TarefaColuna lastTarefaColuna = null;
        for(int i = 0; i < this.getComponentCount(); i++){
            if(getComponent(i) instanceof TarefaColuna){
                lastTarefaColuna = (TarefaColuna) getComponent(i);
            }
        }
        
        if(lastTarefaColuna != null)
            lastTarefaColuna.setRemoverColunaButtonMouseAdapter(e);
    }
}

class TarefaColuna extends JPanel{
    private BoxLayout tarefasLayout;
    
    private ColunaMenu colunaMenu;
    private StickerAddicionar stickerAddicionar;
    
    private String id;

    public String getId() {
        return id;
    }
    
    public String getName(){
        return colunaMenu.getName();
    }
            
    public TarefaColuna(String id, String nome){
        tarefasLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Border margin = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        Border line = BorderFactory.createLineBorder(Color.GRAY,1);
        Border combined = BorderFactory.createCompoundBorder(line, margin);
        setBorder(combined);
        
        setLayout(tarefasLayout);
        
        this.id = id;
        
        colunaMenu = new ColunaMenu(nome);
        stickerAddicionar = new StickerAddicionar();
        
        add(colunaMenu);
        add(stickerAddicionar);
        
        
    }
    
    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e){
        stickerAddicionar.addMouseListener(e);
    }
    
    public void setEditarColunaButtonMouseAdapter(MouseListener e){
        colunaMenu.setEditarColunaButtonMouseAdapter(e);
    }
    
    public void setRemoverColunaButtonMouseAdapter(MouseListener e){
        colunaMenu.setRemoverColunaButtonMouseAdapter(e);
    }
}

class ColunaMenu extends JPanel{
    private JLabel labelName;
    private JButton buttonEditar;
    private JButton buttonRemover;

    public String getName() {
        return labelName.toString();
    }

    public ColunaMenu(String nomeColuna){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        labelName = new JLabel(nomeColuna);
        labelName.setFont(new Font("Arial", Font.BOLD, 20));
        labelName.setForeground(Color.GRAY);
        labelName.setHorizontalAlignment(SwingConstants.LEFT);
        add(labelName);
        
        buttonEditar = new JButton("📝");
        buttonRemover = new JButton("🗑️");
        buttonStyle(buttonEditar);
        buttonStyle(buttonRemover);
        
        add(labelName);
        add(Box.createHorizontalGlue());
        add(buttonEditar);
        add(buttonRemover);
        
    }
    
    private void buttonStyle(JButton button) {
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void setEditarColunaButtonMouseAdapter(MouseListener e){
        buttonEditar.addMouseListener(e);
    }
    
    public void setRemoverColunaButtonMouseAdapter(MouseListener e){
        buttonRemover.addMouseListener(e);
    }
    
    public void buttonRemoverClicked(){
        if(getParent().getParent() instanceof Estados){
            Estados estados = (Estados) getParent().getParent();
            int index = estados.getComponentZOrder(getParent());
            estados.remove(index);

            estados.revalidate();
            estados.repaint();
        }
    }
    
    public void buttonEditarClicked(){
        JPanel panelEdicao = new JPanel();
        panelEdicao.setLayout(new BoxLayout(panelEdicao, BoxLayout.Y_AXIS));

        JTextField fieldTitulo = new JTextField(labelName.getText());

        panelEdicao.add(new JLabel("Titulo:"));
        panelEdicao.add(fieldTitulo);

        int result = JOptionPane.showConfirmDialog(this, panelEdicao, 
                   "Editar Estado #" + ((TarefaColuna)getParent()).getId(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            labelName.setText(fieldTitulo.getText());
            this.revalidate();
            this.repaint();
        }
    }
}

class ColunaAddicionar extends JPanel{               
    public ColunaAddicionar(){
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Border margin = BorderFactory.createEmptyBorder(30, 30, 30, 30);
        setBorder(margin);
        
        ColunaAddicionarButton colunaButton = new ColunaAddicionarButton();
        add(colunaButton, gbc);
            
        
    }
    
    public void mouseClicked(String id, String nome){
        if(getParent() instanceof Estados){
            Estados estados = (Estados) getParent();

            int pos = Math.max(0, estados.getComponentCount() - 1);
            estados.add(new TarefaColuna(id, nome),pos);

            estados.revalidate();
            estados.repaint();
        }
    }
}

class ColunaAddicionarButton extends JPanel{
    public ColunaAddicionarButton() {
        Dimension tamanho = new Dimension(250, 250);
        
        setPreferredSize(tamanho);
        setMaximumSize(tamanho);
        setMinimumSize(tamanho);
        
        setBackground(new Color(249, 249, 249));
        setLayout(new GridBagLayout());
        
        JLabel plusLabel = new JLabel("+");
        plusLabel.setFont(new Font("Arial", Font.BOLD, 30));
        plusLabel.setForeground(Color.GRAY);
        add(plusLabel);

    }
    
}