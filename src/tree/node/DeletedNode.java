package tree.node;

public class DeletedNode extends Node {
	private static DeletedNode entry = null;

	private DeletedNode() {
		super(-1, -1);
	}

	public static DeletedNode getUniqueDeletedNode() {
		if (entry == null)
			entry = new DeletedNode();
		return entry;
	}
}