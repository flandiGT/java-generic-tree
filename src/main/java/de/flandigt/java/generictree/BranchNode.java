package de.flandigt.java.generictree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class BranchNode<K, V> implements Node<K, V> {

    private final Map<K, Node<K, V>> children = new HashMap<>();

    public V getValue() {
        throw new RuntimeException("Cannot get value of BranchNode");
    }

    public void put(List<K> keys, V value) {
        if(keys.size() == 1) {
            putLeafNode(keys, value);
        } else {
            putBranchNodeRecursively(keys, value);
        }
    }

    @Override
    public V search(List<K> keys) {
        V foundValue;

        if(keys.size() == 1) {
            K key = keys.get(0);
            Node<K, V> childNode = children.get(key);

            if(childNode == null) {
                return null;
            }

            foundValue = childNode.getValue();
        } else {
            K key = keys.get(0);
            List<K> subKeyList = keys.subList(1, keys.size());

            foundValue = children.get(key).search(subKeyList);
        }

        return foundValue;
    }

    @Override
    public void remove(List<K> keys) {
        if(keys.size() == 1) {
            K key = keys.get(0);
            children.remove(key);
        } else {
            K key = keys.get(0);
            List<K> subKeyList = keys.subList(1, keys.size() - 1);

            children.get(key).remove(subKeyList);
        }
    }

    @Override
    public int size() {
        int size = 0;

        for (Node<K, V> childNode : children.values()) {
            size += childNode.size();
        }

        return size;
    }

    private void putLeafNode(List<K> keys, V value) {
        K key = keys.get(0);
        Node<K, V> leafNode = new LeafNode<>(value);

        children.put(key, leafNode);
    }

    private void putBranchNodeRecursively(List<K> keys, V value) {
        K key = keys.get(0);
        Node<K, V> childNode = children.get(key);

        if(childNode == null) {
            childNode = new BranchNode<>();
            children.put(key, childNode);
        }

        List<K> subKeyList = keys.subList(1, keys.size());
        childNode.put(subKeyList, value);
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return children.values().iterator();
    }
}
