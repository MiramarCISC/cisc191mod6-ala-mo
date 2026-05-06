package edu.sdccd.cisc191;

import java.util.LinkedList;

public class GenericMatchQueue<T> {

    private final LinkedList<T> items = new LinkedList<>();

    public void enqueue(T item) {
        // adding the item to the back of the queue

        items.addLast(item);
    }

    public T dequeue() {
        // removing and returning the front item of the queue
        if (items.isEmpty()) {
            throw new IllegalStateException("Error caught in dequeue() method.");
        }

        T removedItem = items.removeFirst();

        return removedItem;
    }

    public T peek() {
        // returning the front item without removing it
        if (items.isEmpty()) {
            throw new IllegalStateException("Error caught in peek() method.");
        }

        return items.peek();
    }

    public boolean isEmpty() {
        // returns true when the queue has no items
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}
