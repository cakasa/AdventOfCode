package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 implements Day{
    private static File file = new File("src/AoC_2021/input/day_12/big.txt");

    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        Map<String, List<String>> adjacencyList = new HashMap<>();

        while (!line.equals("END")) {
            String[] nodeStrings = line.split("-");
            if(!adjacencyList.containsKey(nodeStrings[0])) {
                adjacencyList.put(nodeStrings[0], new LinkedList<>());
            }
            if(!adjacencyList.containsKey(nodeStrings[1])) {
                adjacencyList.put(nodeStrings[1], new LinkedList<>());
            }
            adjacencyList.get(nodeStrings[0]).add(nodeStrings[1]);
            adjacencyList.get(nodeStrings[1]).add(nodeStrings[0]);

            line = sc.nextLine();
        }

        List<String> visited = new ArrayList<>();
        int numberOfPaths = dfs("start", adjacencyList, visited);
        return numberOfPaths;
    }

    public int dfs(String current, Map<String, List<String>> adjacencyList, List<String> visited) {
        if (current.equals("end")) {
            return 1;
        }

        List<String> neighbours = adjacencyList.get(current);
        int paths = 0;

        for(String adjacentNode : neighbours) {
            if(current.toLowerCase(Locale.ROOT).equals(current)) {
                visited.add(current);
            }

            if(!visited.contains(adjacentNode)) {
                paths += dfs(adjacentNode, adjacencyList, visited);
            }

            if(current.toLowerCase(Locale.ROOT).equals(current)) {
                visited.remove(current);
            }
        }
        return paths;
    }

    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        Map<String, List<String>> adjacencyList = new HashMap<>();

        while (!line.equals("END")) {
            String[] nodeStrings = line.split("-");
            if(!adjacencyList.containsKey(nodeStrings[0])) {
                adjacencyList.put(nodeStrings[0], new LinkedList<>());
            }
            if(!adjacencyList.containsKey(nodeStrings[1])) {
                adjacencyList.put(nodeStrings[1], new LinkedList<>());
            }
            adjacencyList.get(nodeStrings[0]).add(nodeStrings[1]);
            adjacencyList.get(nodeStrings[1]).add(nodeStrings[0]);

            line = sc.nextLine();
        }

        int numberOfPaths = dfs2("start", adjacencyList, new ArrayList<>(), false);
        return numberOfPaths;
    }

    public int dfs2(String current, Map<String, List<String>> adjacencyList, List<String> visited, boolean visitedSomethingTwice) {
        if (current.equals("end")) {
            return 1;
        }

        List<String> neighbours = adjacencyList.get(current);
        int paths = 0;
        if(current.toLowerCase(Locale.ROOT).equals(current)) {
            visited.add(current);
        }
        for(String adjacentNode : neighbours) {
            if(adjacentNode.equals("start")) {
                continue;
            }

            if(!visited.contains(adjacentNode)) {
                paths += dfs2(adjacentNode, adjacencyList, visited, visitedSomethingTwice);
            } else if(visited.contains(adjacentNode) && !visitedSomethingTwice) {
                paths += dfs2(adjacentNode, adjacencyList, visited, true);
            }
        }
        if(current.toLowerCase(Locale.ROOT).equals(current)) {
            visited.remove(current);
        }
        return paths;
    }
}
