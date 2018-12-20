package generics;

public class Node<E extends Comparable<? super E>> {

    private E value;
    private Node<E> left;
    private Node<E> right;
    private Node<E> parent;

    public Node(E value) {
        this.value = value;
    }

    public E getValue() {
        return this.value;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public void setLeft(Node<E> node) {
        this.left = node;
    }

    public void setRight(Node<E> node) {
        this.right = node;
    }

    public Node<E> parent() {
        return parent;
    }

    public Node<E> leftChild() {
        return left;
    }

    public Node<E> rightChild() {
        return right;
    }
}
