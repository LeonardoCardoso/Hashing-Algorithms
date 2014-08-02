package hashing;

import tree.node.DeletedNode;
import tree.node.Node;
import util.HashPrinter;
import util.PrimeNumbers;

/** Closed Hashing = Open Addressing */

public class ClosedHashingDouble {

	private final static int TABLE_INITIAL_SIZE = 7;

	private Node[] table;

	public ClosedHashingDouble() {

		table = new Node[TABLE_INITIAL_SIZE];

		for (int i = 0; i < TABLE_INITIAL_SIZE; i++) {

			table[i] = null;

		}

	}

	private int firstFunction(int key, int currentAnalysisTableSize) {
		int hash = 0;
		hash = key % currentAnalysisTableSize;
		return hash;
	}

	private int secondFunction(int times) {
		int currentAnalysisTableSize = TABLE_INITIAL_SIZE;
		currentAnalysisTableSize = getPrimeNumberLessThan(currentAnalysisTableSize, times);
		return currentAnalysisTableSize;
	}

	/**
	 * Return the first prime smaller than the current analysis size of the
	 * table, not the table full size
	 */
	private int getPrimeNumberLessThan(int currentAnalysisTableSize, int times) {

		for (int i = 0; i < times; i++) {

			currentAnalysisTableSize = PrimeNumbers.findPreviousPrimeNumber(currentAnalysisTableSize);
		}

		return currentAnalysisTableSize;
	}

	/** Hash function */
	public void put(int key, int value) {

		HashPrinter.tryPut(key, value);

		/** Max number to try to insert */
		int runner = 0;

		int hash = (key % TABLE_INITIAL_SIZE);

		if (table[hash] == null) {

			table[hash] = new Node(key, value);

			HashPrinter.successfulInsertion(key, hash);

		} else {
			/** Collision */
			Node entry = table[hash];

			int secondValue = TABLE_INITIAL_SIZE;

			while (entry != null && entry.getKey() != key && runner < TABLE_INITIAL_SIZE) {

				if (runner > 0) {

					HashPrinter.tableSizeAnalysis(secondValue);

				}

				HashPrinter.collisionMessage(hash);

				runner++;
				secondValue = secondFunction(runner);
				hash = firstFunction(key, secondValue);
				entry = table[hash];

				HashPrinter.movingNextSlotByDouble(hash);

				/** 2 = smaller prime number = stop criteria */
				if (secondValue == 2) {

					HashPrinter.collisionMessage(hash);

					break;
				}

			}

			/**
			 * This first 'if' is to avoid the override of the key value that
			 * already is in hash table
			 */
			if (entry != null && entry.getKey() == key) {

				HashPrinter.alreadyExists(key);

			} else if (runner == TABLE_INITIAL_SIZE || (entry != null && entry.getKey() != key && secondValue == 2)) {

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

		int secondValue = TABLE_INITIAL_SIZE;

		while (table[hash] == null || (table[hash] != null && (table[hash].getKey() != key)) && runner < TABLE_INITIAL_SIZE) {

			runner++;

			secondValue = secondFunction(runner);

			hash = firstFunction(key, secondValue);

			/** 2 = smaller prime number = stop criteria */
			if (secondValue == 2) {

				break;

			}

		}

		if (runner >= TABLE_INITIAL_SIZE || (table[hash] != null && table[hash].getKey() != key && secondValue == 2)) {

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
		int secondValue = TABLE_INITIAL_SIZE;

		while (table[hash] == null || (table[hash] != null && (table[hash].getKey() != key)) && runner < TABLE_INITIAL_SIZE) {

			runner++;

			secondValue = secondFunction(runner);

			hash = firstFunction(key, secondValue);

			/** 2 = smaller prime number = stop criteria */
			if (secondValue == 2) {

				break;

			}
		}

		if (runner >= TABLE_INITIAL_SIZE || (table[hash] != null && table[hash].getKey() != key && secondValue == 2)) {

			HashPrinter.notFound(key);

		} else {

			table[hash] = null;

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
