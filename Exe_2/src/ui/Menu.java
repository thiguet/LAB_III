package ui;
import javax.swing.JOptionPane;

public class Menu {
	public Menu() {}

	public static String displayMenu () {
		return JOptionPane.showInputDialog(null, "1 - Para Cadastrar Médicos: ");
	}	
}
