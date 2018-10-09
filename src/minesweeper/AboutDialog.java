package minesweeper;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	public AboutDialog(JFrame owner) {
		super(owner, "O Programie", true);
		
		add(new JLabel("<html><h1>Saper</h1><br>"
				+ "<h7>Wersja 1.0</h7><hr>"
				+ "Copyright 2018 Jan Majchrzak</html>"), BorderLayout.CENTER);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(event->setVisible(false));
		JPanel panel = new JPanel();
		panel.add(okButton);
		
		add(panel, BorderLayout.SOUTH);
		pack();
		setBounds(getX(), getY(), getWidth()+50, getHeight()+20);
		setResizable(false);
	}

}
