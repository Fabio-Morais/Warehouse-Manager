package guiLogin;

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
import guiAdmin.AdminDesign;
import guiUser.userDesign;
import logic.MessageLogs;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import gui.PopUp;
import gui.StyledButtonUI;
import java.awt.Cursor;
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

	private String loginUsername;

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
		loadingIcon.setIcon(new ImageIcon(AdminDesign.class.getResource("/radio.gif")));

		SwingWorker<Boolean, String> worker = new SwingWorker<Boolean, String>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				int option = -1;
				if (db.checkConnection()) {
					option = db.checkUserLogin(loginUsername, new String(password.getPassword()));
					String nomeArmazem = db.getUserarmazemLogin(username.getText());
					if (option == 0) {

						counterTimer.stop();
						messageLogs.entrouSistema(loginUsername, true, nomeArmazem);
						AdminDesign window2 = new AdminDesign(nomeArmazem, loginUsername);
						window2.getFrmMenuAdmin().setVisible(true);
						frame.dispose();
					} else if (option == 1) {

						counterTimer.stop();
						messageLogs.entrouSistema(loginUsername, false, nomeArmazem);
						userDesign window2 = new userDesign(nomeArmazem, loginUsername);
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

	private Timer counterTimer;
	private boolean isConnected;
	private boolean first = false;
	private JLabel lblConectado;
	private JLabel label_2;

	/* TEMPO COMO BACKGROUND */
	private void backgroundTimer() {
		counterTimer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*Set<Thread> threads = Thread.getAllStackTraces().keySet();
				 System.out.println("\n\n\n\n");
				for (Thread t : threads) {
				    String name = t.getName();
				    Thread.State state = t.getState();
				    int priority = t.getPriority();
				    String type = t.isDaemon() ? "Daemon" : "Normal";
				    System.out.printf("%-20s \t %s \t %d \t %s\n", name, state, priority, type);
				}	*/		
				
				if (db.connect()) {
					lblConectado.setText("Conectado");
					label_2.setIcon(new ImageIcon(AdminDesign.class.getResource(ON)));
					db.disconnect();
					isConnected = true;
					first = true;
				} else {
					lblConectado.setText("Desconectado");
					label_2.setIcon(new ImageIcon(AdminDesign.class.getResource(OFF)));
					isConnected = false;
					if (first) {
						first = false;
						popUp.showPopUpDataBaseError2();

					}
				}
			}

		});
	}

	private JLabel loadingIcon;

	/**
	 * Initialize the contents of the frame.
	 */
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

		JPanel Painel_Superior = new JPanel();
		frame.getContentPane().add(Painel_Superior, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("");
		lblTitulo.setIcon(new ImageIcon(AdminDesign.class.getResource(LOGO)));

		lblConectado = new JLabel(" ");
		lblConectado.setFont(new Font("Consolas", Font.BOLD, 12));

		label_2 = new JLabel("");
		label_2.setIcon(null);
		GroupLayout gl_Painel_Superior = new GroupLayout(Painel_Superior);
		gl_Painel_Superior.setHorizontalGroup(gl_Painel_Superior.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Painel_Superior.createSequentialGroup().addContainerGap(20, Short.MAX_VALUE)
						.addGroup(gl_Painel_Superior.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING,
										gl_Painel_Superior.createSequentialGroup().addComponent(lblConectado)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_2)))
						.addContainerGap()));
		gl_Painel_Superior.setVerticalGroup(gl_Painel_Superior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Painel_Superior.createSequentialGroup().addContainerGap()
						.addGroup(gl_Painel_Superior.createParallelGroup(Alignment.TRAILING).addComponent(lblConectado)
								.addComponent(label_2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 46, Short.MAX_VALUE).addGap(17)));
		Painel_Superior.setLayout(gl_Painel_Superior);

		JPanel Painel_Principal = new JPanel();

		frame.getContentPane().add(Painel_Principal, BorderLayout.CENTER);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 15));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 15));

		username = new JTextField();
		username.setColumns(10);

		password = new JPasswordField();

		JToggleButton btnLogin = new JToggleButton("Login");
		btnLogin.setMaximumSize(new Dimension(36, 23));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 18));
		btnLogin.setBackground(new Color(0x2dce98));
		btnLogin.setForeground(Color.white);
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
		btnLogin.setUI(new StyledButtonUI());

		JButton btnRecuperarPasswod = new JButton("Recuperar Passwod");
		btnRecuperarPasswod.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperarPasswod.setForeground(new Color(12, 51, 243));
		btnRecuperarPasswod.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRecuperarPasswod.setOpaque(false);
		btnRecuperarPasswod.setContentAreaFilled(false);
		btnRecuperarPasswod.setBorderPainted(false);
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminDesign.class.getResource(PASS)));

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AdminDesign.class.getResource(USER)));

		loadingIcon = new JLabel("");
		loadingIcon.setIcon(null);
		GroupLayout gl_Painel_Principal = new GroupLayout(Painel_Principal);
		gl_Painel_Principal.setHorizontalGroup(gl_Painel_Principal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Painel_Principal.createSequentialGroup().addGroup(gl_Painel_Principal
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING,
								gl_Painel_Principal.createSequentialGroup().addGap(153)
										.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(loadingIcon, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
						.addGroup(gl_Painel_Principal.createSequentialGroup().addContainerGap(75, Short.MAX_VALUE)
								.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_Painel_Principal.createSequentialGroup()
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 24,
														GroupLayout.PREFERRED_SIZE)
												.addGap(7))
										.addGroup(gl_Painel_Principal.createSequentialGroup().addComponent(label)
												.addGap(8)))
								.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 85,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 77,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnRecuperarPasswod, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(password).addComponent(username))))
						.addGap(73)));
		gl_Painel_Principal.setVerticalGroup(gl_Painel_Principal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Painel_Principal.createSequentialGroup().addGap(21)
						.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1).addComponent(username, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.BASELINE)
										.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword))
								.addComponent(label))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRecuperarPasswod, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_Painel_Principal.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Painel_Principal.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
										.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addGap(21))
								.addGroup(gl_Painel_Principal.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(loadingIcon,
												GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));
		Painel_Principal.setLayout(gl_Painel_Principal);

		/*************************************************************/

		/* Botão LOGIN */
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginControl();
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
