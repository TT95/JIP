package task9.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import task9.RatStatus;

public class RatsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Status> statuses;
	
	private GridBagConstraints c;
	
	public RatsPanel() {
		statuses = new ArrayList<>();
		
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.ipadx = 5;
		c.ipady = 5;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.weighty = 1.0;
	}

	public Status addRat(String name) {
		Status ratStatus = new Status(name, RatStatus.ALIVE.toString());
		ratStatus.setBorder(BorderFactory.createEtchedBorder());
		addStatusToPanel(ratStatus);
		return ratStatus;
	}
	
	private void addStatusToPanel(Status ratStatus) {
		statuses.add(ratStatus);
		add(ratStatus,c);
		
	}
	
	public void removeRat(Status ratStatus) {
		statuses.remove(ratStatus);
		remove(ratStatus);
		repaint();
	}
	
	public Integer getNumberOfRats() {
		return statuses.size();
	}
	
}
