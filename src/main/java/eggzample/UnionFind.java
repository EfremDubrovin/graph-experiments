package eggzample;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<Vertex, Vertex> parent = new HashMap<>();

    public void makeSet(Vertex v) {
        parent.put(v, v);
    }

    public Vertex find(Vertex v) {
        Vertex p = parent.get(v);
        if (!p.equals(v)) {
            parent.put(v, find(p)); // Path compression - find parent of p
        }
        return parent.get(v);
    }

    public void union(Vertex v1, Vertex v2) {
        Vertex root1 = find(v1);
        Vertex root2 = find(v2);

        if (!root1.equals(root2)) {
            parent.put(root1, root2); // Union
        }
    }

    public boolean connected(Vertex v1, Vertex v2) {
        return find(v1).equals(find(v2));
    }
}

