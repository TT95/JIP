package conExp;

public class ThreadExample {
	private Thread producers[] = new Thread[2];
	private Thread consumers[] = new Thread[3];
	private Stock stock;
	public void setEnv() {
		stock= new Stock();
		for (int i=0; i<producers.length; i++) {
			Producer producer=new Producer(stock,"pr"+i);
			producers[i]= new Thread(producer);
			producers[i].start();
		}
		for (int i=0; i<consumers.length; i++) {
			Consumer consumer= new Consumer(stock, "con"+i);
			consumers[i]= new Thread (consumer);
			consumers[i].start();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadExample ext= new ThreadExample();
		ext.setEnv();
		while( true ) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.print("Break in thread exapmle");
			}
		}
	}
} 