package BEHAVIORAL;

import java.util.ArrayList;
import java.util.List;


public class Observer_Design_Pattern {
    public static void main(String[] args) {
     YouTubeSubscriber sheemab = new YouTubeSubscriber("Sheemab");
     YouTubeSubscriber azeez = new YouTubeSubscriber("azeez");

     YoutubeChannelImpl channel = new YoutubeChannelImpl();
     channel.addSubscriber(sheemab);
     channel.addSubscriber(azeez);

     channel.uploadNewVideo("Intro to Java");

    }
}


// Traditional Approach
class Subscriber {

    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void notifyUser(String video) {
        System.out.println(
                "Hi " + name + ", new video \"" + video +
                        "\" is uploaded. Please watch it. Thank you!"
        );
    }
}

class YouTubeChanel {

    private Subscriber s1;
    private Subscriber s2;

    public YouTubeChanel(Subscriber s1, Subscriber s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public void uploadVideo(String video) {

        System.out.println("Video uploaded: " + video);

        // Direct calls (tight coupling)
        s1.notifyUser(video);
        s2.notifyUser(video);
    }
}


// Observer Design Pattern

interface Observer{
    void update(String video);
} // Observer Interface


// Concrete Implementation of Observer
class YouTubeSubscriber implements Observer {
    private String name; // Name of the subscriber/Observer

    public YouTubeSubscriber(String name) {
        this.name = name; // Initialize the subscriber with their name
    }

    @Override
    public void update(String video) {
        String message = String.format(
                "Hi %s,\n" +
                        "A new video \"%s\" has just been uploaded.\n" +
                        "Please watch it when you have time.\n" +
                        "Thank you!",
                name, video
        );

        System.out.println(message);
    }
}

// Concrete Implementation of Observer
class EmailSubscriber implements Observer {
    private String email;
    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String video) {
        System.out.println(
                "Sending email to " + email + ": New video uploaded: " + video);
    }
}

// This is the subject
interface YouTubeChannel {
    void addSubscriber(Observer observer); // Method to add a new subscriber
    void removeSubscriber(Observer observer); // Method to remove a subscriber
    void notifySubscribers(); // Method to notify all subscribers
}

// Concrete Implementation of subject
class YoutubeChannelImpl implements YouTubeChannel{
    List<Observer> observers = new ArrayList<>(); // List of subscribers
    private String video; // The video that will be uploaded

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeSubscriber(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
     for(Observer o: observers){
         o.update(video);
     }
    }

    public void uploadNewVideo(String video) {
        this.video = video; // Set the video that is being uploaded
        notifySubscribers(); // Notify all subscribers about the new video
    }
}