/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.view;

//import mvc.controller.Controlador;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author CasaSFT
 */

/* Este GrupoLista.java corresponde ao lado esquerdo da interface que é responsavel por 
gerir o grupo do projeto.

Esta classe disponibiliza ao utilizador as seguites funções:

• Adicionar membros à equipa do projeto
• Remover membros da equipa do projeto
• Alterar as informações dos membro do projeto
• Atribuir tarefas a membros específicos da equipa arrastando-o para cima da tarefa

*/

/**
 * Classe responsável por gerir a lista de membros (Responsáveis) na interface.
 * Funciona como um container scrollable que organiza os cards e o botão de adição.
 */
public class GrupoLista extends JScrollPane {

    //Atributos
    private GrupoListaPanel grupoListaPanel;

    //Construtor
    public GrupoLista() {
        
        grupoListaPanel = new GrupoListaPanel();
        setViewportView(grupoListaPanel);
        
    }
    
    //Listeners
    public void setBotaoAdicionarMembroMouseAdapter(MouseListener e) {
        
        grupoListaPanel.setBotaoAdicionarMembroMouseAdapter(e);
    }

    public void setRemoverMembroMouseAdapter(MouseListener e) {
        
        Responsavel lastResponsavel = null;
        
        for (int i = 0; i < grupoListaPanel.getComponentCount(); i++) {
            if (grupoListaPanel.getComponent(i) instanceof Responsavel) {
                lastResponsavel = (Responsavel) grupoListaPanel.getComponent(i);
            }
        }

        if (lastResponsavel != null) {
            lastResponsavel.setRemoverMembroMouseAdapter(e);
        }
    }

    public void setEditarMembroMouseAdapter(MouseListener e) {
        
        Responsavel lastResponsavel = null;
        
        for (int i = 0; i < grupoListaPanel.getComponentCount(); i++) {
            if (grupoListaPanel.getComponent(i) instanceof Responsavel) {
                lastResponsavel = (Responsavel) grupoListaPanel.getComponent(i);
            }
        }

        if (lastResponsavel != null) {
            lastResponsavel.setEditarMembroMouseAdapter(e);
        }
    }

    public void setResponsavelListener(MouseListener e) {
        
        Responsavel lastResponsavel = null;
        
        for (int i = 0; i < grupoListaPanel.getComponentCount(); i++) {
            if (grupoListaPanel.getComponent(i) instanceof Responsavel) {
                lastResponsavel = (Responsavel) grupoListaPanel.getComponent(i);
            }
        }

        if (lastResponsavel != null) {
            lastResponsavel.addMouseListener(e);
            lastResponsavel.addMouseMotionListener((MouseMotionListener) e);
        }
    }
    
    //Actions
    public void refresh() {
        
        revalidate();
        repaint();
        
    }
    
    public void criarNovoResponsavel(String id, String nome) {
        
        grupoListaPanel.add(new Responsavel(id, nome), (grupoListaPanel.getComponentCount() - 1));
        grupoListaPanel.add(Box.createRigidArea(new Dimension(0, 10)), (grupoListaPanel.getComponentCount() - 1));
        
        refresh();
    }
    
    public GrupoListaPanel getGrupoListaPanel() {
        
        return grupoListaPanel;
        
    }

}

/**
 * Painel interno que contém os componentes reais.
 */

class GrupoListaPanel extends JPanel {

    //Atributos
    private BotaoAdicionarMembro botaoAdicionarMembro;

    //Construtor
    public GrupoListaPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        Border margin = BorderFactory.createEmptyBorder(10, 20, 10, 20);
        Border line = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border combined = BorderFactory.createCompoundBorder(line, margin);
        setBorder(combined);
        botaoAdicionarMembro = new BotaoAdicionarMembro();
        add(new GrupoName());
        add(botaoAdicionarMembro);
    }

    public void setBotaoAdicionarMembroMouseAdapter(MouseListener e) {
    
        botaoAdicionarMembro.addMouseListener(e);
    }

}

/**
 * Componente visual para o botão "Adicionar Membro".
 */
class BotaoAdicionarMembro extends JPanel {

    //Construtor
    public BotaoAdicionarMembro() {
        
        Dimension tamanho = new Dimension(250, 125);
        setPreferredSize(tamanho);
        setMaximumSize(tamanho);
        setMinimumSize(tamanho);
        setBackground(new Color(249, 249, 249));
        setLayout(new GridBagLayout());
        JLabel plusLabel = new JLabel("👤+");
        plusLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
        plusLabel.setForeground(Color.GRAY);
        plusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(plusLabel);
    }

}


