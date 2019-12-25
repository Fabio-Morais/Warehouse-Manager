package guiadmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db.DataBase;
import gui.Interface;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;

public class Fornecedores {
	
	private static final String FORNECEDOR = "/truck.png";
	private PopUp popUp;
	private Check check;
	private DataBase db; 
	private MessageLogs messageLogs;
	private JTable fornecedoresTable;
	private DefaultTableModel modelFornecedor;

	private String loginUsername;

	/* POP UP */
	private JTextField fornecedorField;

	public Fornecedores(String username) {
		db = DataBase.getInstance();
		this.popUp = new PopUp();
		this.check = new Check();
		fornecedorField = new JTextField();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
		criaTabela();
	}
	
	private void criaTabela() {
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

	
		fornecedoresTable = new JTable();
		Interface.styleTabela(fornecedoresTable, modelFornecedor);
	}
	
	
	public JTable getFornecedoresTable() {
		return fornecedoresTable;
	}

	public DefaultTableModel getModelFornecedor() {
		return modelFornecedor;
	}

	private void resetBorders() {
		fornecedorField.setBorder(new JTextField().getBorder());
	}
	
	private boolean confirmData() {
		fornecedorField.setBorder(new JTextField().getBorder());
		
		/*Confirmar dados*/
		if (check.blankText(fornecedorField.getText())) {//testa se nao é tudo espaços brancos
			popUp.showPopUp("É necessario escolher um nome para o armazem", "Armazem");
			fornecedorField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		return true;
		
	}
	
	/* User POP UP Adicionar */
	private int showFornecedorPopUpAdicionar() {
		Object[] options1 = { "Adicionar", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(FORNECEDOR));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, -10, 10));

		JLabel lblArmazem = new JLabel("Nome Fornecedor");
		lblArmazem.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblArmazem);

		fornecedorField.setText("");
		panel.add(fornecedorField);
		fornecedorField.setColumns(15);

		
		return JOptionPane.showOptionDialog(null, panel, "Adicionar fornecedor", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	/*User POP UP Editar*/
	private int showFornecedorPopUpEditar(String nome) {
		Object[] options1 = { "Ok", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(FORNECEDOR));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 0, 0));
		
		
		JLabel lblUser = new JLabel("Nome");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblUser);
		
		fornecedorField.setText(nome);
		fornecedorField.setColumns(10);
		panel.add(fornecedorField);
		

		
		return JOptionPane.showOptionDialog(null, panel, "Editar "+ nome, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
		 }

	
	public void adicionarFornecedor(DefaultTableModel modelFornecedor, String armazem) {

		boolean isFinished= false;

		while (!isFinished) {
			int result = showFornecedorPopUpAdicionar();// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {
		
				isFinished = confirmData();

			} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
				resetBorders();
				break;
			}
		}

		if(isFinished) {
			db.addFornecedor(fornecedorField.getText() , armazem);
			messageLogs.adicionaFornecedor(loginUsername+";"+true+";"+armazem, fornecedorField.getText());
			modelFornecedor.addRow(new Object[] {fornecedorField.getText()});
		}
	}

	
	public void editarFornecedor(JTable fornecedorTable, DefaultTableModel modelFornecedor, String armazem) {
		boolean isFinished=false;
		int[] indexOfRow = fornecedorTable.getSelectedRows();
		
		if(indexOfRow.length != 1){
			popUp.showPopUp("Selecione apenas 1 linha","Erro ao selecionar");
			return;
		}
		indexOfRow[0]= fornecedorTable.convertRowIndexToModel(indexOfRow[0]);//converte para o indice correto (quando ordena)
		while(!isFinished) {
			int option=showFornecedorPopUpEditar(modelFornecedor.getValueAt(indexOfRow[0], 0).toString());
			if(option == JOptionPane.YES_OPTION) {
				isFinished = confirmData();
			}else {
				resetBorders();
				break;
			}
		}
		
		if(isFinished) {
			String nome = modelFornecedor.getValueAt(indexOfRow[0], 0).toString();
			db.updateFornecedor(nome, fornecedorField.getText());
			messageLogs.editaFornecedor(loginUsername+";"+true+";"+armazem,nome, fornecedorField.getText());
			modelFornecedor.setValueAt(fornecedorField.getText(),indexOfRow[0], 0);
		}
	}
	

	public void removeFornecedor(JTable fornecedorTable,  DefaultTableModel modelFornecedor, String armazem) {

		int[] selectedRows= fornecedorTable.getSelectedRows();
		if(selectedRows.length==0) {
			popUp.showPopUp("Selecione pelo menos uma linha", "Erro ao selecionar");
			return;
		}
		
		int option= popUp.showPopUpConfirmation("Tem a certeza que deseja apagar "+selectedRows.length + " Fornecedores?", "Eliminar fornecedores");
		if(option == JOptionPane.YES_OPTION) {
			for(int i=selectedRows.length-1; i>=0 ; i--) {
				String nome = modelFornecedor.getValueAt(fornecedorTable.convertRowIndexToModel(selectedRows[i]), 0).toString();
				db.removeFornecedor(nome);
				messageLogs.removeFornecedor(loginUsername+";"+true+";"+armazem, nome);
				modelFornecedor.removeRow(fornecedorTable.convertRowIndexToModel(selectedRows[i]));
			}
		}
		
	}

	
	public void refresh(DefaultTableModel modelFornecedor) {
		int rowCount = modelFornecedor.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelFornecedor.removeRow(i);
		}
		db.nomeFornecedor( modelFornecedor);
	}

}
