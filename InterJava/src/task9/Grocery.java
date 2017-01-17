package task9;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import task9.workers.Bakery;

public class Grocery extends UnicastRemoteObject implements IGrocery {
	
	private static final long serialVersionUID = 1L;
	
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
			try {  wait(); } catch (InterruptedException ignorable) {}
		}
		breadsInStore--;
		breadsConsumed++;
		refreshGUI();
		System.out.println("Rat eats!!");
		notify();
	}
	
	public synchronized int customer(int amount) {
		while(breadsInStore==0) {
			try { wait(); } catch (InterruptedException ignorable) {}
		}
		int cannotBuyAmount = 0; //measures in minus
		if(breadsInStore - amount < 0) {
			cannotBuyAmount = breadsInStore - amount;
		}
		int toConsume = amount + cannotBuyAmount;
		breadsInStore -= toConsume;
		breadsBought += toConsume;
		refreshGUI();
		System.out.println("Customer buys " + amount + " breads!");
		notify();
		return toConsume;
	}
	
	public synchronized void bakery(int amount) {
		while(breadsInStore>=MAX_BREAD_IN_STORE) {
			try { wait(); } catch (InterruptedException ignorable) {}
		}
		breadsInStore+=amount;
		breadsDelivered+=amount;
		refreshGUI();
		System.out.println(amount + " bread added!");
		notify();
	}
	
	private void refreshGUI() {
		frame.setBreadInStoreNumber(breadsInStore);
		frame.setBreadConsumedNumber(breadsConsumed);
		frame.setBreadDeliveredNumber(breadsDelivered);
		frame.setBreadBoughtNumber(breadsBought);
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
