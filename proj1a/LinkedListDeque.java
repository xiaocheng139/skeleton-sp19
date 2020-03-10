public class LinkedListDeque <T> {
    private IntNode sentinel;
    private int size;

    private class IntNode
    {
        T val;
        IntNode next;
        IntNode pre;

        public  IntNode(T newVal, IntNode newNext, IntNode newPre)
        {
            val = newVal;
            next = newNext;
            pre = newPre;
        }

        public IntNode()
        {
            val = null;
            next = null;
            pre = null;
        }
    }

    /* Creates an empty linked list deque */
    public LinkedListDeque()
    {
        sentinel = new IntNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Create a deep copy of other */
    public LinkedListDeque(LinkedListDeque other)
    {

    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item)
    {
        IntNode first = sentinel.next;
        IntNode insertNode = new IntNode(item, first, sentinel);
        sentinel.next = insertNode;
        first.pre = insertNode;
        size += 1;
    }

    /* Adds an item of type T to the end of the deque */
    public void addLast(T item)
    {
        IntNode end = sentinel.pre;
        IntNode insertNode = new IntNode(item, sentinel, end);
        sentinel.pre = insertNode;
        end.next = insertNode;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty()
    {
        return sentinel.next == sentinel && sentinel.pre == sentinel;
    }

    /* Returns the number of items in the deque */
    public int size()
    {
        return size;
    }

    /* Prints the items in the deque from first to last */
    public void printDeque()
    {
        IntNode currentNode = sentinel.next;
        while (currentNode != sentinel)
        {
            System.out.print(currentNode.val);
            System.out.print(" ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    /* Removes and returns the item at the front of the deque */
    public T removeFirst()
    {
        IntNode firstNode = sentinel.next;
        if (firstNode != sentinel)
        {
            sentinel.next = firstNode.next;
            firstNode.next.pre = sentinel;
            return firstNode.val;
        }
        else
        {
            return null;
        }
    }

    /* Removes and returns the item at the back of the deque */
    public T removeLast()
    {
        IntNode endNode = sentinel.pre;
        if (endNode != sentinel)
        {
            sentinel.pre = endNode.pre;
            endNode.pre.next = sentinel;
            return endNode.val;
        }
        else
        {
            return null;
        }
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null */
    public T get(int index)
    {
        if ((sentinel.next == sentinel && sentinel.pre == sentinel) || size < index + 1)
        {
            return null;
        }

    }

    /* Get item using recursive method */
    public T getRecursive(int index)
    {

    }
}
