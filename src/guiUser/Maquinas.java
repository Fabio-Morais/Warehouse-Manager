package guiuser;
import logic.Check;
import logic.MessageLogs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import db.DataBase;
import gui.PopUp;
import javax.swing.ImageIcon;

public class Maquinas {
	private static final String REPORT = "/reportMaquina.png";

	private DataBase db;
	private MessageLogs messageLogs;
	private JTextArea descmaqField;
	private PopUp popUp;
	private Check check;	
	private String loginUsername;

	
	public Maquinas(String username) {
		this.popUp = new PopUp();
		this.check = new Check();
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
	}
	
	private int showReportAvariaPopUp() {
		Object[] options1 = { "Recebido", "Sair" };
		ImageIcon icon = new ImageIcon(userDesign.class.getResource(REPORT));
		
		JSplitPane splitPane = new JSplitPane();
		
		
		JLabel lbldescmaq = new JLabel("Descricao");
		lbldescmaq.setFont(new Font("Tahoma", Font.BOLD, 11));
		JScrollPane scrollPane = new JScrollPane();
		descmaqField = new  JTextArea(4, 20);
		scrollPane.setViewportView(descmaqField);
		splitPane.setLeftComponent(lbldescmaq);
		
		splitPane.setRightComponent(scrollPane);
		splitPane.setEnabled(false);
		
		return JOptionPane.showOptionDialog(null, splitPane, "Reportar Avaria", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, icon, options1, options1[0]);
	}
	
	public void refreshMaquinas(DefaultTableModel modelMaquinas,boolean tglbtnFiltrarAvariadasisSelected, String nomeArmazem) {
		int rowcont = modelMaquinas.getRowCount();
		for (int i=rowcont-1;i>=0;i--) {
			modelMaquinas.removeRow(i);
		}
		if(tglbtnFiltrarAvariadasisSelected)
			db.nomenumeroAvariadasMaquina( nomeArmazem,modelMaquinas);
		else	
			db.nomenumeroestadoMaquina(nomeArmazem,modelMaquinas);
	}
	
	public void reportAvariaMaquina(JTable maquinaTable, DefaultTableModel modelMaquina,boolean tglbtnFiltrarAvariadasisSelected, String nomeArmazem) {

		boolean isFinished= false;
		int[] indexOfRow = maquinaTable.getSelectedRows();
		if (check.multipleSelection(indexOfRow)) {
			popUp.showPopUp("Selecione apenas 1 linha", "Erro ao selecionar");
			return;
		}
		while (!isFinished) {
			int result = showReportAvariaPopUp();// mostra o pop up
			if (result == JOptionPane.YES_OPTION) {
				isFinished = true;
			} else{
				break;
			}
		}

		if(isFinished) {
			/*Saber se maquina avarida e id armazem*/
			String nomeMaquina = maquinaTable.getValueAt(indexOfRow[0], 0).toString();
			String id=maquinaTable.getValueAt(indexOfRow[0], 1).toString();
			String descricao = descmaqField.getText();
			db.updateavariadoMaquina(nomeArmazem, id, descricao);
			messageLogs.reportaAvaria(loginUsername, false, nomeMaquina, id, descricao, nomeArmazem);
			refreshMaquinas(modelMaquina,tglbtnFiltrarAvariadasisSelected, nomeArmazem);
		}
	}

	public void corrigeAvaria(JTable maquinaTable, DefaultTableModel modelMaquina, String nomeArmazem) {
		int[] indexOfRow = maquinaTable.getSelectedRows();
		for(int i=0; i < indexOfRow.length; i++) {
			String nomeMaquina = maquinaTable.getValueAt(indexOfRow[i], 0).toString();
			String id = maquinaTable.getValueAt(indexOfRow[i], 1).toString();
			
			db.corrigeAvariaMaquina(id);
			messageLogs.corrigeAvaria(loginUsername, false, nomeMaquina, id, nomeArmazem);
			modelMaquina.setValueAt(false, indexOfRow[i], 2);
		}
	}
	
	
}
