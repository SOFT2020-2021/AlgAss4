import entitites.Airport;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class MyGraph {

    private int V;                 // number of vertices in this digraph
    private int E;                 // number of edges in this digraph
    private Node[] adj;         // adj[v] = adjacency list for vertex v
    private int[] indegree;        // indegree[v] = indegree of vertex v

    public MyGraph() {

        Path path = Paths.get(System.getProperty("user.dir") + "/src/data/airports.txt");
        String[] tempArray;
        try {
            tempArray = Files.lines(path)
                    .flatMap(line -> Stream.of(line.split(";")))
                    .toArray(String[]::new);
            adj = new Node[tempArray.length / 6 - 1];

            Node node = new Node();
            for(int i = 6; i < tempArray.length; i++){
                switch(i % 6) {
                    //code
                    case 0 : {
                        node = new Node();
                        node.airport.setCode(tempArray[i]);
                        break;
                    }
                    //name
                    case 1 : {
                        node.airport.setName(tempArray[i]);
                        break;
                    }
                    //city
                    case 2 : {
                        node.airport.setCity(tempArray[i]);
                        break;
                    }
                    //country
                    case 3 : {
                        node.airport.setCountry(tempArray[i]);
                        break;
                    }
                    //latitude
                    case 4 : {
                        node.airport.setLatitude(tempArray[i]);
                        break;
                    }
                    //longitude
                    case 5 : {
                        node.airport.setLongitude(tempArray[i]);
                        adj[i / 6 - 1] = node;
                        break;
                    }
                }

                path = Paths.get(System.getProperty("user.dir") + "/src/data/routes.txt");

                tempArray = Files.lines(path)
                        .flatMap(line -> Stream.of(line.split(";")))
                        .toArray(String[]::new);
                for(int j = 5; j < tempArray.length; j++) {
                    String source;
                    String destination;
                    switch (j % 5) {
                        case 0: {

                        }
                        //source airport code
                        case 1: {
                            source = tempArray[j];
                            break;
                        }
                        //destination airport code
                        case 2: {
                            destination = tempArray[j];
                            break;
                        }
                    }
                }


            }
        } catch(Exception E){
            System.out.println("Exception was catched while creating directed graph from data " + E.getMessage());
        }
    }

    private class Node {
        private Airport airport = new Airport();
        private List<Integer> edges;
    }

}
