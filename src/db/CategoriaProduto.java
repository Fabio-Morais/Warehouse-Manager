/**
* Classe BDCategoriaProduto
* Responsavel por tratar da tabela categoria de produtos
* da base de dados 
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

public class CategoriaProduto {
	
	/**
	 * Insere no TableModel o Nome da Categoria do produto 
	 * @param db Base de dados referente 
	 * @param modelCategoria Tabela onde se insere os dados
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso contrario
	 */
	public boolean selectAll(DataBase db, DefaultTableModel modelCategoria) {
		db.connect();
		String sql = "SELECT * FROM categoria_produto ";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String nome = rs.getString("nome");
				modelCategoria.addRow(new Object[] { nome });
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
	 * Insere o nome da categoria no Armazem 
	 * @param db Base de dados referente 
	 * @param nome Nome da categoria 
	 * @param armazem Armazem onde se pretende inserir a categoria
	 * @return Boolean True se inseriu corretamente/ False no caso contrario
	 */
	public boolean insertAll(DataBase db, String nome, String armazem) {
		db.connect();
		String sql = "INSERT INTO categoria_produto (nome, id_armazem) "
				+ "VALUES ('"+nome+"',"+"(SELECT id FROM armazem WHERE nome ="+"'"+armazem+"')"+")";
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
	 * Remove a Categoria 
	 * @param db Base de dados referente 
	 * @param nome Nome da categoria
	 * @return boolean True se removeu corretamente / False no caso contrario
	 */
	public boolean remove(DataBase db, String nome) {
		db.connect();
		String sql = "DELETE FROM categoria_produto WHERE nome="+"'"+nome+"'";		 
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
	 * Devolve todos as categorias dos produtos
	 * @param db Base de dados referente 
	 * @return ArrayListString Todos as categorias
	 */
	public ArrayList<String> selectCat(DataBase db) {
		db.connect();
		ArrayList<String> x = new ArrayList<>();
		String sql = "SELECT nome " + "FROM categoria_produto ";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String categoria = rs.getString("nome");
				x.add(categoria);
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