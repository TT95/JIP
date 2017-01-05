package task8.workers;

import task8.Grocery;
import task8.GroceryWorker;


public class Bakery implements Runnable, GroceryWorker {
	
	private static int frequency = 1000;
	private static final int producingAmount = 1;

	
	Grocery grocery;
	
	boolean running = true;
	
	public void stopMe() {
		running = false;
	}
	
	public Bakery(Grocery grocery) {
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
		grocery.setBreadsInStore(grocery.getBreadsInStore()+producingAmount);
		grocery.setBreadsDelivered(grocery.getBreadsDelivered()+producingAmount);
	}

	public static void setFrequency(int frequency) {
		Bakery.frequency = frequency;
	}
	
	
	
	
}
