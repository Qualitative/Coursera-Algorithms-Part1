import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int tail;
    private Item[] items;
    private int emptySells;
    private int size;
    private boolean isCompressed;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        checkItem(item);

        if ((tail == items.length) && (size() > tail / 2)) {
            resize(items.length * 2);
        }

        if ((tail == items.length) && (size() == 0)) {
            tail--;
        }

        size++;
        items[tail++] = item;
        StdRandom.setSeed(System.nanoTime());
    }

    public Item dequeue() {
        checkNotEmpty();

        int index;
        Item item;

        do {
            index = StdRandom.uniform(tail);
            item = items[index];
        } while (item == null);

        items[index] = null;

        size--;
        emptySells++;

        if ((size() > 0) && (size() <= emptySells)) {
            compressArray();
        }
        return item;
    }

    public Item sample() {
        checkNotEmpty();

        if ((size() > 0) && (size() <= emptySells) && !isCompressed) {
            compressArray();
        }

        int index;
        Item item;

        do {
            index = StdRandom.uniform(tail);
            item = items[index];
        } while (item == null);

        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Item should not be null");
        }
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        for (int i = 0; i < tail; i++) {
            copy[i] = items[i];
        }
        items = copy;
        isCompressed = false;
    }

    private void compressArray() {
        int newSize = size;
        Item[] copy = (Item[]) new Object[newSize];
        int j = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                copy[j++] = items[i];
            }
        }
        emptySells = 0;
        items = copy;
        tail = size;
        isCompressed = true;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int current = 0;
        private Item[] copyOfItems;

        public RandomizedQueueIterator() {
            current = 0;
            if (!isCompressed) {
                compressArray();
            }
            copyOfItems = (Item[]) new Object[items.length];
            for (int i = 0; i < items.length; i++) {
                copyOfItems[i] = items[i];
            }
            StdRandom.setSeed(System.nanoTime());
            StdRandom.shuffle(copyOfItems);
        }

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }
            return copyOfItems[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is unsupported operation");
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        // Given
        int numberOfItems = 100;

        // When
        for (int i = 0; i < numberOfItems; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < numberOfItems; i++) {
            queue.dequeue();
        }
    }

}
