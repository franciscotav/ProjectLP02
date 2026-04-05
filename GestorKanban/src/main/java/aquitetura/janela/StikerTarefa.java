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
public class StikerTarefa extends JPanel {
    private JLabel labelTitulo;
    private String stringDescricao;
    private JLabel labelDescricao;
    private JLabel labelResponsavel;
    private JButton buttonEditar;
    private JButton buttonRemover;
    
    private int startX, startY;
    private TarefaColuna originalParent;
    private int originalIndex;
    private boolean mouseDragged;
    
    private void iniStikerTarefa(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(new Color(100, 200, 200));
        
        Dimension tamanho = new Dimension(250,250);
        setPreferredSize(tamanho);
        setMinimumSize(tamanho);
        setMaximumSize(tamanho);
        
        setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mouseDragged = false;
    }
    
    private void setupVariavels(String titulo, String descricao){
        labelTitulo = new JLabel(titulo);
        stringDescricao = descricao;
        labelDescricao = new JLabel(descricao);
        
        labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        labelDescricao = new JLabel("<html><body style='width: 180px;'>" + stringDescricao + "</body></html>");
        labelDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDescricao.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 15));
        
        labelResponsavel = new JLabel("👤 " + "Responsavel");
        labelResponsavel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
        labelResponsavel.setForeground(new Color(60, 60, 60));
        labelResponsavel.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelResponsavel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        
        
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelButtons.setOpaque(false);
        buttonEditar = new JButton("📝");
        buttonRemover = new JButton("🗑️");
        buttonStyle(buttonEditar);
        buttonStyle(buttonRemover);
        panelButtons.add(buttonEditar);
        panelButtons.add(buttonRemover);
        panelButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Dimension panelDim = new Dimension(250, 60); 
        panelButtons.setMaximumSize(panelDim); 
        panelButtons.setMinimumSize(panelDim);
        panelButtons.setPreferredSize(panelDim);
        
        buttonRemover.addActionListener(e -> {
            if (getParent() instanceof TarefaColuna) {
                TarefaColuna coluna = (TarefaColuna) getParent();
                int index = coluna.getComponentZOrder(this);
                coluna.remove(index + 1);
                coluna.remove(this);
                coluna.revalidate();
                coluna.repaint();
            }
        });
        
              
        buttonEditar.addActionListener(e -> {
            JPanel panelEdicao = new JPanel();
            panelEdicao.setLayout(new BoxLayout(panelEdicao, BoxLayout.Y_AXIS));
            
            JTextField fieldTitulo = new JTextField(labelTitulo.getText());
            JTextArea areaDesc = new JTextArea(stringDescricao, 5, 20);
            JTextField fieldResp = new JTextField(labelResponsavel.getText().replace("👤 ", ""));

            panelEdicao.add(new JLabel("Título:"));
            panelEdicao.add(fieldTitulo);
            panelEdicao.add(new JLabel("Descrição:"));
            panelEdicao.add(new JScrollPane(areaDesc));
            panelEdicao.add(new JLabel("Responsável:"));
            panelEdicao.add(fieldResp);

            int result = JOptionPane.showConfirmDialog(null, panelEdicao, 
                       "Editar Tarefa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                labelTitulo.setText(fieldTitulo.getText());
                stringDescricao = areaDesc.getText();
                
                labelDescricao.setText("<html><body style='width: 180px;'>" + stringDescricao + "</body></html>");
                labelResponsavel.setText("👤 " + fieldResp.getText());

                this.revalidate();
                this.repaint();
            }
        });
        
        add(labelTitulo);
        add(labelDescricao);
        add(Box.createVerticalGlue());
        add(labelResponsavel);
        add(panelButtons);
        add(Box.createRigidArea(new Dimension(0, 5)));
     }
    
    private void buttonStyle(JButton button) {
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private StikerTarefa local(){
        return this;
    }
    
    public StikerTarefa(String titulo, String descricao){
        
        iniStikerTarefa();
        setupVariavels(titulo, descricao);
       
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                mouseDragged = false;
                
                startX = e.getX();
                startY = e.getY();

                if(getParent() instanceof TarefaColuna){
                    originalParent = (TarefaColuna) getParent();
                    originalIndex = originalParent.getComponentZOrder(local());
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                
                if(mouseDragged == false) return;
                
                mouseDragged = false;
                 
                if(originalParent == null) return;
                
                JLayeredPane lp = getRootPane().getLayeredPane();
        
                Point dropPoint = SwingUtilities.convertPoint(local(), e.getPoint(), lp);

                setVisible(false);
                Component target = SwingUtilities.getDeepestComponentAt(lp, dropPoint.x, dropPoint.y);
                setVisible(true);

                TarefaColuna newColumn = null;
                Component check = target;
                while (check != null) {
                    if (check instanceof TarefaColuna) {
                        newColumn = (TarefaColuna) check;
                        break;
                    }
                    
                    check = check.getParent();
                }

                lp.remove(local());

                if(newColumn != null) {
                    if (newColumn == originalParent) {
                        newColumn.add(local(), originalIndex);
                        newColumn.add(Box.createRigidArea(new Dimension(0, 10)), originalIndex + 1);
                    } else {
                        int pos = Math.max(0, newColumn.getComponentCount() - 1);
                        newColumn.add(local(), pos);
                        newColumn.add(Box.createRigidArea(new Dimension(0, 10)), pos + 1);
                    }
                } else {
                    originalParent.add(local(), originalIndex);
                    originalParent.add(Box.createRigidArea(new Dimension(0, 10)), originalIndex + 1);
                }

                if (newColumn != null) {
                    newColumn.revalidate();
                    newColumn.repaint();
                }
                
                originalParent.revalidate();
                originalParent.repaint();
                
                originalParent = null;
            }
            
        });
     
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e){
                
                mouseDragged = true;
                
                if (getParent() instanceof TarefaColuna) {
                    JLayeredPane lp = getRootPane().getLayeredPane();
                    Point p = SwingUtilities.convertPoint(originalParent, getLocation(), lp);
                    
                    originalParent.remove(originalIndex + 1);
                    originalParent.remove(local());
                    lp.add(local(), JLayeredPane.DRAG_LAYER);
                    
                    setLocation(p);
                    
                    originalParent.revalidate();
                    originalParent.repaint();
                    
                }
                
                Point mousePos = SwingUtilities.convertPoint(local(), e.getPoint(), getParent());
                setLocation(mousePos.x - startX, mousePos.y - startY);
                    
                
            }
            
            @Override
            public void mouseMoved(MouseEvent e){
            
            }
        });
    }
    
}

class StickerAddicionar extends JPanel{
    private Dimension tamanho;
    
    public StickerAddicionar() {
        tamanho = new Dimension(250, 250);
        
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
                if(getParent() instanceof TarefaColuna){
                    getParent().add(new StikerTarefa("Tarefa","Descrição da Tarefa"), 1);
                    getParent().add(Box.createRigidArea(new Dimension(0, 10)), 2);
                    getParent().revalidate();
                    getParent().repaint();
                   
                }
            }
        });

    }
}