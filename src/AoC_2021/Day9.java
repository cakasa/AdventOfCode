package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day9 {
    private static File file = new File("src/AoC_2021/input/day_9/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    private static long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        List<List<Byte>> input = new ArrayList<>();
        while(!line.equals("END")) {
            List<Byte> rowVals = Arrays.stream(line.split("")).map(Byte::parseByte).collect(Collectors.toList());
            input.add(rowVals);
            line = sc.nextLine();
        }
        int rows = input.size();
        int cols = input.get(0).size();

        int totalRisk = 0;

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                byte current = input.get(row).get(col);
                byte left, right, up, down;
                try {
                    left = input.get(row).get(col - 1);
                } catch (Exception e) {
                    left = Byte.MAX_VALUE;
                }

                try {
                    right = input.get(row).get(col + 1);
                } catch (Exception e) {
                    right = Byte.MAX_VALUE;
                }

                try {
                    up = input.get(row - 1).get(col);
                } catch (Exception e) {
                    up = Byte.MAX_VALUE;
                }

                try {
                    down = input.get(row + 1).get(col);
                } catch (Exception e) {
                    down = Byte.MAX_VALUE;
                }

                if(current < left && current < right && current < up && current < down) {
                    totalRisk += 1 + current;
                }
            }
        }

        return totalRisk;
    }

    private static long part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        List<List<Byte>> input = new ArrayList<>();
        while(!line.equals("END")) {
            List<Byte> rowVals = Arrays.stream(line.split("")).map(Byte::parseByte).collect(Collectors.toList());
            input.add(rowVals);
            line = sc.nextLine();
        }
        int rows = input.size();
        int cols = input.get(0).size();

        List<int[]> lowPoints = new ArrayList<>();

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                byte current = input.get(row).get(col);
                byte left, right, up, down;
                try {
                    left = input.get(row).get(col - 1);
                } catch (Exception e) {
                    left = Byte.MAX_VALUE;
                }

                try {
                    right = input.get(row).get(col + 1);
                } catch (Exception e) {
                    right = Byte.MAX_VALUE;
                }

                try {
                    up = input.get(row - 1).get(col);
                } catch (Exception e) {
                    up = Byte.MAX_VALUE;
                }

                try {
                    down = input.get(row + 1).get(col);
                } catch (Exception e) {
                    down = Byte.MAX_VALUE;
                }

                if(current < left && current < right && current < up && current < down) {
                    int[] location = {row, col};
                    lowPoints.add(location);
                }
            }

        }

        List<Integer> basinSizes = new ArrayList<>();
        for(var lowPoint : lowPoints) {
            List<int[]> visited = new ArrayList<>();
            int basinSize = findBasinSize(input, lowPoint, visited);
            basinSizes.add(basinSize);
        }

        basinSizes.sort(Comparator.comparingInt(x -> -x));
        return (long) basinSizes.get(0) * (long) basinSizes.get(1) * (long) basinSizes.get(2);
    }

    private static int findBasinSize(List<List<Byte>> input, int[] lowPoint, List<int[]> visited) {
        for(var vis : visited) {
            if (Arrays.equals(lowPoint, vis)) {
                return 0;
            }
        }
        visited.add(lowPoint);
        if(input.get(lowPoint[0]).get(lowPoint[1]) == 9) {
            return 0;
        }

        byte left, right, up, down;
        try {
            left = input.get(lowPoint[0]).get(lowPoint[1] - 1);
        } catch (Exception e) {
            left = Byte.MIN_VALUE;
        }

        try {
            right = input.get(lowPoint[0]).get(lowPoint[1]+ 1);
        } catch (Exception e) {
            right = Byte.MIN_VALUE;
        }

        try {
            up = input.get(lowPoint[0] - 1).get(lowPoint[1]);
        } catch (Exception e) {
            up = Byte.MIN_VALUE;
        }

        try {
            down = input.get(lowPoint[0] + 1).get(lowPoint[1]);
        } catch (Exception e) {
            down = Byte.MIN_VALUE;
        }

        int size = 1;
        byte current = input.get(lowPoint[0]).get(lowPoint[1]);
        if(current < left) {
            int[] point = {lowPoint[0], lowPoint[1] - 1};
            size += findBasinSize(input, point, visited);
        }
        if(current < right) {
            int[] point = {lowPoint[0], lowPoint[1] + 1};
            size += findBasinSize(input, point, visited);
        }
        if(current < up) {
            int[] point = {lowPoint[0] - 1, lowPoint[1]};
            size += findBasinSize(input, point, visited);
        }
        if(current < down) {
            int[] point = {lowPoint[0] + 1, lowPoint[1]};
            size += findBasinSize(input, point, visited);
        }

        return size;
    }
}
