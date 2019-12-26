package gui.gui_user;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import db.DataBase;
import gui.gui_admin.AdminDesign;


public class CriarCsv {
	
	private static final String RELATORIO = "/report.png";
	
	private DataBase db;
		
	public void showPopUpCriarCSV(){
		Object[] options = { "Exportar", "Cancelar" };
		ImageIcon icon = new ImageIcon(AdminDesign.class.getResource(RELATORIO));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNome = new JLabel("Relat�rio:\n");
		panel.add(lblNome);
		
		String possibilities[] = {"Funcion�rios", "Atividade", "Produtos","Lotes","Maquinas"};
		JComboBox<String> comboBox = new JComboBox<String>(possibilities);
		panel.add(comboBox);
		
		JLabel lblAdmin = new JLabel("Abrir File");
		panel.add(lblAdmin);

		JCheckBox chckbx= new JCheckBox("");
		panel.add(chckbx);
		
		int result = JOptionPane.showOptionDialog(null, panel, "Gerar Relat�rio", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.PLAIN_MESSAGE, icon, options,options[0]);
		
		if(result == JOptionPane.YES_OPTION) {
			String TipoRelatorio = comboBox.getSelectedItem().toString();
			if( (TipoRelatorio != null) && (TipoRelatorio.length() > 0) ) {
			
				if ("Funcion�rios".equals(TipoRelatorio))
					csvFuncionarios(chckbx.isSelected());
				else if ("Produtos".equals(TipoRelatorio))
					csvProdutos(chckbx.isSelected());
				else if	("Atividade".equals(TipoRelatorio))
					csvAtividade(chckbx.isSelected());
				else if	("Lotes".equals(TipoRelatorio))
					csvLotes(chckbx.isSelected());
				else if	("Maquinas".equals(TipoRelatorio))
					csvMaquinas(chckbx.isSelected());
			}
		}
	}
	
 	public File localSave() {
 		File file=null;
		JFrame parentFrame = new JFrame();
		JFileChooser fileChooser = new JFileChooser();		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
		
	    fileChooser.setFileFilter(filter);
	    fileChooser.setDialogTitle("Escolha lugar onde guardar");
		if (fileChooser.showSaveDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			if (!file.getName().toLowerCase().endsWith(".csv"))
				file = new File(file.getAbsolutePath() + ".csv");
		}
		return file;
	}
	
