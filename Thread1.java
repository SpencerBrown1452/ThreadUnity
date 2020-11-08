/**
 * File: Thread1.java
 * @author Spencer Brown
 * Date: 6 November 2020
 * Purpose: To ensure thread1 prints its output in the correct order
 *
 */

/**
 * Class to create Thread 1
 */
public class Thread1 extends Thread{
    /*
     * Variable Declaration used to point to the lock object
     */
    ThreadUnity thread;

    public Thread1(){}; //Default Constructor

    /**
     * Constructs a new Thread 1 with the lock to be shared with threads 2 and 3
     * @param thread
     */
    public Thread1(ThreadUnity thread) {
        this.thread = thread;
    }//end specialized constructor

    /**
     * Runs the thread
     */
    @Override
    public void run(){
        //Saves the ID of the thread to a local variable
        long threadId = Thread.currentThread().getId();
        try{
            synchronized (thread){//Synchronizes on the lock with threads 2 and 3
                for(int i = 1; i <= 5; i++) { //Loops through the threads iterations
                    while(thread.status != 1){ //While the other two threads have not looped through their iterations
                        thread.wait();
                    }//end while statement

                    //Prints the current thread's iteration
                    System.out.println("Thread " + threadId + " - iteration no. " + i);
                    //Sets the lock's status to 2
                    thread.status = 2;
                    //Notifys all waiting threads of the locks new status
                    thread.notifyAll();
                }//end for statement
            }//End synchronized block
            }catch(InterruptedException ie){
                System.out.println("Thread1 has been interrupted"); //Notifies the user the thread has been interrupted
            }//end try/catch block
    }//end run statement
}//end Thread1 class
