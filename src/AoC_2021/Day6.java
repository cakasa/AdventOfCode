package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 implements Day {
    private static File file = new File("src/AoC_2021/input/day_6/big.txt");

    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int[] fishDays = new int[9];

        for(int i = 0; i < 9; i++) {
            fishDays[i] = 0;
        }

        List<Integer> fish =
                Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());

        for(var f : fish) {
            fishDays[f]++;
        }

        for(int i = 0; i < 80; i++) {
            int temp = fishDays[0];
            for(int j = 1; j < 9; j++) {
                fishDays[j - 1] = fishDays[j];
            }
            fishDays[6] += temp;
            fishDays[8] = temp;
        }

        return Arrays.stream(fishDays).sum();
    }

    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        long[] fishDays = new long[9];

        for(int i = 0; i < 9; i++) {
            fishDays[i] = 0L;
        }

        List<Integer> fish =
                Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());

        for(var f : fish) {
            fishDays[f]++;
        }

        for(int i = 0; i < 256; i++) {
            long temp = fishDays[0];
            for(int j = 1; j < 9; j++) {
                fishDays[j - 1] = fishDays[j];
            }
            fishDays[6] += temp;
            fishDays[8] = temp;
        }

        return Arrays.stream(fishDays).sum();
    }
}