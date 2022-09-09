package project5;

/**
 * This class is a binary search tree that uses RestStop as the datafield.
 */
public class BSTMountain {
    private int size = 0;
    private Node root;

    /**
     * a empty constructor.
     */
    public BSTMountain(){
        root = null;
    }

    // Helper valuable for add.
    private boolean added;

    /**
     * Adds a RestStop to the tree.
     * @param data A RestStop that need to be added
     * @return True if the node is successfully add, False if not.
     */
    public boolean add(RestStop data) throws NullPointerException{
        added = false;
        if(data==null) throw new NullPointerException();
        // Run the recursion method
        root = add(data,root);
        if(added)size+=1;
        return added;
    }

    /**
     * A recursion method that goes down the tree to add a RestStop.
     * @param data RestStop that need to be added.
     * @param current The node that is currently running.
     * @return the value that returns to the current.
     */
    private Node add(RestStop data, Node current){
        // True if we find a spot for the data
        if(current==null){
            added = true;
            return new Node(data);
        }
        // decide go left or right
        int comp = current.data.compareTo(data);
        if(comp>0){
            current.left = add(data, current.left);

        }
        else if(comp<0){
            current.right = add(data, current.right);
        }
        // When the label duplicated.
        else added = false;
        // Check if this need balancing
        checkRotation(current);
        // Check if the level is correct
        current.checkLevel();
        return current;
    }

    /**
     * Get the difference of value of left and right node.
     * @param node
     * @return The left node's level minus right node's level
     */
    private int getDifference(Node node){
        int l = node.left==null ? 0:node.left.level;
        int r = node.right==null ? 0:node.right.level;
        return l-r;
    }

    /**
     * Check and do rotation if necessary.
     * @param current
     */
    private void checkRotation(Node current){
        int difference = getDifference(current);
        if(difference==2){
            if(getDifference(current.left)>=0){
                //LL Rotation
                RestStop tmp = current.data;
                Node tmp1 = current.right;
                current.data = current.left.data;
                current.right = current.left;
                current.right.data = tmp;
                current.left = current.left.left;
                current.right.left = current.right.right;
                current.right.right = tmp1;
                current.right.checkLevel();
            }
            else if(getDifference(current.left)<0){
                //LR Rotation
                RestStop tmp = current.data;
                Node tmp1 = current.right;
                current.data = current.left.right.data;
                current.left.right.data = tmp;
                current.right = current.left.right;
                current.left.right = current.right.left;
                current.right.left = current.right.right;
                current.right.right = tmp1;
                current.left.checkLevel();
                current.right.checkLevel();
            }
        }else if(difference==-2){
            if(getDifference(current.right)<=0){
                //RR rotation
                RestStop tmp = current.data;
                Node tmp1 = current.left;
                current.data = current.right.data;
                current.left = current.right;
                current.left.data = tmp;
                current.right = current.right.right;
                current.left.right = current.left.left;
                current.left.left = tmp1;
                current.left.checkLevel();
            }
            else if(getDifference(current.right)>0){
                //RL rotation
                RestStop tmp = current.data;
                Node tmp1 = current.left;
                current.data = current.right.left.data;
                current.right.left.data = tmp;
                current.left = current.right.left;
                current.right.left = current.left.right;
                current.left.right = current.left.left;
                current.left.left = tmp1;
                current.left.checkLevel();
                current.right.checkLevel();
            }
        }
    }

    private StringBuffer output = new StringBuffer();

    /**
     * Start walking Hiker down.
     * Print the output
     */
    public void startTrip(){
        if(root == null) return;
        WalkingDown(root, new Hiker());
        System.out.println(output);
    }

    /**
     * A recursion function that have the Hiker goes down the mountain.
     * @param current
     * @param William the Hiker.
     */
    private void WalkingDown(Node current,Hiker William){
        William.addSupplies(current.data.getSupplies());
        // To store the hiker status before entering the node.
        Hiker timeLeaper;
        if(William.consumeSupplies(current.data.getObstacles()) ){
            if(current.left==null && current.right==null && getDepth(current)== root.level){
                // reach the bottom
                output.append(getPath(current)+"\n");
            }
            if(William.consumeFood()){
                //Copy the status of the hiker
                timeLeaper = new Hiker(William.getSupplies());
                if(current.left != null){
                    WalkingDown(current.left, William);
                    // resume the status.
                    William = timeLeaper;
                }
                if(current.right != null){
                    WalkingDown(current.right, William);
                    // resume the status.
                    William = timeLeaper;
                }
            }
        }
    }

    /**
     * A subClass.
     * Data field that is the node of the tree.
     */
    private class Node implements Comparable<Node>{
        RestStop data;
        Node left;
        Node right;
        int level=1;
        /**
         * Constructor that uses a RestStop as its data
         * @param data
         */
        public Node(RestStop data){
            this.data = data;
        }
        /**
         * Make the compare method depends on how RestStop compares.
         * @param o another node
         * @return >1 if bigger, 0 if equal, <1 if smaller
         */
        @Override
        public int compareTo(Node o) {
            return data.compareTo(o.data);
        }
        /**
         * verify the level of the node
         */
        public void checkLevel(){
            int l = left==null ? 0: left.level;
            int r = right==null ? 0: right.level;
            level =  l> r ? l+1 : r+1;
        }
    }

    /**
     * @param target
     * @return Return the depth of the target node
     */
    public int getDepth(Node target){
        return getDepthRec(target,root);
    }
    /**
     * A recursion function that get the depth going under the tree.
     * @param target
     * @param current
     * @return the depth of the Node
     */
    private int getDepthRec(Node target, Node current){
        if(current == null) throw new NullPointerException("Node is not in the tree");
        int comp;
        comp = target.compareTo(current);
        if (comp < 0) {
            return 1+getDepthRec(target, current.left);
        }
        else if (comp > 0){
            return 1+getDepthRec(target, current.right);
        }
        else {
            return 1;
        }
    }

    /**
     * find a path that can go from the root to the targeting node
     * @param target
     * @return A string that contains a path.
     */
    private String getPath(Node target){
        return getPathRec(target,root);
    }

    /**
     * A recursion that goes down the tree to find the path
     * @param target
     * @param current
     * @return path that has already gone.
     */
    private String getPathRec(Node target, Node current){
        if(current == null) throw new NullPointerException("Node is not in the tree");
        int comp;
        comp = target.compareTo(current);
        if (comp < 0) {
            return current.data.getLabel()+" "+getPathRec(target, current.left);
        }
        else if (comp > 0){
            return current.data.getLabel()+" "+getPathRec(target, current.right);
        }
        else {
            return current.data.getLabel();
        }
    }

    /**
     * @return the size of the tree
     */
    public int getSize() {
        return size;
    }
}
