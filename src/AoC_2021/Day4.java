package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {
    private static File file = new File("src/AoC_2021/input/day_4/big.txt");

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(part1());
        System.out.println(part2());
    }

    public static int part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String drawnNumbersString = sc.nextLine();
        String[] drawnNumbersSplit = drawnNumbersString.split(",");
        Map<Integer, List<Board>> boardsToNumbers = new HashMap<>();

        Queue<Integer> drawnNumbers = new LinkedList<>();
        for (var drawnNumber : drawnNumbersSplit) {
            int number = Integer.parseInt(drawnNumber);
            drawnNumbers.add(number);
            boardsToNumbers.put(number, new ArrayList<>());

        }

        while(!sc.nextLine().equals("END")) {
            Board board = new Board();
            int[][] boardNumbers = new int[5][5];
            for(int i = 0; i < 5; i++) {
                List<Integer> rowNumbers =
                        Arrays.stream(sc.nextLine().split(" "))
                        .filter(x -> !x.equals(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                for(int j = 0; j < 5; j++) {
                    int number = rowNumbers.get(j);
                    boardNumbers[i][j] = number;
                    if (boardsToNumbers.containsKey(number)) {
                        List<Board> boardList = boardsToNumbers.get(number);
                        if(!boardList.contains(board)) {
                            boardList.add(board);
                        }
                    }
                }
            }

            board.numbers = boardNumbers;
        }

        while(!drawnNumbers.isEmpty()) {
            int drawnNumber = drawnNumbers.remove();
            List<Board> boardsContainingNumber = boardsToNumbers.get(drawnNumber);
            for(Board board: boardsContainingNumber) {
                board.mark(drawnNumber);
                if(board.isWinner()) {
                    return drawnNumber * board.calculateScore();
                }
            }
        }

        return 0;
    }

    public static int part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        String drawnNumbersString = sc.nextLine();
        String[] drawnNumbersSplit = drawnNumbersString.split(",");
        Map<Integer, List<Board>> boardsToNumbers = new HashMap<>();

        Queue<Integer> drawnNumbers = new LinkedList<>();
        for (var drawnNumber : drawnNumbersSplit) {
            int number = Integer.parseInt(drawnNumber);
            drawnNumbers.add(number);
            boardsToNumbers.put(number, new ArrayList<>());

        }

        int totalBoards = 0;
        while(!sc.nextLine().equals("END")) {
            totalBoards++;
            Board board = new Board();
            int[][] boardNumbers = new int[5][5];
            for(int i = 0; i < 5; i++) {
                List<Integer> rowNumbers =
                        Arrays.stream(sc.nextLine().split(" "))
                                .filter(x -> !x.equals(""))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());

                for(int j = 0; j < 5; j++) {
                    int number = rowNumbers.get(j);
                    boardNumbers[i][j] = number;
                    if (boardsToNumbers.containsKey(number)) {
                        List<Board> boardList = boardsToNumbers.get(number);
                        if(!boardList.contains(board)) {
                            boardList.add(board);
                        }
                    }
                }
            }

            board.numbers = boardNumbers;
        }

        int totalWon = 0;
        boolean isThereAWinner = false;
        while(!drawnNumbers.isEmpty()) {
            int drawnNumber = drawnNumbers.remove();
            List<Board> boardsContainingNumber = boardsToNumbers.get(drawnNumber);
            for(Board board: boardsContainingNumber) {
                if(board.isWinner()){
                    continue;
                }

                board.mark(drawnNumber);
                if(board.isWinner()) {
                    totalWon++;
                }
                if (totalWon == totalBoards) {
                   return drawnNumber * board.calculateScore();
                }
            }
        }

        return 0;
    }
}

class Board {
    public int[][] numbers;
    public boolean[][] marked;

    public Board () {
        marked = new boolean[5][5];
    }

    public void mark(int number) {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers[i].length; j++) {
                if(numbers[i][j] == number) {
                    marked[i][j] = true;
                }
            }
        }
    }

    public boolean isWinner() {
        for(int i = 0; i < numbers.length; i++) {
            boolean rowFull = true;
            for(int j = 0; j < numbers[i].length; j++) {
                if(!marked[i][j]) {
                    rowFull = false;
                    break;
                }
            }
            if(rowFull) {
                return true;
            }
        }

        for(int j = 0; j < numbers[0].length; j++) {
            boolean colFull = true;
            for(int i = 0; i < numbers.length; i++) {
                if(!marked[i][j]) {
                    colFull = false;
                    break;
                }
            }
            if(colFull) {
                return true;
            }
        }

        return false;
    }

    public int calculateScore() {
        int score = 0;
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers[i].length; j++) {
                if(!marked[i][j]) {
                    score += numbers[i][j];
                }
            }
        }

        return score;
    }
}
