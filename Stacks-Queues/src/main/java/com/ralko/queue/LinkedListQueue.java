package com.ralko.queue;

import java.util.Iterator;

public class LinkedListQueue<Item> implements Queue<Item> {

    private Node first;
    private Node last;
    private int size;

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Couldn't dequeue from empty queue");
        Item valueToReturn = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        size--;
        return valueToReturn;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int getSize() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new LinkedListQueueIterator();
    }

    private class Node {
        Node next;
        Item item;
    }

    private class LinkedListQueueIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }

    }

}
