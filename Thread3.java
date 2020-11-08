/**
 * File: Thread3.java
 * @author Spencer Brown
 * Date: 6 November 2020
 * Purpose: To ensure thread3 prints its output in the correct order
 *
 */

/**
 * Class to create Thread 3
 */
public class Thread3 extends Thread{
    /*
     * Variable Declaration used to point to the lock object
     */
    ThreadUnity thread;

    public Thread3(){}; //Default Constructor

    /**
     * Constructs a new Thread 3 with the lock to be shared with threads 1 and 2
     * @param thread
     */
    public Thread3(ThreadUnity thread) {
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
            synchronized (thread){//Synchronizes on the lock with threads 1 and 2
                for(int i = 1; i <= 5; i++) { //Loops through the threads iterations
                    while(thread.status != 3){ //While the other two threads have not looped through their iterations
                        thread.wait();
                    }//end while statement

                    //Prints the current thread's iteration
                    System.out.println("Thread " + threadId + " - iteration no. " + i);
                    //Sets the lock's status to 1
                    thread.status = 1;
                    //Notifies all waiting threads of the locks new status
                    thread.notifyAll();
                }//end for statement
            }//End synchronized block
        }catch(InterruptedException ie){
            System.out.println("Thread3 has been interrupted"); //Notifies the user the thread has been interrupted
        }//end try/catch block
    }//end run statement
}//end Thread3 class
