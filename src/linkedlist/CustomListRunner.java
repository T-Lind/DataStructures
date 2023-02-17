package linkedlist;

public class CustomListRunner {
    public static void main(String[] args) {
        LinkedList<String> listy = new LinkedList<>("apple");

        listy.add("banana");
        listy.add("coconut");
        listy.add("dragonFruit");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nadding eggplant at position 0");
        listy.add(0, "eggplant");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nadding fruit at position 1");
        listy.add(1, "fruit");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nadding grapefruit at position 3");
        listy.add(3, "grapefruit");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nsetting position 0 to lettuce");
        listy.set(0, "lettuce");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nsetting position 1 to tomato");
        listy.set(1, "tomato");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nsetting position 2 to kiwi");
        listy.set(2, "kiwi");
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\ngetting position 5");
        System.out.println("position 5 = " + listy.get(5));

        System.out.println("\ngetting position 1");
        System.out.println("position 1 = " + listy.get(1));

        System.out.println("\nremoving position 0");
        listy.remove(0);
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nremoving position 1");
        listy.remove(1);
        System.out.println("size = " + listy.size() + "  " + listy);

        System.out.println("\nremoving position 3");
        listy.remove(3);
        System.out.println("size = " + listy.size() + "  " + listy);
    }
}