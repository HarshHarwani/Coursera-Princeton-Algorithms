
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            rq.enqueue(input);
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }

}
