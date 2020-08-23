import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        q = (Item[]) new Object[1];
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = q[i];
        q = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return n;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == q.length)
            resize(2 * q.length);
        q[n++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException();

        int i = StdRandom.uniform(n);
        Item item = q[i];
        q[i] = q[n-1];
        q[--n] = null;
        if (n > 0 && n == q.length/4)
            resize(q.length/2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        return q[StdRandom.uniform(n)];
    }

    private class RandQueueIterator implements Iterator<Item>
    {
        private int i = n;
        private final int[] seq;

        public RandQueueIterator()
        {
            seq = new int[n];
            for (int j = 0; j < n; j++)
            {
                seq[j] = j;
            }
            StdRandom.shuffle(seq);
        }
        

        public boolean hasNext()
        {
            return i > 0;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return q[seq[--i]];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(1);
        test.enqueue(2);
        System.out.println(test.size());
        Iterator<Integer> it = test.iterator();
        if (it.hasNext())
            System.out.println(it.next());
        Iterator<Integer> itt = test.iterator();
        if (itt.hasNext())
            System.out.println(itt.next());
        System.out.println(test.dequeue());
        System.out.println(test.sample());
        System.out.println(test.isEmpty());
    }

}