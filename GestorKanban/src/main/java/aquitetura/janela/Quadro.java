/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aquitetura.janela;

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
}

class Estados extends JPanel {
    private BoxLayout estadosLayout;
    
    public Estados(){
        estadosLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(estadosLayout);
        
        ColunaAddicionar colunaAddiconar = new ColunaAddicionar();
        
        add(Box.createVerticalGlue());
        add(new TarefaColuna("Para fazer"));
        add(new TarefaColuna("A fazer"));
        add(new TarefaColuna("Conluido"));
        add(colunaAddiconar);
    }         
}

class TarefaColuna extends JPanel{
    private BoxLayout tarefasLayout;
    
    private ColunaMenu colunaMenu;
    private StickerAddicionar stickerAddicionar;
            
    public TarefaColuna(String nomeColuna){
        tarefasLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Border margin = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        Border line = BorderFactory.createLineBorder(Color.GRAY,1);
        Border combined = BorderFactory.createCompoundBorder(line, margin);
        setBorder(combined);
        
        setLayout(tarefasLayout);
        
        
        colunaMenu = new ColunaMenu(nomeColuna);
        stickerAddicionar = new StickerAddicionar();
        
        add(colunaMenu);
        add(stickerAddicionar);
        
        
    }
}

class ColunaMenu extends JPanel{
    private JLabel labelName;
    private JButton buttonEditar;
    private JButton buttonRemover;
    
    
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
        
        buttonRemover.addActionListener(e -> {
                   
            if(getParent().getParent() instanceof Estados){
                Estados estados = (Estados) getParent().getParent();
                int index = estados.getComponentZOrder(getParent());
                estados.remove(index);
                
                estados.revalidate();
                estados.repaint();
            }
        });
        
              
        buttonEditar.addActionListener(e -> {
            JPanel panelEdicao = new JPanel();
            panelEdicao.setLayout(new BoxLayout(panelEdicao, BoxLayout.Y_AXIS));
            
            JTextField fieldTitulo = new JTextField(labelName.getText());
            
            panelEdicao.add(new JLabel("Titulo:"));
            panelEdicao.add(fieldTitulo);

            int result = JOptionPane.showConfirmDialog(this, panelEdicao, 
                       "Editar Estado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                labelName.setText(fieldTitulo.getText());
                this.revalidate();
                this.repaint();
            }
        });
        
        
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
        //Border line = BorderFactory.createLineBorder(Color.GRAY,1);
        //Border combined = BorderFactory.createCompoundBorder(line, margin);
        setBorder(margin);
        
        //setLayout(tarefasLayout);
        
        ColunaAddicionarButton colunaButton = new ColunaAddicionarButton();
        add(colunaButton, gbc);
            
        
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
    
    
    addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e){
                if(getParent().getParent() instanceof Estados){
                    Estados estados = (Estados) getParent().getParent();
                    
                    int pos = Math.max(0, estados.getComponentCount() - 1);
                    estados.add(new TarefaColuna("Estado"),pos);

                    estados.revalidate();
                    estados.repaint();
                }
            }
        });

    }
}