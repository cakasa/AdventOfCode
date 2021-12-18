package AoC_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day15 implements Day{

    private static File file = new File("src/AoC_2021/input/day_15/big.txt");

    @Override
    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        List<List<Integer>> ints = new ArrayList<>();
        while(!line.equals("END")){
            char[] digits = line.toCharArray();
            List<Integer> currentList = new ArrayList<>();
            for(int i = 0; i < digits.length; i++) {
                currentList.add(Integer.parseInt("" + digits[i]));
            }
            ints.add(currentList);
            line = sc.nextLine();
        }

        int n = ints.size();
        int m = ints.get(0).size();

        int[][] memo = new int[n+2][m+2];
        memo[n+1][1] = Integer.MAX_VALUE;
        memo[1][m+1] = Integer.MAX_VALUE;

        for(int i = 2; i <= n; i++) {
            memo[0][i] = Integer.MAX_VALUE;
            memo[n + 1][i] = Integer.MAX_VALUE;
            memo[i][0] = Integer.MAX_VALUE;
            memo[i][m + 1] = Integer.MAX_VALUE;
        }

        boolean isThereChange = true;
        while(isThereChange) {
            isThereChange = false;
            for(int r = 1; r<= n; r++) {
                for(int c = 1; c <= m; c++) {
                    int prev = memo[r][c];
                    memo[r][c] = ints.get(r - 1).get(c - 1);
                    memo[r][c] += Math.min(
                            Math.min(memo[r - 1][c], memo[r + 1][c]),
                            Math.min(memo[r][c - 1], memo[r][c + 1])
                    );

                    if(prev != memo[r][c]) {
                        isThereChange = true;
                    };
                }
            }
        }

        return memo[n][m] - ints.get(0).get(0);
    }

    @Override
    public long part2() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String line = sc.nextLine();
        List<List<Integer>> ints = new ArrayList<>();
        while(!line.equals("END")){
            char[] digits = line.toCharArray();
            List<Integer> currentList = new ArrayList<>();
            for(int i = 0; i < digits.length; i++) {
                currentList.add(Integer.parseInt("" + digits[i]));
            }
            ints.add(currentList);
            line = sc.nextLine();
        }
        int n = ints.size();
        int m = ints.get(0).size();

        for(int r = 0; r < n ; r++) {
            List<Integer> row = ints.get(r);
            for(int i = 1; i <= 4; i++) {
                for(int c = 0; c < m; c++) {
                    row.add(row.get(c) + i > 9 ? (row.get(c) + i) % 10 + 1: row.get(c) + i);
                }
            }
        }

        int newM = ints.get(0).size();
        for(int i = 0; i < 4; i++) {
            for(int r = 0; r < n; r++) {
                List<Integer> row = ints.get(i*n + r);
                List<Integer> newRow = new ArrayList<>();
                for(int c = 0; c < newM; c++) {
                    newRow.add(row.get(c) + 1 > 9 ? 1 : row.get(c) + 1);
                }
                ints.add(newRow);
            }
        }

        n = ints.size();
        m = ints.get(0).size();

        int[][] memo = new int[n+2][m+2];
        memo[n+1][1] = Integer.MAX_VALUE;
        memo[1][m+1] = Integer.MAX_VALUE;

        for(int i = 2; i <= n; i++) {
            memo[0][i] = Integer.MAX_VALUE;
            memo[n + 1][i] = Integer.MAX_VALUE;
            memo[i][0] = Integer.MAX_VALUE;
            memo[i][m + 1] = Integer.MAX_VALUE;
        }

        boolean isThereChange = true;
        while(isThereChange) {
            isThereChange = false;
            for(int r = 1; r<= n; r++) {
                for(int c = 1; c <= m; c++) {
                    int prev = memo[r][c];
                    memo[r][c] = ints.get(r - 1).get(c - 1);
                    memo[r][c] += Math.min(
                            Math.min(memo[r - 1][c], memo[r + 1][c]),
                            Math.min(memo[r][c - 1], memo[r][c + 1])
                    );

                    if(prev != memo[r][c]) {
                        isThereChange = true;
                    };
                }
            }
        }

        return memo[n][m] - ints.get(0).get(0);
    }
}
