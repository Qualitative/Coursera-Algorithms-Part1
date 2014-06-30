import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StdRandom.class)
public class RandomizedQueueTest {

    @Captor
    private ArgumentCaptor<String[]> arrayCaptor;

    private RandomizedQueue<String> queue;

    @Before
    public void init() {
        PowerMockito.spy(StdRandom.class);
        MockitoAnnotations.initMocks(getClass());
        queue = new RandomizedQueue<String>();
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        // Then
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void shouldInsertItemWhenEnqueueIsInvoked() {
        // When
        queue.enqueue("Item");
        // Then
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    public void shouldBeIterableAndReturnValuesInRandomOrder() {
        // Given
        String item1 = "Item1";
        String item2 = "Item2";

        queue.enqueue(item1);
        queue.enqueue(item2);

        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());

        // When
        int i = 0;
        String[] items = new String[2];
        for (String item : queue) {
            items[i++] = item;
        }

        // Then
        List<String> itemList = Arrays.asList(items);
        assertTrue(itemList.contains(item1));
        assertTrue(itemList.contains(item2));
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
    }

    @Test
    public void shouldUpdateSeedWhenCreateIterator() {
        // When
        queue.iterator();
        queue.iterator();
        // Then
        PowerMockito.verifyStatic(Mockito.times(2));
        StdRandom.shuffle(arrayCaptor.capture());

        // Then
        List<String[]> arrays = arrayCaptor.getAllValues();
        assertEquals(2, arrays.size());
        assertNotEquals(arrays.get(0), arrays.get(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfDequeueFromEmptyQueue() {
        // When
        queue.dequeue();
    }

    @Test
    public void shouldEnqueueAndDequeueManyItemsWithoutExceptions() {
        // When
        for (int i = 0; i < 60; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 40; i++) {
            queue.dequeue();
        }
        for (int i = 60; i < 140; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 80; i++) {
            queue.dequeue();
        }
        for (int i = 140; i < 200; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 80; i++) {
            queue.dequeue();
        }
    }

    @Test
    public void shouldEnqueueAndDequeueManyItemsWithoutExceptionsSecondCase() {
        // When
        queue.enqueue("!");
        queue.dequeue();
        for (int i = 0; i < 60; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 60; i++) {
            queue.dequeue();
        }
        for (int i = 0; i < 140; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 140; i++) {
            queue.dequeue();
        }
        for (int i = 0; i < 320; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 320; i++) {
            queue.dequeue();
        }
        for (int i = 0; i < 500; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 500; i++) {
            queue.dequeue();
        }
        for (int i = 0; i < 1000; i++) {
            queue.enqueue("Item" + i);
        }
        for (int i = 0; i < 1000; i++) {
            queue.dequeue();
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenRemoveOnIteratorIsInvoked() {
        // Given
        queue.enqueue("item");
        Iterator<String> iterator = queue.iterator();
        // When
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNoNextElement() {
        // Given
        Iterator<String> iterator = queue.iterator();
        // When
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNoNextElementMultipleInvokation() {
        // Given
        String item = "item";
        queue.enqueue(item);
        Iterator<String> iterator = queue.iterator();

        // When
        String actualItem = iterator.next();
        assertSame(item, actualItem);

        iterator.next();
    }

}
