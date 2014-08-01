package hashing;

import node.AVLNode;
import node.TreeNode;
import util.HashPrinter;

/** Open Hashing with AVL */

public class OpenAVLHashing {

	private final static int TABLE_INITIAL_SIZE = 7;

	private AVLNode[] table;

	public OpenAVLHashing() {

		table = new AVLNode[TABLE_INITIAL_SIZE];

		for (int i = 0; i < TABLE_INITIAL_SIZE; i++) {

			table[i] = null;

		}

	}

	/** Hash function */
	public void put(int key, int value) {

		HashPrinter.tryPut(key, value);

		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] == null) {

			table[hash] = new AVLNode(key, value);

			HashPrinter.successfulInsertion(table, key, hash);

		} else {
			/** Collision */

			AVLNode entry = table[hash];

			entry.getAVLTree().insert(key, value);

			HashPrinter.successfulInsertionfulWithColision(table, key, hash);

		}

	}

	/** Get item */
	public void get(int key) {

		HashPrinter.tryGet(key);

		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] != null) {

			AVLNode entry = table[hash];

			TreeNode find = entry.getAVLTree().find(key);

			if (find != null) {

				HashPrinter.found(find, entry, key, hash);

			} else {

				HashPrinter.notFound(key);

			}

		} else {

			HashPrinter.notFound(key);

		}

	}

	/** Remove item */
	public void remove(int key) {

		HashPrinter.tryRemove(key);

		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] != null) {

			AVLNode entry = table[hash];

			entry.getAVLTree().remove(key, hash);

		} else {

			HashPrinter.notFound(key);

		}

	}

	/**
	 * The Hash Table Load Factor in Half Open Hashing is the max height among
	 * the trees of hash table nodes
	 */
	public int loadFactor() {

		int maxHeight = 0;

		for (int i = 0; i < table.length; i++) {

			int height = 0;

			if (table[i] != null) {

				height = table[i].getAVLTree().height() + 1;

			}

			if (height > maxHeight) {

				maxHeight = height;

			}

		}

		return maxHeight;
	}

	/**
	 * The Hash Table Balancing Factor in Half Open Hashing is the sum of the
	 * heights of the trees of hash table nodes divided by number of the size
	 * times max height(load factor). (h1+h2+...+hn)/(TABLE_INITIAL_SIZE * H)
	 */
	public double balancingFactor() {
		double maxHeight = 0;
		double heightsSum = 0;

		for (int i = 0; i < table.length; i++) {
			double height = 0;

			if (table[i] != null)
				height = table[i].getAVLTree().height() + 1;

			heightsSum += height;

			if (height > maxHeight)
				maxHeight = height;
		}

		return (heightsSum / ((double) TABLE_INITIAL_SIZE * maxHeight));
	}

	public void printBalancingFactor() {
		System.out.println("\nBalancing Factor: " + balancingFactor());
	}

	public AVLNode[] getTable() {
		return table;
	}

}
