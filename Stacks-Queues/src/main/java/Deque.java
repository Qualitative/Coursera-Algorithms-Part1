import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node pre;
    private Node post;

    public Deque() {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkItem(item);

        Node first = pre.next;
        Node x = new Node();
        x.item = item;
        x.prev = pre;
        x.next = first;
        pre.next = x;
        first.prev = x;
        size++;
    }

    public void addLast(Item item) {
        checkItem(item);

        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        size++;
    }

    public Item removeFirst() {
        checkNotEmpty();

        Node first = pre.next;
        Item itemToReturn = first.item;

        Node next = first.next;
        pre.next = next;
        next.prev = pre;

        size--;

        return itemToReturn;
    }

    public Item removeLast() {
        checkNotEmpty();

        Node last = post.prev;
        Item itemToReturn = last.item;

        Node prev = last.prev;
        post.prev = prev;
        prev.next = post;

        size--;

        return itemToReturn;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
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

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = pre.next;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }

            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is unsupported");
        }
    }
}
