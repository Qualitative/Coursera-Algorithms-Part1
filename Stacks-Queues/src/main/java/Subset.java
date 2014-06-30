public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            queue.enqueue(StdIn.readString());
        }
        for (String item : queue) {
            StdOut.println(item);
        }
    }

}
