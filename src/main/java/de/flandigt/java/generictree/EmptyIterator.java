package de.flandigt.java.generictree;

import java.util.Iterator;
import java.util.NoSuchElementException;

class EmptyIterator<E> implements Iterator<E> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        throw new NoSuchElementException();
    }
}
