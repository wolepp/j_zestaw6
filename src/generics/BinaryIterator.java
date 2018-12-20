package generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryIterator<E extends Comparable<? super E>>
        implements Iterator<E> {

    private BinarySearchTree bst;
    private Node<E> currentNode;
    private boolean start;

    public BinaryIterator(BinarySearchTree<E> bst) {
        this.bst = bst;
        currentNode = mostLeftChild(bst.root);
        start = true;
    }

    @Override
    public boolean hasNext() {
        return getNextNode(currentNode) != null;
    }

    @Override
    public E next() {
        if (hasNext()) {
            if (!start)
                currentNode = getNextNode(currentNode);
            else
                start = false;

            return currentNode.getValue();
        }
        throw new NoSuchElementException("No more positions available");

    }

    private Node<E> getNextNode(Node<E> node) {
        if (node.rightChild() != null)
            return mostLeftChild(node.rightChild());
        else {
            while (node.parent() != null && node == node.parent().rightChild())
                node = node.parent();
            return node.parent();
        }
    }

    private Node<E> mostLeftChild(Node<E> node) {
        while (node.leftChild() != null)
            node = node.leftChild();
        return node;
    }
}
