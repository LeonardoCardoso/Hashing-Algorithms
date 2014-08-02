package hashing;

import tree.node.DeletedNode;
import tree.node.Node;
import util.HashPrinter;

/** Closed Hashing = Open Addressing */

public class ClosedHashingLinear {

	private final static int TABLE_INITIAL_SIZE = 8;

	private Node[] table;

	public ClosedHashingLinear() {

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

			while (entry != null && runner < TABLE_INITIAL_SIZE) {

				HashPrinter.collisionMessage(hash);

				runner++;
				hash = ((key + runner) % TABLE_INITIAL_SIZE);
				entry = table[hash];

				HashPrinter.movingNextSlotByLinear(hash);

			}

			if (runner == TABLE_INITIAL_SIZE) {

				HashPrinter.arrayFull(key, value);

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

		while (table[hash] != null && runner < TABLE_INITIAL_SIZE) {

			if (table[hash].getKey() == key) {
				break;
			}

			runner++;

			hash = ((key + runner) % TABLE_INITIAL_SIZE);

		}

		if (runner >= TABLE_INITIAL_SIZE) {

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

		while (table[hash] != null && runner < TABLE_INITIAL_SIZE) {

			if (table[hash].getKey() == key) {

				break;

			}

			runner++;

			hash = ((key + runner) % TABLE_INITIAL_SIZE);

		}

		if (runner >= TABLE_INITIAL_SIZE) {

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