/**
 * Card visual que representa um Responsável e as suas tarefas.
 */
class Responsavel extends JPanel {

    //Atributos
    private String id;
    private String nome;
    private JLabel labelname;
    private ArrayList<StikerTarefa> stickersAtribuidos;
    private JPanel panelTarefas;
    private JLabel dragLabel;
    JButton buttonEditar;
    JButton buttonRemover;

    //Construtor
    public Responsavel(String id, String nome) {
        iniResponsavel(id, nome);
        setupVariavels();
    }
    
    //MouseEvents
    public void mousePressed(MouseEvent e) {
        
        dragLabel = null;
    }

    public boolean mouseReleased(MouseEvent e) {
        
        if (dragLabel == null) {
            return false;
        }

        JLayeredPane lp = getRootPane().getLayeredPane();
        Point dropPoint = SwingUtilities.convertPoint(local(), e.getPoint(), lp);
        dragLabel.setVisible(false);
        Component target = SwingUtilities.getDeepestComponentAt(lp, dropPoint.x, dropPoint.y);
        dragLabel.setVisible(true);
        StikerTarefa sticker = null;
        Component check = target;
        
        while (check != null) {
            
            if (check instanceof StikerTarefa) {
                sticker = (StikerTarefa) check;
                break;
            }
            
            check = check.getParent();
        }

        if (sticker != null) {
            if (!stickersAtribuidos.contains(sticker)) {
                sticker.addResponsavel(local());
                addTarefa(sticker);
                lp.remove(dragLabel);
                dragLabel = null;
                lp.revalidate();
                lp.repaint();
                return true;
            }
        }

        lp.remove(dragLabel);
        dragLabel = null;
        lp.revalidate();
        lp.repaint();
        return false;
    }

