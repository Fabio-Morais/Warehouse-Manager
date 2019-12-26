package gui.gui_admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import db.DataBase;
import gui.AutoCompletion;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;

public class CategoriaProduto {
	private DataBase db;// DATA BASE
	private MessageLogs messageLogs;
	private static final String CATEGORIA = "/list.png";
	private PopUp popUp;
	private Check check;
	private DefaultTableModel modelCategoriaProduto;
	private DefaultTableModel modelSubCategoriaProduto;
	private JTable subCategoriaProdutoTable;
	private JTable categoriaProdutoTable;
	private String loginUsername;

	/* POP UP */
	private JTextField nomeField;
	private JComboBox<String> comboBoxCategoria;
	private JComboBox<String> comboBox;

	public CategoriaProduto(String username) {
		this.popUp = new PopUp();
		this.check = new Check();
		nomeField = new JTextField();
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
		criaTabela();

	}
	private DefaultTableModel criaCategoriaModel() {
		return new DefaultTableModel(new Object[][] {}, new String[] { "Categoria" }) {

			private static final long serialVersionUID = -4871994548676222605L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	private DefaultTableModel criaSubCategoriaModel() {
		return new DefaultTableModel(new Object[][] {}, new String[] { "Sub-Categoria" }) {
			private static final long serialVersionUID = 7212103990277148587L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}
	
	private void criaTabela() {
		modelCategoriaProduto = criaCategoriaModel();
		modelSubCategoriaProduto = criaSubCategoriaModel();
		
		subCategoriaProdutoTable = new JTable();
		categoriaProdutoTable = new JTable();
		subCategoriaProdutoTable.setModel(modelSubCategoriaProduto);
		categoriaProdutoTable.setModel(modelCategoriaProduto);

		/* Para nao mover */
		subCategoriaProdutoTable.getTableHeader().setReorderingAllowed(false);
		subCategoriaProdutoTable.setAutoCreateRowSorter(true);// para ordenar
		subCategoriaProdutoTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		/* Para nao mover */
		categoriaProdutoTable.getTableHeader().setReorderingAllowed(false);
		categoriaProdutoTable.setAutoCreateRowSorter(true);// para ordenar
		categoriaProdutoTable.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

	}
	
	
	public DefaultTableModel getModelCategoriaProduto() {
		return modelCategoriaProduto;
	}

	public DefaultTableModel getModelSubCategoriaProduto() {
		return modelSubCategoriaProduto;
	}

	public JTable getSubCategoriaProdutoTable() {
		return subCategoriaProdutoTable;
	}

	public JTable getCategoriaProdutoTable() {
		return categoriaProdutoTable;
	}

	private boolean confirmData() {
		nomeField.setBorder(new JTextField().getBorder());

		/* Confirma dados */
		if (!check.textValidWithoutNumbers(nomeField.getText())) {
			popUp.showPopUp("Por favor introduzir um nome válido", "Nome");
			nomeField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		return true;

	}

	public void showSubCategoria(JTable categoriaTable, DefaultTableModel modelCategoria,
			DefaultTableModel modelSubCategoria) {
		int rowCount2 = modelSubCategoria.getRowCount();

		for (int i = rowCount2 - 1; i >= 0; i--) {
			modelSubCategoria.removeRow(i);
		}
		if (categoriaTable.getSelectedRow() >= 0) {
			String categoria = modelCategoria
					.getValueAt((categoriaTable.convertRowIndexToModel(categoriaTable.getSelectedRow())), 0).toString();
			db.subcategoriaSubCategoria(modelSubCategoria, categoria);

		}
	}

	/* User POP UP Adicionar */
	private int showCategoriaPopUpAdicionar(DefaultTableModel modelCategoria, String name) {
		Object[] options1 = { "Adicionar", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(CATEGORIA));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, 10, 10));

		comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> modelCombo = new DefaultComboBoxModel<String>(
				new String[] { "Categoria", "Sub-Categoria" });
		comboBox.setModel(modelCombo);

		panel.add(comboBox);

		comboBoxCategoria = new JComboBox<String>();
		DefaultComboBoxModel<String> modelComboSub = new DefaultComboBoxModel<String>(new String[] {});
		comboBoxCategoria.setModel(modelComboSub);
		comboBoxCategoria.setEnabled(false);
		AutoCompletion.enable(comboBoxCategoria);

		panel.add(comboBoxCategoria);

		/* Mete todas as sub categorias na comboBox */
		for (int i = 0; i < modelCategoria.getRowCount(); i++)
			modelComboSub.addElement(modelCategoria.getValueAt(i, 0).toString());

		JLabel lbl = new JLabel("Nome da " + comboBox.getSelectedItem());
		lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lbl);

		panel.add(nomeField);
		nomeField.setColumns(15);
		nomeField.setToolTipText("Nome " + comboBox.getSelectedItem());
		nomeField.setText(name);

		/* Serve para mudar o texto do "lblnome" dependendo da escolha */
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				nomeField.setToolTipText("Nome " + comboBox.getSelectedItem());
				if (comboBox.getSelectedItem().equals("Sub-Categoria")) {
					comboBoxCategoria.setEnabled(true);
					lbl.setText("Nome da " + comboBox.getSelectedItem());

				} else {
					comboBoxCategoria.setEnabled(false);
					lbl.setText("Nome da " + comboBox.getSelectedItem());

				}
			}
		});

		return JOptionPane.showOptionDialog(null, panel, "Adicionar fornecedor", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	/* User POP UP Editar */
	private int showFornecedorPopUpEditar(String nome, int choice,
			String categoria) {

		Object[] options1 = { "Ok", "Sair" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(CATEGORIA));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, 10, 10));

		comboBox = new JComboBox<>();
		DefaultComboBoxModel<String> modelCombo = new DefaultComboBoxModel<>(
				new String[] { "Categoria", "Sub-Categoria" });
		modelCombo.setSelectedItem(modelCombo.getElementAt(choice));
		comboBox.setModel(modelCombo);
		comboBox.setEnabled(false);
		panel.add(comboBox);

		comboBoxCategoria = new JComboBox<>();
		DefaultComboBoxModel<String> modelComboSub = new DefaultComboBoxModel<>(new String[] {});
		comboBoxCategoria.setModel(modelComboSub);
		comboBoxCategoria.setEnabled(false);
		panel.add(comboBoxCategoria);
		if (choice == 1) {
			modelComboSub.addElement(categoria);
			comboBoxCategoria.setSelectedItem(categoria);// colocar da base de dados o correspondente
		}
		JLabel lbl = new JLabel("Nome da " + comboBox.getSelectedItem());
		lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lbl);

		panel.add(nomeField);
		nomeField.setColumns(15);
		nomeField.setToolTipText("Nome " + comboBox.getSelectedItem());
		nomeField.setText(nome);

		return JOptionPane.showOptionDialog(null, panel, "Editar " + nome, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}

	public void adicionarCategoria(DefaultTableModel modelCategoria, DefaultTableModel modelSubCategoria,
			String armazem) {
		boolean isFinished = false;
		String name = null;
		while (!isFinished) {
			int result = showCategoriaPopUpAdicionar(modelCategoria, name);// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {
				isFinished = confirmData();
				name = nomeField.getText();
			} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
				break;
			}
		}
		if (isFinished) {
			if (comboBox.getSelectedItem().toString().equals("Categoria")) {
				db.addCategoriaProduto(name, armazem);
				messageLogs.adicionaCategoria(loginUsername+";"+true+";"+armazem, name, null);
				modelCategoria.addRow(new Object[] { name });

			} else {
				db.addSubCategoria(name, comboBoxCategoria.getSelectedItem().toString());// sub categoria, categoria
				messageLogs.adicionaCategoria(loginUsername+";"+true+";"+armazem, comboBoxCategoria.getSelectedItem().toString(), name);
				modelSubCategoria.addRow(new Object[] { name });
			}
		}

	}

	private void eliminaSubCategoria(int[] selectedRows2, JTable subCategoriaTable,
			DefaultTableModel modelSubCategoria, String categoria, String armazem) {
		for (int i = selectedRows2.length - 1; i >= 0; i--) {
			int index= subCategoriaTable.convertRowIndexToModel(selectedRows2[i]);
			String subCategoria = (String)modelSubCategoria.getValueAt(index,0);
			if (db.removeSubCategoria(subCategoria, categoria)) {
				messageLogs.removeCategoria(loginUsername+";"+true+";"+armazem, categoria, subCategoria);
				modelSubCategoria.removeRow(index);
			}else{
				popUp.showPopUpErroEliminar();
				break;
			}
		}
	}

	private void eliminaCategoria(int[] selectedRows, JTable categoriaTable, DefaultTableModel modelCategoria, String armazem) {
		for (int i = selectedRows.length - 1; i >= 0; i--) {
			String categoria= (String)modelCategoria.getValueAt(categoriaTable.convertRowIndexToModel(selectedRows[i]),0);
			if (db.removeCategoriaProduto(categoria)) {
				messageLogs.removeCategoria(loginUsername+";"+true+";"+armazem,categoria, null);
				modelCategoria.removeRow(categoriaTable.convertRowIndexToModel(selectedRows[i]));
			}else{
				popUp.showPopUpErroEliminar();
				break;
			}
		}
	}

	public void removeCategoria(JTable categoriaTable, JTable subCategoriaTable, DefaultTableModel modelCategoria,
			DefaultTableModel modelSubCategoria, String armazem) {
		int option = 0;
		int[] selectedRows = categoriaTable.getSelectedRows();
		int[] selectedRows2 = subCategoriaTable.getSelectedRows();
		int selected = check.selectedIndex(selectedRows, selectedRows2);

		if (selected == 3) {
			popUp.showPopUp("Precisa de selecionar pelo menos 1 linha", "Erro ao eliminar");
			return;
		} else if (selected == -1) {
			popUp.showPopUp("Selecione só 1 categoria e depois uma ou mais sub categorias", "Erro ao eliminar");
			return;
		}

		if (selected == 1) {
			option = popUp.showPopUpConfirmation(
					"Tem a certeza que deseja apagar " + (selectedRows.length) + " categorias?", "Eliminar categorias");
		} else if (selected == 2) {
			option = popUp.showPopUpConfirmation(
					"Tem a certeza que deseja apagar " + (selectedRows2.length) + " sub categorias?",
					"Eliminar sub categorias");
		}

		if (option == JOptionPane.YES_OPTION) {
			/* quando quer eliminar uma sub categoria pertencente a uma CATEGORIA */
			if (selected == 2) {
				String categoria=(String)modelCategoria.getValueAt(categoriaTable.convertRowIndexToModel(selectedRows[0]), 0);
				eliminaSubCategoria(selectedRows2, subCategoriaTable, modelSubCategoria, categoria, armazem);
				/* quando quer eliminar uma categoria */
			} else if (selected == 1) {
				eliminaCategoria(selectedRows, categoriaTable, modelCategoria, armazem);
				if (selectedRows.length > 0)
					categoriaTable.getSelectionModel().setSelectionInterval(0, 0);
			}

		}

	}

	public void editarCategoria(JTable categoriaProdutoTable, JTable subCategoriaProdutoTable,
			DefaultTableModel modelCategoriaProduto) {
		boolean isFinished = false;
		String nome = null;
		int[] indexOfCategoria = categoriaProdutoTable.getSelectedRows();
		int[] indexOfSubCategoria = subCategoriaProdutoTable.getSelectedRows();

		if (indexOfCategoria.length > 1 || indexOfSubCategoria.length > 1) {
			popUp.showPopUp("Selecione apenas 1 linha", "Erro ao selecionar");
			return;
		}

		while (!isFinished) {
			int option = 0;
			if (indexOfCategoria.length > 0 && indexOfSubCategoria.length == 0) {
				option = showFornecedorPopUpEditar((String) categoriaProdutoTable.getValueAt(indexOfCategoria[0], 0), 0, null);
			} else if (indexOfSubCategoria.length > 0) {
				option = showFornecedorPopUpEditar(
						(String) subCategoriaProdutoTable.getValueAt(indexOfSubCategoria[0], 0), 1
						, (String) categoriaProdutoTable.getValueAt(indexOfCategoria[0], 0));
			}
			if (option == JOptionPane.YES_OPTION) {
				isFinished = confirmData();
				nome = nomeField.getText();
			} else {
				break;
			}
		}
		/* inserir as coisas, tudo validado */
		if (isFinished) {
			if (indexOfCategoria.length > 0 && indexOfCategoria[0] >= 0) {
				categoriaProdutoTable.setValueAt(nome, indexOfCategoria[0], 0);
			} else if (indexOfSubCategoria.length > 0 && indexOfSubCategoria[0] >= 0) {
				subCategoriaProdutoTable.setValueAt(nome, indexOfSubCategoria[0], 0);

			}

		}

	}

	public void refresh(DefaultTableModel modelCategoriaProduto, DefaultTableModel modelSubCategoria) {
		int rowCount = modelCategoriaProduto.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			modelCategoriaProduto.removeRow(i);
		}
		int rowCount2 = modelSubCategoria.getRowCount();
		for (int i = rowCount2 - 1; i >= 0; i--) {
			modelSubCategoria.removeRow(i);
		}

		db.categoriaProduto(modelCategoriaProduto);
	}

}
