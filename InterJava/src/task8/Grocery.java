package task8;


import task8.workers.Bakery;

public class Grocery {
	
	private static final int MAX_BREAD_IN_STORE = 100;
	private static final int EXECUTING_DELAY = 0;
	
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
		try { Thread.sleep(EXECUTING_DELAY); } catch (InterruptedException ignorable) { }
		groceryWorker.consume(this);
		notify();
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
		frame.setBreadInStoreNumber(breadsInStore);
	}

	public void setBreadsConsumed(int breadsConsumed) {
		this.breadsConsumed = breadsConsumed;
		frame.setBreadConsumedNumber(breadsConsumed);
	}

	public void setBreadsBought(int breadsBought) {
		this.breadsBought = breadsBought;
		frame.setBreadBoughtNumber(breadsBought);
	}

	public void setBreadsDelivered(int breadsDelivered) {
		this.breadsDelivered = breadsDelivered;
		frame.setBreadDeliveredNumber(breadsDelivered);
	}
	
	public synchronized void addBreadManually(int breadAmount) {
		this.breadsInStore = breadAmount;
		this.breadsDelivered += breadAmount;
		notify();
		frame.setBreadInStoreNumber(breadsInStore);
		frame.setBreadDeliveredNumber(breadsDelivered);
		
	}

}
