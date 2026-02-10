package BEHAVIORAL;

public class Command_Design_Pattern {

    public static void main(String[] args) {

        // Receiver (real object which performs the actual work)
        MusicPlayer music = new MusicPlayer();

        // Concrete Commands
        // Each command wraps a request as an object
        Command play = new PlayCommand(music);
        Command stop = new StopCommand(music);

        // Invoker (does not know how work is done)
        RemoteControl control = new RemoteControl();

        // To play music
        control.setCommand(play);
        control.pressButton();

        // To stop music
        control.setCommand(stop);
        control.pressButton();
    }
}


/*
 * Command interface
 * Represents a request as an object.
 * Invoker only knows this interface.
 */
interface Command {

    // Single entry point for executing a command
    void execute();
}


/*
 * Receiver
 * This class contains the real business logic.
 */
class MusicPlayer {

    public void play() {
        System.out.println("Music play!");
    }

    public void stop() {
        System.out.println("Music stop");
    }
}


/*
 * Concrete Command
 * Binds the receiver with the action (play).
 */
class PlayCommand implements Command {

    // Reference to the real worker (receiver)
    private final MusicPlayer musicPlayer;

    // Command is created with its receiver
    public PlayCommand(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    /*
     * execute() hides the real method call.
     * Invoker does not know about musicPlayer.play()
     */
    @Override
    public void execute() {
        musicPlayer.play();
    }
}


/*
 * Concrete Command
 * Binds the receiver with another action (stop).
 */
class StopCommand implements Command {

    // Reference to the real worker (receiver)
    private final MusicPlayer musicPlayer;

    // Command is created with its receiver
    public StopCommand(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    /*
     * execute() hides the real method call.
     * Invoker only triggers the command.
     */
    @Override
    public void execute() {
        musicPlayer.stop();
    }
}


/*
 * Invoker / Controller
 * This class triggers commands.
 * It does NOT know anything about MusicPlayer.
 */
class RemoteControl {

    // Current command assigned to the button
    private Command command;

    // Allows changing the command at runtime
    public void setCommand(Command command) {
        this.command = command;
    }

    /*
     * Invoker only calls execute().
     * It is completely decoupled from the receiver.
     */
    public void pressButton() {
        command.execute();
    }
}
