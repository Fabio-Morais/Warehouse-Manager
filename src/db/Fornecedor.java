/**
 * Classe BDFornecedor
 * Responsavel por tratar da tabela Fornecedor da base de dados
 * 
 * @author  Auto-buys
 * @version 1.0
 * @since   2019-11-22 
 */
package db;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Fornecedor {

	
	/**
	 * Insere no TableModel o Nome dos Fornecedores 
	 * @param db Base de dados referente 
	 * @param modelFornecedor Tabela onde se insere os dados
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso contrario
	 */
	public boolean selectAll(DataBase db, DefaultTableModel modelFornecedor) {
		db.connect();
		String sql = "SELECT id, nome, id_armazem "
				   + "FROM fornecedor";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id =rs.getInt("id");
				String nome = rs.getString("nome");
				int id_armazem = rs.getInt("id_armazem");
				//String format = "%-25s%-25s%s%n";
				//System.out.printf(format,"id: "+id,"nome: "+nome,"id_armazem: "+id_armazem);
				modelFornecedor.addRow(new Object[] { nome });
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
	        return false;
		}
		db.disconnect();
		return true;
	}
	
	/**
	 * Insere o Fornecedor no Armazem 
	 * @param db base de dados referente 
	 * @param nome Nome da categoria 
	 * @param nomeArmazem nome do Armazem onde se pretende inserir o Fornecedor
	 * @return Boolean True se inseriu corretamente/ False no caso contrario
	 */
	public boolean insertAll(DataBase db, String nome, String nomeArmazem) {
		db.connect();
		String sql = "INSERT INTO fornecedor (nome, id_armazem)"
				   + "VALUES ('"+nome+"',"+"(SELECT id FROM armazem WHERE nome="+"'"+nomeArmazem+"')"+")";
		//System.out.println(sql);
		try {
			Statement stmt = db.getC().createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
			return false;
		}
		db.disconnect();
		return true;
	}
	
	/**
	 * Update dos dados do fornecedor
	 * @param db Base de dados referente 
	 * @param nomeAntigo Nome Antigo do fornecedor
	 * @param nome Nome novo do fornecedor
	 * @return Boolean True se atualizou corretamente/ False no caso contrario
	 */
	public boolean update(DataBase db, String nomeAntigo,String nome) {
		db.connect();
		String sql = "UPDATE fornecedor " + 
				"SET nome="+"'"+nome+"'"+
				"WHERE nome="+"'"+nomeAntigo+"'";
		System.out.println(sql);
		try {
			Statement stmt = db.getC().createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
			return false;
		}
		db.disconnect();
		return true;
	}
	
	/**
	 * Remove o fornecedor da base de dados
	 * @param db Base de dados referente 
	 * @param nome Nome do fornecedor 
	 * @return Boolean True se removeu corretamente/ False no caso contrario
	 */
	public boolean remove(DataBase db, String nome) {
		db.connect();
		String sql = "DELETE FROM fornecedor WHERE nome="+"'"+nome+"'";
		//System.out.println(sql);
		try {
			Statement stmt = db.getC().createStatement();
			stmt.executeUpdate(sql);
		} 
		catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
			return false;
		}
		db.disconnect();

		return true;
	}
	
	/**
	 * Devolve todos os fornecedores de produtos
	 * @param db Base de dados referente 
	 * @return ArrayListString Todos os Fornecedores
	 */
	public ArrayList<String> selectFunc(DataBase db) {
		db.connect();
		ArrayList<String> x = new ArrayList<>();
		String sql = "SELECT nome "
				   + "FROM fornecedor";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String fornecedores = rs.getString("nome");
				x.add(fornecedores);
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		db.disconnect();
		return x;
	}
	
}
