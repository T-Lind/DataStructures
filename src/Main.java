import linkedlist.LinkedList;
import unrolledlinkedlist.UnrolledLinkedList;
import unrolledlinkedlist.UnrolledNode;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(5, 4);
        System.out.println(list.get(3));
        list.remove(0);
        list.set(1, -4);
        System.out.println(list);
        System.out.println("Size: "+list.size());

        UnrolledLinkedList<Integer> unrolledList = new UnrolledLinkedList<>(5);
        unrolledList.add(2);
        unrolledList.add(3);
        unrolledList.add(4);
        unrolledList.add(7);
        unrolledList.add(8);
        unrolledList.set(4, 9);
        unrolledList.removeLast();
        unrolledList.add(10);
        System.out.println(unrolledList);
        System.out.println("Size: "+unrolledList.size());
        System.out.println(unrolledList.get(4));
    }
}
