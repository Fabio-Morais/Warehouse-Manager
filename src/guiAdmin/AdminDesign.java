package guiadmin;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import db.DataBase;
import gui.Interface;
import gui.MenuBar;
import gui_admin_fornecedores.Fornecedores;
import gui_admin_maquina.Maquina;
import gui_admin_users.Users;
import logic.MessageLogs;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AdminDesign {

	private String nomeArmazem;
	private String username;

	private JFrame frmMenuAdmin;
	private static final String MENUADMINSTRING = "menu_admin";
	private static final String ADD = "/add.png";
	private static final String REMOVE = "/remove.png";
	private static final String REFRESH = "/refresh.png";
	private static final String EDIT = "/edit1.png";

	private static final String WIFI = "/wifi.png";
	private static final String DBICON = "/db.png";
	private static final String LOG = "/log.png";

	/* MENU */
	private static final String USERMENU = "/avatar.png";
	private static final String MACHINEMENU = "/machineMin.png";
	private static final String DBMENU = "/db.png";
	private static final String CATEGORIAMENU = "/listMin.png";
	private static final String FUNCIONARIOMENU = "/worker.png";
	private static final String FORNECEDORMENU = "/truckMin.png";

	private static final String REFRESHSTRING = "Refresh";
	private static final String EDITARSTRING = "Editar";
	private static final String REMOVERSTRING = "Remover";

	private MessageLogs messageLogs;


	/* LOGIC CLASS */
	private Users usersClass;
	private Funcionarios funcionarioClass;
	private Fornecedores fornecedorClass;
	private Maquina maquinaClass;
	private CategoriaProduto categoriaProdutoClass;
	private BaseDados baseDadosClass;
	/************/

	/* PANELS */
	private JPanel menuAdmin;
	private JPanel categoriaProduto;
	private JPanel baseDados;
	private JPanel funcionario;
	/************/

	private CardLayout cl;

	/* MAIN MENU */
	private JSeparator separator1;
	private JSeparator separator;
	private JSeparator separator2;
	private JSeparator separator3;
	private JLabel lblControlo;
	private JPanel menuAdminPanel;
	private JLabel lblAtividade;
	private JLabel lblSegurana;
	private JButton btnUsers;
	private JButton btnLog;
	private JButton btnFuncionario;
	private JButton btnCategoriaProduto;
	private JButton btnFornecedores;
	private JButton btnMaquinas;
	private JButton btnDB;
	/************/

	/* USERS MENU */
	
	private JLabel lblData;
	private JEditorPane textoAdmin;
	/**************/

	/* CATEGORIA PRODUTO MENU */
	private JLabel categoriaProdutoTexto;
	private JSeparator categoriaProdutoSeparator;
	private JScrollPane categoriaProdutoScrollPane;
	private JScrollPane categoriaProdutoScrollPane2;
	private JPanel categoriaProdutoPanel;
	private JButton categoriaProdutoBtnAdicionar;
	private JButton categoriaProdutoBtnRemover;
	private JButton categoriaProdutoBtnRefresh;
	private JButton categoriaProdutoBtnHome;
	private JTable categoriaProdutoTable;
	private JTable subCategoriaProdutoTable;
	private DefaultTableModel modelCategoriaProduto;
	private DefaultTableModel modelSubCategoriaProduto;
	/******************/


	/* BASE DE DADOS MENU */
	private JTextField baseDadosUser;
	private JTextField baseDadosPassword;
	private JTextField baseDadosUrl;
	private JPanel baseDadosPanel;
	private JLabel baseDadosTexto;
	private JSeparator baseDadosSeparator;
	private JLabel baseDadosTextoUser;
	private JLabel baseDadosTextoPassword;
	private JLabel lblUrl;
	private JButton baseDadosBtnAdicionar;
	private JButton baseDadosBtnhome;
	private JButton baseDadosBtnTest;
	private JButton baseDadosBtnCriarTabelas;
	/********************/

	/* FUNCIONARIO MENU */
	private JPanel funcionarioPanel;
	private JLabel funcionarioTexto;
	private JSeparator funcionarioSeparator;
	private JScrollPane funcionarioScrollPane;
	private JButton funcionarioBtnAdicionar;
	private JButton funcionarioBtnEditar;
	private JButton funcionarioBtnRemover;
	private JButton funcionarioBtnHome;
	private JButton funcionarioBtnRefresh;
	private DefaultTableModel modelFuncionario;
	private TableRowSorter<DefaultTableModel> sorterFuncionario;
	private JTable funcionarioTable;
	private JTextField funcionarioSearch;

	/******************/
	
	/* LOGS MENU */
	private JPanel logs;
	private JPanel logsPanel;
	private JLabel lblLogs;
	private JTable logsTable;
	private JSeparator logSeparator;
	private JScrollPane scrollPane;
	private JTextField logsSearch;
	private DefaultTableModel modelLogs;
	private TableRowSorter<DefaultTableModel> sorterLogs;
	private JButton logsBtnRefresh;
	private JButton logsBtnHome;
	private JComboBox<String> numeroLinhasCombo;
	/******************/
	
	private DataBase db;// BASE DE DADOS
	private MenuBar menuBar;

	/*
	 * Create the application.
	 */
	public AdminDesign(String nomeArmazem, String username) {
		db = DataBase.getInstance();
		this.nomeArmazem = nomeArmazem;
		this.funcionarioClass = new Funcionarios(username);
		this.categoriaProdutoClass = new CategoriaProduto(username);
		this.baseDadosClass = new BaseDados(username);
		this.username = username;
		this.messageLogs = MessageLogs.getInstance();

		initialize();
		

	}

	/* Coloca os panels para conseguir mudar */
	private void putPanels() {
		frmMenuAdmin.getContentPane().add(menuAdmin, MENUADMINSTRING);
		frmMenuAdmin.getContentPane().add(usersClass.getUsers(), "user");
		frmMenuAdmin.getContentPane().add(categoriaProduto, "categoria_produto");
		frmMenuAdmin.getContentPane().add(fornecedorClass.getFornecedores(), "fornecedores");
		frmMenuAdmin.getContentPane().add(maquinaClass.getMaquina(), "maquina");
		frmMenuAdmin.getContentPane().add(baseDados, "base_dados");
		frmMenuAdmin.getContentPane().add(funcionario, "funcionario");
		frmMenuAdmin.getContentPane().add(logs, "logs");

		cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);// mostrar o main menu
	}

	/* MAIN PANEL - PRINCIPAL */
	private GroupLayout putMenuLayout() {
		GroupLayout glMenuAdminPanel = new GroupLayout(menuAdminPanel);
		glMenuAdminPanel.setHorizontalGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glMenuAdminPanel.createSequentialGroup().addGroup(glMenuAdminPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(glMenuAdminPanel.createSequentialGroup().addContainerGap().addComponent(textoAdmin,
								GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(glMenuAdminPanel.createSequentialGroup().addGap(57).addGroup(glMenuAdminPanel
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSegurana, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(separator1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnUsers, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100,
												Short.MAX_VALUE)))
								.addGap(54)
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblControlo, GroupLayout.PREFERRED_SIZE, 129,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(separator, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(glMenuAdminPanel.createSequentialGroup()
														.addGroup(glMenuAdminPanel
																.createParallelGroup(Alignment.TRAILING)
																.addComponent(btnFornecedores,
																		GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnMaquinas, GroupLayout.PREFERRED_SIZE,
																		100, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(
																glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
																		.addComponent(btnFuncionario,
																				GroupLayout.PREFERRED_SIZE, 100,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(btnCategoriaProduto,
																				GroupLayout.PREFERRED_SIZE, 100,
																				GroupLayout.PREFERRED_SIZE)))))
								.addGap(61)
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnDB, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 99,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 129,
												GroupLayout.PREFERRED_SIZE))
								.addGap(37)
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAtividade, GroupLayout.PREFERRED_SIZE, 129,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 99,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnLog, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(72, Short.MAX_VALUE)));
		glMenuAdminPanel.setVerticalGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glMenuAdminPanel.createSequentialGroup().addGroup(glMenuAdminPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(glMenuAdminPanel.createSequentialGroup().addContainerGap().addGroup(glMenuAdminPanel
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(glMenuAdminPanel.createSequentialGroup()
										.addComponent(
												textoAdmin, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addGap(29)
										.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblControlo, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSegurana, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAtividade, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(separator, GroupLayout.PREFERRED_SIZE, 15,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 15,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 15,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(Alignment.TRAILING,
								glMenuAdminPanel.createSequentialGroup()
										.addComponent(separator3, GroupLayout.PREFERRED_SIZE, 15,
												GroupLayout.PREFERRED_SIZE)
										.addGap(15)))
						.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLog, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnDB, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addGroup(glMenuAdminPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(glMenuAdminPanel.createSequentialGroup()
														.addGroup(glMenuAdminPanel
																.createParallelGroup(Alignment.LEADING)
																.addComponent(btnMaquinas, GroupLayout.PREFERRED_SIZE,
																		75, Short.MAX_VALUE)
																.addComponent(btnFuncionario,
																		GroupLayout.PREFERRED_SIZE, 75,
																		Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(
																glMenuAdminPanel.createParallelGroup(Alignment.BASELINE)
																		.addComponent(btnFornecedores,
																				GroupLayout.PREFERRED_SIZE, 75,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(btnCategoriaProduto,
																				GroupLayout.PREFERRED_SIZE, 75,
																				Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED))
												.addComponent(btnUsers, GroupLayout.PREFERRED_SIZE, 75,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(64, Short.MAX_VALUE)));
		menuAdminPanel.setLayout(glMenuAdminPanel);
		return glMenuAdminPanel;
	}
	private void criaBotoesMenu() {
		btnUsers = new JButton("Users");
		btnLog =  new JButton("Logs");
		btnFuncionario =  new JButton("<html>Funcionario</html>");
		btnCategoriaProduto =  new JButton("Categoria");
		btnFornecedores =  new JButton("<html>Fornecedores<html>");
		btnMaquinas =  new JButton("Maquinas");
		btnDB =  new JButton("<html>Base de<br >Dados</html>");
		Interface.styleButton(btnDB, DBMENU, new Insets(5, 20, 5, 20));
		Interface.styleButton(btnUsers, USERMENU, new Insets(10, 14, 10, 14));
		Interface.styleButton(btnLog, LOG, new Insets(10, 2, 10, 2));
		Interface.styleButton(btnFuncionario, FUNCIONARIOMENU, new Insets(10, 2, 10, 2));
		Interface.styleButton(btnCategoriaProduto, CATEGORIAMENU, new Insets(10, 2, 10, 2));
		Interface.styleButton(btnFornecedores, FORNECEDORMENU, new Insets(10, 5, 10, 5));
		Interface.styleButton(btnMaquinas, MACHINEMENU, new Insets(10, 14, 10, 14));
	}
	private void showMainMenu() {
		menuAdmin = new JPanel();

		cl = new CardLayout(0, 0);
		frmMenuAdmin.getContentPane().setLayout(cl);
		frmMenuAdmin.getContentPane().add(menuAdmin, "name_1243457861194300");
		menuAdmin.setLayout(new BorderLayout(0, 0));

		/* MENU_ADMIN_PANEL PANEL */
		menuAdminPanel = new JPanel();
		menuAdminPanel.setPreferredSize(new Dimension(10, 16));
		menuAdmin.add(menuAdminPanel, BorderLayout.CENTER);

		criaBotoesMenu();

		lblSegurana =  new JLabel("Seguran\u00E7a");
		Interface.styleLabelMenu(lblSegurana);
		separator1 = new JSeparator();
		Interface.styleSeparator(separator1);
		separator = new JSeparator();
		Interface.styleSeparator(separator);
		separator2 = new JSeparator();
		Interface.styleSeparator(separator2);
		lblControlo = new JLabel("Controlo");
		Interface.styleLabelMenu(lblControlo);
		lblData =new JLabel("Data");
		Interface.styleLabelMenu(lblData);
		lblAtividade = new JLabel("Atividade");
		Interface.styleLabelMenu(lblAtividade);
		separator3 = new JSeparator();
		Interface.styleSeparator(separator3);
		
		textoAdmin = new JEditorPane();
		Interface.styleTituloMenu(textoAdmin,"Admin");
		
		GroupLayout glMenuAdminPanel = putMenuLayout();
		menuAdminPanel.setLayout(glMenuAdminPanel);
	}
	
	/* USER PANEL - SECUNDARIO */
	/* CATEGORIA PRODUTO MENU - SECUNDARIO */
	private void criaBotoesCategoria() {
		categoriaProdutoBtnAdicionar = new JButton("Adicionar");
		Interface.styleBotaoSimples(categoriaProdutoBtnAdicionar, ADD);

		categoriaProdutoBtnRemover = new JButton(REMOVERSTRING);
		Interface.styleBotaoSimples(categoriaProdutoBtnRemover, REMOVE);
		
		categoriaProdutoBtnRefresh = new JButton(REFRESHSTRING);
		Interface.styleBotaoSimples(categoriaProdutoBtnRefresh, REFRESH);

		categoriaProdutoBtnHome = new JButton("Home");
		Interface.styleBotaoHome(categoriaProdutoBtnHome);
	}
	private GroupLayout putCategoriaLayout() {
		GroupLayout glCategoriaProdutoPanel = new GroupLayout(categoriaProdutoPanel);
		glCategoriaProdutoPanel.setHorizontalGroup(
				glCategoriaProdutoPanel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						glCategoriaProdutoPanel.createSequentialGroup().addGap(37).addGroup(glCategoriaProdutoPanel
								.createParallelGroup(Alignment.LEADING).addComponent(categoriaProdutoTexto)
								.addGroup(glCategoriaProdutoPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(categoriaProdutoSeparator, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(glCategoriaProdutoPanel.createSequentialGroup().addGap(15)
												.addGroup(glCategoriaProdutoPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(categoriaProdutoBtnRefresh,
																GroupLayout.PREFERRED_SIZE, 113,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(categoriaProdutoBtnHome,
																GroupLayout.PREFERRED_SIZE, 113,
																GroupLayout.PREFERRED_SIZE)))))
								.addGap(99)
								.addGroup(glCategoriaProdutoPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(glCategoriaProdutoPanel.createSequentialGroup()
												.addComponent(categoriaProdutoBtnAdicionar)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(categoriaProdutoBtnRemover, GroupLayout.PREFERRED_SIZE,
														113, GroupLayout.PREFERRED_SIZE))
										.addComponent(categoriaProdutoScrollPane, GroupLayout.DEFAULT_SIZE, 414,
												Short.MAX_VALUE)
										.addComponent(categoriaProdutoScrollPane2, 0, 0, Short.MAX_VALUE))
								.addContainerGap(91, Short.MAX_VALUE)));
		glCategoriaProdutoPanel.setVerticalGroup(glCategoriaProdutoPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glCategoriaProdutoPanel.createSequentialGroup().addGroup(glCategoriaProdutoPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(glCategoriaProdutoPanel.createSequentialGroup().addContainerGap()
								.addComponent(categoriaProdutoTexto).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(categoriaProdutoSeparator, GroupLayout.PREFERRED_SIZE, 15,
										GroupLayout.PREFERRED_SIZE)
								.addGap(54)
								.addComponent(categoriaProdutoBtnRefresh, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(categoriaProdutoBtnHome, GroupLayout.PREFERRED_SIZE, 59,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(glCategoriaProdutoPanel.createSequentialGroup().addGap(26)
								.addGroup(glCategoriaProdutoPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(categoriaProdutoBtnAdicionar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(categoriaProdutoBtnRemover, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(categoriaProdutoScrollPane, GroupLayout.PREFERRED_SIZE, 117,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(categoriaProdutoScrollPane2, GroupLayout.PREFERRED_SIZE, 117,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(34, Short.MAX_VALUE)));
		return glCategoriaProdutoPanel;
	}
	private void showCategoriaProduto() {
		categoriaProduto = new JPanel();
		frmMenuAdmin.getContentPane().add(categoriaProduto, "name_1323741143870400");
		categoriaProduto.setLayout(new BorderLayout(0, 0));
		categoriaProdutoPanel = new JPanel();
		categoriaProduto.add(categoriaProdutoPanel, BorderLayout.CENTER);

		categoriaProdutoTexto = new JLabel("<html>Categoria<br>Produto</html>");
		Interface.styleLabel28(categoriaProdutoTexto);
		
		categoriaProdutoSeparator = new JSeparator();
		Interface.styleSeparator(categoriaProdutoSeparator);
		
		categoriaProdutoScrollPane = new JScrollPane();
		categoriaProdutoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		categoriaProdutoScrollPane2 = new JScrollPane();
		categoriaProdutoScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		criaBotoesCategoria();
		
		GroupLayout glCategoriaProdutoPanel = putCategoriaLayout();

		subCategoriaProdutoTable = categoriaProdutoClass.getSubCategoriaProdutoTable();
		categoriaProdutoTable = categoriaProdutoClass.getCategoriaProdutoTable();
		modelCategoriaProduto = categoriaProdutoClass.getModelCategoriaProduto();
		modelSubCategoriaProduto = categoriaProdutoClass.getModelSubCategoriaProduto();
		
		categoriaProdutoScrollPane2.setViewportView(subCategoriaProdutoTable);
		categoriaProdutoScrollPane.setViewportView(categoriaProdutoTable);
		categoriaProdutoPanel.setLayout(glCategoriaProdutoPanel);

		db.categoriaProduto(modelCategoriaProduto);

	}

	
	/* BASE DE DADOS MENU - SECUNDARIO */
	private void criaBotoesBaseDados() {
		baseDadosBtnAdicionar = new JButton("Atualizar");
		Interface.styleBotaoSimples(baseDadosBtnAdicionar, ADD);

		baseDadosBtnhome = new JButton("Home");
		Interface.styleBotaoHome(baseDadosBtnhome);

		baseDadosBtnTest = new JButton("<html>Testar<br> Conex\u00E3o</html>");
		Interface.styleBotaoSimples(baseDadosBtnTest, WIFI);

		baseDadosBtnCriarTabelas = new JButton("Criar Tabelas");
		Interface.styleBotaoSimples(baseDadosBtnCriarTabelas, DBICON);
	}
	private GroupLayout putBaseDadosLayout() {
		GroupLayout glBaseDadosPanel = new GroupLayout(baseDadosPanel);
		glBaseDadosPanel.setHorizontalGroup(
			glBaseDadosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glBaseDadosPanel.createSequentialGroup()
					.addGap(42)
					.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glBaseDadosPanel.createSequentialGroup()
							.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(baseDadosBtnhome, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addGroup(glBaseDadosPanel.createSequentialGroup()
									.addGap(2)
									.addComponent(baseDadosSeparator, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
								.addComponent(baseDadosBtnTest, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
							.addGap(131)
							.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glBaseDadosPanel.createSequentialGroup()
									.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(baseDadosTextoUser, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUrl)
										.addComponent(baseDadosTextoPassword))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(baseDadosPassword, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addComponent(baseDadosUser, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addComponent(baseDadosUrl, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
								.addComponent(baseDadosBtnAdicionar))
							.addGap(135)
							.addComponent(baseDadosBtnCriarTabelas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(94))
						.addGroup(glBaseDadosPanel.createSequentialGroup()
							.addComponent(baseDadosTexto, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(686, Short.MAX_VALUE))))
		);
		glBaseDadosPanel.setVerticalGroup(
			glBaseDadosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glBaseDadosPanel.createSequentialGroup()
					.addGap(85)
					.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(baseDadosBtnCriarTabelas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(glBaseDadosPanel.createSequentialGroup()
							.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(baseDadosTextoUser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(baseDadosUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(baseDadosTextoPassword, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(baseDadosPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(glBaseDadosPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUrl, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(baseDadosUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(baseDadosBtnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(107))
				.addGroup(glBaseDadosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(baseDadosTexto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(baseDadosSeparator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(baseDadosBtnTest)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(baseDadosBtnhome, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(136, Short.MAX_VALUE))
		);
		return glBaseDadosPanel;
	}
	private void showBaseDadosMenu() {
		baseDados = new JPanel();
		frmMenuAdmin.getContentPane().add(baseDados, "name_1323856331283900");
		baseDados.setLayout(new BorderLayout(0, 0));

		baseDadosPanel = new JPanel();
		baseDados.add(baseDadosPanel, BorderLayout.CENTER);

		baseDadosTexto = new JLabel("<html>Base de <br>Dados</html>");
		baseDadosTexto.setFont(new Font("HP Simplified", Font.BOLD, 27));

		baseDadosSeparator = new JSeparator();
		Interface.styleSeparator(baseDadosSeparator);

		baseDadosTextoUser = new JLabel("User:");
		baseDadosTextoUser.setFont(new Font("Tahoma", Font.BOLD, 16));

		baseDadosTextoPassword = new JLabel("Password:");
		baseDadosTextoPassword.setFont(new Font("Tahoma", Font.BOLD, 16));

		baseDadosUser = new JTextField();
		baseDadosUser.setText("sinf19a38");
		baseDadosUser.setColumns(10);

		baseDadosPassword = new JTextField();
		baseDadosPassword.setText("UbwJSLsu");
		baseDadosPassword.setColumns(10);

		baseDadosUrl = new JTextField();
		baseDadosUrl.setText("db.fe.up.pt:5432");
		baseDadosUrl.setColumns(10);

		lblUrl = new JLabel("Url:");
		lblUrl.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		criaBotoesBaseDados();
		GroupLayout glBaseDadosPanel = putBaseDadosLayout();
		baseDadosPanel.setLayout(glBaseDadosPanel);
	}

	/* FUNCIONARIO MENU - SECUNDARIO */
	private void criaFuncionarioSearch() {
		sorterFuncionario = new TableRowSorter<>(modelFuncionario);
		funcionarioTable.setRowSorter(sorterFuncionario);

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
	private void criaBotoesFuncionario() {
		funcionarioBtnAdicionar = new JButton("Adicionar");
		Interface.styleBotaoSimples(funcionarioBtnAdicionar, ADD);

		funcionarioBtnEditar = new JButton(EDITARSTRING);
		Interface.styleBotaoSimples(funcionarioBtnEditar, EDIT);

		funcionarioBtnRemover = new JButton(REMOVERSTRING);
		Interface.styleBotaoSimples(funcionarioBtnRemover, REMOVE);
		
		funcionarioBtnHome = new JButton("Home");
		Interface.styleBotaoHome(funcionarioBtnHome);

		funcionarioBtnRefresh = new JButton(REFRESHSTRING);
		Interface.styleBotaoSimples(funcionarioBtnRefresh, REFRESH);		
	}
	private GroupLayout putFuncionarioLayout() {
		GroupLayout glFuncionarioPanel = new GroupLayout(funcionarioPanel);
		glFuncionarioPanel.setHorizontalGroup(
			glFuncionarioPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glFuncionarioPanel.createSequentialGroup()
					.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glFuncionarioPanel.createSequentialGroup()
							.addGap(20)
							.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(funcionarioTexto, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
								.addComponent(funcionarioSeparator, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
						.addGroup(glFuncionarioPanel.createSequentialGroup()
							.addGap(44)
							.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(funcionarioBtnRefresh, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(funcionarioBtnHome, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(glFuncionarioPanel.createSequentialGroup()
							.addComponent(funcionarioBtnAdicionar)
							.addGap(18)
							.addComponent(funcionarioBtnEditar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(funcionarioBtnRemover, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addComponent(funcionarioScrollPane, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE))
					.addGap(42))
		);
		glFuncionarioPanel.setVerticalGroup(
			glFuncionarioPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glFuncionarioPanel.createSequentialGroup()
					.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glFuncionarioPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(funcionarioTexto, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(funcionarioSeparator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(funcionarioBtnRefresh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(funcionarioBtnHome, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(glFuncionarioPanel.createSequentialGroup()
							.addGap(32)
							.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(funcionarioBtnAdicionar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addComponent(funcionarioBtnRemover, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addComponent(funcionarioBtnEditar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(funcionarioScrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		return glFuncionarioPanel;
	}
	private void showFuncionarioMenu() {

		funcionario = new JPanel();
		frmMenuAdmin.getContentPane().add(funcionario, "name_1832054822092700");
		funcionario.setLayout(new BorderLayout(0, 0));

		funcionarioPanel = new JPanel();
		funcionario.add(funcionarioPanel, BorderLayout.CENTER);

		funcionarioTexto = new JLabel("Funcion\u00E1rios");
		Interface.styleLabel(funcionarioTexto);

		funcionarioSeparator = new JSeparator();
		Interface.styleSeparator(funcionarioSeparator);

		funcionarioScrollPane = new JScrollPane();
		funcionarioScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		funcionarioScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		funcionarioSearch = new JTextField();
		Interface.styleSearch(funcionarioSearch);

		criaBotoesFuncionario();
		
		GroupLayout glFuncionarioPanel = putFuncionarioLayout();
		
		modelFuncionario = funcionarioClass.getModelFuncionario();
		funcionarioTable = funcionarioClass.getFuncionarioTable();
		
		funcionarioScrollPane.setViewportView(funcionarioTable);
		funcionarioPanel.setLayout(glFuncionarioPanel);
		
		db.nomeNifFuncionario(modelFuncionario, nomeArmazem);

		criaFuncionarioSearch();

	}

	/* LOGS MENU - SECUNDARIO */
	private void criaLogsSearch() {
		sorterLogs = new TableRowSorter<>(modelLogs);
		logsTable.setRowSorter(sorterLogs);

		logsSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logsSearch.setText("");
			}
		});
		logsSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(logsSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(logsSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(logsSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterLogs.setRowFilter(null);
				} else {
					sorterLogs.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});
		

	}
	private void criaBotoesLogs() {
		logsBtnRefresh = new JButton(REFRESHSTRING);
		Interface.styleBotaoSimples(logsBtnRefresh, REFRESH);

		logsBtnHome = new JButton("Home");
		Interface.styleBotaoHome(logsBtnHome);	
	}
	private GroupLayout putLogsLayout() {
		GroupLayout glLogsPanel = new GroupLayout(logsPanel);
		glLogsPanel.setHorizontalGroup(
			glLogsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glLogsPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(glLogsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogs, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(logSeparator, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(logsBtnRefresh, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(logsBtnHome, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(glLogsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glLogsPanel.createSequentialGroup()
							.addGap(339)
							.addComponent(numeroLinhasCombo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(logsSearch, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		glLogsPanel.setVerticalGroup(
			glLogsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glLogsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(glLogsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(glLogsPanel.createSequentialGroup()
							.addGap(41)
							.addGroup(glLogsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(logsSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(numeroLinhasCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(glLogsPanel.createSequentialGroup()
							.addComponent(lblLogs, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(logSeparator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glLogsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addGroup(glLogsPanel.createSequentialGroup()
							.addGap(35)
							.addComponent(logsBtnRefresh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(logsBtnHome, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		return glLogsPanel;
	}
	private void showLogsMenu() {
		logs = new JPanel();
		frmMenuAdmin.getContentPane().add(logs, "name_317578181588800");
		logs.setLayout(new BorderLayout(0, 0));

		logsPanel = new JPanel();
		logs.add(logsPanel, BorderLayout.CENTER);

		lblLogs = new JLabel("Logs");
		lblLogs.setFont(new Font("HP Simplified", Font.BOLD, 34));

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		criaBotoesLogs();

		logsSearch = new JTextField();
		Interface.styleSearch(logsSearch);

		logSeparator = new JSeparator();
		Interface.styleSeparator(logSeparator);
		
		numeroLinhasCombo = new JComboBox<>();
		numeroLinhasCombo.setToolTipText("Numero de linhas");
		numeroLinhasCombo.setFont(new Font("Consolas", Font.PLAIN, 14));
		numeroLinhasCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"25", "50", "100", "200", "400"}));
		
		GroupLayout glLogsPanel = putLogsLayout();
				
		modelLogs = new DefaultTableModel(new Object[][] {}, 
				new String[] { "Data", "Username", "Admin", "Acao", "Ip" }) {
			/**
					 * 
					 */
					private static final long serialVersionUID = -7481186986491942822L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, Boolean.class, String.class, String.class };

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

		logsTable = new JTable();
		logsTable.setModel(modelLogs);
		logsTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(logsTable);
		logsPanel.setLayout(glLogsPanel);
		
		/* Para nao mover */
		logsTable.getTableHeader().setReorderingAllowed(false);
		logsTable.setAutoCreateRowSorter(true);// para ordenar
		logsTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.getLogs(modelLogs, numeroLinhasCombo.getSelectedItem().toString());
		
		criaLogsSearch();
		
	}

	/* TEMPO COMO BACKGROUND */
	
	
	private void buttonsMainMenu() {

		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "user");
				menuBar.setCurrentPanel("user");
			}
		});
		btnCategoriaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "categoria_produto");
				menuBar.setCurrentPanel("categoria_produto");

			}
		});
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "fornecedores");
				menuBar.setCurrentPanel("fornecedores");

			}
		});
		btnMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "maquina");
				menuBar.setCurrentPanel("maquina");

			}
		});
		btnDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "base_dados");
				menuBar.setCurrentPanel("base_dados");

			}
		});
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cl.show(frmMenuAdmin.getContentPane(), "funcionario");
				menuBar.setCurrentPanel("funcionario");

			}
		});
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "logs");
				menuBar.setCurrentPanel("logs");

			}
		});
		
	}
	
	

	private void buttonsCategoria() {
		categoriaProdutoBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				menuBar.setCurrentPanel(MENUADMINSTRING);
			}
		});
		categoriaProdutoBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaProdutoClass.adicionarCategoria(modelCategoriaProduto, modelSubCategoriaProduto, menuBar.getNomeArmazem());
			}
		});
		categoriaProdutoBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaProdutoClass.refresh(modelCategoriaProduto, modelSubCategoriaProduto);
			}
		});
		categoriaProdutoBtnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaProdutoClass.removeCategoria(categoriaProdutoTable, subCategoriaProdutoTable,
						modelCategoriaProduto, modelSubCategoriaProduto, menuBar.getNomeArmazem());
			}
		});

		/* listener de quando o selection muda */
		categoriaProdutoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				categoriaProdutoClass.showSubCategoria(categoriaProdutoTable, modelCategoriaProduto,
						modelSubCategoriaProduto);

			}
		});
		/* listener das teclas */
		categoriaProdutoTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					categoriaProdutoClass.removeCategoria(categoriaProdutoTable, subCategoriaProdutoTable,
							modelCategoriaProduto, modelSubCategoriaProduto, menuBar.getNomeArmazem());

			}
		});
		subCategoriaProdutoTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					categoriaProdutoClass.removeCategoria(categoriaProdutoTable, subCategoriaProdutoTable,
							modelCategoriaProduto, modelSubCategoriaProduto, menuBar.getNomeArmazem());

			}
		});

		categoriaProdutoPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				categoriaProdutoTable.clearSelection();
				subCategoriaProdutoTable.clearSelection();

			}
		});
	}
	
	private void buttonsFuncionarios() {
		funcionarioBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				menuBar.setCurrentPanel(MENUADMINSTRING);

			}
		});
		funcionarioBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.adicionarFuncionario(modelFuncionario, menuBar.getNomeArmazem());
			}
		});
		funcionarioBtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.editarFuncionario(funcionarioTable, modelFuncionario);
			}
		});
		funcionarioBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.refresh(modelFuncionario, menuBar.getNomeArmazem());
			}
		});
		funcionarioBtnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.removeFuncionario(funcionarioTable, modelFuncionario, menuBar.getNomeArmazem());
			}
		});
		funcionarioTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					funcionarioClass.removeFuncionario(funcionarioTable, modelFuncionario, menuBar.getNomeArmazem());

			}
		});
		/* Double click num elemento */
		funcionarioTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println("Clicou no  " + modelFuncionario.getValueAt(table.getSelectedRow(), 1));
				}
			}
		});

	}
	
	private void buttonsBaseDados() {
		baseDadosBtnhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				menuBar.setCurrentPanel(MENUADMINSTRING);

			}
		});
		baseDadosBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baseDadosClass.adicionar(baseDadosUser.getText(), baseDadosPassword.getText(), baseDadosUrl.getText(),
						menuBar.getNomeArmazem());
			}
		});
		baseDadosBtnCriarTabelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parentFrame = new JFrame();
				JFileChooser fileChooser = new JFileChooser("baseDados");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.sql", "sql");
				File file = null;
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Escolha lugar do script");
				if (fileChooser.showSaveDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					if (!file.getName().toLowerCase().endsWith(".sql"))
						file = new File(file.getAbsolutePath() + ".sql");
				}
				db.createTable(file);
			}
		});

		baseDadosBtnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JLabel aux = new JLabel();
				baseDadosClass.testDataBase(aux);
				menuBar.setLblStatusDb(aux.getText());
			}
		});
	}
	
	private void buttonsLogs() {
		logsBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				menuBar.setCurrentPanel(MENUADMINSTRING);

			}
		});
		logsBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = modelLogs.getRowCount() - 1; i >= 0; i--) {
					modelLogs.removeRow(i);
				}
				db.getLogs(modelLogs, numeroLinhasCombo.getSelectedItem().toString());
			}
		});
		numeroLinhasCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println("entrei");
				for (int i = modelLogs.getRowCount() - 1; i >= 0; i--) {
					modelLogs.removeRow(i);
				}
				db.getLogs(modelLogs, numeroLinhasCombo.getSelectedItem().toString());
			}
		});
	}
	
	/* AÇOES DOS BOTOES */
	private void buttons() {
		buttonsMainMenu();
		//botoes secundarios
		buttonsCategoria();
		buttonsFuncionarios();
		buttonsBaseDados();
		buttonsLogs();
	}

	public void initialize() {
		/* cria a frame do windows */
		frmMenuAdmin = new JFrame("Menu Admin");
		frmMenuAdmin.setResizable(false);
		frmMenuAdmin.setMaximizedBounds(new Rectangle(0, 0, 0, 0));
		frmMenuAdmin.setForeground(Color.WHITE);
		frmMenuAdmin.setTitle("Menu Admin");
		frmMenuAdmin.setBackground(Color.WHITE);
		frmMenuAdmin.setBounds(100, 100, 855, 416);
		frmMenuAdmin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja sair do programa?",
						"Exit", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					messageLogs.saiuSistema(username+";"+true+";"+menuBar.getNomeArmazem());
					frmMenuAdmin.dispose();
				}
			}
		});
		frmMenuAdmin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frmMenuAdmin.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frmMenuAdmin.getHeight()) / 2);
		frmMenuAdmin.setLocation(x, y);
		URL iconURL = getClass().getResource("/logo.png");
		ImageIcon img = new ImageIcon(iconURL);
		frmMenuAdmin.setIconImage(img.getImage());
		showMainMenu();// MAIN MENU - PRINCIPAL


		showCategoriaProduto();// CATEGORIA PRODUTO MENU - SECUNDARIO

		showBaseDadosMenu();// BASE DE DADOS MENU - SECUNDARIO

		showFuncionarioMenu();// FUNCIONARIO MENU - SECUNDARIO

		showLogsMenu();
		
		menuBar = new MenuBar(frmMenuAdmin, cl, username);
		menuBar.setCurrentPanel(MENUADMINSTRING);
		menuBar.setNomeArmazem(nomeArmazem);
		menuBar.showMenuBar(0);		

		this.usersClass = new Users(username, menuBar);
		usersClass.showUserMenu(frmMenuAdmin,cl);
		this.maquinaClass = new Maquina(username, nomeArmazem, menuBar);
		maquinaClass.showMaquinaMenu(frmMenuAdmin,cl);
		this.fornecedorClass = new Fornecedores(username,menuBar);
		fornecedorClass.showFornecedoresMenu(frmMenuAdmin,cl);
		
		putPanels();// COLOCAR OS PANELS PARA MANIPULAR

		buttons();// LISTENERS DOS BOTÕES

	}

	public JFrame getFrmMenuAdmin() {
		return frmMenuAdmin;
	}
}
