package de.flandigt.java.generictree;

import java.util.Iterator;
import java.util.Stack;

class TreeIterator<K, V> implements Iterator<V> {

    private final Tree<K, V> tree;
    private final Stack<Node<K, V>> nodes = new Stack<>();
    private Node<K, V> currentNode;
    private final Stack<Iterator<Node<K, V>>> iterators = new Stack<>();

    TreeIterator(Tree<K, V> tree) {
        this.tree = tree;
        nodes.push(tree.root);
        iterators.push(tree.root.iterator());
    }

    @Override
    public boolean hasNext() {
        Iterator<Node<K, V>> currentIterator = iterators.peek();
        boolean hasNext = currentIterator.hasNext();

        while(!hasNext && !iterators.isEmpty()) {
            iterators.pop();

            if(!iterators.isEmpty()) {
                currentIterator = iterators.peek();
                hasNext = currentIterator.hasNext();
            } else {
                return false;
            }
        }

        while(hasNext) {
            currentNode = currentIterator.next();
            currentIterator = currentNode.iterator();
            iterators.push(currentIterator);
            hasNext = currentIterator.hasNext();
        }

        return true;
    }

    @Override
    public V next() {
        return currentNode.getValue();
    }
}
