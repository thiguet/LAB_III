package ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class VideoGamesTable extends JFrame{
	private static final long serialVersionUID = 6293642844817217471L;
	private JTable table;
	private final String column_names[] = {
		"Id",
		"Rank",
		"Name",
		"Platform",
		"Year",
		"Genre",
		"Publisher",
		"NA Sales",
		"EU Sales",
		"JP Sales",
		"Other Sales",
		"Global Sales"
	};
	
	public VideoGamesTable(String[][] rows) {
		super("Inverted Index");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(700, 50);
		this.setSize(600, 600);

		this.setLayout(new BorderLayout (2, 2));
		
		this.table = new JTable(new DefaultTableModel(column_names, 0));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();	
		
		this.setTableColsWidth(this.table);
		
		for(int i = 0; i < rows.length; i++)
			model.addRow(rows[i]);
		
		this.add(new JScrollPane(table));
		this.table.setEnabled(false);
		
		this.setVisible(true);
		table.setVisible(true);
		
		table.setAutoCreateRowSorter(true);
	}
	
	private void setTableColsWidth(JTable table) {
		TableColumn column; 
		
		// Rank
		column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(102);

		// Name
		column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(400);

    	// Genre
		column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(170);
    
        // Platform
		column = table.getColumnModel().getColumn(6);
        column.setPreferredWidth(200);
	}
	
}
