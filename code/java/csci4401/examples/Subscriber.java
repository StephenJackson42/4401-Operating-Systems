package csci4401.examples;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subscriber extends Remote {
   public void deliver( String sender, String newMessage) throws RemoteException;
}
