package hashing;

import java.util.ArrayList;

import node.AVLNodeForHalfOpenHashing;
import node.TreeNode;
import util.PrimeNumbers;
import util.HashPrinter;

/** Mixed of Open Hashing with AVL and Rehashing */
public class HalfOpenHashing {

	private final static int TABLE_INITIAL_SIZE = 13;

	/** In this case, the threshold is a max size of the tree */
	public static final int THRESHOLD = 2;

	private AVLNodeForHalfOpenHashing[] table;

	public HalfOpenHashing() {

		table = new AVLNodeForHalfOpenHashing[TABLE_INITIAL_SIZE];

		for (int i = 0; i < TABLE_INITIAL_SIZE; i++) {

			table[i] = null;

		}

	}

	/** Hash function */
	public void put(int key, int value) {

		HashPrinter.tryPut(key, value);

		/** Index that moves through array slots */
		int runner = 0;

		int hash = (key % table.length);

		if (table[hash] == null) {

			table[hash] = new AVLNodeForHalfOpenHashing(key, value);

			HashPrinter.successfulInsertion(table, key, hash);

			if (table[hash].getAVLTree().height() == THRESHOLD) {

				performResize(key);

			}

		} else {
			/** Collision */

			AVLNodeForHalfOpenHashing entry = table[hash];

			boolean success = entry.getAVLTree().insert(key, value);

			if (entry.getAVLTree().height() == THRESHOLD) {

				performResize(key);

			} else if (success) {

				HashPrinter.successfulInsertionfulWithColision(table, key, hash);

			} else {

				int edge = (int) Math.ceil((double) TABLE_INITIAL_SIZE / 2);

				while (entry != null && runner < edge && !success) {

					runner++;
					hash = ((key + runner * runner) % table.length);
					entry = table[hash];

					HashPrinter.movingNextSlotByQuadratic(hash);

					if (entry == null) {

						entry = table[hash] = new AVLNodeForHalfOpenHashing(key, value);

						success = true;

					} else {

						success = entry.getAVLTree().insert(key, value);

					}

				}

				if (success) {

					HashPrinter.successfulInsertion(table, key, hash);

				} else {

					HashPrinter.insertionFailed(key);

				}

				if (!success) {

					resize();
					put(key, value);

				}

			}

		}

	}

	private void performResize(int key) {

		HashPrinter.exceedsHeight(key);

		resize();

	}

	/** Get item */
	public void get(int key) {

		HashPrinter.tryGet(key);

		int hash = (key % table.length);

		if (table[hash] != null) {

			AVLNodeForHalfOpenHashing entry = table[hash];

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

		int hash = (key % table.length);

		if (table[hash] != null) {

			AVLNodeForHalfOpenHashing entry = table[hash];

			entry.getAVLTree().remove(key, hash);

		} else {

			HashPrinter.notFound(key);

		}

	}

	/** Resize table and rearrange the elements from the old to the new */
	public void resize() {

		HashPrinter.resizing();

		int tableSize = PrimeNumbers.findClosestPrimeNumber(table.length * 2);
		AVLNodeForHalfOpenHashing[] oldTable = table;

		HashPrinter.printOldTable(table);

		table = new AVLNodeForHalfOpenHashing[tableSize];

		for (int i = 0; i < oldTable.length; i++) {

			if (oldTable[i] != null) {

				ArrayList<TreeNode> treeNodes = oldTable[i].getAVLTree().inOrder();

				for (TreeNode treeNode : treeNodes) {

					put(treeNode.getKey(), treeNode.getValue());

				}

			}

		}

	}

	/**
	 * The Hash Table Load Factor in Half Open Hashing is the max height among
	 * the trees of hash table nodes
	 */
	public double loadFactor() {

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

			if (table[i] != null) {

				height = table[i].getAVLTree().height() + 1;

			}

			heightsSum += height;

			if (height > maxHeight) {

				maxHeight = height;

			}

		}

		return (heightsSum / ((double) table.length * maxHeight));
	}

	public AVLNodeForHalfOpenHashing[] getTable() {
		return table;
	}

}
