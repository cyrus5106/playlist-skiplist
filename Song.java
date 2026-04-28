/**
 * Song.java
 * 
 * satellite data stored at each skip list node
 * 
 * A song has a title and tracks its total play count
 * 
 * play count here is kept in sync with the skip list key
 * whenver a song is played, the playlist manager removes the node
 * increments the count, and reinserts at new rank
 */

public class Song {
    /**
     * display title of song
     */
    private String title;
    /**
     * Artist name
     */
    private String artist;
    /**
     * total times song has been played
     */
    private int playCount;

    /**
     * creates new song with zero plays
     * 
     * paramters: title: title of song, artist: artist of song
     */
    public Song(String title, String artist){
        this.title = title;
        this.artist = artist;
        this.playCount = 0;
    }

    /**
     * getters
     */
    public String getTitle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }
    public int getPlayCount() {
        return playCount;
    }

    /**
     * increments play count by 1 and returns new val
     * 
     * caller (PlaylistManager) is responsible for updating skip list key
     * 
     * returns the updated play count after incrementing
     */

    public int incrementPlayCount() {
        return ++playCount;
    }

    /**
     * directly sets the play count
     */

    public void setPlayCount(int count) {
        this.playCount = count;
    }
    @Override
    public String toString() {
        return String.format("\"%s\" by %s (%d play%s)",
        title, artist, playCount, playCount == 1 ? "" : "s");
    }

}
