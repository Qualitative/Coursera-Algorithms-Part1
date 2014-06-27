package com.ralko.queue;


public class ArrayQueueTest extends AbstractQueueTest {

    @Override
    public Queue<String> createQueue() {
        return new ArrayQueue<String>();
    }

}
