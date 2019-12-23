package guiadmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.security.crypto.bcrypt.BCrypt;
import db.DataBase;
import gui.AutoCompletion;
import gui.Interface;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;

public class Users {
	private DataBase db;// DATA BASE
	private MessageLogs messageLogs;
	private static final String USER = "/user.png";
	private PopUp popUp;
	private Check check;
	
	private String loginUsername;
	private DefaultTableModel modelUser;
	private JTable usersTable;
	/* POP UP */
	private JComboBox<String> comboBox;
	private JTextField userField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JCheckBox chckbxAdmin;

	/* CONSTRUCTOR */
	public Users(String username) {
		this.popUp = new PopUp();
		this.check = new Check();
		comboBox = new JComboBox<>();
		userField = new JTextField();
		emailField = new JTextField();
		passwordField = new JPasswordField();
		passwordField2 = new JPasswordField();
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
		criaTabela();
	}

	private void criaTabela() {
		modelUser = new DefaultTableModel(new Object[][] {}, new String[] { "NIF", "Username", "Admin" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 60845133227382893L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, Boolean.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		usersTable = Interface.criaTabela(modelUser);
	}
	
	public DefaultTableModel getModelUser() {
		return modelUser;
	}
	

	public JTable getUsersTable() {
		return usersTable;
	}

	private void resetBorders() {
		comboBox.setBorder(new JTextField().getBorder());
		userField.setBorder(new JTextField().getBorder());
		emailField.setBorder(new JTextField().getBorder());
		passwordField.setBorder(new JTextField().getBorder());
		passwordField2.setBorder(new JTextField().getBorder());
		chckbxAdmin.setBorder(new JTextField().getBorder());
	}

	/*
	 * choice = 0 -> adicionar choice=1-> editar
	 */
	private boolean confirmData(int choice) {
		resetBorders();
		/* Confirma dados */
		if (choice == 0 && !check.validSelectedItem(comboBox.getSelectedItem())) {
			popUp.showPopUp("É necessario escolher o nif associado ao user", "Nif");
			comboBox.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if (check.blankText(userField.getText())) {
			popUp.showPopUp("É necessario escolher um username", "Username");
			userField.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if(!check.validEmail(emailField.getText())) {
			popUp.showPopUp("É necessario escolher um email valido", "Email");
			emailField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}else if (check.blankText(new String(passwordField.getPassword()))) {
			popUp.showPopUp("É necessario escolher uma password", "Password");
			passwordField.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if (check.blankText(new String(passwordField2.getPassword()))) {
			popUp.showPopUp("É necessario escolher uma password", "Password");
			passwordField2.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if (!check.samePassword(new String(passwordField.getPassword()),
				new String(passwordField2.getPassword()))) {
			popUp.showPopUp("As passwords precisam de ser iguais", "Password");
			passwordField.setBorder(new LineBorder(Color.red, 1));
			passwordField2.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if (!check.validPassword(new String(passwordField.getPassword()))) {
			popUp.showPopUp("As passwords precisam de ter um tamanho superior a 6 caracteres", "Password inferior a 6");
			passwordField.setBorder(new LineBorder(Color.red, 1));
			passwordField2.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		return true;

	}

	/* User POP UP Adicionar */
	private int showUserPopUpAdicionar(Object nif, String userName, String email) {
		Object[] options1 = { "Adicionar", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(USER));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 0, 0));

		JLabel lblNome = new JLabel("NIF");
		panel.add(lblNome);

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		comboBox.setModel(model);
		panel.add(comboBox);
		ArrayList<String> nifs = db.getAllNifLogin();
		for (int i = 0; i < nifs.size(); i++) {
			model.addElement(nifs.get(i));

		}
		comboBox.setSelectedItem(nif);
		AutoCompletion.enable(comboBox);
		/* adicionar aqui com a base de dados os NIFS */

		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblUser);

		panel.add(userField);
		userField.setColumns(10);
		userField.setText(userName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblEmail);

		panel.add(emailField);
		emailField.setColumns(10);
		emailField.setText(email);

		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);

		panel.add(passwordField);

		JLabel lblRepeatPassword = new JLabel("Repeat Password");
		panel.add(lblRepeatPassword);

		panel.add(passwordField2);

		JLabel lblAdmin = new JLabel("Admin");
		panel.add(lblAdmin);

		chckbxAdmin = new JCheckBox("");
		panel.add(chckbxAdmin);

		return JOptionPane.showOptionDialog(null, panel, "Adicionar user", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	/* User POP UP Editar */
	private int showUserPopUpEditar(Object nome, String userName, boolean admin) {
		Object[] options1 = { "Ok", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(USER));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 0, 0));

		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblUser);

		panel.add(userField);
		userField.setColumns(10);
		userField.setText(userName);

		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);

		panel.add(passwordField);
		passwordField.setText(null);

		JLabel lblRepeatPassword = new JLabel("Repeat Password");
		panel.add(lblRepeatPassword);

		passwordField2.setText(null);
		panel.add(passwordField2);

		JLabel lblAdmin = new JLabel("Admin");
		panel.add(lblAdmin);

		chckbxAdmin = new JCheckBox("");
		panel.add(chckbxAdmin);
		chckbxAdmin.setSelected(admin);

		return JOptionPane.showOptionDialog(null, panel, "Editar user com nif = " + nome.toString(),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	public void removeUser( String nomeArmazem) {
		int[] selectedRows = usersTable.getSelectedRows();

		if (selectedRows.length <= 0) {
			popUp.showPopUp("Precisa de selecionar pelo menos 1 linha", "Erro ao eliminar");
			return;
		}

		int option = popUp.showPopUpConfirmation(
				"Tem a certeza que deseja apagar " + selectedRows.length + " utilizadores?", "Eliminar utilizadores");
		if (option == JOptionPane.YES_OPTION) {
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				String user = modelUser.getValueAt(usersTable.convertRowIndexToModel(selectedRows[i]), 1).toString();
				if (db.removeUserLogin(user)) {
					messageLogs.removeUser(loginUsername, true, user, nomeArmazem);
					modelUser.removeRow(usersTable.convertRowIndexToModel(selectedRows[i]));
				} else {
					popUp.showPopUpErroEliminar();
					break;
				}

			}
		}

	}

	public void refresh() {
		int rowCount = modelUser.getRowCount();
		if (!db.connect()) {
			popUp.showPopUpDataBaseError();
		} else {
			db.disconnect();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelUser.removeRow(i);
			}
			db.nifusernameadminLogin(modelUser);
		}
	}

	public void editarUser(String nomeArmazem) {
		boolean isFinished = false;
		int[] indexOfRow = usersTable.getSelectedRows();
		if (check.multipleSelection(indexOfRow)) {
			popUp.showPopUp("Selecione apenas 1 linha", "Erro ao selecionar");
			return;
		}
		indexOfRow[0] = usersTable.convertRowIndexToModel(indexOfRow[0]);// converte para o indice correto (quando
																			// ordena)
		while (!isFinished) {
			int option = showUserPopUpEditar(modelUser.getValueAt(indexOfRow[0], 0).toString(),
					modelUser.getValueAt(indexOfRow[0], 1).toString(), 
					(boolean) modelUser.getValueAt(indexOfRow[0], 2));
			if (option == JOptionPane.YES_OPTION) {
				isFinished = confirmData(1);
			} else {
				resetBorders();
				break;
			}

		}
		/* editar na base de dados */
		if (isFinished) {
			String passEncript = BCrypt.hashpw(String.valueOf(passwordField.getPassword()), BCrypt.gensalt());
			String oldUser = modelUser.getValueAt(indexOfRow[0], 1).toString();
			String user = userField.getText();
			if (db.updateUserLogin(oldUser, user, passEncript,
					chckbxAdmin.isSelected())) {
				messageLogs.editaUser(loginUsername, true, oldUser, user, nomeArmazem);
				popUp.showPopUpEditarSucesso();
				modelUser.setValueAt(userField.getText(), indexOfRow[0], 1);
				modelUser.setValueAt(chckbxAdmin.isSelected(), indexOfRow[0], 2);
			} else {
				popUp.showPopUpErroEditar();
			}

		}

	}

	public void adicionarUser( String nomeArmazem) {
		boolean itsFinished = false;
		Object nif = null;
		String username = null;
		String email = null;

		while (!itsFinished) {
			int result = showUserPopUpAdicionar(nif, username, email);// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {
				itsFinished = confirmData(0);
				nif = comboBox.getSelectedItem();
				username = userField.getText();// coloca o nome que tinha escrito, antes de dar erro
				email= emailField.getText();
			} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
				resetBorders();
				break;
			}
		}
		if (itsFinished) {
			String passEncript = BCrypt.hashpw(String.valueOf(passwordField.getPassword()), BCrypt.gensalt());
			if (db.addUserLogin(comboBox.getSelectedItem().toString(), userField.getText(),  emailField.getText(), passEncript,
					chckbxAdmin.isSelected())) {
				messageLogs.adicionaUser(loginUsername, true, comboBox.getSelectedItem().toString(), userField.getText(), nomeArmazem);
				popUp.showPopUpAdicionadoSucesso();
				modelUser.addRow(
						new Object[] { comboBox.getSelectedItem(), userField.getText(), chckbxAdmin.isSelected() });
			} else {
				popUp.showPopUpErroAdicionar();
			}

		}

	}

	
	
}
