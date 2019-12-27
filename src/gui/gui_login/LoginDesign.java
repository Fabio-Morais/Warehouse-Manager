package gui.gui_login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import db.DataBase;
import gui.gui_admin.Admin;
import gui.gui_user.UserDesign;
import logic.MessageLogs;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import gui.DefaultDesign;
import gui.PopUp;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginDesign {
	private static final String USER = "/user1.png";
	private static final String PASS = "/lock.png";
	private static final String LOGO = "/logoText.png";
	private static final String OFF = "/off.png";
	private static final String ON = "/on.png";

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private DataBase db;
	private MessageLogs messageLogs;
	private PopUp popUp;
	private JPanel painelSuperior;
	private JLabel lblTitulo;
	private JPanel painelPrincipal;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JToggleButton btnLogin;
	private JButton btnRecuperarPasswod ;
	private JLabel label;
	private JLabel label_1;
	private String loginUsername;
	private Timer counterTimer;
	private boolean first = false;
	private JLabel lblConectado;
	private JLabel label_2;

	private JLabel loadingIcon;


	/**
	 * Create the application.
	 */
	public LoginDesign() {
		initialize();
		this.popUp = new PopUp();
		backgroundTimer();
		counterTimer.start();
	}

	private void loginControl() {

		this.loginUsername = username.getText();
		loadingIcon.setIcon(new ImageIcon(Admin.class.getResource("/radio.gif")));

		SwingWorker<Boolean, String> worker = new SwingWorker<Boolean, String>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				int option = -1;
				if (db.checkConnection()) {
					option = db.checkUserLogin(loginUsername, new String(password.getPassword()));
					String nomeArmazem = db.getUserarmazemLogin(username.getText());
					if (option == 0) {

						counterTimer.stop();
						messageLogs.entrouSistema(loginUsername+";"+true+";"+nomeArmazem);
						Admin window2 = new Admin(nomeArmazem, loginUsername);
						window2.getFrmMenuAdmin().setVisible(true);
						frame.dispose();
					} else if (option == 1) {

						counterTimer.stop();
						messageLogs.entrouSistema(loginUsername+";"+false+";"+nomeArmazem);
						UserDesign window2 = new UserDesign(nomeArmazem, loginUsername);
						window2.getFrmUserDesign().setVisible(true);
						frame.dispose();
					} else {
						loadingIcon.setIcon(null);
						JOptionPane.showMessageDialog(frame, "Username ou password errada", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					loadingIcon.setIcon(null);
					popUp.showPopUpDataBaseError2();

				}
				return true;
			}

			@Override
			protected void done() {
				cancel(true);
			}
		};
		worker.execute();


	}

	public String getLoginUsername() {
		return loginUsername;
	}

	/* TEMPO COMO BACKGROUND */
	private void backgroundTimer() {
		counterTimer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (db.connect()) {
					lblConectado.setText("Conectado");
					label_2.setIcon(new ImageIcon(Admin.class.getResource(ON)));
					db.disconnect();
					first = true;
				} else {
					lblConectado.setText("Desconectado");
					label_2.setIcon(new ImageIcon(Admin.class.getResource(OFF)));
					if (first) {
						first = false;
						popUp.showPopUpDataBaseError2();

					}
				}
			}

		});
	}

	private void designNorth() {
		painelSuperior = new JPanel();
		frame.getContentPane().add(painelSuperior, BorderLayout.NORTH);

		lblTitulo = new JLabel("");
		lblTitulo.setIcon(new ImageIcon(Admin.class.getResource(LOGO)));

		lblConectado = new JLabel(" ");
		lblConectado.setFont(new Font("Consolas", Font.BOLD, 12));

		label_2 = new JLabel("");
		label_2.setIcon(null);
	}
	private void designCenter() {
		painelPrincipal = new JPanel();

		frame.getContentPane().add(painelPrincipal, BorderLayout.CENTER);

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 15));

		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 15));

		username = new JTextField();
		username.setColumns(10);

		password = new JPasswordField();

		btnLogin = new JToggleButton("Login");
		DefaultDesign.styleBotaoLogin(btnLogin);
	
		btnRecuperarPasswod = new JButton("Recuperar Passwod");
		DefaultDesign.styleBotaoRecuPass(btnRecuperarPasswod);

		label = new JLabel("");
		label.setIcon(new ImageIcon(Admin.class.getResource(PASS)));

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Admin.class.getResource(USER)));

		loadingIcon = new JLabel("");
		loadingIcon.setIcon(null);
	}
	private void putLayoutNorth() {
		GroupLayout glPainelSuperior = new GroupLayout(painelSuperior);
		glPainelSuperior.setHorizontalGroup(glPainelSuperior.createParallelGroup(Alignment.TRAILING)
				.addGroup(glPainelSuperior.createSequentialGroup().addContainerGap(20, Short.MAX_VALUE)
						.addGroup(glPainelSuperior.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING,
										glPainelSuperior.createSequentialGroup().addComponent(lblConectado)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_2)))
						.addContainerGap()));
		glPainelSuperior.setVerticalGroup(glPainelSuperior.createParallelGroup(Alignment.LEADING)
				.addGroup(glPainelSuperior.createSequentialGroup().addContainerGap()
						.addGroup(glPainelSuperior.createParallelGroup(Alignment.TRAILING).addComponent(lblConectado)
								.addComponent(label_2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE).addGap(17)));
		painelSuperior.setLayout(glPainelSuperior);
	}
	private void putLayout() {
		GroupLayout glPainelPrincipal = new GroupLayout(painelPrincipal);
		glPainelPrincipal.setHorizontalGroup(glPainelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(glPainelPrincipal.createSequentialGroup().addGroup(glPainelPrincipal
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								glPainelPrincipal.createSequentialGroup().addGap(153)
										.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(loadingIcon, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
						.addGroup(glPainelPrincipal.createSequentialGroup().addContainerGap(75, Short.MAX_VALUE)
								.addGroup(glPainelPrincipal.createParallelGroup(Alignment.TRAILING)
										.addGroup(glPainelPrincipal.createSequentialGroup()
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7))
										.addGroup(glPainelPrincipal.createSequentialGroup().addComponent(label)
												.addGap(8)))
								.addGroup(glPainelPrincipal.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 85,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 77,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(glPainelPrincipal.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnRecuperarPasswod, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(password).addComponent(username))))
						.addGap(73)));
		glPainelPrincipal.setVerticalGroup(glPainelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(glPainelPrincipal.createSequentialGroup().addGap(21)
						.addGroup(glPainelPrincipal.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1).addComponent(username, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(glPainelPrincipal.createParallelGroup(Alignment.TRAILING)
								.addGroup(glPainelPrincipal.createParallelGroup(Alignment.BASELINE)
										.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword))
								.addComponent(label))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRecuperarPasswod, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(glPainelPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(glPainelPrincipal.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
										.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(21))
								.addGroup(glPainelPrincipal.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(loadingIcon,
												GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));
		painelPrincipal.setLayout(glPainelPrincipal);
	}
	private void initialize() {
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		frame = new JFrame("Warehouse Manager");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja sair do programa?",
						"Exit", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
			}
		});
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 313);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		URL iconURL = getClass().getResource("/logo.png");
		ImageIcon img = new ImageIcon(iconURL);
		frame.setIconImage(img.getImage());
		
		designNorth();
		putLayoutNorth();
		designCenter();
		putLayout();
		/*************************************************************/
		buttons();
	
	

	}
	private void loginButton() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginControl();
			}

		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnLogin.setBackground(new Color(0x74CEB0));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnLogin.setBackground(new Color(0x2dce98));
			}

		});
		
	}
	private void buttons() {
		loginButton();
		btnRecuperarPasswod.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnRecuperarPasswod.setForeground(new Color(109, 131, 243));
				PasswordRecovery passwordRecoveryClass = new PasswordRecovery();

				passwordRecoveryClass.passwordRecovery();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnRecuperarPasswod.setForeground(new Color(12, 51, 243));
			}

		});		
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					loginControl();
				}
			}
		});
		username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					loginControl();
				}
			}
		});
	}
	public JFrame getFrmLogin() {
		return frame;
	}
}
