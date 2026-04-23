/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author CasaSFT
 */

/**
 * Representação visual de uma tarefa individual no Quadro Kanban (Post-it).
 * Gere a exibição de informações (título, descrição, responsáveis) e o estado visual da tarefa.
 * Implementa lógica complexa de Drag and Drop para permitir mover tarefas entre colunas e
 * fornece diálogos internos para a edição rápida de conteúdos.
 */
public class StikerTarefa extends JPanel {

    //atributos
    private JLabel labelTitulo;
    private String stringDescricao;
    private JLabel labelDescricao;
    private JPanel panelResponsaveis;
    private JButton buttonEditar;
    private JButton buttonRemover;
    private int startX, startY;
    private TarefaColuna originalParent;
    private int originalIndex;
    private boolean mouseDragged;
    private String idTarefa;
    private ArrayList<Responsavel> membrosAtribuidos;

    //Construtor
    public StikerTarefa(String idTarefa, String titulo, String descricao) {
        iniStikerTarefa();
        setupVariavels(idTarefa, titulo, descricao);
    }
    
    //setup
    private void iniStikerTarefa() {
        setLayout(new BorderLayout());
        setBackground(new Color(100, 200, 200));

        Dimension tamanho = new Dimension(250, 250);
        setPreferredSize(tamanho);
        setMinimumSize(tamanho);
        setMaximumSize(tamanho);

        setAlignmentX(Component.CENTER_ALIGNMENT);

        mouseDragged = false;
        membrosAtribuidos = new ArrayList<>();
    }

    private void setupVariavels(String idTarefa, String titulo, String descricao) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        this.idTarefa = idTarefa;

        labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        labelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        stringDescricao = descricao;
        labelDescricao = new JLabel("<html><body style='width: 180px;'>" + stringDescricao + "</body></html>");
        labelDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
        labelDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDescricao.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 15));

        panelResponsaveis = new JPanel();
        panelResponsaveis.setLayout(new BoxLayout(panelResponsaveis, BoxLayout.Y_AXIS));
        panelResponsaveis.setOpaque(false);
        panelResponsaveis.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(labelTitulo);
        contentPanel.add(labelDescricao);
        contentPanel.add(panelResponsaveis);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelButtons.setOpaque(false);
        buttonEditar = new JButton("📝");
        buttonRemover = new JButton("🗑️");
        buttonStyle(buttonEditar);
        buttonStyle(buttonRemover);
        panelButtons.add(buttonEditar);
        panelButtons.add(buttonRemover);

        add(contentPanel, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    }
    
    private void buttonStyle(JButton button) {
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    //listeners
    public void setEditarTarefaButtonMouseAdapter(MouseListener e) {
        buttonEditar.addMouseListener(e);
    }

    public void setsetRemoverTarefaButtonMouseAdapter(MouseListener e) {
        buttonEditar.addMouseListener(e);
    }

    public void setRemoverTarefaButtonMouseAdapter(MouseListener e) {
        buttonRemover.addMouseListener(e);
    }

    //Responsavel
    public void setResponsavel(Responsavel responsavel) {
        membrosAtribuidos.add(responsavel);
        updatePanelResponsaveis();
    }
    
    public void addResponsavel(Responsavel membro) {
        membrosAtribuidos.add(membro);

        JLabel labelResponsavel = new JLabel("👤 " + membro.getNome());
        labelResponsavel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
        labelResponsavel.setForeground(new Color(60, 60, 60));
        labelResponsavel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        panelResponsaveis.add(labelResponsavel);

        panelResponsaveis.revalidate();
        panelResponsaveis.repaint();
    }
    
    public void removeResponsavel(Responsavel membro) {
        membrosAtribuidos.remove(membro);
        updatePanelResponsaveis();
    }
    
    // botões Sticker
    public void removerMousePressed() {
        if (getParent() instanceof TarefaColuna) {
            TarefaColuna coluna = (TarefaColuna) getParent();
            int index = coluna.getComponentZOrder(this);
            coluna.remove(index + 1);
            removeInGrupos();
            coluna.remove(this);
            coluna.revalidate();
            coluna.repaint();
        }
    }

    public ArrayList<String> editarMousePressed() {
        JPanel panelEdicao = new JPanel();
        panelEdicao.setLayout(new BoxLayout(panelEdicao, BoxLayout.Y_AXIS));

        JTextField fieldTitulo = new JTextField(labelTitulo.getText());
        JTextArea areaDesc = new JTextArea(stringDescricao, 5, 20);

        JPanel selectResponsavel = new JPanel();
        selectResponsavel.setLayout(new BoxLayout(selectResponsavel, BoxLayout.Y_AXIS));
        for (Responsavel resp : membrosAtribuidos) {
            JCheckBox checkBox = new JCheckBox(resp.getNome() + " #" + resp.getId());
            checkBox.setSelected(true);
            selectResponsavel.add(checkBox);
        }

        panelEdicao.add(new JLabel("Titulo:"));
        panelEdicao.add(fieldTitulo);
        panelEdicao.add(new JLabel("Descrição:"));
        panelEdicao.add(new JScrollPane(areaDesc));
        panelEdicao.add(selectResponsavel);

        int result = JOptionPane.showConfirmDialog(this, panelEdicao,
                "Editar Tarefa #" + idTarefa, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        ArrayList<String> tarefasRemovidas = new ArrayList();
        
        if (result == JOptionPane.OK_OPTION) {
            labelTitulo.setText(fieldTitulo.getText());
            stringDescricao = areaDesc.getText();
            labelDescricao.setText("<html><body style='width: 180px;'>" + stringDescricao + "</body></html>");
           
            ArrayList<Integer> indicesToRemove = new ArrayList<>();
            int checkBoxIndex = 0;
            for (int i = 0; i < selectResponsavel.getComponentCount(); i++) {
                if(selectResponsavel.getComponent(i) instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) selectResponsavel.getComponent(i);
                    if(!checkBox.isSelected()) {
                        indicesToRemove.add(checkBoxIndex);
                    }
                    checkBoxIndex++;
                }
            }
            
            for (int i = indicesToRemove.size() - 1; i >= 0; i--) {
                int index = indicesToRemove.get(i);
                tarefasRemovidas.add(membrosAtribuidos.get(index).getId());
                membrosAtribuidos.get(index).removeStickers(this);
                removeResponsavel(membrosAtribuidos.get(index));
            }

            updateStickers();
            this.revalidate();
            this.repaint();
        }
        
        return tarefasRemovidas;
    }

    //drag and drop Sticker
    public void mousePressed(MouseEvent e) {
        mouseDragged = false;

        startX = e.getX();
        startY = e.getY();

        if (getParent() instanceof TarefaColuna) {
            originalParent = (TarefaColuna) getParent();
            originalIndex = originalParent.getComponentZOrder(local());
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (mouseDragged == false) {
            return;
        }

        mouseDragged = false;

        if (originalParent == null) {
            return;
        }

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

        if (newColumn != null) {
            if (newColumn == originalParent) {
                newColumn.add(local(), originalIndex);
                newColumn.add(Box.createRigidArea(new Dimension(0, 10)), originalIndex + 1);
            } else {
                newColumn.add(local(), 1);
                newColumn.add(Box.createRigidArea(new Dimension(0, 10)), 2);
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

    public void mouseDragged(MouseEvent e) {
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

    //getters
    public String getID() {
        return idTarefa;
    }

    public String getTarefaLastPessoaID() {
        return membrosAtribuidos.getLast().getId();
    }

    public String getTitulo() {
        return labelTitulo.getText();
    }

    public String getDescricao() {
        return stringDescricao;
    }
    
    public JLabel getLabelTitulo() {
        return labelTitulo;
    }
    
    //auxiliar
    private StikerTarefa local() {
           return this;
       }   
    
    public void updatePanelResponsaveis() {
        panelResponsaveis.removeAll();

        for (Responsavel membro : membrosAtribuidos) {
            JLabel labelResponsavel = new JLabel("👤 " + membro.getNome());
            labelResponsavel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
            labelResponsavel.setForeground(new Color(60, 60, 60));
            labelResponsavel.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
            panelResponsaveis.add(labelResponsavel);
        }

        panelResponsaveis.revalidate();
        panelResponsaveis.repaint();
    }

    public void updateStickers() {
        for (Responsavel membro : membrosAtribuidos) {
            membro.updateStickersAtribuidos();
        }
    }
    
    public void removeInGrupos() {
        for (Responsavel responsavel : membrosAtribuidos) {
            responsavel.removeStickers(this);
        }

        membrosAtribuidos = null;
    }

}


/**
 * Componente funcional de interação para a criação de novas tarefas.
 * Atua como um botão visual estilizado que, ao ser clicado, desencadeia o processo 
 * de inserção de um novo StikerTarefa na coluna correspondente.
 */

class StickerAddicionar extends JPanel {

    //Construtor
    public StickerAddicionar() {
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

    public void mouseClicked(String idTarefa, String titulo, String descricao) {
        if (getParent() instanceof TarefaColuna) {
            getParent().add(new StikerTarefa(idTarefa, titulo, descricao), 1);
            getParent().add(Box.createRigidArea(new Dimension(0, 10)), 2);
            getParent().revalidate();
            getParent().repaint();
        }
    }
}
