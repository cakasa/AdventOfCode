import AoC_2021.*;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    public static void main(String[] args) throws FileNotFoundException {
        double totalTime = 0;
        totalTime += executeTask(1, new Day1());
        totalTime += executeTask(2, new Day2());
        totalTime += executeTask(3, new Day3());
        totalTime += executeTask(4, new Day4());
        totalTime += executeTask(5, new Day5());
        totalTime += executeTask(6, new Day6());
        totalTime += executeTask(7, new Day7());
        totalTime += executeTask(8, new Day8());
        totalTime += executeTask(9, new Day9());
        totalTime += executeTask(10, new Day10());
        totalTime += executeTask(11, new Day11());

        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println("Total time: " + df.format(totalTime) + " ms");
    }

    static double executeTask(int dayNumber, Day day) throws FileNotFoundException {
        for(int i = 0; i < 100; i++) {
            day.part1();
        }

        double part1Time = 0;
        for(int i = 0; i < 100; i++) {
            long startTime = System.nanoTime();
            day.part1();
            long endTime = System.nanoTime();
            part1Time += endTime - startTime;
        }
        part1Time /= 100 * 1000000;
        printResult(dayNumber, 1, part1Time);

        for(int i = 0; i < 100; i++) {
            day.part2();
        }

        double part2Time = 0;
        for(int i = 0; i < 100; i++) {
            long startTime = System.nanoTime();
            day.part2();
            long endTime = System.nanoTime();
            part2Time += endTime - startTime;
        }
        part2Time /= 100 * 1000000;

        printResult(dayNumber, 2, part2Time);

        return part1Time + part2Time;
    }

    static void printResult(int day, int part, double duration) {
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println("Day " + day + " Part " + part + ", " + df.format(duration) + " ms");
    }
}
