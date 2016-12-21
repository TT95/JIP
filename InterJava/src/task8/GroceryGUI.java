package task8;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import task8.components.Control;
import task8.components.RatsPanel;
import task8.components.Status;
import task8.workers.Bakery;
import task8.workers.Customer;
import task8.workers.Rat;

public class GroceryGUI extends JFrame {
	
	private Grocery grocery;
	
	private Status breadsInStore;
	private Status breadsDelivered;
	private Status breadsBought;
	private Status breadsConsumed;
	
	private Control ratControl;
	private Control customerControl;
	private Control bakeryControl;
	
	private RatsPanel ratsPanel;
	
	private Stack<Rat> rats = new Stack<>();
	private Stack<Customer> customers = new Stack<>();
	private Stack<Bakery> bakeries = new Stack<>();
	
	private int numberOfRats = 0;
	private int numberOfConsumers = 0;
	private int numberOfBakeries = 0;
	
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
		ratControl = new Control( e -> {addRat();}, e -> {removeRat();}, "Rats");
		customerControl = new Control(e -> {addCustomer();}, e -> {removeCustomer();}, "Customer");
		bakeryControl = new Control(e -> {addBakery();}, e -> {removeBakery();}, "Bakeries");
		controls.add(customerControl,c);
		controls.add(bakeryControl,c);
		controls.add(ratControl,c);
		
		JPanel monitor = new JPanel(new BorderLayout());
		JPanel general = new JPanel(new GridBagLayout());
		
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
		
		ratsPanel = new RatsPanel();
		
		monitor.add(general, BorderLayout.WEST);
		monitor.add(ratsPanel, BorderLayout.CENTER);
		
		getContentPane().add(controls, BorderLayout.NORTH);
		getContentPane().add(monitor, BorderLayout.CENTER);
		
//		startRefreshing();
		//initialize threads
		grocery = new Grocery(5, 0, 0, 0, this);

		addRat();
		addCustomer();
		addBakery();
		
	}
	
	
//	private void startRefreshing() {
//		Timer time = new Timer(); // Instantiate Timer Object
//		time.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				SwingUtilities.invokeLater(new Thread( () -> {
//					refreshGUI();
//				})); 
//			}
//		}, 0, 50);
//		
//	}

	private void addRat() {
		numberOfRats++;
		Rat rat = new Rat(grocery, ratsPanel.addRat("Rat "+ ratsPanel.getNumberOfRats()));
		new Thread(rat).start();
		rats.push(rat);
	}
	
	private void removeRat() {
		numberOfRats--;
		if(rats.isEmpty()) {
			return;
		}
		Rat rat = rats.pop();
		ratsPanel.removeRat(rat.getStatus());
		rat.stopMe();
	}
	
	private void addCustomer() {
		numberOfConsumers++;
		Customer customer = new Customer(grocery);
		new Thread(customer).start();
		customers.push(customer);
	}

	private void removeCustomer() {
		numberOfConsumers--;
		if(customers.isEmpty()) {
			return;
		}
		Customer customer = customers.pop();
		customer.stopMe();
	}
	
	private void addBakery() {
		numberOfBakeries++;
		Bakery bakery = new Bakery(grocery);
		new Thread(bakery).start();
		bakeries.push(bakery);
	}
	
	private void removeBakery() {
		numberOfBakeries--;
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
		ratControl.setControlValue(numberOfRats);
		bakeryControl.setControlValue(numberOfBakeries);
		customerControl.setControlValue(numberOfConsumers);
	}

	
}
