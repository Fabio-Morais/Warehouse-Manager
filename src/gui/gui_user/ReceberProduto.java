package gui.gui_user;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import db.DataBase;
import gui.AutoCompletion;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ReceberProduto {
	private JSpinner quantidadeJS;
	private JTextField numeroLoteJS;
	private JTextField paisField;
	private JComboBox<String> comboBoxSubCategoria;
	private JComboBox<String> comboBoxCategoria;
	private JComboBox<String> comboBoxFornecedor;

	private JTextField LoteField;
	private JDateChooser dateChooser;
	private PopUp popUp;
	private Check check;
	private DataBase db;

	private MessageLogs messageLogs;
	private JTextField SKUField;
	
	private String loginUsername;

	
	public ReceberProduto(String username) {
		db = DataBase.getInstance();
		check=new Check();
		popUp=new PopUp();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
	}
	
	private void resetBorders() {
		dateChooser.setBorder(new JTextField().getBorder());
		comboBoxSubCategoria.setBorder(new JTextField().getBorder());
		LoteField.setBorder(new JTextField().getBorder());
		comboBoxFornecedor.setBorder(new JTextField().getBorder());
		quantidadeJS.setBorder(new JTextField().getBorder());
		numeroLoteJS.setBorder(new JTextField().getBorder());
	}

	private boolean confirmData() {
		resetBorders();
		/* Confirma dados */
		try {
			Date pega = dateChooser.getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			String data = formato.format(pega);
		} catch (NullPointerException e) {
			popUp.showPopUp("É necessario escolher uma data válida", "Data");
			dateChooser.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		if (LoteField==null || check.blankText(LoteField.getText())) {
			popUp.showPopUp("É necessario escolher um Produto valido", "Produto Invalido");
			LoteField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		if (comboBoxSubCategoria.getSelectedItem()==null || check.blankText(comboBoxSubCategoria.getSelectedItem().toString())) {
			popUp.showPopUp("É necessario escolher SubCategoria Valida", "Sub Categoria Invalido");
			comboBoxSubCategoria.setBorder(new LineBorder(Color.red, 1));
			return false;
		} 
		if (comboBoxFornecedor==null || check.blankText(comboBoxFornecedor.getSelectedItem().toString())) {
			popUp.showPopUp("É necessario escolher um Pais de Origem", "Pais de origem Invalido");
			paisField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		if (SKUField==null || check.blankText(SKUField.getText())) {
			popUp.showPopUp("É necessario escolher um SKU", "SKU Invalido");
			SKUField.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		if (numeroLoteJS==null || !check.onlyNumbers(numeroLoteJS.getText())) {
			popUp.showPopUp("Escolha um numero de lote valido (so com caracteres numericos)", "Numero lote invalido");
			numeroLoteJS.setBorder(new LineBorder(Color.red, 1));
			return false;
		}
		return true;

	}
	
	public int showReceberPopUp() {
		Object[] options1 = { "Recebido", "Sair" };
		ImageIcon icon = new ImageIcon(userDesign.class.getResource("/shipping.png"));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblNome2 = new JLabel("Categoria");
		panel.add(lblNome2);
		comboBoxCategoria = new JComboBox<>();
		DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>();
		comboBoxCategoria.setModel(model3);
		panel.add(comboBoxCategoria);
		ArrayList<String> Categoria = db.getAllCategoria();
		for (int i = 0; i <  Categoria.size(); i++) {
			model3.addElement( Categoria.get(i));

		}
		comboBoxCategoria.setSelectedItem(Categoria);
		AutoCompletion.enable(comboBoxCategoria);

		JLabel lblNome = new JLabel("Sub-Categoria");
		panel.add(lblNome);
		comboBoxSubCategoria = new JComboBox<>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		comboBoxSubCategoria.setModel(model);
		panel.add(comboBoxSubCategoria);
		AutoCompletion.enable(comboBoxSubCategoria);

		// Mudar conforme categoria
		
		comboBoxCategoria.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	model.removeAllElements();
		    	String cat = comboBoxCategoria.getSelectedItem().toString();
		    	ArrayList<String> subCategoria = db.getAllSubCategoria(cat);
				for (int i = 0; i <  subCategoria.size(); i++) {
					model.addElement( subCategoria.get(i));

				}
				comboBoxSubCategoria.setSelectedItem(null);
		    }
		});
		
		JLabel lblUser = new JLabel("Nome produto");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblUser);
		LoteField = new JTextField();
		panel.add(LoteField);
		
		JLabel lblProduto = new JLabel("Quantidade");
		panel.add(lblProduto);
		quantidadeJS = new JSpinner();
		quantidadeJS.setModel(new SpinnerNumberModel(1, 1, 10000, 1));
		panel.add(quantidadeJS);
		
		
		JLabel lblOrigem = new JLabel("Fornecedor");
		panel.add(lblOrigem);
		
		comboBoxFornecedor = new JComboBox<>();
		DefaultComboBoxModel<String> model4 = new DefaultComboBoxModel<>();
		comboBoxFornecedor.setModel(model4);
		panel.add(comboBoxFornecedor);
		ArrayList<String> Fornecedor = db.getAllFornecedores();
		for (int i = 0; i <  Fornecedor.size(); i++) {
			model4.addElement( Fornecedor.get(i));

		}
		comboBoxFornecedor.setSelectedItem(Fornecedor);
		AutoCompletion.enable(comboBoxFornecedor);

		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblData);
		dateChooser=new JDateChooser();
		dateChooser.setMaxSelectableDate(new Date());
		Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
		dateChooser.setMinSelectableDate(date);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		Date date22 = new Date();
		dateChooser.setDate(date22);
		panel.add(dateChooser);
		
		JLabel lblNLote = new JLabel("Numero do Lote");
		panel.add(lblNLote);
		numeroLoteJS = new JTextField();
		panel.add(numeroLoteJS);
		
		
		JLabel lblSKU = new JLabel("SKU do Produto");
		panel.add(lblSKU);
		SKUField = new JTextField();
		panel.add(SKUField);
		
		
		return JOptionPane.showOptionDialog(null, panel, "Receber lote", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
		 }

	public void receberProdutoFunc(String nomeArmazem) {
		boolean itsFinished = false;
		String subCategoria = null;
		while (!itsFinished) {
			int result = showReceberPopUp();// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {
				itsFinished = confirmData();
			} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {

				break;
			}
		}
		if (itsFinished) {
			subCategoria = comboBoxSubCategoria.getSelectedItem().toString();
			String nomeProduto = LoteField.getText();
			int quantidade= (Integer) quantidadeJS.getValue();
			String origem = comboBoxFornecedor.getSelectedItem().toString();
			String numeroLote = numeroLoteJS.getText();
			
			java.sql.Timestamp timestamp = new java.sql.Timestamp(dateChooser.getDate().getTime());			
			String data = new SimpleDateFormat("MM/dd/yyyy").format(timestamp);
			String sku=SKUField.getText();
			sendtoBD(numeroLote+";"+origem+";"+data+";"+subCategoria+";"+nomeProduto,quantidade,sku);
			String dados = nomeProduto+";"+quantidade+";"+origem+";"+data+";"+numeroLote;
			messageLogs.receberProduto(loginUsername+";"+false+";"+nomeArmazem, dados);
		}
	}
	
	public void sendtoBD(String dadosLote,int quantidade,String SKU) {		
		String[] aux= dadosLote.split(";");

		if(!db.addLote(dadosLote)) {
			popUp.showPopUp("O Lote ja existe", "Lote invalido");
			return;
		}
		for (int i=0;i<quantidade;i++) {
			db.addProduto(SKU+Integer.toString(i+1), aux[0]);
		}
	}
	
	class RecebeProduto{
		private String numeroLote;
		private String origem;
		private String dataChegada;
		private String subCategoria;
		private String nome; 
		private int quantidade;
		private String SKU;
		public String getNumeroLote() {
			return numeroLote;
		}
		public void setNumeroLote(String numeroLote) {
			this.numeroLote = numeroLote;
		}
		public String getOrigem() {
			return origem;
		}
		public void setOrigem(String origem) {
			this.origem = origem;
		}
		public String getDataChegada() {
			return dataChegada;
		}
		public void setDataChegada(String dataChegada) {
			this.dataChegada = dataChegada;
		}
		public String getSubCategoria() {
			return subCategoria;
		}
		public void setSubCategoria(String subCategoria) {
			this.subCategoria = subCategoria;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getQuantidade() {
			return quantidade;
		}
		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}
		public String getSKU() {
			return SKU;
		}
		public void setSKU(String sKU) {
			SKU = sKU;
		}
		
		
	}
	
}
	
