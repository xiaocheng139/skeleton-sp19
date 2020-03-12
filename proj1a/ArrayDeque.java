public class ArrayDeque <T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque()
    {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 7;
    }

    public void resize(int capacity)
    {
        T[] result = (T[]) new Object[capacity];
        System.arraycopy(items, 0, result, 0, size);
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item)
    {
        items[nextFirst] = item;
        size ++;
        nextFirst = minusOne(nextFirst);
    }

    /* Adds an item of type T to the end of the deque */
    public void addLast(T item)
    {
        items[nextLast] = item;
        size ++;
        nextLast = plusOne(nextLast);
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst()
    {
        int preFirst = plusOne(nextFirst);
        size--;
        return items[preFirst];
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast()
    {
        int preLast= minusOne(nextLast);
        size--;
        return items[preLast];
    }

    /* compute the index immediately “before” a given index */
    public int minusOne(int index)
    {
        if (index == 0)
        {
            return items.length - 1;
        }
        else
        {
            return index - 1;
        }
    }

    /* compute the index immediately “after” a given index */
    public int plusOne(int index)
    {
        if (index == items.length - 1)
        {
            return 0;
        }
        else
        {
            return index + 1;
        }
    }

    /* Calculate the usage proportion of memory boxes */
    public double getUsageRate()
    {
        return (float) size / items.length;
    }

    public int calculateRFactor()
    {
        double usageRate = getUsageRate();

    }
}
