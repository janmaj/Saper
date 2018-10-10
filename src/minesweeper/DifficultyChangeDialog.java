package minesweeper;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class DifficultyChangeDialog extends JPanel{
	public static final int OK_OPTION = 1;
	public static final int CANCEL_OPTION = 0;
	public static final int ERROR_OPTION = -1;
	
	private JTextField widthField;
	private JTextField heightField;
	private JTextField minesField;
	private JRadioButton beginnerButton;
	private JRadioButton intermediateButton;
	private JRadioButton expertButton;
	private JRadioButton customButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDialog dialog;
	private int selectedOption;
	
	public DifficultyChangeDialog() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 14, -29, -62, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSzeroko = new JLabel("Szeroko\u015B\u0107");
		GridBagConstraints gbc_lblSzeroko = new GridBagConstraints();
		gbc_lblSzeroko.insets = new Insets(0, 0, 5, 5);
		gbc_lblSzeroko.gridx = 1;
		gbc_lblSzeroko.gridy = 0;
		add(lblSzeroko, gbc_lblSzeroko);
		
		JLabel lblWysoko = new JLabel("Wysoko\u015B\u0107");
		GridBagConstraints gbc_lblWysoko = new GridBagConstraints();
		gbc_lblWysoko.insets = new Insets(0, 0, 5, 5);
		gbc_lblWysoko.gridx = 2;
		gbc_lblWysoko.gridy = 0;
		add(lblWysoko, gbc_lblWysoko);
		
		JLabel lblMiny = new JLabel("Miny");
		GridBagConstraints gbc_lblMiny = new GridBagConstraints();
		gbc_lblMiny.insets = new Insets(0, 12, 5, 12);
		gbc_lblMiny.gridx = 3;
		gbc_lblMiny.gridy = 0;
		add(lblMiny, gbc_lblMiny);
		
		beginnerButton = new JRadioButton("Pocz\u0105tkuj\u0105cy");
		buttonGroup.add(beginnerButton);
		GridBagConstraints gbc_beginnerButton = new GridBagConstraints();
		gbc_beginnerButton.anchor = GridBagConstraints.WEST;
		gbc_beginnerButton.insets = new Insets(0, 0, 5, 5);
		gbc_beginnerButton.gridx = 0;
		gbc_beginnerButton.gridy = 1;
		add(beginnerButton, gbc_beginnerButton);
		
		JLabel label = new JLabel("9");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		JLabel label_1 = new JLabel("9");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		add(label_1, gbc_label_1);
		
		JLabel label_2 = new JLabel("10");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 3;
		gbc_label_2.gridy = 1;
		add(label_2, gbc_label_2);
		
		intermediateButton = new JRadioButton("\u015Aredniozaawansowany");
		buttonGroup.add(intermediateButton);
		GridBagConstraints gbc_IntermediateButton = new GridBagConstraints();
		gbc_IntermediateButton.anchor = GridBagConstraints.WEST;
		gbc_IntermediateButton.insets = new Insets(0, 0, 5, 5);
		gbc_IntermediateButton.gridx = 0;
		gbc_IntermediateButton.gridy = 2;
		add(intermediateButton, gbc_IntermediateButton);
		
		JLabel label_3 = new JLabel("16");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 2;
		add(label_3, gbc_label_3);
		
		JLabel label_4 = new JLabel("16");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 2;
		add(label_4, gbc_label_4);
		
		JLabel label_5 = new JLabel("40");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 0);
		gbc_label_5.gridx = 3;
		gbc_label_5.gridy = 2;
		add(label_5, gbc_label_5);
		
		expertButton = new JRadioButton("Ekspert");
		buttonGroup.add(expertButton);
		GridBagConstraints gbc_expertButton = new GridBagConstraints();
		gbc_expertButton.anchor = GridBagConstraints.WEST;
		gbc_expertButton.insets = new Insets(0, 0, 5, 5);
		gbc_expertButton.gridx = 0;
		gbc_expertButton.gridy = 3;
		add(expertButton, gbc_expertButton);
		
		JLabel label_6 = new JLabel("16");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 3;
		add(label_6, gbc_label_6);
		
		JLabel label_7 = new JLabel("30");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 2;
		gbc_label_7.gridy = 3;
		add(label_7, gbc_label_7);
		
		JLabel label_8 = new JLabel("99");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 5, 0);
		gbc_label_8.gridx = 3;
		gbc_label_8.gridy = 3;
		add(label_8, gbc_label_8);
		
		customButton = new JRadioButton("W\u0142asny");
		buttonGroup.add(customButton);
		GridBagConstraints gbc_customButton = new GridBagConstraints();
		gbc_customButton.anchor = GridBagConstraints.WEST;
		gbc_customButton.insets = new Insets(0, 0, 5, 5);
		gbc_customButton.gridx = 0;
		gbc_customButton.gridy = 4;
		add(customButton, gbc_customButton);
		
		widthField = new JTextField();
		widthField.setColumns(3);
		GridBagConstraints gbc_widthField = new GridBagConstraints();
		gbc_widthField.insets = new Insets(0, 0, 5, 5);
		gbc_widthField.gridx = 1;
		gbc_widthField.gridy = 4;
		add(widthField, gbc_widthField);
		
		heightField = new JTextField();
		GridBagConstraints gbc_Height = new GridBagConstraints();
		gbc_Height.insets = new Insets(0, 0, 5, 5);
		gbc_Height.gridx = 2;
		gbc_Height.gridy = 4;
		add(heightField, gbc_Height);
		heightField.setColumns(3);
		
		minesField = new JTextField();
		GridBagConstraints gbc_minesField = new GridBagConstraints();
		gbc_minesField.insets = new Insets(0, 0, 5, 0);
		gbc_minesField.gridx = 3;
		gbc_minesField.gridy = 4;
		add(minesField, gbc_minesField);
		minesField.setColumns(3);
		
		JButton okButton = new JButton("Nowa gra");
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.anchor = GridBagConstraints.EAST;
		gbc_okButton.insets = new Insets(0, 0, 0, 5);
		gbc_okButton.gridx = 0;
		gbc_okButton.gridy = 5;
		add(okButton, gbc_okButton);
		okButton.addActionListener(event->{
			if(customButton.isSelected()) {
				int width = Integer.parseInt(widthField.getText());
				int height = Integer.parseInt(heightField.getText());
				int mines = Integer.parseInt(minesField.getText());
				if(width<6||width>35||height<6||height>30||mines/(width*height)<0.1||mines/(width*height)>0.3) {
					JOptionPane.showConfirmDialog(this, "Wprowadzono niew³aœciwe dane", "B³¹d", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			selectedOption = OK_OPTION;
			setVisible(false);
			dialog.setVisible(false);						
		});
		
		JButton cancelButton = new JButton("Anuluj");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.WEST;
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.gridx = 1;
		gbc_cancelButton.gridy = 5;
		add(cancelButton, gbc_cancelButton);
		cancelButton.addActionListener(event->{
			selectedOption = CANCEL_OPTION;
			setVisible(false);
			dialog.setVisible(false);
		});
	}
	
	public int showDialog(Frame parent, String title) {
		selectedOption = ERROR_OPTION;
		
		if(dialog==null||dialog.getParent()!=parent) {
			dialog = new JDialog(parent, true);
			dialog.setTitle(title);
			dialog.add(this);
			dialog.pack();
		}
		setVisible(true);
		dialog.setVisible(true);
		return selectedOption;
	}
	
	public DifficultySetting getDifficultySetting() {
		DifficultySetting difficultySetting = new DifficultySetting();
		if(beginnerButton.isSelected()) {
			difficultySetting.setHeight(9);
			difficultySetting.setWidth(9);
			difficultySetting.setMineCount(10);
		}else if(intermediateButton.isSelected()) {
			difficultySetting.setHeight(16);
			difficultySetting.setWidth(16);
			difficultySetting.setMineCount(40);
		} else if(expertButton.isSelected()) {
			difficultySetting.setHeight(16);
			difficultySetting.setWidth(30);
			difficultySetting.setMineCount(99);
		} else {
			difficultySetting.setWidth(Integer.parseInt(widthField.getText()));
			difficultySetting.setHeight(Integer.parseInt(heightField.getText()));
			difficultySetting.setMineCount(Integer.parseInt(minesField.getText()));
		}
		
		return difficultySetting;
	}
	
	public static class DifficultySetting {
		private int width;
		private int height;
		private int mineCount;
		
		public int getWidth() {
			return width;
		}
		protected void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		protected void setHeight(int height) {
			this.height = height;
		}
		public int getMineCount() {
			return mineCount;
		}
		protected void setMineCount(int mineCount) {
			this.mineCount = mineCount;
		}
	}
}
