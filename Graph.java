package assign3_group5sec51;
/**
 *
 * @author Group5-Sec51: Lynn Abbidi 1083873, Aliya Haidar 1082079
 */
import java.util.List;

public interface Graph<V> {
   int getSize();
   List<V> getVertices();
    V getVertex(int index);
    int getIndex(V v);
    List<Integer> getNeighbors(int index);
    int getDegree(int index);
    void printEdges();
    void clear();
    boolean addVertex(V v);
    boolean addEdge(int u, int v);
    AbstractGraph<V>.Tree dfs(int v);
    AbstractGraph<V>.Tree bfs(int v);
}