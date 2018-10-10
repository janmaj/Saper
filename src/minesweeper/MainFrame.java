/**
 * 
 */
package minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;

/**
 * G³ówne okno gry
 * @author Jan Majchrzak
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			MainFrame mainFrame = new MainFrame();
			mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			mainFrame.setVisible(true);
		});
	}
	
	private static ImageIcon mineIcon = new ImageIcon(MainFrame.class.getResource("resources/mine.png"));
	private static ImageIcon plainIcon = new ImageIcon(MainFrame.class.getResource("resources/plain_field.png"));
	private static ImageIcon flagIcon = new ImageIcon(MainFrame.class.getResource("resources/flag.png"));
	private static ImageIcon oneIcon = new ImageIcon(MainFrame.class.getResource("resources/1.png"));
	private static ImageIcon twoIcon = new ImageIcon(MainFrame.class.getResource("resources/2.png"));
	private static ImageIcon threeIcon = new ImageIcon(MainFrame.class.getResource("resources/3.png"));
	private static ImageIcon fourIcon = new ImageIcon(MainFrame.class.getResource("resources/4.png"));
	private static ImageIcon fiveIcon = new ImageIcon(MainFrame.class.getResource("resources/5.png"));
	private static ImageIcon sixIcon = new ImageIcon(MainFrame.class.getResource("resources/6.png"));
	private static ImageIcon sevenIcon = new ImageIcon(MainFrame.class.getResource("resources/7.png"));
	private static ImageIcon eightIcon = new ImageIcon(MainFrame.class.getResource("resources/8.png"));
	private static ImageIcon smileyIcon = new ImageIcon(MainFrame.class.getResource("resources/smiley.png"));
	private static ImageIcon deadIcon = new ImageIcon(MainFrame.class.getResource("resources/dead.png"));
	private static ImageIcon coolIcon = new ImageIcon(MainFrame.class.getResource("resources/cool.png"));
	
	private JButton smileyButton;
	private JPanel gamePanel;
	private JButton[][] fields;
	private JCheckBox flagCheckbox;
	private int bombsLeft = 10;
	private int flaggedBombs = 0;
	private boolean gameOver = false;
	private int width = 9;
	private int height = 9;
	private int bombCount = 10;
	private AboutDialog aboutDialog;
	private DifficultyChangeDialog difficultyChangeDialog;
	
	public MainFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		aboutDialog = new AboutDialog(this);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Gra");
		JMenuItem restartGameMenuItem = new JMenuItem("Nowa Gra");
		restartGameMenuItem.addActionListener(event->initialize());
		JMenuItem difficultyMenuItem = new JMenuItem("Poziom trudnoœci...");
		difficultyMenuItem.addActionListener(event->{
			if(difficultyChangeDialog==null)
				difficultyChangeDialog = new DifficultyChangeDialog();
			int result = difficultyChangeDialog.showDialog(this, "Wybór poziomu trudnoœci");
			if(result==DifficultyChangeDialog.OK_OPTION) {
				DifficultyChangeDialog.DifficultySetting difficultySetting = difficultyChangeDialog.getDifficultySetting();
				width = difficultySetting.getWidth();
				height = difficultySetting.getHeight();
				bombCount = difficultySetting.getMineCount();
				initialize();
			}
		});
		gameMenu.add(difficultyMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Wyjœcie");
		exitMenuItem.addActionListener(event->{
			int result = JOptionPane.showConfirmDialog(this, "Czy na pewno chcesz wyjœæ?", "Chcesz wyjœæ?", JOptionPane.YES_NO_CANCEL_OPTION);
			if(result==JOptionPane.YES_OPTION)
				System.exit(0);
		});
		gameMenu.add(restartGameMenuItem);
		gameMenu.add(exitMenuItem);
		menuBar.add(gameMenu);
		
		JMenu helpMenu = new JMenu("Pomoc");
		JMenuItem aboutMenuItem = new JMenuItem("O programie");
		aboutMenuItem.addActionListener(event->aboutDialog.setVisible(true));
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);
		
		JPanel topPanel = new JPanel();
		smileyButton = new JButton(smileyIcon);
		smileyButton.setFocusable(false);
		smileyButton.addActionListener(event->initialize());
		smileyButton.setPreferredSize(new Dimension(30, 30));
		topPanel.add(smileyButton);
		add(topPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
		flagCheckbox = new JCheckBox("Ustawianie flagi", false);
		flagCheckbox.setFocusable(false);
		bottomPanel.add(flagCheckbox);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		setTitle("Saper");
		setIconImage(mineIcon.getImage());
		initialize();
	}
	
	/**
	 * restartuje grê
	 */
	private void initialize() {
		if(gamePanel!=null)
			remove(gamePanel);
		gamePanel = new JPanel();
		gamePanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		getContentPane().add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(height, width));
		fields = new JButton[width][height];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j<width; j++) {
				fields[j][i] = new JButton(new FieldClickedAction());
				fields[j][i].setFocusable(false);
				fields[j][i].setPreferredSize(new Dimension(26, 26));
				gamePanel.add(fields[j][i]);
			}
		}
		
		/**
		 * Ustawienie bomb w losowych polach
		 */
		Random random = new Random();
		for(int i = 0; i<bombCount; i++) {
			int p = random.nextInt(width);
			int q = random.nextInt(height);
			if((boolean)fields[p][q].getAction().getValue("bomb"))
				i--;
			else fields[p][q].getAction().putValue("bomb", true);
		}
		
		int[] adjacentIndexes = {1,-1, 1, 0, 1, 1, 0, -1, 0, 1, -1, -1, -1, 0, -1, 1};
		
		/**
		 * W tej pêtli sprawdzana jest iloœæ bomb otaczaj¹cych ka¿de pole
		 */
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j<height; j++) {
				int surroundingBombs = 0;
				for(int k = 0; k<adjacentIndexes.length-1; k+=2) {
					try {
						if((boolean)fields[i+adjacentIndexes[k]][j+adjacentIndexes[k+1]].getAction().getValue("bomb"))
							surroundingBombs++;
					}catch (ArrayIndexOutOfBoundsException e) {	}
					fields[i][j].getAction().putValue("surroundingBombs", surroundingBombs);
				}
			}
		}
		gameOver = false;
		bombsLeft = bombCount;
		flaggedBombs = 0;
		smileyButton.setIcon(smileyIcon);
		flagCheckbox.setSelected(false);
		pack();
		setResizable(false);
	}
	
	private void mineClicked() {
		gameOver = true;
		smileyButton.setIcon(deadIcon);
		for(JButton[] column : fields) {
			for(JButton field : column) {
				field.getAction().putValue("visible", true);
				((FieldClickedAction)field.getAction()).revealIcon(true);
			}
		}
	}
	
	private void solved() {
		gameOver = true;
		smileyButton.setIcon(coolIcon);
		for(JButton[] column : fields) {
			for(JButton field : column) {
				field.getAction().putValue("visible", true);
				((FieldClickedAction)field.getAction()).revealIcon(false);
			}
		}
		JOptionPane.showMessageDialog(this, "Gratulacje! Uda³o ci sie wygraæ", "Wygrana", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Akcja reprezentuj¹ca pole gry, reaguj¹ca na jego klikniêcie
	 * @author Jan Majchrzak
	 */
	class FieldClickedAction extends AbstractAction {

		public FieldClickedAction() {
			putValue("visible", false);
			putValue("bomb", false);
			putValue("flag", false);
			putValue("surroundingBombs", 0);
			putValue(SMALL_ICON, null);
		}
		
		public void actionPerformed(ActionEvent action) {
			if(gameOver||(boolean)getValue("visible"))
				return;
			
			if(flagCheckbox.isSelected()) {
				if((boolean)getValue("flag")) {
					putValue("flag", false);
					putValue(SMALL_ICON, null);
					bombsLeft++;
				}
				else if(bombsLeft>0){
					putValue("flag", true);
					putValue(SMALL_ICON, flagIcon);
					bombsLeft--;
					if((boolean)getValue("bomb"))
						flaggedBombs++;
					if(flaggedBombs==bombCount)
						solved();
				}
			}
			else {
				if((boolean)getValue("flag"))
					return;
				if((boolean)getValue("bomb"))
					mineClicked();
				else 
					revealSurroundingFields();
			}
		}
		
		public void revealIcon(boolean revealFlags) {
			if(!revealFlags&&(boolean)getValue("flag"))
				return;
			if((boolean)getValue("bomb"))
				putValue(SMALL_ICON, mineIcon);
			else {
				switch ((int)getValue("surroundingBombs")) {
				case 1: putValue(SMALL_ICON, oneIcon);					
					break;
				case 2: putValue(SMALL_ICON, twoIcon);
					break;
				case 3: putValue(SMALL_ICON, threeIcon);
					break;
				case 4: putValue(SMALL_ICON, fourIcon);
					break;
				case 5: putValue(SMALL_ICON, fiveIcon);
					break;
				case 6: putValue(SMALL_ICON, sixIcon);
					break;
				case 7: putValue(SMALL_ICON, sevenIcon);
					break;
				case 8: putValue(SMALL_ICON, eightIcon);
					break;
				default: putValue(SMALL_ICON, plainIcon);
					break;
				}
			}
		}
		
		public void revealSurroundingFields() {
			int[] adjacentIndexes = {1,-1, 1, 0, 1, 1, 0, -1, 0, 1, -1, -1, -1, 0, -1, 1};
			if((boolean)getValue("visible"))
				return;
			
			putValue("visible", true);
			revealIcon(false);
			if((int)getValue("surroundingBombs")==0) {
				int k=0, l=0;
				/**
				 * Wyszukanie odpowiedniego pola w tablicy fields
				 */
				for(int i = 0; i < width; i++)
				{
					for(int j = 0; j<height; j++) {
						if(fields[i][j].getAction()==this) {
							k = i;
							l = j;
						}
					}
				}
				
				/**
				 * Rekurencyjne wywo³anie funkcji ods³aniaj¹cej
				 */
				for(int i = 0; i<adjacentIndexes.length-1; i+=2) {
					try {
						if(!(boolean)fields[k+adjacentIndexes[i]][l+adjacentIndexes[i+1]].getAction().getValue("flag"))
							((FieldClickedAction)fields[k+adjacentIndexes[i]][l+adjacentIndexes[i+1]].getAction()).revealSurroundingFields();
					}catch (ArrayIndexOutOfBoundsException e) {	}
				}
			}
		}
	}
}
