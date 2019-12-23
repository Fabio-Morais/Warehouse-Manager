package logic;

import java.awt.EventQueue;

import guiAdmin.AdminDesign;
import guiLogin.LoginDesign;
import guiUser.userDesign;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*AdminDesign window = new AdminDesign("warehouse1");
					window.getFrmMenuAdmin().setVisible(true);*/

					LoginDesign window1 = new LoginDesign();
					window1.getFrmLogin().setVisible(true);
					
					/*userDesign window2 = new userDesign("warehouse1");
					window2.getFrmUserDesign().setVisible(true);*/
					
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}

}
