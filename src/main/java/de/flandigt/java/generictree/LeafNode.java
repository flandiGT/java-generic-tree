package de.flandigt.java.generictree;

import java.util.Iterator;
import java.util.List;

class LeafNode<K, V> implements Node<K, V> {

    private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator<>();

    private final V value;

    LeafNode(V value) {
        assert(value != null);

        this.value = value;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void put(List<K> keys, V value) {
        throw new RuntimeException("Cannot put value to LeafNode");
    }

    @Override
    public V search(List<K> keys) {
        throw new RuntimeException("Cannot search on LeafNode");
    }

    @Override
    public void remove(List<K> keys) {
        throw new RuntimeException("Cannot remove on LeafNode");
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return EMPTY_ITERATOR;
    }
}
