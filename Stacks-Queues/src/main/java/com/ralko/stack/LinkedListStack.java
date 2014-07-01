package com.ralko.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {

    private Node first;
    private int size;

    public void push(T item) {
        Node oldFirst = first;

        first = new Node();
        first.next = oldFirst;
        first.item = item;

        size++;
    }

    public T pop() {

        if (isEmpty()) {
            throw new IllegalStateException("Couldn't pop from empty stack");
        }

        T valueToReturn = first.item;
        first = first.next;
        size--;
        return valueToReturn;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    public Iterator<T> iterator() {
        return new LinkedListStackIterator();
    }

    private class Node {
        Node next;
        T item;
    }

    private class LinkedListStackIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }
            T item = current.item;
            current = current.next;
            return item;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }

    }

}
