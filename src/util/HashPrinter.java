package util;

import util.graphics.Joins;
import util.graphics.SurfaceTreeForHashing;
import util.graphics.SurfaceTreeForHalfOpenHashing;
import hashing.HalfOpenHashing;

import javax.swing.SwingUtilities;

import tree.node.AVLNode;
import tree.node.AVLNodeForHalfOpenHashing;
import tree.node.DeletedNode;
import tree.node.Node;
import tree.node.TreeNode;

public class HashPrinter {

	public static void successfulInsertion(AVLNodeForHalfOpenHashing[] table, int key, int hash) {
		System.out.println("\nKey " + key + " put successfully in position " + hash + " at tree height "
				+ (table[hash].getAVLTree().height()) + ".");
	}

	public static void successfulInsertion(AVLNode[] table, int key, int hash) {
		System.out.println("\nKey " + key + " put successfully in position " + hash + " at tree height "
				+ (table[hash].getAVLTree().height()) + ".");
	}

	public static void successfulInsertion(int key, int hash) {
		System.out.println("\nKey " + key + " put successfully in position " + hash + ".");
	}

	public static void successfulInsertionfulWithColision(AVLNodeForHalfOpenHashing[] table, int key, int hash) {
		System.out.println("\nCollision! Position " + hash + " already occupied.\nKey " + key + " put successfully in position "
				+ hash + " at tree height " + (table[hash].getAVLTree().height()) + ".");
	}

	public static void successfulInsertionfulWithColision(AVLNode[] table, int key, int hash) {
		System.out.println("\nCollision! Position " + hash + " already occupied.\nKey " + key + " put successfully in position "
				+ hash + " at tree height " + (table[hash].getAVLTree().height()) + ".");
	}

	public static void arrayFull(int key, int value) {
		System.out.println("\nArray is probally full or the load factor is too high for Key " + key + " with Value " + value
				+ ". Ignoring...");
	}

	public static void arrayFullQuadratic(int key, int value) {
		System.out.println("\nArray is probally full or the load factor is too high for Value " + value
				+ " or array is more than half full,\nsince Quadratic Probing only grants an element insertion while increment "
				+ "* is under the half of the table size. Ignoring...");
	}

	public static void exceedsHeight(int key) {
		System.out.println("\nInserting Key " + key + " exceeds the max height " + HalfOpenHashing.THRESHOLD + ". Rehashing...");
	}

	public static void found(TreeNode find, AVLNodeForHalfOpenHashing entry, int key, int hash) {
		System.out.println("\nKey " + key + " with Value " + find.getValue() + " gotten successfully in position " + hash
				+ " at tree height " + entry.getAVLTree().currentSearchHeight + ".");
	}

	public static void found(TreeNode find, AVLNode entry, int key, int hash) {
		System.out.println("\nKey " + key + " with Value " + find.getValue() + " gotten successfully in position " + hash
				+ " at tree height " + entry.getAVLTree().currentSearchHeight + ".");
	}

	public static void printOldTable(AVLNodeForHalfOpenHashing[] table) {
		if (table.length > 0) {

			System.out.println("\n*** Table before resizing. ***");
			printAll(table);
			System.out.println("\n*** Rearranging elements from the old to the new. ***");

		}
	}

	public static void printOldTable(Node[] table) {
		if (table.length > 0) {

			System.out.println("\n*** Table before resizing. ***");
			printAll(table);
			System.out.println("\n*** Rearranging elements from the old to the new. ***");

		}
	}

	public static void printOldTableRearranged(Node[] table) {
		if (table.length > 0) {

			System.out.println("\n*** Elements rearranging done. ***");
			printAll(table);

		}
	}

	public static void remotionSuccessful(int searchingKey, int currentSearchHeight, TreeNode startingNode, int hash) {
		System.out.println("\nKey " + searchingKey + " with Value " + startingNode.value + " removed successfully in position "
				+ hash + " at tree height " + currentSearchHeight + ".");
	}

	public static void treeNode(TreeNode node, int l, int r, int p) {
		System.out.println("Left: " + l + " Key: " + node.key + " Right: " + r + " Parent: " + p + " Balance: " + node.balance);
	}

	public static void printAll(AVLNodeForHalfOpenHashing[] table) {
		System.out.println("\n>> Current Hash Table");
		for (int i = 0; i < table.length; i++) {
			System.out.print("Position " + i + ". \n");
			display(table[i]);
		}
	}

	public static void printAll(AVLNode[] table) {
		System.out.println("\n>> Current Hash Table");
		for (int i = 0; i < table.length; i++) {
			System.out.print("Position " + i + ". \n");
			display(table[i]);
		}
	}

