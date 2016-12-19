package conExp;

public class Consumer implements Runnable {
	private Stock stock;
	private String ids;
	int number=0;
	public Consumer (Stock stock, String id) {
		this.stock= stock;
		ids=id;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int k=0;
		while (k<10) {
			String msg=stock.retrieveMessage();

			System.out.print("Retrieved:\t"+ids+"\t"+k+"\t"+msg+System.lineSeparator());
			k++;
		}
	}
} 