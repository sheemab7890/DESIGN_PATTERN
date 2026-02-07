package CREATIONAL;

public class NotificationFactory {
    public static Notification getNotification(String type) {

        if (type == null) {
            throw new IllegalArgumentException("Type is null");
        }

        switch (type.toUpperCase()) {

            case "EMAIL":
                return new EmailNotification();

            case "SMS":
                return new SmsNotification();

            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }

}
