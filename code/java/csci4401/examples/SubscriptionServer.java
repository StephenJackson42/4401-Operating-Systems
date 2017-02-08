package csci4401.examples;

import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class SubscriptionServer implements SubscriptionService {  
    private Vector subs = new Vector();
    private Vector names = new Vector();  

    public void subscribe( Subscriber subscriber, String name) throws RemoteException {
        subs.add( subscriber);
        names.add( name);
    }
    public void sendAll( Subscriber sender, String message) throws RemoteException {
        for( int i=0; i<subs.size(); i++) {
            Subscriber sub = (Subscriber)subs.get(i); 
//            if( !sub.equals( sender))
                sub.deliver( (String)names.get(i), message);
        }
        System.out.println( "Delivered: '"  + message + "'.");
    }

    public static void main(String[] args) throws Exception {    
        SubscriptionServer server = new SubscriptionServer();
        SubscriptionService stub = (SubscriptionService) UnicastRemoteObject.exportObject( server);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind( "SubServer", stub);
        System.out.println( "Subscription server registered successfully!");
    }
}
