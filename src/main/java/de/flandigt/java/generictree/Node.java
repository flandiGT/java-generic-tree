package de.flandigt.java.generictree;

import java.util.List;

interface Node<K, V> extends Iterable<Node<K, V>> {
    V getValue();
    void put(List<K> keys, V value);
    V search(List<K> keys);
    void remove(List<K> keys);
    int size();
}
