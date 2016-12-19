package task8;

public class Rat implements Runnable{

	Grocery grocery;
	
	boolean running = true;
	
	public void stopMe() {
		running = false;
	}
	
	public Rat(Grocery grocery) {
		super();
		this.grocery = grocery;
	}
	
	@Override
	public void run() {
		while(running) {
			grocery.doJob(GroceryWorker.RAT);
			try { Thread.sleep(1000); } catch (InterruptedException ignorable) {}
		}
	}
}
