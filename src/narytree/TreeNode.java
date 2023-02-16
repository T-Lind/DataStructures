package narytree;

import java.util.ArrayList;

public class TreeNode<T> {
    private T value;
    private ArrayList<TreeNode<T>> childNodes;

    public TreeNode(T value){
        this.value = value;
    }

    public T get(){
        return value;
    }

    public T set(T value){
        var old = this.value;
        this.value = value;
        return old;
    }

    public void addChild(TreeNode<T> newNode){
        childNodes.add(newNode);
    }

    public TreeNode<T> getChild(int idx){
        return childNodes.get(idx);
    }

    public TreeNode<T> removeChild(int idx){
        return childNodes.remove(idx);
    }
}
