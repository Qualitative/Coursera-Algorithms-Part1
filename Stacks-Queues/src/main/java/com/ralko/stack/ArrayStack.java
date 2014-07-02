package com.ralko.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {

    private T[] items;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        items = (T[]) new Object[1];
    }

    public void push(T item) {
        if (items.length == n) {
            resize(items.length * 2);
        }
        items[n++] = item;
    }

    public T pop() {

        if (isEmpty()) {
            throw new IllegalStateException("Couldn't pop from empty stack");
        }

        T item = items[--n];
        items[n] = null;
        if (n > 0 && n == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int getSize() {
        return n;
    }

    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] copy = (T[]) new Object[newSize];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    private class ArrayStackIterator implements Iterator<T> {

        private int current = n;

        @Override
        public boolean hasNext() {
            return current > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }
            return items[--current];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }

    }

}
