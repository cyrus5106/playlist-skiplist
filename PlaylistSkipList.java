import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlaylistSkipList {
    /**
     * PlaylistSkipList.java
     * 
     * Skip list that maintains songs sorted
     * by play count in descending order
     * 
     * Ties are broken alphabetically by title
     * 
     * Skip List Overview
     * 
     * A skip list is a probabilistic
     * data structure that layers multiple linked lists
     * on top of a base sorted list. Higher layers act as
     * sort of "express lanes" that can skip large blocks of nodes,
     * allowing a runtime of O(long(n)) to be achieved. 
     * 
     * Search, insert, and delete. Similar to a balanced BST, far simpler
     * to implement and modifiy.
     * 
     */

public class PlaylistSkipList {
    
    /**
     * Max num of levles in the skip list, taking log2(expected max songs)
     */

    private static final int max_level = 16;

    /**
     * Prob of promoting a node to next level
     */

    private static final double promotion_probability = 0.5;
    
    /**
     * Head node, play count is max_value so it compares
     * "before" everything
     */

    private final SkipListNode head;

    /**
     * tail node, play count is min_value so it compares "after everything"
     */

    private final SkipListNode tail;

    /**
     * current highest occupied level in the skip list
     */

    private int currentLevel;

    /**
     * total num of songs in the list
     */
    private int size;

    /**
     * radnom source for probabilistic level generation
     */

    private final Random random;

    /**
     * Contsructs an empty PlaylistSkipList
     * Head and tail spans all max_level levels from start
     */
    public PlaylistSkipList() {
        head = new SkipListNode(null, Integer.max_level, max_level);
        tail = new SkipListNode(null, Integer.min_value, max_level);
        for (int i = 0; i < max_level; i++){
            head.forward[i] = tail;
        }
        currentLevel = 0;
        size = 0;
        random = new Random();
    }

    /**
     * Compare
     * 
     * compares two (playcount, title) pairs using the playlist ordering:
     *  - higher play count ranks first (descending by count)
     *  - equal play counts are broken aplphabetically ascedning by title
     * Returns negative if (countA, titleA) should come before (countB, titleB)
     * 
     * param: countA play count of the first element
     * param: titleA title of the first ele
     * param: countB play count of second ele
     * param: titleB title of the second ele
     *
     */
    private int compare(int countA, String titleA, int countB, String titleB) {
        if (countA != countB) {
            return countB - countA;
        }
    
        if (titleA == null || titleB == null) {
            return 0;
        }
        return titleA.compareTo(titleB);
    }

    /**
     * Level generation
     * 
     * randomly generates a level for a new node by flipping coins.
     * Each successive level is reached with probability promotion_probability
     * The result is capped at max_level
     * 
     */

    private int randomLevel() {
        int level = 1;
        while (level < max_level && radnom.nextDouble() < promotion_probability) {
            level ++;
        }
        return level;
    }

    /**
     * Core Ops
     * 
     * Insert: intserts a song into the skip list at the positon determined by play count
     * if a song with the same title already exists, insertion is rejected
     * and false is returned
     * 
     * param: song, the song to be inserted, return true if inserted
     */

    public boolean insert(Song song){
        if (lookupByTitle(song.getTitle()) != null){
            return false;
        }
        int newCount = song.getPlayCount();
        String newTitle = song.getTitle();
        SkipListNode[] update = new SkipListNode[max_level];
        SkipListNode current = head;
        for (int i = currentLevel; i >= 0; i--) {
            while (current.forward[i] != tail && compare(current.forward[i].playCount, current.forward[i].song.getTitle(), newCount, newTitle) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }
        int nodeLevel = randomLevel() - 1;
        if (nodeLevel > currentLevel) {
            for (int i = currentLevel + 1; i <= nodeLevel; i++){
                update[i] = head;
            }
            currentLevel = nodeLevel;
        }
        SkipListNode newNode = new SkipListNode (song, newCount, nodeLevel + 1);
        for (int i =0; i <= nodeLevel; i++){
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
        size++;
        return true;
    }

}   





}
