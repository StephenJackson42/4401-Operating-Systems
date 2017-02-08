package csci4401.examples;

import java.io.*;
import java.rmi.registry.*;

public class MessageClient {

	public static void main( String argv[]) throws Exception {
        String line;
        Registry reg = LocateRegistry.getRegistry( argv[0], Integer.parseInt( argv[1]));
        MessageService server = (MessageService) reg.lookup( "MessageServer");
        BufferedReader stdin = new BufferedReader( new InputStreamReader( System.in));

        System.out.print( ">> ");
    	while(( line = stdin.readLine()) != null) {
            server.postMessage( argv[2], line);
            System.out.print( ">> ");
		} 
	} 
}
 
