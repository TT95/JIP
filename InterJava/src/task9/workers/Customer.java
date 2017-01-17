package task9.workers;

import task9.Grocery;
import task9.GroceryWorker;

public class Customer implements Runnable, GroceryWorker{
	
	private static int frequency = 1000;
	private static final int consumingAmount = 1;


	Grocery grocery;
	
	boolean running = true;
	
	public void stopMe() {
		running = false;
	}
	
	public Customer(Grocery grocery) {
		super();
		this.grocery = grocery;
	}
	@Override
	public void run() {
		while(running) {
			grocery.doJob(this);
			try { Thread.sleep(frequency); } catch (InterruptedException ignorable) {}
		}
	}
	
	@Override
	public void consume(Grocery grocery) {
		grocery.setBreadsInStore(grocery.getBreadsInStore()-consumingAmount);
		grocery.setBreadsBought(grocery.getBreadsBought()+consumingAmount);
	}
	
	public static void setFrequency(int frequency) {
		Customer.frequency = frequency;
	}
}
