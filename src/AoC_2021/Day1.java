package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    private static File file = new File("src/AoC_2021/input/day_1/small.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int prev = Integer.parseInt(sc.nextLine());
        int count = 0;
        while(prev != -1) {
            int current = Integer.parseInt(sc.nextLine());
            if(current > prev) {
                count++;
            }
            prev = current;
        }

        return count;
    }

    public static int part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());
        int prevSum = Integer.MAX_VALUE;
        int count = 0;

        while(c != -1) {
            int currentSum = a + b + c;
            if(currentSum > prevSum) {
                count++;
            }
            prevSum = currentSum;
            a = b;
            b = c;
            c = Integer.parseInt(sc.nextLine());
        }

        return count;

    }
}
