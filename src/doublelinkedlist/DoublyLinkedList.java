package doublelinkedlist;

class DoublyLinkedList<T> {
    static class Node<Z> {
        public Z item;
        public Node<Z> previous;
        public Node<Z> next;

        public Node(Z item) {
            this.item = item;
        }
    }

    private Node<T> head, tail = null;
    private int length;

    public DoublyLinkedList() {
        length = 0;
    }

    public DoublyLinkedList(T initValue) {
        length = 0;
        add(initValue);
    }

    public void add(T item) {
        var newNode = new Node<T>(item);

        if (head == null) {
            head = tail = newNode;
            head.previous = null;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        tail.next = null;
        length++;
    }

    public void add(int pos, T item) {
        var newNode = new Node<T>(item);

        if (head == null) {
            add(item);
            return;
        }
        if(pos == 0){
            var next = head;
            head = newNode;
            head.next = next;
            next.previous = head;
            length++;
            return;
        }
        var node = getNode(pos-1);
        var nextNode = node.next;
        node.next = newNode;
        node.next.previous = node;
        node.next.next = nextNode;
        node.next.next.previous = newNode;
        length++;
    }

    public T remove(int pos){
        if(pos == 0){
            var item = head.item;
            head = head.next;
            return item;
        }

        var nodeBefore = getNode(pos-1);
        var nodeAfter = nodeBefore.next.next;
        var oldItem = nodeBefore.next.item;
        nodeBefore.next = nodeAfter;
        nodeAfter.previous = nodeBefore;
        return oldItem;
    }

    public T get(int pos){
        return getNode(pos).item;
    }

    public T set(int pos, T value){
        var node = getNode(pos);
        var old = node.item;
        node.item = value;
        return old;
    }

    public Node<T> getNode(int pos) {
        Node<T> currentNode;
        int currentPos;
        if (pos < size() / 2) {
            currentNode = head;
            currentPos = 0;
            while (currentPos != pos) {
                currentNode = currentNode.next;
                currentPos++;
            }
        } else {
            currentNode = tail;
            currentPos = size() - 1;
            while (currentPos != pos) {
                currentNode = currentNode.previous;
                currentPos--;
            }
        }
        return currentNode;
    }

    public int size() {
        return length;
    }

    public String toString() {
        var returnString = new StringBuilder("[");
        var current = head;
        while (current != null) {
            returnString.append(current.item.toString()).append(", ");
            current = current.next;
        }
        returnString.deleteCharAt(returnString.length() - 1);
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.append("]").toString();
    }

    public String toStringReverse(){
        var returnString = new StringBuilder("[");
        var current = tail;
        while (current != null) {
            returnString.append(current.item.toString()).append(", ");
            current = current.previous;
        }
        returnString.deleteCharAt(returnString.length() - 1);
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.append("]").toString();
    }

    public String nodeConnections(){
        var returnString = new StringBuilder();
        var currentNode = head;
        int idx = 0;
        while(currentNode != null){
            returnString.append("Node ").append(idx).append("; Item: ").append(currentNode.item);
            if(currentNode.previous != null)
                returnString.append(", Prev item: ").append(currentNode.previous.item);
            if(currentNode.next != null)
                returnString.append(", next item: ").append(currentNode.next.item);
            returnString.append("\n");
            currentNode = currentNode.next;
            idx++;
        }
        return returnString.toString();
    }
}