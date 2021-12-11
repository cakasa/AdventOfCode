package AoC_2021;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 implements Day{
    private static File file = new File("src/AoC_2021/input/day_10/big.txt");

    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);

        HashMap<Character, Integer> scores = new HashMap<>();
        scores.put(')', 3);
        scores.put(']', 57);
        scores.put('}', 1197);
        scores.put('>', 25137);

        HashMap<Character, Character> matchingPairs = new HashMap<>();
        matchingPairs.put('(', ')');
        matchingPairs.put('[', ']');
        matchingPairs.put('<', '>');
        matchingPairs.put('{', '}');

        long totalScore = 0;
        String line = sc.nextLine();
        while(!line.equals("END")) {
            char[] chars = line.toCharArray();

            Stack<Character> openingCharacters = new Stack<>();
            for(int i = 0; i < chars.length; i++) {
                if(!scores.containsKey(chars[i])) {
                    openingCharacters.push(chars[i]);
                    continue;
                }
                char opening = openingCharacters.pop();
                char closing = chars[i];

                if(matchingPairs.get(opening) != closing) {
                    totalScore += scores.get(closing);
                    break;
                }
            }

            line = sc.nextLine();
        }

        return totalScore;
    }

    public long part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);

        HashMap<Character, Integer> scores = new HashMap<>();
        scores.put(')', 1);
        scores.put(']', 2);
        scores.put('}', 3);
        scores.put('>', 4);

        HashMap<Character, Character> matchingPairs = new HashMap<>();
        matchingPairs.put('(', ')');
        matchingPairs.put('[', ']');
        matchingPairs.put('<', '>');
        matchingPairs.put('{', '}');

        List<Long> totalScores = new ArrayList<>();
        String line = sc.nextLine();
        while(!line.equals("END")) {
            char[] chars = line.toCharArray();

            boolean isCorrupt = false;
            Stack<Character> openingCharacters = new Stack<>();
            for(int i = 0; i < chars.length; i++) {
                if(!scores.containsKey(chars[i])) {
                    openingCharacters.push(chars[i]);
                    continue;
                }
                char opening = openingCharacters.pop();
                char closing = chars[i];

                if(matchingPairs.get(opening) != closing) {
                    isCorrupt = true;
                    break;
                }
            }

            if(isCorrupt) {
                line = sc.nextLine();
                continue;
            }

            if(openingCharacters.isEmpty()) {
                line = sc.nextLine();
                continue;
            }

            long currentScore = 0;
            while(!openingCharacters.isEmpty()) {
                char currentChar = openingCharacters.pop();
                char closingPair = matchingPairs.get(currentChar);

                currentScore = currentScore * 5 + scores.get(closingPair);
            }
            totalScores.add(currentScore);
            line = sc.nextLine();
        }

        Collections.sort(totalScores);
        return totalScores.get(totalScores.size() / 2);
    }
}
