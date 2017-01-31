package task1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class Task1 extends JFrame {

	private static final long serialVersionUID = 1L;


	private DefaultListModel<String> listModel;
	private JList<String> list;
	private List<String> words = new ArrayList<>();
	private boolean ascending = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Task1 frame = new Task1();
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
	public Task1() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout());
		
		//menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnLoad = new JMenu("Load");
		menuBar.add(mnLoad);
		JMenuItem item = new JMenuItem("file");
		mnLoad.add(item);
		item.addActionListener(e -> {
			
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {	
					File file = fc.getSelectedFile();
					populateNames(file);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Problem reading properties file!");
				}
			}
			
		});
		
		//list
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);

		
		//filter
		JPanel filter = new JPanel(new GridBagLayout());
		JRadioButton ascending = new JRadioButton("ascending");
		JRadioButton descending = new JRadioButton("descending");
		ascending.doClick();
		ascending.addActionListener(e -> {
			reverse(true);
		});
		descending.addActionListener(e -> {
			reverse(false);
		});
		ButtonGroup group = new ButtonGroup();
	    group.add(ascending);
	    group.add(descending);
	    filter.add(ascending);
	    filter.add(descending);
	    SpinnerModel spinnerModel = new SpinnerNumberModel(3, 0, 20, 1);
	    JSpinner spinner = new JSpinner(spinnerModel);
	    spinner.addChangeListener(l -> {
	    	int value = (Integer) spinner.getValue();
	    	filerByNum(value);
	    });
	    filter.add(spinner);
	    getContentPane().add(filter, BorderLayout.NORTH);
	    
	    
//	    populateNames(new File("res/Nostromo.txt"));
		
	
	}
	
	private void populateNames(File file)  {
	        FileInputStream fis;
	        String str="";
			try {
				fis = new FileInputStream(file);
				 byte[] data = new byte[(int) file.length()];
			        fis.read(data);
			        fis.close();

			        str = new String(data, "UTF-8");
			        str = str.toLowerCase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Set<String> set = new LinkedHashSet<>();
			
			str = str.replaceAll("[!?,;:()*.\\\"]", "");
			str = str.replaceAll("-", " ");
			
			String[] list = str.split("\\s+");
			Arrays.sort(list, new comp()); 
			for(String word : list) {
				set.add(word);
			}
			
			words.addAll(set);
			
			for(String word : set) {
				listModel.addElement(word);
			}
	       
	}
	
	private void filerByNum(int num) {
		List<String> result = words.stream().filter(line -> line.length() == num).collect(Collectors.toList());
		listModel.removeAllElements();
		for(String word: result) {
			listModel.addElement(word);
		}
	}
	
	private void reverse(boolean be) {
		if(be == ascending) {
			return;
		}
		ascending = be;
		Collections.reverse(words);
		listModel.removeAllElements();
		for(String word : words) {
			listModel.addElement(word);
		}
	}
	
	class comp implements Comparator<String> {
		  public int compare(String o1, String o2) {
		    if(o1.length() < o2.length()) {
		    	return 1;
		    } else if(o1.length() > o2.length()) {
		    	return -1;
		    }
		    return 0;
		  }
		}
	
	
}
