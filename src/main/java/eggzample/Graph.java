package eggzample;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private final Map<String, Vertex> vertices = new HashMap<>();
    private final Map<Vertex, List<Edge>> adjacencyList = new HashMap<>();

    public Vertex getOrCreateVertex(String id) {
        return vertices.computeIfAbsent(id, Vertex::new);
    }

    public void addEdge(String sourceId, String targetId, int weight, double timestamp) {
        Vertex source = getOrCreateVertex(sourceId);
        Vertex target = getOrCreateVertex(targetId);
        Edge edge = new Edge(source, target, weight, timestamp);
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(edge);
    }

    public List<Edge> getEdgesFrom(Vertex v) {
        return adjacencyList.getOrDefault(v, Collections.emptyList());
    }

    public Collection<Vertex> getAllVertices() {
        return vertices.values();
    }

    public Collection<Edge> getAllEdges() {
        return vertices.values().stream()
            .map(this::getEdgesFrom)
            .flatMap(Collection::stream)
            .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : adjacencyList.keySet()) {
            for (Edge e : adjacencyList.get(v)) {
                sb.append(e).append("\n");
            }
        }
        return sb.toString();
    }
}
