package task9.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Status extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textLabel;
	private JLabel valueLabel;
	
	public Status(String text, String startingValue) {
		
		super();
		textLabel = new JLabel(text + ":");
		valueLabel = new JLabel(startingValue);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(textLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(valueLabel,c);
		
		
	}
	
	public void setValue(String value) {
		valueLabel.setText(value);
	}

	public JLabel getTextLabel() {
		return textLabel;
	}

	
	
	
}
