package csci4401.examples;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriptionService extends Remote {
    public void subscribe( Subscriber subscriber, String name) throws RemoteException;
    public void sendAll( Subscriber sender, String message) throws RemoteException;  
}
