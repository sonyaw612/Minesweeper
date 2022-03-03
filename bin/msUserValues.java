import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class msUserValues extends JFrame implements ActionListener {
	
	private JPanel userPanel;
	private JLabel title, title2, jlRow, jlCol, jlBomb, placeHolder, placeHolder2;		
	private JFormattedTextField jtRow, jtCol, jtBomb;
	private JButton defaultButton;
	private JButton enter;
	private int row = -1;
	private int col = -1;
	private int bomb = -1;
	private int subBoolean = 1;
	private MineSweeperGUI play;
	private JLabel recommend, placeHolder3, rec2, warning, warning2;
	
	public msUserValues() {
		userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(8, 2));
		
		Font textFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
		Font jlFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		Font recFont = new Font(Font.MONOSPACED, Font.ITALIC, 11);
		
		title = new JLabel("   MineSweeper");
		title2 = new JLabel("  Specifications");
		title.setFont(textFont);
		title2.setFont(textFont);
		
		recommend = new JLabel("   Recommended to use same");
		rec2 = new JLabel("# for rows and columns");
		rec2.setFont(recFont);
		recommend.setFont(recFont);
		
		jlRow = new JLabel("     Rows:");
		jlCol = new JLabel("     Columns:");
		jlBomb = new JLabel("     Bombs:");
		jlRow.setFont(jlFont);
		jlCol.setFont(jlFont);
		jlBomb.setFont(jlFont);
		
		warning = new JLabel("  Entering values less than");
		warning2 = new JLabel("3 results in default values");
		warning.setFont(recFont);
		warning2.setFont(recFont);
		
		placeHolder = new JLabel();
		placeHolder2 = new JLabel();
		placeHolder3 = new JLabel();
		jtRow = new JFormattedTextField();
		jtCol = new JFormattedTextField();
		jtBomb = new JFormattedTextField();
		
		defaultButton = new JButton("Default");
		defaultButton.addActionListener(this);
		
		enter = new JButton("Enter");
		enter.addActionListener(this);
		
		userPanel.add(title);
		userPanel.add(placeHolder);
		userPanel.add(title2);
		userPanel.add(placeHolder3);
		userPanel.add(recommend);
		userPanel.add(rec2);
		userPanel.add(warning);
		userPanel.add(warning2);
		userPanel.add(jlRow);
		userPanel.add(jtRow);
		userPanel.add(jlCol);
		userPanel.add(jtCol);
		userPanel.add(jlBomb);
		userPanel.add(jtBomb);
		userPanel.add(defaultButton);
		userPanel.add(enter);
		add(userPanel);
		
		setVisible(true);
		setSize(400, 250);
		setTitle("MineSweeper");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public int getRows() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getBomb() {
		return bomb;
	}
	
	public void setValues() throws Exception {
		try {
			row = Integer.parseInt(jtRow.getText().trim());
			col = Integer.parseInt(jtCol.getText().trim());
			bomb = Integer.parseInt(jtBomb.getText().trim());

		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Please enter a valid number, type 0 to enter default row/column/bomb", "Error", JOptionPane.WARNING_MESSAGE);
			
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton)e.getSource();
		if(btnClicked.equals(defaultButton)) {
			row = 10;
			col = 10;
			bomb = 20;
			JOptionPane.showMessageDialog(null, "You have set your values: \nRow: " + row + "\nColumns: " + col + "\nNumber of Bombs: " + bomb);
			play = new MineSweeperGUI(row, col, bomb);
			play.setVisible(true);
			setVisible(false);
		}
		else if(btnClicked.equals(enter)) {
			try {
				setValues();
			}catch(Exception f) {
			}
			if((row >= 0) && (col >= 0) && (bomb >= 0)) {
				if(row < 3) {
					row = 10;
				}
				if(col < 3) {
					col = 10;
				}
				if(bomb < 3) {
					bomb = row;
				}
				if((bomb == 0) && (col == 0) && (row == 0)) {
					row = 10;
					col = 10;
					bomb = 10;
				}
				JOptionPane.showMessageDialog(null, "You have set your values: \nRow: " + row + "\nColumns: " + col + "\nNumber of Bombs: " + bomb);
				play = new MineSweeperGUI(row, col, bomb);
				play.setVisible(true);
				setVisible(false);
			}
		}
		
	}
	
	
	

}
