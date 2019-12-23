/**
 * Classe BDArmazem 
 * Responsavel por tratar da tabela armazem da base de dados
 * 
 * @author  Auto-buys
 * @version 1.0
 * @since   2019-11-22 
 */
package db;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Armazem {
	
	
	/**
	 * Insere no TableModel os dados Nome e Localização 
	 * do respetivo Armazem
	 * 
	 * @param db Base de dados respetiva
	 * @param modelArmazem Tabela onde se insere os dados
	 * @return boolean True se inseriu corretamente na tabela/ False no caso contrario
	 */
	public boolean selectAll(DataBase db, DefaultTableModel modelArmazem) {
		db.connect();
		String sql = "SELECT nome, localizacao FROM armazem";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String nome = rs.getString("nome");
				String localizacao = rs.getString("localizacao");
				modelArmazem.addRow(new Object[] { nome, localizacao });
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
	 * Retorna a Localização 
	 * do respetivo Armazem
	 * 
	 * @param db Base de dados respetiva
	 * @param nome nome do armazem a pesquisar
	 * @return String localizacao do armazem se encontrou com aquele nome/ null no caso contrario
	 */
	public String selectLocalizacao(DataBase db, String nome) {
		String x=null;
		db.connect();
		String sql = "SELECT nome, localizacao FROM armazem WHERE nome="+"'"+nome+"'";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				x = rs.getString("localizacao");
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
			return null;	
		}
		db.disconnect();
		return x;
	}
	
	/**
	 * Insere o Novo armazem na base de dados com o seu respetivo nome
	 * e local
	 * @param db Base de dados respetiva 
	 * @param nome Nome do Armazem
	 * @param localizacao Local onde se encontra
	 * @return boolean True se inseriu corretamente / False no caso contrario
	 */
	public boolean insertAll(DataBase db, String nome, String localizacao) {
		db.connect();
		String sql = "INSERT INTO armazem (nome, localizacao)"
				   + "VALUES ('"+nome+"', '"+localizacao+"');";
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
	 * Atualiza os dados, na base de dados, referentes a um respetivo Armazem
	 * @param db Base de dados referente
	 * @param nomeAntigo Nome antigo do armazem
	 * @param nome Novo nome do armazem
	 * @param localizacao Local do armazem
	 * @return boolean True se deu update corretamente / False no caso contrario
	 */
	public boolean updateArmazem(DataBase db, String nomeAntigo, String nome, String localizacao) {
		db.connect();
		String sql = "UPDATE armazem "
				   + "SET nome = '"+nome+"', localizacao = '"+localizacao+"' "
				   + "WHERE nome = '"+nomeAntigo+"';";
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
	 * Remove o Armazem com o nome indicado da base de dados
	 * @param db Base de dados referente
	 * @param nome Nome do armazem a eliminar
	 * @return boolean True se removeu corretamente / False no caso contrario
	 */
	public boolean remove(DataBase db, String nome) {
		db.connect();
		String sql = "DELETE FROM armazem WHERE nome="+"'"+nome+"'";
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
}
