public class DepthFirst {
    private AirportGraph graph;
    private boolean[] inspected;

    public DepthFirst(AirportGraph graph) {
        this.graph = graph;
        inspected = new boolean[graph.getAmountOfVertices()];
    }

    private boolean explore(int source, int destination, String airline) {
        try {
            if(source == destination) return true;
            inspected[source] = true;
            for (AirportNode.Route route : graph.getVertices()[source].getRoutes()) {
                if (!route.airline.equals(airline)) continue;
                if (route.destination == destination) return true;
                if (!inspected[route.destination]) {
                    inspected[route.destination] = true;
                    return explore(route.destination, destination, airline);
                }
            }
        } catch (NullPointerException E) {
            return false;
        }
        return false;
    }

    public boolean search(int foundation, int destination, String airline) {
        return explore(foundation, destination, airline);
    }

}