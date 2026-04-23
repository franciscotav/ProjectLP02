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

/**
 * Contentor principal com suporte a scroll do quadro Kanban.
 * Atua como a moldura externa que permite a navegação horizontal quando o número 
 * de colunas de estados excede a largura da janela.
 * Delega a gestão de eventos das colunas e tarefas para o painel interno de Estados.
 */
public class Quadro extends JScrollPane {

    //atributos
    private Estados estados;

    //Construtor
    public Quadro() {
        estados = new Estados();
        estados.setAlignmentY(Component.TOP_ALIGNMENT);
        this.setViewportView(estados);

    }
    
    //listeners
    public void setAddicionarColunaButtonMouseAdapter(MouseListener e) {
        estados.setAddicionarColunaButtonMouseAdapter(e);
    }

    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e) {
        estados.setAddicionarTarefaButtonMouseAdapter(e);
    }

    public void setEditarColunaButtonMouseAdapter(MouseListener e) {
        estados.setEditarColunaButtonMouseAdapter(e);
    }

    public void setRemoverColunaButtonMouseAdapter(MouseListener e) {
        estados.setRemoverColunaButtonMouseAdapter(e);
    }
    
    //getters
    public Estados getEstados() {
        return estados;
    }

    public TarefaColuna getlastTarefaColuna() {
        TarefaColuna tarefaColuna = null;

        for (int i = 0; i < estados.getComponentCount(); i++) {
            if (estados.getComponent(i) instanceof TarefaColuna) {
                tarefaColuna = (TarefaColuna) estados.getComponent(i);
            }
        }

        return tarefaColuna;
    }
}

/**
 * Painel de organização horizontal dos estados do projeto.
 * Gere a disposição das colunas (TarefaColuna) e do botão de adição de novas colunas.
 * Funciona como o motor de distribuição de listeners para os componentes dinâmicos do quadro.
 */
class Estados extends JPanel {

    //atributos
    private BoxLayout estadosLayout;
    private ColunaAddicionar colunaAddiconar;

    //Construtor
    public Estados() {
        estadosLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(estadosLayout);

        colunaAddiconar = new ColunaAddicionar();

        add(Box.createVerticalGlue());
        add(colunaAddiconar);
    }
    
    //getters
    public ColunaAddicionar getColunaAddicionar() {
        return colunaAddiconar;
    }
    
    //listeners
    public void setAddicionarColunaButtonMouseAdapter(MouseListener e) {
        colunaAddiconar.addMouseListener(e);
    }

    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e) {
        TarefaColuna lastTarefaColuna = null;
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (getComponent(i) instanceof TarefaColuna) {
                lastTarefaColuna = (TarefaColuna) getComponent(i);
            }
        }

        if (lastTarefaColuna != null) {
            lastTarefaColuna.setAddicionarTarefaButtonMouseAdapter(e);
        }
    }

    public void setEditarColunaButtonMouseAdapter(MouseListener e) {
        TarefaColuna lastTarefaColuna = null;
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (getComponent(i) instanceof TarefaColuna) {
                lastTarefaColuna = (TarefaColuna) getComponent(i);
            }
        }

        if (lastTarefaColuna != null) {
            lastTarefaColuna.setEditarColunaButtonMouseAdapter(e);
        }
    }

    public void setRemoverColunaButtonMouseAdapter(MouseListener e) {
        TarefaColuna lastTarefaColuna = null;
        for (int i = 0; i < this.getComponentCount(); i++) {
            if (getComponent(i) instanceof TarefaColuna) {
                lastTarefaColuna = (TarefaColuna) getComponent(i);
            }
        }

        if (lastTarefaColuna != null) {
            lastTarefaColuna.setRemoverColunaButtonMouseAdapter(e);
        }
    }
}


/**
 * Representação visual de uma coluna de estado no Kanban (ex: "A Fazer", "Concluído").
 * Agrupa verticalmente o menu da coluna, as tarefas individuais e o botão de criação de stickers.
 * Define a estrutura visual e as margens que delimitam cada categoria de tarefas.
 */
class TarefaColuna extends JPanel {

    //atributos
    private BoxLayout tarefasLayout;
    private ColunaMenu colunaMenu;
    private StickerAddicionar stickerAddicionar;
    private String nome;
    private String id;

