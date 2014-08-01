package main;

import graphics.Joins;
import hashing.ClosedHashingDouble;
import hashing.ClosedHashingLinear;
import hashing.ClosedHashingQuadratic;
import hashing.HalfOpenHashing;
import hashing.OpenAVLHashing;
import hashing.OpenHashing;
import hashing.RehashingLinear;
import hashing.RehashingQuadratic;

import java.util.Random;

import javax.swing.SwingUtilities;

import util.HashPrinter;

@SuppressWarnings("unused")
public class Main {

	private static int[] KEYS = { 13, 13, 24, 6, 23, 55, 42, 28, 37, 52, 68, 29, 13, 66, 31, 80 };

	/**
	 * Value can be any other data, since the hashing function will consider the
	 * key to put, get and remove elements
	 */
	private static int value = 0;

	public static void main(String[] args) {
		/** Uncomment to test each one */
		// callOpenHashing();
		// callOpenAVLHashing();
		// callClosedHashingLinear();
		// callClosedHashingQuadratic();
		// callClosedHashingDouble();
		// callRehashingLinear();
		// callRehashingQuadratic();
		// callHalfOpenHashing();
	}

	private static void callOpenHashing() {

		OpenHashing hashing = new OpenHashing();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
			// hashing.putInTheHead(key, value); // Insertion in O(1);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.drawAll("Open Hashing", hashing.getTable(), hashing.loadFactor());

	}

	private static void callOpenAVLHashing() {

		OpenAVLHashing hashing = new OpenAVLHashing();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.printBalancingFactor(hashing.balancingFactor());

		HashPrinter.drawAll("Open Hashing", hashing.getTable(), hashing.loadFactor(), hashing.balancingFactor());
	}

	private static void callClosedHashingLinear() {

		ClosedHashingLinear hashing = new ClosedHashingLinear();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.drawAll("Closed Hashing Linear", hashing.getTable(), hashing.loadFactor());

	}

	private static void callClosedHashingQuadratic() {

		ClosedHashingQuadratic hashing = new ClosedHashingQuadratic();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.drawAll("Closed Hashing Quadratic", hashing.getTable(), hashing.loadFactor());

	}

	private static void callClosedHashingDouble() {

		ClosedHashingDouble hashing = new ClosedHashingDouble();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.drawAll("Double Hashing", hashing.getTable(), hashing.loadFactor());

	}

	private static void callRehashingLinear() {

		RehashingLinear hashing = new RehashingLinear();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.drawAll("Rehashing Linear", hashing.getTable(), hashing.loadFactor());

	}

	private static void callRehashingQuadratic() {

		RehashingQuadratic hashing = new RehashingQuadratic();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.drawAll("Rehashing Quadratic", hashing.getTable(), hashing.loadFactor());

	}

	private static void callHalfOpenHashing() {

		HalfOpenHashing hashing = new HalfOpenHashing();

		for (int key : KEYS) {
			value = Math.abs(new Random().nextInt(1000));
			hashing.put(key, value);
		}

		HashPrinter.printAll(hashing.getTable());

		hashing.get(23);
		hashing.get(31);
		hashing.get(6);
		hashing.remove(31);
		hashing.remove(23);
		hashing.remove(6);

		HashPrinter.printAll(hashing.getTable());

		HashPrinter.printLoadFactor(hashing.loadFactor());

		HashPrinter.printBalancingFactor(hashing.balancingFactor());

		HashPrinter.drawAll("Open Hashing", hashing.getTable(), hashing.loadFactor(), hashing.balancingFactor());
	}

}
