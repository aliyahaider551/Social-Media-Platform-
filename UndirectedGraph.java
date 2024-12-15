package assign3_group5sec51;
/**
 *
 * @author Group5-Sec51: Lynn Abbidi 1083873, Aliya Haidar 1082079
 */
public class UndirectedGraph<V> extends AbstractGraph<V> {
    @Override
    public boolean addEdge(int u, int v) {
        if (u >= 0 && u < getSize() && v >= 0 && v < getSize()) {
            if (!neighbors.get(u).contains(v)) {
                neighbors.get(u).add(v);
                neighbors.get(v).add(u); // Undirected edge between u and v
                return true;
            }
        }
        return false;
    }
}
