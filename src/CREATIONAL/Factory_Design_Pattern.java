package CREATIONAL;

/*
 * Factory Design Pattern –
 *
 * Goal:
 * Create objects without exposing the concrete classes
 * to the client code.
 *
 * Client only talks to:
 *   -> Notification (interface)
 *   -> NotificationFactory
 *
 * It never uses:
 *   -> new EmailNotification()
 *   -> new SmsNotification()
 */

public class Factory_Design_Pattern {

    public static void main(String[] args) {

        /*
         * We only ask the factory for the object.
         * We do NOT create EmailNotification or SmsNotification directly.
         */
        Notification n =
                NotificationFactory.getNotification("sms");

        /*
         * We use the object only through the interface.
         */
        n.send("Hello");
    }

    /*
     * -------------------------------
     * Product interface
     * -------------------------------
     * All notification types must implement this.
     */
    interface Notification {
        void send(String msg);
    }

    /*
     * -------------------------------
     * Concrete product - Email
     * -------------------------------
     */
    static class EmailNotification implements Notification {

        @Override
        public void send(String msg) {
            System.out.println("Email: " + msg);
        }
    }

    /*
     * -------------------------------
     * Concrete product - SMS
     * -------------------------------
     */
    static class SmsNotification implements Notification {

        @Override
        public void send(String msg) {
            System.out.println("SMS: " + msg);
        }
    }

    /*
     * -------------------------------
     * Factory class
     * -------------------------------
     * This class decides which object to create.
     */
    class NotificationFactory {

        /*
         * This method hides the object creation logic.
         * The caller does not know which concrete class
         * is being created.
         */
        public static Notification getNotification(String type) {

            // Safety check to avoid NullPointerException
            if (type == null) {
                throw new IllegalArgumentException("Type is null");
            }

            // Make input case-insensitive
            switch (type.toUpperCase()) {

                // Create Email notification
                case "EMAIL":
                    return new EmailNotification();

                // Create SMS notification
                case "SMS":
                    return new SmsNotification();

                // Unknown type
                default:
                    throw new IllegalArgumentException("Invalid type");
            }
        }
    }
}

// Static nested class is used when the class is created from a static context
// and does not depend on the outer object.





