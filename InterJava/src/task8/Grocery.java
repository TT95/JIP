package task8;

import javax.swing.SwingUtilities;

import task8.workers.Bakery;

public class Grocery {
	
	private static final int MAX_BREAD_IN_STORE = 100;
	
	private int breadsInStore;
	private int breadsConsumed;
	private int breadsBought;
	private int breadsDelivered;
	
	private GroceryGUI frame;
	
	public Grocery(int breadsInStore, int breadsConsumed, int breadsBought, int breadsDelivered, GroceryGUI frame) {
		super();
		this.breadsInStore = breadsInStore;
		this.breadsConsumed = breadsConsumed;
		this.breadsBought = breadsBought;
		this.breadsDelivered = breadsDelivered;
		this.frame=frame;
	}
	
	public synchronized void doJob(GroceryWorker groceryWorker) {
		while(breadsInStore>=MAX_BREAD_IN_STORE ||
				(!(groceryWorker instanceof Bakery) && breadsInStore == 0)) {
			try {
				wait();
			} catch (InterruptedException ignorable) {}
		}
		
		groceryWorker.consume(this);
		refreshGUI();
		notify();
	}
	
	private void refreshGUI() {
		try { 
			SwingUtilities.invokeAndWait(new Thread( () -> {
				frame.refreshGUI();
			}));
			
		} catch (Exception e ) {
			
		}
//		SwingUtilities.invokeLater(new Thread( () -> {
//			frame.refreshGUI();
//		})); 
	}

	public int getBreadsInStore() {
		return breadsInStore;
	}

	public int getBreadsConsumed() {
		return breadsConsumed;
	}

	public int getBreadsBought() {
		return breadsBought;
	}

	public int getBreadsDelivered() {
		return breadsDelivered;
	}

	public void setBreadsInStore(int breadsInStore) {
		this.breadsInStore = breadsInStore;
	}

	public void setBreadsConsumed(int breadsConsumed) {
		this.breadsConsumed = breadsConsumed;
	}

	public void setBreadsBought(int breadsBought) {
		this.breadsBought = breadsBought;
	}

	public void setBreadsDelivered(int breadsDelivered) {
		this.breadsDelivered = breadsDelivered;
	}
	
	

}
