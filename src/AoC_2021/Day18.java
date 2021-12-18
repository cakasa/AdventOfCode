package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day18 implements Day {
    private static File file = new File("src/AoC_2021/input/day_18/big.txt");

    @Override
    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        Node number = parseNumber(line, 0);

        line = sc.nextLine();
        while(!line.equals("END")) {
            Node other = parseNumber(line, 0);

            increaseDepth(number);
            increaseDepth(other);

            number = new Node(null, number, other, 0,-1);
            number.left.parent = number;
            number.right.parent = number;
            reduce(number);


            line = sc.nextLine();
        }

        return magnitude(number);
    }

    private long magnitude(Node number) {
        if(number.left == null && number.right == null) {
            return number.value;
        }

        return 3 * magnitude(number.left) + 2 * magnitude(number.right);
    }

    private void print(Node number) {
        if(number.left == null && number.right == null) {
            System.out.print(number.value);
            return;
        }

        System.out.print("[");

        print(number.left);

        System.out.print(",");

        print(number.right);

        System.out.print("]");
    }

    private void reduce(Node number) {
        boolean exploded = false;
        boolean splitted = false;
        do {
            exploded = false;
            splitted = false;

            exploded = explode(number);
            if(exploded) {
                continue;
            }

            splitted = split(number);
        } while(exploded || splitted);
    }

    private boolean split(Node number) {
        List<Node> valueNodes = new ArrayList<>();
        dfs(number, valueNodes);
        for(int i = 0; i < valueNodes.size(); i++) {
            Node currentNode = valueNodes.get(i);
            if (currentNode.value >= 10) {
                Node left = new Node(currentNode, null, null, currentNode.depth + 1, currentNode.value / 2);
                Node right = new Node(currentNode, null, null, currentNode.depth + 1, currentNode.value - left.value);
                currentNode.value = -1;
                currentNode.left = left;
                currentNode.right = right;
                return true;
            }
        }
        return false;
    }

    private boolean explode(Node number) {
        List<Node> valueNodes = new ArrayList<>();
        dfs(number, valueNodes);

        for(int i = 0; i < valueNodes.size() - 1; i++) {
            if(valueNodes.get(i).parent == valueNodes.get(i + 1).parent) {
                if (valueNodes.get(i).depth > 4) {
                    if(i != 0) {
                        Node leftNode = valueNodes.get(i - 1);
                        leftNode.value += valueNodes.get(i).value;
                    }
                    if(i != valueNodes.size() - 2) {
                        Node rightNode = valueNodes.get(i + 2);
                        rightNode.value += valueNodes.get(i + 1).value;
                    }

                    Node parent = valueNodes.get(i).parent;
                    parent.left = null;
                    parent.right = null;
                    parent.value = 0;

                    return true;
                }
            }
        }

        return false;
    }

    private void dfs(Node number, List<Node> order) {
        if(number == null) {
            return;
        }

        dfs(number.left, order);
        if(number.left == null && number.right == null) {
            order.add(number);
        }
        dfs(number.right, order);
    }

    private void increaseDepth(Node number) {
        if(number == null) {
            return;
        }

        number.depth += 1;
        increaseDepth(number.left);
        increaseDepth(number.right);
    }
    private Node parseNumber(String line, int depth) {
        if(line.length() == 1) {
            return new Node(null, null, null, depth, Integer.parseInt("" + line.charAt(0)));
        }
        if(line.length() == 3) {
            Node parent = new Node(null, null, null, depth, -1);
            Node left = new Node(parent, null, null, depth + 1, Integer.parseInt("" + line.charAt(0)));
            Node right = new Node(parent, null, null, depth + 1, Integer.parseInt("" + line.charAt(2)));
            parent.left = left;
            parent.right = right;
            return parent;
        }

        int lineIndex = 0;
        int openingBrackets = 0;
        do {
            if (line.charAt(lineIndex) == '[') {
                openingBrackets++;
            } else if (line.charAt(lineIndex) == ']') {
                openingBrackets--;
            }

            lineIndex++;
        } while (openingBrackets != 1 || line.charAt(lineIndex) != ',');

        Node left = parseNumber(line.substring(1, lineIndex), depth + 1);
        Node right = parseNumber(line.substring(lineIndex + 1, line.length() - 1), depth + 1);
        Node parent = new Node(null, left, right, depth, -1);
        left.parent = parent;
        right.parent = parent;
        return parent;
    }

    @Override
    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        List<Node> numbers = new ArrayList<>();

        while(!line.equals("END")) {
            numbers.add(parseNumber(line, 0));
            line = sc.nextLine();
        }

        long maxM = Integer.MIN_VALUE;
        Node maxFirst = null;
        Node maxSecond = null;
        for(int i = 0; i < numbers.size(); i++) {
            for(int j = 0; j < numbers.size(); j++) {
                if(i == j) {
                    continue;
                }

                Node first = numbers.get(i).deepCopy();
                Node second = numbers.get(j).deepCopy();

                increaseDepth(first);
                increaseDepth(second);

                Node total = new Node(null, first, second, 0, -1);
                first.parent = total;
                second.parent = total;
                reduce(total);
                long m = magnitude(total);
                maxM = Math.max(m, maxM);
            }
        }

        return maxM;
    }
}

class Node {
    Node parent;
    Node left;
    Node right;
    int depth;
    int value;

    public Node(Node parent, Node left, Node right, int depth, int value) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.depth = depth;
        this.value = value;
    }

    public Node deepCopy() {
        Node root = new Node(null, null, null, depth, value);
        if(left != null) {
            Node newLeft = left.deepCopy();
            root.left = newLeft;
            newLeft.parent = root;
        }
        if(right != null) {
            Node newRight = right.deepCopy();
            root.right = newRight;
            newRight.parent = root;
        }

        return root;
    }
}