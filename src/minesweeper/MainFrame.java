/**
 * 
 */
package minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.*;

import sun.security.provider.JavaKeyStore.CaseExactJKS;

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
	private static ImageIcon flagIcon = new ImageIcon(MainFrame.class.getResource("resources/flag.png"));
	private static ImageIcon oneIcon = new ImageIcon(MainFrame.class.getResource("resources/1.png"));
	private static ImageIcon twoIcon = new ImageIcon(MainFrame.class.getResource("resources/2.png"));
	private static ImageIcon threeIcon = new ImageIcon(MainFrame.class.getResource("resources/3.png"));
	
	private JPanel topPanel;
	private JPanel gamePanel;
	private JPanel bottomPanel;
	private JButton[][] fields;
	private JCheckBox flagCheckbox;
	private int bombsLeft = 10;
	private boolean gameOver = false;
	private int width = 9;
	private int height = 9;
	private JButton bombCountDisplay;
	
	public MainFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Gra");
		JMenuItem restartGameMenuItem = new JMenuItem("Nowa Gra");
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
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);
		
		topPanel = new JPanel();
		topPanel.add(new JLabel("Pozosta³e bomby: "));
		bombCountDisplay = new JButton(""+bombsLeft);
		bombCountDisplay.setEnabled(false);
		topPanel.add(bombCountDisplay);
		add(topPanel, BorderLayout.NORTH);
		
		gamePanel = new JPanel();
		gamePanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		add(gamePanel, BorderLayout.CENTER);
		bottomPanel = new JPanel();
		flagCheckbox = new JCheckBox("Ustawianie flagi", false);
		bottomPanel.add(flagCheckbox);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setTitle("Saper");
		initialize();
	}
	
	/**
	 * restartuje grê
	 */
	private void initialize() {
		gamePanel.setLayout(new GridLayout(width, height));
		fields = new JButton[width][height];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j<width; j++) {
				fields[j][i] = new JButton(new FieldClickedAction());
				fields[j][i].setFocusable(false);
				gamePanel.add(fields[j][i]);
			}
		}
		gamePanel.setPreferredSize(new Dimension(30*width, 25*height));
		pack();
		setResizable(false);
	}
	
	private void mineClicked() {
		gameOver = true;
		for(JButton[] column : fields) {
			for(JButton field : column) {
				field.getAction().putValue("visible", true);
			}
		}
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
				}
				bombCountDisplay.setText("" + bombsLeft);
			}
		}
		
		public void revealIcon() {
			if(getValue("bomb").equals("true"))
				putValue(SMALL_ICON, mineIcon);
			else {
				switch ((int)getValue("surroundingBombs")) {
				case 1: putValue(SMALL_ICON, oneIcon);					
					break;
				case 2: putValue(SMALL_ICON, twoIcon);
					break;
				case 3: putValue(SMALL_ICON, threeIcon);
				break;
				default: putValue(SMALL_ICON, null);
					break;
				}
			}
		}
	}
}
