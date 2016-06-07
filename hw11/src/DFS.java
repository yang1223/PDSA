/**
 * Created by Yang Chi-Chang on 2016/6/7.
 */
public class DFS {

    private boolean[] marked;
    DFS(DirectedGraph graph , int source){
        marked = new boolean[graph.size()];
        dfs(graph , source);
    }

    private void dfs(DirectedGraph graph , int v){
        marked[v] = true;
        for (int w : graph.getAdjacencies(v)){
            if (!marked[w]) dfs(graph , w);
        }
    }

    public boolean isMarked(int v) {
        return marked[v];
    }

}
