package graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import node.AVLNode;
import node.TreeNode;
import tree.AVLTree;

public class SurfaceTree extends JPanel {

	private static final long serialVersionUID = -9054075219988539709L;
	private AVLNode[] nodes;
	private Double loadFactor;
	private Double balacingFactor;
	private String title = "";
	private int screenWidth, screenHeight;

	public static final int DIMENSION_TOP = 66, DIMENSION_LEFT = 35, DIMENSION_WIDTH = 100, DIMENSION_HEIGHT = 60;

	Node selection = null;

	Point linkEnd = null;
	Node linkTarget = null;

	public SurfaceTree(String title, AVLNode[] nodes, Double loadFactor, Double balacingFactor) {
		setDimensions();
		this.title = title;
		this.nodes = nodes;
		this.loadFactor = loadFactor;
		this.balacingFactor = balacingFactor;

		setDimensions();
		setLayout(null);

		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);

		setPreferredSize(new Dimension(screenWidth, screenHeight));

		setSelection(null);

		buildData();

		JFrame frame = makeFrame();
		frame.setVisible(true);
	}

	private void setDimensions() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
	}

	public JFrame makeFrame() {

		JFrame f = new JFrame(title);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scroller = new JScrollPane(this);
		scroller.setPreferredSize(new Dimension(screenWidth, screenHeight));

		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(scroller, BorderLayout.CENTER);
		f.pack();

		return f;
	}

	private void buildData() {

		for (int i = 0; i < nodes.length; i++) {
			AVLNode node = nodes[i];
			if (node != null && node.getAVLTree() != null) {
				AVLTree tree = node.getAVLTree();
				printAll(tree.top(), i, null, null, 0, 0);
			} else {
				printAll(null, i, null, null, 0, 0);
			}
		}
	}

	private void printAll(TreeNode root, int i, TreeNode parent, Node parentNode, int depthLeft, int depthRight) {
		Node p = null, l = null, r = null;

		if (root != null && parent == null) {
			p = makeNode(String.valueOf(root.getKey()), new Point(getRectNextLeft(i), getRectNextTop(depthRight)),
					Node.COLOR_PARENT, i);
		} else {
			p = parentNode;
		}

		if (root == null) {
			makeNode("", new Point(getRectNextLeft(i), getRectNextTop(depthRight)), Node.COLOR_PARENT, i);
			return;
		}

		if (root.left != null && p != null) {
			depthLeft++;
			l = makeNode(String.valueOf(root.left.key), new Point(getRectNextLeft(i), getRectNextTop(depthLeft)),
					Node.COLOR_LEFT, i);
			l.setParent(p);
		}
		if (root.right != null && p != null) {
			depthRight++;
			r = makeNode(String.valueOf(root.right.key), new Point(getRectNextRight(i), getRectNextTop(depthRight)),
					Node.COLOR_RIGHT, i);
			r.setParent(p);
		}

		if (root.left != null) {
			printAll(root.left, i, root, l, depthLeft, depthRight);
		}
		if (root.right != null) {
			printAll(root.right, i, root, r, depthLeft, depthRight);
		}
	}

	public class Node extends JLabel {

		private static final long serialVersionUID = -3405121483030773951L;
		Node parent = null;
		private Set<Node> children = new HashSet<Node>();
		public static final String COLOR_LEFT = "red", COLOR_RIGHT = "blue", COLOR_PARENT = "black";

		public Node(String text, Point location, String color, int index) {
			super("<html>"
					+ (color == COLOR_PARENT ? "<div style='border: 1px dashed black; border-bottom: 0px; text-align: center;'>"
							+ index + "</div>" : "") + "<div style='width: 35px; height: 35px; color: " + color
					+ "; font-family: Arial; padding-top: 10px; text-align: center; background: white; "
					+ "border: 1px solid black; margin: auto;'>" + text + "<div></html>");
			setOpaque(true);
			setLocation(location);
			setSize(getPreferredSize());
		}

		public void setText(String text) {
			super.setText(text);
			setSize(getPreferredSize());
		}

		public void setParent(Node newParent) {
			if (parent != null) {
				parent.getChildren().remove(this);
			}
			this.parent = newParent;
			if (newParent != null) {
				newParent.getChildren().add(this);
			}
		}

		public boolean hitsLinkHotspot(Point pt) {
			Rectangle r = getBounds();
			int x = pt.x - r.x;
			int y = pt.y - r.y;
			x -= (r.width / 2 - 4);
			return (-2 < x && x < 10 && -2 < y && y < 10);
		}

		public void paintLink(Graphics g) {
			Rectangle myRect = getBounds();
			int x1 = myRect.x + myRect.width / 2;
			int y1 = myRect.y + 4;

			int x2;
			int y2;
			if (parent != null) {
				Rectangle parentRect = parent.getBounds();
				x2 = parentRect.x + parentRect.width / 2;
				y2 = parentRect.y + parentRect.height;
			} else if (selection == this && linkEnd != null) {
				x2 = linkEnd.x;
				y2 = linkEnd.y;
			} else {
				return;
			}

			g.drawLine(x1, y1, x2, y2);
		}

		public Set<Node> getChildren() {
			return children;
		}

		public void setChildren(Set<Node> children) {
			this.children = children;
		}

	} // end of Node class

	// Resuming Tree methods.

	// paint links and nodes
	public void paintChildren(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int n = getComponentCount();
		for (int i = 0; i < n; ++i) {
			Component c = getComponent(i);
			if (c instanceof Node) {
				((Node) c).paintLink(g2);
			}
		}

		super.paintChildren(g);
	}

	// make a new node
	public Node makeNode(String text, Point pt, String color, int index) {
		Node n = new Node(text, pt, color, index);
		add(n);
		return n;
	}

	// Mouse listener
	// for selecting nodes, moving nodes, and dragging out links
	private MouseInputListener mouseListener = new MouseInputAdapter() {
		boolean dragging = false;
		int offsetX;
		int offsetY;

		public void mousePressed(MouseEvent e) {
			Component c = getComponentAt(e.getPoint());
			if (c instanceof Node) {
				setSelection((Node) c);
			} else {
				setSelection(null);
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (selection == null) {
				return;
			}

			if (dragging) {
				selection.setLocation(e.getX() + offsetX, e.getY() + offsetY);
				repaint();
			} else {
				dragging = true;
				offsetX = selection.getX() - e.getX();
				offsetY = selection.getY() - e.getY();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (dragging) {
				dragging = false;
			} else if (linkEnd != null) {
				if (linkTarget != null) {
					selection.setParent(linkTarget);
				}
				linkEnd = null;
				linkTarget = null;
				repaint();
			}
		}
	};

	public void setSelection(Node n) {

		if (selection != null) {
			selection.repaint();
		}

		selection = n;
	}

	private int getRectNextLeft(int i) {
		return DIMENSION_LEFT + i * DIMENSION_WIDTH;
	}

	private int getRectNextRight(int i) {
		return getRectNextLeft(i) + DIMENSION_WIDTH / 2;
	}

	private int getRectNextTop(int i) {
		return DIMENSION_TOP + i * DIMENSION_HEIGHT;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if (loadFactor != null) {
			g2d.drawString("Load Factor: " + loadFactor, DIMENSION_LEFT, Surface.LOAD_FACTOR_TOP);
		}

		if (balacingFactor != null) {
			g2d.drawString("Balacing Factor: " + balacingFactor, DIMENSION_LEFT, Surface.BALANCING_FACTOR_TOP);
		}
	}
}
