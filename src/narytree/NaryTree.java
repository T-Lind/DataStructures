package narytree;

public class NaryTree<T> {
    private TreeNode<T> firstNode;

    public NaryTree(){
        firstNode = new TreeNode<>(null);
    }

    public NaryTree(T startValue){
        firstNode = new TreeNode<>(startValue);
    }

    public TreeNode<T> getFirstNode(){
        return firstNode;
    }
}
