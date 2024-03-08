package db;

public class AVLNode {
    AVLNode left, right;
    String key;

    int entryLocationIndex;
    int height;

    int EntryLocation;

    public AVLNode() {
        left = null;
        right = null;
        key = "";
        height = 0;
    }

    public AVLNode(String key, int entryLocationIndex) {
        this.left = null;
        this.right = null;
        this.key = key;
        this.height = 0;
        this.entryLocationIndex = entryLocationIndex;
    }
}
