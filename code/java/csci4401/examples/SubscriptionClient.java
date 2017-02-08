package csci4401.examples;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class SubscriptionClient implements Subscriber {
    private String name;

    public SubscriptionClient( String name) {
        this.name = name;
    }
    public void deliver( String sender, String newMessage) throws RemoteException {
        System.out.print( "\r" + sender + ":  " + newMessage + "\n>>");
    }
    public String toString() {
        return name;
    }

	public static void main( String argv[]) throws Exception {
        if( argv.length != 3)
            throw new Exception( "\nUsage: java csci4401.examples.SubscriptionClinet <host> <port> <username>");
        Subscriber client = new SubscriptionClient( argv[2]);
        UnicastRemoteObject.exportObject( client);
        String line;
        Registry reg = LocateRegistry.getRegistry( argv[0], Integer.parseInt( argv[1]));
        SubscriptionService server = (SubscriptionService) reg.lookup( "SubServer");
        server.subscribe( client, argv[2]);
        BufferedReader stdin = new BufferedReader( new InputStreamReader( System.in));

        System.out.print( ">> ");
    	while(( line = stdin.readLine()) != null) {
            server.sendAll( client, line);
            System.out.print( ">> ");
		} 
	} 
}
 
