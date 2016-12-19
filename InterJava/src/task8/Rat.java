package task8;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import task8.components.Status;

public class Rat implements Runnable{

	Grocery grocery;
	
	boolean running = true;
	private Thread thisThread=null;
	
	private Status status;
	
	public void stopMe() {
		running = false;
	}
	
	public Rat(Grocery grocery, Status status) {
		super();
		this.grocery = grocery;
		this.status = status;
		Timer time = new Timer(); // Instantiate Timer Object
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				if(thisThread == null) {
					return;
				}
				SwingUtilities.invokeLater(new Thread( () -> {
					String text;
					switch (thisThread.getState()) {
					case TIMED_WAITING:
						text="active";
						break;
					case WAITING:
						text="starving";
						break;
					default:
						text="undefined";
						break;
					}
					status.setValue(text);
				})); 
			}
		}, 0, 50);
	}
	
	@Override
	public void run() {
		thisThread = Thread.currentThread();
		while(running) {
			grocery.doJob(GroceryWorker.RAT);
			try { Thread.sleep(1000); } catch (InterruptedException ignorable) {}
		}
	}

	public Status getStatus() {
		return status;
	}
	
	
}
