import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {

    private Deque<String> deque;

    @Before
    public void init() {
        deque = new Deque<String>();
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        // Then
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void shouldInsertItemWhenAddLastIsInvoked() {
        // When
        deque.addLast("Item");
        // Then
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
    }

    @Test
    public void shouldInsertItemWhenAddFirstIsInvoked() {
        // When
        deque.addFirst("Item");
        // Then
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
    }

    @Test
    public void shouldBecomeEmptyWhenRemoveLastIsInvokedAfterAddLast() {
        // Given
        String item = "Item";
        deque.addLast(item);
        // When
        String actualItem = deque.removeLast();
        // Then
        assertSame(item, actualItem);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void shouldBecomeEmptyWhenRemoveFirstIsInvokedAfterAddLast() {
        // Given
        String item = "Item";
        deque.addLast(item);
        // When
        String actualItem = deque.removeFirst();
        // Then
        assertSame(item, actualItem);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void shouldBecomeEmptyWhenRemoveFirstIsInvokedAfterAddFirst() {
        // Given
        String item = "Item";
        deque.addFirst(item);
        // When
        String actualItem = deque.removeFirst();
        // Then
        assertSame(item, actualItem);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void shouldBecomeEmptyWhenRemoveLastIsInvokedAfterAddFirst() {
        // Given
        String item = "Item";
        deque.addFirst(item);
        // When
        String actualItem = deque.removeLast();
        // Then
        assertSame(item, actualItem);
        assertTrue(deque.isEmpty());
    }

    @Test
    public void shouldReturnLeastRecentlyAddedObjectWhenRemoveFirstIsInvoked() {
        // Given
        String item1 = "Item1";
        String item2 = "Item2";

        deque.addLast(item1);
        deque.addLast(item2);

        assertFalse(deque.isEmpty());
        assertEquals(2, deque.size());

        // When
        String dequeuedItem1 = deque.removeFirst();
        // Then
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertSame(item1, dequeuedItem1);

        // When
        String dequeuedItem2 = deque.removeFirst();
        // Then
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertSame(item2, dequeuedItem2);
    }

    @Test
    public void shouldReturnMostRecentlyAddedObjectWhenRemoveLastIsInvoked() {
        // Given
        String item1 = "Item1";
        String item2 = "Item2";

        deque.addLast(item1);
        deque.addLast(item2);

        assertFalse(deque.isEmpty());
        assertEquals(2, deque.size());

        // When
        String dequeuedItem1 = deque.removeLast();
        // Then
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.size());
        assertSame(item2, dequeuedItem1);

        // When
        String dequeuedItem2 = deque.removeLast();
        // Then
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
        assertSame(item1, dequeuedItem2);
    }

    @Test
    public void shouldBeIterableAndReturnValuesInCorrectOrder() {
        // Given
        String item1 = "Item1";
        String item2 = "Item2";
        String item3 = "Item3";

        deque.addLast(item1);
        deque.addLast(item2);
        deque.addFirst(item3);

        assertFalse(deque.isEmpty());
        assertEquals(3, deque.size());

        // When
        int i = 0;
        String[] items = new String[3];
        for (String item : deque) {
            items[i++] = item;
        }

        // Then
        assertSame(item3, items[0]);
        assertSame(item1, items[1]);
        assertSame(item2, items[2]);
        assertFalse(deque.isEmpty());
        assertEquals(3, deque.size());
    }

    @Test
    public void shouldBeIndependentWhenUseIteratorMultipleTimes() {
        // Given
        String item1 = "Item1";
        String item2 = "Item2";
        String item3 = "Item3";

        deque.addLast(item1);
        deque.addLast(item2);
        deque.addLast(item3);

        assertFalse(deque.isEmpty());
        assertEquals(3, deque.size());

        // When
        int i = 0;
        String[] items = new String[9];
        for (String i1 : deque) {
            for (String i2 : deque) {
                items[i++] = i1 + i2;
            }
        }

        assertEquals("Item1Item1", items[0]);
        assertEquals("Item1Item2", items[1]);
        assertEquals("Item1Item3", items[2]);
        assertEquals("Item2Item1", items[3]);
        assertEquals("Item2Item2", items[4]);
        assertEquals("Item2Item3", items[5]);
        assertEquals("Item3Item1", items[6]);
        assertEquals("Item3Item2", items[7]);
        assertEquals("Item3Item3", items[8]);

        assertFalse(deque.isEmpty());
        assertEquals(3, deque.size());

    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfRemoveFirstFromEmptyDeque() {
        // When
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfRemoveLastFromEmptyDeque() {
        // When
        deque.removeLast();
    }

    @Test
    public void shouldAddAndRemoveManyItemsWithoutExceptions() {
        // When
        for (int i = 99; i >= 0; i--) {
            deque.addFirst("Item" + i);
        }
        for (int i = 100; i < 200; i++) {
            deque.addLast("Item" + i);
        }
        for (int i = 0; i < 200; i++) {
            assertEquals("Item" + i, deque.removeFirst());
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenRemoveOnIteratorIsInvoked() {
        // Given
        deque.addLast("item");
        Iterator<String> iterator = deque.iterator();
        // When
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNoNextElement() {
        // Given
        Iterator<String> iterator = deque.iterator();
        // When
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNoNextElementMultipleInvokation() {
        // Given
        String item = "item";
        deque.addLast(item);
        Iterator<String> iterator = deque.iterator();

        // When
        String actualItem = iterator.next();
        assertSame(item, actualItem);

        iterator.next();
    }

}
