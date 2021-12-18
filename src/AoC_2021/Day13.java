package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day13 implements Day {
    private static File file = new File("src/AoC_2021/input/day_13/small.txt");

    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        List<Point> points = new ArrayList<>();
        while(!line.equals("")) {
            String[] coordinates = line.split(",");
            points.add(new Point(Short.parseShort(coordinates[0]), Short.parseShort(coordinates[1])));
            line = sc.nextLine();
        }

        line = sc.nextLine();
        String[] instructionParts = line.split(" ");
        String[] coordinates = instructionParts[2].split("=");
        boolean isY = coordinates[0].equals("y");
        int amount = Integer.parseInt(coordinates[1]);

        if (isY) {
            for(var point: points) {
                if(point.y > amount) {
                    int diff = point.y - amount;
                    point.y -= 2 * diff;
                }
            }
        } else {
            for(var point: points) {
                if(point.x > amount) {
                    int diff = point.x - amount;
                    point.x -= 2 * diff;
                }
            }
        }

        List<Point> distinct = new ArrayList<>();
        for(var point : points) {
            if(distinct.contains(point)) {
                continue;
            }
            distinct.add(point);
        }

        return distinct.size();
    }

    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        Set<Point> points = new HashSet<>();
        while(!line.equals("")) {
            String[] coordinates = line.split(",");
            points.add(new Point(Short.parseShort(coordinates[0]), Short.parseShort(coordinates[1])));
            line = sc.nextLine();
        }

        line = sc.nextLine();
        while(!line.equals("END")) {
            String[] instructionParts = line.split(" ");
            String[] coordinates = instructionParts[2].split("=");
            boolean isY = coordinates[0].equals("y");
            int amount = Integer.parseInt(coordinates[1]);

            if (isY) {
                for(var point: points) {
                    if(point.y > amount) {
                        int diff = point.y - amount;
                        point.y -= 2 * diff;
                    }
                }
            } else {
                for(var point: points) {
                    if(point.x > amount) {
                        int diff = point.x - amount;
                        point.x -= 2 * diff;
                    }
                }
            }
            line = sc.nextLine();
        }

        List<Point> distinct = new ArrayList<>();
        for(var point : points) {
            if(distinct.contains(point)) {
                continue;
            }
            distinct.add(point);
        }

        int dimX = distinct.stream().map(x -> x.x).max(Comparator.comparingInt(x -> x)).get();
        int dimY = distinct.stream().map(x -> x.y).max(Comparator.comparingInt(x -> x)).get();

        /*for(int i = 0; i <= dimY; i++) {
            for (int j = 0; j <= dimX; j++) {
                System.out.print(distinct.contains(new Point(j, i)) ? "###" : "...");
            }
            System.out.println();
        }*/

        return 0;
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Point o) {
            return x == o.x && y == o.y;
        }
        return false;
    }
}