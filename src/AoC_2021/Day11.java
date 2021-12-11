package AoC_2021;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 implements Day{
    private static File file = new File("src/AoC_2021/input/day_11/big.txt");
    public long part1() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        byte[][] octopuses = new byte[12][12];

        for (int i = 1; i <= 10; i++) {
            char[] line = sc.nextLine().toCharArray();
            for(int j = 1; j <= 10; j++) {
                octopuses[i][j] = Byte.parseByte("" + line[j - 1]);
            }
        }

        int flashes = 0;
        int iterations = 1;
        while(iterations <= 100) {
            iterations++;


            for(int i = 1; i<= 10; i++) {
                for(int j = 1; j<= 10; j++) {
                    octopuses[i][j]++;
                }
            }

            int numberOfOverNines = 0;
            for(int i = 1; i <= 10; i++) {
                for (int j = 1; j<= 10; j++) {
                    if(octopuses[i][j] > 9) {
                        numberOfOverNines++;
                    }
                }
            }

            boolean[][] flashed = new boolean[12][12];
            while(numberOfOverNines > 0) {
                for(int i = 1; i<= 10; i++) {
                    for(int j = 1; j<= 10; j++) {
                        if(octopuses[i][j] > 9 && !flashed[i][j]) {
                            octopuses[i][j] = 0;
                            flashes++;
                            flashed[i][j] = true;

                            incrementFlash(octopuses, flashed, i-1, j-1);
                            incrementFlash(octopuses, flashed, i, j-1);
                            incrementFlash(octopuses, flashed, i+1, j-1);
                            incrementFlash(octopuses, flashed, i-1, j);
                            incrementFlash(octopuses, flashed, i, j);
                            incrementFlash(octopuses, flashed, i+1, j);
                            incrementFlash(octopuses, flashed, i-1, j+1);
                            incrementFlash(octopuses, flashed, i, j+1);
                            incrementFlash(octopuses, flashed, i+1, j+1);
                        }
                    }
                }

                numberOfOverNines = 0;
                for(int i = 1; i <= 10; i++) {
                    for (int j = 1; j<= 10; j++) {
                        if(octopuses[i][j] > 9) {
                            numberOfOverNines++;
                        }
                    }
                }
            }
        }

        return flashes;
    }

    public long part2() throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        byte[][] octopuses = new byte[12][12];

        for (int i = 1; i <= 10; i++) {
            char[] line = sc.nextLine().toCharArray();
            for(int j = 1; j <= 10; j++) {
                octopuses[i][j] = Byte.parseByte("" + line[j - 1]);
            }
        }

        int flashes = 0;
        int iterations = 0;
        while(true) {
            iterations++;

            for(int i = 1; i<= 10; i++) {
                for(int j = 1; j<= 10; j++) {
                    octopuses[i][j]++;
                }
            }

            int numberOfOverNines = 0;
            for(int i = 1; i <= 10; i++) {
                for (int j = 1; j<= 10; j++) {
                    if(octopuses[i][j] > 9) {
                        numberOfOverNines++;
                    }
                }
            }

            boolean[][] flashed = new boolean[12][12];
            while(numberOfOverNines > 0) {
                for(int i = 1; i<= 10; i++) {
                    for(int j = 1; j<= 10; j++) {
                        if(octopuses[i][j] > 9 && !flashed[i][j]) {
                            octopuses[i][j] = 0;
                            flashes++;
                            flashed[i][j] = true;

                            incrementFlash(octopuses, flashed, i-1, j-1);
                            incrementFlash(octopuses, flashed, i, j-1);
                            incrementFlash(octopuses, flashed, i+1, j-1);
                            incrementFlash(octopuses, flashed, i-1, j);
                            incrementFlash(octopuses, flashed, i, j);
                            incrementFlash(octopuses, flashed, i+1, j);
                            incrementFlash(octopuses, flashed, i-1, j+1);
                            incrementFlash(octopuses, flashed, i, j+1);
                            incrementFlash(octopuses, flashed, i+1, j+1);
                        }
                    }
                }

                numberOfOverNines = 0;
                for(int i = 1; i <= 10; i++) {
                    for (int j = 1; j<= 10; j++) {
                        if(octopuses[i][j] > 9) {
                            numberOfOverNines++;
                        }
                    }
                }
            }

            boolean shouldContinue = false;
            for(int i = 1; i <= 10; i++) {
                for(int j = 1; j <= 10; j++) {
                    if(octopuses[i][j] != 0) {
                        shouldContinue = true;
                        break;
                    }
                }
                if(shouldContinue) {
                    break;
                }
            }

            if(!shouldContinue) {
                break;
            }
        }

        return iterations;
    }

    private void incrementFlash(byte[][] octopuses, boolean[][] flashed, int i, int j) {
        if(!flashed[i][j]) {
            octopuses[i][j]++;
        }
    }
}
