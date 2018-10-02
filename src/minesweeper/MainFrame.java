/**
 * 
 */
package minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;

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
	
	private JPanel topPanel;
	private JPanel gamePanel;
	private JPanel bottomPanel;
	private JButton[][] fields;
	private JCheckBox flagCheckbox;
	private int bombsLeft = 0;
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
		bombCountDisplay = new JButton("0");
		bombCountDisplay.setEnabled(false);
		topPanel.add(bombCountDisplay);
		add(topPanel, BorderLayout.NORTH);
		
		gamePanel = new JPanel();
		gamePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(gamePanel, BorderLayout.CENTER);
		bottomPanel = new JPanel();
		flagCheckbox = new JCheckBox("Ustawianie flagi", false);
		bottomPanel.add(flagCheckbox);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setTitle("Saper");
		pack();
	}
	
	/**
	 * Akcja reprezentuj¹ca pole gry, reaguj¹ca na jego klikniêcie
	 * @author Jan Majchrzak
	 */
	class FieldClickedAction extends AbstractAction {

		public FieldClickedAction() {
			putValue("visible", "false");
			putValue("bomb", "false");
			putValue("flag", "false");
			putValue("surroundingBombs", "0");
		}
		
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}
}
