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
    public 
}




}
