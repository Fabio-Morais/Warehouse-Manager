package guiAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db.DataBase;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;

public class Funcionarios {
	private DataBase db;//DATA BASE
	private MessageLogs messageLogs;
	private static final String WAREHOUSE = "/funcionario.png";
	private PopUp popUp;
	private Check check;

	private String loginUsername;

	/* POP UP */
	private JTextField nifField;
	private JTextField nomeField;
	private JSpinner idade;
	private JTextField funcaoField;
	private JSpinner salario;

	public Funcionarios(String username) {
		this.popUp = new PopUp();
		this.check = new Check();
		nifField = new JTextField();
		nomeField = new JTextField();
		funcaoField = new JTextField();
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;

	}

	private boolean confirmData() {

		nomeField.setBorder(new JTextField().getBorder());
		nifField.setBorder(new JTextField().getBorder());
		funcaoField.setBorder(new JTextField().getBorder());

		/* Confirma dados */
		if (!check.isNifValid(nifField.getText())) {
			popUp.showPopUp("NIF inválido, por favor introduzir um NIF válido", "NIF");
			nifField.setBorder(new LineBorder(Color.red, 1));

			return false;
		} else if (!check.textValidWithoutNumbers(nomeField.getText())) {
			popUp.showPopUp("É necessario escolher uma nome válido", "Nome");
			nomeField.setBorder(new LineBorder(Color.red, 1));
			return false;
		} else if (!check.textValidWithoutNumbers(funcaoField.getText())) {
			popUp.showPopUp("É necessario escolher uma função válido", "Função");
			funcaoField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		return true;

	}

	/* User POP UP Adicionar */
	private int showFuncionarioPopUpAdicionar(String nif, String nome, int idade, String funcao, double salario) {
		Object[] options1 = { "Adicionar", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(WAREHOUSE));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, -10, 10));

