public class FindNeighbors {

    // DO NOT MODIFY THE CONSTRUCTOR! 
    public FindNeighbors(){}


    public Node root = null;

    // TODO
    // please use the Point2D from algs4.jar 
    public void init(Point2D[] points){
        int counter = 0;
        for (Point2D p:points) {
            System.out.println(counter++);
            insert(new Node(p , null , null));
        }
    }

    private void insert(Node node){
        if (root == null) {
            root = node;
        } else {
            Node thisRoot = root;
            while (thisRoot.getNext(node) != null) {
                thisRoot = thisRoot.getNext(node);
            }
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
    public Point2D[] query(Point2D point, int k){
        Point2D[] result = new Point2D[k];
        return result;
    }


    static class Node{
        Point2D point2D;
        private Node left;
        private Node right;
        boolean isDependsOnX;

        Node(Point2D point2D , Node left , Node right){
            this.point2D = point2D;
            this.left = left;
            this.right = right;
        }

        public Node getNext(Node target) {
            if (this.isDependsOnX()){
                if (this.getX() < target.getX()) return this.getRight();
                else return this.getLeft();
            } else {
                if (this.getY() < target.getY()) return this.getRight();
                else return this.getLeft();
            }
        }


        public boolean isLeaf() { return left == null && right == null; }

        public double getX() { return point2D.x(); }

        public double getY() { return point2D.y(); }

        public double distanceTo(Point2D p) { return point2D.distanceTo(p); }

        public Point2D getPoint2D() { return point2D; }

        public boolean isDependsOnX() {
            return isDependsOnX;
        }

        public void setDependsOnX(boolean dependsOnX) {
            isDependsOnX = dependsOnX;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

    }

}

