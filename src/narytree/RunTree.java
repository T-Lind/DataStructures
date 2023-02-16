package narytree;

public class RunTree {
    public static void main(String[] args) {
        NaryTree<Integer> tree = new NaryTree<>(3);
        tree.getFirstNode().addChild(9);
        tree.getFirstNode().addChild(15);
        tree.getFirstNode().addChild(17);
        tree.getFirstNode().getChild(0).addChild(6);
        tree.getFirstNode().getChild(0).addChild(9);
    }
}
