/**
 * Created by Development on 11/12/2016.
 */
public class QueueExample {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() {
        return head.data;
    }

    public void enqueue(int data) {
        Node newNode = new Node(data);

        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public int dequeue() {
        if (isEmpty()) return -1;
        int tmp = head.data;
        head = head.next;
        return tmp;
    }

    public static void main(String args[]) {
        QueueExample queue = new QueueExample();
        System.out.println("Is Queue Empty : "+queue.isEmpty());
        queue.enqueue(12);
        System.out.println("Is Queue Empty : " + queue.isEmpty());
        queue.enqueue(34);
        queue.enqueue(56);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("Is Queue Empty : " + queue.isEmpty());
    }
}
