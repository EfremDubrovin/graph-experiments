package eggzample;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) throws IOException {
//        Graph graph = GraphCsvReader.readGraphFromCsv("bitcoin-otc.csv");
//        System.out.println("Graph loaded with vertices: " + graph.getAllVertices().size());

        Graph graph = new Graph();
        graph.addEdge("Ivan", "Asen", 10, Instant.now().getEpochSecond());
        graph.addEdge("Ivan", "Petar", 5, Instant.now().getEpochSecond());
        graph.addEdge("Asen", "Stamat", 10, Instant.now().getEpochSecond());
        graph.addEdge("Asen", "Ivaylo", 6, Instant.now().getEpochSecond());
        graph.addEdge("Petar", "Ivaylo", 10, Instant.now().getEpochSecond());
        graph.addEdge("Stamat", "Pencho", 7, Instant.now().getEpochSecond());
        graph.addEdge("Pencho", "Ivaylo", 5, Instant.now().getEpochSecond());

//        visualizeGraph(graph);

        detectCyclesWithUnionFindAlgorithm(graph);

        // Depth-first search - find all paths
        findAllPathsWithDepthFirstSearch(graph);

        BreadthFirstSearchAllPaths breadthFirstSearchAllPaths = new BreadthFirstSearchAllPaths();

        List<List<Edge>> allShortestPathsBfs =
            breadthFirstSearchAllPaths.findAllShortestPaths(new Vertex("Ivan"), new Vertex("Ivaylo"), graph);
        allShortestPathsBfs.forEach(path -> System.out.println("BFS path: " + path));

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath();
        List<Edge> shortestPath =
            dijkstraShortestPath.findShortestPath(new Vertex("Ivan"), new Vertex("Ivaylo"), graph);
        System.out.println("DijkstraShortestPath: " + shortestPath);
    }

    private static void findAllPathsWithDepthFirstSearch(Graph graph) {
        Vertex source = new Vertex("Ivan");
        Vertex destination = new Vertex("Ivaylo");

        RecursiveDepthFirstSearch recursiveDepthFirstSearch = new RecursiveDepthFirstSearch(source, destination, graph);
        List<List<Edge>> paths = recursiveDepthFirstSearch.getAllPaths();
        paths.forEach(path -> System.out.println("DFS path: " + path));
    }

    private static void detectCyclesWithUnionFindAlgorithm(Graph graph) {
        UnionFind uf = new UnionFind();
        graph.getAllVertices().forEach(uf::makeSet);

        for (Edge edge : graph.getAllEdges()) {
            Vertex u = edge.getSource();
            Vertex v = edge.getTarget();

            if (uf.connected(u, v)) {
                System.out.println("Cycle detected between: " + u + " and " + v);
            } else {
                uf.union(u, v);
            }
        }
    }

    private static void visualizeGraph(Graph graph) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new GraphPanel(graph));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
