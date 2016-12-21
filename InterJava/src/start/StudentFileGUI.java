package start;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class StudentFileGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private StudentFile studentFile;
	private DefaultListModel<LineAnalyzed> listModel;
	private JList<LineAnalyzed> anayzedList;
	private JLabel linesCount;
	private static final String linesText = "Selected lines: ";
	
	private static final String defaultStudentFile = "res/Students.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFileGUI frame = new StudentFileGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public StudentFileGUI() {
		
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
						studentFile = new StudentFile(file.getPath().toString());
						loadStudentFile(studentFile);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Problem reading properties file!");
					}
				}
			}
		});
		
		mnLoad.add(mntmPropertyFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		listModel = new DefaultListModel<>();
		anayzedList = new JList<>(listModel);
		anayzedList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JScrollPane scrollPane = new JScrollPane(anayzedList);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton rdbtnProper = new JRadioButton("proper");
		JRadioButton rdbtnNotProper = new JRadioButton("not proper");
		JRadioButton rdbtnFirstNameError = new JRadioButton("first name error");
		JRadioButton rdbtnLastNameError = new JRadioButton("last name error");
		JRadioButton rdbtnGenderError = new JRadioButton("gender error");
		JRadioButton rdbtnDateError = new JRadioButton("date error");

		leftPanel.add(rdbtnProper);
		leftPanel.add(rdbtnNotProper);
		leftPanel.add(rdbtnFirstNameError);
		leftPanel.add(rdbtnLastNameError);
		leftPanel.add(rdbtnGenderError);
		leftPanel.add(rdbtnDateError);

		group.add(rdbtnProper);
		group.add(rdbtnNotProper);
		group.add(rdbtnFirstNameError);
		group.add(rdbtnLastNameError);
		group.add(rdbtnGenderError);
		group.add(rdbtnDateError);
		
		rdbtnProper.addActionListener(e ->  newFilter(studentFile.getProperLines()));
		rdbtnNotProper.addActionListener(e ->  newFilter(studentFile.getWrongLines()));
		rdbtnFirstNameError.addActionListener(e ->  newFilter(studentFile.getFirstNameError()));
		rdbtnLastNameError.addActionListener(e -> newFilter(studentFile.getLastNameError()));
		rdbtnGenderError.addActionListener(e ->  newFilter(studentFile.getGenderError()));
		rdbtnDateError.addActionListener(e ->  newFilter(studentFile.getDateError()));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		linesCount = new JLabel(linesText);
		bottomPanel.add(linesCount);
		getContentPane().add(bottomPanel,BorderLayout.SOUTH);
		
		
		
		try {
			studentFile = new StudentFile(defaultStudentFile);
			loadStudentFile(studentFile);
		} catch (IOException ignorable) { System.err.println("josok"); }

		JOptionPane.showMessageDialog(this,
		    "Default text file will be loaded.",
		    "Information",
		    JOptionPane.INFORMATION_MESSAGE);
		
		anayzedList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("unchecked")
				JList<LineAnalyzed> list = (JList<LineAnalyzed>)evt.getSource();
				if (evt.getClickCount() == 1) {
					group.clearSelection();
					showNumberOfSelectedLines();
				}
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					LineAnalyzed lineAnalyzed = listModel.getElementAt(index);
					String newInput = JOptionPane.showInputDialog("Edit line:",lineAnalyzed.getInput());
					if (newInput != null) {
						lineAnalyzed.setInput(newInput);
					}
				}
			}
		});
		
	}
	
	private void newFilter(List<LineAnalyzed> list) {
		anayzedList.clearSelection();
		List<Integer> indexesToSelect = new ArrayList<>();
		for(int index=0; index<listModel.getSize(); index++) {
			if(list.contains(listModel.getElementAt(index))) {
				indexesToSelect.add(index);
			}
		}
		anayzedList.setSelectedIndices(indexesToSelect.stream().mapToInt(i -> i).toArray());
		showNumberOfSelectedLines();
	}
	
	private void loadStudentFile(StudentFile studentFile) {
		listModel.removeAllElements();
		for(LineAnalyzed line : studentFile.getAnalyzedLines()) {
			listModel.addElement(line);
		}
	}
	
	private void showNumberOfSelectedLines() {
		linesCount.setText(linesText + anayzedList.getSelectedIndices().length);
	}
	
}
