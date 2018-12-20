package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<E extends Comparable<? super E>> implements Collection<E> {

    protected Node<E> root;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean add(E e) {
        Node<E> node = new Node<>(e);

        if (root == null)
            root = node;
        else {
            addChild(node, root);
        }

        //zwraca true, jeśli obiekt się zmienił; BST w wyniku add() zawsze się zmienia
        return true;
    }

    private void addChild(Node<E> e, Node<E> parent) {
        if (e.getValue().compareTo(parent.getValue()) < 0) {
            if (parent.leftChild() == null) {
                parent.setLeft(e);
                e.setParent(parent);
            }
            else
                addChild(e, parent.leftChild());
        }
        else {
            if (parent.rightChild() == null) {
                parent.setRight(e);
                e.setParent(parent);
            }
            else
                addChild(e, parent.rightChild());
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new BinaryIterator<E>(this);
    }


    @Override
    public Object[] toArray() {
        ArrayList<E> array = new ArrayList<>();
        addElementsToArray(array);
        return array.toArray();

    }

    private void addElementsToArray(List<E> list) {

        //dodaj każdy element do arraya i tam ten, jeśli nie ma dziecków
        // to go po prostu dodaj
        // dodaj też lewy i prawy
        // oczywiście rekurencyjnie xDDDDDDDD
        //TODO

    }

    /*
    1. add(current.left)
    2. addCurrent()
    3. add(current.right)
     */



    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    //PONIŻSZE SĄ UNSUPPORTED, ZGODNIE Z POLECENIEM
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
