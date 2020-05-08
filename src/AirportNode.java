import entitites.Airport;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class AirportNode implements Iterable<AirportNode.Route>{

    private Airport airport = new Airport();
    private List<Route> edges = new ArrayList();

    public AirportNode() { }

    public void add(int source, int destination, String airline, String distance) {
        edges.add(new Route(source, destination, airline, distance));
    }

    public Airport getAirport() {
        return airport;
    }

    public List<Route> getRoutes() {
        return edges;
    }

    @NotNull
    @Override
    public Iterator<Route> iterator() {
        return edges.iterator();
    }

    @Override
    public void forEach(Consumer<? super Route> action) {

    }

    @Override
    public Spliterator<Route> spliterator() {
        return null;
    }

    public class Route {
        String airline;
        int source;
        int destination;
        String distance;
        public Route(int source, int destination, String airline, String distance) {
            this.source = source;
            this.destination = destination;
            this.airline = airline;
            this.distance = distance;
        }
    }


}
