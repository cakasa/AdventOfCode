package AoC_2021;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day16 implements Day {
    private static File file = new File("src/AoC_2021/input/day_16/big.txt");
    private static List<Long> versionNums = new ArrayList<>();

    @Override
    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String hexadecimalString = sc.nextLine();
        StringBuilder binaryStringBulder = new StringBuilder();

        Map<Character, String> binaryConverter = new HashMap<>();
        binaryConverter.put('0', "0000");
        binaryConverter.put('1', "0001");
        binaryConverter.put('2', "0010");
        binaryConverter.put('3', "0011");
        binaryConverter.put('4', "0100");
        binaryConverter.put('5', "0101");
        binaryConverter.put('6', "0110");
        binaryConverter.put('7', "0111");
        binaryConverter.put('8', "1000");
        binaryConverter.put('9', "1001");
        binaryConverter.put('A', "1010");
        binaryConverter.put('B', "1011");
        binaryConverter.put('C', "1100");
        binaryConverter.put('D', "1101");
        binaryConverter.put('E', "1110");
        binaryConverter.put('F', "1111");

        for(int i = 0; i < hexadecimalString.length(); i++) {
            binaryStringBulder.append(binaryConverter.get(hexadecimalString.charAt(i)));
        }

        parseBlock(binaryStringBulder.toString(), 0);
        return versionNums.stream().mapToLong(x -> x).sum();
    }

    public Pair parseBlock(String binaryString, int currentChar) {
        long version = Long.parseLong(binaryString.substring(currentChar, currentChar + 3), 2);
        versionNums.add(version);

        long typeId = Long.parseLong(binaryString.substring(currentChar + 3, currentChar + 6), 2);
        currentChar += 6;

        if(typeId == 4) {
            return parseLiteral(binaryString, currentChar);
        }

        return parseOperation(binaryString, currentChar, typeId);
    }

    public Pair parseOperation(String binaryString, int currentChar, long typeId) {
        boolean lengthIsInBits = binaryString.charAt(currentChar++) == '0';
        List<Long> values = new ArrayList<>();
        if(lengthIsInBits) {
            long totalBits = Long.parseLong(binaryString.substring(currentChar, currentChar + 15), 2);
            currentChar += 15;
            long totalBitsEnd = totalBits + currentChar;
            while(currentChar < totalBitsEnd) {
                Pair pair = parseBlock(binaryString, currentChar);
                currentChar = pair.index;
                values.add(pair.value);
            }
        } else {
            long totalBlocks = Long.parseLong(binaryString.substring(currentChar, currentChar + 11), 2);
            currentChar += 11;
            for(long i = 0; i < totalBlocks; i++) {
                Pair pair = parseBlock(binaryString, currentChar);
                currentChar = pair.index;
                values.add(pair.value);
            }
        }

        if(typeId == 0) {
            long sum = values.stream().mapToLong(x -> x).sum();
            return new Pair(sum, currentChar);
        } else if (typeId == 1) {
            long prod = values.stream().reduce(1L, (x, y) -> x * y);
            return new Pair(prod, currentChar);
        } else if(typeId == 2) {
            long min = values.stream().mapToLong(x -> x).min().getAsLong();
            return new Pair(min, currentChar);
        } else if(typeId == 3) {
            long max = values.stream().mapToLong(x -> x).max().getAsLong();
            return new Pair(max, currentChar);
        } else if(typeId == 5) {
            long bool = values.get(0) > values.get(1) ? 1 : 0;
            return new Pair(bool, currentChar);
        } else if(typeId == 6) {
            long bool = values.get(0) < values.get(1) ? 1 : 0;
            return new Pair(bool, currentChar);
        }

        return new Pair(values.get(0).equals(values.get(1)) ? 1 : 0, currentChar);
    }

    public Pair parseLiteral(String binaryString, int currentChar) {
        boolean stopOnNextRun = binaryString.charAt(currentChar++) == '0';
        StringBuilder literal = new StringBuilder();
        while(!stopOnNextRun) {
            literal.append(binaryString.substring(currentChar, currentChar + 4));
            currentChar += 4;
            stopOnNextRun = binaryString.charAt(currentChar++) == '0';
        }

        literal.append(binaryString.substring(currentChar, currentChar + 4));
        currentChar += 4;
        long literalValue = Long.parseLong(literal.toString(), 2);
        return new Pair(literalValue, currentChar);
    }



    @Override
    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String hexadecimalString = sc.nextLine();
        StringBuilder binaryStringBulder = new StringBuilder();

        Map<Character, String> binaryConverter = new HashMap<>();
        binaryConverter.put('0', "0000");
        binaryConverter.put('1', "0001");
        binaryConverter.put('2', "0010");
        binaryConverter.put('3', "0011");
        binaryConverter.put('4', "0100");
        binaryConverter.put('5', "0101");
        binaryConverter.put('6', "0110");
        binaryConverter.put('7', "0111");
        binaryConverter.put('8', "1000");
        binaryConverter.put('9', "1001");
        binaryConverter.put('A', "1010");
        binaryConverter.put('B', "1011");
        binaryConverter.put('C', "1100");
        binaryConverter.put('D', "1101");
        binaryConverter.put('E', "1110");
        binaryConverter.put('F', "1111");

        for(int i = 0; i < hexadecimalString.length(); i++) {
            binaryStringBulder.append(binaryConverter.get(hexadecimalString.charAt(i)));
        }

        return parseBlock(binaryStringBulder.toString(), 0).value;
    }
}

class Pair {
    long value;
    int index;

    public Pair(long value, int index) {
        this.value = value;
        this.index = index;
    }
}
