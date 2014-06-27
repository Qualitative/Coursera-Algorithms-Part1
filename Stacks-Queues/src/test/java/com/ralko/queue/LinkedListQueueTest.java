package com.ralko.queue;


public class LinkedListQueueTest extends AbstractQueueTest {

    @Override
    public Queue<String> createQueue() {
        return new LinkedListQueue<String>();
    }

}
