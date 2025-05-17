package eggzample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GraphCsvReader {

    public static Graph readGraphFromCsv(String filePath) throws IOException {
        Graph graph = new Graph();
        InputStream input = Main.class.getClassLoader().getResourceAsStream(filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String sourceId = parts[0].trim();
                String targetId = parts[1].trim();
                int rating = Integer.parseInt(parts[2].trim());
                double timestamp = Double.parseDouble(parts[3].trim());

                graph.addEdge(sourceId, targetId, rating, timestamp);
            }
        }

        return graph;
    }
}
