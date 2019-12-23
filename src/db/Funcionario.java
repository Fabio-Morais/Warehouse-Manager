/**
 * Classe BDFuncionario
 * Responsavel por tratar da tabela Funcionario da base de dados
 * 
 * @author  Auto-buys
 * @version 1.0
 * @since   2019-11-22 
 */
package db;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Funcionario {
	
	/**
	 * Insere no TableModel o nif, nome, idade e função do funcionario
	 * @param DB Base de dados referente 
	 * @param armazem Armazem onde o funcionario trabalha
	 * @param modelFuncionario Tabela onde se insere os dados
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso contrario
	 */
	public boolean selectNifIdadeNomeFuncao(DataBase DB,String armazem, DefaultTableModel modelFuncionario) {
			String sql = "SELECT * "
					   + "FROM funcionario "
					   + "WHERE id_armazem=(SELECT id from armazem where nome='"+armazem+"')";
			Statement stmt = null;
			try {
				DB.connect();
				stmt = DB.getC().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String nif =rs.getString("nif");
					String nome =rs.getString("nome");
					String funcao=rs.getString("funcao");
					int idade = rs.getInt("idade");
					modelFuncionario.addRow(new Object[] {nif,nome , ""+idade,funcao });
				}
			} catch (Exception e) {
				e.printStackTrace();
		        System.err.println(e.getClass().getName()+": "+e.getMessage());
		        return false;
			}
			DB.disconnect();
			return true;
		}
	
	/**
	 * Insere no TableModel o nome, e nif do funcionario
	 * @param db Base de dados referente
	 * @param modelFuncionario Tabela onde se insere os dados
	 * @param armazem armazem onde o funcionario trabalha
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso contrario
	 */
	public boolean selectNomeNif(DataBase db, DefaultTableModel modelFuncionario, String armazem) {
		db.connect();
		String sql = "SELECT nome,nif "
				   + "FROM funcionario "
				   + "WHERE id_armazem=(SELECT id from armazem where nome='"+armazem+"')";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String nome = rs.getString("nome");
				String nif = rs.getString("nif");
				//String format = "%-25s%s%n";
				//System.out.printf(format,"nome: "+nome,"nif: "+nif);
				modelFuncionario.addRow(new Object[] { nif, nome });
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
	 * Devolve um vetor com todos os dados de um certo funcionario
	 * @param DB Base de dados referente
	 * @param nif Nif do funcionario
	 * @return Vetor String com Nome Idade Salario Função e armazem do funcionario
	 */
	public String[] selectAllNif(DataBase db,String nif) {
		db.connect();
		String[] x= new String[5]; 
		
		String sql = "SELECT * "
				   + "FROM funcionario "
				   + "WHERE nif="+"'"+nif+"'"+" "
				   + "ORDER BY nome";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String nome = rs.getString("nome");
				int idade = rs.getInt("idade");
				String função = rs.getString("funcao");
				double salário = rs.getDouble("salario");
				int id_aramzem = rs.getInt("id_armazem");
				x[0]=nome;
				x[1]=String.valueOf(idade);
				x[2]=função;
				x[3]=String.valueOf(salário);
				x[4]=String.valueOf(id_aramzem);
				//String format = "%-25s%-25s%-25s%-25s%s%n";
				//System.out.printf(format,"nome: "+x[0],"idade: "+x[1],"função: "+x[2],"salário: "+x[3],"id_aramzem: "+x[4]);
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());
		}		
		db.disconnect();
		return x;
	}
	
	/**
	 * Insere um novo Funcionario
	 * @param db Base de dados referente
	 * @param nif Nif do funcionario
	 * @param nome Nome do funcionario
	 * @param idade Idade do funcionario
	 * @param funcao Função do funcionario
	 * @param salario Salario do funiconario
	 * @param armazem Armazem onde trabalha 
	 * @return Boolean True se inseriu corretamente/False caso contrario
	 */
	public boolean insertAll(DataBase db, String nif, String nome, int idade, String funcao, double salario, String armazem ) {
		db.connect();
		String sql = "INSERT INTO funcionario(nif, nome, idade, funcao, salario, id_armazem)"
				   + "VALUES ("+"'"+nif+"'"+", '"+nome+"', "+idade+", '"+funcao+"',"+salario+", "+"(SELECT id from armazem where nome='"+armazem+"') "+")";
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
	 * Update no utilizador
	 * @param db Base de Dados referente 
	 * @param nifAntigo Nif antigo 
	 * @param novosDados dados do novo funcionario separado por ";", contem o nif, nome, idaide, funcao e salario do funcionario
	 * @return Boolean True se inseriu corretamente/False caso contrario
	 */
	public boolean updateFuncionario(DataBase db, String nifAntigo, String novosDados) {
		String[] dados= novosDados.split(";");
		db.connect();
		String sql ="UPDATE funcionario "
				  + "SET nif = '"+dados[0]+"' ,nome= '"+dados[1]+"' ,idade= '"+dados[2]+"' ,funcao= '"+dados[3]+"' ,salario=' "+dados[4]+"'"
				  + " WHERE nif = '"+nifAntigo+"'";
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
	 * Remove o funcionario da base de dados
	 * @param db Base de Dados referente
	 * @param nif Nif do funcionario
	 * @return Boolean True se removeu corretamente/False caso contrario
	 */
	public boolean remove(DataBase db, String nif) {
		db.connect();
		String sql = "DELETE FROM funcionario WHERE nif="+"'"+nif+"'";
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