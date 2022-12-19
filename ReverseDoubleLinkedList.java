public class ReverseDoubleLinkedList {

    Node head;

    public void revert(Node current, Node previous){
        if (current.next == null){
            head = current;
        } else {
            revert(current.next, current);
        }
        current.next = previous;
        previous.next = null;
    }
    public class Node {
        Node next;

    }
}
