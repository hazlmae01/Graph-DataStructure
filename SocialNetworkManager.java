import java.util.*;

public class SocialNetworkManager {
    static class Graph {
        private final Map<String, List<String>> adjacencyList = new HashMap<>();

        public void addPerson(String person) {
            adjacencyList.putIfAbsent(person, new ArrayList<>());
        }

        public void addConnection(String person1, String person2) {
            adjacencyList.get(person1).add(person2);
            adjacencyList.get(person2).add(person1);
        }

        public boolean areConnected(String person1, String person2) {
            Set<String> visited = new HashSet<>();
            return dfs(person1, person2, visited);
        }

        private boolean dfs(String current, String target, Set<String> visited) {
            if (current.equals(target)) return true;
            visited.add(current);
            for (String neighbor : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    if (dfs(neighbor, target, visited)) return true;
                }
            }
            return false;
        }

        public void displayNetwork() {
            adjacencyList.forEach((person, connections) -> {
                System.out.println(person + " is connected to " + connections);
            });
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph socialNetwork = new Graph();

        System.out.println("Social Network Manager");
        while (true) {
            System.out.println("1. Add Person\n2. Add Connection\n3. Check Connection\n4. Display Network\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter person's name: ");
                    String person = scanner.nextLine();
                    socialNetwork.addPerson(person);
                    System.out.println(person + " added.");
                }
                case 2 -> {
                    System.out.print("Enter two names to connect (space-separated): ");
                    String[] people = scanner.nextLine().split(" ");
                    if (people.length == 2) {
                        socialNetwork.addConnection(people[0], people[1]);
                        System.out.println("Connection added between " + people[0] + " and " + people[1]);
                    } else {
                        System.out.println("Invalid input! Please enter exactly two names.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter two names to check connection (space-separated): ");
                    String[] people = scanner.nextLine().split(" ");
                    if (people.length == 2) {
                        boolean connected = socialNetwork.areConnected(people[0], people[1]);
                        System.out.println(people[0] + " and " + people[1] + " are " + (connected ? "connected." : "not connected."));
                    } else {
                        System.out.println("Invalid input! Please enter exactly two names.");
                    }
                }
                case 4 -> {
                    System.out.println("Social Network Connections:");
                    socialNetwork.displayNetwork();
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
