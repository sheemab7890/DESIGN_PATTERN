package CREATIONAL;

/*
 * Factory class.
 * Its only job is to decide which object to create and return it to the caller.
 */
class NotificationFactory {

    /*
     * This method hides the object creation logic.
     * The caller does not know (and does not care) which concrete class is created.
     */
    public static Notification getNotification(String type) {

        // Safety check to avoid NullPointerException
        if (type == null) {
            throw new IllegalArgumentException("Type is null");
        }

        // Convert input to upper case so that
        // "sms", "Sms", "SMS" all work the same
        switch (type.toUpperCase()) {

            // If type is EMAIL, create EmailNotification object
            case "EMAIL":
                return new EmailNotification();

            // If type is SMS, create SmsNotification object
            case "SMS":
                return new SmsNotification();

            // If type does not match any known type
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }
}

