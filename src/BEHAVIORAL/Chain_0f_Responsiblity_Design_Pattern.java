package BEHAVIORAL;

import com.sun.net.httpserver.Request;

public class Chain_0f_Responsiblity_Design_Pattern {
    public static void main(String[] args) {
        Handler auth = new AuthenticationHandler();
        Handler validation = new ValidationHandler();
        Handler business = new BusinessHandler();

        // build chain
        auth.setNext(validation);
        validation.setNext(business);

        Requestt request =
                new Requestt("user-101", true, true);

        // start chain
        auth.handle(request);
    }
}

abstract class Handler {
    protected Handler next ;

    public void setNext(Handler next){
        this.next = next;
    }

    public abstract void handle(Requestt req);
}

class Requestt {
    String userId;
    boolean authenticated;
    boolean valid;

    public Requestt(String userId, boolean authenticated, boolean valid) {
        this.userId = userId;
        this.authenticated = authenticated;
        this.valid = valid;
    }
}

class AuthenticationHandler extends Handler {

    @Override
    public void handle(Requestt request) {

        if (!request.authenticated) {
            System.out.println("Authentication failed");
            return;   // chain stops here
        }

        System.out.println("Authentication passed");

        if (next != null)
            next.handle(request);
    }
}

class ValidationHandler extends Handler {

    @Override
    public void handle(Requestt request) {

        if (!request.valid) {
            System.out.println("Validation failed");
            return;   // chain stops here
        }

        System.out.println("Validation passed");

        if (next != null)
            next.handle(request);
    }
}

class BusinessHandler extends Handler {

    @Override
    public void handle(Requestt request) {

        System.out.println("Business logic executed for user: " + request.userId);

        if (next != null)
            next.handle(request);
    }
}


