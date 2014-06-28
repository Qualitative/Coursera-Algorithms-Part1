package com.ralko.queue;

public interface Queue<T> extends Iterable<T> {
    void enqueue(T item);
    T dequeue();
    boolean isEmpty();
    int getSize();
}
