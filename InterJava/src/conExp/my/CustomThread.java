package conExp.my;

public class CustomThread implements Runnable {
	
	Store store;
	boolean adding;
	
	public CustomThread(Store store, boolean adding) {
		super();
		this.store = store;
		this.adding = adding;
	}

	@Override
	public void run() {
		if(adding) {
			store.store();
		} else {
			store.remove();
		}
		
	}
}