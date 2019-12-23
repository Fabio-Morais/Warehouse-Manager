package db;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Logs {
	/**
	 * Insere log do que o user fez
	 * @param db - Base de Dados referente
	 * @param username - user que fez a a��o~
	 * @param admin - se user � admin ou user normal
	 * @param acao - a��o que o user fez
	 * @param acaoCompleta - a��o que o user fez de modo detalhada
	 * @param ip - ip do user que fez a a��o
	 * @param armazem - armazem a que pertence
	 * @return Boolean True se inseriu corretamente/ False no caso contrario
	 */
	public boolean insertAll(DataBase db, String username, boolean admin, String acao, String acaoCompleta, String ip, String armazem) {
		db.connect();
		String sql = "INSERT INTO logs (data,username, admin, acao_min, acao_compl, ip, id_armazem) "
				   + "VALUES ('"+db.localDate() +"', "+"'"+username+"', "+" '"+admin+"', '"+acao+"', '"+acaoCompleta+"', '"+ip+"', "+"(SELECT id from armazem where nome='"+armazem+"'))";
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
	 * Insere no TableModel o historico dos users 
	 * @param db - Base de dados referente 
	 * @param modelFornecedor - Tabela onde se insere os dados
	 * @param numeroLinhas - numero de linhas que deseja visualizar
	 * @return Boolean - True se inseriu corretamente na tabela/ False no caso contrario
	 */
	public boolean selectAll(DataBase db, DefaultTableModel modelFornecedor, String numeroLinhas) {
		db.connect();
		String sql = "SELECT data, username, admin, acao_min, ip FROM (SELECT * from logs ORDER BY data DESC LIMIT "+numeroLinhas+") as ta ORDER BY ta.data";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String data =rs.getString("data");
				String username = rs.getString("username");
				boolean admin = rs.getBoolean("admin");
				String acao = rs.getString("acao_min");
				String ip = rs.getString("ip");
				//String format = "%-25s%-25s%s%n";
				//System.out.printf(format,"id: "+id,"nome: "+nome,"id_armazem: "+id_armazem);
				modelFornecedor.addRow(new Object[] { data, username, admin, acao, ip });
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
	
}
