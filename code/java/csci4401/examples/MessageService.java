package csci4401.examples;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessageService extends Remote {
    public void postMessage( String sender, String message) throws RemoteException;
}
