package com.ralko.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<T> implements Queue<T> {

    private Node first;
    private Node last;
    private int size;

    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Couldn't dequeue from empty queue");
        }

        T valueToReturn = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }

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
        return new LinkedListQueueIterator();
    }

    private class Node {
        Node next;
        T item;
    }

    private class LinkedListQueueIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
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
