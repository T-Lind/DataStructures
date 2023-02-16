package narytree;

import java.util.ArrayList;

public class TreeNode<T> {
    private T value;
    private ArrayList<TreeNode<T>> childNodes;

    public TreeNode(){
        childNodes = new ArrayList<>();
        this.value = null;
    }

    public TreeNode(T value){
        childNodes = new ArrayList<>();
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
    public void addChild(T value){
        childNodes.add(new TreeNode<T>(value));
    }


    public TreeNode<T> getChild(int idx){
        return childNodes.get(idx);
    }

    public TreeNode<T> removeChild(int idx){
        return childNodes.remove(idx);
    }

    public String toString(){
        return value.toString();
    }
}
