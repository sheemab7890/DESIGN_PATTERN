package STRUCTURAL;

public class Proxy_Design_Pattern {
    public static void main(String[] args) {
        VideoService service =
                new VideoServiceProxy(true);

        service.play("Design Patterns Tutorial");
    }
}

/*
 * Common interface for both real object and proxy
 */
interface VideoService {

    void play(String videoName);
}

/*
 * The actual heavy object
 */
class RealVideoService implements VideoService {

    @Override
    public void play(String videoName) {
        System.out.println("Playing video: " + videoName);
    }
}

/*
 * Proxy controls access to RealVideoService
 */
class VideoServiceProxy implements VideoService {

    private RealVideoService realService;

    private boolean isPremiumUser;

    public VideoServiceProxy(boolean isPremiumUser) {
        this.isPremiumUser = isPremiumUser;
    }

    @Override
    public void play(String videoName) {

        // Access control logic
        if (!isPremiumUser) {
            System.out.println("Access denied. Premium required.");
            return;
        }

        // Lazy initialization (create real object only when needed)
        if (realService == null) {
            realService = new RealVideoService();
        }

        realService.play(videoName);
    }
}
