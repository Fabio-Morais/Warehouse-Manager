package gui.gui_admin;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import gui.Interface;
import gui.gui_admin.base_dados.BaseDados;
import gui.gui_admin.categoria_produto.CategoriaProduto;
import gui.gui_admin.fornecedores.Fornecedores;
import gui.gui_admin.funcionarios.Funcionarios;
import gui.gui_admin.logs.Logs;
import gui.gui_admin.maquina.Maquina;
import gui.gui_admin.users.Users;
import gui.menu_bar.MenuBar;
import logic.MessageLogs;
import javax.swing.ImageIcon;
import java.awt.Insets;


public class AdminDesign {

	private String nomeArmazem;
	private String username;

	private JFrame frmMenuAdmin;
	private static final String MENUADMINSTRING = "menu_admin";
	private static final String LOG = "/log.png";

	/* MENU */
	private static final String USERMENU = "/avatar.png";
	private static final String MACHINEMENU = "/machineMin.png";
	private static final String DBMENU = "/db.png";
	private static final String CATEGORIAMENU = "/listMin.png";
	private static final String FUNCIONARIOMENU = "/worker.png";
	private static final String FORNECEDORMENU = "/truckMin.png";

	private MessageLogs messageLogs;

	/* LOGIC CLASS */
	private Users usersClass;
	private Funcionarios funcionarioClass;
	private Fornecedores fornecedorClass;
	private Maquina maquinaClass;
	private CategoriaProduto categoriaProdutoClass;
	private BaseDados baseDadosClass;
	private Logs logsClass;

	/************/

	/* PANELS */
	private JPanel menuAdmin;
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
	
	private MenuBar menuBar;

	/*
	 * Create the application.
	 */
	public AdminDesign(String nomeArmazem, String username) {
		this.nomeArmazem = nomeArmazem;
		this.username = username;
		this.messageLogs = MessageLogs.getInstance();
		initialize();
	}

	/* Coloca os panels para conseguir mudar */
	private void putPanels() {
		frmMenuAdmin.getContentPane().add(menuAdmin, MENUADMINSTRING);
		frmMenuAdmin.getContentPane().add(usersClass.getUsers(), "user");
		frmMenuAdmin.getContentPane().add(categoriaProdutoClass.getCategoriaProduto(), "categoria_produto");
		frmMenuAdmin.getContentPane().add(fornecedorClass.getFornecedores(), "fornecedores");
		frmMenuAdmin.getContentPane().add(maquinaClass.getMaquina(), "maquina");
		frmMenuAdmin.getContentPane().add(baseDadosClass.getBaseDados(), "base_dados");
		frmMenuAdmin.getContentPane().add(funcionarioClass.getFuncionario(), "funcionario");
		frmMenuAdmin.getContentPane().add(logsClass.getLogs(), "logs");

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
	private void initializeSecund() {
		this.usersClass = new Users(username, menuBar);
		usersClass.showUserMenu(frmMenuAdmin,cl);
		this.maquinaClass = new Maquina(username, nomeArmazem, menuBar);
		maquinaClass.showMaquinaMenu(frmMenuAdmin,cl);
		this.fornecedorClass = new Fornecedores(username,menuBar);
		fornecedorClass.showFornecedoresMenu(frmMenuAdmin,cl);
		this.funcionarioClass = new Funcionarios(username, nomeArmazem, menuBar);
		funcionarioClass.showFuncionarioMenu(frmMenuAdmin, cl);
		this.categoriaProdutoClass = new CategoriaProduto(username, menuBar);
		categoriaProdutoClass.showCategoriaProduto(frmMenuAdmin, cl);
		this.baseDadosClass = new BaseDados(username, menuBar);
		baseDadosClass.showBaseDadosMenu(frmMenuAdmin, cl);
		this.logsClass = new Logs(menuBar);
		logsClass.showLogsMenu(frmMenuAdmin, cl);
	}
	private void initializeMenu() {
		menuBar = new MenuBar(frmMenuAdmin, cl, username);
		menuBar.setCurrentPanel(MENUADMINSTRING);
		menuBar.setNomeArmazem(nomeArmazem);
		menuBar.showMenuBar(0);		
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
		initializeMenu();
		initializeSecund();
		putPanels();// COLOCAR OS PANELS PARA MANIPULAR

		buttonsMainMenu();

	}

	public JFrame getFrmMenuAdmin() {
		return frmMenuAdmin;
	}
}
