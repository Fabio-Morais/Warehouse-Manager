package guiUser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import db.DataBase;
import logic.MessageLogs;

public class Produtos {

	private DataBase db;
	private MessageLogs messageLogs;
	private String loginUsername;

	public Produtos(String username) {
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
	}
	
	public void marcarDefeito(JTable tableProdutos, DefaultTableModel modelProdutos, String nomeArmazem) {
		int[] selectedRows = tableProdutos.getSelectedRows();

		for (int i = selectedRows.length - 1; i >= 0; i--) {
			String sku = modelProdutos.getValueAt(tableProdutos.convertRowIndexToModel(selectedRows[i]), 0).toString();
			db.marcarDefeitoProduto(sku);
			messageLogs.reportaDefeito(loginUsername, false, sku, nomeArmazem);
		}
		refreshProdutos(modelProdutos);
	}
	
	public void refreshProdutos(DefaultTableModel modelProdutos){
		int rowcont = modelProdutos.getRowCount();
		for (int i=rowcont-1;i>=0;i--) {
			modelProdutos.removeRow(i);
		}
		db.produtoNaoVendido(modelProdutos);
	}
}
