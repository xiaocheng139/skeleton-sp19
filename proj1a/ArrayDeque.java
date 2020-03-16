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
        int capacity = other.capacity();
        size = other.size();
        items = (T[]) new Object[capacity];

        /* Determine range */
        nextFirst = other.getNextFirst();
        nextLast = other.getNextLast();
        int start = plusOne(nextFirst);
        int end = minusOne(nextLast);
        int index = start;

        /* Do the copy */
        while (index != end)
        {
            items[index] = (T) other.get(index);
            index = plusOne(index);
        }
        items[index] = (T) other.get(index);
    }

    public void resize(int capacity)
    {
        /* Extent the array doubly */
        if (capacity > items.length)
        {
            T[] result = (T[]) new Object[items.length * 2];

            int start = plusOne(nextFirst);
            int end = minusOne(nextLast);
            if (start < end)
            {
                System.arraycopy(items, start, result, 0, size);
            }
            else
            {
                System.arraycopy(items, start, result, 0, size - start);
                System.arraycopy(items, 0, result, size - start, start);
            }

            nextFirst = items.length * 2 - 1;
            nextLast = size;
            items = result;
        }
        /* Shrink the array, set the length to (capacity / RFACTOR) */
        else if (items.length >= 16 && capacity < items.length * RFACTOR)
        {
            int newLength = (int) (capacity / RFACTOR);
            if (newLength < MINIMUMLENGTH)
            {
                newLength = MINIMUMLENGTH;
            }
            T[] result = (T[]) new Object[newLength];

            int start = plusOne(nextFirst);
            int end = minusOne(nextLast);
            if (end >= newLength)
            {
                System.arraycopy(items, start, result, 0, size);
            }
            else if (start >= newLength && end < newLength)
            {
                System.arraycopy(items, start, result, 0, size - start);
                System.arraycopy(items, 0, result, size - start, start);
            }
            nextFirst = newLength - 1;
            nextLast = size;
            items = result;
        }
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item)
    {
        int newSize = size + 1;
        resize(newSize);
        size = newSize;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }

    /* Adds an item of type T to the end of the deque */
    public void addLast(T item)
    {
        int newSize = size + 1;
        resize(newSize);
        size = newSize;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst()
    {
        int newSize = size - 1;
        resize(newSize);
        size = newSize;
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
        int newSize = size - 1;
        resize(newSize);
        size = newSize;
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

    /* Return the maximum number of items can be stored */
    public int capacity()
    {
        return items.length;
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

        if (index > items.length - 1)
        {
            return null;
        }
        return items[index];
    }

    /* Prints the items in the deque from first to last */
    public void printDeque()
    {
        int start = plusOne(nextFirst);
        int end = minusOne(nextLast);

        for (int i = start; i != end; i = plusOne(i))
        {
            System.out.print(items[i]);
            System.out.print(" ");
        }

        System.out.print(items[end]);
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> dequeue = new ArrayDeque<>();
        dequeue.addLast(1);
        dequeue.addFirst(2);
        dequeue.addFirst(3);
        dequeue.addLast(4);
        dequeue.addFirst(5);
        dequeue.addLast(6);
        dequeue.addFirst(7);
        dequeue.addLast(8);
        dequeue.addFirst(9);
        dequeue.addLast(10);
        dequeue.addFirst(11);
        dequeue.addLast(12);
        dequeue.addFirst(13);
        dequeue.addLast(14);
        dequeue.addFirst(15);
        dequeue.addLast(16);
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.addLast(1);
        dequeue.addLast(2);
        dequeue.addLast(3);
        dequeue.addLast(4);
        dequeue.addLast(5);
        dequeue.addLast(6);
        dequeue.addLast(7);
        dequeue.addLast(8);
        dequeue.addLast(9);
        dequeue.addLast(10);
        dequeue.addLast(11);
        dequeue.addLast(12);
        dequeue.addFirst(13);
        ArrayDeque<Integer> dequeue2 = new ArrayDeque<>(dequeue);
        dequeue2.printDeque();
    }
}
