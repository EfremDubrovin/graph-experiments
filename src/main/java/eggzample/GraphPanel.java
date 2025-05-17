package eggzample;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GraphPanel extends JPanel {
    private final Graph graph;
    private final Map<Vertex, Point> positions = new HashMap<>();

    public GraphPanel(Graph graph) {
        this.graph = graph;
        layoutNodes();
        setPreferredSize(new Dimension(800, 600));
    }

    private void layoutNodes() {
        List<Vertex> vertices = new ArrayList<>(graph.getAllVertices());
        int radius = 200;
        int centerX = 400;
        int centerY = 300;
        int n = vertices.size();

        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            positions.put(vertices.get(i), new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEdges(g);
        drawVertices(g);
    }

    private void drawEdges(Graphics g) {
        g.setColor(Color.GRAY);
        Set<String> drawn = new HashSet<>();

        for (Vertex v : graph.getAllVertices()) {
            Point p1 = positions.get(v);
            for (Edge e : graph.getEdgesFrom(v)) {
                Vertex u = e.getTarget();
                Point p2 = positions.get(u);

                // Avoid drawing duplicate undirected edges
                String key = v.getId() + "-" + u.getId();
                String rev = u.getId() + "-" + v.getId();
                if (drawn.contains(key) || drawn.contains(rev)) continue;

                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                drawn.add(key);
            }
        }
    }

    private void drawVertices(Graphics g) {
        for (Vertex v : graph.getAllVertices()) {
            Point p = positions.get(v);
            g.setColor(Color.ORANGE);
            g.fillOval(p.x - 20, p.y - 20, 40, 40);
            g.setColor(Color.BLACK);
            g.drawOval(p.x - 20, p.y - 20, 40, 40);

            // Draw ID centered
            FontMetrics fm = g.getFontMetrics();
            String text = v.getId();
            int textWidth = fm.stringWidth(text);
            g.drawString(text, p.x - textWidth / 2, p.y + 5);
        }
    }
}
