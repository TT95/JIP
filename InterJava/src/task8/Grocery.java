package task8;

import javax.swing.SwingUtilities;

public class Grocery {
	
	private static final int MAX_BREAD_IN_STORE = 100;
	private static final int CUSTOMER_MAX_BUY_AMOUNT = 3;
	private static final int RAT_EATING_FREQ_SECS = 10;
	private static final int RAT_STARVING_TIME_SECS = 120;
	
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
				(groceryWorker!= GroceryWorker.BAKERY && breadsInStore == 0)) {
			try {
				wait();
			} catch (InterruptedException ignorable) {}
		}
		switch (groceryWorker) {
		case RAT:
			breadsInStore--;
			breadsConsumed++;
			break;
		case CUSTOMER:
			breadsInStore--;
			breadsBought++;
			break;
		case BAKERY:
			breadsInStore+= 1;
			breadsDelivered++;
			break;
		}
		refreshGUI();
		notify();
	}
	
	private void refreshGUI() {
		SwingUtilities.invokeLater(new Thread( () -> {
			frame.refreshGUI();
		})); 
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
	

}
