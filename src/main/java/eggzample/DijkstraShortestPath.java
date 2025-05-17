package eggzample;

import java.util.*;

public class DijkstraShortestPath {

    public List<Edge> findShortestPath(Vertex source, Vertex destination, Graph graph) {
        Map<Vertex, Double> distances = new HashMap<>();
        Map<Vertex, Edge> previousEdge = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        // Init distances
        for (Vertex vertex : graph.getAllVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        distances.put(source, 0.0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            boolean added = visited.add(current);
            if (!added) {
                continue;
            }

            if (current.equals(destination)) {
                break;
            }

            List<Edge> edges = graph.getEdgesFrom(current);
            for (Edge edge : edges) {
                Vertex neighbor = edge.getTarget();
                double weight = normalizeWeight(edge); // Derived from trust
                double newDist = distances.get(current) + weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previousEdge.put(neighbor, edge);
                    queue.add(neighbor);
                }
            }
        }

        // Reconstruct path
        List<Edge> path = new ArrayList<>();
        Vertex current = destination;
        while (previousEdge.containsKey(current)) {
            Edge edge = previousEdge.get(current);
            path.add(edge);
            current = edge.getSource();
        }

        Collections.reverse(path);
        return path;
    }

    // dataset weight is in the range -10, 10
    private double normalizeWeight(Edge edge) {
        return 10.0 - edge.getWeight();
    }
}

