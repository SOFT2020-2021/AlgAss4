import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirst {
    private AirportGraph graph;
    private boolean[] inspected;
    private Queue<Integer> verticesToExplore;

    public BreadthFirst(AirportGraph graph) {
        this.graph = graph;
        inspected = new boolean[graph.getAmountOfVertices()];
        verticesToExplore = new LinkedList();
    }

    private boolean explore(int source, int destination, String airline){
        try {
            for (AirportNode.Route route : graph.getVertices()[source].getRoutes()) {
                if(!route.airline.equals(airline)) continue;
                if (route.destination == destination) return true;
                if (!inspected[route.destination]) verticesToExplore.add(route.destination);
                inspected[route.destination] = true;
            }
            return explore(verticesToExplore.poll(), destination, airline);
        } catch(NullPointerException E){
            return false;
        }
    }

    public boolean search(int foundation, int destination, String airline) {
        boolean x = explore(foundation, destination, airline);
        System.out.println(x);
        return true;
    }


}