package node;

import tree.AVLTreeForHalfOpenHashing;

public class AVLNodeForHalfOpenHashing {
	private int key;
	private int value;
	private AVLTreeForHalfOpenHashing avlTree;

	public AVLNodeForHalfOpenHashing(int key, int value) {
		this.avlTree = new AVLTreeForHalfOpenHashing();
		this.avlTree.insert(key, value);
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public AVLTreeForHalfOpenHashing getAVLTree() {
		return avlTree;
	}

	public void setAVLTree(AVLTreeForHalfOpenHashing avlTree) {
		this.avlTree = avlTree;
	}

}
