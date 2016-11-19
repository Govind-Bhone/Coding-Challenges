/**
 * Created by govind.bhone on 11/11/2016.
 */

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListCycle {
    Node head;

    LinkedListCycle() {

    }

    LinkedListCycle(Node head) {
        this.head = head;
    }

    private boolean isListEmpty() {
        return head == null;
    }

    public void append(int data) {
        if (isListEmpty()) {
            head = new Node(data);
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);
    }

    public void prepend(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public int size() {
        Node current = head;
        int size = 1;
        while (current.next != null) {
            size++;
        }
        return size;
    }

    public int indexOf(int data) {
        if (isListEmpty()) return -1;

        if (head.data == data) return 0;

        Node current = head;
        int index = 0;
        while (current.next != null) {
            index = index + 1;
            if (current.data == data) return index;
            current = current.next;
        }
        return -1;
    }

    public void delete(int data) {
        if (isListEmpty()) return;

        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return;

            }
            current = current.next;
        }
    }

    public void sort() {
        if (isListEmpty()) return;

        if (size() == 1) return;

        Node iNode = head;

        while (iNode.next != null) {
            Node jNode = iNode.next;
            while (jNode.next != null) {
                if (iNode.data > jNode.data) {
                    int tmp = iNode.data;
                    iNode.data = jNode.data;
                    jNode.data = tmp;
                }
                jNode = jNode.next;
            }

        }
    }

    public boolean isCycle() {
        /*
        Set<Node> seen = new HashSet<>();
        while (head != null) {
            seen.add(head);
            head = head.next;
            if (seen.contains(head)) return true;
        }
        return false;
        */
        if (isListEmpty()) return false;

        // Rabbit tortoise problem
        Node tortoise = head;
        Node rabbit = head.next;
        while (tortoise != rabbit) {
            if (rabbit == null || rabbit.next == null) return false;
            tortoise = tortoise.next;
            rabbit = rabbit.next.next;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isListEmpty()) return sb.append("{ }").toString();
        Node current = head;
        sb.append("{");
        int index = 0;
        while (current.next != null) {
            index = index + 1;
            sb.append(current.data + ",");
            if (index == size()) {
                sb.append(current.data);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String args[]) {
        LinkedListCycle list = new LinkedListCycle(new Node(100));
        list.append(20);
        list.append(45);
        list.prepend(23);
        System.out.println("Size is" + list.size());
        System.out.println("Printing Elements: " + list);
        list.delete(45);
        list.indexOf(23);
        list.sort();
        System.out.println("Printing Elements: " + list);
    }


}
