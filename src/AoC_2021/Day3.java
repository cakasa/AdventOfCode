package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    private static File file = new File("src/AoC_2021/input/day_3/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String binary = sc.nextLine();
        int[] columnOnes = new int[binary.length()];

        int count = 0;
        while(!binary.equals("-1")) {
            for(int i = 0; i < binary.length(); i++) {
                columnOnes[i] = (binary.charAt(i) == '1' ? columnOnes[i] + 1: columnOnes[i]);
            }
            count++;
            binary = sc.nextLine();
        }

        int gamma = 0;
        int epsilon = 0;

        for(int i = 0; i < columnOnes.length; i++) {
            int mostCommonBit = (columnOnes[i] > count / 2) ? 1 : 0;
            gamma += (int) Math.pow(2, columnOnes.length - i - 1) * mostCommonBit;
            epsilon += (int) Math.pow(2, columnOnes.length - i - 1) * (1 - mostCommonBit);
        }

        return (long) gamma * (long) epsilon;
    }

    public static long part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        String binary = sc.nextLine();
        int[] columnOnes = new int[binary.length()];
        List<String> rows = new ArrayList<>();

        int count = 0;
        while(!binary.equals("-1")) {
            rows.add(binary);
            for(int i = 0; i < binary.length(); i++) {
                columnOnes[i] = (binary.charAt(i) == '1' ? columnOnes[i] + 1: columnOnes[i]);
            }
            count++;
            binary = sc.nextLine();
        }

        int[] columnOnesOxygenGenerator = Arrays.copyOf(columnOnes, columnOnes.length);
        int[] columnOnesCo2Rating = Arrays.copyOf(columnOnes, columnOnes.length);
        List<String> rowsOxygenGenerator = new ArrayList<>(rows);
        List<String> rowsCo2Rating = new ArrayList<>(rows);

        for(int i = 0; i < columnOnes.length; i++) {
            char mostCommonBit = (columnOnesOxygenGenerator[i] >= (rowsOxygenGenerator.size() + 1) / 2) ? '1' : '0';
            char leastCommonBit = (columnOnesCo2Rating[i] < (rowsCo2Rating.size() + 1) / 2) ? '1' : '0';
            if(rowsOxygenGenerator.size() > 1) {
                List<String> toRemove = new ArrayList<>();
                for(String row : rowsOxygenGenerator) {
                    if(row.charAt(i) != mostCommonBit) {
                        toRemove.add(row);
                        for(int j = 0; j < row.length(); j++) {
                            if(row.charAt(j) == '1') {
                                columnOnesOxygenGenerator[j]--;
                            }
                        }
                    }
                }

                rowsOxygenGenerator.removeAll(toRemove);
            }
            if(rowsCo2Rating.size() > 1) {
                List<String> toRemove = new ArrayList<>();
                for(String row : rowsCo2Rating) {
                    if(row.charAt(i) != leastCommonBit) {
                        toRemove.add(row);
                        for(int j = 0; j < row.length(); j++) {
                            if(row.charAt(j) == '1') {
                                columnOnesCo2Rating[j]--;
                            }
                        }
                    }
                }

                rowsCo2Rating.removeAll(toRemove);
            }
        }

        return (long) Integer.parseInt(rowsOxygenGenerator.get(0), 2)
                * (long) Integer.parseInt(rowsCo2Rating.get(0), 2);
    }
}
