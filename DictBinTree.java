public class DictBinTree implements Dict {
	
    public Node root;
    public int size, height;

    public DictBinTree() {
        root = new Node(0);
	}

	@Override
	public void insert(int k) {
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;
            if (k < x.key)
                x = x.left;
            else
                x = x.right;
        }
        if (y == null)
            root.key = k;
        else if (k < y.key)
            y.left = new Node(k);
        else
            y.right = new Node(k);        
	}

    @Override
    public int[] orderedTraversal() {
    	return null;
    }

    @Override
    public boolean search(int k) {
        Node x = root;
    	while (x == null && k != x.key) {
            if (k < x.key)
                x = x.left;
            else
                x = x.right;
        }
        if (x.key == k)
            return true;
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