import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {

    private class Node {
        private final Point2D p;
        private final RectHV rect;
        private Node left, right;
        private final boolean vertical;  // true for vertical, false for horizontal

        public Node(Point2D p, boolean vertical, RectHV rect) {
            this.p = p;
            this.rect = rect;
            this.vertical = vertical;
        }

        public boolean nextVertical() {
            return !vertical;
        }

        public RectHV leftRect() {
            if (vertical)
                return new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
            else
                return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
        }

        public RectHV rightRect() {
            if (vertical)
                return new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            else
                return new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
        }

        public boolean isRightOrTopOf(Point2D q) {
            return (vertical && p.x() > q.x()) || (!vertical && p.y() > q.y());
        }
    }

    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }
    
    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        
        if (root == null) {
            root = new Node(p, true, new RectHV(0, 0, 1, 1));
            size++;
            return;
        }

        Node prev = null;
        Node curr = root;
        do {
            if (curr.p.equals(p))
                return;
            prev = curr;
            curr = curr.isRightOrTopOf(p) ? curr.left : curr.right;
        } while (curr != null);

        if (prev.isRightOrTopOf(p))
            prev.left = new Node(p, prev.nextVertical(), prev.leftRect());
        else
            prev.right = new Node(p, prev.nextVertical(), prev.rightRect());
        size++;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        Node curr = root;
        while (curr != null) {
            if (curr.p.equals(p))
                return true;
            curr = curr.isRightOrTopOf(p) ? curr.left : curr.right;
        }
        return false;
    }

    // draw all points to standard draw
    public void draw() {

    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();
        ArrayList<Point2D> res = new ArrayList<Point2D>();
        addAll(root, rect, res);
        return res;
    }

    private void addAll(Node node, RectHV rect, ArrayList<Point2D> res) {
        if (node == null)
            return;
        if (rect.contains(node.p)) {
            res.add(node.p);
            addAll(node.left, rect, res);
            addAll(node.right, rect, res);
            return;
        }
        if (node.isRightOrTopOf(new Point2D(rect.xmin(), rect.ymin()))) {
            addAll(node.left, rect, res);
        }
        if (!node.isRightOrTopOf(new Point2D(rect.xmax(), rect.ymax()))) {
            addAll(node.right, rect, res);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();
        if (this.isEmpty())
            return null;
        return nearest(p, root.p, root);
    }

    private Point2D nearest(Point2D target, Point2D min, Node node) {
        if (node == null)
            return min;
        double minDist = min.distanceTo(target);
        if (node.rect.distanceTo(target) < minDist) {
            double nodeDist = node.p.distanceTo(target);
            if (nodeDist < minDist)
                min = node.p;
            if (node.isRightOrTopOf(target)) {
                min = nearest(target, min, node.left);
                min = nearest(target, min, node.right);
            } else {
                min = nearest(target, min, node.right);
                min = nearest(target, min, node.left);
            }
        }
        return min;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        
    }
}
