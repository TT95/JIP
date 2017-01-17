package task9.workers;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import task9.Grocery;
import task9.GroceryWorker;
import task9.IGrocery;

public class Customer implements Runnable, GroceryWorker{
	
	private static int frequency = 1000;
	private static final int consumingAmount = 1;


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
			int amountBought = look_up.customer(amount);
			System.out.println("Bought " + amountBought + " breads!");
		}
		scanner.close();
	}
	
	
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
