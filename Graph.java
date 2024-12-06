import java.util.*;

public class Graph {
    private Map<String, List<String>> adjList;

    // Constructor
    public Graph() {
        // Initialize the adjacency list
        adjList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(String vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
        } else {
            System.out.println("Vertex " + vertex + " already exists.");
        }
    }

    // Add an edge to the graph (directed or undirected)
    public void addEdge(String src, String dest) {
        // Add the edge from src to dest
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.get(src).add(dest);

        // For undirected graph, uncomment the next two lines
        // adjList.putIfAbsent(dest, new ArrayList<>());
        // adjList.get(dest).add(src);
    }

    // Perform DFS traversal
    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        System.out.println("DFS Traversal:");
        dfsHelper(start, visited);
        System.out.println();
    }

    // Helper method for DFS
    private void dfsHelper(String vertex, Set<String> visited) {
        if (visited.contains(vertex)) {
            return;
        }

        // Mark the current vertex as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recursively visit all adjacent vertices
        for (String neighbor : adjList.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    // Perform BFS traversal
    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // Start BFS with the initial vertex
        visited.add(start);
        queue.add(start);

        System.out.println("BFS Traversal:");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");

            // Add all unvisited neighbors to the queue
            for (String neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create a graph instance
        Graph graph = new Graph();

        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        // Add edges
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        // Perform DFS and BFS traversals
        graph.dfs("A");
        graph.bfs("A");
    }
}
