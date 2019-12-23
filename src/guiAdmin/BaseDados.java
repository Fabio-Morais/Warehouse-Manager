package guiAdmin;


import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import db.DataBase;
import gui.PopUp;
import logic.Check;
import logic.MessageLogs;

public class BaseDados {
	private DataBase db;// DATA BASE
	private MessageLogs messageLogs;

	private static final String CATEGORIA = "/list.png";
	private PopUp popUp;
	private Check check;

	private String loginUsername;

	public BaseDados(String username) {
		this.popUp = new PopUp();
		this.check = new Check();
		db = DataBase.getInstance();
		messageLogs = MessageLogs.getInstance();
		this.loginUsername = username;
	}
	
	private boolean confirmData(String user, String password, String url) {
		/* Confirma dados */
		if (check.blankText(user)) {
			popUp.showPopUp("É necessario escolher o user", "User");
			return false;
		} else if (check.blankText(password)) {
			popUp.showPopUp("É necessario escolher o password", "Password");
			return false;
		} else if (check.blankText(url)) {
			popUp.showPopUp("É necessario escolher o url", "Url");
			return false;
		} 
		return true;
	}
	
	public void testDataBase(JLabel lblStatusDb) {
		if (db.connect()) {
			lblStatusDb.setText("ON");
			lblStatusDb.setForeground(Color.GREEN);
			popUp.showPopUpDataBaseSucess();
			db.disconnect();
		}else {
			lblStatusDb.setText("OFF");
			lblStatusDb.setForeground(Color.RED);
			popUp.showPopUpDataBaseError2();
		}
	}
	
	public void adicionar(String user, String password, String url, String nomeArmazem) {
		confirmData(user, password, url);
		String userAntigo =db.getUser();
		String urlAntigo =db.getUrl();
		String passAntigo =db.getPassword();
		String dados= userAntigo+";"+urlAntigo+";"+passAntigo;
		String dadosNovos = user+";"+password+";"+url;
		if(db.checkConnection())
			messageLogs.editaBaseDados(loginUsername, true, dados, dadosNovos, nomeArmazem);
		
		db.setUser(user);
		db.setPassword(password);
		db.setUrl(url);
		
		if(db.checkConnection())
			messageLogs.editaBaseDados(loginUsername, true, dados, dadosNovos, nomeArmazem);
		
	}

}
