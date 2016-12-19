package conExp;

import java.util.Vector;

public class Stock {
	private Vector<String> stock= new Vector<String>();
	private final int capacity=5;
	public synchronized void storeMessage(String msg) {
		while (stock.size()>=capacity) {
			try {
				wait();
			}
			catch (InterruptedException e) { }
		}
		msg="Recieved by STOCK\t"+msg;
		stock.add(msg);
		System.err.println(msg);
		notify(); }
	public synchronized String retrieveMessage() {
		while (stock.size()==0) { 
			System.out.println("$$$$$$$$$$$$$");
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		String result=stock.elementAt(0);
		result="sent by stock\t"+result;
		stock.removeElementAt(0);
		System.err.println(result);
		notify();
		return(result);
	}
} 