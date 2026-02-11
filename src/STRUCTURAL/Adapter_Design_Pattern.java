package STRUCTURAL;

public class Adapter_Design_Pattern {
    public static void main(String[] args) {
        RazorpayClient client = new RazorpayClient();

        Payment payment = new RazorPay(client);
        payment.pay(100);
    }
}

// Client want this
interface Payment{
    void pay(double amount);
}

// Adaptee(3rd party)
class RazorpayClient {

    public void makePayment(double amount) {
        System.out.println("Paid using Razorpay: " + amount);
    }
}

// Translate Payment to RazorpayClient
class RazorPay implements Payment{

    private final RazorpayClient razorpayClient;

    RazorPay(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public void pay(double amount) {
       razorpayClient.makePayment(amount);
    }
}
