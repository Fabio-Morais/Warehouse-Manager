package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import gui.gui_admin.Admin;

public class Interface {
	private static final String HOME = "/home.png";
	private Interface() {
		
	}
	public static void styleBotaoSimples(JButton button, String icon) {
		button.setFont(new Font("Consolas", Font.PLAIN, 12));
		button.setIcon(new ImageIcon(Admin.class.getResource(icon)));
		button.setBackground(Color.LIGHT_GRAY);

	}
	

	public static void styleButton(JButton button, String icon, Insets insets) {
		button.setMargin(insets);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalAlignment(SwingConstants.BOTTOM);
		button.setVerticalTextPosition(SwingConstants.TOP);
		button.setIcon(new ImageIcon(Admin.class.getResource(icon)));
		button.setBorder(UIManager.getBorder("Button.border"));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setForeground(new Color(25, 0, 75));
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Consolas", Font.BOLD, 12));
	}
	
	public static void styleBotaoHome(JButton button) {
		button.setFont(new Font("Consolas", Font.BOLD, 12));
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setIcon(new ImageIcon(Admin.class.getResource(HOME)));
		button.setBackground(Color.LIGHT_GRAY);

	}
	public static void styleBotaoLogin(JToggleButton btnLogin) {
		btnLogin.setMaximumSize(new Dimension(36, 23));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 18));
		btnLogin.setBackground(new Color(0x2dce98));
		btnLogin.setForeground(Color.white);
		
		btnLogin.setUI(new StyledButtonUI());
	}
	public static void styleBotaoRecuPass(JButton btnRecuperarPasswod) {
		btnRecuperarPasswod.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperarPasswod.setForeground(new Color(12, 51, 243));
		btnRecuperarPasswod.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRecuperarPasswod.setOpaque(false);
		btnRecuperarPasswod.setContentAreaFilled(false);
		btnRecuperarPasswod.setBorderPainted(false);
	}
	public static void styleLabel(JLabel label) {
		label.setFont(new Font("HP Simplified", Font.BOLD, 38));
	}
	public static void styleLabel28(JLabel label) {
		label.setFont(new Font("HP Simplified", Font.BOLD, 28));
	}
	public static void styleLabelMenu(JLabel label) {
		label.setFont(new Font("HP Simplified", Font.BOLD, 18));
	}
	
	public static void styleSearch(JTextField search) {
		search.setText("Quick Access");
		search.setToolTipText("Quick Access");
		search.setColumns(10);
	}
	
	public static void styleTabela(JTable table, DefaultTableModel model) {
		table.setSelectionForeground(Color.BLACK);
		table.setModel(model);

		/* Para nao mover */
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);// para ordenar
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public static void styleSeparator(JSeparator separator) {
		separator.setPreferredSize(new Dimension(0, 50));
		separator.setMinimumSize(new Dimension(120, 120));
		separator.setFont(new Font("Dialog", Font.BOLD, 15));
		separator.setForeground(Color.BLUE);
	}
	
	public static void styleTituloMenu(JEditorPane titulo, String string) {
		titulo.setBorder(new MatteBorder(1, 1, 3, 1, (Color) new Color(0, 0, 0)));
		titulo.setBackground(new Color(255, 250, 250));
		titulo.setFocusable(false);
		titulo.setIgnoreRepaint(true);
		titulo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		titulo.setFont(new Font("Lucida Sans", Font.BOLD, 22));
		titulo.setEditable(false);
		titulo.setText(string);
	}
}
