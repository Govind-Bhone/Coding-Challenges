/**
 * Created by Development on 11/12/2016.
 */
public class StackExample {

    private static class Node {
        int data;
        Node next;

        private Node(int data) {
            this.data = data;
        }
    }

    private Node top;

    public void push(int data) {
        Node newNode = new Node(data);
        if (top == null) {
            top = newNode;
        }
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) return -1;
        int data = top.data;
        top = top.next;
        return data;
    }

    public static void main(String args[]){
        StackExample stack = new StackExample();
        stack.push(10);
        stack.push(20);
        stack.push(40);
        System.out.println(stack.top.data);
        stack.pop();
        System.out.println(stack.top.data);
    }

}
