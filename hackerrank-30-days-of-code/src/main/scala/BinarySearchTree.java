import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by govind.bhone on 11/27/2016.
 */
interface Tree<T extends Comparable> {
    public boolean isEmpty();

    public int cardinality();

    public boolean member(T elem);

    public NonEmptyBST<T> add(T elem);
}

class NonEmptyBST<T extends Comparable> implements Tree<T> {
    T data;
    Tree<T> left;
    Tree<T> right;

    public NonEmptyBST(T elem) {
        data = elem;
        left = new EmptyBST<T>();
        right = new EmptyBST<T>();
    }

    public NonEmptyBST(T elem, Tree<T> left, Tree<T> right) {
        data = elem;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int cardinality() {
        return 1 + left.cardinality() + right.cardinality();
    }

    @Override
    public boolean member(T elem) {
        if (data == elem) {
            return true;
        } else {
            if (data.compareTo(elem) < 0) {
                return right.member(elem);
            } else {
                return left.member(elem);
            }
        }
    }

    @Override
    public NonEmptyBST<T> add(T elem) {
        if (elem == data) {
            return this;
        } else {
            if (data.compareTo(elem) < 0) {
                return new NonEmptyBST<T>(data, left, right.add(elem));
            } else {
                return new NonEmptyBST<T>(data, left.add(elem), right);
            }
        }
    }

    @Override
    public String toString() {
        return data + " ";
    }

    ;
}

class EmptyBST<T extends Comparable> implements Tree<T> {

    public EmptyBST() {

    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int cardinality() {
        return 0;
    }

    @Override
    public boolean member(T elem) {
        return false;
    }

    @Override
    public NonEmptyBST<T> add(T elem) {
        return new NonEmptyBST(elem);
    }

    @Override
    public String toString() {
        return "";
    }

    ;
}

public class BinarySearchTree {
    public static void main(String args[]) {
        Tree<Integer> tree = new EmptyBST<Integer>();
        tree.add(123);
        tree.add(456);
        tree.add(789);
        tree.add(100);
        System.out.println(tree.isEmpty());
        System.out.println(tree.member(100));
        System.out.println(tree.cardinality());
        System.out.println(tree);
    }
}