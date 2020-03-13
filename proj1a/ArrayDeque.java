public class ArrayDeque <T> {

    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private static final int MINIMUMLENGTH = 8;
    private static final double RFACTOR = 0.25;

    public ArrayDeque()
    {
        items = (T[]) new Object[MINIMUMLENGTH];
        size = 0;
        nextFirst = MINIMUMLENGTH - 1;
        nextLast = 0;
    }

    public ArrayDeque(ArrayDeque other)
    {
        size = other.size();
        items = (T[]) new Object[size];

        /* Determine range */
        nextFirst = other.getNextFirst();
        nextLast = other.getNextLast();
        int index = plusOne(nextFirst);

        /* Do the copy */
        while (index != nextLast)
        {
            items[index] = (T) other.get(index);
            index = plusOne(index);
        }
    }

    public void resize()
    {
        /* Extent the array doubly */
        if (size > items.length)
        {
            T[] result = (T[]) new Object[items.length * 2];
            System.arraycopy(items, 0, result, 0, size);
            items = result;
        }
        /* Shrink the array, set the length to (capacity / RFACTOR) */
        else if (items.length >= 16 && size < items.length * RFACTOR)
        {
            int newLength = (int) (size / RFACTOR);
            if (newLength < MINIMUMLENGTH)
            {
                newLength = MINIMUMLENGTH;
            }
            T[] result = (T[]) new Object[newLength];
            System.arraycopy(items, 0, result, 0, size);
            items = result;
        }
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item)
    {
        size ++;
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }

    /* Adds an item of type T to the end of the deque */
    public void addLast(T item)
    {
        size ++;
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst()
    {
        size--;
        resize();
        int firstIndex = plusOne(nextFirst);
        nextFirst = plusOne(nextFirst);

        // Save the deleted item and return
        T item = items[firstIndex];

        // Remove the item from array
        items[firstIndex] = null;
        return item;
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast()
    {
        size--;
        resize();
        int lastIndex= minusOne(nextLast);
        nextLast = minusOne(nextLast);

        // Save the deleted item and return
        T item = items[lastIndex];

        // Remove the item from array
        items[lastIndex] = null;
        return item;
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

    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /* Returns the number of items in the deque */
    public int size()
    {
        return size;
    }

    public int getNextLast()
    {
        return nextLast;
    }

    public int getNextFirst()
    {
        return nextFirst;
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null */
    public T get(int index)
    {
        if (index > size - 1)
        {
            return null;
        }
        return items[index];
    }

    /* Prints the items in the deque from first to last */
    public void printDeque()
    {
        int i = plusOne(nextFirst);

        if (items.length == size)
        {
            System.out.print(items[i]);
            System.out.print(" ");
            i = plusOne(i);
        }

        while (i != nextLast)
        {
            System.out.print(items[i]);
            System.out.print(" ");
            i = plusOne(i);
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> dequeue = new ArrayDeque<>();
        dequeue.addFirst(1);
        dequeue.addLast(2);
        System.out.println(dequeue.removeLast());
        System.out.println(dequeue.removeLast());
        dequeue.addFirst(1);
        dequeue.addLast(2);
        dequeue.printDeque();
    }
}
