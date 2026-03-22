import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class KanbanLayout {

    // ── Cores ───────────────────────────────────────────────
    private static final Color COR_FUNDO       = new Color(245, 245, 245);
    private static final Color COR_STICKER     = new Color(255, 182, 193);   // rosa
    private static final Color COR_HEAD        = new Color(230, 230, 230);
    private static final Color COR_BODY        = new Color(250, 250, 250);
    private static final Color COR_NAV         = new Color(220, 220, 220);
    private static final Color COR_TEXTO       = new Color(60, 60, 60);
    private static final Color COR_BORDA       = new Color(180, 180, 180);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KanbanLayout::criarJanela);
    }

    // ════════════════════════════════════════════════════════
    //  JFrame  –  BorderLayout
    // ════════════════════════════════════════════════════════
    private static void criarJanela() {

        // ── JFrame (BorderLayout) ──────────────────────────
        JFrame window = new JFrame("window");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1100, 650);
        window.setLayout(new BorderLayout());               // JFrame Boarder layout
        window.getContentPane().setBackground(COR_FUNDO);

        // ════════════════════════════════════════════════════
        //  NORTH  –  Start JFrame Flow  (Nav menus)
        // ════════════════════════════════════════════════════
        JPanel startJFrameFlow = new JPanel();
        startJFrameFlow.setLayout(new BoxLayout(startJFrameFlow, BoxLayout.Y_AXIS));

        // ── TabMainMenu  (Nav menu) ────────────────────────
        JPanel tabMainMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabMainMenu.setName("TabMainMenu");
        tabMainMenu.setBackground(COR_NAV);
        tabMainMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COR_BORDA));
        JLabel lblNavMenu = new JLabel("Nav menu");
        lblNavMenu.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNavMenu.setForeground(COR_TEXTO);
        tabMainMenu.add(lblNavMenu);

        // ── TabProjetoMenu ─────────────────────────────────
        JPanel tabProjetoMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tabProjetoMenu.setName("TabProjetoMenu");
        tabProjetoMenu.setBackground(COR_NAV.brighter());
        tabProjetoMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COR_BORDA));
        JLabel lblProjeto = new JLabel("TabProjetoMenu");
        lblProjeto.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblProjeto.setForeground(COR_TEXTO);
        tabProjetoMenu.add(lblProjeto);

        startJFrameFlow.add(tabMainMenu);                    // Start JFrame boarder layout
        startJFrameFlow.add(tabProjetoMenu);                  // End JFrame Flow
        window.add(startJFrameFlow, BorderLayout.NORTH);

        // ════════════════════════════════════════════════════
        //  WEST  –  "?" botão
        // ════════════════════════════════════════════════════
        JButton btnHelp = new JButton("?");
        btnHelp.setFont(new Font("SansSerif", Font.BOLD, 22));
        btnHelp.setPreferredSize(new Dimension(50, 50));
        btnHelp.setFocusPainted(false);
        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        westPanel.setBackground(COR_FUNDO);
        westPanel.add(btnHelp);
        window.add(westPanel, BorderLayout.WEST);

        // ════════════════════════════════════════════════════
        //  CENTER  –  JScrollPane  ▸  Flow «table»
        // ════════════════════════════════════════════════════

        //  Painel interior com FlowLayout (a "table")
        JPanel table = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        table.setName("table");
        table.setBackground(COR_FUNDO);

        // ── Coluna 1 ──────────────────────────────────────
        table.add(criarColunaJpanel("Coluna 1",
                new String[][]{
                        {"Type anything, @mention anyone", "Bernardo Silva"},
                        {"Type anything, @mention anyone", "Bernardo Silva"}
                }));

        // ── Coluna 2 ──────────────────────────────────────
        table.add(criarColunaJpanel("Coluna 2",
                new String[][]{
                        {"Type anything, @mention anyone", "Germano Silva"}
                }));

        // ── Coluna 3 ──────────────────────────────────────
        table.add(criarColunaJpanel("Coluna 3",
                new String[][]{
                        {"Type anything, @mention anyone", "Germano Silva"}
                }));

        // ── ADD  (JPanel para adicionar nova coluna) ──────
        JPanel addPanel = criarAddColunaPanel();
        table.add(addPanel);

        // Definir tamanho preferido para scroll horizontal
        table.setPreferredSize(new Dimension(
                4 * 230 + 5 * 15,   // 4 colunas + gaps
                500));

        // ── Center Jscroller Frame ────────────────────────
        JScrollPane jscroller = new JScrollPane(table);
        jscroller.setName("Jscroller");
        jscroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jscroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jscroller.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                "Jscroller",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.PLAIN, 10), COR_TEXTO));
        jscroller.getViewport().setBackground(COR_FUNDO);

        window.add(jscroller, BorderLayout.CENTER);          // Center Jscroller Frame

        // ── Mostrar ───────────────────────────────────────
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // ════════════════════════════════════════════════════════
    //  ColunaJpanel  –  BorderLayout  (ColunaLayout)
    // ════════════════════════════════════════════════════════
    private static JPanel criarColunaJpanel(String titulo, String[][] stickers) {

        // ── JPanel  ColunaJpanel ──────────────────────────
        JPanel colunaJpanel = new JPanel(new BorderLayout(0, 5)); // Boarder layout  ColunaLayout
        colunaJpanel.setName("ColunaJpanel");
        colunaJpanel.setPreferredSize(new Dimension(220, 420));
        colunaJpanel.setBackground(COR_FUNDO);
        colunaJpanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // ── HeadColuna  (Start flow Titulo) ───────────────
        JPanel headColuna = new JPanel(new FlowLayout(FlowLayout.LEFT));  // Start flow Titulo
        headColuna.setName("HeadColuna");
        headColuna.setBackground(COR_HEAD);
        headColuna.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, COR_BORDA),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblTitulo.setForeground(COR_TEXTO);
        headColuna.add(lblTitulo);

        colunaJpanel.add(headColuna, BorderLayout.NORTH);    // HeadColuna no NORTH

        // ── BodyColuna  (Center Flow Stickers) ────────────
        JPanel bodyColuna = new JPanel();
        bodyColuna.setLayout(new BoxLayout(bodyColuna, BoxLayout.Y_AXIS));
        bodyColuna.setName("BodyColuna");
        bodyColuna.setBackground(COR_BODY);
        bodyColuna.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Painel com FlowLayout para os stickers
        JPanel stickersFlow = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        stickersFlow.setName("Center Flow Stickers");
        stickersFlow.setBackground(COR_BODY);

        for (String[] sticker : stickers) {
            stickersFlow.add(criarSticker(sticker[0], sticker[1]));
        }

        bodyColuna.add(stickersFlow);

        // ── Botão "+" para adicionar sticker ──────────────
        JPanel addStickerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addStickerPanel.setBackground(COR_BODY);
        JButton btnAddSticker = new JButton("+");
        btnAddSticker.setFont(new Font("SansSerif", Font.BOLD, 24));
        btnAddSticker.setPreferredSize(new Dimension(60, 40));
        btnAddSticker.setFocusPainted(false);
        btnAddSticker.setBackground(Color.WHITE);
        btnAddSticker.setBorder(BorderFactory.createLineBorder(COR_BORDA));
        addStickerPanel.add(btnAddSticker);
        bodyColuna.add(addStickerPanel);

        colunaJpanel.add(bodyColuna, BorderLayout.CENTER);   // BodyColuna no CENTER

        return colunaJpanel;
    }

    // ════════════════════════════════════════════════════════
    //  Sticker  –  cartão rosa
    // ════════════════════════════════════════════════════════
    private static JPanel criarSticker(String texto, String autor) {
        JPanel sticker = new JPanel();
        sticker.setLayout(new BorderLayout(4, 4));
        sticker.setPreferredSize(new Dimension(130, 150));
        sticker.setBackground(COR_STICKER);
        sticker.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_STICKER.darker()),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));

        JTextArea txtArea = new JTextArea(texto);
        txtArea.setFont(new Font("SansSerif", Font.PLAIN, 10));
        txtArea.setForeground(COR_TEXTO);
        txtArea.setBackground(COR_STICKER);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setEditable(false);
        txtArea.setBorder(null);

        JLabel lblAutor = new JLabel(autor);
        lblAutor.setFont(new Font("SansSerif", Font.ITALIC, 9));
        lblAutor.setForeground(COR_TEXTO);

        sticker.add(txtArea, BorderLayout.CENTER);
        sticker.add(lblAutor, BorderLayout.SOUTH);

        return sticker;
    }

    // ════════════════════════════════════════════════════════
    //  ADD  –  painel para adicionar nova coluna
    // ════════════════════════════════════════════════════════
    private static JPanel criarAddColunaPanel() {
        JPanel addPanel = new JPanel(new BorderLayout());
        addPanel.setName("ADD");
        addPanel.setPreferredSize(new Dimension(220, 420));
        addPanel.setBackground(COR_FUNDO);
        addPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createDashedBorder(COR_BORDA, 3, 5),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JLabel lblAdd = new JLabel("ADD");
        lblAdd.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblAdd.setForeground(COR_TEXTO);
        lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
        addPanel.add(lblAdd, BorderLayout.NORTH);

        JButton btnAdd = new JButton("+");
        btnAdd.setFont(new Font("SansSerif", Font.BOLD, 36));
        btnAdd.setFocusPainted(false);
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setBorder(BorderFactory.createLineBorder(COR_BORDA));
        btnAdd.setPreferredSize(new Dimension(80, 60));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(COR_FUNDO);
        centerPanel.add(btnAdd);

        addPanel.add(centerPanel, BorderLayout.CENTER);

        return addPanel;
    }
}
