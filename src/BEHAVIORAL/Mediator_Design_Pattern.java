package BEHAVIORAL;


import java.util.ArrayList;
import java.util.List;

public class Mediator_Design_Pattern {

    public static void main(String[] args) {

        // Concrete Mediator (central communication controller)
        ChatRoomMediator mediator = new ChatRoomMediator();

        // Concrete Colleagues
        // Each user only knows the mediator, not other users
        User u1 = new ChatUser(mediator, "Aman");
        User u2 = new ChatUser(mediator, "Ravi");
        User u3 = new ChatUser(mediator, "Neha");

        // Mediator maintains the list of all participants
        mediator.addUser(u1);
        mediator.addUser(u2);
        mediator.addUser(u3);

        // User sends message ONLY through mediator
        // User does not directly talk to other users
        u1.send("Hello everyone!");
    }
}


/*
 * Mediator interface
 * Defines how colleagues will communicate.
 * Colleagues depend only on this interface, not on concrete mediator.
 */
interface Mediator {
    void sendMessage(String msg, User user);
}


/*
 * Colleague (base class)
 * All users keep a reference of mediator
 * and use it for communication.
 */
abstract class User {

    // Reference of mediator (central coordinator)
    protected Mediator mediator;

    // Name of the user
    protected String name;

    // Every colleague must be connected to a mediator
    public User(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    // Send message via mediator (never directly to another user)
    public abstract void send(String msg);

    // Called by mediator when a message is delivered to this user
    public abstract void receive(String msg);
}


/*
 * Concrete Mediator
 * Contains the real communication logic.
 */
class ChatRoomMediator implements Mediator {

    // Mediator keeps track of all users
    private List<User> users = new ArrayList<>();

    // Register users in this chat room
    public void addUser(User user) {
        users.add(user);
    }

    /*
     * Central routing logic.
     * Mediator decides who should receive the message.
     */
    @Override
    public void sendMessage(String msg, User sender) {

        // Send message to everyone except the sender
        for (User u : users) {

            // Do not send back to the same user who sent the message
            if (u != sender) {
                u.receive(sender.name + ": " + msg);
            }
        }
    }
}


/*
 * Concrete Colleague
 * A real user of the system.
 */
class ChatUser extends User {

    // Pass mediator and name to the base class (User)
    public ChatUser(Mediator mediator, String name) {
        super(mediator, name);
    }

    /*
     * User does NOT know other users.
     * It only tells the mediator:
     * "I want to send this message and I am the sender."
     */
    @Override
    public void send(String msg) {

        // 'this' = current user (sender)
        // Mediator needs sender info to apply routing rules
        mediator.sendMessage(msg, this);
    }

    /*
     * Called by mediator when a message arrives for this user
     */
    @Override
    public void receive(String msg) {
        System.out.println(name + " received -> " + msg);
    }
}




