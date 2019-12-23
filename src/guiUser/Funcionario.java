package guiUser;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.color.CMMException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.UIManager;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import db.DataBase;
import gui.PopUp;
import guiAdmin.AdminDesign;
//import GUI.Maquinas;
import guiLogin.LoginDesign;
import logic.Check;

import javax.swing.ImageIcon;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Funcionario {
	private static final String USER = "/user.png";

	private DataBase db;
	private PopUp popUp;
	private Check check;
	
	public Funcionario() {
		this.popUp = new PopUp();
		this.check = new Check();
		db = DataBase.getInstance();
	}
	public int showAllInfoFuncionar(String nif) {
		Object[] options1 = { "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(USER));

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
