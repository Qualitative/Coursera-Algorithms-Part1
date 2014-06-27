package com.ralko.queue;

import java.util.Iterator;

public class ArrayQueue<Item> implements Queue<Item> {

    private Item[] items;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        items = (Item[]) new Object[1];
    }

    //FIXME: fix resizing and moving to zero index logic
    public void enqueue(Item item) {
        if ((tail == items.length) && (tail - head) <= tail / 2) {
            moveToZeroIndex();
            resize(items.length * 2);
        } else if ((tail == items.length)) {
            resize(items.length * 2);
        }
        items[tail++] = item;
        System.out.println("Enqueue:" + items.length);
        System.out.println("" + head + ":" + tail);
    }

    //FIXME: fix resizing and moving to zero index logic
    public Item dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Couldn't dequeue from empty queue");
        Item item = items[head];
        items[head++] = null;
        if ((getSize() > 0) && (getSize() < tail / 2)) {
            moveToZeroIndex();
        }
        if ((getSize() > 0) && (tail == items.length / 4)) {
            resize(items.length / 2);
        }
        System.out.println("Dequeue:" + items.length);
        System.out.println("" + head + ":" + tail);
        return item;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int getSize() {
        return tail - head;
    }

    public Iterator<Item> iterator() {
        return new ArrayQueueIterator();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @SuppressWarnings("unchecked")
    private void moveToZeroIndex() {
        Item[] copy = (Item[]) new Object[items.length];
        for (int i = head, j = 0; i < tail; i++, j++) {
            copy[j] = items[i];
        }
        items = copy;
        tail = getSize();
        head = 0;
    }

    private class ArrayQueueIterator implements Iterator<Item> {

        private int current = head;

        public boolean hasNext() {
            return current != tail;
        }

        public Item next() {
            return items[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }

    }

}
