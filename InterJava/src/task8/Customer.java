package task8;

public class Customer implements Runnable{

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
			grocery.doJob(GroceryWorker.CUSTOMER);
			try { Thread.sleep(1000); } catch (InterruptedException ignorable) {}
		}
	}
}
