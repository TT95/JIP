package task9;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import task9.components.AddBread;
import task9.components.Control;
import task9.components.RatsPanel;
import task9.components.Status;
import task9.workers.Bakery;
import task9.workers.Customer;
import task9.workers.Rat;

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
					frame.setSize(600, 600);
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
		ratControl = new Control( e -> {addRat();}, e -> {removeRat();},e -> {
			JSpinner s = (JSpinner)e.getSource();
			Rat.setFrequency((Integer)s.getValue());
		},2000, "Rats");
		customerControl = new Control(e -> {addCustomer();}, e -> {removeCustomer();},e -> {
			JSpinner s = (JSpinner)e.getSource();
			Customer.setFrequency((Integer)s.getValue());
		},2000, "Customer");
		bakeryControl = new Control(e -> {addBakery();}, e -> {removeBakery();},e -> {
			JSpinner s = (JSpinner)e.getSource();
			Bakery.setFrequency((Integer)s.getValue());
		},2000, "Bakeries");
		controls.add(customerControl,c);
		controls.add(bakeryControl,c);
		controls.add(ratControl,c);

		
		
		JPanel monitor = new JPanel(new BorderLayout());
		JPanel general = new JPanel(new GridBagLayout());
		
		//statuses
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
		
		//rats panel
		ratsPanel = new RatsPanel();
		monitor.add(general, BorderLayout.WEST);
		monitor.add(ratsPanel, BorderLayout.CENTER);
		
		
//		startRefreshing();
		//initialize threads
		try {
			grocery = new Grocery(0, 0, 0, 0, this);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

//		getContentPane().add(controls, BorderLayout.NORTH);
		getContentPane().add(monitor, BorderLayout.CENTER);
//		getContentPane().add(new AddBread(grocery, 5), BorderLayout.SOUTH);
		
		
		
		//i sockets
		
		try {

            Naming.rebind("//localhost/MyServer", grocery);
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }

	}

	private void addRat() {
		numberOfRats++;
		ratControl.setControlValue(numberOfRats);
		Rat rat = new Rat(grocery, ratsPanel.addRat("Rat "+ ratsPanel.getNumberOfRats()));
		new Thread(rat).start();
		rats.push(rat);
	}
	
	private void removeRat() {
		numberOfRats--;
		ratControl.setControlValue(numberOfRats);
		if(rats.isEmpty()) {
			return;
		}
		Rat rat = rats.pop();
		ratsPanel.removeRat(rat.getStatus());
		rat.stopMe();
	}
	
	private void addCustomer() {
		numberOfConsumers++;
		customerControl.setControlValue(numberOfConsumers);
		Customer customer = new Customer(grocery);
		new Thread(customer).start();
		customers.push(customer);
	}

	private void removeCustomer() {
		numberOfConsumers--;
		customerControl.setControlValue(numberOfConsumers);
		if(customers.isEmpty()) {
			return;
		}
		Customer customer = customers.pop();
		customer.stopMe();
	}
	
	private void addBakery() {
		numberOfBakeries++;
		bakeryControl.setControlValue(numberOfBakeries);
		Bakery bakery = new Bakery(grocery);
		new Thread(bakery).start();
		bakeries.push(bakery);
	}
	
	private void removeBakery() {
		numberOfBakeries--;
		bakeryControl.setControlValue(numberOfBakeries);
		if(bakeries.isEmpty()) {
			return;
		}
		Bakery bakery = bakeries.pop();
		bakery.stopMe();
	}
	
	public void setBreadInStoreNumber (Integer number) {
		breadsInStore.setValue(number+"");
	}

	public void setBreadDeliveredNumber (Integer number) {
		breadsDelivered.setValue(number+"");
	}
	
	public void setBreadBoughtNumber (Integer number) {
		breadsBought.setValue(number+"");
	}
	
	public void setBreadConsumedNumber (Integer number) {
		breadsConsumed.setValue(number+"");
	}
	
	
}
