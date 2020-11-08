/**
 * File: Thread2.java
 * @author Spencer Brown
 * Date: 6 November 2020
 * Purpose: To ensure thread2 prints its output in the correct order
 *
 */

/**
 * Class to create Thread 2
 */
public class Thread2 extends Thread{
    /*
     * Variable Declaration used to point to the lock object
     */
    ThreadUnity thread;

    public Thread2(){}; //Default Constructor

    /**
     * Constructs a new Thread 2 with the lock to be shared with threads 1 and 3
     * @param thread
     */
    public Thread2(ThreadUnity thread) {
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
            synchronized (thread){//Synchronizes on the lock with threads 1 and 3
                for(int i = 1; i <= 5; i++) { //Loops through the threads iterations
                    while(thread.status != 2){ //While the other two threads have not looped through their iterations
                        thread.wait();
                    }//end while statement

                    //Prints the current thread's iteration
                    System.out.println("Thread " + threadId + " - iteration no. " + i);
                    //Sets the lock's status to 3
                    thread.status = 3;
                    //Notifies all waiting threads of the locks new status
                    thread.notifyAll();
                }//end for statement
            }//End synchronized block
        }catch(InterruptedException ie){
            System.out.println("Thread2 has been interrupted"); //Notifies the user the thread has been interrupted
        }//end try/catch block
    }//end run statement
}//end Thread2 class
