package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day17 implements Day {
    private static File file = new File("src/AoC_2021/input/day_17/big.txt");

    @Override
    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String[] coordinates = sc.nextLine().substring(13).split(", ");
        List<Integer> x = Arrays.stream(coordinates[0].split("=")[1].split("\\.\\.")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> y = Arrays.stream(coordinates[1].split("=")[1].split("\\.\\.")).map(Integer::parseInt).collect(Collectors.toList());
        return (long) (-y.get(0) * (-y.get(0) - 1)) / 2;
    }

    @Override
    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String[] coordinates = sc.nextLine().substring(13).split(", ");
        List<Integer> x = Arrays.stream(coordinates[0].split("=")[1].split("\\.\\.")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> y = Arrays.stream(coordinates[1].split("=")[1].split("\\.\\.")).map(Integer::parseInt).collect(Collectors.toList());
        long pairs = 0;
        for(int testX = 1; testX <= x.get(1); testX++) {
            for (int testY = -96; testY <= 100; testY++) {
                int step = 1;
                while(true) {
                    int currentX = step * testX - (step * (step - 1)) / 2;
                    if(step > testX) {
                        currentX = (testX * (testX + 1)) / 2;
                    }

                    int currentY = step * testY - (step * (step - 1)) / 2;

                    if(currentX > x.get(1) || currentY < y.get(0)) {
                        break;
                    }

                    if(currentX >= x.get(0) && currentX <= x.get(1) && currentY >= y.get(0) && currentY <= y.get(1)) {
                        pairs++;
                        break;
                    }

                    step++;
                }
            }
        }

        return pairs;
    }
}