package conExp.my;

public class Store {
	
	private int store = 0;
	
	public synchronized void store() {
		store++;
		notify();
	}
	
	public synchronized void remove() {
		while(store==0) {
			try {
				System.out.println("waiting!");
				wait();
			} catch (InterruptedException ignorable) {}
		}
		store--;
		notify();
		System.out.println("done!");
	}

}
