package gui.gui_user;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;
import db.DataBase;
import gui.gui_admin.Admin;

import javax.swing.ImageIcon;

public class Funcionario {
	
	private static final String USER = "/user.png";
	private DataBase db;
	
	public Funcionario() {
		db = DataBase.getInstance();
	}
	
	public int showAllInfoFuncionar(String nif) {
		Object[] options1 = { "Sair" };
		ImageIcon icon = new ImageIcon(Admin.class.getResource(USER));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 0, 0));
		
		String[] user= db.getFuncinarioByNif(nif);
		JLabel lblNif = new JLabel("NIF:");
		panel.add(lblNif);

		JLabel lblNifUser = new JLabel(nif);
		panel.add(lblNifUser);
		
		JLabel lblNome = new JLabel("Nome:");
		panel.add(lblNome);

		JLabel lblNomeUser = new JLabel(user[0]);
		panel.add(lblNomeUser);
		
		JLabel lblIdade = new JLabel("Idade:");
		panel.add(lblIdade);

		JLabel lblIdadeUser = new JLabel(user[1]);
		panel.add(lblIdadeUser);
		
		JLabel lblFuncao = new JLabel("Funcao:");
		panel.add(lblFuncao);

		JLabel lblFuncaoUser = new JLabel(user[2]);
		panel.add(lblFuncaoUser);
		
		JLabel lblSalario = new JLabel("Salario:");
		panel.add(lblSalario);

		JLabel lblSalarioUser = new JLabel(user[3]+" €");
		panel.add(lblSalarioUser);
		
		return JOptionPane.showOptionDialog(null, panel, lblNomeUser.getText(), JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}
	
	public void refreshFuncionarios(DefaultTableModel modelFuncionarios, String nomeArmazem) {
		int rowcont = modelFuncionarios.getRowCount();
		for (int i=rowcont-1;i>=0;i--) {
			modelFuncionarios.removeRow(i);
		}
		db.nifIdadeNomeFuncaoFuncionario(nomeArmazem,modelFuncionarios);
	}
	
}
