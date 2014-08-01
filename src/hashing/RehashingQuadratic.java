package hashing;

import node.DeletedNode;
import node.Node;
import util.HashPrinter;
import util.PrimeNumbers;

/** Rehashing */

public class RehashingQuadratic {

	private final int TABLE_INITIAL_SIZE = 11;
	private float threshold = 0.5f;
	private int maxSize = (int) (TABLE_INITIAL_SIZE * threshold);
	private int size = 0;

	private Node[] table;

	public RehashingQuadratic() {

		table = new Node[TABLE_INITIAL_SIZE];

		for (int i = 0; i < TABLE_INITIAL_SIZE; i++) {

			table[i] = null;

		}

	}

	void setThreshold(float threshold) {
		this.threshold = threshold;
		maxSize = (int) (table.length * threshold);
	}

	public void put(int key, int value) {

		HashPrinter.tryPut(key, value);

		/** Index that moves through array slots */
		int runner = 0;

		int hash = (key % table.length);

		if (table[hash] == null) {

			table[hash] = new Node(key, value);

			HashPrinter.successfulInsertion(key, hash);

			size++;

		} else {
			/** Collision */
			Node entry = table[hash];

			while (entry != null && runner < table.length) {

				HashPrinter.collisionMessage(hash);

				runner++;
				hash = ((key + runner * runner) % table.length);
				entry = table[hash];

				HashPrinter.movingNextSlotByQuadratic(hash);

			}

			if (runner == table.length) {

				HashPrinter.arrayFull(key, value);

			} else {

				HashPrinter.successfulInsertion(key, hash);

				table[hash] = new Node(key, value);

				size++;

			}
		}

		if (size >= maxSize) {
			resize();
		}
	}

	/** Get item */
	public void get(int key) {
		HashPrinter.tryGet(key);

		/** Run along the array */
		int runner = 0;

		int hash = (key % table.length);

		while (table[hash] != null && runner < table.length) {

			if (table[hash].getKey() == key) {

				break;

			}

			runner++;

			hash = ((key + runner * runner) % table.length);

		}

		if (runner == table.length || table[hash].getKey() != key) {

			HashPrinter.notFound(key);

		} else if (table[hash].getKey() == key) {

			HashPrinter.found(table, key, hash);

		}
	}

	/** Remove item */
	public void remove(int key) {
		HashPrinter.tryRemove(key);

		/** Run along the array */
		int runner = 0;

		int hash = (key % table.length);

		while (table[hash] != null && runner < table.length) {

			if (table[hash].getKey() == key) {

				break;

			}

			runner++;

			hash = ((key + runner * runner) % table.length);

		}

		if (runner == table.length || table[hash].getKey() != key) {

			HashPrinter.notFound(key);

		} else if (table[hash].getKey() == key) {

			size--;

			table[hash] = DeletedNode.getUniqueDeletedNode();

			HashPrinter.remotionSuccessful(key, hash);

		}
	}

	/** Resize table and rearrange the elements from the old to the new */
	private void resize() {
		System.out.println("\n*** Resizing table. ***");

		int tableSize = PrimeNumbers.findClosestPrimeNumber(table.length * 2);
		maxSize = (int) (tableSize * threshold);
		Node[] oldTable = table;

		HashPrinter.printOldTable(table);

		table = new Node[tableSize];
		size = 0;

		for (int i = 0; i < oldTable.length; i++) {

			if (oldTable[i] != null && !oldTable[i].equals(DeletedNode.getUniqueDeletedNode())) {

				put(oldTable[i].getKey(), oldTable[i].getValue());

			}

		}

		HashPrinter.printOldTableRearranged(table);

	}

	private int countKeys() {
		int count = 0;

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].equals(DeletedNode.getUniqueDeletedNode())) {
				count++;
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
