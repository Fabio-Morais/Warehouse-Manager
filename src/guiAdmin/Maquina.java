package guiAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db.DataBase;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;

public class Maquina {

	private static final String MAQUINA = "/machine.png";
	private PopUp popUp;
	private Check check;
	private DataBase db;
	private MessageLogs messageLogs;

	
	private String loginUsername;


	/* POP UP */
	private JTextField maquinaField;
	private JTextField numeroField;

	public Maquina(String username) {
		db = DataBase.getInstance();
		this.popUp = new PopUp();
		this.check = new Check();
		maquinaField = new JTextField();
		numeroField = new JTextField();
		this.loginUsername = username;
		messageLogs = MessageLogs.getInstance();

	}

	private void resetBorders() {
		maquinaField.setBorder(new JTextField().getBorder());
		numeroField.setBorder(new JTextField().getBorder());
	}

	private boolean confirmData() {
		maquinaField.setBorder(new JTextField().getBorder());
		numeroField.setBorder(new JTextField().getBorder());

		/* Confirmar dados */
		if (check.blankText(maquinaField.getText())) {
			popUp.showPopUp("É necessario escolher um nome para a maquina", "Maquina");
			maquinaField.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if (check.blankText(numeroField.getText())) {
			popUp.showPopUp("É necessario escolher um numero para a maquina", "Maquina");
			numeroField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		return true;
	}

	/* User POP UP Adicionar */
	private int showMaquinaPopUpAdicionar() {
		Object[] options1 = { "Adicionar", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(MAQUINA));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, -10, 10));

		JLabel lblArmazem = new JLabel("Maquina");
		lblArmazem.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblArmazem);

		maquinaField.setText("");
		panel.add(maquinaField);
		maquinaField.setColumns(15);

		JLabel lblNumero = new JLabel("Numero Serie");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNumero);

		numeroField.setText("");
		panel.add(numeroField);
		numeroField.setColumns(15);

		return JOptionPane.showOptionDialog(null, panel, "Adicionar máquina", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	/* User POP UP Editar */
	private int showMaquinaPopUpEditar(String nome, String numero) {
		Object[] options1 = { "Ok", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(MAQUINA));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 0, 0));

		JLabel lblArmazem = new JLabel("Maquina");
		lblArmazem.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblArmazem);

		panel.add(maquinaField);
		maquinaField.setColumns(15);
		maquinaField.setText(nome);

		JLabel lblNumero = new JLabel("Numero Serie");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNumero);

		panel.add(numeroField);
		numeroField.setColumns(15);
		numeroField.setText(numero);

		return JOptionPane.showOptionDialog(null, panel, "Editar " + nome, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	/* Só falta base de dados */
	public void adicionarMaquina(DefaultTableModel modelMaquina, String armazem) {

		boolean isFinished = false;

		while (!isFinished) {
			int result = showMaquinaPopUpAdicionar();// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {

				isFinished = confirmData();

			} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
				resetBorders();
				break;
			}
		}

		if (isFinished) {
			if (db.addMaquina(numeroField.getText(), maquinaField.getText(), armazem)) {
				messageLogs.adicionaMaquina(loginUsername, true, maquinaField.getText(), numeroField.getText(), armazem);
				popUp.showPopUpAdicionadoSucesso();
				modelMaquina.addRow(new Object[] { maquinaField.getText(), numeroField.getText() });
			} else {
				popUp.showPopUpErroAdicionar();
			}
		}
			
	}

	public void editarMaquina(JTable maquinaTable, DefaultTableModel modelMaquina, String nomeArmazem) {
		boolean isFinished = false;
		int[] indexOfRow = maquinaTable.getSelectedRows();

		if (indexOfRow.length != 1) {
			popUp.showPopUp("Selecione apenas 1 linha", "Erro ao selecionar");
			return;
		}
		indexOfRow[0] = maquinaTable.convertRowIndexToModel(indexOfRow[0]);// converte para o indice correto (quando
																			// ordena)
		while (!isFinished) {
			int option = showMaquinaPopUpEditar(modelMaquina.getValueAt(indexOfRow[0], 0).toString(),
					modelMaquina.getValueAt(indexOfRow[0], 1).toString());
			if (option == JOptionPane.YES_OPTION) {
				isFinished = confirmData();
			} else {
				resetBorders();
				break;
			}
		}

		if (isFinished) {
			String numero= modelMaquina.getValueAt(indexOfRow[0], 1).toString();
			String nome = modelMaquina.getValueAt(indexOfRow[0], 0).toString();
			if (db.updateMaquina(numero, numeroField.getText(),
					maquinaField.getText())) {
				messageLogs.editaMaquina(loginUsername, true, nome, numero, maquinaField.getText(), numeroField.getText(), nomeArmazem);
				popUp.showPopUpEditarSucesso();
				modelMaquina.setValueAt(maquinaField.getText(), indexOfRow[0], 0);
				modelMaquina.setValueAt(numeroField.getText(), indexOfRow[0], 1);
			} else {
				popUp.showPopUpErroEditar();

			}

		}
	}

	public void removeMaquina(JTable maquinaTable, DefaultTableModel modelMaquina, String armazem) {

		int[] selectedRows = maquinaTable.getSelectedRows();
		if (selectedRows.length == 0) {
			popUp.showPopUp("Selecione pelo menos uma linha", "Erro ao selecionar");
			return;
		}

		int option = popUp.showPopUpConfirmation(
				"Tem a certeza que deseja apagar " + selectedRows.length + " maquinas?", "Eliminar maquinas");
		if (option == JOptionPane.YES_OPTION) {
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				String numero = modelMaquina.getValueAt(maquinaTable.convertRowIndexToModel(selectedRows[i]), 1).toString();
				String nome = modelMaquina.getValueAt(maquinaTable.convertRowIndexToModel(selectedRows[i]), 0).toString();
				if (db.removeMaquina(numero)) {
					messageLogs.removeMaquina(loginUsername, true, nome, numero, armazem);
					modelMaquina.removeRow(maquinaTable.convertRowIndexToModel(selectedRows[i]));
				} else {
					popUp.showPopUpErroEliminar();
					break;
				}
			}
		}

	}

	public void refresh(DefaultTableModel modelMaquina, String armazem) {
		int rowCount = modelMaquina.getRowCount();
		if (!db.connect()) {
			popUp.showPopUpDataBaseError();
		} else {
			for (int i = rowCount - 1; i >= 0; i--) {
				modelMaquina.removeRow(i);
			}
			db.nomenumeroMaquina(armazem, modelMaquina);
		}
	}

}
