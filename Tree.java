import java.util.ArrayList;

public class Tree {
    ArrayList<Tree> nodes;

    public Tree () {
        this.nodes = new ArrayList<Tree>();
    }

    public void addNode (Tree add) {
        this.nodes.add(add);
    }

    public void rmNode (Tree rm) {
        this.nodes.remove(rm);
    }
}
