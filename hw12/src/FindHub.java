/**
 * Created by Yang, Chi-Chang on 2016/6/24.
 */
public class FindHub {
    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++)
            points[i] = new Point2D(in.readDouble() , in.readDouble());

        EdgeWeightedGraph graph = new EdgeWeightedGraph(n);

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                graph.addEdge(new Edge(i,j,points[i].distanceTo(points[j])));
            }
        }
        KruskalMST mst = new KruskalMST(graph);

        EdgeWeightedDigraph spanningTree = new EdgeWeightedDigraph(n);
        for(Edge e:mst.edges()){
            int v = e.either();
            int w = e.other(v);
            double weight = e.weight();
            spanningTree.addEdge(new DirectedEdge(v, w, weight));
            spanningTree.addEdge(new DirectedEdge(w, v, weight));
        }


        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            DijkstraSP sp = new DijkstraSP(spanningTree,i);
            double sum = 0.0;
            for (int v = 0; v < n; v++)
                sum += sp.distTo(v);
            if (min > sum) min = sum;
        }

        System.out.println(String.format("%5.5f",min));


    }


}
