package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    private static File file = new File("src/AoC_2021/input/day_8/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    private static long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        int[] counts = new int[10];
        while(!line.equals("END")) {
            String[] wiresAndDigits = line.split(" \\| ");
            String[] wires = wiresAndDigits[0].split(" ");
            String[] digits = wiresAndDigits[1].split(" ");

            for(int i = 0; i < wires.length; i++) {
                char[] currentWireCharArray = wires[i].toCharArray();
                Arrays.sort(currentWireCharArray);
                wires[i] = new String(currentWireCharArray);
            }

            for(int i = 0; i < digits.length; i++) {
                char[] currentDigitCharArray = digits[i].toCharArray();
                Arrays.sort(currentDigitCharArray);
                digits[i] = new String(currentDigitCharArray);
            }

            Arrays.sort(wires, Comparator.comparingInt(String::length));

            Map<String, Integer> wireToInt = new HashMap<>();
            wireToInt.put(wires[0], 1);
            wireToInt.put(wires[1], 7);
            wireToInt.put(wires[2], 4);
            wireToInt.put(wires[9], 8);
            for(int i = 3; i < 9; i++) {
                String currentWire = wires[i];
                if(currentWire.length() == 5) {
                    if(currentWire.contains("" + wires[0].charAt(0)) && currentWire.contains("" + wires[0].charAt(1))) {
                        wireToInt.put(wires[i], 3);
                        continue;
                    }

                    int alsoAppearInFour = 0;
                    for(int j = 0; j < 4; j++) {
                        if(currentWire.contains("" + wires[2].charAt(j))) {
                            alsoAppearInFour++;
                        }
                    }
                    if(alsoAppearInFour == 2) {
                        wireToInt.put(wires[i], 2);
                        continue;
                    }

                    wireToInt.put(wires[i], 5);

                    assert alsoAppearInFour == 3;
                    continue;
                }

                int alsoAppearInOne = 0;
                if(currentWire.contains("" + wires[0].charAt(0))) {
                    alsoAppearInOne++;
                }

                if(currentWire.contains("" + wires[0].charAt(1))) {
                    alsoAppearInOne++;
                }

                if(alsoAppearInOne == 1) {
                    wireToInt.put(wires[i], 6);
                    continue;
                }

                int alsoAppearInFour = 0;
                for(int j = 0; j < 4; j++) {
                    if(currentWire.contains("" + wires[2].charAt(j))) {
                        alsoAppearInFour++;
                    }
                }
                if(alsoAppearInFour == 3) {
                    wireToInt.put(wires[i], 0);
                    continue;
                }

                wireToInt.put(wires[i], 9);

                assert alsoAppearInFour == 4;
            }

            for(int i = 0; i < 4; i++) {
                String currentDigit = digits[i];
                counts[wireToInt.get(currentDigit)]++;
            }

            line = sc.nextLine();
        }

        return counts[1] + counts[4] + counts[7] + counts[8];
    }

    private static long part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();

        long sum = 0;
        while(!line.equals("END")) {
            String[] wiresAndDigits = line.split(" \\| ");
            String[] wires = wiresAndDigits[0].split(" ");
            String[] digits = wiresAndDigits[1].split(" ");

            for(int i = 0; i < wires.length; i++) {
                char[] currentWireCharArray = wires[i].toCharArray();
                Arrays.sort(currentWireCharArray);
                wires[i] = new String(currentWireCharArray);
            }

            for(int i = 0; i < digits.length; i++) {
                char[] currentDigitCharArray = digits[i].toCharArray();
                Arrays.sort(currentDigitCharArray);
                digits[i] = new String(currentDigitCharArray);
            }

            Arrays.sort(wires, Comparator.comparingInt(String::length));

            Map<String, Integer> wireToInt = new HashMap<>();
            wireToInt.put(wires[0], 1);
            wireToInt.put(wires[1], 7);
            wireToInt.put(wires[2], 4);
            wireToInt.put(wires[9], 8);
            for(int i = 3; i < 9; i++) {
                String currentWire = wires[i];
                if(currentWire.length() == 5) {
                    if(currentWire.contains("" + wires[0].charAt(0)) && currentWire.contains("" + wires[0].charAt(1))) {
                        wireToInt.put(wires[i], 3);
                        continue;
                    }

                    int alsoAppearInFour = 0;
                    for(int j = 0; j < 4; j++) {
                        if(currentWire.contains("" + wires[2].charAt(j))) {
                            alsoAppearInFour++;
                        }
                    }
                    if(alsoAppearInFour == 2) {
                        wireToInt.put(wires[i], 2);
                        continue;
                    }

                    wireToInt.put(wires[i], 5);

                    assert alsoAppearInFour == 3;
                    continue;
                }

                int alsoAppearInOne = 0;
                if(currentWire.contains("" + wires[0].charAt(0))) {
                    alsoAppearInOne++;
                }

                if(currentWire.contains("" + wires[0].charAt(1))) {
                    alsoAppearInOne++;
                }

                if(alsoAppearInOne == 1) {
                    wireToInt.put(wires[i], 6);
                    continue;
                }

                int alsoAppearInFour = 0;
                for(int j = 0; j < 4; j++) {
                    if(currentWire.contains("" + wires[2].charAt(j))) {
                        alsoAppearInFour++;
                    }
                }
                if(alsoAppearInFour == 3) {
                    wireToInt.put(wires[i], 0);
                    continue;
                }

                wireToInt.put(wires[i], 9);

                assert alsoAppearInFour == 4;
            }

            int current = 0;
            for(int i = 0; i < 4; i++) {
                String currentDigit = digits[i];
                current += Math.pow(10, 3 - i) * wireToInt.get(currentDigit);
            }

            sum += current;
            line = sc.nextLine();
        }

        return sum;
    }
}
