package task7.actions;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ListModel;

import task7.PopularNameGUI;
import task7.strategy.StringCriteria;


public class ActionSelectionCriteriaString  extends AbstractAction {
	
	private PopularNameGUI gui;
	private StringCriteria criteria;
	private String matchString;
	
	public ActionSelectionCriteriaString(PopularNameGUI gui, StringCriteria criteria) {
		this.gui=gui;
		this.criteria=criteria;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		matchString = gui.getMatchStringTyped();
		gui.getRightListModel().removeAllElements();
		ListModel<String> model = gui.getLeftListModel();
		for(int index=0; index< model.getSize();index++) {
			String name = model.getElementAt(index);
			if(criteria.satisfies(name,matchString)) {
				gui.getRightListModel().addElement(name);
			}
		}
		gui.stateChanged();
	}

}
