package task7;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;




import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;




import start.PopularNames;
import task7.actions.ActionSelect;
import task7.actions.ActionSelectionCriteriaString;

public class PopularNameGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	
	private DefaultListModel<String> rightListModel;
	private DefaultListModel<String> leftListModel;
	private String matchStringTyped="";

	private JList<String> rightList;
	private JList<String> leftList;

	
	private JLabel nameOfSelectedFile;
	private JLabel numberOfNames;
	private JLabel numberOfSelectedNames;
	
	private String selectedFileName;
	
	private static final String selectedFileText = "Selected File: ";
	private static final String numberOfNamesText = "Total names: ";
	private static final String numberOfSelectedNamesText = "Names selected: ";

	
	private static final String defaultpopularNamesFile = "res/PopularNames.txt";



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopularNameGUI frame = new PopularNameGUI();
					frame.pack();
					frame.setVisible(true);
					frame.setSize(800, 500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public PopularNameGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnLoad = new JMenu("Load");
		menuBar.add(mnLoad);
	

		JMenuItem mntmPropertyFile = new JMenuItem("New student data file");
		mntmPropertyFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {	
						File file = fc.getSelectedFile();
						loadpopularNameFile(file.getPath());
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Problem reading names file!");
					}
				}
			}
		});
		
		mnLoad.add(mntmPropertyFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		

		leftListModel = new DefaultListModel<>();
		leftList = new JList<>(leftListModel);
		leftList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JLabel leftLabel = new JLabel("Names available");
		JScrollPane leftScrollPane = new JScrollPane(leftList);
		leftPanel.add(leftLabel);
		leftPanel.add(leftScrollPane);
		
		rightListModel = new DefaultListModel<>();
		rightList = new JList<>(rightListModel);
		rightList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JLabel rightLabel = new JLabel("Selected names");
		JScrollPane rightScrollPane = new JScrollPane(rightList);
		rightPanel.add(rightLabel);
		rightPanel.add(rightScrollPane);
		
		

		
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton starting = new JRadioButton("starting");
		JRadioButton containing = new JRadioButton("containing");
		JRadioButton similar = new JRadioButton("similar");
		starting.addActionListener(new ActionSelectionCriteriaString(this, (name,matchString) -> {
			return name.startsWith(matchString);
		}));
		containing.addActionListener(new ActionSelectionCriteriaString(this, (name,matchString) -> {
			return name.contains(matchString);
		}));
		similar.addActionListener(new ActionSelectionCriteriaString(this, (name,matchString) -> {
			Integer distance = LevenshteinDistance.computeLevenshteinDistance(matchString, name);
			Integer maxDistance = 3;
			return distance<=maxDistance?true:false;
		}));
		group.add(starting);
		group.add(containing);
		group.add(similar);
		JTextField field = new JTextField();
		field.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) { refresh(); }
			  public void removeUpdate(DocumentEvent e) { refresh(); }
			  public void insertUpdate(DocumentEvent e) { refresh(); }
			  public void refresh() {
			     matchStringTyped = field.getText();
			     if(getSelectedButton(group) == null) {
			    	 return;
			     }
			     getSelectedButton(group).doClick();
			  }
			});
		
		
		String[] options = { "All", "Male", "Female" };
		@SuppressWarnings("rawtypes")
		JComboBox genderCombo = new JComboBox<>(options);
		genderCombo.addActionListener(e -> {
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
	        String option = (String)cb.getSelectedItem();
	        group.clearSelection();
	        switch (option) {
			case "All":
				replaceWithNewList(PopularNames.getPopularNames());
				break;
			case "Male":
				replaceWithNewList(PopularNames.getPopularMaleNames());
				break;
			case "Female":
				replaceWithNewList(PopularNames.getPopularFemaleNames());
				break;
			default:
				break;
			}
		});
		
		
		JToolBar toolBar = new JToolBar("Tools");
		JButton buttonSelect = new JButton(new ActionSelect(this));
		buttonSelect.setText("Select");
		toolBar.add(genderCombo);
		toolBar.add(starting);
		toolBar.add(containing);
		toolBar.add(similar);
		toolBar.add(field);
		
		contentPane.add(rightPanel, BorderLayout.CENTER);
		contentPane.add(leftPanel, BorderLayout.WEST);
		contentPane.add(toolBar, BorderLayout.NORTH);
		

		JPanel statusBar = new JPanel();
		statusBar.setLayout(new GridBagLayout());
		nameOfSelectedFile = new JLabel(selectedFileText);
		numberOfNames = new JLabel(numberOfNamesText);
		numberOfSelectedNames = new JLabel(numberOfSelectedNamesText);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		statusBar.add(nameOfSelectedFile, c);
		statusBar.add(numberOfNames, c);
		statusBar.add(numberOfSelectedNames, c);
		getContentPane().add(statusBar,BorderLayout.SOUTH);
		
		
		
		try {
			loadpopularNameFile(defaultpopularNamesFile);
		} catch (IOException ignorable) { ignorable.printStackTrace(); }

		JOptionPane.showMessageDialog(this,
		    "Default text file will be loaded.",
		    "Information",
		    JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void stateChanged() {
		numberOfSelectedNames.setText(numberOfSelectedNamesText + rightListModel.size());
		numberOfNames.setText(numberOfNamesText + leftListModel.size());
		nameOfSelectedFile.setText(selectedFileText + selectedFileName);
		
	}
	

	private void loadpopularNameFile(String path) throws IOException {
		PopularNames.setPathToFile(path);
		leftListModel.removeAllElements();
		rightListModel.removeAllElements();
		for(String name : PopularNames.getPopularNames()) {
			leftListModel.addElement(name);
			
		}
		selectedFileName = path;
		stateChanged();
	}
	
	
	
	private void replaceWithNewList(List<String> list) {
		getLeftListModel().removeAllElements();
		getRightListModel().removeAllElements();
		for(String name : list) {
			getLeftListModel().addElement(name);
		}
	}

	public DefaultListModel<String> getRightListModel() {
		return rightListModel;
	}

	public void setRightListModel(DefaultListModel<String> rightListModel) {
		this.rightListModel = rightListModel;
	}

	public DefaultListModel<String> getLeftListModel() {
		return leftListModel;
	}

	public void setLeftListModel(DefaultListModel<String> leftListModel) {
		this.leftListModel = leftListModel;
	}

	public JList<String> getRightList() {
		return rightList;
	}

	public void setRightList(JList<String> rightList) {
		this.rightList = rightList;
	}

	public JList<String> getLeftList() {
		return leftList;
	}

	public void setLeftList(JList<String> leftList) {
		this.leftList = leftList;
	}

	public String getMatchStringTyped() {
		return matchStringTyped;
	}

	public void setMatchStringTyped(String matchStringTyped) {
		this.matchStringTyped = matchStringTyped;
	}
	
	
	public AbstractButton getSelectedButton(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button;
            }
        }

        return null;
    }
	
	
}
