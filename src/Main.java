import linkedlist.LinkedList;

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
    }
}
