package BEHAVIORAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Iterator_Design_Pattern {
    public static void main(String[] args) {
        MyAggregate<String> names = new NameCollection();
        MyIterator<String> nameIterator = names.iterator();

        while (nameIterator.hasNext()) {
            System.out.println(nameIterator.next());
        }

        MyAggregate<Integer> numbers = new NumberCollection();
        MyIterator<Integer> numberIterator = numbers.iterator();

        while (numberIterator.hasNext()) {
            System.out.println(numberIterator.next());
        }
    }
}

// Traditional approach
class Playlist {
    private ArrayList<String> songs;
    public Playlist() {
        songs = new ArrayList<>();
    }
    public void addSong(String song) {
        songs.add(song);
    }
    public void playPlaylist() {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println("Playing song: " + songs.get(i));
        }
    }
}//  if we add more types of playlists in the future,
// such as shuffle or repeat modes this will very hard to maintain


// Iterator Design Pattern
interface MyIterator<T> {
    boolean hasNext();
    T next();
}

interface MyAggregate<T> {
    MyIterator<T> iterator();
}


class NameCollection implements MyAggregate<String> {

    private String[] names = {"Aman", "Ravi", "Neha"};

    @Override
    public MyIterator<String> iterator() {
        return new NameIterator();
    }

    private class NameIterator implements MyIterator<String> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public String next() {
            return names[index++];
        }
    }
}

class NumberCollection implements MyAggregate<Integer> {

    private Integer[] numbers = {1,2,3,4,5,6};

    @Override
    public MyIterator<Integer> iterator() {
        return new NumberIterator();
    }

    private class NumberIterator implements MyIterator<Integer> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < numbers.length;
        }

        @Override
        public Integer next() {
            return numbers[index++];
        }
    }
}


interface PlaylistIterator {
    boolean hasNext();
    String next();
}

enum PlaylistIteratorType {
    SIMPLE,
    SHUFFLED,
}

class Playlistt {

    private final List<String> songs = new ArrayList<>();

    public void addSong(String song) {
        songs.add(song);
    }

    public List<String> getSongs() {
        return songs;
    }

    public PlaylistIterator iterator(PlaylistIteratorType type) {

        return switch (type) {
            case SIMPLE -> new SimplePlaylistIterator(this);
            case SHUFFLED -> new ShuffledPlaylistIterator(this);
        };
    }
}

class SimplePlaylistIterator implements PlaylistIterator {

    private final List<String> songs;
    private int index = 0;

    public SimplePlaylistIterator(Playlistt playlist) {
        this.songs = playlist.getSongs();
    }

    @Override
    public boolean hasNext() {
        return index < songs.size();
    }

    @Override
    public String next() {
        return songs.get(index++);
    }
}

class ShuffledPlaylistIterator implements PlaylistIterator {

    private final List<String> shuffled;
    private int index = 0;

    public ShuffledPlaylistIterator(Playlistt playlist) {

        this.shuffled = new ArrayList<>(playlist.getSongs());
        Collections.shuffle(this.shuffled);
    }

    @Override
    public boolean hasNext() {
        return index < shuffled.size();
    }

    @Override
    public String next() {
        return shuffled.get(index++);
    }
}





