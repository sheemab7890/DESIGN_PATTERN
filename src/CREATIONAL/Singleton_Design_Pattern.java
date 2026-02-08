package CREATIONAL;

/*
 * Singleton Design Pattern demonstration
 *
 * This file shows two versions of Singleton:
 *
 * 1) Logger   -> NOT thread-safe (for learning purpose)
 * 2) Loger    -> Thread-safe Singleton using
 *                volatile + double-checked locking
 *
 * Main idea:
 * A Singleton class allows only ONE object to be created
 * in the whole application.
 */

public class Singleton_Design_Pattern {

    public static void main(String[] args) {

        /*
         * -------------------------------
         * 1. Normal Singleton (NOT thread-safe)
         * -------------------------------
         * Multiple threads try to get the same instance.
         * This may create more than one object.
         */

        Runnable task = () -> {
            Logger logger = Logger.getInstance();
            logger.log("Hyy");
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        Thread t3 = new Thread(task, "T3");

        t1.start();
        t2.start();
        t3.start();

        /*
         * -------------------------------
         * 2. Thread-safe Singleton
         * -------------------------------
         * Uses volatile + synchronized block
         * to make sure only ONE object is created.
         */

        Runnable multithreadingTask = () -> {
            Loger logger = Loger.getInstance();
            logger.log("Hyy");
        };

        Thread t4 = new Thread(multithreadingTask, "T4");
        Thread t5 = new Thread(multithreadingTask, "T5");
        Thread t6 = new Thread(multithreadingTask, "T6");

        t4.start();
        t5.start();
        t6.start();
    }
}

/*
 * ---------------------------------------
 * Normal Singleton (NOT thread-safe)
 * ---------------------------------------
 *
 * Only good for single-threaded programs.
 */
class Logger {

    // Holds the single instance
    private static Logger instance;

    // Private constructor so no one can create object using new
    private Logger() {
        System.out.println("Logger constructor called by "
                + Thread.currentThread().getName());
    }

    /*
     * Returns the single instance.
     * In multi-threaded environment this is NOT safe.
     */
    public static Logger getInstance() {

        // If two threads enter here at the same time,
        // both can create a new object.
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void log(String message) {
        System.out.println(Thread.currentThread().getName()
                + " -> " + message);
    }
}

/*
 * ---------------------------------------
 * Thread-safe Singleton
 * ---------------------------------------
 *
 * Uses:
 *  - volatile  -> visibility guarantee
 *  - synchronized block -> mutual exclusion
 *
 * This implementation is called:
 * "Double-checked locking Singleton"
 */
class Loger {

    /*
     * volatile ensures that when one thread creates the instance,
     * other threads see the fully created object immediately.
     */
    private static volatile Loger instance;

    // Private constructor to stop direct object creation
    private Loger() {
        System.out.println("Loger constructor called by "
                + Thread.currentThread().getName());
    }

    /*
     * Returns the single instance in a thread-safe way.
     */
    public static Loger getInstance() {

        /*
         * First check (without locking).
         * This improves performance after the instance is created.
         */
        if (instance == null) {

            /*
             * Only one thread is allowed inside this block
             * at a time.
             */
            synchronized (Loger.class) {

                /*
                 * Second check is required because
                 * another thread may have already created
                 * the instance while this thread was waiting.
                 */
                if (instance == null) {

                    // Create the single instance
                    instance = new Loger();
                }
            }
        }

        return instance;
    }

    public void log(String message) {
        System.out.println(Thread.currentThread().getName()
                + " -> " + message);
    }
}

