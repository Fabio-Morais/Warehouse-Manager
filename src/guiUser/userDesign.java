package guiuser;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartPanel;
import db.DataBase;
import gui.Interface;
import gui.MenuBar;
import guiuser.vendas.Vendas;
import logic.MessageLogs;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class userDesign {

	private String nomeArmazem;
	private String username;

	private JFrame frmUserDesign;
	// Imagens

	private static final String RECEBER = "/receber.png";
	private static final String MAQUINA = "/machineMin.png";
	private static final String ENVIAR = "/truckMin.png";
	private static final String FUNCIONARIO = "/avatar.png";
	private static final String RELATORIO = "/report.png";
	private static final String VENDAS = "/vendas.png";
	private static final String PRODUTO = "/box.png";
	private static final String GRAFICO = "/grafico.png";
	private static final String REFRESH = "/refresh.png";

	// Classes
	private Maquinas maquinaClass;
	private Funcionario funcionarioClasse;
	private Produtos produtoClass;
	private Vendas vendasClass;
	private EnviarProduto enviarprodutoClass;
	private ReceberProduto receberprodutoClass;
	private CriarCsv criarCsvClasse;
	private Graficos chart;
	// Panels
	private JPanel userDesign;
	private JPanel maquinas;
	private JPanel funcionarios;
	private JPanel produtos;

	private DataBase db;

	// Botoes
	// Main Menu
	private JPanel mainMenuHome;
	private JLabel lblRececao;
	private JLabel lblEnviar;
	private JLabel lblRelatorios;
	private JEditorPane dtrpnUser;
	private JLabel lblArmazem;
	private JSeparator separator;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSeparator separator3;
	private JButton btnVendas;
	private JButton btnProdutos;
	private JButton btnFuncionarios;
	private JButton btnMaquinas;
	private JButton btnRececaoProdutos;
	private JButton btnEnviarProduto;
	private JButton btnGerarRelatorioStock;

	// Menu Vendas


	// Menu Maquinas
	private JSeparator separator_6;
	private JLabel lblMaquinas;
	private JScrollPane scrollPane_2;
	private JPanel maquinasMenu;
	private JPanel funcionariosMenu;
	private JScrollPane scrollPaneFuncionario;
	private JLabel lblFuncionarios;
	private JSeparator separatorFuncionario;
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
	private JPanel produtosMenu;
	private JLabel lblProdutos;
	private JScrollPane scrollPaneProdutos;
	private JSeparator separatorProdutos;
	private JButton btnRefreshProdutos;
	private JButton btnHomeProdutos;
	private DefaultTableModel modelProdutos;
	private JTable tableProdutos;
	private TableRowSorter<DefaultTableModel> sorterProdutos;
	private JButton btnMarcarDefeitoProdutos;

	// menu grafico
	private JButton btnGraficos;
	private JComboBox<String> selectGraph;

	/**
	 * Launch the application.
	 */
	private CardLayout cl;
	private CardLayout clGrafico;

	private MessageLogs messageLogs;

	private JTextField funcionarioSearch;
	private JTextField produtosSearch;
	private JTextField maquinaSearch;
	private JPanel grafico;
	private JPanel graficoMenu;
	private JPanel grafico1;
	private JPanel grafico2;
	private JPanel grafico3;
	private JPanel grafico4;
	private JPanel grafico5;

	/**
	 * Adiciona os panels a janela principal
	 */
	private void putPanels() {
		frmUserDesign.getContentPane().add(userDesign, "userDesign");
		frmUserDesign.getContentPane().add(vendasClass.getVendas(), "Vendas");
		frmUserDesign.getContentPane().add(maquinas, "Maquinas");
		frmUserDesign.getContentPane().add(funcionarios, "Funcionarios");
		frmUserDesign.getContentPane().add(produtos, "Produtos");
		frmUserDesign.getContentPane().add(grafico, "Grafico");
		cl.show(frmUserDesign.getContentPane(), "userDesign");// mostrar o main menu
	}

	/**
	 * Create the application.
	 */
	public userDesign(String armazem, String username) {
		this.nomeArmazem = armazem;
		this.maquinaClass = new Maquinas(username);
		this.funcionarioClasse = new Funcionario();
		this.produtoClass = new Produtos(username);
		this.enviarprodutoClass = new EnviarProduto(username);
		this.receberprodutoClass = new ReceberProduto(username);
		this.username = username;
		this.criarCsvClasse = new CriarCsv();
		this.chart = new Graficos();
		db = DataBase.getInstance();
		this.messageLogs = MessageLogs.getInstance();
		initialize();
		MenuBar menuBar = new MenuBar(frmUserDesign, cl, username);
		menuBar.setNomeArmazem(nomeArmazem);
		menuBar.showMenuBar(1);
	}

	private void criaTituloMenu() {
		lblArmazem = new JLabel("Armazem");
		Interface.styleLabelMenu(lblArmazem);

		lblRececao = new JLabel("Rece\u00E7\u00E3o");
		Interface.styleLabelMenu(lblRececao);

		lblEnviar = new JLabel("Enviar");
		Interface.styleLabelMenu(lblEnviar);

		lblRelatorios = new JLabel("Relatorios");
		Interface.styleLabelMenu(lblRelatorios);

		dtrpnUser = new JEditorPane();
		Interface.styleTituloMenu(dtrpnUser, "User");

		separator = new JSeparator();
		Interface.styleSeparator(separator);

		separator1 = new JSeparator();
		Interface.styleSeparator(separator1);

		separator2 = new JSeparator();
		Interface.styleSeparator(separator2);

		separator3 = new JSeparator();
		Interface.styleSeparator(separator3);
	}
	private void criaBotoesMenu() {
		btnProdutos = new JButton("<html>Produtos<html>");
		Interface.styleButton(btnProdutos, PRODUTO, new Insets(2, 14, 10, 14));
		btnFuncionarios = new JButton("Funcionarios");
		Interface.styleButton(btnFuncionarios, FUNCIONARIO, new Insets(2, 0, 10, 0));
		btnMaquinas = new JButton("Maquinas");
		Interface.styleButton(btnMaquinas, MAQUINA, new Insets(2, 14, 10, 14));
		btnVendas = new JButton("<html>Vendas<html>");
		Interface.styleButton(btnVendas, VENDAS, new Insets(2, 14, 10, 14));
		btnRececaoProdutos = new JButton("Receção lote");
		Interface.styleButton(btnRececaoProdutos, RECEBER, new Insets(10, 2, 10, 2));
		btnEnviarProduto = new JButton("<html>Enviar<br>Produto<html>");
		Interface.styleButton(btnEnviarProduto, ENVIAR, new Insets(5, 20, 5, 20));
		btnGerarRelatorioStock = new JButton("<html>Gerar<br>Relatorio<br><html>");
		Interface.styleButton(btnGerarRelatorioStock, RELATORIO, new Insets(5, 20, 5, 20));
		btnGraficos = new JButton("Graficos");
		Interface.styleButton(btnGraficos, GRAFICO, new Insets(2, 14, 10, 14));

	}
	private GroupLayout putMenuLayout() {
		GroupLayout glMainMenuHome = new GroupLayout(mainMenuHome);
		glMainMenuHome.setHorizontalGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING).addGroup(glMainMenuHome
				.createSequentialGroup()
				.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING).addGroup(glMainMenuHome
						.createSequentialGroup().addGap(83)
						.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING).addGroup(glMainMenuHome
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(glMainMenuHome.createSequentialGroup()
										.addComponent(btnProdutos, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnFuncionarios, GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING, false)
										.addGroup(glMainMenuHome.createSequentialGroup()
												.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(btnMaquinas, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(separator, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblArmazem, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGap(51)
						.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRececao, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRececaoProdutos, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE))
						.addGap(39)
						.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEnviarProduto, GroupLayout.PREFERRED_SIZE, 100,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnviar, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGap(57)
						.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGraficos, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblRelatorios)
										.addComponent(separator2, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addComponent(btnGerarRelatorioStock, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(glMainMenuHome.createSequentialGroup().addContainerGap().addComponent(dtrpnUser,
								GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(103, Short.MAX_VALUE)));
		glMainMenuHome.setVerticalGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING).addGroup(glMainMenuHome
				.createSequentialGroup()
				.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING)
						.addGroup(glMainMenuHome.createSequentialGroup().addContainerGap().addComponent(dtrpnUser,
								GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(glMainMenuHome.createSequentialGroup().addGap(72)
								.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING)
										.addGroup(glMainMenuHome.createSequentialGroup()
												.addComponent(lblRelatorios, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 15,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnGerarRelatorioStock, GroupLayout.PREFERRED_SIZE, 75,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(btnGraficos, GroupLayout.PREFERRED_SIZE, 75,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(glMainMenuHome.createSequentialGroup().addGroup(glMainMenuHome
												.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblRececao, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblArmazem)).addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(glMainMenuHome.createParallelGroup(Alignment.LEADING)
														.addGroup(glMainMenuHome.createSequentialGroup()
																.addComponent(separator, GroupLayout.PREFERRED_SIZE, 22,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(glMainMenuHome
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(btnVendas,
																				GroupLayout.PREFERRED_SIZE, 75,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(btnMaquinas,
																				GroupLayout.PREFERRED_SIZE, 75,
																				GroupLayout.PREFERRED_SIZE))
																.addGap(21)
																.addGroup(glMainMenuHome
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(btnFuncionarios,
																				GroupLayout.PREFERRED_SIZE, 75,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(btnProdutos,
																				GroupLayout.PREFERRED_SIZE, 75,
																				GroupLayout.PREFERRED_SIZE)))
														.addGroup(glMainMenuHome.createSequentialGroup()
																.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 8,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(19).addComponent(btnRececaoProdutos,
																		GroupLayout.PREFERRED_SIZE, 75,
																		GroupLayout.PREFERRED_SIZE))))
										.addGroup(glMainMenuHome.createSequentialGroup()
												.addComponent(lblEnviar, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 15,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnEnviarProduto, GroupLayout.PREFERRED_SIZE, 75,
														GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(39, Short.MAX_VALUE)));
		return glMainMenuHome;
	}
	private void showMainMenu() {

		userDesign = new JPanel();
		frmUserDesign.getContentPane().add(userDesign, "name_144798506529000");
		userDesign.setLayout(new BorderLayout(0, 0));

		mainMenuHome = new JPanel();
		userDesign.add(mainMenuHome, BorderLayout.NORTH);
		criaTituloMenu();
		criaBotoesMenu();
		GroupLayout glMainMenuHome = putMenuLayout();
		mainMenuHome.setLayout(glMainMenuHome);
	}

	/**
	 * Menu Vendas
	 */

	/**
	 * Menu Maquinas
	 */
	private void criaMaquinasSearch() {
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
					sorterMaquinas.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});

	}
	private void criaBotoesMaquinas() {
		btnRefreshMaquinas = new JButton("Refresh");
		Interface.styleBotaoSimples(btnRefreshMaquinas, REFRESH);

		btnHomeMaquinas = new JButton("Home");
		Interface.styleBotaoHome(btnHomeMaquinas);

		btnCorrigirAvaria = new JButton("Corrigir Avaria");
		btnCorrigirAvaria.setMargin(new Insets(2, 2, 2, 2));
		btnCorrigirAvaria.setBackground(Color.LIGHT_GRAY);

		btnReportarAvaria = new JButton("Reportar Avaria");
		btnReportarAvaria.setBackground(Color.LIGHT_GRAY);

	}
	private GroupLayout putMaquinasLayout() {
		GroupLayout glMaquinasMenu = new GroupLayout(maquinasMenu);
		glMaquinasMenu.setHorizontalGroup(glMaquinasMenu
				.createParallelGroup(Alignment.LEADING).addGroup(glMaquinasMenu.createSequentialGroup().addGap(39)
						.addGroup(glMaquinasMenu.createParallelGroup(Alignment.LEADING).addComponent(lblMaquinas)
								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addGroup(glMaquinasMenu.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnHomeMaquinas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnRefreshMaquinas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(115)
						.addGroup(glMaquinasMenu.createParallelGroup(Alignment.LEADING)
								.addGroup(glMaquinasMenu.createSequentialGroup().addComponent(tglbtnFiltrarAvariadas)
										.addGap(18).addComponent(btnReportarAvaria).addGap(18)
										.addComponent(btnCorrigirAvaria, GroupLayout.PREFERRED_SIZE, 109,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
										.addComponent(maquinaSearch, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
						.addGap(55)));
		glMaquinasMenu.setVerticalGroup(glMaquinasMenu.createParallelGroup(Alignment.LEADING).addGroup(glMaquinasMenu
				.createSequentialGroup()
				.addGroup(glMaquinasMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(glMaquinasMenu.createSequentialGroup().addGap(49)
								.addComponent(lblMaquinas, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator_6, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
								.addGap(50)
								.addComponent(btnRefreshMaquinas, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnHomeMaquinas, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(glMaquinasMenu.createSequentialGroup().addGap(85)
								.addGroup(glMaquinasMenu.createParallelGroup(Alignment.TRAILING)
										.addGroup(glMaquinasMenu.createParallelGroup(Alignment.BASELINE)
												.addComponent(tglbtnFiltrarAvariadas).addComponent(btnReportarAvaria)
												.addComponent(btnCorrigirAvaria))
										.addComponent(maquinaSearch, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
				.addContainerGap()));

		return glMaquinasMenu;
	}
	private void showMaquinasMenu() {
		maquinas = new JPanel();
		frmUserDesign.getContentPane().add(maquinas, "name_111064051438100");
		maquinas.setLayout(new BorderLayout(0, 0));

		maquinasMenu = new JPanel();
		maquinas.add(maquinasMenu, BorderLayout.CENTER);

		lblMaquinas = new JLabel("Maquinas");
		lblMaquinas.setFont(new Font("Dialog", Font.BOLD, 29));

		
		scrollPane_2 = new JScrollPane();

		tglbtnFiltrarAvariadas = new JToggleButton("Filtrar Avariadas");

		tglbtnFiltrarAvariadas.setBackground(Color.LIGHT_GRAY);
		UIManager.put("ToggleButton.select", Color.GREEN);
		SwingUtilities.updateComponentTreeUI(tglbtnFiltrarAvariadas);

		separator_6 = new JSeparator();
		separator_6.setBackground(Color.BLUE);

		
		maquinaSearch = new JTextField();
		Interface.styleSearch(maquinaSearch);
		
		criaBotoesMaquinas();
		
		GroupLayout glMaquinasMenu = putMaquinasLayout();
		
		model_Maquinas = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "ID", "Avariado" }) {
		
			private static final long serialVersionUID = -96636141310423198L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, Boolean.class };

			@Override
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Maquinas = new JTable();
		table_Maquinas.setModel(model_Maquinas);
		table_Maquinas.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_Maquinas.getColumnModel().getColumn(0).setMaxWidth(150);

		scrollPane_2.setViewportView(table_Maquinas);
		maquinasMenu.setLayout(glMaquinasMenu);

		table_Maquinas.getTableHeader().setReorderingAllowed(false);
		table_Maquinas.setAutoCreateRowSorter(true);// para ordenar
		table_Maquinas.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nomenumeroestadoMaquina("warehouse1", model_Maquinas);

		criaMaquinasSearch();
	}

	private void criaFuncionariosSearch() {
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
	private void criaBotoesFuncionarios() {
		btnRefreshFuncionarios = new JButton("Refresh");
		Interface.styleBotaoSimples(btnRefreshFuncionarios, REFRESH);

		btnHomeFuncionarios = new JButton("Home");
		Interface.styleBotaoHome(btnHomeFuncionarios);
	}
	private GroupLayout putFuncionariosLayout() {
		GroupLayout glFuncionariosMenu = new GroupLayout(funcionariosMenu);
		glFuncionariosMenu.setHorizontalGroup(glFuncionariosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(glFuncionariosMenu.createSequentialGroup().addGap(33).addGroup(glFuncionariosMenu
						.createParallelGroup(Alignment.LEADING)
						.addGroup(glFuncionariosMenu.createSequentialGroup().addGroup(glFuncionariosMenu
								.createParallelGroup(Alignment.LEADING)
								.addComponent(separatorFuncionario, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addGroup(glFuncionariosMenu.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnRefreshFuncionarios, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnHomeFuncionarios, GroupLayout.DEFAULT_SIZE, 109,
												Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE).addComponent(
										scrollPaneFuncionario, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE))
						.addGroup(glFuncionariosMenu.createSequentialGroup().addComponent(lblFuncionarios)
								.addPreferredGap(ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
								.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addGap(38)));
		glFuncionariosMenu.setVerticalGroup(glFuncionariosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(glFuncionariosMenu.createSequentialGroup().addGap(41)
						.addComponent(lblFuncionarios, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(separatorFuncionario, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(43)
						.addComponent(btnRefreshFuncionarios, GroupLayout.PREFERRED_SIZE, 33,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnHomeFuncionarios, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						glFuncionariosMenu.createSequentialGroup().addContainerGap(73, Short.MAX_VALUE)
								.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPaneFuncionario, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
								.addGap(19)));
		return glFuncionariosMenu;
	}
	private void showFuncionariosMenu() {
		funcionarios = new JPanel();
		frmUserDesign.getContentPane().add(funcionarios, "name_126627817283200");
		funcionarios.setLayout(new BorderLayout(0, 0));

		funcionariosMenu = new JPanel();
		funcionarios.add(funcionariosMenu, BorderLayout.CENTER);

		scrollPaneFuncionario = new JScrollPane();

		lblFuncionarios = new JLabel("Funcionarios\r\n");
		lblFuncionarios.setFont(new Font("Dialog", Font.BOLD, 22));

		criaBotoesFuncionarios();

		separatorFuncionario = new JSeparator();
		separatorFuncionario.setForeground(Color.BLUE);

		funcionarioSearch = new JTextField();
		Interface.styleSearch(funcionarioSearch);
		
		GroupLayout glFuncionariosMenu = putFuncionariosLayout();
		
		modelFuncionarios = new DefaultTableModel(new Object[][] {},
				new String[] { "NIF", "Nome", "Idade", "Fun\u00E7\u00E3o" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			@Override
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

		scrollPaneFuncionario.setViewportView(tabela_Funcionarios);
		funcionariosMenu.setLayout(glFuncionariosMenu);

		tabela_Funcionarios.getTableHeader().setReorderingAllowed(false);
		tabela_Funcionarios.setAutoCreateRowSorter(true);// para ordenar
		tabela_Funcionarios.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nifIdadeNomeFuncaoFuncionario(nomeArmazem, modelFuncionarios);

		criaFuncionariosSearch();

	}

	private void criaProdutosSearch() {

		sorterProdutos = new TableRowSorter<>(modelProdutos);
		tableProdutos.setRowSorter(sorterProdutos);

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
					sorterProdutos.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});

	}
	private void criaBotoesProdutos() {
		btnRefreshProdutos = new JButton("Refresh");
		Interface.styleBotaoSimples(btnRefreshProdutos, REFRESH);

		btnHomeProdutos = new JButton("Home");
		Interface.styleBotaoHome(btnHomeProdutos);
		
		btnMarcarDefeitoProdutos = new JButton("Marcar Defeito");
		btnMarcarDefeitoProdutos.setFont(new Font("Consolas", Font.PLAIN, 12));
	}
	private GroupLayout putProdutosLayout() {
		GroupLayout glProdutosMenu = new GroupLayout(produtosMenu);
		glProdutosMenu.setHorizontalGroup(glProdutosMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(glProdutosMenu.createSequentialGroup().addGap(40).addGroup(glProdutosMenu
						.createParallelGroup(Alignment.LEADING)
						.addGroup(glProdutosMenu.createParallelGroup(Alignment.LEADING).addGroup(glProdutosMenu
								.createSequentialGroup()
								.addGroup(glProdutosMenu.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnHomeProdutos, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
										.addComponent(btnRefreshProdutos, Alignment.LEADING))
								.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
								.addGroup(glProdutosMenu.createParallelGroup(Alignment.LEADING, false).addGroup(
										Alignment.TRAILING,
										glProdutosMenu.createSequentialGroup().addComponent(btnMarcarDefeitoProdutos)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(produtosSearch, GroupLayout.PREFERRED_SIZE, 120,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(scrollPaneProdutos, GroupLayout.PREFERRED_SIZE, 572,
												GroupLayout.PREFERRED_SIZE))
								.addGap(38))
								.addGroup(glProdutosMenu.createSequentialGroup()
										.addComponent(separatorProdutos, GroupLayout.PREFERRED_SIZE, 132,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(677, Short.MAX_VALUE)))
						.addGroup(glProdutosMenu.createSequentialGroup().addComponent(lblProdutos).addContainerGap(678,
								Short.MAX_VALUE)))));
		glProdutosMenu.setVerticalGroup(glProdutosMenu.createParallelGroup(Alignment.LEADING).addGroup(glProdutosMenu
				.createSequentialGroup().addGap(41)
				.addComponent(lblProdutos, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(glProdutosMenu.createParallelGroup(Alignment.LEADING).addGroup(glProdutosMenu
						.createSequentialGroup()
						.addGroup(glProdutosMenu.createParallelGroup(Alignment.BASELINE)
								.addComponent(produtosSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMarcarDefeitoProdutos))
						.addGap(6)
						.addComponent(scrollPaneProdutos, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(glProdutosMenu.createSequentialGroup()
								.addComponent(separatorProdutos, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
								.addGap(40)
								.addComponent(btnRefreshProdutos, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnHomeProdutos, GroupLayout.PREFERRED_SIZE, 41,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(6, Short.MAX_VALUE)));
		return glProdutosMenu;
	}
	private void showProdutos() {
		produtos = new JPanel();
		frmUserDesign.getContentPane().add(produtos, "name_127783532138000");
		produtos.setLayout(new BorderLayout(0, 0));

		produtosMenu = new JPanel();
		produtos.add(produtosMenu, BorderLayout.CENTER);

		lblProdutos = new JLabel("Produtos");
		lblProdutos.setFont(new Font("Dialog", Font.BOLD, 30));

		scrollPaneProdutos = new JScrollPane();

		separatorProdutos = new JSeparator();
		separatorProdutos.setBackground(Color.BLUE);
		produtosSearch = new JTextField();
		Interface.styleSearch(produtosSearch);
		criaBotoesProdutos();
		GroupLayout glProdutosMenu =putProdutosLayout();

		modelProdutos = new DefaultTableModel(new Object[][] {},
				new String[] { "SKU", "Produto", "Origem", "Com Defeito" }) {

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

		tableProdutos = new JTable();

		tableProdutos.setModel(modelProdutos);
		tableProdutos.getColumnModel().getColumn(0).setMinWidth(120);
		tableProdutos.getColumnModel().getColumn(0).setMaxWidth(150);

		tableProdutos.getColumnModel().getColumn(1).setMinWidth(120);

		tableProdutos.getColumnModel().getColumn(2).setMinWidth(90);
		tableProdutos.getColumnModel().getColumn(3).setMinWidth(90);
		tableProdutos.getColumnModel().getColumn(3).setMaxWidth(90);

		tableProdutos.getTableHeader().setReorderingAllowed(false);
		tableProdutos.setAutoCreateRowSorter(true);// para ordenar
		tableProdutos.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPaneProdutos.setViewportView(tableProdutos);
		produtosMenu.setLayout(glProdutosMenu);
		
		db.produtoNaoVendido(modelProdutos);
		criaProdutosSearch();
	}

	private void putPanelsGrafico() {
		graficoMenu.add(grafico1, "grafico1");
		graficoMenu.add(grafico2, "grafico2");
		graficoMenu.add(grafico3, "grafico3");
		graficoMenu.add(grafico4, "grafico4");
		graficoMenu.add(grafico5, "grafico5");
		clGrafico.show(graficoMenu, "grafico1");
	}

	private void showGraficos() {
		grafico = new JPanel();
		ChartPanel chartPanel1;
		ChartPanel chartPanel2;
		ChartPanel chartPanel3;
		ChartPanel chartPanel4;
		ChartPanel chartPanel5;
		frmUserDesign.getContentPane().add(grafico, "name_1773234573107200");
		grafico.setLayout(new BorderLayout(0, 0));

		graficoMenu = new JPanel();
		grafico.add(graficoMenu, BorderLayout.CENTER);
		clGrafico = new CardLayout(0, 0);
		graficoMenu.setLayout(clGrafico);

		grafico1 = new JPanel();
		grafico1.setLayout(new BorderLayout(0, 0));

		chartPanel1 = chart.origemLotes();
		grafico1.add(chartPanel1);

		grafico2 = new JPanel();
		grafico2.setLayout(new BorderLayout(0, 0));

		chartPanel2 = chart.VendasSubCategoria();
		grafico2.add(chartPanel2);

		grafico3 = new JPanel();
		grafico3.setLayout(new BorderLayout(0, 0));

		chartPanel3 = chart.mediaSalariosIdade();
		grafico3.add(chartPanel3);

		grafico4 = new JPanel();
		grafico4.setLayout(new BorderLayout(0, 0));

		chartPanel4 = chart.VendasPorDia();
		grafico4.add(chartPanel4);

		grafico5 = new JPanel();
		grafico5.setLayout(new BorderLayout(0, 0));

		chartPanel5 = chart.AtividadeUser();
		grafico5.add(chartPanel5);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		grafico.add(panel, BorderLayout.NORTH);

		selectGraph = new JComboBox<>();
		DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>(new String[] { "Origem Lotes",
				"Vendas Sub-Categoria", "Média Salários Idade", "Vendas por Dia", "Atividade diária Utilizadores" });
		selectGraph.setModel(model3);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addContainerGap(700, Short.MAX_VALUE)
						.addComponent(selectGraph, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(selectGraph,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE));
		panel.setLayout(gl_panel);
	}

	
	private void botoesFuncionarios() {
		btnFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Funcionarios");
			}
		});

		btnHomeFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "userDesign");
			}
		});
		tabela_Funcionarios.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				int index = table.convertRowIndexToModel(table.getSelectedRow());
				if (mouseEvent.getClickCount() == 2 && index != -1) {
					funcionarioClasse.showAllInfoFuncionar(modelFuncionarios.getValueAt(index, 0).toString());
				}
			}
		});
		
		btnRefreshFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClasse.refreshFuncionarios(modelFuncionarios, nomeArmazem);
			}
		});

	}

	private void botoesProdutos() {

		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Produtos");
			}
		});

		btnMarcarDefeitoProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				produtoClass.marcarDefeito(tableProdutos, modelProdutos, nomeArmazem);
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

	}

	private void botoesMaquinas() {
		btnMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Maquinas");
			}
		});

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
		
		btnReportarAvaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.reportAvariaMaquina(table_Maquinas, model_Maquinas, tglbtnFiltrarAvariadas.isSelected(),
						nomeArmazem);
			}
		});
		btnRefreshMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.refreshMaquinas(model_Maquinas, tglbtnFiltrarAvariadas.isSelected(), nomeArmazem);
			}
		});
	}

	private void botoesRelatorio() {
		btnGerarRelatorioStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarCsvClasse.showPopUpCriarCSV();
			}
		});
	}
	
	private void botoesGrafico() {
		btnGraficos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl.show(frmUserDesign.getContentPane(), "Grafico");
			}
		});
		selectGraph.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (selectGraph.getSelectedItem().toString().equals("Origem Lotes")) {
					clGrafico.show(graficoMenu, "grafico1");
				} else if (selectGraph.getSelectedItem().toString().equals("Vendas Sub-Categoria")) {
					clGrafico.show(graficoMenu, "grafico2");

				} else if (selectGraph.getSelectedItem().toString().equals("Média Salários Idade")) {
					clGrafico.show(graficoMenu, "grafico3");

				} else if (selectGraph.getSelectedItem().toString().equals("Vendas por Dia")) {
					clGrafico.show(graficoMenu, "grafico4");

				} else {
					clGrafico.show(graficoMenu, "grafico5");

				}
			}
		});
	}
	
	private void botoes() {
		
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmUserDesign.getContentPane(), "Vendas");
			}
		});
		
		botoesFuncionarios();
		botoesProdutos();
		botoesMaquinas();
		botoesRelatorio();
		botoesGrafico();
		// Menu Pop UPS
		btnRececaoProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				receberprodutoClass.receberProdutoFunc(nomeArmazem);

			}
		});
		btnEnviarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarprodutoClass.enviar(nomeArmazem);
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
				int confirmed = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja sair do programa?",
						"Exit", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					messageLogs.saiuSistema(username+";"+false+";"+nomeArmazem);
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
		showMaquinasMenu();
		showFuncionariosMenu();
		showProdutos();
		showGraficos();
		
		this.vendasClass = new Vendas();
		vendasClass.showVendasMenu(frmUserDesign,cl);
		putPanels();
		putPanelsGrafico();

		botoes();
	}

	public JFrame getFrmUserDesign() {
		return frmUserDesign;
	}
}
