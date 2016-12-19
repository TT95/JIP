package task8;

import java.util.Timer;
import java.util.TimerTask;

public class Bakery implements Runnable {
	
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
			grocery.doJob(GroceryWorker.BAKERY);
			try { Thread.sleep(1000); } catch (InterruptedException ignorable) {}
		}
	}
	
}
