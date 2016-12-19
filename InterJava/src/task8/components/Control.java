package task8.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
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
	
	
	public Control(ActionListener addAction, ActionListener subAction, String title, Integer startValue) {
		
		super();
		add = new JButton("Add");
		sub = new JButton("Sub");
		add.addActionListener(addAction);
		add.addActionListener(e -> {
			valueInt++;
			value.setText(valueInt+"");
		});
		sub.addActionListener(subAction);
		sub.addActionListener(e -> {
			if(valueInt==0) {
				return;
			}
			valueInt--;
			value.setText(valueInt+"");
		});
		this.label = new JLabel(title+":");
		this.valueInt = startValue;
		this.value = new JLabel(startValue + "");
		
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
	

}
