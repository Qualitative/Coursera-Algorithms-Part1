public class Subset {

    // Prevents instantiation
    private Subset() {
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        while (k > 0) {
            StdOut.println(queue.dequeue());
            k--;
        }
    }
}
