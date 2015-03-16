public class DictBinTree implements Dict {

    protected Node root;
    protected int size, height;
    protected int[] outOrder;
    protected int counter = 0;

    /**
     * Default constructor for DictBinTree
     */
    public DictBinTree() {
      size = height = 0;
    }

    /**
     * Inserts a new value into the dictionary
     *
     * param k - integer to insert
     */
    @Override
    public void insert(int k) {
      int tempHeight;
        if (size != 0) {
            Node y = null;
            Node x = root;
            tempHeight = 1;
            while (x != null) {
                y = x;
                if (k < x.key) {
                    x = x.left;
                } else {
                    x = x.right;
                }
                tempHeight++;
            }
            if (y == null) {
                root.key = k;
            } else if (k < y.key) {
                y.left = new Node(k);
            } else {
                y.right = new Node(k);
            }
            if (height < tempHeight) {
                height = tempHeight;
            }
        } else {
            root = new Node(k);
            height = 1;
        }
        size++;

    }

    /**
     * Finds the smallest value in the tree
     *
     * returns smallest tree value
     */
    protected Node treeMinimum() {
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * Finds the largest value in the tree
     *
     * returns largest tree value
     */
    protected Node treeMax() {
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    /**
     * Creates an ordered copy of the tree's elements
     *
     * returns ordered array
     */
    @Override
    public int[] orderedTraversal() {
        outOrder = new int[size];
        inOrderTreeWalk(root);

        return outOrder;
    }

    /**
     * Recursive tree walk algorithm used for traversal
     *
     * param x - the current node
     */
    protected int[] inOrderTreeWalk(Node x) {
        if (x != null) {
            inOrderTreeWalk(x.left);
            outOrder[counter++] = x.key;
            inOrderTreeWalk(x.right);
        }
        return outOrder;
    }

    /**
     * Searches through the tree to find a value
     *
     * param k - the element to be found
     * returns true if the element is found, false if not
     */
    @Override
    public boolean search(int k) {
        Node x = root;
        while (x != null && k != x.key) {
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        if (x == null) {
            return false;
        } else if (x.key == k) {
            return true;
        }
        return false;

    }

    protected class Node {

        public Node left, right;
        public int key;

        /**
         * Defines a simple Node datastructure
         *
         * param key - the value of the node
         */
        protected Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }
}
