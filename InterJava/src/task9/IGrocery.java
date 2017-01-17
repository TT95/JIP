package task9;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGrocery extends Remote{
	
	public void rat() throws RemoteException;
	public int customer(int amount) throws RemoteException;
	public void bakery(int amount) throws RemoteException;
}
