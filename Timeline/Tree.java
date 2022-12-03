package Timeline;

import java.util.ArrayList;

public class Tree {
    ArrayList<Tree> nodes;
    String val;

    public Tree (String val) {
        this.nodes = new ArrayList<Tree>();
        this.val = val;
    }

    public void addNode (Tree add) {
        this.nodes.add(add);
    }

    public void rmNode (Tree rm) {
        // broken way to remove a node but for this context it should be fine?
        // to be deleted probably
        this.nodes.remove(rm);
    }
}
