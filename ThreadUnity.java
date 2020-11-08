/**
 * File: ThreadUnity.java 
 * @author Spencer Brown
 * Date: 6 November 2020
 * Purpose: To print the iteration of the threads loop
 */
public class ThreadUnity{
    /**
     * Variable Declaration
     */
    volatile int status = 1;

    /**
     * Default constructor for the thread unity class
     */
    public ThreadUnity(){}//end default constructor

    /**
     * Main method, creates the three threads and starts them
     * @param args
     */
    public static void main(String[] args){
        //Lock created to be shared amongst the threads
        ThreadUnity thread = new ThreadUnity();

        /*
         * Thread Declarations and Initializations
         */
        Thread1 thread1 = new Thread1(thread);
        Thread2 thread2 = new Thread2(thread);
        Thread3 thread3 = new Thread3(thread);

        /*
         * Thread Starts
         */
        thread1.start();
        thread2.start();
        thread3.start();
    }//end main method
}//end class ThreadUnity