package BEHAVIORAL;

public class Command_Design_Pattern {
    public static void main(String[] args) {
      MusicPlayer music = new MusicPlayer();

      Command play = new PlayCommand(music);
      Command stop = new StopCommand(music);

      RemoteControl control = new RemoteControl();

      // To play music
      control.setCommand(play);
      control.pressButton();

      // To stop music
      control.setCommand(stop);
      control.pressButton();
    }
}

// Command interface
interface Command {
    void execute();
}

// Real Worker
class MusicPlayer {

    public void play(){
        System.out.println("Music play!");
    }

    public void stop(){
        System.out.println("Music stop");
    }
}

class PlayCommand implements Command{
    private final MusicPlayer musicPlayer;

    public PlayCommand(MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void execute() {
        musicPlayer.play();
    }
}

class StopCommand implements Command{
    private final MusicPlayer musicPlayer;

    public StopCommand(MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }

    @Override
    public void execute() {
        musicPlayer.stop();
    }
}

// Invoker / Controller
class RemoteControl{
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton(){
        command.execute();
    }
}