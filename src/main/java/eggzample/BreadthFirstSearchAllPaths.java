package eggzample;

import java.util.*;

public class BreadthFirstSearchAllPaths {

    public List<List<Edge>> findAllShortestPaths(Vertex source, Vertex destination, Graph graph) {
        List<List<Edge>> shortestPaths = new ArrayList<>();
        Queue<List<Edge>> queue = new LinkedList<>();

        for (Edge edge : graph.getEdgesFrom(source)) {
            List<Edge> path = new ArrayList<>();
            path.add(edge);
            queue.add(path);
        }

        int shortestPathLength = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            List<Edge> currentPath = queue.poll();
            Edge lastEdge = currentPath.getLast();
            Vertex current = lastEdge.getTarget();

            if (currentPath.size() > shortestPathLength) {
                continue;
            }

            if (current.equals(destination)) {
                if (currentPath.size() < shortestPathLength) {
                    shortestPaths.clear();
                    shortestPathLength = currentPath.size();
                }
                shortestPaths.add(currentPath);
                continue;
            }

            for (Edge nextEdge : graph.getEdgesFrom(current)) {
                Vertex next = nextEdge.getTarget();

                if (containsVertex(currentPath, next)) {
                    continue;
                }

                List<Edge> newPath = new ArrayList<>(currentPath);
                newPath.add(nextEdge);
                queue.add(newPath);
            }
        }

        return shortestPaths;
    }

    private boolean containsVertex(List<Edge> path, Vertex vertex) {
        for (Edge edge : path) {
            if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
                return true;
            }
        }
        return false;
    }
}

