package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day14 implements Day{

    private static File file = new File("src/AoC_2021/input/day_14/big.txt");

    @Override
    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String chain = sc.nextLine();
        Map<String, Long> chainPairs = new HashMap<>();
        for(int i = 0; i < chain.length() - 1; i++) {
            String currentPair = "" + chain.charAt(i) + chain.charAt(i+1);
            if(!chainPairs.containsKey(currentPair)) {
                chainPairs.put(currentPair, 0L);
            }
            chainPairs.put(currentPair, chainPairs.get(currentPair) + 1);
        }

        Map<String, Character> reactions = new HashMap<>();
        sc.nextLine();
        chain = sc.nextLine();
        while(!chain.equals("END")) {
            String[] arg = chain.split(" -> ");
            reactions.put(arg[0], arg[1].charAt(0));
            chain = sc.nextLine();
        }

        int nSteps = 10;
        int currentStep = 1;
        while(currentStep <= nSteps) {
            Map<String, Long> newChainPairs = new HashMap<>();

            for(var pair : chainPairs.keySet()) {
                long count = chainPairs.get(pair);
                char introducedLetter = reactions.get(pair);

                String newChain1 = "" + pair.charAt(0) + introducedLetter;
                String newChain2 = "" + introducedLetter + pair.charAt(1);
                if(!newChainPairs.containsKey(newChain1)) {
                    newChainPairs.put(newChain1, 0L);
                }
                newChainPairs.put(newChain1, newChainPairs.get(newChain1) + count);
                if(!newChainPairs.containsKey(newChain2)) {
                    newChainPairs.put(newChain2, 0L);
                }
                newChainPairs.put(newChain2, newChainPairs.get(newChain2) + count);
            }

            chainPairs = newChainPairs;
            currentStep++;
        }

        Map<Character, Long> characters = new HashMap<>();
        for(var pair : chainPairs.keySet()) {
            long count = chainPairs.get(pair);

            if(!characters.containsKey(pair.charAt(0))) {
                characters.put(pair.charAt(0), 0L);
            }
            characters.put(pair.charAt(0), characters.get(pair.charAt(0)) + count);

            if(!characters.containsKey(pair.charAt(1))) {
                characters.put(pair.charAt(1), 0L);
            }
            characters.put(pair.charAt(1), characters.get(pair.charAt(1)) + count);
        }

        long mostCommon = (long) Math.ceil(characters.values().stream().max(Comparator.comparingLong(x -> x)).get() / 2.0);
        long leastCommon = (long) Math.ceil (characters.values().stream().min(Comparator.comparingLong(x -> x)).get() / 2.0);
        return mostCommon - leastCommon;
    }

    @Override
    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String chain = sc.nextLine();
        Map<String, Long> chainPairs = new HashMap<>();
        for(int i = 0; i < chain.length() - 1; i++) {
            String currentPair = "" + chain.charAt(i) + chain.charAt(i+1);
            if(!chainPairs.containsKey(currentPair)) {
                chainPairs.put(currentPair, 0L);
            }
            chainPairs.put(currentPair, chainPairs.get(currentPair) + 1);
        }

        Map<String, Character> reactions = new HashMap<>();
        sc.nextLine();
        chain = sc.nextLine();
        while(!chain.equals("END")) {
            String[] arg = chain.split(" -> ");
            reactions.put(arg[0], arg[1].charAt(0));
            chain = sc.nextLine();
        }

        int nSteps = 40;
        int currentStep = 1;
        while(currentStep <= nSteps) {
            Map<String, Long> newChainPairs = new HashMap<>();

            for(var pair : chainPairs.keySet()) {
                long count = chainPairs.get(pair);
                char introducedLetter = reactions.get(pair);

                String newChain1 = "" + pair.charAt(0) + introducedLetter;
                String newChain2 = "" + introducedLetter + pair.charAt(1);
                if(!newChainPairs.containsKey(newChain1)) {
                    newChainPairs.put(newChain1, 0L);
                }
                newChainPairs.put(newChain1, newChainPairs.get(newChain1) + count);
                if(!newChainPairs.containsKey(newChain2)) {
                    newChainPairs.put(newChain2, 0L);
                }
                newChainPairs.put(newChain2, newChainPairs.get(newChain2) + count);
            }

            chainPairs = newChainPairs;
            currentStep++;
        }

        Map<Character, Long> characters = new HashMap<>();
        for(var pair : chainPairs.keySet()) {
            long count = chainPairs.get(pair);

            if(!characters.containsKey(pair.charAt(0))) {
                characters.put(pair.charAt(0), 0L);
            }
            characters.put(pair.charAt(0), characters.get(pair.charAt(0)) + count);

            if(!characters.containsKey(pair.charAt(1))) {
                characters.put(pair.charAt(1), 0L);
            }
            characters.put(pair.charAt(1), characters.get(pair.charAt(1)) + count);
        }

        long mostCommon = (long) Math.ceil(characters.values().stream().max(Comparator.comparingLong(x -> x)).get() / 2.0);
        long leastCommon = (long) Math.ceil (characters.values().stream().min(Comparator.comparingLong(x -> x)).get() / 2.0);
        return mostCommon - leastCommon;
    }
}
