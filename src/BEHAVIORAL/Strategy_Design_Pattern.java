package BEHAVIORAL;

public class Strategy_Design_Pattern {
    public static void main(String[] args) {
       PaymentProcessor processor = new PaymentProcessor(new CardPaymentStrategy());
       processor.makePayment(100);

       PaymentProcessorr processorr = new PaymentProcessorr();
       processorr.processPayment("Card", 100);
    }
}


/*
 * ----------------------------------------
 * Strategy interface
 * ----------------------------------------
 * This represents the behaviour that can change.
 */
interface PaymentStrategy {

    void pay(double amount);
}

/*
 * ----------------------------------------
 * Concrete strategy – UPI payment
 * ----------------------------------------
 */
class UpiPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid via UPI: " + amount);
    }
}

/*
 * ----------------------------------------
 * Concrete strategy – Card payment
 * ----------------------------------------
 */
class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid via Card: " + amount);
    }
}

/*
 * ----------------------------------------
 * Traditional approach (WITHOUT Strategy)
 * ----------------------------------------
 *
 * Problem:
 * Every time a new payment method is added,
 * this class must be modified.
 */
class PaymentProcessorr {

    public void processPayment(String paymentMethod, double amount) {

        if (paymentMethod.equals("Card")) {

            CardPaymentStrategy card = new CardPaymentStrategy();
            card.pay(amount);

        } else if (paymentMethod.equals("UPI")) {

            UpiPaymentStrategy upi = new UpiPaymentStrategy();
            upi.pay(amount);
        }
    }
}

/*
 * ----------------------------------------
 * Context class (uses Strategy pattern)
 * ----------------------------------------
 *
 * This class does not know which payment type is used.
 * It only knows that it has a PaymentStrategy.
 */
class PaymentProcessor {

    private final PaymentStrategy strategy;

    /*
     * The strategy (behaviour) is injected from outside.
     */
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /*
     * Delegates the payment work to the selected strategy.
     */
    public void makePayment(double amount) {
        strategy.pay(amount);
    }
}