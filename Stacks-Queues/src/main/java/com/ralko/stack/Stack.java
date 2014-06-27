package com.ralko.stack;

public interface Stack<Item> extends Iterable<Item> {
    
    void push(Item item);
    Item pop();
    boolean isEmpty();
    int getSize();
    
}
