package CREATIONAL;

public class Factory_Design_Pattern {
    public static void main(String[] args) {
        // We only ask the factory for a Notification object.
        // We do NOT create EmailNotification or SmsNotification directly here.
        Notification n =
                NotificationFactory.getNotification("sms");

        n.send("Hello");
    }
}


/*
 * Common interface.
 * All notification types must follow this contract.
 */
interface Notification {
    void send(String msg);
}

/*
 * One concrete implementation of Notification.
 */
class EmailNotification implements Notification {

    @Override
    public void send(String msg) {
        System.out.println("Email: " + msg);
    }
}

/*
 * Another concrete implementation of Notification.
 */
class SmsNotification implements Notification {

    @Override
    public void send(String msg) {
        System.out.println("SMS: " + msg);
    }
}




