public class ArrayDeque <T> {

    private int size;
    private T[] items;

    public ArrayDeque()
    {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void resize(int capacity)
    {

    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item)
    {
        items[0] = item;
        size ++;
    }
}