    public void mouseDragged(MouseEvent e) {
        
        JLayeredPane lp = getRootPane().getLayeredPane();

        if (dragLabel == null) {
            dragLabel = new JLabel("👤+");
            dragLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 40));
            dragLabel.setForeground(new Color(138, 221, 133));
            dragLabel.setSize(dragLabel.getPreferredSize());
            lp.add(dragLabel, JLayeredPane.DRAG_LAYER);
        }

        Point p = SwingUtilities.convertPoint(local(), e.getPoint(), lp);
        dragLabel.setLocation(p.x, p.y);
        lp.repaint();
    }
    
    public Responsavel(String id, String nome) {
        iniResponsavel(id,nome);
        setupVariavels();        
    }
    
    public void setTarefa(StikerTarefa stikerTarefa){
        stickersAtribuidos.add(stikerTarefa);
        updateStickersAtribuidos();
    }
    
    private void iniResponsavel(String id, String nome){
        Dimension tamanho = new Dimension(250, 250);
        setPreferredSize(tamanho);
        setMaximumSize(tamanho);
        setMinimumSize(tamanho);
        setBackground(new Color(184, 204, 255));
        setLayout(new BorderLayout());
        setAlignmentX(Component.CENTER_ALIGNMENT);
        this.id = id;
        this.nome = nome;
        stickersAtribuidos = new ArrayList<>();
        
    }

    private void setupVariavels() {
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // label vars section
        labelname = new JLabel("<html><body style='width: 180px;'>" + "👤 " + nome + "</body></html>");
        labelname.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 16));
        labelname.setForeground(Color.BLACK);
        labelname.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelname.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        contentPanel.add(labelname);
        
        // panel vars section
        panelTarefas = new JPanel();
        panelTarefas.setLayout(new BoxLayout(panelTarefas, BoxLayout.Y_AXIS));
        panelTarefas.setOpaque(false);
        panelTarefas.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(panelTarefas);
        
        // create buttons panel and JButtons section
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelButtons.setOpaque(false);
        buttonEditar = new JButton("📝");
        buttonRemover = new JButton("🗑️");
        
        // buttons vars section
        buttonStyle(buttonEditar);
        buttonStyle(buttonRemover);
        panelButtons.add(buttonEditar);
        panelButtons.add(buttonRemover);
        panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        //ADD section
        add(contentPanel, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        
    }
    
    private void buttonStyle(JButton button) {
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    //Actions
    public void remover() {
        
        GrupoListaPanel coluna = (GrupoListaPanel) getParent();
        int index = coluna.getComponentZOrder(this);
        coluna.remove(index + 1);
        removeInStickers();
        coluna.remove(this);
        coluna.revalidate();
        coluna.repaint();
    }

    public void editar() {
        JPanel panelEdicao = new JPanel();
        panelEdicao.setLayout(new BoxLayout(panelEdicao, BoxLayout.Y_AXIS));

        JTextField fieldTitulo = new JTextField(nome);

        JPanel selectSticker = new JPanel();
        selectSticker.setLayout(new BoxLayout(selectSticker, BoxLayout.Y_AXIS));
        for (StikerTarefa sticker : stickersAtribuidos) {
            JCheckBox checkBox = new JCheckBox(sticker.getLabelTitulo().getText() + " #" + sticker.getID());
            checkBox.setSelected(true);
            selectSticker.add(checkBox);
        }

        panelEdicao.add(new JLabel("Nome:"));
        panelEdicao.add(fieldTitulo);
        panelEdicao.add(selectSticker);

        int result = JOptionPane.showConfirmDialog(this, panelEdicao,
                "Editar Membro #" + id, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            nome = fieldTitulo.getText();
            labelname.setText("<html><body style='width: 180px;'>" + "👤 " + nome + "</body></html>");

            for (int i = 0; i < selectSticker.getComponentCount(); i++) {
                if (selectSticker.getComponent(i) instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) selectSticker.getComponent(i);
                    if (!checkBox.isSelected()) {
                        stickersAtribuidos.get(i).removeResponsavel(this);
                        removeStickers(stickersAtribuidos.get(i));
                    }
                }
            }

            updateStickers();
            revalidate();
            repaint();
        }
    }

    private void removeInStickers() {
        for (StikerTarefa tarefa : stickersAtribuidos) {
            tarefa.removeResponsavel(this);
        }

        stickersAtribuidos = null;
    }
    
    //Actions com Sticker
    private void addTarefa(StikerTarefa sticker) {
        stickersAtribuidos.add(sticker);
        JLabel stickername = new JLabel("◼️ " + sticker.getLabelTitulo().getText());
        stickername.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
        stickername.setForeground(new Color(60, 60, 60));
        stickername.setAlignmentX(Component.LEFT_ALIGNMENT);
        stickername.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
        panelTarefas.add(stickername);

        panelTarefas.revalidate();
        panelTarefas.repaint();
    }
    
    public void removeStickers(StikerTarefa sticker) {
        stickersAtribuidos.remove(sticker);
        updateStickersAtribuidos();
    }
    
    public void setTarefa(StikerTarefa stikerTarefa) {
        
        stickersAtribuidos.add(stikerTarefa);
        updateStickersAtribuidos();
    }
    
    public void updateStickers() {
        for (StikerTarefa sticker : stickersAtribuidos) {
            sticker.updatePanelResponsaveis();
        }
    }
    
    public void updateStickersAtribuidos() {
        panelTarefas.removeAll();

        for (StikerTarefa sticker : stickersAtribuidos) {
            JLabel stickername = new JLabel("◼️ " + sticker.getLabelTitulo().getText());
            stickername.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 15));
            stickername.setForeground(new Color(60, 60, 60));
            stickername.setAlignmentX(Component.LEFT_ALIGNMENT);
            stickername.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 15));
            panelTarefas.add(stickername);
        }

        panelTarefas.revalidate();
        panelTarefas.repaint();
    }
   
    //getters
    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }
    
    public String getResponsavelLastStikerID() {
        
        return stickersAtribuidos.getLast().getID();
    }
    
    private Responsavel local() {   //TODO: questionar se faz sentido ter isto
        return this;
    }
    
    //listeners
    public void setEditarMembroMouseAdapter(MouseListener e) {
        buttonEditar.addMouseListener(e);
    }

    public void setRemoverMembroMouseAdapter(MouseListener e) {
        buttonRemover.addMouseListener(e);
    }

}


/**
 * Título do Grupo.
 */
class GrupoName extends JPanel {

    //Construtor
    public GrupoName() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel labelName = new JLabel("Grupo");
        labelName.setFont(new Font("Arial", Font.BOLD, 20));
        labelName.setForeground(Color.GRAY);
        labelName.setHorizontalAlignment(SwingConstants.LEFT);

        Border margin = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        setBorder(margin);

        add(labelName);

    }
}
