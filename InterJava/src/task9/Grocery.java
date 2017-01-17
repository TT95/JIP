package task9;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import task9.workers.Bakery;

public class Grocery extends UnicastRemoteObject implements IGrocery {
	
	private static final long serialVersionUID = 1L;
	
	private static final int CONSUMER_BUY_AMOUNT = 3;
	private static final int MAX_BREAD_IN_STORE = 100;
	
	private static final int EXECUTING_DELAY = 0;
	
	private int breadsInStore;
	private int breadsConsumed;
	private int breadsBought;
	private int breadsDelivered;
	
	private GroceryGUI frame;
	
	public Grocery(int breadsInStore, int breadsConsumed, int breadsBought, int breadsDelivered, GroceryGUI frame) throws RemoteException {
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
			try { wait(); } catch (InterruptedException ignorable) {}
		}
		try { Thread.sleep(EXECUTING_DELAY); } catch (InterruptedException ignorable) { }
		groceryWorker.consume(this);
		notify();
	}
	
	public synchronized void rat() {
		while(breadsInStore==0) {
			try { wait(); } catch (InterruptedException ignorable) {}
		}
		breadsInStore--;
		breadsConsumed--;
		refreshGUI();
		System.out.println("eaten!!");
	}
	
	public synchronized void customer() {
		while(breadsInStore==0) {
			try { wait(); } catch (InterruptedException ignorable) {}
		}
		int cannotBuyAmount = 0; //measures in minus
		if(breadsInStore - CONSUMER_BUY_AMOUNT < 0) {
			cannotBuyAmount = breadsInStore - CONSUMER_BUY_AMOUNT;
		}
		int toConsume = CONSUMER_BUY_AMOUNT + cannotBuyAmount;
		breadsInStore -= toConsume;
		breadsBought += toConsume;
		refreshGUI();
	}
	
	public synchronized void bakery() {
		while(breadsInStore>=MAX_BREAD_IN_STORE) {
			try { wait(); } catch (InterruptedException ignorable) {}
		}
		//blabla
	}
	
	private void refreshGUI() {
		frame.setBreadInStoreNumber(breadsInStore);
		frame.setBreadConsumedNumber(breadsConsumed);
		frame.setBreadDeliveredNumber(breadsDelivered);
		frame.setBreadInStoreNumber(breadsInStore);
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
