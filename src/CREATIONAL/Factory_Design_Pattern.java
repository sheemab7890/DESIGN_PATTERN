package CREATIONAL;

public class Factory_Design_Pattern {
    public static void main(String[] args) {
        Notification n =
                NotificationFactory.getNotification("sms");

        n.send("Hello");
    }
}


interface Notification {
    void send(String msg);
}

class EmailNotification implements Notification {
    public void send(String msg) {
        System.out.println("Email: " + msg);
    }
}

class SmsNotification implements Notification {
    public void send(String msg) {
        System.out.println("SMS: " + msg);
    }
}




