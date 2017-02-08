package csci4401.examples;

import java.util.Vector;

public class SyncCounter {
    private long counter = 0;

    // Add/remove 'synchronized' keyword to method signature
    public void increment( int inc) {
            counter += inc;
    }
    public String toString() {
        return "Counter: " + counter;
    }
      
    public static void main( String[] argv) throws Exception {
        testCounter(Long.parseLong(argv[0]));
    }
    
    static void testCounter(long iterations) throws Exception {
        SyncCounter cnt = new SyncCounter();
        Thread[] t = { new IncrementThread( cnt, 1, iterations), new IncrementThread( cnt, -1, iterations) };
        for( int i=0; i<t.length; i++)
            t[i].start();
        for( int i=0; i<t.length; i++)
            t[i].join();

        System.out.println( "Iterations: " + iterations + " " + cnt);
   } 
//    void testQ() {
//        SyncCounter c = new SyncCounter();
//        Vector[] q = { new Vector(), new Vector(), new Vector()};
//		Thread[] t = { new WorkerThread(0, q[0], 1000), new WorkerThread(1, q[1], 1000), new WorkerThread(2, q[2], 1000)};
//
//        for( int i=0; i<t.length; i++)
//            t[i].start();
//
//		for(int j=0; j<10; j++) {
//			for(int i=0; i<q.length; i++) {
//				synchronized(q[i]) {
//					q[i].add( "message" + j);
//				}
//			}
//			Thread.currentThread().sleep(500);
//		} 
//        for( int i=0; i<t.length; i++)
//            t[i].interrupt();
//}
}

class IncrementThread extends Thread {
    private SyncCounter counter;
    private int inc;
    private long iterations;
    
    public IncrementThread( SyncCounter c, int inc, long iterations) {
        this.counter = c;
        this.inc = inc;
        this.iterations = iterations;
    }
    public void run() {
        for( int i=0; i<iterations; i++)
            counter.increment( inc);
    }
}

/*
class WorkerThread extends Thread {
	private Vector msgQ;
	private int period, id;
	
	public WorkerThread(int id, Vector msgQ, int period) {
		this.msgQ = msgQ;
		this.period = period;
		this.id = id;
	}
	public void run() {
		try {
			while (true) {
				while(!msgQ.isEmpty()) {
					synchronized(msgQ) {
						System.out.println( id + ": " + msgQ.remove(0));
					}
				}
				this.sleep(period);
			}
		} catch (InterruptedException ex) {
			System.out.println(id + " exiting");
		}
	}
}
*/
