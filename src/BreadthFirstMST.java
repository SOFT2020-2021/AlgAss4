import java.util.*;

public class BreadthFirstMST {
    private AirportGraph graph;
    private boolean[] inspected;
    private List<Integer> minimumSpanningTree;
    private Queue<Integer> verticesToExplore;

    public BreadthFirstMST(AirportGraph graph) {
        this.graph = graph;
        inspected = new boolean[graph.getAmountOfVertices()];
        verticesToExplore = new LinkedList();
    }

    private void explore(int source){
        try {
            for (AirportNode.Route route : graph.getVertices()[source].getRoutes()) {
                if (!inspected[route.destination]) {
                    minimumSpanningTree.add(route.destination);
                    verticesToExplore.add(route.destination);
                }
                inspected[route.destination] = true;
            }
            explore(verticesToExplore.poll());
        } catch(NullPointerException E){
            return;
        }
    }

    public Map<Integer, List<Integer>> getWidestCoverage(AirportGraph ag) {
        Map<Integer, List<Integer>> map = new HashMap();

        for(int i = 0; i < ag.getAmountOfVertices(); i++){
            minimumSpanningTree = new ArrayList();
            inspected = new boolean[graph.getAmountOfVertices()];
            verticesToExplore = new LinkedList();
            explore(i);
            map.put(i, minimumSpanningTree);
        }
        return map;
    }


}