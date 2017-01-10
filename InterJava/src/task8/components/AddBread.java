package task8.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import task8.Grocery;

public class AddBread extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final String text = "Add bread manually";
	private JButton add;
	private JLabel label;
	private JSpinner spinner;
	
	
	public AddBread(Grocery grocery, int startValue) {
		
		super();
		add = new JButton("Add");
		spinner = new JSpinner(new SpinnerNumberModel(startValue, 0, 100, 1));
		add.addActionListener(e -> {
			grocery.addBreadManually((Integer)spinner.getValue() + grocery.getBreadsInStore());
		});
		this.label = new JLabel(text);
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEtchedBorder());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		add(label, c);
		add(spinner,c);
		add(add,c);
		
		
		
		
	}
	
	

}
