package guiUser;
import db.*;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class Graficos extends JFrame {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Graficos() {
    }

	
//Origem dos Lotes
    public ChartPanel origemLotes() {
    	
        CategoryDataset dataset = dadosOrigemLotes();
        JFreeChart chart = criarGraficoOrigemLotes(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        return chartPanel;
    }

    public CategoryDataset dadosOrigemLotes() {
    	DataBase db = DataBase.getInstance();
    	db.connect();
		String sql = "select origem, count(origem) " + 
				"from lote " + 
				"group by origem " +
				"ORDER BY count(origem) desc " +
				"limit 5";
		Statement stmt = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dataset.setValue(rs.getInt("count"), "LOTES", rs.getString("origem"));
				
				db.disconnect(); 
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());	
		}
		
        return dataset;
    }
    
    public JFreeChart criarGraficoOrigemLotes(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Origem dos Lotes",
                "",
                "Lotes",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    
 //Vendas Sub Categoria  
    public ChartPanel VendasSubCategoria() {

        CategoryDataset dataset = DadosVendasSubCategoria();
        JFreeChart chart = criarGraficoVendasSubCategoria(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        return chartPanel;
    }

    public CategoryDataset DadosVendasSubCategoria() {
    	DataBase db = DataBase.getInstance();
    	db.connect();
		String sql = "select count(tipo), tipo from(" + 
				"select sku, num_lote, vendido, lote.sub_categoria as tipo " + 
				"from produto " + 
				"join lote " + 
				"on produto.num_lote = lote.numero_lote " + 
				"join sub_categoria " + 
				"on lote.sub_categoria = sub_categoria.sub_categoria " + 
				"where vendido=TRUE) as exemplo " + 
				"group by tipo";
		Statement stmt = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dataset.setValue(rs.getInt("count"), "PRODUTOS", rs.getString("tipo"));
				
				db.disconnect(); 
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());	
		}
		
        return dataset;
    }

    public JFreeChart criarGraficoVendasSubCategoria(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Vendas Sub-Categoria",
                "",
                "Produtos",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
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
    	DataBase db = DataBase.getInstance();
    	db.connect();
		String sql = "select idade, avg(salario) " + 
				"from funcionario " + 
				"group by idade";
		Statement stmt = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dataset.setValue(rs.getInt("avg"), "avg", rs.getString("idade"));
				
				db.disconnect(); 
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());	
		}
		
        return dataset;
    }
    
    public JFreeChart criarGraficoMediaSalario(CategoryDataset dataset) {
    	JFreeChart Chart = ChartFactory.createLineChart(
                "Média Salário por Idades",
                "idade",
                "avg",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return Chart;
    }

    
 //Vendas Por Dia  
    public ChartPanel VendasPorDia() {
    	
        CategoryDataset dataset = dadosVendasPorDia();
        JFreeChart chart = criarGraficoVendasPorDia(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        return chartPanel;
    }
    
    public CategoryDataset dadosVendasPorDia() {
    	DataBase db = DataBase.getInstance();
    	db.connect();
		String sql = "select count(data_saida), data_saida " + 
				"from produto " + 
				"WHERE vendido=true "+
				"group by data_saida " +
				"order by data_saida asc";
		Statement stmt = null;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dataset.setValue(rs.getInt("count"), "PRODUTOS", rs.getDate("data_saida"));	
				db.disconnect(); 
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());	
		}
		
      return dataset;
  }
    
    public JFreeChart criarGraficoVendasPorDia(CategoryDataset dataset) {
    	JFreeChart Chart = ChartFactory.createLineChart(
                "Vendas por dia",
                "Data de Saída",
                "Número de vendas",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return Chart;
    }
    

//Atividade por user
    public ChartPanel AtividadeUser() {
    	
        PieDataset dataset = dadosAtividadeUser();
        JFreeChart chart = criarGraficoAtividadeUser(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        return chartPanel;
    }
    
    public PieDataset dadosAtividadeUser() {
    	DataBase db = DataBase.getInstance();
    	db.connect();
		String sql = "select count(data), username " + 
				"from logs " + 
				"where cast(data as Date)=CURRENT_DATE " +
				"group by username";
		Statement stmt = null;
		DefaultPieDataset dataset = new DefaultPieDataset();
		try {
			stmt = db.getC().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				dataset.setValue(rs.getString("username"), rs.getInt("count"));	
				db.disconnect(); 
			}
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
	        System.err.println(e.getClass().getName()+": "+e.getMessage());	
		}
		
      return dataset;
  }
    
    public JFreeChart criarGraficoAtividadeUser(PieDataset dataset) {
    	JFreeChart Chart = ChartFactory.createPieChart(
                "Atividade Diária Utilizadores",
                dataset,
                false, true, false);

        return Chart;
    }
    
   
}
