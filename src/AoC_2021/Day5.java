package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5 {
    private static File file = new File("src/AoC_2021/input/day_5/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        short[][] map = new short[1000][1000];

        while(!line.equals("END")) {
            String[] pointStrings = line.split(" -> ");
            String[] point1String = pointStrings[0].split(",");
            int startX = Integer.parseInt(point1String[0]);
            int startY = Integer.parseInt(point1String[1]);
            String[] point2String = pointStrings[1].split(",");
            int endX = Integer.parseInt(point2String[0]);
            int endY = Integer.parseInt(point2String[1]);

            int deltaX = 0;
            if(endX - startX != 0) {
                deltaX = (endX - startX) / Math.abs(endX - startX);
            }

            int deltaY = 0;
            if(endY - startY != 0) {
                deltaY = (endY - startY) / Math.abs(endY - startY);
            }

            if(deltaX != 0 && deltaY != 0) {
                line = sc.nextLine();
                continue;
            }

            int currentX = startX;
            int currentY = startY;
            while(currentX != endX || currentY != endY) {
                map[currentX][currentY]++;
                currentX += deltaX;
                currentY += deltaY;
            }
            map[endX][endY]++;

            line = sc.nextLine();
        }

        int count = 0;
        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                if(map[i][j] >= 2) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        short[][] map = new short[1000][1000];

        while(!line.equals("END")) {
            String[] pointStrings = line.split(" -> ");
            String[] point1String = pointStrings[0].split(",");
            int startX = Integer.parseInt(point1String[0]);
            int startY = Integer.parseInt(point1String[1]);
            String[] point2String = pointStrings[1].split(",");
            int endX = Integer.parseInt(point2String[0]);
            int endY = Integer.parseInt(point2String[1]);

            int deltaX = 0;
            if(endX - startX != 0) {
                deltaX = (endX - startX) / Math.abs(endX - startX);
            }

            int deltaY = 0;
            if(endY - startY != 0) {
                deltaY = (endY - startY) / Math.abs(endY - startY);
            }

            int currentX = startX;
            int currentY = startY;
            while(currentX != endX || currentY != endY) {
                map[currentX][currentY]++;
                currentX += deltaX;
                currentY += deltaY;
            }
            map[endX][endY]++;

            line = sc.nextLine();
        }

        int count = 0;
        for(int i = 0; i < 1000; i++) {
            for(int j = 0; j < 1000; j++) {
                if(map[i][j] >= 2) {
                    count++;
                }
            }
        }

        return count;
    }
}