import Exceptions.AirportCodeNotFound;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AirportGraph {

    private int amountOfVertices;               // number of vertices in this digraph
    private int amountOfEdges;                 // number of edges in this digraph
    private AirportNode[] vertices;            // adj[v] = adjacency list for vertex v
    private int[] indegree;                    // indegree[v] = indegree of vertex v

    public AirportGraph() {

        Path path = Paths.get(System.getProperty("user.dir") + "/src/data/airports.txt");
        String[] tempArray;
        try {
            tempArray = Files.lines(path)
                    .flatMap(line -> Stream.of(line.split(";")))
                    .toArray(String[]::new);

            int amountOfNodes = tempArray.length / 6 - 1;

            vertices = new AirportNode[amountOfNodes];
            amountOfVertices = amountOfNodes;
            indegree = new int[amountOfNodes];

            AirportNode node = new AirportNode();
            for (int i = 6; i < tempArray.length; i++) {
                switch (i % 6) {
                    case 0: //code
                        node = new AirportNode();
                        node.getAirport().setCode(tempArray[i]);
                        break;
                    case 1: //name
                        node.getAirport().setName(tempArray[i]);
                        break;
                    case 2: //city
                        node.getAirport().setCity(tempArray[i]);
                        break;
                    case 3: //country
                        node.getAirport().setCountry(tempArray[i]);
                        break;
                    case 4: //latitude
                        node.getAirport().setLatitude(tempArray[i]);
                        break;
                    case 5: //longitude
                        node.getAirport().setLongitude(tempArray[i]);
                        vertices[i / 6 - 1] = node;
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            path = Paths.get(System.getProperty("user.dir") + "/src/data/routes.txt");

            tempArray = Files.lines(path)
                    .flatMap(line -> Stream.of(line.split(";")))
                    .toArray(String[]::new);

            String source = "";
            String destination = "";
            String airlineName = "";
            String distance = "";
            for (int j = 5; j < tempArray.length; j++) {
                switch (j % 5) {
                    case 0:
                        airlineName = tempArray[j];
                        break;
                    case 1: //source airport code
                        source = tempArray[j];
                        break;
                    case 2: //destination airport code
                        destination = tempArray[j];
                        break;
                    case 3:
                        distance = tempArray[j];
                        int sourceNodeIndex = getVerticeByCode(source);
                        int destinationNodeIndex = getVerticeByCode(destination);
                        addEdge(sourceNodeIndex, destinationNodeIndex, airlineName, distance);

                    case 4:
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        } catch (Exception E) {
            System.out.println("Exception was caught while creating directed graph from data " + E.getMessage());
        }
    }

    private int getVerticeByCode(String code) {

        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getAirport().getCode().equals(code)) {
                return i;
            }
        }
        throw new AirportCodeNotFound(" following code does not exist in the graph: " + code);
    }

    public int getAmountOfVertices() {
        return amountOfVertices;
    }

    public int getAmountOfEdges(){
        return amountOfEdges;
    }

    public AirportNode[] getVertices() {
        return vertices;
    }

    private void addEdge(int source, int destination, String airlineName, String distance) {
        vertices[source].add(source, destination, airlineName, distance);
        indegree[destination]++;
        amountOfEdges++;
    }

}
