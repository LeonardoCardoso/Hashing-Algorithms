package util.graphics;

import tree.node.Node;

import javax.swing.*;

public class Joins extends JFrame {

    private static final long serialVersionUID = -4142017181972222003L;
    private String title = "";
    private Node[] nodes;
    private double loadFactor;

    public Joins(String title, Node[] nodes, double loadFactor) {
        this.title = title;
        this.nodes = nodes;
        this.loadFactor = loadFactor;
        initUI();
    }

    private void initUI() {

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int jPanelWidth = nodes.length * SurfaceForHashing.DIMENSION_WIDTH + 2 * SurfaceForHashing.DIMENSION_LEFT;
        setSize(jPanelWidth, SurfaceForHashing.DIMENSION_HEIGHT * 10);
        add(new SurfaceForHashing(nodes, loadFactor));
        setLocationRelativeTo(null);

    }

}