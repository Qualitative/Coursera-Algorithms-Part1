package com.ralko.stack;

import java.util.Iterator;

public class ArrayStack<Item> implements Stack<Item> {

    private Item[] items;
    private int N;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        items = (Item[]) new Object[1];
    }

    public void push(Item item) {
        if (items.length == N)
            resize(items.length * 2);
        items[N++] = item;
    }

    public Item pop() {
        if (isEmpty())
            throw new IllegalStateException("Couldn't pop from empty stack");
        Item item = items[--N];
        items[N] = null;
        if (N > 0 && N == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int getSize() {
        return N;
    }

    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }
    
    private class ArrayStackIterator implements Iterator<Item> {
        
        private int current = N;

        public boolean hasNext() {
            return current > 0;
        }

        public Item next() {
            return items[--current];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }
        
    }

}
