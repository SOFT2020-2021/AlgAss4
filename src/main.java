import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {
        AirportGraph mg = new AirportGraph();
        BreadthFirst bf = new BreadthFirst(mg);
        DepthFirst df = new DepthFirst(mg);
        bf.search(2045, 2046, "2B");
        df.search(2045, 2046, "2B");
        DijkstrasWeighted djw = new DijkstrasWeighted(mg, 2045);
        DijkstrasNotWeighted djnw = new DijkstrasNotWeighted(mg, 2045);
        double distanceWeighted = djw.getDistTo(2046);
        double distanceNotWeighted = djnw.getDistTo(2046);
        System.out.println(distanceWeighted);
        System.out.println(distanceNotWeighted);
        BreadthFirstMST bfmst = new BreadthFirstMST(mg);
        Map<Integer, List<Integer>> coverage = bfmst.getWidestCoverage(mg);
        for (Map.Entry<Integer,List<Integer>> entry : coverage.entrySet())
            System.out.println("Airport " + mg.getVertices()[entry.getKey()].getAirport().getCode() +
                    " has " + entry.getValue().size() + " connections.");

    }
}
