package generics;

import java.util.Iterator;

public class BinaryIterator<E extends Comparable<? super E>>
        implements Iterator<E> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }
}
