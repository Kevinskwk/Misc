import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int n;

    private class Node
    {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque()
    {
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return n == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (isEmpty())
            last = first;
        else
        {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
            last.prev = oldLast;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null;
        else
            first.prev = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException();

        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return item;
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(1);
        test.addLast(2);
        System.out.println(test.size());
        Iterator<Integer> it = test.iterator();
        if (it.hasNext())
            System.out.println(it.next());
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        System.out.println(test.isEmpty());
    }
}