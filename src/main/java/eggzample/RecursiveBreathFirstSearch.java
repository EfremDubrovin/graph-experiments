package eggzample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecursiveBreathFirstSearch {

    private final Vertex source;
    private final Vertex target;
    private final Graph graph;
    private final List<List<Edge>> allPaths = new ArrayList<>();
    private final List<Edge> currentPath = new ArrayList<>();
    private final Set<Vertex> visited = new HashSet<>();

    public RecursiveBreathFirstSearch(Vertex source, Vertex target, Graph graph) {
        this.source = source;
        this.target = target;
        this.graph = graph;
    }

    public List<List<Edge>> getAllPaths() {
        bfs(source, target, graph);
        return allPaths;
    }

    private void bfs(Vertex current, Vertex destination, Graph graph) {
        if (visited.contains(current)) {
            return;
        }

        visited.add(current);

        for (Edge edge : graph.getEdgesFrom(current)) {
            Vertex next = edge.getTarget();
            currentPath.add(edge);

            if (next.equals(destination)) {
                allPaths.add(new ArrayList<>(currentPath));
            } else {
                bfs(next, destination, graph);
            }
            currentPath.removeLast();
        }

        visited.remove(current);
    }

}
