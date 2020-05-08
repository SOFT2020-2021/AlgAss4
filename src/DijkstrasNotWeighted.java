import org.jetbrains.annotations.NotNull;

import java.util.PriorityQueue;

public class DijkstrasNotWeighted {
    private AirportGraph graph;
    private int source;
    private int[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Path> pqMin = new PriorityQueue<>();

    public double getDistTo(int destination){
        return distTo[destination];
    }

    public DijkstrasNotWeighted(AirportGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        int V = graph.getAmountOfVertices();
        edgeTo = new int[V];
        distTo = new double[V];
        for (int v = 0; v < V; v++) {
            edgeTo[v] = -1;
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        edgeTo[source] = source;
        distTo[source] = 0;
        pqMin.add(new Path(source, 0.0));
        build();
    }

    private class Path implements Comparable<Path> {
        int destination;
        double weight;

        Path(int v, double weight) {
            this.destination = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull Path other) {
            if (this.weight < other.weight) return -1;
            if (this.weight > other.weight) return 1;
            return 0;
        }

    }

    private void build() {
        while (!pqMin.isEmpty()) {
            Path path = pqMin.poll();
            relax(path);
        }
    }

    private void relax(Path path) {
        Iterable<AirportNode.Route> adj = graph.getVertices()[path.destination];
        for (AirportNode.Route edge : adj) {
            double newDistance = distTo[edge.source] + 1;
            if (distTo[edge.destination] > newDistance) {
                distTo[edge.destination] = newDistance;
                edgeTo[edge.destination] = edge.source;
                pqMin.add(new Path(edge.destination, newDistance));
            }
        }
    }
}