	public void openFile(File file) {
		if(!Desktop.isDesktopSupported()) {  
	    	System.out.println("N�o suportado");    
	    }  
	    Desktop desktop = Desktop.getDesktop();  
	    if(file.exists())
			try {
				desktop.open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void csvFuncionarios(boolean openFile){
		 
		 File file = localSave();
			
		StringBuilder sb = new StringBuilder();
		
		db = DataBase.getInstance();
		db.connect();
		String query = "SELECT * FROM funcionario";
		Statement stmt = null;
		try {
			sb.append("NIF");
			sb.append(",");
			sb.append("Nome");
			sb.append(",");
			sb.append("Idade");
			sb.append(",");
			sb.append("Fun��o");
			sb.append(",");
			sb.append("Salario");
			sb.append("\r\n");
			
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
			     sb.append(rs.getString("nif"));
			     sb.append(","); 
			     sb.append(rs.getString("nome"));
			     sb.append(","); 
			     sb.append(rs.getString("idade"));
			     sb.append(","); 
			     sb.append(rs.getString("funcao"));
			     sb.append(","); 
			     sb.append(rs.getString("salario"));
			     sb.append("\r\n");
			}

			PrintWriter pw = new PrintWriter(file);
			pw.write(sb.toString());
			pw.close();
			
			if(openFile)
				openFile(file);
		    
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	public void csvProdutos(boolean openFile){
		 
		 File file = localSave();		
		
		StringBuilder sb = new StringBuilder();
		
		db = DataBase.getInstance();
		db.connect();
		String query = "SELECT * FROM produto";
		Statement stmt = null;
		try {
			 sb.append("SKU");
		     sb.append(","); 
			sb.append("Data Saida");
			sb.append(",");
			sb.append("Destino");
			sb.append(",");
			sb.append("Com Defeito");
			sb.append(",");
			sb.append("Num Lote");
			sb.append(",");
			sb.append("Vendido");
			sb.append("\r\n");
			
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){ 
			     sb.append(rs.getString("sku"));
			     sb.append(","); 
			     sb.append(rs.getString("data_saida"));
			     sb.append(","); 
			     sb.append(rs.getString("destino"));
			     sb.append(","); 
			     sb.append(rs.getString("com_defeito"));
			     sb.append(","); 
			     sb.append(rs.getString("num_lote"));
			     sb.append(","); 
			     sb.append(rs.getString("vendido"));
			     sb.append("\r\n");
			}

			PrintWriter pw = new PrintWriter(file);
			pw.write(sb.toString());
			pw.close();
			
			if(openFile)
				openFile(file);
		    
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}

	public void csvAtividade(boolean openFile) {
		File file = localSave();		
		
		StringBuilder sb = new StringBuilder();
		
		db = DataBase.getInstance();
		db.connect();
		String query = "SELECT * FROM logs";
		Statement stmt = null;
		try {
			sb.append("ID");
			sb.append(",");
			sb.append("Data");
		    sb.append(","); 
			sb.append("Username");
			sb.append(",");
			sb.append("Admin");
			sb.append(",");
			sb.append("A��o");
			sb.append(",");
			sb.append("IP");
			sb.append("\r\n");
			
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
			     sb.append(rs.getString("id"));
			     sb.append(","); 
			     sb.append(rs.getString("data"));
			     sb.append(","); 
			     sb.append(rs.getString("username"));
			     sb.append(","); 
			     sb.append(rs.getString("admin"));
			     sb.append(","); 
			     sb.append(rs.getString("acao_compl"));
			     sb.append(","); 
			     sb.append(rs.getString("ip"));
			     sb.append("\r\n");
			}

			PrintWriter pw = new PrintWriter(file);
			pw.write(sb.toString());
			pw.close();
			
			if(openFile)
				openFile(file);
		    
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	public void csvMaquinas(boolean openFile) {
		File file = localSave();		
		
		StringBuilder sb = new StringBuilder();
		
		db = DataBase.getInstance();
		db.connect();
		String query = "SELECT * FROM maquina";
		Statement stmt = null;
		try {
			sb.append("numero_serie");
			sb.append(",");
			sb.append("nome");
		    sb.append(","); 
			sb.append("avariada");
			sb.append(",");
			sb.append("id_armazem");
			sb.append(",");
			sb.append("descricao_avaria");
			sb.append("\r\n");
			
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
			     sb.append(rs.getString("numero_serie"));
			     sb.append(","); 
			     sb.append(rs.getString("nome"));
			     sb.append(","); 
			     sb.append(rs.getString("avariada"));
			     sb.append(","); 
			     sb.append(rs.getString("id_armazem"));
			     sb.append(","); 
			     sb.append(rs.getString("descricao_avaria"));
			     sb.append("\r\n");
			}

			PrintWriter pw = new PrintWriter(file);
			pw.write(sb.toString());
			pw.close();
			
			if(openFile)
				openFile(file);
		    
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
	public void csvLotes(boolean openFile) {
		 
		 File file = localSave();		
		
		StringBuilder sb = new StringBuilder();
		
		db = DataBase.getInstance();
		db.connect();
		String query = "SELECT * FROM lote";
		Statement stmt = null;
		try {
			sb.append("numero_lote");
			sb.append(",");
			sb.append("origem");
		    sb.append(","); 
			sb.append("data_de_chegada");
			sb.append(",");
			sb.append("sub_categoria");
			sb.append(",");
			sb.append("nome");
			sb.append("\r\n");
			
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
			     sb.append(rs.getString("numero_lote"));
			     sb.append(","); 
			     sb.append(rs.getString("origem"));
			     sb.append(","); 
			     sb.append(rs.getString("data_de_chegada"));
			     sb.append(","); 
			     sb.append(rs.getString("sub_categoria"));
			     sb.append(","); 
			     sb.append(rs.getString("nome"));
			     sb.append("\r\n");
			}

			PrintWriter pw = new PrintWriter(file);
			pw.write(sb.toString());
			pw.close();
			
			if(openFile)
				openFile(file);
		    
		} catch (Exception e) {
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}
	}
	
}