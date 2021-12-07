package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    private static File file = new File("src/AoC_2021/input/day_7/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Integer> crabs = new ArrayList<>();
        crabs = Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        crabs.sort(Comparator.comparingInt(x -> x));
        double median = 0;
        if(crabs.size() % 2 == 0) {
            median = (crabs.get(crabs.size()/2) + crabs.get(crabs.size()/2 - 1)) / 2.0;
        } else {
            median = crabs.get(crabs.size()/2);
        }

        int floor = (int) Math.floor(median);
        int ceiling = (int) Math.ceil(median);

        int floorDif = 0;
        int ceilingDif = 0;
        for(var crab : crabs) {
            floorDif += Math.abs(crab - floor);
            ceilingDif += Math.abs(crab - ceiling);
        }

        return Math.min(floorDif, ceilingDif);
    }

    public static long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Integer> crabs = new ArrayList<>();
        crabs = Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
        crabs.sort(Comparator.comparingInt(x -> x));

        int min = crabs.get(0);
        int max = crabs.get(crabs.size() - 1);

        long bestPositionFuel = Long.MAX_VALUE;
        for(int i = min; i <= max; i++) {
            long currentPositionFuel = 0;
            for(var crab : crabs) {
                long diff = Math.abs(crab - i);
                currentPositionFuel += (diff * (diff + 1)) / 2;
            }

            if(currentPositionFuel < bestPositionFuel) {
                bestPositionFuel = currentPositionFuel;
            }
        }

        return bestPositionFuel;
    }
}