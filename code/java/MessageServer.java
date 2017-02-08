package csci4401.examples;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class MessageServer implements MessageService {

    public void postMessage( String sender, String message) throws RemoteException {
        System.out.println( sender + ">> " + message);
    }

    public static void main(String[] args) throws Exception {    
        MessageServer server = new MessageServer();
        MessageService stub = (MessageService) UnicastRemoteObject.exportObject( server);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind( "MessageServer", stub);
        System.out.println( "Message server registered successfully!");
    }
}
