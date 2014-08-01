package hashing;

import node.DeletedNode;
import node.Node;
import util.HashPrinter;

/** Closed Hashing = Open Addressing */

public class ClosedHashingQuadratic {

	private final static int TABLE_INITIAL_SIZE = 7;

	private Node[] table;

	public ClosedHashingQuadratic() {

		table = new Node[TABLE_INITIAL_SIZE];

		for (int i = 0; i < TABLE_INITIAL_SIZE; i++) {

			table[i] = null;

		}

	}

	/** Hash function */
	public void put(int key, int value) {

		HashPrinter.tryPut(key, value);

		/** Index that moves through array slots */
		int runner = 0;

		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] == null) {

			table[hash] = new Node(key, value);

			HashPrinter.successfulInsertion(key, hash);

		} else {
			/** Collision */
			Node entry = table[hash];

			/**
			 * Quadratic probing only grants an element insertion while runner
			 * is under the half of the table size.
			 */
			int edge = (int) Math.ceil((double) TABLE_INITIAL_SIZE / 2);

			while (entry != null && runner < edge) {

				HashPrinter.collisionMessage(hash);

				runner++;
				hash = ((key + runner * runner) % TABLE_INITIAL_SIZE);
				entry = table[hash];

				HashPrinter.movingNextSlotByQuadratic(hash);

			}

			if (runner == edge) {

				HashPrinter.arrayFullQuadratic(key, value);

			} else {

				HashPrinter.successfulInsertion(key, hash);

				table[hash] = new Node(key, value);

			}
		}
	}

	/** Get item */
	public void get(int key) {
		HashPrinter.tryGet(key);

		/** Run along the array */
		int runner = 0;

		int hash = (key % TABLE_INITIAL_SIZE);
		int edge = (int) Math.ceil((double) TABLE_INITIAL_SIZE / 2);

		while (table[hash] != null && runner < edge) {

			if (table[hash].getKey() == key) {
				break;
			}

			runner++;

			hash = ((key + runner * runner) % TABLE_INITIAL_SIZE);

		}

		if (runner >= edge) {

			HashPrinter.notFound(key);

		} else {

			HashPrinter.found(table, key, hash);

		}

	}

	/** Remove item */
	public void remove(int key) {
		HashPrinter.tryRemove(key);

		/** Run along the array */
		int runner = 0;

		int hash = (key % TABLE_INITIAL_SIZE);
		int edge = (int) Math.ceil((double) TABLE_INITIAL_SIZE / 2);

		while (table[hash] != null && runner < edge) {

			if (table[hash].getKey() == key) {
				break;
			}

			runner++;

			hash = ((key + runner * runner) % TABLE_INITIAL_SIZE);

		}

		if (runner >= edge) {

			HashPrinter.notFound(key);

		} else {

			table[hash] = DeletedNode.getUniqueDeletedNode();

			HashPrinter.remotionSuccessful(key, hash);

		}
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
