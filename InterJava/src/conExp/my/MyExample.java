package conExp.my;

public class MyExample {
	
	public static void main(String[] args) {
		
		Store store = new Store();
		Thread adder = new Thread(new CustomThread(store, true));
		Thread remover = new Thread(new CustomThread(store, false));
		
		remover.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adder.start();
		
	}
	


}
