package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
public class Help {
	private static final String HELP = "/help.png";

	
	private void showUserHtml(JFrame frmMenuAdmin) {
		  // create jeditorpane
        JEditorPane jEditorPane = new JEditorPane();
        
        // make it read-only
        jEditorPane.setEditable(false);
        
        // create a scrollpane; modify its attributes as desired
        JScrollPane scrollPane = new JScrollPane(jEditorPane);
        
        // add an html editor kit
        HTMLEditorKit kit = new HTMLEditorKit();
        jEditorPane.setEditorKit(kit);
        
        // add some styles to the html
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
        styleSheet.addRule("h1 {color: blue;}");
        styleSheet.addRule("h2 {color: #ff0000;}");
        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

        String prefsFileAsString = this.getClass().getResource(HELP).toString();

        // create some simple html as a string
        String htmlString = "<html>\n"
                          + "<body>\n"
                          + "<h1>HELP USER!</h1>\n"
                          + "<h2>Escrever ajuda</h2>\n"
                          + "<p>Easy</p>\n"
                          + "<p><a href=\"http://devdaily.com/blog/\">devdaily blog</a></p>\n"
                          + "<img src='"+prefsFileAsString+"' alt=\"Smiley face\" height=\"100\" width=\"100\">\r\n"
                          + "</body>\n"
        				  + "</html>\n";

        // create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(htmlString);

        // now add it all to a frame
        JDialog  j = new JDialog (frmMenuAdmin,"ASD");
        j.setModal(true);
        j.getContentPane().add(scrollPane, BorderLayout.CENTER);
        URL iconURL = getClass().getResource(HELP);

		ImageIcon img = new ImageIcon(iconURL);
		j.setIconImage(img.getImage());

        // make it easy to close the application
        j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // display the frame
        j.setSize(new Dimension(500,400));
        
        // pack it, if you prefer
        //j.pack();
        
        // center the jframe, then make it visible
        j.setLocationRelativeTo(null);
        j.setVisible(true);
	}

	public void selectHelp(JFrame frmMenuAdmin, String currentPanel) {
		switch (currentPanel) {
		case "menu_admin":
			
			break;
		case "user":
			showUserHtml(frmMenuAdmin);
			break;
		case "categoria_produto":
			
			break;
		case "fornecedores":
			
			break;
		case "maquina":
			
			break;
		case "funcionario":
			
			break;
		case "base_dados":
			
			break;


		default:
			break;
		}
	}
}
