package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    private static File file = new File("src/AoC_2021/input/day_2/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        long horizontal = 0;
        long depth = 0;
        String instruction = sc.nextLine();
        while(!instruction.equals("-1")) {
            String dir = instruction.split(" ")[0];
            int amount = Integer.parseInt(instruction.split(" ")[1]);
            switch (dir) {
                case "forward" -> horizontal += amount;
                case "up" -> depth -= amount;
                case "down" -> depth += amount;
            }
            instruction = sc.nextLine();
        }

        return horizontal * depth;
    }

    public static long part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);

        long horizontal = 0;
        long depth = 0;
        long aim = 0;

        String instruction = sc.nextLine();
        while(!instruction.equals("-1")) {
            String dir = instruction.split(" ")[0];
            int amount = Integer.parseInt(instruction.split(" ")[1]);
            switch (dir) {
                case "forward" -> {
                    horizontal += amount;
                    depth += aim * amount;
                }
                case "up" -> aim -= amount;
                case "down" -> aim += amount;
            }
            instruction = sc.nextLine();
        }

        return horizontal * depth;
    }
}
