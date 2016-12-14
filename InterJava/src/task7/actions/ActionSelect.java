package task7.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import task7.PopularNameGUI;

public class ActionSelect extends AbstractAction {
	
	private PopularNameGUI gui;
	
	public ActionSelect(PopularNameGUI gui) {
		this.gui=gui;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<String> selected = gui.getLeftList().getSelectedValuesList();
		for(String name : selected){
			gui.getRightListModel().addElement(name);
		}
	}

}
