package task9.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class Control extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final String periodString = "Sleep period[ms]:";
	private JButton add;
	private JButton sub;
	private JLabel label;
	private Integer valueInt;
	private JLabel value;
	private JSpinner spinner;
	
	
	public Control(ActionListener addAction, ActionListener subAction, ChangeListener freqAction, int freqValue, String title) {
		
		super();
		add = new JButton("Add");
		sub = new JButton("Sub");
		spinner = new JSpinner(new SpinnerNumberModel(freqValue, 0, 10000000, 100));
		add.addActionListener(addAction);
		sub.addActionListener(subAction);
//		add.addActionListener(repaintActionListener(this));
//		sub.addActionListener(repaintActionListener(this));
		spinner.addChangeListener(freqAction);
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
		
		c.gridx = 0;
		c.gridy = 3;
		add(new JLabel(periodString),c);
		
		c.gridx = 1;
		c.gridy = 3;
		add(spinner,c);
		
		
	}
	
//	private ActionListener repaintActionListener(JPanel panel) {
//		return new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				panel.repaint();
//			}
//		};
//	}
	
	public void setControlValue(int controlValue) {
		value.setText(controlValue + "");
	}
	

}