		JLabel lblNif = new JLabel("NIF");
		lblNif.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNif);

		panel.add(nifField);
		nifField.setColumns(15);
		nifField.setText(nif);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNome);

		panel.add(nomeField);
		nomeField.setColumns(15);
		nomeField.setText(nome);

		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblIdade);

		this.idade = new JSpinner();
		this.idade.setModel(new SpinnerNumberModel(22, 16, 99, 1));
		panel.add(this.idade);
		this.idade.setValue(Integer.valueOf(idade));

		JLabel lblFuncao = new JLabel("Função");
		lblFuncao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblFuncao);

		panel.add(funcaoField);
		funcaoField.setColumns(15);
		funcaoField.setText(funcao);

		JLabel lblSalario = new JLabel("Salário");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblSalario);

		this.salario = new JSpinner();
		panel.add(this.salario);
		this.salario.setModel(new SpinnerNumberModel(new Double(500), new Double(0), new Double(500000), new Double(200)));
		this.salario.setValue(Double.valueOf(salario));

		return JOptionPane.showOptionDialog(null, panel, "Adicionar Funcionario", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	/* User POP UP Editar */
	private int showFuncionarioPopUpEditar(String nif, String nome, int idade, String funcao, float salario) {
		Object[] options1 = { "Ok", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(WAREHOUSE));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 0, 0));

		JLabel lblNif = new JLabel("NIF");
		lblNif.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNif);

		panel.add(nifField);
		nifField.setColumns(15);
		nifField.setText(nif);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNome);

		panel.add(nomeField);
		nomeField.setColumns(15);
		nomeField.setText(nome);

		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblIdade);

		this.idade = new JSpinner();
		this.idade.setModel(new SpinnerNumberModel(idade, 16, 99, 1));
		panel.add(this.idade);

		JLabel lblFuncao = new JLabel("Função");
		lblFuncao.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblFuncao);

		panel.add(funcaoField);
		funcaoField.setColumns(15);
		funcaoField.setText(funcao);

		JLabel lblSalario = new JLabel("Salário");
		lblSalario.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblSalario);

		this.salario = new JSpinner();
		panel.add(this.salario);
		this.salario
				.setModel(new SpinnerNumberModel(new Float(salario), new Float(0), new Float(500000), new Float(200)));

		return JOptionPane.showOptionDialog(null, panel, "Editar " + nome, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	public void adicionarFuncionario(DefaultTableModel modelFuncionario, String armazem) {
		String nif = null;
		String nome = null;
		int idade = 22;
		String funcao = null;
		double salario = (double) 500.0;

		boolean isFinished = false;

		while (!isFinished) {
			int result = showFuncionarioPopUpAdicionar(nif, nome, idade, funcao, salario);// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {
				/* adiciona na tabela e base de dados */
				isFinished = confirmData();
				nif = nifField.getText();
				nome = nomeField.getText();
				idade = (Integer) this.idade.getValue();
				funcao = funcaoField.getText();
				salario = (double) this.salario.getValue();
			} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
				System.out.println("cancelou");
				break;
			}
		}

		if (isFinished) {
			db.addFuncionario(nif, nome, idade, funcao, salario, armazem);
			messageLogs.adicionaFuncionario(loginUsername, true, nome, nif, armazem);
			modelFuncionario.addRow(new Object[] { nifField.getText(), nomeField.getText() });
		}

	}

	public void editarFuncionario(JTable funcionarioTable, DefaultTableModel modelFuncionario) {
		boolean isFinished = false;
		int[] indexOfRow = funcionarioTable.getSelectedRows();
		if (indexOfRow.length != 1) {
			popUp.showPopUp("Selecione apenas 1 linha", "Erro ao selecionar");
			return;
		}
		indexOfRow[0]= funcionarioTable.convertRowIndexToModel(indexOfRow[0]);
		String nif = (String) modelFuncionario.getValueAt(indexOfRow[0], 0);
		String nome = (String) modelFuncionario.getValueAt(indexOfRow[0], 1);

		while (!isFinished) {
			/* Colocar a informaçao vinda da base de dados */
			int option = showFuncionarioPopUpEditar(nif, nome, 18, "Engenheiro", (float) 1000.50);
			if (option == JOptionPane.YES_OPTION) {
				isFinished = confirmData();
				nif = nifField.getText();
				nome = nomeField.getText();
			} else {
				break;
			}
		}
		if (isFinished) {
			db.updateFuncionario(modelFuncionario.getValueAt(indexOfRow[0],0 ).toString(), nifField.getText(),
					nomeField.getText(), (int)this.idade.getValue(), funcaoField.getText(), (double)this.salario.getValue());
			modelFuncionario.setValueAt(nomeField.getText(), indexOfRow[0], 1);
			modelFuncionario.setValueAt(nifField.getText(), indexOfRow[0], 0);
		}

	}

	public void removeFuncionario(JTable funcionarioTable, DefaultTableModel modelFuncionario, String armazem) {
		/* eliminar na base de dados */
		int[] selectedRows = funcionarioTable.getSelectedRows();
		if(selectedRows.length<=0) {
			popUp.showPopUp("Precisa de selecionar pelo menos 1 linha", "Erro ao eliminar");
			return;
		}
	
		int option = popUp.showPopUpConfirmation(
				"Tem a certeza que deseja apagar " + selectedRows.length + " funcionarios?", "Eliminar funcionarios");
		if (option == JOptionPane.YES_OPTION) {
			for (int i = selectedRows.length - 1; i >= 0; i--) {
				String nif= modelFuncionario.getValueAt(funcionarioTable.convertRowIndexToModel(selectedRows[i]), 0).toString();
				String nome = modelFuncionario.getValueAt(funcionarioTable.convertRowIndexToModel(selectedRows[i]), 1).toString();
				db.removeFuncionarioByNif(nif);
				messageLogs.removeFuncionario(loginUsername, true, nome, nif, armazem);
				modelFuncionario.removeRow(funcionarioTable.convertRowIndexToModel(selectedRows[i]));
			}
		}
	}

	public void refresh(DefaultTableModel modelFuncionario, String armazem) {
		int rowCount = modelFuncionario.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelFuncionario.removeRow(i);
		}
		db.nomeNifFuncionario(modelFuncionario, armazem);

	}

}
