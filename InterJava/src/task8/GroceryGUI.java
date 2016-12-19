package task8;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import task8.components.Control;
import task8.components.Status;

public class GroceryGUI extends JFrame {
	
	private Grocery grocery;
	
	private Status breadsInStore;
	private Status breadsDelivered;
	private Status breadsBought;
	private Status breadsConsumed;
	
	private Control ratControl;
	private Control customerControl;
	private Control bakeryControl;
	
	private JPanel ratsPanel;
	
	private Stack<Rat> rats = new Stack<>();
	private Stack<Customer> customers = new Stack<>();
	private Stack<Bakery> bakeries = new Stack<>();

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroceryGUI frame = new GroceryGUI();
					frame.pack();
					frame.setVisible(true);
					frame.setSize(800, 500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GroceryGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		
		getContentPane().setLayout(new BorderLayout());
		
		JPanel controls = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		ratControl = new Control( e -> {addRat();}, e -> {removeRat();}, "Rats", 3);
		customerControl = new Control(e -> {addCustomer();}, e -> {removeCustomer();}, "Customer", 2);
		bakeryControl = new Control(e -> {addBakery();}, e -> {removeBakery();}, "Bakeries", 1);
		controls.add(customerControl,c);
		controls.add(bakeryControl,c);
		controls.add(ratControl,c);
		
		JPanel monitor = new JPanel(new BorderLayout());
		JPanel general = new JPanel(new GridBagLayout());
		ratsPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints generalConstraint = new GridBagConstraints();
		generalConstraint.insets = new Insets(5, 5, 5, 5);
		generalConstraint.weightx = 1.0;
		generalConstraint.weighty = 1.0;
		generalConstraint.anchor = GridBagConstraints.LINE_START;
		breadsInStore = new Status("Bread in store", 0+"");
		breadsDelivered = new Status("Breads delivered", 0+"");
		breadsBought = new Status("Breads bought", 0+"");
		breadsConsumed = new Status("Breads consumed", 0+"");
		general.add(breadsInStore,generalConstraint);
		generalConstraint.gridy = 1;
		generalConstraint.gridx = 0;
		general.add(breadsDelivered,generalConstraint);
		generalConstraint.gridy = 2;
		generalConstraint.gridx = 0;
		general.add(breadsBought,generalConstraint);
		generalConstraint.gridy = 3;
		generalConstraint.gridx = 0;
		general.add(breadsConsumed,generalConstraint);
		
		GridBagConstraints ratConstraint = new GridBagConstraints();
		ratConstraint.weightx = 1.0;
		ratConstraint.weightx = 1.0;
		ratsPanel.add(new Status("Rat 1", "Alive"),ratConstraint);
		monitor.add(general, BorderLayout.WEST);
		monitor.add(ratsPanel, BorderLayout.CENTER);
		
		getContentPane().add(controls, BorderLayout.NORTH);
		getContentPane().add(monitor, BorderLayout.CENTER);
		
		//initialize threads
		grocery = new Grocery(5, 0, 0, 0, this);
		addRat();
		addCustomer();
		addBakery();
		
	}
	
	private void addRatOnGUI() {
		
	}
	
	private void addRat() {
		Rat rat = new Rat(grocery);
		new Thread(rat).start();
		rats.push(rat);
	}
	
	private void removeRat() {
		if(rats.isEmpty()) {
			return;
		}
		Rat rat = rats.pop();
		rat.stopMe();
	}
	
	private void addCustomer() {
		Customer customer = new Customer(grocery);
		new Thread(customer).start();
		customers.push(customer);
	}

	private void removeCustomer() {
		if(customers.isEmpty()) {
			return;
		}
		Customer customer = customers.pop();
		customer.stopMe();
	}
	
	private void addBakery() {
		Bakery bakery = new Bakery(grocery);
		new Thread(bakery).start();
		bakeries.push(bakery);
	}
	
	private void removeBakery() {
		if(bakeries.isEmpty()) {
			return;
		}
		Bakery bakery = bakeries.pop();
		bakery.stopMe();
	}
	
	public void refreshGUI() {
		breadsInStore.setValue(grocery.getBreadsInStore()+"");
		breadsDelivered.setValue(grocery.getBreadsDelivered()+"");
		breadsBought.setValue(grocery.getBreadsBought()+"");
		breadsConsumed.setValue(grocery.getBreadsConsumed()+"");
	}

	
}
