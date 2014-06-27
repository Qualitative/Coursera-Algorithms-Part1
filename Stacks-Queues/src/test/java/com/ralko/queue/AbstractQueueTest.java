package com.ralko.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractQueueTest {
    
    private Queue<String> queue;
    
    @Before
    public void init() {
        queue = createQueue();
    }
    
    public abstract Queue<String> createQueue();

    @Test
    public void shouldBeEmptyWhenInitialized() {
        // Then
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
    }
    
    @Test
    public void shouldInsertItemWhenEnqueueIsInvoked() {
        // When 
        queue.enqueue("Item");
        // Then
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getSize());
    }
    
    @Test
    public void shouldReturnLeastRecentlyAddedObjectWhenDequeueIsInvoked() {
        // Given
        String item1 = new String("Item1");
        String item2 = new String("Item2");
        
        queue.enqueue(item1);
        queue.enqueue(item2);
        
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.getSize());
        
        // When
        String dequeuedItem1 = queue.dequeue();
        // Then
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getSize());
        assertSame(item1, dequeuedItem1);
        
        // When
        String dequeuedItem2 = queue.dequeue();
        // Then
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
        assertSame(item2, dequeuedItem2);
    }
    
    @Test
    public void shouldBeIterableAndReturnValuesInOrderTheyWereAdded() {
        // Given
        String item1 = new String("Item1");
        String item2 = new String("Item2");
        
        queue.enqueue(item1);
        queue.enqueue(item2);
        
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.getSize());
        
        // When
        int i = 0;
        String [] items = new String[2];
        for (String item : queue) {
            items[i++] = item;
        }
        
        // Then
        assertSame(item1, items[0]);
        assertSame(item2, items[1]);
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.getSize());
    }
    
    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfDequeueFromEmptyQueue() {
        // When
        queue.dequeue();
    }
    
    @Test
    public void shouldPushAndPopManyItemsWithoutExceptions() {
        // Given
        int numberOfItems = 100;
        
        // When
        for(int i = 0; i< numberOfItems; i++) {
            queue.enqueue(new String("Item" + i));
        }
        for(int i = 0; i< numberOfItems; i++) {
            assertEquals("Item" + i, queue.dequeue());
        }
    }
}
