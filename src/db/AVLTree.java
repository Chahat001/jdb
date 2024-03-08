package db;

public class AVLTree {
    private AVLNode root;
    public AVLTree() {
        root = null;
    }


    /**
     *
     * @param avlNode
     * @return
     */
    private int height(AVLNode avlNode) {
        return avlNode == null ? -1 : avlNode.height;
    }

    /**
     *
     * @param lHeight
     * @param rHeight
     * @return
     */
    private int max(int lHeight, int rHeight) {
        return lHeight > rHeight ? lHeight : rHeight;
    }

    /**
     *
     * @param key
     */
    public void insert(String key, int entryLocationIndex) {
        root = insert(key, entryLocationIndex, root);
    }


    /**
     *
     * @param key
     * @param avlNode
     * @return
     */
    private AVLNode insert(String key, int entryLocationIndex, AVLNode avlNode) {
        if (avlNode == null)
            avlNode = new AVLNode(key, entryLocationIndex);
        else if (key.compareTo(avlNode.key) < 0) {
            avlNode.left = insert(key, entryLocationIndex, avlNode.left);
            if (height(avlNode.left) - height(avlNode.right) == 2)
                if (key.compareTo(avlNode.left.key) < 0)
                    avlNode = leftRotation(avlNode);
                else
                    avlNode = leftRightRotation(avlNode);
        } else if (key.compareTo(avlNode.key) >0){
            avlNode.right = insert(key, entryLocationIndex,avlNode.right);
            if (height(avlNode.right) - height(avlNode.left) == 2)
                if (key.compareTo(avlNode.right.key) > 0)
                    avlNode = rightRotation(avlNode);
                else
                    avlNode = rightLeftRotation(avlNode);
        } else
            ; // Duplicate; do nothing
        avlNode.height = max(height(avlNode.left), height(avlNode.right)) + 1;
        return avlNode;
    }

    /**
     *
     * @param avlNode
     * @return
     */
    private AVLNode leftRotation(AVLNode avlNode) {
        AVLNode k1 = avlNode.left;
        avlNode.left = k1.right;
        k1.right = avlNode;
        avlNode.height = max(height(avlNode.left), height(avlNode.right)) + 1;
        k1.height = max(height(k1.left), avlNode.height) + 1;
        return k1;
    }


    /**
     *
     * @param avlNode
     * @return
     */
    private AVLNode rightRotation(AVLNode avlNode) {
        AVLNode node = avlNode.right;
        avlNode.right = node.left;
        node.left = avlNode;
        avlNode.height = max(height(avlNode.left), height(avlNode.right)) + 1;
        node.height = max(height(node.right), avlNode.height) + 1;
        return node;
    }
    /**
     * left-right rotation
     * @param avlNode
     * @return
     */
    private AVLNode leftRightRotation(AVLNode avlNode) {
        avlNode.left = rightRotation(avlNode.left);
        return leftRotation(avlNode);
    }

    /**
     * right-left rotation
     * @param avlNode
     * @return
     */
    private AVLNode rightLeftRotation(AVLNode avlNode) {
        avlNode.right = leftRotation(avlNode.right);
        return rightRotation(avlNode);
    }

    /**
     *
     * @return
     */
    public int countNodes() {
        return countNodes(root);
    }

    /**
     *
     * @param avlNode
     * @return
     */
    private int countNodes(AVLNode avlNode) {
        if (avlNode == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(avlNode.left);
            l += countNodes(avlNode.right);
            return l;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public AVLNode search(String key) {
        return search(root, key);
    }

    /**
     *
     * @param avlNode
     * @param key
     * @return
     */
    private AVLNode search(AVLNode avlNode, String key) {
        AVLNode found = null;
        while ((avlNode != null) && (found==null)) {
            String rval = avlNode.key;
            if (key.compareTo(rval) < 0)
                avlNode = avlNode.left;
            else if (key.compareTo(rval) >0)
                avlNode = avlNode.right;
            else {
                found = avlNode;
                break;
            }
            found = search(avlNode, key);
        }
        return found;
    }

    /**
     *
     */
    public void inorder() {
        inorder(root);
    }

    /**
     *
     * @param avlNode
     */
    private void inorder(AVLNode avlNode) {
        if (avlNode != null) {
            inorder(avlNode.left);
            System.out.print(avlNode.key + " ");
            inorder(avlNode.right);
        }
    }
}