	/** Print item and its children */
	private static void display(AVLNodeForHalfOpenHashing item) {
		if (item != null) {
			item.getAVLTree().printAll();
		} else {
			System.out.println("[ ]\n");
		}
	}

	private static void display(AVLNode item) {
		if (item != null) {
			item.getAVLTree().printAll();
		} else {
			System.out.println("[ ]\n");
		}
	}

	public static void tableSizeAnalysis(int secondValue) {
		System.out.print("\nTable size for analysis is now " + secondValue + ".");
	}

	public static void movingNextSlotByDouble(int hash) {
		System.out.print("Moving to the next slot " + hash + " using double hashing...");
	}

	public static void movingNextSlotByQuadratic(int hash) {
		System.out.print("Moving to the next slot " + hash + " with quadratic probing...");
	}

	public static void collisionMessage(int hash) {
		System.out.print("\nCollision! Position " + hash + " already occupied. ");
	}

	public static void movingNextSlotByLinear(int hash) {
		System.out.print("Moving to the next slot " + hash + " with linear probing...");
	}

	public static void insertionFailed(int key) {
		System.out.print("Failed to insert the Key " + key);
	}

	public static void tryPut(int key, int value) {
		System.out.print("\nAttempt to put Key " + key + " with Value " + value + ".");
	}

	public static void tryGet(int key) {
		System.out.print("\nAttempt to get Key " + key + ".");
	}

	public static void tryRemove(int key) {
		System.out.print("\nAttempt to remove Key " + key + ".");
	}

	public static void notFound(int key) {
		System.out.println("\nThe Key " + key + " was not found.");
	}

	public static void alreadyExistsInTree(int key) {
		System.out.print("\nKey " + key + " already exists on this tree. ");
	}

	public static void alreadyExists(int key) {
		System.out.println("\nKey " + key + " already exists. Ignoring...");
	}

	public static void resizing() {
		System.out.println("\n*** Resizing table. ***");
	}

	public static void successfulInsertion(int key, int hash, int level) {
		System.out.println("\nKey " + key + " put successfully in position " + hash + " at level " + level + ".");
	}

	public static void colisionMessageIfNeeded(int hash, int level) {
		if (level > 0) {
			System.out.print("\nCollision! Position " + hash + " already occupied. Going down one level... ");
		}
	}

	public static void found(int level, Node entry, int key, int hash) {
		System.out.println("\nKey " + key + " with Value " + entry.getValue() + " gotten successfully in position " + hash
				+ " at level " + level + ".");
	}

	public static void found(Node[] table, int key, int hash) {
		System.out.println("\nKey " + key + " with Value " + table[hash].getValue() + " gotten successfully in position " + hash
				+ ".");
	}

	public static void headColisionMessageIfNeeded(int hash) {
		System.out.print("\nCollision! Position " + hash + " already occupied. Placing in the head... ");
	}

	public static void printBalancingFactor(double balacingFactor) {
		System.out.println("\nBalancing Factor: " + balacingFactor);
	}

	public static void printLoadFactor(double loadFactor) {
		System.out.println("\nLoad Factor: " + loadFactor);
	}

	public static void remotionSuccessful(int key, int hash) {
		System.out.println("\nKey " + key + " removed successfully in position " + hash + ". ");
	}

	public static void remotionSuccessful(int key, int hash, int level) {
		System.out.println("\nKey " + key + " removed successfully in position " + hash + " at level " + level + ".");
	}

	public static void printAll(Node[] table) {

		System.out.println("\n\n>> Current Hash Table");

		for (int i = 0; i < table.length; i++) {

			System.out.print("Position " + i + ". \n");

			display(table[i]);

		}
	}

	public static void drawAll(final String title, final Node[] table, final double loadFactor) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Joins js = new Joins(title, table, loadFactor);
				js.setVisible(true);
			}
		});
	}

	public static void drawAll(final String title, final AVLNode[] nodes, final double loadFactor, final double balacingFactor) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SurfaceTreeForHashing(title, nodes, loadFactor, balacingFactor);
			}
		});
	}

	public static void drawAll(final String title, final AVLNodeForHalfOpenHashing[] nodes, final double loadFactor,
			final double balacingFactor) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SurfaceTreeForHalfOpenHashing(title, nodes, loadFactor, balacingFactor);
			}
		});
	}

	/** Print item and its children */
	private static void display(Node item) {
		int count = 0;

		while (item != null) {

			if (count != 0) {

				System.out.print("->");
			}

			if (item.equals(DeletedNode.getUniqueDeletedNode())) {
				System.out.print("[DELETED]");
			} else {
				System.out.print("[" + item.getKey() + ";" + item.getValue() + "]");
			}

			item = item.getNext();

			count++;

		}

		if (item == null && count == 0) {

			System.out.print("[ ]");

		}

		System.out.println();
	}
}
