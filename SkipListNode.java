    /**
     * SkipListNode.java
     * 
     * represents a single node in the skiplist. 
     * each node stores a song (satellite data) and a key (play count)
     * 
     * the forward[] array hold references to successor nodes at each level
     * level 0 is base linked list; higher levels "skip" over eles
     */
public class SkipListNode {
    /**
     * song stored at this note
     */
    public Song song;

    /**
     * primary sort key: play count
     */
    public int playCount;

    /**
     * Forward pointers for each lvl of the skip list
     * 
     */
    public SkipListNode[] forward;

    /**
     * Constructs skiplistnode with a gives song, play count, and max lvl
     * 
     * parameters: song: song obj stored at this node. May be null for sentinel nodes,
     * playcount: sort key for this node, level: number of forward pointer lvls allocated
     */
    public SkipListNode(Song song, int playCount, int level) {
        this.song = song;
        this.playCount = playCount;
        this.forward = new SkipListNode[level];
    }
}

