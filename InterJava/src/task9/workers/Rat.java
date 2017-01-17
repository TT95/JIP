package task9.workers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import task9.Grocery;
import task9.GroceryWorker;
import task9.IGrocery;
import task9.components.Status;

public class Rat implements Runnable, GroceryWorker{
	
	private static  int frequency = 1000;
	private static final int consumingAmount = 1;

	Grocery grocery;
	
	boolean running = true;
	private Thread thisThread=null;
	
	private Status status;
	
	public void stopMe() {
		running = false;
	}
	
	private static IGrocery look_up;

	public static void main(String[] args)
		throws MalformedURLException, RemoteException, NotBoundException {

		look_up = (IGrocery) Naming.lookup("//localhost/MyServer");
		
		while(true) {
			look_up.rat();
			System.out.println("eaten some bread!");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
		}, 0, 10);
	}
	
	@Override
	public void run() {
		thisThread = Thread.currentThread();
		while(running) {
			grocery.doJob(this);
			try { Thread.sleep(frequency); } catch (InterruptedException ignorable) {}
		}
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public void consume(Grocery grocery) {
		grocery.setBreadsInStore(grocery.getBreadsInStore()-consumingAmount);
		grocery.setBreadsConsumed(grocery.getBreadsConsumed()+consumingAmount);
	}
	
	public static void setFrequency(int frequency) {
		Rat.frequency = frequency;
	}
	
	
}
