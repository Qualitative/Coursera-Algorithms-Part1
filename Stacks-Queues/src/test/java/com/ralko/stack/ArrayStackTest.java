package com.ralko.stack;


public class ArrayStackTest extends AbstractStackTest {

    @Override
    public Stack<String> createStack() {
        return new ArrayStack<String>();
    }

}
