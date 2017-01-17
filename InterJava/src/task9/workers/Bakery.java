package task9.workers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import task9.Grocery;
import task9.GroceryWorker;
import task9.IGrocery;


public class Bakery implements Runnable, GroceryWorker {
	
	private static int frequency = 1000;
	private static final int producingAmount = 1;

	
	Grocery grocery;
	
	
	private static IGrocery look_up;

	public static void main(String[] args)
		throws MalformedURLException, RemoteException, NotBoundException {

		look_up = (IGrocery) Naming.lookup("//localhost/MyServer");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String input = scanner.nextLine();
			if(input=="exit") {
				break;
			}
			Integer amount;
			amount = Integer.parseInt(input);
			look_up.bakery(amount);
			System.out.println("Added " + amount + " bread!");
		}
		scanner.close();
	}

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
