package STRUCTURAL;

public class Facade_Design_Pattern {
    public static void main(String[] args) {
       OrderFacade client = new OrderFacade();
       client.placeOrder("1","3",600);
    }
}

/*
 * Subsystem class - User related operations
 */
class UserService {

    public void checkUser(String userId) {
        System.out.println("Checking user: " + userId);
    }
}


/*
 * Subsystem class - Inventory related operations
 */
class InventoryService {

    public void reserveItem(String itemId) {
        System.out.println("Reserving item: " + itemId);
    }
}


/*
 * Subsystem class - Payment related operations
 */
class PaymentService {

    public void pay(double amount) {
        System.out.println("Payment done: " + amount);
    }
}


/*
 * Subsystem class - Notification related operations
 */
class NotificationService {

    public void sendConfirmation() {
        System.out.println("Order confirmation sent");
    }
}

/*
 * Facade
 * Provides a simple interface over multiple complex subsystems.
 */
class OrderFacade {

    private final UserService userService = new UserService();
    private final InventoryService inventoryService = new InventoryService();
    private final PaymentService paymentService = new PaymentService();
    private final NotificationService notificationService = new NotificationService();

    /*
     * Client will call only this method.
     * All internal coordination is hidden here.
     */
    public void placeOrder(String userId, String itemId, double amount) {

        userService.checkUser(userId);
        inventoryService.reserveItem(itemId);
        paymentService.pay(amount);
        notificationService.sendConfirmation();
    }
}

