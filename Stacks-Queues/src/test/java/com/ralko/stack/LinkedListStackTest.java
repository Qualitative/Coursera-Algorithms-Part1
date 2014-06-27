package com.ralko.stack;

public class LinkedListStackTest extends AbstractStackTest {

    @Override
    public Stack<String> createStack() {
        return new LinkedListStack<String>();
    }
    
}