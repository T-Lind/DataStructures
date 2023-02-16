import linkedlist.LinkedList;
import unrolledlinkedlist.UnrolledLinkedList;

public class Main {
    private final static int N_ITEMS = 10_000;

    public static long getTime(){
        return System.currentTimeMillis();
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        UnrolledLinkedList<Integer> unrolledList = new UnrolledLinkedList<>(100);

        var before = 0L;
        var after = 0L;

        before = getTime();
        listAdd(list);
        after = getTime();
        System.out.println("Linked list adding "+N_ITEMS+" items: "+(after-before)+" ms");

        before = getTime();
        unrolledListAdd(unrolledList);
        after = getTime();
        System.out.println("Unrolled linked list adding "+N_ITEMS+" items: "+(after-before)+" ms");

        before = getTime();
        listGet(list);
        after = getTime();
        System.out.println("Linked list get "+N_ITEMS+" items: "+(after-before)+" ms");

        before = getTime();
        unrolledListGet(unrolledList);
        after = getTime();
        System.out.println("Unrolled linked list get "+N_ITEMS+" items: "+(after-before)+" ms");

    }

    public static void listAdd(LinkedList<Integer> list){
        for(int i=0;i<N_ITEMS;i++)
            list.add(i);
    }

    public static void unrolledListAdd(UnrolledLinkedList<Integer> unrolledList){
        for(int i=0;i<N_ITEMS;i++)
            unrolledList.add(i);
    }

    public static void listGet(LinkedList<Integer> list){
        list.get(N_ITEMS-1);
    }
    public static void unrolledListGet(UnrolledLinkedList<Integer> unrolledList){
        unrolledList.get(N_ITEMS-1);
    }
}
