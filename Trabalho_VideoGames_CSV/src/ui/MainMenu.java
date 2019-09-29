package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.MainMenuController;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = -9133954004722282024L;
	private MainMenuController ctrl;
	private JButton createInvertedIndexBtn,
			searchBtn;
	
	public MainMenu() {
		super("Inverted Index");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(700, 50);
		this.setSize(600, 600);

		this.setLayout(new FlowLayout());

		createInvertedIndexBtn = new JButton("Create Inverted");
		searchBtn = new JButton("Search");
		
		try {
			this.ctrl = new MainMenuController();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		this.add(createInvertedIndexBtn);
		this.add(searchBtn);
		
		createInvertedIndexBtn.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					try {
						ctrl.buildInvertedIndex();
						JOptionPane.showMessageDialog(null, "Feito!");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
	        }
		);
		
		searchBtn.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					try {
						String searchStr = JOptionPane.showInputDialog("Digite o texto para buscar: ");
						searchStr = Normalizer.normalize(searchStr, Normalizer.Form.NFD);
						String [][] rows = ctrl.searchBtn(searchStr);
						if(rows.length == 0) 
							JOptionPane.showMessageDialog(null, "Nenhum registro foi encontrado!");
						else
							new VideoGamesTable(rows);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
	        }
		);
	}
}
