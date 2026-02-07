package CREATIONAL;

public class Singleton_Design_Pattern {
    public static void main(String[] args) {
        // 4. Fetch the single instance of the Logger
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

class Logger {
    // 1. Private static variable to hold the single instance
    private static Logger instance;

    // 2. Private constructor to prevent instantiation
    private Logger() {
        System.out.println("Loger constructor called by "
                + Thread.currentThread().getName());
    }

    // 3. Public method to provide access to the instance
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger(); // Create a new instance only if it doesn't exist
        }
        return instance; // Return the existing instance
    }
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}


// SINGLETON IN MULTITHREADED
class Loger {
    private static volatile Loger instance;
    // volatile ensures that changes made to a variable by one thread are immediately visible to other threads.

    // Private constructor to prevent instantiation
    private Loger() {
        System.out.println("Loger constructor called by "
                + Thread.currentThread().getName());
    }

    public static Loger getInstance() {
        if (instance == null) { // First check (no synchronization needed here)
            synchronized (  // A synchronized block allows only one thread at a time to execute a critical section of code
                    Loger.class) { // Synchronize only when creating the instance
                if (instance == null) { // Second check (inside synchronized block)
                    // why do we do second check because suppose Thread A enter in the critical section and make the instance and when Thread B(that are waiting for thread A to complete) enter in a critical section it see that instance is already created
                    instance = new Loger(); // Create the instance if it's still null
                }
            }
        }
        return instance; // Return the single instance
    }

    public void log(String message) {
        System.out.println(Thread.currentThread().getName()
                + " -> " + message);
    }
}
