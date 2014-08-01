package hashing;

import node.Node;
import util.HashPrinter;

/** Open Hashing = Closed Addressing = Separate Chaining */

public class OpenHashing {
	private final static int TABLE_INITIAL_SIZE = 7;

	private Node[] table;

	public OpenHashing() {

		table = new Node[TABLE_INITIAL_SIZE];

		for (int i = 0; i < TABLE_INITIAL_SIZE; i++) {

			table[i] = null;

		}

	}

	/** Hash function */
	public void put(int key, int value) {

		HashPrinter.tryPut(key, value);

		int level = 0;
		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] == null) {

			table[hash] = new Node(key, value);

			HashPrinter.successfulInsertion(key, hash, level);

		} else {
			/** Collision */

			level++;
			Node entry = table[hash];

			while (entry.getNext() != null) {

				entry = entry.getNext();

				level++;

			}

			HashPrinter.colisionMessageIfNeeded(hash, level);

			HashPrinter.successfulInsertion(key, hash, level);

			entry.setNext(new Node(key, value));

		}
	}

	/**
	 * Hash function inserting in the head of linked list. Thus, the insertion
	 * is always O(1)
	 */
	public void putInTheHead(int key, int value) {

		HashPrinter.tryPut(key, value);

		int level = 0;
		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] == null) {

			table[hash] = new Node(key, value);

			HashPrinter.successfulInsertion(key, hash, level);

		} else {
			/** Collision */

			Node entry = table[hash];

			Node head = new Node(key, value);

			head.setNext(entry);

			table[hash] = head;

			HashPrinter.headColisionMessageIfNeeded(hash);

			HashPrinter.successfulInsertion(key, hash, level);

		}
	}

	/** Get item */
	public void get(int key) {

		int level = 0;

		HashPrinter.tryGet(key);

		int hash = (key % TABLE_INITIAL_SIZE);

		boolean found = false;

		if (table[hash] != null) {

			Node entry = table[hash];

			while (entry != null) {

				if (entry.getKey() == key) {

					found = true;

					break;

				}

				if (entry != null) {

					entry = entry.getNext();

				}

				level++;
			}

			if (found) {

				HashPrinter.found(level, entry, key, hash);

			} else {

				HashPrinter.notFound(key);

			}

		}

	}

	/** Remove item */
	public void remove(int key) {

		int level = 0;

		HashPrinter.tryRemove(key);

		int hash = (key % TABLE_INITIAL_SIZE);

		boolean found = false;

		if (table[hash] != null) {

			Node entry = table[hash];

			Node previousNode = null;

			do {

				previousNode = entry;

				if (entry != null && entry.getNext() != null) {

					entry = entry.getNext();

				}

				if (entry.getKey() == key) {

					found = true;

					level++;

					break;

				}

				level++;

			} while (entry != null && previousNode != entry);

			if (previousNode == entry) {

				level = 0;

				table[hash] = table[hash].getNext();

				HashPrinter.remotionSuccessful(key, hash, level);

			} else if (found) {

				previousNode.setNext(entry.getNext());

				HashPrinter.remotionSuccessful(key, hash, level);

			} else {

				HashPrinter.notFound(key);

			}

		}

	}

	private int countKeys() {
		int count = 0;

		for (int i = 0; i < table.length; i++) {
			Node current = table[i];
			while (current != null) {
				count++;
				current = current.getNext();
			}
		}

		return count;
	}

	public double loadFactor() {
		return ((double) ((double) countKeys() / (double) table.length));
	}

	public Node[] getTable() {
		return table;
	}
}
