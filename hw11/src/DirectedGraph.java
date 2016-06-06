import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang Chi-Chang on 2016/6/7.
 */
public class DirectedGraph {

    protected final int v;
    protected List<List<Integer>> adjacencies;
    DirectedGraph (int v){
        this.v = v;
        adjacencies = new ArrayList<List<Integer>>();
        for (int i = 0; i < v; i++) {
            adjacencies.add(new ArrayList<Integer>());
        }
    }

    public int size(){
        return v;
    }

    public void addEdge(int v , int w){
        adjacencies.get(v).add(w);
    }

    public List<Integer> getAdjacencies(int v){
        return adjacencies.get(v);
    }
}
