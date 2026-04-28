import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

/**
 * PlaylistManager.java
 * 
 * Interactive command-line app fort the PlaylistSkipList
 * Commands:
 * add
 * play
 * remove
 * search
 * top songs
 * songs sorted by play count
 * 
 */

public class Playlistmanager {
    private static final PlaylistSkipList playlist = new PlaylistSkipList();
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println(" Playlist Manager Skip List Demo");
        System.out.println("==============================================");
        System.out.println("Type 'help' for a list of commands.\n");
        preloud();
        while(true){
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if(line.isEmpty()) continue;
            String[] parts = line.split("\\s+", 2);
            String command = parts[0].toLowerCase();
            String args1 = parts.length > 1 ? parts[1].trim() : "";
            switch (command) {
                case "add": cmdAdd(args1);
                break;
                case "play": cmdPlay(args1);
                break;
                case "remove": cmdRemove(args1);
                break;
                case "search": cmdSearch(args1);
                break;
                case "top": cmdTop(args1);
                break;
                case "list": cmdList();
                break;
                case "debug": cmdDebug();
                break;
                case "help": printHelp();
                break;
                case "quit":
                case "exit";
                System.out.println("goodbye");
                return;
                default: System.out.println("unknown command. Type 'help' for options");
            }
        }
    }
    /**
     * Command Handlers
     */

    /**
     * adds new song to plylist
     * expected format is <title> | <artist>
     */

    private static void cmdAdd(String arg){
        if(arg.isEmpty() || !arg.contains("|")) {
            System.out.println("Usage: add <title> | <artist>");
            return;
        }
        String[] fields = arg.split("\\|", 2);
        String title = fields[0].trim();
        String artist = fields[1].trim();
        if(title.isEmpty() || artist.isEmpty()) {
            System.out.println("Both title and artist is required");
            return;
        }
        Song song = new Song(title, artist);
        boolean insterted = playlist.insert(song);
        if (inserted) {
            System.out.printf("Added: %s%n", song);
        } else {
            System.out.printf("a song titled \"%s\" already exists.%n", title);
        }
    }
    /**
     * records one play for the named song and shows its new rank
     */
    private static void cmdPlay(String title) {
        if(title.isEmpty()) {
            System.out.println("Usage: play <title>");
            return;
        }
        Song updated = playlist.play(title);
        if(updated == null) {
            System.out.printf("song not found: \"%s\"%n", title);
        } else {
            System.out.printf("Now playing: %s%n", updated);
            showRank(updated);
        }
    }

    /**
     * Removes a song by title
     */
    private static void cmdRemove(String title) {
        if(title.isEmpty()) {
            System.out.println("Usage: remove {<title>");
            return;
        }
        Song found = playlist.lookupByTitle(title);
        if (found == null) {
            System.out.printf("Song not found: \"%s\"%n", title);
            return;
        }
        playlist.remove(found.getPlayCount(), found.getTitle());
        System.out.printf("Removed; %s%n", found);
    }
    /**
     * Searches for song by title and prints its details and ranks
     */
    private static void cmdSearch(String title){
        if(title.isEmpty()) {
            System.out.println("Usage: search <title>");
            return;
        }
        Song found = playlist.lookupByTitle(title);
        if(found == null){
            System.out.printf("Not found: \"%s\"%n", title);
        } else {
            System.out.printf("Found: %s%n", found);
            showRank(found);
        }
    }
    /**
     * prints the top N songs (defaulted to 5)
     * @param arg
     */
    private static void cmdTop(String arg) {
        int n = 5;
        if (!arg.isEmpty()){
            try{
                n = Integer.parseInt(arg);
                if (n <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("usage: top [positive integer]");
                return;
            }
        }
        List<Song> top = playlist.topN(n);
        if(top.isEmpty()) {
            System.out.println("The playlist is empty");
            return;
        }
        System.out.printf("Top %d song %n", Math.min(n, top.size()));
        for (int i = 0; i < top.size(); i++){
            System.out.printf(" %2d. %s%n", i + 1, top.get(i));
        }
    }
    /**
     * prints all songs in descending play-count order
     */
    private static void cmdList(){
        List<Song> all = playlist.getAllSorted();
        if (all.isEmpty()) {
            System.out.println("The playlist is empty");
            return;
        }
        System.out.println("All songs (most played first)");
        for (int i = 0; i < all.size(); i++) {
            System.out.printf(" %2d. %s%n", i+ 1, all.get(i));
        }
    }
    /**
     * Prints the raw multi level structure of the skiplist for debug
     */
    private static void cmdDebug() {
        System.out.println(" Skip list structure");
        System.out.println(play.toString());
        System.out.printf("Total songs: %d%n", playlist.size());
    }
    /**
     * HELPERS:
     */

    /**
     * displays the 1-based rank of song in the current playlist
     */

    private static void showRank(Song song) {
        List<Song> all = playlist.getAllSorted();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getTitle().equalsIgnoreCase(song.getTitle())) {
                System.out.printf("  Current rank: #%d of %d%n", i+ 1, all.size());
                return;
            }
        }
    }
    /**
     * prints all commands
     */

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add <title> | <artist>   add a new song");
        System.out.println("  play <title>             record one play");
        System.out.println("  remove <title>           remove a song");
        System.out.println("  search <title>           look up a song by title");
        System.out.println("  top [n]                  show top n songs (default to 5)");
        System.out.println("  list                     show all songs sorted by plays");
        System.out.println("  debug                    print skip list internal structure");
        System.out.println("  help                     show this message");
        System.out.println("  quit                     exit");
    }

    /**
     * preloaded set of songs for demo
     */

    private static void preload() {
        Object[][] data = {
            {"Fearless",       "Pink Floyd",             182},
            {"Figerbib",         "Aphex Twin",            122},
            {"Xtal",       "Aphex Twin",      111},
            {"Let Down",  "Radiohead",           110},
            {"Oblivius",                  "The Strokes",       98},
            {"Oblivion",     "Grimes",         90},
            {"Do You Realize??",              "The Flaming Lips",      85},
            {"Watermelon Man",          "Herbie Hancock",       83},
            {"Superstition",             "Stevie Wonder",      75},
            {"Lorelei",              "Cocteau Twins",    74},
        };
 
        for (Object[] row : data) {
            Song s = new Song((String) row[0], (String) row[1]);
            s.setPlayCount((int) row[2]);
            playlist.insert(s);
        }
 
        System.out.println("Loaded 10 songs. Type 'list' to see them, or 'help' for commands.");
    }
}


}   

