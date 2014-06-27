package com.ralko.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStackTest {

    private Stack<String> stack;

    @Before
    public void init() {
        stack = createStack();
    }

    public abstract Stack<String> createStack();

    @Test
    public void shouldBeEmptyWhenInitialized() {
        // Then
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
    }

    @Test
    public void shouldInsertItemWhenPushIsInvoked() {
        // When
        stack.push("Item");
        // Then
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.getSize());
    }

    @Test
    public void shouldReturnMostRecentlyAddedObjectWhenPopIsInvoked() {
        // Given
        String item1 = new String("Item1");
        String item2 = new String("Item2");

        stack.push(item1);
        stack.push(item2);

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.getSize());

        // When
        String poppedItem1 = stack.pop();
        // Then
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.getSize());
        assertSame(item2, poppedItem1);

        // When
        String poppedItem2 = stack.pop();
        // Then
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.getSize());
        assertSame(item1, poppedItem2);
    }

    @Test
    public void shouldBeIterableAndReturnValuesInReverseOrderTheyWereAdded() {
        // Given
        String item1 = new String("Item1");
        String item2 = new String("Item2");

        stack.push(item1);
        stack.push(item2);

        assertFalse(stack.isEmpty());
        assertEquals(2, stack.getSize());

        // When
        int i = 0;
        String[] items = new String[2];
        for (String item : stack) {
            items[i++] = item;
        }

        // Then
        assertSame(item2, items[0]);
        assertSame(item1, items[1]);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.getSize());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenPopFromEmptyStack() {
        // When
        stack.pop();
    }
    
    @Test
    public void shouldPushAndPopManyItemsWithoutExceptions() {
        // Given
        int numberOfItems = 10000;
        
        // When
        for(int i = 0; i< numberOfItems; i++) {
            stack.push(new String("Item" + i));
        }
        for(int i = 0; i< numberOfItems; i++) {
            stack.pop();
        }
    }
}
