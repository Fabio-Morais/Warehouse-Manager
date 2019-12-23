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
//import GUI.Maquinas;
import guiLogin.LoginDesign;
import logic.Check;
import logic.MessageLogs;

import javax.swing.ImageIcon;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Produtos {

	private DataBase db;
	private MessageLogs messageLogs;
	private PopUp popUp;
	private Check check;
	private String loginUsername;

	public Produtos(String username) {
		this.popUp = new PopUp();
		this.check = new Check();
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
	}
	
	public void marcarDefeito(JTable tableProdutos, DefaultTableModel modelProdutos, String nomeArmazem) {
		int[] selectedRows = tableProdutos.getSelectedRows();

		for (int i = selectedRows.length - 1; i >= 0; i--) {
			String sku = modelProdutos.getValueAt(tableProdutos.convertRowIndexToModel(selectedRows[i]), 0).toString();
			db.marcarDefeitoProduto(sku);
			messageLogs.reportaDefeito(loginUsername, false, sku, nomeArmazem);
		}
		refreshProdutos(modelProdutos);
	}
	
	public void refreshProdutos(DefaultTableModel modelProdutos){
		int rowcont = modelProdutos.getRowCount();
		for (int i=rowcont-1;i>=0;i--) {
			modelProdutos.removeRow(i);
		}
		db.produtoNaoVendido(modelProdutos);
	}
}
