package project.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Andrew Bell
 * @version 1.0
 */
public class Queue<Item> implements Iterable<Item> {
	private int N; // Number of elements on queue
	private Node first; // Beginning of the queue
	private Node last; // End of the queue

	// Helper linked list class
	private class Node {
		private Item item;
		private Node next;
	}

	/**
	 * Create an empty queue.
	 */
	public Queue() {
		first = null;
		last = null;
		N = 0;
		assert check();
	}

	/**
	 * Is the queue empty?
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Return the number of items in the queue.
	 * 
	 * @param size
	 */
	public int size() {
		return N;
	}

	/**
	 * Return the item least recently added to the queue.
	 * 
	 * @throws java.util.NoSuchElementException
	 *             if queue is empty.
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue Underflow");
		return first.item;
	}

	/**
	 * Add the item to the queue.
	 */
	public void enqueue(Item item) {
		Node oldest = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldest.next = last;
		N++;
		assert check();
	}

	/**
	 * Remove and return the item on the queue least recently added.
	 * 
	 * @throws java.util.NoSuchElementException
	 *             if queue is empty.
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Queue Underflow");
		}
		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null;
		assert check();
		return item;
	}

	/**
	 * Return string representation.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this) {
			s.append(item + " ");
		}
		return s.toString();
	}

	// check internal invariants
	private boolean check() {
		if (N == 0) {
			if (first != null) {
				return false;
			}
			if (last != null) {
				return false;
			}
		} else if (N == 1) {
			if (first == null || last == null) {
				return false;
			}
			if (first != last) {
				return false;
			}
			if (first.next != null) {
				return false;
			}
		} else {
			if (first == last) {
				return false;
			}
			if (first.next == null) {
				return false;
			}
			if (last.next != null) {
				return false;
			}

			// check internal consistency of instance variable N
			int numberOfNodes = 0;
			for (Node x = first; x != null; x = x.next) {
				numberOfNodes++;
			}
			if (numberOfNodes != N) {
				return false;
			}

			// check internal consistency of instance variable last
			Node lastNode = first;
			while (lastNode.next != null) {
				lastNode = lastNode.next;
			}
			if (last != lastNode) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return an iterator that iterates over the items on the queue in FIFO
	 * order.
	 */
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	// An iterator, doesn't implement remove() since it's optional
	private class QueueIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}