package com.ralko.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayQueue<T> implements Queue<T> {

    private T[] items;
    private int head;
    private int tail;

    private static final Logger LOG = LoggerFactory.getLogger(ArrayQueue.class);

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        items = (T[]) new Object[1];
    }

    public void enqueue(T item) {
        if ((tail == items.length) && (getSize() <= tail / 2)) {
            moveToZeroIndex();
        } else if ((tail == items.length) && (getSize() > tail / 2)) {
            resize(items.length * 2);
        }
        items[tail++] = item;
    }

    public T dequeue() {

        if (isEmpty()) {
            throw new IllegalStateException("Couldn't dequeue from empty queue");
        }

        T item = items[head];
        items[head++] = null;
        if ((getSize() > 0) && (getSize() <= tail / 2)) {
            moveToZeroIndex();
        }

        if ((getSize() > 0) && (head == 0) && (tail <= items.length / 4)) {
            resize(items.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int getSize() {
        return tail - head;
    }

    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        LOG.debug("Resized to newSize={} from oldSize={}", newSize, items.length);
        LOG.debug("Head: {}, Tail: {}", head, tail);

        T[] copy = (T[]) new Object[newSize];
        for (int i = 0; i < getSize(); i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @SuppressWarnings("unchecked")
    private void moveToZeroIndex() {
        LOG.debug("Moved to zero index {} items. Array length is {}", getSize(), items.length);
        LOG.debug("Head: {}, Tail: {}", head, tail);

        T[] copy = (T[]) new Object[items.length];
        for (int i = head, j = 0; i < tail; i++, j++) {
            copy[j] = items[i];
        }
        items = copy;
        tail = getSize();
        head = 0;
    }

    private class ArrayQueueIterator implements Iterator<T> {

        private int current = head;
        
        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }
            return items[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }

    }

}
