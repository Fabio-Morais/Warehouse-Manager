/**
 * Classe BDProduto
 * Responsavel por tratar da tabela Produto da base de dados
 * 
 * @author  Auto-buys
 * @version 1.0
 * @since   2019-11-22 
 */
package db;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Produto {

	/**
	 * Insere no TableModel o nome e Sku dos produtos vendidos
	 * 
	 * @param db           Base de dados referente
	 * @param modelProduto Tabela onde se insere os dados
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso
	 *         contrario
	 */
	public boolean selectProdutoVendido(DataBase db, DefaultTableModel modelProduto) {
		db.connect();
		modelProduto.setRowCount(0);// eliminar todos os dados contidos na tabela
		/* NOME, numero de serie */
		String sql = "SELECT data_saida,sku,nome, destino " + "FROM produto "
				+ "inner join lote on num_lote=numero_lote " + "WHERE vendido='true'";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String data = rs.getString("data_saida");
				String sku = rs.getString("sku");
				String nome = rs.getString("nome");
				String destino = rs.getString("destino");
				modelProduto.addRow(new Object[] { data, sku, nome, destino });
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		db.disconnect();
		return true;
	}

	/**
	 * Insere no TableModel o Sku, nome, origem e se est� com defeito ou nao dos
	 * produtos nao vendidos
	 * 
	 * @param db           Base de dados referente
	 * @param modelProduto Tabela onde se insere os dados
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso
	 *         contrario
	 */
	public boolean selectProdutoNaoVendido(DataBase db, DefaultTableModel modelProduto) {
		db.connect();
		modelProduto.setRowCount(0);// eliminar todos os dados contidos na tabela
		/* NOME, numero de serie */
		String sql = "SELECT sku, nome, origem, com_defeito " + "FROM produto "
				+ "inner join lote on num_lote=numero_lote " + "WHERE vendido='false'";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String sku = rs.getString("sku");
				String nome = rs.getString("nome");
				String origem = rs.getString("origem");
				boolean comDefeito = rs.getBoolean("com_defeito");
				modelProduto.addRow(new Object[] { sku, nome, origem, comDefeito });
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		db.disconnect();
		return true;
	}

	/**
	 * Insere no TableModel todas as SKUs dos produtos n�o vendidos
	 * 
	 * @param db-          Base de dados referente
	 * @param modelProduto - Tabela onde se insere os dados
	 * @return Boolean True se inseriu corretamente na tabela/ False no caso
	 *         contrario
	 */
	public boolean selectAllSKUsNaoVendidos(DataBase db, DefaultTableModel modelProduto) {
		db.connect();
		modelProduto.setRowCount(0);// eliminar todos os dados contidos na tabela
		/* NOME, numero de serie */
		String sql = "SELECT sku FROM produto inner join lote on num_lote=numero_lote " + "WHERE vendido='false'";
		Statement stmt = null;
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String sku = rs.getString("sku");
				modelProduto.addRow(new Object[] { sku });
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		db.disconnect();
		return true;
	}

	/**
	 * Insere informa��o do Produto
	 * 
	 * @param db      Base de Dados referente
	 * @param sku     Numero do produto
	 * @param numLote Numero de lote a que pertence o produto
	 * @return Boolean True se inseriu corretamente/ False no caso contrario
	 */
	public boolean insertAll(DataBase db, String sku, String numLote) {
		db.connect();
		String sql = "INSERT INTO produto(sku, data_saida, com_defeito, num_lote,vendido)" + "VALUES ('" + sku + "'"
				+ ", " + null + ", 'false', '" + numLote + "', 'false')";
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

	/*
	 * Coloca o produto com a SKU especificada como defeituoso
	 * 
	 * @param db - Base de Dados referente
	 * 
	 * @param sku - Numero do produto
	 * 
	 * @return Boolean True se inseriu corretamente/ False no caso contrario
	 */
	public boolean marcarDefeitoProduto(DataBase db, String sku) {
		db.connect();
		String sql = "UPDATE produto SET com_defeito = 'true' WHERE sku='" + sku + "'";
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
	 * 	Envia produto
	 * @param db - Base de dados referente
	 * @param sku - SKU do produto
	 * @param Data - Data de venda
	 * @return Boolean True se operou corretamente/ False no caso contrario
	 */
	public boolean produtoEnviado(DataBase db, String sku, String Data,String destino) {
		db.connect();
		String sql = "UPDATE produto SET data_saida = '" + Data + "'" + ", vendido='true', destino='"+destino+"' WHERE sku='" + sku + "'";
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
