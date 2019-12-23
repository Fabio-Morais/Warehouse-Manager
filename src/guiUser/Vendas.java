package guiuser;
import javax.swing.table.DefaultTableModel;
import db.DataBase;
public class Vendas {

	private DataBase db;
	
	public Vendas() {
		db = DataBase.getInstance();
	}
	
	public void refreshVendas(DefaultTableModel modelVendas){
		int rowcont = modelVendas.getRowCount();
		for (int i=rowcont-1;i>=0;i--) {
			modelVendas.removeRow(i);
		}
		db.connect();
		db.produtoVendido(modelVendas);
		db.disconnect();
	}

}
