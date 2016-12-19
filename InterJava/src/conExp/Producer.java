package conExp;

public class Producer implements Runnable {
	private Stock stock;
	private String ids;
	public Producer(Stock stock, String ids) {
		this.stock=stock;
		this.ids=ids;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int k=0;
		while (k<10) {
			String msg=ids+"\t"+k+"\t";
			System.out.println("Producing:\t"+msg);
			stock.storeMessage(msg);
			k++;
		}
	}
} 