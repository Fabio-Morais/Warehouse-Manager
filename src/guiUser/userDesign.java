package guiUser;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.color.CMMException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import db.DataBase;
import gui.PopUp;
import guiAdmin.AdminDesign;
//import GUI.Maquinas;
import guiLogin.LoginDesign;
import logic.Check;
import logic.MessageLogs;

import javax.swing.ImageIcon;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class userDesign {

	private String nomeArmazem;
	private String username;

	private JFrame frmUserDesign;
	// Imagens
	private static final String ADD = "/add.png";
	private static final String REMOVE = "/remove.png";
	private static final String HOME = "/home.png";
	private static final String REFRESH = "/refresh.png";
	private static final String EDIT = "/edit1.png";

	private static final String MORE = "/more.png";
	private static final String WEBSITE = "/website.PNG";
	private static final String HELP = "/help.png";
	private static final String HOMEMIN = "/homeMin.png";
	private static final String LOGOUT = "/logout.png";
	private static final String CHANGE = "/change.png";
	private static final String RECEBER = "/receber.png";
	private static final String MAQUINA = "/machineMin.png";
	private static final String ENVIAR = "/truckMin.png";
	private static final String FUNCIONARIO = "/avatar.png";
	private static final String RELATORIO = "/report.png";
	private static final String VENDAS = "/vendas.png";
	private static final String PRODUTO = "/box.png";
	private static final String GRAFICO = "/grafico.png";

	// Classes
	private Maquinas maquinaClass;
	private Funcionario funcionarioClasse;
	private Produtos produtoClass;
	private Vendas vendasClass;
	private EnviarProduto enviarprodutoClass;
	private ReceberProduto receberprodutoClass;
	private CriarCsv criarCsvClasse;
	private Graficos Chart;

	// Panels
	private JPanel userDesign;
	private JPanel Vendas;
	private JPanel Maquinas;
	private JPanel Funcionarios;
	private JPanel Produtos;

	private DataBase db;

	// Botoes
	// Main Menu
	private JButton btnVendas;
	private JButton btnProdutos;
	private JButton btnFuncionarios;
	private JButton btnMaquinas;
	private JButton btnRececaoProdutos;
	private JButton btnEnviarProduto;
	private JButton btnGerarRelatorioStock;

	// Menu Vendas
	private JButton btnRefreshVendas;
	private JButton btnHomeVendas;
	private JTable table_Vendas;
	private DefaultTableModel modelVendas;
	private TableRowSorter<DefaultTableModel> sorterVendas;

	// Menu Maquinas

	private JButton btnRefreshMaquinas;
	private JButton btnHomeMaquinas;
	private JButton btnReportarAvaria;
	private JTable table_Maquinas;
	private DefaultTableModel model_Maquinas;
	private JToggleButton tglbtnFiltrarAvariadas;
	private TableRowSorter<DefaultTableModel> sorterMaquinas;
	private JButton btnCorrigirAvaria;
	// Menu Funcionarios

	private JButton btnRefreshFuncionarios;
	private JButton btnHomeFuncionarios;
	private DefaultTableModel modelFuncionarios;
	private JTable tabela_Funcionarios;
	private TableRowSorter<DefaultTableModel> sorterFuncionario;

	// Menu Produtos

	private JButton btnRefreshProdutos;
	private JButton btnHomeProdutos;
	private DefaultTableModel modelProdutos;
	private JTable table_Produtos;
	private TableRowSorter<DefaultTableModel> sorterProdutos;
	private JButton btnMarcarDefeitoProdutos;

	//menu grafico
	private JButton btnGraficos;
	private JComboBox selectGraph;
	
	/**
	 * Launch the application.
	 */
	private CardLayout cl;
	private CardLayout clGrafico;

	private JTextField skuField;
	private JTextField dataField;
	private JTextField userField;
	private JMenuItem mntmMenu;
	private JMenuItem mntmExit1;
	private JMenuItem menuWebsite;
	private JMenuItem menuHelpMe;
	private JLabel lblArmazem;
	private JLabel lblStatusDb;
	private JMenu mnNewMenu;

	/******************/
	private Timer counterTimer;

	private Check check;
	private PopUp popUp;

	private MessageLogs messageLogs;

	/**
	 * Adiciona os panels a janela principal
	 */
	private void putPanels() {
		frmUserDesign.getContentPane().add(userDesign, "userDesign");
		frmUserDesign.getContentPane().add(Vendas, "Vendas");
		frmUserDesign.getContentPane().add(Maquinas, "Maquinas");
		frmUserDesign.getContentPane().add(Funcionarios, "Funcionarios");
		frmUserDesign.getContentPane().add(Produtos, "Produtos");
		frmUserDesign.getContentPane().add(Grafico, "Grafico");
		cl.show(frmUserDesign.getContentPane(), "userDesign");// mostrar o main menu
	}

	/**
	 * Create the application.
	 */
	public userDesign(String armazem, String username) {
		this.nomeArmazem = armazem;
		this.maquinaClass = new Maquinas(username);
		this.funcionarioClasse = new Funcionario();
		this.vendasClass = new Vendas();
		this.produtoClass = new Produtos(username);
		this.enviarprodutoClass = new EnviarProduto(username);
		this.receberprodutoClass = new ReceberProduto(username);
		this.username= username;
		this.criarCsvClasse = new CriarCsv();
		this.Chart = new Graficos();
		db = DataBase.getInstance();
		this.messageLogs = MessageLogs.getInstance();
		check = new Check();
		popUp = new PopUp();
		initialize();
	}

	/**
	 * Menu Utilizador
	 */
	private void showMainMenu() {

		userDesign = new JPanel();
		frmUserDesign.getContentPane().add(userDesign, "name_144798506529000");
		userDesign.setLayout(new BorderLayout(0, 0));

		JPanel MainMenuHome = new JPanel();
		userDesign.add(MainMenuHome, BorderLayout.NORTH);

		btnProdutos = new JButton("<html>Produtos<html>");
		btnProdutos.setMargin(new Insets(2, 14, 10, 14));
		btnProdutos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProdutos.setVerticalTextPosition(SwingConstants.TOP);
		btnProdutos.setVerticalAlignment(SwingConstants.BOTTOM);
		btnProdutos.setIcon(new ImageIcon(AdminDesign.class.getResource(PRODUTO)));
		btnProdutos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProdutos.setFont(new Font("Consolas", Font.BOLD, 13));
		btnProdutos.setBackground(Color.LIGHT_GRAY);

		btnFuncionarios = new JButton("Funcionarios");
		btnFuncionarios.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFuncionarios.setVerticalAlignment(SwingConstants.BOTTOM);
		btnFuncionarios.setVerticalTextPosition(SwingConstants.TOP);
		btnFuncionarios.setIcon(new ImageIcon(AdminDesign.class.getResource(FUNCIONARIO)));
		btnFuncionarios.setMargin(new Insets(2, 0, 10, 0));
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFuncionarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFuncionarios.setFont(new Font("Consolas", Font.BOLD, 12));
		btnFuncionarios.setBackground(Color.LIGHT_GRAY);

		btnMaquinas = new JButton("Maquinas");
		btnMaquinas.setMargin(new Insets(2, 14, 10, 14));
		btnMaquinas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMaquinas.setVerticalTextPosition(SwingConstants.TOP);
		btnMaquinas.setVerticalAlignment(SwingConstants.BOTTOM);
		btnMaquinas.setIcon(new ImageIcon(AdminDesign.class.getResource(MAQUINA)));
		btnMaquinas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMaquinas.setFont(new Font("Consolas", Font.BOLD, 13));
		btnMaquinas.setBackground(Color.LIGHT_GRAY);

		btnVendas = new JButton("<html>Vendas<html>");
		btnVendas.setMargin(new Insets(2, 14, 10, 14));
		btnVendas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVendas.setVerticalTextPosition(SwingConstants.TOP);
		btnVendas.setVerticalAlignment(SwingConstants.BOTTOM);
		btnVendas.setIcon(new ImageIcon(AdminDesign.class.getResource(VENDAS)));
		btnVendas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendas.setFont(new Font("Consolas", Font.BOLD, 13));
		btnVendas.setBackground(Color.LIGHT_GRAY);

		JLabel lblArmazem = new JLabel("Armazem");
		lblArmazem.setFont(new Font("Dialog", Font.BOLD, 18));

		btnRececaoProdutos = new JButton("Receção lote");
		btnRececaoProdutos.setMargin(new Insets(10, 2, 10, 2));

		btnRececaoProdutos.setVerticalTextPosition(SwingConstants.TOP);
		btnRececaoProdutos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRececaoProdutos.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRececaoProdutos.setIcon(new ImageIcon(AdminDesign.class.getResource(RECEBER)));
		btnRececaoProdutos.setFont(new Font("Consolas", Font.BOLD, 13));
		btnRececaoProdutos.setBackground(Color.LIGHT_GRAY);

		btnEnviarProduto = new JButton("<html>Enviar<br>Produto<html>");
		btnEnviarProduto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEnviarProduto.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEnviarProduto.setVerticalTextPosition(SwingConstants.TOP);
		btnEnviarProduto.setIcon(new ImageIcon(AdminDesign.class.getResource(ENVIAR)));

		btnEnviarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEnviarProduto.setFont(new Font("Consolas", Font.BOLD, 13));
		btnEnviarProduto.setBackground(Color.LIGHT_GRAY);

		btnGerarRelatorioStock = new JButton("<html>Gerar<br>Relatorio<br><html>");
		btnGerarRelatorioStock.setVerticalTextPosition(SwingConstants.TOP);
		btnGerarRelatorioStock.setVerticalAlignment(SwingConstants.BOTTOM);
		btnGerarRelatorioStock.setIcon(new ImageIcon(AdminDesign.class.getResource(RELATORIO)));
		btnGerarRelatorioStock.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnGerarRelatorioStock.setFont(new Font("Consolas", Font.BOLD, 13));
		btnGerarRelatorioStock.setBackground(Color.LIGHT_GRAY);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLUE);

		JLabel lblRececao = new JLabel("Rece\u00E7\u00E3o");
		lblRececao.setFont(new Font("Dialog", Font.BOLD, 18));

		JLabel lblEnviar = new JLabel("Enviar");
		lblEnviar.setFont(new Font("Dialog", Font.BOLD, 18));

		JLabel lblRelatorios = new JLabel("Relatorios");
		lblRelatorios.setFont(new Font("Dialog", Font.BOLD, 18));

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLUE);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLUE);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLUE);

		JEditorPane dtrpnUser = new JEditorPane();
		dtrpnUser.setText("USER");
		dtrpnUser.setIgnoreRepaint(true);
		dtrpnUser.setFont(new Font("Lucida Sans", Font.BOLD, 22));
		dtrpnUser.setFocusable(false);
		dtrpnUser.setEditable(false);
		dtrpnUser.setBorder(new MatteBorder(1, 1, 3, 1, (Color) new Color(0, 0, 0)));
		dtrpnUser.setBackground(new Color(255, 250, 250));

		JSeparator separator_4 = new JSeparator();
		separator_4.setPreferredSize(new Dimension(0, 50));
		separator_4.setMinimumSize(new Dimension(20, 20));
		separator_4.setForeground(Color.BLUE);
		separator_4.setFont(new Font("Dialog", Font.BOLD, 15));

		JSeparator separator_5 = new JSeparator();
		separator_5.setPreferredSize(new Dimension(0, 50));
		separator_5.setMinimumSize(new Dimension(20, 20));
		separator_5.setForeground(Color.BLUE);
		separator_5.setFont(new Font("Dialog", Font.BOLD, 15));

		JSeparator separator_6 = new JSeparator();
		separator_6.setPreferredSize(new Dimension(0, 50));
		separator_6.setMinimumSize(new Dimension(20, 20));
		separator_6.setForeground(Color.BLUE);
		separator_6.setFont(new Font("Dialog", Font.BOLD, 15));

		JSeparator separator_7 = new JSeparator();
		separator_7.setPreferredSize(new Dimension(0, 50));
		separator_7.setMinimumSize(new Dimension(20, 20));
		separator_7.setForeground(Color.BLUE);
		separator_7.setFont(new Font("Dialog", Font.BOLD, 15));

		JSeparator separator_8 = new JSeparator();
		separator_8.setPreferredSize(new Dimension(0, 50));
		separator_8.setMinimumSize(new Dimension(20, 20));
		separator_8.setForeground(Color.BLUE);
		separator_8.setFont(new Font("Dialog", Font.BOLD, 15));

		btnGraficos = new JButton("Graficos");
		
		btnGraficos.setMargin(new Insets(2, 14, 10, 14));
		btnGraficos.setIcon(new ImageIcon(AdminDesign.class.getResource(GRAFICO)));
		btnGraficos.setVerticalTextPosition(SwingConstants.TOP);
		btnGraficos.setVerticalAlignment(SwingConstants.BOTTOM);
		btnGraficos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGraficos.setFont(new Font("Consolas", Font.BOLD, 13));
		btnGraficos.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_MainMenuHome = new GroupLayout(MainMenuHome);
		gl_MainMenuHome.setHorizontalGroup(
			gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainMenuHome.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_MainMenuHome.createSequentialGroup()
							.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_MainMenuHome.createSequentialGroup()
										.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(btnFuncionarios, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_MainMenuHome.createSequentialGroup()
											.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(btnMaquinas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblArmazem, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGap(51)
							.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_MainMenuHome.createSequentialGroup()
									.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRececao, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(separator_8, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnRececaoProdutos, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
									.addGap(39)
									.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
										.addComponent(btnEnviarProduto, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEnviar, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
									.addGap(57)
									.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblRelatorios)
										.addComponent(separator_7, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addComponent(btnGerarRelatorioStock, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
								.addComponent(btnGraficos, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(142)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(214))
				.addGroup(gl_MainMenuHome.createSequentialGroup()
					.addContainerGap()
					.addComponent(dtrpnUser, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1041, Short.MAX_VALUE))
		);
		gl_MainMenuHome.setVerticalGroup(
			gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainMenuHome.createSequentialGroup()
					.addContainerGap()
					.addComponent(dtrpnUser, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_MainMenuHome.createSequentialGroup()
							.addGap(30)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_MainMenuHome.createSequentialGroup()
					.addGap(100)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 159, Short.MAX_VALUE)
					.addGap(81))
				.addGroup(gl_MainMenuHome.createSequentialGroup()
					.addGap(72)
					.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MainMenuHome.createSequentialGroup()
							.addComponent(lblRelatorios, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_7, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGerarRelatorioStock, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGraficos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_MainMenuHome.createSequentialGroup()
							.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRececao, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblArmazem))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_MainMenuHome.createSequentialGroup()
									.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
										.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnMaquinas, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
									.addGap(21)
									.addGroup(gl_MainMenuHome.createParallelGroup(Alignment.LEADING)
										.addComponent(btnFuncionarios, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_MainMenuHome.createSequentialGroup()
									.addComponent(separator_8, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
									.addGap(19)
									.addComponent(btnRececaoProdutos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_MainMenuHome.createSequentialGroup()
							.addComponent(lblEnviar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEnviarProduto, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		MainMenuHome.setLayout(gl_MainMenuHome);
	}

	/**
	 * Menu Vendas
	 */
	private void showVendasMenu() {
		Vendas = new JPanel();
		frmUserDesign.getContentPane().add(Vendas, "name_148919274819900");
		Vendas.setLayout(new BorderLayout(0, 0));

		JPanel VendasMenu = new JPanel();
		Vendas.add(VendasMenu, BorderLayout.CENTER);

		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setFont(new Font("Dialog", Font.BOLD, 32));

		btnRefreshVendas = new JButton("Refresh");
		btnRefreshVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnRefreshVendas.setIcon(new ImageIcon(userDesign.class.getResource("/refresh.png")));
		btnRefreshVendas.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshVendas.setBackground(Color.LIGHT_GRAY);

		btnHomeVendas = new JButton("Home");
		btnHomeVendas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHomeVendas.setHorizontalAlignment(SwingConstants.LEFT);
		btnHomeVendas.setVerticalAlignment(SwingConstants.TOP);

		btnHomeVendas.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnHomeVendas.setBackground(Color.LIGHT_GRAY);
		btnHomeVendas.setIcon(new ImageIcon(userDesign.class.getResource("/home.png")));

		JScrollPane scrollPane = new JScrollPane();

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(Color.BLUE);

		vendasSearch = new JTextField();
		vendasSearch.setText("Quick Access");
		vendasSearch.setToolTipText("Quick Access");
		vendasSearch.setColumns(10);
		GroupLayout gl_VendasMenu = new GroupLayout(VendasMenu);
		gl_VendasMenu.setHorizontalGroup(gl_VendasMenu.createParallelGroup(Alignment.LEADING).addGroup(gl_VendasMenu
				.createSequentialGroup().addGap(48)
				.addGroup(gl_VendasMenu.createParallelGroup(Alignment.LEADING, false).addGroup(gl_VendasMenu
						.createSequentialGroup()
						.addGroup(gl_VendasMenu.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_VendasMenu.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnHomeVendas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRefreshVendas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGap(134)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_VendasMenu.createSequentialGroup().addComponent(lblVendas)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(vendasSearch, GroupLayout.PREFERRED_SIZE, 118,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(60, Short.MAX_VALUE)));
		gl_VendasMenu.setVerticalGroup(gl_VendasMenu.createParallelGroup(Alignment.LEADING).addGroup(gl_VendasMenu
				.createSequentialGroup().addGap(42)
				.addGroup(gl_VendasMenu.createParallelGroup(Alignment.TRAILING).addComponent(lblVendas).addComponent(
						vendasSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_VendasMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
						.addGroup(gl_VendasMenu.createSequentialGroup()
								.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addGap(39)
								.addComponent(btnRefreshVendas, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnHomeVendas)))
				.addContainerGap()));

		modelVendas = new DefaultTableModel(new Object[][] {},
				new String[] { "data Saida", "SKU", "Produto", "destino" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Vendas = new JTable();
		table_Vendas.setModel(modelVendas);
		table_Vendas.getColumnModel().getColumn(0).setResizable(true);
		table_Vendas.getColumnModel().getColumn(0).setMinWidth(140);
		table_Vendas.getColumnModel().getColumn(0).setMaxWidth(150);
		table_Vendas.getColumnModel().getColumn(1).setResizable(true);
		table_Vendas.getColumnModel().getColumn(1).setMinWidth(90);
		table_Vendas.getColumnModel().getColumn(1).setMaxWidth(120);
		table_Vendas.getColumnModel().getColumn(2).setMinWidth(90);
		table_Vendas.getColumnModel().getColumn(2).setMaxWidth(120);
		table_Vendas.getColumnModel().getColumn(3).setMinWidth(90);
		table_Vendas.getColumnModel().getColumn(3).setMaxWidth(120);
		scrollPane.setViewportView(table_Vendas);
		VendasMenu.setLayout(gl_VendasMenu);

		table_Vendas.getTableHeader().setReorderingAllowed(false);
		table_Vendas.setAutoCreateRowSorter(true);// para ordenar
		table_Vendas.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.produtoVendido(modelVendas);
		
		
		sorterVendas = new TableRowSorter<>(modelVendas);
		table_Vendas.setRowSorter(sorterVendas);

		vendasSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vendasSearch.setText("");
			}
		});
		vendasSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(vendasSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(vendasSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(vendasSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterVendas.setRowFilter(null);
				} else {
					sorterVendas.setRowFilter(RowFilter.regexFilter("(?i)" +str));
				}
			}
		});

	}

	/**
	 * Menu Maquinas
	 */
	private void showMaquinasMenu() {
		Maquinas = new JPanel();
		frmUserDesign.getContentPane().add(Maquinas, "name_111064051438100");
		Maquinas.setLayout(new BorderLayout(0, 0));

		JPanel MaquinasMenu = new JPanel();
		Maquinas.add(MaquinasMenu, BorderLayout.CENTER);

		JLabel lblMaquinas = new JLabel("Maquinas");
		lblMaquinas.setFont(new Font("Dialog", Font.BOLD, 29));

		btnRefreshMaquinas = new JButton("Refresh");
		btnRefreshMaquinas.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshMaquinas.setBackground(Color.LIGHT_GRAY);
		btnRefreshMaquinas.setIcon(new ImageIcon(userDesign.class.getResource("/refresh.png")));

		btnHomeMaquinas = new JButton("Home");
		btnHomeMaquinas.setVerticalAlignment(SwingConstants.TOP);
		btnHomeMaquinas.setHorizontalAlignment(SwingConstants.LEFT);
		btnHomeMaquinas.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnHomeMaquinas.setBackground(Color.LIGHT_GRAY);
		btnHomeMaquinas.setIcon(new ImageIcon(userDesign.class.getResource("/home.png")));

		JScrollPane scrollPane_2 = new JScrollPane();

		tglbtnFiltrarAvariadas = new JToggleButton("Filtrar Avariadas");
		
		tglbtnFiltrarAvariadas.setBackground(Color.LIGHT_GRAY);
		UIManager.put("ToggleButton.select", Color.GREEN);
		SwingUtilities.updateComponentTreeUI(tglbtnFiltrarAvariadas);
		
		btnReportarAvaria = new JButton("Reportar Avaria");
		btnReportarAvaria.setBackground(Color.LIGHT_GRAY);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBackground(Color.BLUE);

		btnCorrigirAvaria = new JButton("Corrigir Avaria");
		
		btnCorrigirAvaria.setMargin(new Insets(2, 2, 2, 2));
		btnCorrigirAvaria.setBackground(Color.LIGHT_GRAY);

		maquinaSearch = new JTextField();
		maquinaSearch.setText("Quick Access");
		maquinaSearch.setToolTipText("Quick Access");
		maquinaSearch.setColumns(10);
		GroupLayout gl_MaquinasMenu = new GroupLayout(MaquinasMenu);
		gl_MaquinasMenu.setHorizontalGroup(gl_MaquinasMenu
				.createParallelGroup(Alignment.LEADING).addGroup(gl_MaquinasMenu.createSequentialGroup().addGap(39)
						.addGroup(gl_MaquinasMenu.createParallelGroup(Alignment.LEADING).addComponent(lblMaquinas)
								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_MaquinasMenu.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnHomeMaquinas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRefreshMaquinas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(115)
						.addGroup(gl_MaquinasMenu.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_MaquinasMenu.createSequentialGroup().addComponent(tglbtnFiltrarAvariadas)
										.addGap(18).addComponent(btnReportarAvaria).addGap(18)
										.addComponent(btnCorrigirAvaria, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
										.addComponent(maquinaSearch, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
						.addGap(55)));
		gl_MaquinasMenu.setVerticalGroup(gl_MaquinasMenu.createParallelGroup(Alignment.LEADING).addGroup(gl_MaquinasMenu
				.createSequentialGroup()
				.addGroup(gl_MaquinasMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_MaquinasMenu.createSequentialGroup().addGap(49)
								.addComponent(lblMaquinas, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
								.addGap(50)
								.addComponent(btnRefreshMaquinas, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnHomeMaquinas, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_MaquinasMenu.createSequentialGroup().addGap(85)
								.addGroup(gl_MaquinasMenu.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_MaquinasMenu.createParallelGroup(Alignment.BASELINE)
												.addComponent(tglbtnFiltrarAvariadas).addComponent(btnReportarAvaria)
												.addComponent(btnCorrigirAvaria))
										.addComponent(maquinaSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
				.addContainerGap()));

		model_Maquinas = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "ID", "Avariado" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -96636141310423198L;
			Class[] columnTypes = new Class[] { String.class, String.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Maquinas = new JTable();
		table_Maquinas.setModel(model_Maquinas);
		table_Maquinas.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_Maquinas.getColumnModel().getColumn(0).setMaxWidth(150);

		scrollPane_2.setViewportView(table_Maquinas);
		MaquinasMenu.setLayout(gl_MaquinasMenu);

		table_Maquinas.getTableHeader().setReorderingAllowed(false);
		table_Maquinas.setAutoCreateRowSorter(true);// para ordenar
		table_Maquinas.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nomenumeroestadoMaquina("warehouse1", model_Maquinas);
		
		sorterMaquinas = new TableRowSorter<>(model_Maquinas);
		table_Maquinas.setRowSorter(sorterMaquinas);

		maquinaSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				maquinaSearch.setText("");
			}
		});
		maquinaSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(maquinaSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(maquinaSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(maquinaSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterMaquinas.setRowFilter(null);
				} else {
					sorterMaquinas.setRowFilter(RowFilter.regexFilter("(?i)" +str));
				}
			}
		});

	}

	private void showFuncionariosMenu() {
		Funcionarios = new JPanel();
		frmUserDesign.getContentPane().add(Funcionarios, "name_126627817283200");
		Funcionarios.setLayout(new BorderLayout(0, 0));

		JPanel FuncionariosMenu = new JPanel();
		Funcionarios.add(FuncionariosMenu, BorderLayout.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblFuncionarios = new JLabel("Funcionarios\r\n");
		lblFuncionarios.setFont(new Font("Dialog", Font.BOLD, 22));

		btnRefreshFuncionarios = new JButton("Refresh");
		btnRefreshFuncionarios.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshFuncionarios.setBackground(Color.LIGHT_GRAY);
		btnRefreshFuncionarios.setIcon(new ImageIcon(userDesign.class.getResource("/refresh.png")));

		btnHomeFuncionarios = new JButton("Home");
		btnHomeFuncionarios.setVerticalAlignment(SwingConstants.TOP);
		btnHomeFuncionarios.setHorizontalAlignment(SwingConstants.LEFT);
		btnHomeFuncionarios.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnHomeFuncionarios.setBackground(Color.LIGHT_GRAY);
		btnHomeFuncionarios.setIcon(new ImageIcon(userDesign.class.getResource("/home.png")));

		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.BLUE);

		funcionarioSearch = new JTextField();
		funcionarioSearch.setColumns(10);
		funcionarioSearch.setText("Quick Access");
		funcionarioSearch.setToolTipText("Quick Access");
		GroupLayout gl_FuncionariosMenu = new GroupLayout(FuncionariosMenu);
		gl_FuncionariosMenu.setHorizontalGroup(gl_FuncionariosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FuncionariosMenu.createSequentialGroup().addGap(33).addGroup(gl_FuncionariosMenu
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_FuncionariosMenu.createSequentialGroup().addGroup(gl_FuncionariosMenu
								.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_FuncionariosMenu.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnRefreshFuncionarios, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnHomeFuncionarios, GroupLayout.DEFAULT_SIZE, 109,
												Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE).addComponent(
										scrollPane_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_FuncionariosMenu.createSequentialGroup().addComponent(lblFuncionarios)
								.addPreferredGap(ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
								.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(38)));
		gl_FuncionariosMenu.setVerticalGroup(gl_FuncionariosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FuncionariosMenu.createSequentialGroup().addGap(41)
						.addComponent(lblFuncionarios, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(43)
						.addComponent(btnRefreshFuncionarios, GroupLayout.PREFERRED_SIZE, 33,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnHomeFuncionarios, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_FuncionariosMenu.createSequentialGroup().addContainerGap(73, Short.MAX_VALUE)
								.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
								.addGap(19)));
		modelFuncionarios = new DefaultTableModel(new Object[][] {},
				new String[] { "NIF","Nome", "Idade", "Fun\u00E7\u00E3o" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		tabela_Funcionarios = new JTable();
		tabela_Funcionarios.setModel(modelFuncionarios);
		tabela_Funcionarios.getColumnModel().getColumn(0).setMinWidth(80);
		tabela_Funcionarios.getColumnModel().getColumn(0).setMaxWidth(80);

		tabela_Funcionarios.getColumnModel().getColumn(1).setMinWidth(100);
		tabela_Funcionarios.getColumnModel().getColumn(1).setPreferredWidth(150);
		tabela_Funcionarios.getColumnModel().getColumn(2).setMinWidth(40);
		tabela_Funcionarios.getColumnModel().getColumn(2).setMaxWidth(50);
		tabela_Funcionarios.getColumnModel().getColumn(3).setMinWidth(150);



		scrollPane_1.setViewportView(tabela_Funcionarios);
		FuncionariosMenu.setLayout(gl_FuncionariosMenu);

		tabela_Funcionarios.getTableHeader().setReorderingAllowed(false);
		tabela_Funcionarios.setAutoCreateRowSorter(true);// para ordenar
		tabela_Funcionarios.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nifIdadeNomeFuncaoFuncionario(nomeArmazem, modelFuncionarios);

		sorterFuncionario = new TableRowSorter<>(modelFuncionarios);
		tabela_Funcionarios.setRowSorter(sorterFuncionario);

		funcionarioSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				funcionarioSearch.setText("");
			}
		});
		funcionarioSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(funcionarioSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(funcionarioSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(funcionarioSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterFuncionario.setRowFilter(null);
				} else {
					sorterFuncionario.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});

	}

	private void showProdutos() {
		Produtos = new JPanel();
		frmUserDesign.getContentPane().add(Produtos, "name_127783532138000");
		Produtos.setLayout(new BorderLayout(0, 0));

		JPanel ProdutosMenu = new JPanel();
		Produtos.add(ProdutosMenu, BorderLayout.CENTER);

		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Dialog", Font.BOLD, 30));

		JScrollPane scrollPane_3 = new JScrollPane();

		btnRefreshProdutos = new JButton("Refresh");
		btnRefreshProdutos.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshProdutos.setBackground(Color.LIGHT_GRAY);
		btnRefreshProdutos.setIcon(new ImageIcon(userDesign.class.getResource("/refresh.png")));

		btnHomeProdutos = new JButton("Home");
		btnHomeProdutos.setVerticalAlignment(SwingConstants.TOP);
		btnHomeProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		btnHomeProdutos.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnHomeProdutos.setBackground(Color.LIGHT_GRAY);
		btnHomeProdutos.setIcon(new ImageIcon(userDesign.class.getResource("/home.png")));

		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(Color.BLUE);

		btnMarcarDefeitoProdutos = new JButton("Marcar Defeito");
		
		btnMarcarDefeitoProdutos.setFont(new Font("Consolas", Font.PLAIN, 12));

		produtosSearch = new JTextField();
		produtosSearch.setText("Quick Access");
		produtosSearch.setToolTipText("Quick Access");
		produtosSearch.setColumns(10);
		GroupLayout gl_ProdutosMenu = new GroupLayout(ProdutosMenu);
		gl_ProdutosMenu.setHorizontalGroup(
			gl_ProdutosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ProdutosMenu.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_ProdutosMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ProdutosMenu.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_ProdutosMenu.createSequentialGroup()
								.addGroup(gl_ProdutosMenu.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnHomeProdutos, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
									.addComponent(btnRefreshProdutos, Alignment.LEADING))
								.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
								.addGroup(gl_ProdutosMenu.createParallelGroup(Alignment.LEADING, false)
									.addGroup(Alignment.TRAILING, gl_ProdutosMenu.createSequentialGroup()
										.addComponent(btnMarcarDefeitoProdutos)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(produtosSearch, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
									.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE))
								.addGap(38))
							.addGroup(gl_ProdutosMenu.createSequentialGroup()
								.addComponent(separator_7, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(677, Short.MAX_VALUE)))
						.addGroup(gl_ProdutosMenu.createSequentialGroup()
							.addComponent(lblProdutos)
							.addContainerGap(678, Short.MAX_VALUE))))
		);
		gl_ProdutosMenu.setVerticalGroup(
			gl_ProdutosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ProdutosMenu.createSequentialGroup()
					.addGap(41)
					.addComponent(lblProdutos, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ProdutosMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ProdutosMenu.createSequentialGroup()
							.addGroup(gl_ProdutosMenu.createParallelGroup(Alignment.BASELINE)
								.addComponent(produtosSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMarcarDefeitoProdutos))
							.addGap(6)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ProdutosMenu.createSequentialGroup()
							.addComponent(separator_7, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnRefreshProdutos, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnHomeProdutos, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(6, Short.MAX_VALUE))
		);

		modelProdutos = new DefaultTableModel(new Object[][] {}, new String[] { "SKU", "Produto", "Origem", "Com Defeito" }) {

			private static final long serialVersionUID = 60845133227382893L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Boolean.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Produtos = new JTable();

		table_Produtos.setModel(modelProdutos);
		table_Produtos.getColumnModel().getColumn(0).setMinWidth(120);
		table_Produtos.getColumnModel().getColumn(0).setMaxWidth(150);

		table_Produtos.getColumnModel().getColumn(1).setMinWidth(120);

		table_Produtos.getColumnModel().getColumn(2).setMinWidth(90);
		table_Produtos.getColumnModel().getColumn(3).setMinWidth(90);
		table_Produtos.getColumnModel().getColumn(3).setMaxWidth(90);


		table_Produtos.getTableHeader().setReorderingAllowed(false);
		table_Produtos.setAutoCreateRowSorter(true);// para ordenar
		table_Produtos.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.produtoNaoVendido(modelProdutos);
		scrollPane_3.setViewportView(table_Produtos);
		ProdutosMenu.setLayout(gl_ProdutosMenu);
		
		sorterProdutos = new TableRowSorter<>(modelProdutos);
		table_Produtos.setRowSorter(sorterProdutos);

		produtosSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				produtosSearch.setText("");
			}
		});
		produtosSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(produtosSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(produtosSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(produtosSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterProdutos.setRowFilter(null);
				} else {
					sorterProdutos.setRowFilter(RowFilter.regexFilter("(?i)" +str));
				}
			}
		});
		

	}

	private boolean first = true;
	private JTextField funcionarioSearch;
	private JTextField produtosSearch;
	private JTextField maquinaSearch;
	private JTextField vendasSearch;
	private JPanel Grafico;
	private JPanel GraficoMenu;
	private JPanel Grafico1;
	private JPanel Grafico2;
	private JPanel Grafico3;	
	private JPanel Grafico4;	
	private JPanel Grafico5;	
	private ChartPanel chartPanel1;	
	private ChartPanel chartPanel2;	
	private ChartPanel chartPanel3;	
	private ChartPanel chartPanel4;	
	private ChartPanel chartPanel5;
	
	private void putPanelsGrafico() {
		GraficoMenu.add(Grafico1, "grafico1");
		GraficoMenu.add(Grafico2, "grafico2");
		GraficoMenu.add(Grafico3, "grafico3");	
		GraficoMenu.add(Grafico4, "grafico4");	
		GraficoMenu.add(Grafico5, "grafico5");
		clGrafico.show(GraficoMenu, "grafico1");
	}
	
	private void showGraficos() {
		Grafico = new JPanel();
		frmUserDesign.getContentPane().add(Grafico, "name_1773234573107200");
		Grafico.setLayout(new BorderLayout(0, 0));
		
		GraficoMenu = new JPanel();
		Grafico.add(GraficoMenu, BorderLayout.CENTER);
		clGrafico = new CardLayout(0, 0);
		GraficoMenu.setLayout(clGrafico);
		
		Grafico1 = new JPanel();
		Grafico1.setLayout(new BorderLayout(0, 0));
		
		chartPanel1 = Chart.origemLotes();
		Grafico1.add(chartPanel1);
		
		Grafico2 = new JPanel();
		Grafico2.setLayout(new BorderLayout(0, 0));
		
		chartPanel2 = Chart.VendasSubCategoria();
		Grafico2.add(chartPanel2);
		
		Grafico3 = new JPanel();	
		Grafico3.setLayout(new BorderLayout(0, 0));	
			
		chartPanel3 = Chart.mediaSalariosIdade();	
		Grafico3.add(chartPanel3);	
			
		Grafico4 = new JPanel();	
		Grafico4.setLayout(new BorderLayout(0, 0));	
			
		chartPanel4 = Chart.VendasPorDia();	
		Grafico4.add(chartPanel4);	
			
		Grafico5 = new JPanel();	
		Grafico5.setLayout(new BorderLayout(0, 0));	
			
		chartPanel5 = Chart.AtividadeUser();	
		Grafico5.add(chartPanel5);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		Grafico.add(panel, BorderLayout.NORTH);
		
		selectGraph = new JComboBox();
		
		selectGraph.setModel(new DefaultComboBoxModel(new String[] {"Origem Lotes", "Vendas Sub-Categoria", "Média Salários Idade", "Vendas por Dia", "Atividade diária Utilizadores"}));		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(700, Short.MAX_VALUE)
					.addComponent(selectGraph, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(selectGraph, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
	}
	
	/* TEMPO COMO BACKGROUND */
	private void backgroundTimer() {
		counterTimer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (db.connect()) {
					lblStatusDb.setText("ON");
					lblStatusDb.setForeground(Color.GREEN);
					db.disconnect();
					first=true;
				} else {
					lblStatusDb.setText("OFF");
					lblStatusDb.setForeground(Color.RED);
					if (first) {
						int option = popUp.showPopUpDataBaseError2();

						if (option == JOptionPane.NO_OPTION) {
							cl.show(frmUserDesign.getContentPane(), "base_dados");
						}
						first = false;
					}
				}
			}

		});
	}

	/* BARRA MENU */
	private void showMenuBar() {
		JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setBackground(Color.WHITE);
		frmUserDesign.setJMenuBar(menuBar1);

		JMenu mnFile1 = new JMenu("File");
		mnFile1.setBackground(Color.LIGHT_GRAY);
		menuBar1.add(mnFile1);

		mntmMenu = new JMenuItem("Menu");
		mntmMenu.setIcon(new ImageIcon(AdminDesign.class.getResource(HOMEMIN)));
		mnFile1.add(mntmMenu);

		mntmExit1 = new JMenuItem("Exit");
		mntmExit1.setIcon(new ImageIcon(AdminDesign.class.getResource(LOGOUT)));
		mnFile1.add(mntmExit1);

		JSeparator separator = new JSeparator();
		separator.setMaximumSize(new Dimension(100, 50));
		menuBar1.add(separator);

		mnNewMenu = new JMenu("Help");
		menuBar1.add(mnNewMenu);

		menuWebsite = new JMenuItem("Website");

		menuWebsite.setIcon(new ImageIcon(AdminDesign.class.getResource(WEBSITE)));
		mnNewMenu.add(menuWebsite);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);

		menuHelpMe = new JMenuItem("Help Me");
		menuHelpMe.setIcon(new ImageIcon(AdminDesign.class.getResource(HELP)));
		mnNewMenu.add(menuHelpMe);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		menuBar1.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JLabel lblBaseDeDados = new JLabel("Base de Dados:");
		panel.add(lblBaseDeDados);

		lblStatusDb = new JLabel("ON");
		lblStatusDb.setForeground(Color.GREEN);
		lblStatusDb.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblStatusDb);

		JLabel lblDg = new JLabel("                                                                               ");
		panel.add(lblDg);

		lblArmazem = new JLabel(nomeArmazem);
		lblArmazem.setForeground(Color.BLACK);
		lblArmazem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		lblArmazem.setBackground(Color.BLACK);
		panel.add(lblArmazem);
		lblArmazem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblArmazem.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel label = new JLabel("       ");
		menuBar1.add(label);
		label.setBackground(Color.WHITE);

		backgroundTimer();

		counterTimer.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void botoes() {
		/****************************************
		 * menu em cima *
		 ****************************************/
		mntmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "userDesign");
			}
		});
		mntmExit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counterTimer.stop();
		    	messageLogs.saiuSistema(username, false, nomeArmazem);
				LoginDesign window2 = new LoginDesign();
				window2.getFrmLogin().setVisible(true);
				frmUserDesign.dispose();

			}
		});

		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Vendas");
			}
		});

		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Funcionarios");
			}
		});

		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Produtos");
			}
		});

		btnMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Maquinas");
			}
		});

		// Menu Vendas
		btnHomeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "userDesign");
			}
		});

		// Menu Maquinas

		btnHomeMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "userDesign");
			}
		});
		tglbtnFiltrarAvariadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.refreshMaquinas(model_Maquinas, tglbtnFiltrarAvariadas.isSelected(), nomeArmazem);
				
			
			

		    }
		});
		btnCorrigirAvaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maquinaClass.corrigeAvaria(table_Maquinas, model_Maquinas, nomeArmazem);
			}
		});
		// Menu funcionarios

		btnHomeFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "userDesign");
			}
		});
		tabela_Funcionarios.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				int index= table.convertRowIndexToModel(table.getSelectedRow());
				if (mouseEvent.getClickCount() == 2 &&  index!= -1) {
					funcionarioClasse.showAllInfoFuncionar( modelFuncionarios.getValueAt(index, 0).toString());
				}
			}
		});
		// Menu Produtos
		btnMarcarDefeitoProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				produtoClass.marcarDefeito(table_Produtos, modelProdutos, nomeArmazem);
			}
		});
		btnHomeProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "userDesign");
			}
		});
		
		btnRefreshProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtoClass.refreshProdutos(modelProdutos);
			}
		});

		// MENU GERAR RELATORIO 
		btnGerarRelatorioStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarCsvClasse.showPopUpCriarCSV();
			}
		});
		
		// MENU GRAFICO
		btnGraficos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl.show(frmUserDesign.getContentPane(), "Grafico");
			}
		});
		selectGraph.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(selectGraph.getSelectedItem().toString().equals("Origem Lotes")) {	
					clGrafico.show(GraficoMenu, "grafico1");	
				}else if (selectGraph.getSelectedItem().toString().equals("Vendas Sub-Categoria")) {	
					clGrafico.show(GraficoMenu, "grafico2");	
						
				}else if (selectGraph.getSelectedItem().toString().equals("Média Salários Idade")){	
					clGrafico.show(GraficoMenu, "grafico3");	
						
				}else if (selectGraph.getSelectedItem().toString().equals("Vendas por Dia")){	
					clGrafico.show(GraficoMenu, "grafico4");	
						
				}else {	
					clGrafico.show(GraficoMenu, "grafico5");	
						
				}	
			}	
		});
		
		// Menu Pop UPS
		btnRececaoProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receberprodutoClass.receberProdutoFunc(nomeArmazem) ;

			}
		});
		btnEnviarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarprodutoClass.enviar(nomeArmazem);
			}
		});
		btnReportarAvaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.reportAvariaMaquina(table_Maquinas, model_Maquinas, tglbtnFiltrarAvariadas.isSelected(), nomeArmazem);
			}
		});
		btnRefreshMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.refreshMaquinas(model_Maquinas, tglbtnFiltrarAvariadas.isSelected(),nomeArmazem);
			}
		});
		btnRefreshFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClasse.refreshFuncionarios(modelFuncionarios, nomeArmazem);
			}
		});
		
		btnRefreshVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendasClass.refreshVendas(modelVendas);
			}
		});

		
		
	}

	private void initialize() {
		frmUserDesign = new JFrame();
		frmUserDesign.setResizable(false);
		frmUserDesign.setTitle("Menu User");
		frmUserDesign.setBounds(100, 100, 855, 416);
		frmUserDesign.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				  int confirmed = JOptionPane.showConfirmDialog(null, 
					        "Tem a certeza que deseja sair do programa?", "Exit",
					        JOptionPane.YES_NO_OPTION);

					    if (confirmed == JOptionPane.YES_OPTION) {
					    	messageLogs.saiuSistema(username, false, nomeArmazem);
					    	frmUserDesign.dispose();
					    }
			}
		});
		frmUserDesign.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frmUserDesign.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frmUserDesign.getHeight()) / 2);
		frmUserDesign.setLocation(x, y);
		URL iconURL = getClass().getResource("/logo.png");
		ImageIcon img = new ImageIcon(iconURL);
		frmUserDesign.setIconImage(img.getImage());
		cl = new CardLayout(0, 0);
		frmUserDesign.getContentPane().setLayout(cl);
		showMainMenu();
		showVendasMenu();
		showMaquinasMenu();
		showFuncionariosMenu();
		showProdutos();
		showGraficos();
		putPanels();
		putPanelsGrafico();
		// Menu Principal

		/***** MENU BAR *****/
		showMenuBar();
		/***********************/
		botoes();
	}

	public JFrame getFrmUserDesign() {
		return frmUserDesign;
	}
}
