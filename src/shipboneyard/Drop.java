package shipboneyard;


/**
 * Synchronized message drop
 *   borrowed from 
 *   "https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html"
 * 
 * take() - waits until message is available
 * put()  - sends message
 * @author leev
 */
public class Drop {
    // Message sent from producer to consumer.
    private String input;
    
    // True if consumer should wait for producer to send message,
    // false if producer should wait for consumer to retrieve message.
    private boolean empty = true;

    public synchronized String take() {
        // Wait until message is available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = true;
        // Notify producer that status has changed.
        notifyAll();
        return input;
    }

    public synchronized void put(String input) {
        // Wait until message has been retrieved.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.input = input;
        // Notify consumer that status has changed.
        notifyAll();
    }
}