    //Construtor
    public TarefaColuna(String id, String nome) {
        tarefasLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        Border margin = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        Border line = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border combined = BorderFactory.createCompoundBorder(line, margin);
        setBorder(combined);

        setLayout(tarefasLayout);

        this.id = id;
        this.nome = nome;

        colunaMenu = new ColunaMenu(nome);
        stickerAddicionar = new StickerAddicionar();

        add(colunaMenu);
        add(stickerAddicionar);

    }
    
    //getters
    public String getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public StickerAddicionar getStickerAddicionar() {
        return stickerAddicionar;
    }
    
    //listeners
    public void setAddicionarTarefaButtonMouseAdapter(MouseListener e) {
        stickerAddicionar.addMouseListener(e);
    }

    public void setEditarColunaButtonMouseAdapter(MouseListener e) {
        colunaMenu.setEditarColunaButtonMouseAdapter(e);
    }

    public void setRemoverColunaButtonMouseAdapter(MouseListener e) {
        colunaMenu.setRemoverColunaButtonMouseAdapter(e);
    }
}

/**
 * Componente de cabeçalho e controlo da coluna de tarefas.
 * Exibe o nome do estado e disponibiliza botões para edição do título ou remoção da coluna.
 * Implementa a lógica de limpeza de referências de tarefas ao eliminar uma coluna inteira.
 */
class ColunaMenu extends JPanel {

    //atributos
    private JLabel labelName;
    private JButton buttonEditar;
    private JButton buttonRemover;

    //Construtor
    public ColunaMenu(String nomeColuna) {
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
    
    //setup
    private void buttonStyle(JButton button) {
        button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 45));
        button.setForeground(Color.gray);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    //listeners
    public void setEditarColunaButtonMouseAdapter(MouseListener e) {
        buttonEditar.addMouseListener(e);
    }

    public void setRemoverColunaButtonMouseAdapter(MouseListener e) {
        buttonRemover.addMouseListener(e);
    }
    
    //button logic
    public void buttonRemoverClicked() {
        if (getParent() instanceof TarefaColuna) {
            TarefaColuna tarefaColuna = (TarefaColuna) getParent();

            for (int i = 0; i < tarefaColuna.getComponentCount(); i++) {
                if (tarefaColuna.getComponent(i) instanceof StikerTarefa) {
                    StikerTarefa tarefa = (StikerTarefa) tarefaColuna.getComponent(i);
                    tarefa.removeInGrupos();
                }
            }
        }

        if (getParent().getParent() instanceof Estados) {
            Estados estados = (Estados) getParent().getParent();
            int index = estados.getComponentZOrder(getParent());
            estados.remove(index);

            estados.revalidate();
            estados.repaint();
        }
    }

    public void buttonEditarClicked() {
        JPanel panelEdicao = new JPanel();
        panelEdicao.setLayout(new BoxLayout(panelEdicao, BoxLayout.Y_AXIS));

        JTextField fieldTitulo = new JTextField(labelName.getText());

        panelEdicao.add(new JLabel("Titulo:"));
        panelEdicao.add(fieldTitulo);

        int result = JOptionPane.showConfirmDialog(this, panelEdicao,
                "Editar Estado #" + ((TarefaColuna) getParent()).getId(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            getParent().setName(fieldTitulo.getText());
            labelName.setText(fieldTitulo.getText());
            this.revalidate();
            this.repaint();
        }
    }
    
    //getters
    public String getName() {
        return labelName.toString();
    }

}
/**
 * Contentor de posicionamento para o botão de criação de novos estados.
 * Utiliza GridBagLayout para garantir que o componente de adição se mantém alinhado 
 * corretamente no final da lista de colunas.
 */
class ColunaAddicionar extends JPanel {

    //Construtor
    public ColunaAddicionar() {
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

    public void mouseClicked(String id, String nome) {
        if (getParent() instanceof Estados) {
            Estados estados = (Estados) getParent();

            int pos = Math.max(0, estados.getComponentCount() - 1);
            estados.add(new TarefaColuna(id, nome), pos);

            estados.revalidate();
            estados.repaint();
        }
    }
}

/**
 * Botão visual para a criação de novas colunas.
 * Apresenta um design padronizado com um símbolo de soma (+) para indicar a 
 * funcionalidade de expansão do quadro Kanban.
 */
class ColunaAddicionarButton extends JPanel {

    //Construtor
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
