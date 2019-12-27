package gui.gui_user.graficos;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import db.DataBase;

public class Graficos {
	private DataBase db;

	public Graficos() {
		this.db = DataBase.getInstance();

	}

	public ChartPanel origemLotes() {

		CategoryDataset dataset = dadosOrigemLotes();
		JFreeChart chart = criarGraficoOrigemLotes(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);

		return chartPanel;
	}

	public CategoryDataset dadosOrigemLotes() {
		String sql = "select origem, count(origem) " + "from lote " + "group by origem "
				+ "ORDER BY count(origem) desc " + "limit 5";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ResultSet rs = db.executeQueryResult(sql);
		try {
			while (rs.next()) {
				dataset.setValue(rs.getInt("count"), "LOTES", rs.getString("origem"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	public JFreeChart criarGraficoOrigemLotes(CategoryDataset dataset) {

		return ChartFactory.createBarChart("Origem dos Lotes", "", "Lotes", dataset, PlotOrientation.VERTICAL, false,
				true, false);

	}

	// Vendas Sub Categoria
	public ChartPanel vendasSubCategoria() {

		CategoryDataset dataset = dadosVendasSubCategoria();
		JFreeChart chart = criarGraficoVendasSubCategoria(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);

		return chartPanel;
	}

	public CategoryDataset dadosVendasSubCategoria() {
		String sql = "select count(tipo), tipo from(" + "select sku, num_lote, vendido, lote.sub_categoria as tipo "
				+ "from produto " + "join lote " + "on produto.num_lote = lote.numero_lote " + "join sub_categoria "
				+ "on lote.sub_categoria = sub_categoria.sub_categoria " + "where vendido=TRUE) as exemplo "
				+ "group by tipo";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ResultSet rs = db.executeQueryResult(sql);

		try {
			while (rs.next()) {
				dataset.setValue(rs.getInt("count"), "PRODUTOS", rs.getString("tipo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	public JFreeChart criarGraficoVendasSubCategoria(CategoryDataset dataset) {

		return ChartFactory.createBarChart("Vendas Sub-Categoria", "", "Produtos", dataset, PlotOrientation.VERTICAL,
				false, true, false);

	}

//Média Salários por Idade
	public ChartPanel mediaSalariosIdade() {

		CategoryDataset dataset = dadosMediaSalariosIdade();
		JFreeChart chart = criarGraficoMediaSalario(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);

		return chartPanel;
	}

	public CategoryDataset dadosMediaSalariosIdade() {
		String sql = "select idade, avg(salario) " + "from funcionario " + "group by idade";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ResultSet rs = db.executeQueryResult(sql);

		try {
			while (rs.next()) {
				dataset.setValue(rs.getInt("avg"), "avg", rs.getString("idade"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public JFreeChart criarGraficoMediaSalario(CategoryDataset dataset) {
		return ChartFactory.createLineChart("Média Salário por Idades", "idade", "avg", dataset,
				PlotOrientation.VERTICAL, false, true, false);

	}

	// Vendas Por Dia
	public ChartPanel vendasPorDia() {

		CategoryDataset dataset = dadosVendasPorDia();
		JFreeChart chart = criarGraficoVendasPorDia(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);

		return chartPanel;
	}

	public CategoryDataset dadosVendasPorDia() {
		String sql = "select count(data_saida), data_saida " + "from produto " + "WHERE vendido=true "
				+ "group by data_saida " + "order by data_saida asc";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ResultSet rs = db.executeQueryResult(sql);

		try {
			while (rs.next()) {
				dataset.setValue(rs.getInt("count"), "PRODUTOS", rs.getDate("data_saida"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	public JFreeChart criarGraficoVendasPorDia(CategoryDataset dataset) {
		return ChartFactory.createLineChart("Vendas por dia", "Data de Saída", "Número de vendas", dataset,
				PlotOrientation.VERTICAL, false, true, false);

	}

//Atividade por user
	public ChartPanel atividadeUser() {

		PieDataset dataset = dadosAtividadeUser();
		JFreeChart chart = criarGraficoAtividadeUser(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);

		return chartPanel;
	}

	public PieDataset dadosAtividadeUser() {
		String sql = "select count(data), username " + "from logs " + "where cast(data as Date)=CURRENT_DATE "
				+ "group by username";
		DefaultPieDataset dataset = new DefaultPieDataset();
		ResultSet rs = db.executeQueryResult(sql);

		try {
			while (rs.next()) {
				dataset.setValue(rs.getString("username"), rs.getInt("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataset;
	}

	public JFreeChart criarGraficoAtividadeUser(PieDataset dataset) {
		return ChartFactory.createPieChart("Atividade Diária Utilizadores", dataset, false, true, false);

	}

}
