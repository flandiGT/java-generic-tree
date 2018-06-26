package de.flandigt.java.generictree;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Tree<K, V> implements Iterable<V> {

    final Node<K, V> root = new BranchNode<>();

    public void put(TreeKey<K> treeKey, V value) {
        root.put(treeKey.toKeys(), value);
    }

    public V search(TreeKey<K> treeKey) {
        return root.search(treeKey.toKeys());
    }

    public void remove(TreeKey<K> treeKey) {
        root.remove(treeKey.toKeys());
    }

    public int size() {
        return root.size();
    }

    @Override
    public Iterator<V> iterator() {
        return new TreeIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super V> action) {

    }

    @Override
    public Spliterator<V> spliterator() {
        return null;
    }

}
