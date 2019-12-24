package guiadmin;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Desktop;
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
import gui.Help;
import gui.PopUp;
import guilogin.LoginDesign;
import logic.MessageLogs;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.MenuEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AdminDesign {

	private String nomeArmazem;
	private String currentPanel;
	private String username;

	private JFrame frmMenuAdmin;
	private static final String MENUADMINSTRING = "menu_admin";
	private static final String ADD = "/add.png";
	private static final String REMOVE = "/remove.png";
	private static final String HOME = "/home.png";
	private static final String REFRESH = "/refresh.png";
	private static final String EDIT = "/edit1.png";
	private static final String MORE = "/more.png";
	private static final String MORE1 = "/more1.png";
	private static final String WEBSITE = "/website.PNG";
	private static final String HELP = "/help.png";
	private static final String HOMEMIN = "/homeMin.png";
	private static final String LOGOUT = "/logout.png";
	private static final String CHANGE = "/change.png";
	private static final String WIFI = "/wifi.png";
	private static final String DB = "/db.png";
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
	private Armazem armazemClass;
	private Funcionarios funcionarioClass;
	private Fornecedores fornecedorClass;
	private Maquina maquinaClass;
	private CategoriaProduto categoriaProdutoClass;
	private BaseDados baseDadosClass;
	/************/

	/* PANELS */
	private JPanel menuAdmin;
	private JPanel users;
	private JPanel categoriaProduto;
	private JPanel fornecedores;
	private JPanel maquina;
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
	private JPanel usersPanel;
	private JButton usersBtnAdicionar;
	private JButton usersBtnRemover;
	private JButton usersBtnEditar;
	private JButton usersBtnRefresh;
	private JButton usersBtnHome;
	private JTable usersTable;
	private JLabel lblData;
	private JEditorPane textoAdmin;
	private DefaultTableModel modelUser;
	private TableRowSorter<DefaultTableModel> sorterUser;
	private JTextField userSearch;

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

	/* FORNECEDORES MENU */
	private JButton fornecedoresBtnAdicionar;
	private JButton fornecedoresBtnEditar;
	private JButton fornecedoresBtnRemover;
	private JButton fornecedoresBtnRefresh;
	private JButton fornecedoresBtnHome;
	private JTable fornecedorTable;
	private DefaultTableModel modelFornecedor;
	private TableRowSorter<DefaultTableModel> sorterFornecedor;
	private JTextField fornecedorSearch;

	/**************/

	/* MAQUINA MENU */
	private JButton maquinaBtnRefresh;
	private JButton maquinaBtnHome;
	private JButton maquinaBtnAdicionar;
	private JButton maquinaBtnEditar;
	private JButton maquinaBtnRemover;
	private JTable maquinaTable;
	private DefaultTableModel modelMaquina;
	private TableRowSorter<DefaultTableModel> sorterMaquina;
	private JTextField maquinaSearch;

	/*************/

	/* BASE DE DADOS MENU */
	private JTextField baseDadosUser;
	private JTextField baseDadosPassword;
	private JTextField baseDadosUrl;

	private JButton baseDadosBtnAdicionar;
	private JButton baseDadosBtnhome;
	private JButton baseDadosBtnTest;
	private JButton baseDadosBtnCriarTabelas;
	/********************/

	/* FUNCIONARIO MENU */
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

	/* MENU BAR */
	private JMenu menuBarArmazem;
	private JMenuItem mntmMenu;
	private JMenuItem mntmExit1;
	private JMenuItem menuWebsite;
	private JMenuItem menuHelpMe;
	private JMenuItem menuMudarNomeArmazem;
	private JLabel lblArmazem;

	/******************/
	private DataBase db;// BASE DE DADOS
	private Help help;// HELP interface
	private PopUp popUp;
	private JMenu mnNewMenu;
	private JLabel lblStatusDb;
	private Timer counterTimer;

	/*
	 * Create the application.
	 */
	public AdminDesign(String nomeArmazem, String username) {
		db = DataBase.getInstance();
		this.nomeArmazem = nomeArmazem;
		this.usersClass = new Users(username);
		this.armazemClass = new Armazem(username);
		this.funcionarioClass = new Funcionarios(username);
		this.fornecedorClass = new Fornecedores(username);
		this.maquinaClass = new Maquina(username);
		this.categoriaProdutoClass = new CategoriaProduto(username);
		this.baseDadosClass = new BaseDados(username);
		this.popUp = new PopUp();
		this.currentPanel = MENUADMINSTRING;
		this.username = username;
		this.messageLogs = MessageLogs.getInstance();

		help = new Help();
		initialize();
	}

	/* Coloca os panels para conseguir mudar */
	private void putPanels() {
		frmMenuAdmin.getContentPane().add(menuAdmin, MENUADMINSTRING);
		frmMenuAdmin.getContentPane().add(users, "user");
		frmMenuAdmin.getContentPane().add(categoriaProduto, "categoria_produto");
		frmMenuAdmin.getContentPane().add(fornecedores, "fornecedores");
		frmMenuAdmin.getContentPane().add(maquina, "maquina");
		frmMenuAdmin.getContentPane().add(baseDados, "base_dados");
		frmMenuAdmin.getContentPane().add(funcionario, "funcionario");
		frmMenuAdmin.getContentPane().add(logs, "logs");

		cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);// mostrar o main menu
	}

	
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
		/* MAIN PANEL - PRINCIPAL */
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

	
	private void criaUserSearch() {
		sorterUser = new TableRowSorter<>(modelUser);
		usersTable.setRowSorter(sorterUser);
		userSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				userSearch.setText("");
			}
		});
		userSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(userSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(userSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(userSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterUser.setRowFilter(null);
				} else {
					sorterUser.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});
	}
	private GroupLayout putUserLayout(JLabel usersTexto, JScrollPane usersScrollPane, JSeparator usersSeparator) {
		GroupLayout glUsersPanel = new GroupLayout(usersPanel);
		glUsersPanel.setHorizontalGroup(glUsersPanel.createParallelGroup(Alignment.TRAILING).addGroup(glUsersPanel
				.createSequentialGroup().addGap(37)
				.addGroup(glUsersPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(usersBtnHome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(usersTexto, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
						.addComponent(usersSeparator, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE).addComponent(
								usersBtnRefresh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(51)
				.addGroup(glUsersPanel.createParallelGroup(Alignment.LEADING).addGroup(glUsersPanel
						.createSequentialGroup().addComponent(usersBtnAdicionar).addGap(18)
						.addComponent(usersBtnEditar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(usersBtnRemover, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addGap(74).addComponent(userSearch, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
						.addComponent(usersScrollPane, GroupLayout.PREFERRED_SIZE, 632, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(16, Short.MAX_VALUE)));
		glUsersPanel.setVerticalGroup(glUsersPanel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				glUsersPanel.createSequentialGroup().addGroup(glUsersPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(glUsersPanel.createParallelGroup(Alignment.TRAILING).addGroup(glUsersPanel
								.createSequentialGroup().addContainerGap(19, Short.MAX_VALUE)
								.addGroup(glUsersPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(usersBtnAdicionar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(usersBtnEditar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(usersBtnRemover, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE))
								.addGap(4))
								.addGroup(Alignment.LEADING,
										glUsersPanel.createSequentialGroup().addContainerGap().addComponent(usersTexto,
												GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
						.addGroup(glUsersPanel.createSequentialGroup().addContainerGap()
								.addComponent(userSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)))
						.addGroup(
								glUsersPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(glUsersPanel.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(usersSeparator, GroupLayout.PREFERRED_SIZE, 15,
														GroupLayout.PREFERRED_SIZE)
												.addGap(46)
												.addComponent(usersBtnRefresh, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(usersBtnHome)
												.addContainerGap(124, Short.MAX_VALUE))
										.addGroup(Alignment.TRAILING,
												glUsersPanel.createSequentialGroup().addComponent(usersScrollPane,
														GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
														.addContainerGap()))));
		return glUsersPanel;
	}
	/* USER PANEL - SECUNDARIO */
	private void showUserMenu() {

		users = new JPanel();
		frmMenuAdmin.getContentPane().add(users, "name_1243457881841100");
		users.setLayout(new BorderLayout(0, 0));
		usersPanel = new JPanel();
		users.add(usersPanel, BorderLayout.CENTER);
		JScrollPane usersScrollPane = new JScrollPane();
		usersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		usersBtnAdicionar = new JButton("Adicionar");
		Interface.styleBotaoSimples(usersBtnAdicionar, ADD);
		usersBtnRemover =  new JButton(REMOVERSTRING);
		Interface.styleBotaoSimples(usersBtnRemover, REMOVE);
		usersBtnEditar = new JButton(EDITARSTRING);
		Interface.styleBotaoSimples(usersBtnEditar, EDIT);
		usersBtnRefresh = new JButton(REFRESHSTRING);
		Interface.styleBotaoSimples(usersBtnRefresh, REFRESH);
		JLabel usersTexto = new JLabel("Users");
		Interface.styleLabel(usersTexto);
		JSeparator usersSeparator = new JSeparator();
		Interface.styleSeparator(usersSeparator);
		usersBtnHome = new JButton("Home");
		Interface.styleBotaoHome(usersBtnHome,HOME );
		userSearch = new JTextField();
		Interface.styleSearch(userSearch);
		
		GroupLayout glUsersPanel = putUserLayout(usersTexto, usersScrollPane, usersSeparator);
		modelUser = usersClass.getModelUser();
		usersTable = usersClass.getUsersTable();
		usersScrollPane.setViewportView(usersTable);
		usersPanel.setLayout(glUsersPanel);
		criaUserSearch();
		db.nifusernameadminLogin(modelUser);

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
	/* CATEGORIA PRODUTO MENU - SECUNDARIO */
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

		categoriaProdutoBtnAdicionar = new JButton("Adicionar");
		Interface.styleBotaoSimples(categoriaProdutoBtnAdicionar, ADD);

		categoriaProdutoBtnRemover = new JButton(REMOVERSTRING);
		Interface.styleBotaoSimples(categoriaProdutoBtnRemover, REMOVE);

		categoriaProdutoScrollPane2 = new JScrollPane();
		categoriaProdutoScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		categoriaProdutoBtnRefresh = new JButton(REFRESHSTRING);
		Interface.styleBotaoSimples(categoriaProdutoBtnRefresh, REFRESH);

		categoriaProdutoBtnHome = new JButton("Home");
		Interface.styleBotaoHome(categoriaProdutoBtnHome, HOME);
		
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

	/* FORNECEDORES MENU - SECUNDARIO */
	
	private void showFornecedoresMenu() {
		fornecedores = new JPanel();
		frmMenuAdmin.getContentPane().add(fornecedores, "name_1323749306915900");
		fornecedores.setLayout(new BorderLayout(0, 0));

		JPanel fornecedoresPanel = new JPanel();
		fornecedores.add(fornecedoresPanel, BorderLayout.CENTER);

		JLabel fornecedoresTexto = new JLabel("Fornecedores");
		fornecedoresTexto.setFont(new Font("HP Simplified", Font.BOLD, 38));

		JSeparator fornecedoresdestinosSeparator = new JSeparator();
		fornecedoresdestinosSeparator.setPreferredSize(new Dimension(0, 50));
		fornecedoresdestinosSeparator.setMinimumSize(new Dimension(20, 20));
		fornecedoresdestinosSeparator.setForeground(Color.BLUE);
		fornecedoresdestinosSeparator.setFont(new Font("Dialog", Font.BOLD, 15));

		JScrollPane fornecedoresScrollPane = new JScrollPane();
		fornecedoresScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		fornecedoresBtnAdicionar = new JButton("Adicionar");
		fornecedoresBtnAdicionar.setIcon(new ImageIcon(AdminDesign.class.getResource(ADD)));
		fornecedoresBtnAdicionar.setFont(new Font("Consolas", Font.PLAIN, 12));

		fornecedoresBtnEditar = new JButton(EDITARSTRING);
		fornecedoresBtnEditar.setIcon(new ImageIcon(AdminDesign.class.getResource(EDIT)));
		fornecedoresBtnEditar.setFont(new Font("Consolas", Font.PLAIN, 12));

		fornecedoresBtnRemover = new JButton(REMOVERSTRING);
		fornecedoresBtnRemover.setIcon(new ImageIcon(AdminDesign.class.getResource(REMOVE)));
		fornecedoresBtnRemover.setFont(new Font("Consolas", Font.PLAIN, 12));

		fornecedoresBtnRefresh = new JButton(REFRESHSTRING);
		fornecedoresBtnRefresh.setIcon(new ImageIcon(AdminDesign.class.getResource(REFRESH)));
		fornecedoresBtnRefresh.setFont(new Font("Consolas", Font.PLAIN, 12));

		fornecedoresBtnHome = new JButton("Home");
		fornecedoresBtnHome.setIcon(new ImageIcon(AdminDesign.class.getResource(HOME)));
		fornecedoresBtnHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		fornecedoresBtnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		fornecedoresBtnHome.setFont(new Font("Consolas", Font.BOLD, 12));

		fornecedorSearch = new JTextField();
		fornecedorSearch.setToolTipText("Quick Access");
		fornecedorSearch.setText("Quick Access");
		fornecedorSearch.setColumns(10);
		GroupLayout glFornecedoresPanel = new GroupLayout(fornecedoresPanel);
		glFornecedoresPanel.setHorizontalGroup(glFornecedoresPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glFornecedoresPanel.createSequentialGroup().addGap(37).addGroup(glFornecedoresPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(glFornecedoresPanel.createSequentialGroup().addGroup(glFornecedoresPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(glFornecedoresPanel
										.createSequentialGroup().addGap(30).addGroup(glFornecedoresPanel
												.createParallelGroup(Alignment.LEADING)
												.addComponent(fornecedoresBtnRefresh, GroupLayout.PREFERRED_SIZE, 113,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(fornecedoresBtnHome, GroupLayout.PREFERRED_SIZE, 113,
														GroupLayout.PREFERRED_SIZE))
										.addGap(85)
										.addGroup(glFornecedoresPanel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(glFornecedoresPanel.createSequentialGroup()
														.addComponent(fornecedoresBtnAdicionar).addGap(18)
														.addComponent(fornecedoresBtnEditar, GroupLayout.PREFERRED_SIZE,
																105, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(fornecedoresBtnRemover,
																GroupLayout.PREFERRED_SIZE, 113,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(fornecedoresScrollPane, 0, 0, Short.MAX_VALUE))
										.addGap(6).addComponent(fornecedorSearch, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(fornecedoresTexto, GroupLayout.PREFERRED_SIZE, 261,
										GroupLayout.PREFERRED_SIZE))
								.addGap(383))
						.addComponent(fornecedoresdestinosSeparator, GroupLayout.PREFERRED_SIZE, 222,
								GroupLayout.PREFERRED_SIZE))));
		glFornecedoresPanel.setVerticalGroup(glFornecedoresPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(glFornecedoresPanel.createSequentialGroup().addContainerGap()
						.addComponent(fornecedoresTexto, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(fornecedoresdestinosSeparator, GroupLayout.PREFERRED_SIZE, 15,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
						.addGroup(glFornecedoresPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(glFornecedoresPanel.createSequentialGroup()
										.addComponent(fornecedoresBtnRefresh, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(fornecedoresBtnHome, GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE)
										.addGap(64))
								.addGroup(glFornecedoresPanel.createSequentialGroup()
										.addGroup(glFornecedoresPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(fornecedoresBtnAdicionar, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(fornecedoresBtnEditar, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(fornecedoresBtnRemover, GroupLayout.PREFERRED_SIZE, 40,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(glFornecedoresPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(fornecedoresScrollPane, GroupLayout.PREFERRED_SIZE, 170,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(fornecedorSearch, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(34)))));
		modelFornecedor = new DefaultTableModel(new Object[][] {}, new String[] { "Nome" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		fornecedorTable = new JTable();
		fornecedorTable.setSelectionForeground(Color.BLACK);
		fornecedoresScrollPane.setViewportView(fornecedorTable);
		fornecedoresPanel.setLayout(glFornecedoresPanel);
		fornecedorTable.setModel(modelFornecedor);

		/* Para nao mover */
		fornecedorTable.getTableHeader().setReorderingAllowed(false);
		fornecedorTable.setAutoCreateRowSorter(true);
		fornecedorTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nomeFornecedor(modelFornecedor);

		sorterFornecedor = new TableRowSorter<>(modelFornecedor);
		fornecedorTable.setRowSorter(sorterFornecedor);

		fornecedorSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fornecedorSearch.setText("");
			}
		});
		fornecedorSearch.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search(fornecedorSearch.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search(fornecedorSearch.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search(fornecedorSearch.getText());
			}

			public void search(String str) {
				if (str.length() == 0) {
					sorterFornecedor.setRowFilter(null);
				} else {
					sorterFornecedor.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});

	}

	/* MAQUINA MENU - SECUNDARIO */
	private void showMaquinaMenu() {
		maquina = new JPanel();
		frmMenuAdmin.getContentPane().add(maquina, "name_1323854438390700");
		maquina.setLayout(new BorderLayout(0, 0));

		JPanel maquinaPanel = new JPanel();
		maquina.add(maquinaPanel, BorderLayout.NORTH);

		maquinaBtnRefresh = new JButton(REFRESHSTRING);
		maquinaBtnRefresh.setIcon(new ImageIcon(AdminDesign.class.getResource(REFRESH)));

		maquinaBtnRefresh.setFont(new Font("Consolas", Font.PLAIN, 12));

		maquinaBtnHome = new JButton("Home");

		maquinaBtnHome.setIcon(new ImageIcon(AdminDesign.class.getResource(HOME)));
		maquinaBtnHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		maquinaBtnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		maquinaBtnHome.setFont(new Font("Consolas", Font.BOLD, 12));

		maquinaBtnAdicionar = new JButton("Adicionar");
		maquinaBtnAdicionar.setIcon(new ImageIcon(AdminDesign.class.getResource(ADD)));
		maquinaBtnAdicionar.setFont(new Font("Consolas", Font.PLAIN, 12));

		maquinaBtnEditar = new JButton(EDITARSTRING);
		maquinaBtnEditar.setIcon(new ImageIcon(AdminDesign.class.getResource(EDIT)));
		maquinaBtnEditar.setFont(new Font("Consolas", Font.PLAIN, 12));

		maquinaBtnRemover = new JButton(REMOVERSTRING);
		maquinaBtnRemover.setIcon(new ImageIcon(AdminDesign.class.getResource(REMOVE)));
		maquinaBtnRemover.setFont(new Font("Consolas", Font.PLAIN, 12));

		JScrollPane maquinaScrollPane = new JScrollPane();
		maquinaScrollPane.setBorder(new LineBorder(Color.BLACK));
		maquinaScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JSeparator maquinaSeparator = new JSeparator();
		maquinaSeparator.setPreferredSize(new Dimension(0, 50));
		maquinaSeparator.setMinimumSize(new Dimension(20, 20));
		maquinaSeparator.setForeground(Color.BLUE);
		maquinaSeparator.setFont(new Font("Dialog", Font.BOLD, 15));

		JLabel maquinaTexto = new JLabel("Maquinas");
		maquinaTexto.setFont(new Font("HP Simplified", Font.BOLD, 38));

		maquinaSearch = new JTextField();
		maquinaSearch.setColumns(10);
		maquinaSearch.setText("Quick Access");
		maquinaSearch.setToolTipText("Quick Access");

		GroupLayout glMaquinaPanel = new GroupLayout(maquinaPanel);
		glMaquinaPanel.setHorizontalGroup(glMaquinaPanel.createParallelGroup(Alignment.TRAILING).addGroup(glMaquinaPanel
				.createSequentialGroup().addGap(37)
				.addGroup(glMaquinaPanel.createParallelGroup(Alignment.LEADING).addGroup(glMaquinaPanel
						.createSequentialGroup().addGap(30)
						.addGroup(
								glMaquinaPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(maquinaBtnRefresh, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(
												maquinaBtnHome, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE))
						.addGap(85)
						.addGroup(glMaquinaPanel.createParallelGroup(Alignment.TRAILING, false).addGroup(
								Alignment.LEADING,
								glMaquinaPanel.createSequentialGroup().addComponent(maquinaBtnAdicionar).addGap(18)
										.addComponent(maquinaBtnEditar, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(maquinaBtnRemover, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(maquinaSearch, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(maquinaScrollPane, GroupLayout.PREFERRED_SIZE, 544,
										GroupLayout.PREFERRED_SIZE)))
						.addComponent(maquinaTexto, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
						.addComponent(maquinaSeparator, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
				.addGap(193)));
		glMaquinaPanel.setVerticalGroup(glMaquinaPanel.createParallelGroup(Alignment.LEADING).addGroup(glMaquinaPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(glMaquinaPanel.createParallelGroup(Alignment.TRAILING).addGroup(glMaquinaPanel
						.createSequentialGroup()
						.addComponent(maquinaTexto, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(maquinaSeparator, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
						.addComponent(maquinaBtnRefresh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(maquinaBtnHome, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addGap(64))
						.addGroup(glMaquinaPanel.createSequentialGroup().addGroup(glMaquinaPanel
								.createParallelGroup(Alignment.TRAILING)
								.addComponent(maquinaSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(glMaquinaPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(maquinaBtnAdicionar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(maquinaBtnEditar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(maquinaBtnRemover, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(maquinaScrollPane,
										GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(34)))));
		modelMaquina = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Numero Serie" }) {

			private static final long serialVersionUID = 1880689174093893276L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		maquinaTable = new JTable();
		maquinaTable.setModel(modelMaquina);
		maquinaScrollPane.setViewportView(maquinaTable);
		maquinaPanel.setLayout(glMaquinaPanel);
		/* Para nao mover */
		maquinaTable.getTableHeader().setReorderingAllowed(false);
		maquinaTable.setAutoCreateRowSorter(true);// para ordenar
		maquinaTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nomenumeroMaquina(nomeArmazem, modelMaquina);

		sorterMaquina = new TableRowSorter<>(modelMaquina);
		maquinaTable.setRowSorter(sorterMaquina);

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
					sorterMaquina.setRowFilter(null);
				} else {
					sorterMaquina.setRowFilter(RowFilter.regexFilter("(?i)" + str));
				}
			}
		});

	}

	/* BASE DE DADOS MENU - SECUNDARIO */
	private void showBaseDadosMenu() {
		baseDados = new JPanel();
		frmMenuAdmin.getContentPane().add(baseDados, "name_1323856331283900");
		baseDados.setLayout(new BorderLayout(0, 0));

		JPanel baseDadosPanel = new JPanel();
		baseDados.add(baseDadosPanel, BorderLayout.CENTER);

		JLabel baseDadosTexto = new JLabel("<html>Base de <br>Dados</html>");
		baseDadosTexto.setFont(new Font("HP Simplified", Font.BOLD, 27));

		JSeparator baseDadosSeparator = new JSeparator();
		baseDadosSeparator.setPreferredSize(new Dimension(0, 50));
		baseDadosSeparator.setMinimumSize(new Dimension(20, 20));
		baseDadosSeparator.setForeground(Color.BLUE);
		baseDadosSeparator.setFont(new Font("Dialog", Font.BOLD, 15));

		baseDadosBtnAdicionar = new JButton("Atualizar");
		baseDadosBtnAdicionar.setIcon(new ImageIcon(AdminDesign.class.getResource(ADD)));
		baseDadosBtnAdicionar.setFont(new Font("Consolas", Font.PLAIN, 12));

		JLabel baseDadosTextoUser = new JLabel("User:");
		baseDadosTextoUser.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel baseDadosTextoPassword = new JLabel("Password:");
		baseDadosTextoPassword.setFont(new Font("Tahoma", Font.BOLD, 16));

		baseDadosUser = new JTextField();
		baseDadosUser.setText("sinf19a38");
		baseDadosUser.setToolTipText("");
		baseDadosUser.setColumns(10);

		baseDadosPassword = new JTextField();
		baseDadosPassword.setText("UbwJSLsu");
		baseDadosPassword.setColumns(10);

		baseDadosBtnhome = new JButton("Home");
		baseDadosBtnhome.setIcon(new ImageIcon(AdminDesign.class.getResource(HOME)));
		baseDadosBtnhome.setVerticalTextPosition(SwingConstants.BOTTOM);
		baseDadosBtnhome.setHorizontalTextPosition(SwingConstants.CENTER);
		baseDadosBtnhome.setFont(new Font("Consolas", Font.BOLD, 12));

		baseDadosBtnTest = new JButton("<html>Testar<br> Conex\u00E3o</html>");

		baseDadosBtnTest.setIcon(new ImageIcon(AdminDesign.class.getResource(WIFI)));
		baseDadosBtnTest.setFont(new Font("Consolas", Font.PLAIN, 12));

		baseDadosBtnCriarTabelas = new JButton("Criar Tabelas");
		baseDadosBtnCriarTabelas.setHorizontalAlignment(SwingConstants.LEFT);
		baseDadosBtnCriarTabelas.setIcon(new ImageIcon(AdminDesign.class.getResource(DB)));

		baseDadosUrl = new JTextField();
		baseDadosUrl.setText("db.fe.up.pt:5432");
		baseDadosUrl.setColumns(10);

		JLabel lblUrl = new JLabel("Url:");
		lblUrl.setFont(new Font("Tahoma", Font.BOLD, 16));
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
		baseDadosPanel.setLayout(glBaseDadosPanel);
	}

	/* FUNCIONARIO MENU - SECUNDARIO */
	private void showFuncionarioMenu() {

		funcionario = new JPanel();
		frmMenuAdmin.getContentPane().add(funcionario, "name_1832054822092700");
		funcionario.setLayout(new BorderLayout(0, 0));

		JPanel funcionarioPanel = new JPanel();
		funcionario.add(funcionarioPanel, BorderLayout.CENTER);

		JLabel funcionarioTexto = new JLabel("Funcion\u00E1rios");
		funcionarioTexto.setFont(new Font("HP Simplified", Font.BOLD, 34));

		JSeparator funcionarioSeparator = new JSeparator();
		funcionarioSeparator.setPreferredSize(new Dimension(0, 50));
		funcionarioSeparator.setMinimumSize(new Dimension(20, 20));
		funcionarioSeparator.setForeground(Color.BLUE);
		funcionarioSeparator.setFont(new Font("Dialog", Font.BOLD, 15));

		funcionarioBtnAdicionar = new JButton("Adicionar");
		funcionarioBtnAdicionar.setIcon(new ImageIcon(AdminDesign.class.getResource(ADD)));
		funcionarioBtnAdicionar.setFont(new Font("Consolas", Font.PLAIN, 12));

		funcionarioBtnEditar = new JButton(EDITARSTRING);
		funcionarioBtnEditar.setIcon(new ImageIcon(AdminDesign.class.getResource(EDIT)));
		funcionarioBtnEditar.setFont(new Font("Consolas", Font.PLAIN, 12));

		funcionarioBtnRemover = new JButton(REMOVERSTRING);
		funcionarioBtnRemover.setIcon(new ImageIcon(AdminDesign.class.getResource(REMOVE)));
		funcionarioBtnRemover.setFont(new Font("Consolas", Font.PLAIN, 12));

		JScrollPane funcionarioScrollPane = new JScrollPane();
		funcionarioScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		funcionarioScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		funcionarioBtnHome = new JButton("Home");

		funcionarioBtnHome.setIcon(new ImageIcon(AdminDesign.class.getResource(HOME)));
		funcionarioBtnHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		funcionarioBtnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		funcionarioBtnHome.setFont(new Font("Consolas", Font.BOLD, 12));

		funcionarioBtnRefresh = new JButton(REFRESHSTRING);
		funcionarioBtnRefresh.setIcon(new ImageIcon(AdminDesign.class.getResource(REFRESH)));
		funcionarioBtnRefresh.setFont(new Font("Consolas", Font.PLAIN, 12));

		funcionarioSearch = new JTextField();
		funcionarioSearch.setToolTipText("Quick Access");
		funcionarioSearch.setText("Quick Access");
		funcionarioSearch.setColumns(10);

		GroupLayout glFuncionarioPanel = new GroupLayout(funcionarioPanel);
		glFuncionarioPanel.setHorizontalGroup(glFuncionarioPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glFuncionarioPanel.createSequentialGroup().addGroup(glFuncionarioPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(glFuncionarioPanel.createSequentialGroup().addGap(20)
								.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(funcionarioTexto, GroupLayout.PREFERRED_SIZE, 239,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(funcionarioSeparator, GroupLayout.PREFERRED_SIZE, 182,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(glFuncionarioPanel.createSequentialGroup().addGap(44)
								.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(funcionarioBtnRefresh, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(funcionarioBtnHome, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, glFuncionarioPanel.createSequentialGroup()
										.addComponent(funcionarioBtnAdicionar).addGap(18)
										.addComponent(funcionarioBtnEditar, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(funcionarioBtnRemover, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE, 127,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(funcionarioScrollPane, GroupLayout.PREFERRED_SIZE, 548,
										GroupLayout.PREFERRED_SIZE))
						.addGap(42)));
		glFuncionarioPanel.setVerticalGroup(glFuncionarioPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(glFuncionarioPanel.createSequentialGroup()
						.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(glFuncionarioPanel.createSequentialGroup().addContainerGap()
										.addComponent(funcionarioTexto, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(funcionarioSeparator, GroupLayout.PREFERRED_SIZE, 15,
												GroupLayout.PREFERRED_SIZE)
										.addGap(52)
										.addComponent(funcionarioBtnRefresh, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(funcionarioBtnHome, GroupLayout.PREFERRED_SIZE, 59,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(glFuncionarioPanel.createSequentialGroup().addGap(32)
										.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(glFuncionarioPanel.createParallelGroup(Alignment.BASELINE)
														.addComponent(funcionarioBtnAdicionar,
																GroupLayout.PREFERRED_SIZE, 40,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(funcionarioBtnEditar, GroupLayout.PREFERRED_SIZE,
																40, GroupLayout.PREFERRED_SIZE)
														.addComponent(funcionarioBtnRemover, GroupLayout.PREFERRED_SIZE,
																40, GroupLayout.PREFERRED_SIZE))
												.addComponent(funcionarioSearch, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(funcionarioScrollPane,
												GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)))
						.addGap(37)));
		modelFuncionario = new DefaultTableModel(new Object[][] {}, new String[] { "NIF", "Nome" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		funcionarioTable = new JTable();
		funcionarioTable.setModel(modelFuncionario);
		funcionarioTable.getColumnModel().getColumn(0).setResizable(true);
		funcionarioTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		funcionarioTable.getColumnModel().getColumn(0).setMaxWidth(150);
		funcionarioTable.getColumnModel().getColumn(1).setResizable(true);
		funcionarioScrollPane.setViewportView(funcionarioTable);
		funcionarioPanel.setLayout(glFuncionarioPanel);

		funcionarioTable.getTableHeader().setReorderingAllowed(false);
		funcionarioTable.setAutoCreateRowSorter(true);// para ordenar
		funcionarioTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.nomeNifFuncionario(modelFuncionario, nomeArmazem);

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

	private DefaultTableModel modelLogs;
	private TableRowSorter<DefaultTableModel> sorterLogs;
	private JButton logsBtnRefresh;
	private JButton logsBtnHome;
	private JComboBox<String> numeroLinhasCombo;
	
	private void showLogsMenu() {
		logs = new JPanel();
		frmMenuAdmin.getContentPane().add(logs, "name_317578181588800");
		logs.setLayout(new BorderLayout(0, 0));

		JPanel logsPanel = new JPanel();
		logs.add(logsPanel, BorderLayout.CENTER);

		JLabel lblLogs = new JLabel("Logs");
		lblLogs.setFont(new Font("HP Simplified", Font.BOLD, 34));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		logsBtnRefresh = new JButton(REFRESHSTRING);
		logsBtnRefresh.setIcon(null);
		logsBtnRefresh.setFont(new Font("Consolas", Font.PLAIN, 12));

		logsBtnHome = new JButton("Home");
		logsBtnHome.setIcon(null);
		logsBtnHome.setVerticalTextPosition(SwingConstants.BOTTOM);
		logsBtnHome.setHorizontalTextPosition(SwingConstants.CENTER);
		logsBtnHome.setFont(new Font("Consolas", Font.BOLD, 12));

		logsSearch = new JTextField();
		logsSearch.setToolTipText("Quick Access");
		logsSearch.setText("Quick Access");
		logsSearch.setColumns(10);

		JSeparator separator_2 = new JSeparator();
		separator_2.setPreferredSize(new Dimension(0, 50));
		separator_2.setMinimumSize(new Dimension(20, 20));
		separator_2.setForeground(Color.BLUE);
		separator_2.setFont(new Font("Dialog", Font.BOLD, 15));
		
		numeroLinhasCombo = new JComboBox<String>();
		
		numeroLinhasCombo.setToolTipText("Numero de linhas");
		numeroLinhasCombo.setFont(new Font("Consolas", Font.PLAIN, 14));
		numeroLinhasCombo.setModel(new DefaultComboBoxModel<String>(new String[] {"25", "50", "100", "200", "400"}));
		GroupLayout gl_logsPanel = new GroupLayout(logsPanel);
		gl_logsPanel.setHorizontalGroup(
			gl_logsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_logsPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_logsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLogs, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(logsBtnRefresh, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(logsBtnHome, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_logsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_logsPanel.createSequentialGroup()
							.addGap(339)
							.addComponent(numeroLinhasCombo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(logsSearch, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_logsPanel.setVerticalGroup(
			gl_logsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_logsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_logsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_logsPanel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_logsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(logsSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(numeroLinhasCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_logsPanel.createSequentialGroup()
							.addComponent(lblLogs, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_logsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_logsPanel.createSequentialGroup()
							.addGap(35)
							.addComponent(logsBtnRefresh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(logsBtnHome, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
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

		JTable logsTable = new JTable();
		logsTable.setModel(modelLogs);
		logsTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(logsTable);
		logsPanel.setLayout(gl_logsPanel);
		
		/* Para nao mover */
		logsTable.getTableHeader().setReorderingAllowed(false);
		logsTable.setAutoCreateRowSorter(true);// para ordenar
		logsTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		db.getLogs(modelLogs, numeroLinhasCombo.getSelectedItem().toString());
		
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

	/* BARRA MENU */
	private void showMenuBar() {
		JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setBackground(Color.WHITE);
		frmMenuAdmin.setJMenuBar(menuBar1);

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

		menuBarArmazem = new JMenu("");
		menuBarArmazem.setMaximumSize(new Dimension(50, 50));
		menuBarArmazem.setMargin(new Insets(0, 0, 0, 0));

		menuBarArmazem.setHorizontalAlignment(SwingConstants.LEFT);
		menuBarArmazem.setBackground(Color.WHITE);
		menuBar1.add(menuBarArmazem);
		menuBarArmazem.setIcon(new ImageIcon(AdminDesign.class.getResource(MORE)));

		menuMudarNomeArmazem = new JMenuItem("Mudar informação");

		menuMudarNomeArmazem.setIcon(new ImageIcon(AdminDesign.class.getResource(CHANGE)));
		menuBarArmazem.add(menuMudarNomeArmazem);

		JLabel label = new JLabel("       ");
		menuBar1.add(label);
		label.setBackground(Color.WHITE);

		backgroundTimer();

		counterTimer.start();

	}

	private boolean first = true;
	private JPanel logs;
	private JTextField logsSearch;

	/* TEMPO COMO BACKGROUND */
	private void backgroundTimer() {
		counterTimer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (db.connect()) {

					lblStatusDb.setText("ON");
					lblStatusDb.setForeground(Color.GREEN);
					db.disconnect();
					first = true;
				} else {

					lblStatusDb.setText("OFF");
					lblStatusDb.setForeground(Color.RED);
					if (first) {
						first = false;
						int option = popUp.showPopUpDataBaseError();
						if (option == JOptionPane.NO_OPTION) {
							cl.show(frmMenuAdmin.getContentPane(), "base_dados");
						}
					}
				}

			}

		});

	}

	private void buttonsMenu() {
		mntmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;

			}
		});
		mntmExit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				counterTimer.stop();
				messageLogs.saiuSistema(username, true, nomeArmazem);
				LoginDesign window2 = new LoginDesign();
				window2.getFrmLogin().setVisible(true);
				frmMenuAdmin.dispose();

			}
		});
		menuWebsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Desktop desktop = java.awt.Desktop.getDesktop();
					URI oURL = new URI("https://up201504257.github.io/");
					desktop.browse(oURL);
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		menuBarArmazem.addMenuListener(new MenuListener() {
			public void menuDeselected(MenuEvent arg0) {
				menuBarArmazem.setIcon(new ImageIcon(AdminDesign.class.getResource(MORE)));

			}

			public void menuSelected(MenuEvent arg0) {
				menuBarArmazem.setIcon(new ImageIcon(AdminDesign.class.getResource(MORE1)));

			}

			@Override
			public void menuCanceled(MenuEvent e) {

			}
		});
		menuMudarNomeArmazem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String localizacao = db.localizacaoArmazem(nomeArmazem);
				String[] x = armazemClass.editarArmazem(nomeArmazem, localizacao);
				if (x[0] != null) {
					nomeArmazem = x[0];
					lblArmazem.setText(nomeArmazem);
				}

			}
		});
		menuHelpMe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				help.selectHelp(frmMenuAdmin, currentPanel);
			}
		});
	}
	
	private void buttonsMainMenu() {

		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "user");
				currentPanel = "user";
			}
		});
		btnCategoriaProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "categoria_produto");
				currentPanel = "categoria_produto";
			}
		});
		btnFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "fornecedores");
				currentPanel = "fornecedores";

			}
		});
		btnMaquinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "maquina");
				currentPanel = "maquina";

			}
		});
		btnDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "base_dados");
				currentPanel = "base_dados";

			}
		});
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cl.show(frmMenuAdmin.getContentPane(), "funcionario");
				currentPanel = "funcionario";

			}
		});
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), "logs");
				currentPanel = "logs";

			}
		});
		
	}
	
	private void buttonsUser() {
		usersBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;

			}
		});
		usersBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersClass.adicionarUser(nomeArmazem);

			}
		});
		usersBtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersClass.editarUser( nomeArmazem);
			}
		});
		usersBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersClass.refresh();
			}
		});
		usersBtnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersClass.removeUser(nomeArmazem);

			}
		});
		usersBtnRemover.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					usersClass.removeUser(nomeArmazem);

			}
		});

	}

	private void buttonsCategoria() {
		categoriaProdutoBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;
			}
		});
		categoriaProdutoBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriaProdutoClass.adicionarCategoria(modelCategoriaProduto, modelSubCategoriaProduto, nomeArmazem);
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
						modelCategoriaProduto, modelSubCategoriaProduto, nomeArmazem);
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
							modelCategoriaProduto, modelSubCategoriaProduto, nomeArmazem);

			}
		});
		subCategoriaProdutoTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					categoriaProdutoClass.removeCategoria(categoriaProdutoTable, subCategoriaProdutoTable,
							modelCategoriaProduto, modelSubCategoriaProduto, nomeArmazem);

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
	
	private void buttonsFornecedor() {
		fornecedoresBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;

			}
		});
		fornecedoresBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedorClass.adicionarFornecedor(modelFornecedor, nomeArmazem);
			}
		});
		fornecedoresBtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedorClass.editarFornecedor(fornecedorTable, modelFornecedor, nomeArmazem);
			}
		});
		fornecedoresBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedorClass.refresh(modelFornecedor);
			}
		});
		fornecedoresBtnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedorClass.removeFornecedor(fornecedorTable, modelFornecedor, nomeArmazem);
			}
		});

		fornecedorTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					fornecedorClass.removeFornecedor(fornecedorTable, modelFornecedor, nomeArmazem);

			}
		});
		/* Double click num elemento */
		fornecedorTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println("Clicou no  " + modelFornecedor.getValueAt(table.getSelectedRow(), 0));
				}
			}
		});
	}
	
	private void buttonsMaquina() {
		maquinaBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;

			}
		});
		maquinaBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.adicionarMaquina(modelMaquina, nomeArmazem);
			}
		});
		maquinaBtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.editarMaquina(maquinaTable, modelMaquina, nomeArmazem);
			}
		});
		maquinaBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.refresh(modelMaquina, nomeArmazem);
			}
		});
		maquinaBtnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaClass.removeMaquina(maquinaTable, modelMaquina, nomeArmazem);
			}
		});
		maquinaTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					maquinaClass.removeMaquina(maquinaTable, modelMaquina, nomeArmazem);

			}
		});
		/* Double click num elemento */
		maquinaTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println("Clicou no  " + modelMaquina.getValueAt(table.getSelectedRow(), 0));
				}
			}
		});
	}
	
	private void buttonsFuncionarios() {
		funcionarioBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;

			}
		});
		funcionarioBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.adicionarFuncionario(modelFuncionario, nomeArmazem);
			}
		});
		funcionarioBtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.editarFuncionario(funcionarioTable, modelFuncionario);
			}
		});
		funcionarioBtnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.refresh(modelFuncionario, nomeArmazem);
			}
		});
		funcionarioBtnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionarioClass.removeFuncionario(funcionarioTable, modelFuncionario, nomeArmazem);
			}
		});
		funcionarioTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DELETE)
					funcionarioClass.removeFuncionario(funcionarioTable, modelFuncionario, nomeArmazem);

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
				currentPanel = MENUADMINSTRING;

			}
		});
		baseDadosBtnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baseDadosClass.adicionar(baseDadosUser.getText(), baseDadosPassword.getText(), baseDadosUrl.getText(),
						nomeArmazem);
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
				baseDadosClass.testDataBase(lblStatusDb);

			}
		});
	}
	
	private void buttonsLogs() {
		logsBtnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(frmMenuAdmin.getContentPane(), MENUADMINSTRING);
				currentPanel = MENUADMINSTRING;

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
		buttonsMenu();
		buttonsMainMenu();
		//botoes secundarios
		buttonsUser();
		buttonsCategoria();
		buttonsFornecedor();
		buttonsMaquina();
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
					messageLogs.saiuSistema(username, true, nomeArmazem);
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

		showUserMenu();// USER MENU - SECUNDARIO

		showCategoriaProduto();// CATEGORIA PRODUTO MENU - SECUNDARIO

		showFornecedoresMenu();// FORNECEDORES MENU - SECUNDARIO

		showMaquinaMenu();// MAQUINA MENU - SECUNDARIO

		showBaseDadosMenu();// BASE DE DADOS MENU - SECUNDARIO

		showFuncionarioMenu();// FUNCIONARIO MENU - SECUNDARIO

		showLogsMenu();

		putPanels();// COLOCAR OS PANELS PARA MANIPULAR

		showMenuBar();// MENU BAR

		buttons();// LISTENERS DOS BOTÕES

	}

	public JFrame getFrmMenuAdmin() {
		return frmMenuAdmin;
	}
}
