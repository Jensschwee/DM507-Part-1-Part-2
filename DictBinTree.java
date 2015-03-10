
public class DictBinTree implements Dict {

    public Node root;
    public int size = 0, height = 0;

    public DictBinTree() {
    }

    @Override
    public void insert(int k) {
        if (size != 0) {
            Node y = null;
            Node x = root;
            int tempHeight = 1;
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
            if (height < ++tempHeight) {
                height = tempHeight;
            }
        } else {
            root = new Node(k);
            height = 1;
        }
        size++;

    }

    public Node treeMinimum() {
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node treeMax() {
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    @Override
    public int[] orderedTraversal() {
        outOrder = new int[size];
        inorderTreeWalk(root);

        return outOrder;
    }

    int[] outOrder;
    int counter = 0;

    public int[] inorderTreeWalk(Node x) {
        if (x != null) {
            inorderTreeWalk(x.left);
            outOrder[counter++] = x.key;
            inorderTreeWalk(x.right);
        }
        return outOrder;
    }

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

        protected Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

}
