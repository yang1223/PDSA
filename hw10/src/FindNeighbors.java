import java.util.Comparator;

public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}

    Node root = null;

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        for (Point2D p:points)
            this.insert(new Node(p, null, null));
    }

    private void insert(Node node){
        if (root == null) {
            root = node;
            node.setDependsOnX(true);
        } else {
            Node thisRoot = root;
            while (thisRoot.getNext(node) != null)
                thisRoot = thisRoot.getNext(node);
            if (thisRoot.isDependsOnX()) {
                node.setDependsOnX(false);
                if (thisRoot.getX() < node.getX()) thisRoot.setRight(node);
                else thisRoot.setLeft(node);
            } else {
                node.setDependsOnX(true);
                if (thisRoot.getY() < node.getY()) thisRoot.setRight(node);
                else thisRoot.setLeft(node);
            }
        }
    }


    // TODO
    // the result should be sorted accordingly to their distances to the query, from small to large
    // please use the Point2D from algs4.jar 
    public Point2D[] query(Point2D point , int k){
        MaxPQ<Node> pq = new MaxPQ<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getDistance() > o2.getDistance()) return 1;
                else if (o1.getDistance() < o2.getDistance()) return -1;
                else return 0;
            }
        });
        queryHelper(pq , root , point , k , Double.MAX_VALUE);
        Point2D[] result = new Point2D[k];
        for (int i = k-1; i >= 0 ; i--) {
            result[i] = pq.delMax().getPoint2D();
        }
        return result;
    }

    private void queryHelper(MaxPQ<Node> pq , Node root , Point2D target , int k , double minDistance) {
        if (root == null) return;
        root.setDistance(root.distanceTo(target));
        pq.insert(root);

        if (pq.size() > k) {
            pq.delMax();
            Node peek = pq.delMax();
            minDistance = peek.distanceTo(target);
            pq.insert(peek);
        }

        Node targetNode = new Node(target, null, null);
        queryHelper(pq , root.getNext(targetNode) , target, k , minDistance);

        if (pq.size() < k) {
            queryHelper(pq, root.getOppositeNext(targetNode), target, k , minDistance);
        } else if (root.isDependsOnX()) {
            if (Math.abs(root.getX() - target.x()) < minDistance)
                queryHelper(pq , root.getOppositeNext(targetNode) , target , k , minDistance);
        } else {
            if (Math.abs(root.getY() - target.y()) < minDistance)
                queryHelper(pq , root.getOppositeNext(targetNode) , target , k , minDistance);
        }
    }

    @Deprecated
    Node nearest = null;
    @Deprecated
    double nearestDistance = 0.0;
    @Deprecated
    public Point2D getNearestNode(Point2D target) {
        nearest = root;
        nearestDistance = nearest.distanceTo(target);
        getNearestNode(root , target);
        return nearest.getPoint2D();
    }

    @Deprecated
    private void getNearestNode(Node root , Point2D target) {
        if (root == null) return;
        if (root.distanceTo(target) < nearestDistance) {
            nearest = root;
            nearestDistance = nearest.distanceTo(target);
        }
        Node targetNode = new Node(target , null , null);
        Node next = root.getNext(targetNode);
        getNearestNode(next, target);
        if (root.isDependsOnX()) {
            if (Math.abs(root.getX() - target.x()) < nearestDistance)
                getNearestNode(root.getOppositeNext(targetNode) , target);
        } else {
            if (Math.abs(root.getY() - target.y()) < nearestDistance)
                getNearestNode(root.getOppositeNext(targetNode) , target);
        }
    }

    static class Node{
        private Point2D point2D;
        private Node left;
        private Node right;
        private boolean isDependsOnX;
        private double distance;

        Node(Point2D point2D , Node left , Node right){
            this.point2D = point2D;
            this.left = left;
            this.right = right;
        }

        public Node getNext(Node target) {
            if (this.isLeaf()) return null;
            if (this.isDependsOnX()){
                if (this.getX() < target.getX()) return this.getRight();
                else return this.getLeft();
            } else {
                if (this.getY() < target.getY()) return this.getRight();
                else return this.getLeft();
            }
        }

        public Node getOppositeNext(Node target) {
            if (this.isLeaf()) return null;
            if (this.isDependsOnX()){
                if (this.getX() < target.getX()) return this.getLeft();
                else return this.getRight();
            } else {
                if (this.getY() < target.getY()) return this.getLeft();
                else return this.getRight();
            }
        }

        public boolean isLeaf() { return left == null && right == null; }

        public double getX() { return point2D.x(); }

        public double getY() { return point2D.y(); }

        public double distanceTo(Point2D p) { return point2D.distanceTo(p); }

        public Point2D getPoint2D() { return point2D; }

        public boolean isDependsOnX() { return isDependsOnX; }

        public void setDependsOnX(boolean dependsOnX) { isDependsOnX = dependsOnX; }

        public Node getLeft() { return left; }

        public void setLeft(Node left) { this.left = left; }

        public Node getRight() { return right; }

        public void setRight(Node right) { this.right = right; }

        public double getDistance() { return distance; }

        public void setDistance(double distance) { this.distance = distance; }
    }


}

