package assign3_group5sec51;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Group5-Sec51: Lynn Abbidi 1083873, Aliya Haidar 1082079
 */
public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();               // List of vertices
    protected List<List<Integer>> neighbors = new ArrayList<>();  // Adjacency list for edges

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    @Override
    public int getDegree(int index) {
        return neighbors.get(index).size();
    }

    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print("Vertex " + getVertex(u) + " -> ");
            for (int v : neighbors.get(u)) {
                System.out.print("(" + getVertex(u) + ", " + getVertex(v) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            neighbors.add(new ArrayList<>());
            return true;
        }
        return false;
    }

    // Abstract method for adding an edge, to be implemented by subclasses
    @Override
    public abstract boolean addEdge(int u, int v);

    // DFS and BFS tree structures and algorithms
    public class Tree {
        private int root;
        private int[] parent;

        public Tree(int root, int[] parent) {
            this.root = root;
            this.parent = parent;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }
    }

    @Override
    public Tree dfs(int v) {
        boolean[] visited = new boolean[getSize()];
        int[] parent = new int[getSize()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;  // -1 indicates no parent
        }
        dfs(v, visited, parent);
        return new Tree(v, parent);
    }

    private void dfs(int u, boolean[] visited, int[] parent) {
        visited[u] = true;
        for (int neighbor : neighbors.get(u)) {
            if (!visited[neighbor]) {
                parent[neighbor] = u;
                dfs(neighbor, visited, parent);
            }
        }
    }

    @Override
    public Tree bfs(int v) {
        boolean[] visited = new boolean[getSize()];
        int[] parent = new int[getSize()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;  // -1 indicates no parent
        }

        List<Integer> queue = new ArrayList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int u = queue.remove(0);
            for (int neighbor : neighbors.get(u)) {
                if (!visited[neighbor]) {
                    parent[neighbor] = u;
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return new Tree(v, parent);
    }
}
