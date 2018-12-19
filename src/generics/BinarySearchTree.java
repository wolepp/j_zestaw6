package generics;

import java.util.Collection;
import java.util.Iterator;

public class BinarySearchTree<E extends Comparable<? super E>> implements Collection<E> {






    @Override
    public boolean add(E e) {
        //dynamicznie
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        //zwraca obiekt klasy BinaryIterator<E extends Comparable<? super e>>
        // powyższa klasa implementuje interfejs Iterator<E>
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
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
