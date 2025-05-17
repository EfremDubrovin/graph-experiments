package eggzample;

public class Edge {
    private final Vertex source;
    private final Vertex target;
    private final int weight; // Trust rating from -10 to +10
    private final double timestamp;

    public Edge(Vertex source, Vertex target, int weight, double timestamp) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.timestamp = timestamp;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    public double getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return source + " -> " + target + " (trust: " + weight + ", time: " + timestamp + ")";
    }
}
