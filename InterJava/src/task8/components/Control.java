package task8.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Control extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton add;
	private JButton sub;
	private JLabel label;
	private Integer valueInt;
	private JLabel value;
	
	
	public Control(ActionListener addAction, ActionListener subAction, String title) {
		
		super();
		add = new JButton("Add");
		sub = new JButton("Sub");
		add.addActionListener(addAction);
		sub.addActionListener(subAction);
		this.label = new JLabel(title+":");
		this.valueInt = 0;
		this.value = new JLabel(valueInt + "");
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEtchedBorder());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridheight = 2;
		add(label, c);
		add(value,c);
		
		c.gridheight = 1;
		add(add,c);
		
		c.gridx = 2;
		c.gridy = 1;
		add(sub,c);
		
	}
	
	public void setControlValue(int controlValue) {
		value.setText(controlValue + "");
	}
	

}
