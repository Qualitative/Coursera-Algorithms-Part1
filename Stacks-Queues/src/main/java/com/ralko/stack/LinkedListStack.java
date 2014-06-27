package com.ralko.stack;

import java.util.Iterator;

public class LinkedListStack<Item> implements Stack<Item> {

    private Node first = null;
    private int size = 0;

    public void push(Item item) {
        Node oldFirst = first;
        
        first = new Node();
        first.next = oldFirst;
        first.item = item;
        
        size++;
    }

    public Item pop() {
        Item valueToReturn = first.item;
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

    public Iterator<Item> iterator() {
        return new LinkedListStackIterator();
    }

    private class Node {
        Node next;
        Item item;;
    }
    
    private class LinkedListStackIterator implements Iterator<Item> {
        
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
