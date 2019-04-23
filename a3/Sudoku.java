package cs445.a3;

import java.util.List;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sudoku {
    private static int[][] original = new int[9][9];
    private static int[][] test = new int[9][9];
    private static int oldRow;
    private static int oldCol;


    static boolean isFullSolution(int[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean reject(int[][] board, int row, int col) {
        if(row == -1) {
            return false;
        }
        if(isValidRowAndCol(board, board[row][col], row, col) && !isValidRegion(board, board[row][col], row, col)) {
            return false;
        }
        return true;
    }

    static boolean isValidRowAndCol(int[][] board, int val, int row, int col) {
        //check if duplicate vals in row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val && i != col) {
                return false;
            }
        }
        //check if duplicate vals in col
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val && i != row) {
                return false;
            }
        }
        return true;
    }

    static boolean isValidRegion(int[][] board, int val, int row, int col) {
        //check if duplicate vals in region
        int regionRowStart = (row / 3) * 3;
        int regionRowEnd = regionRowStart + 3;
        int regionColStart = (col / 3) * 3;
        int regionColEnd = regionColStart + 3;
        for(int i = regionRowStart; i < regionRowEnd; i++) {
            for(int j = regionColStart; j < regionColEnd; j++) {
                if((i != row) && (j != col) && (board[i][j] == val)) {
                    return true;
                }
            }
        }
        return false;
    }

    static int[][] extend(int[][] board) {
        int[][] temp = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                temp[i][j] = board[i][j];
            }
        }
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(temp[i][j] == 0) {
                    temp[i][j] = 1;
                    oldRow = i;
                    oldCol = j;
                    return temp;
                }
            }
        }
        return null;
    }

    static int[][] next(int[][] board, int row, int col) {
        if(board[row][col] < 9) {
            board[row][col] += 1;
            return board;
        }
        return null;
    }

    static void testIsFullSolution() {
        int[][] solved = new int[][] {
                {3, 6, 1, 7, 9, 4, 2, 5, 8},
                {2, 8, 4, 5, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 1, 4, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        printBoard(solved);
        System.out.println("Solved isFullSolution Status: " + isFullSolution(solved));

        int[][] unsolved = new int[][] {
                {3, 6, 1, 7, 9, 4, 2, 5, 8},
                {3, 8, 4, 0, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 1, 4, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        printBoard(unsolved);
        System.out.println("Unsolved isFullSolution Status: " + isFullSolution(unsolved));
    }

    static void testReject() {
        int[][] sameRow = new int[][] {
                {8, 8, 1, 7, 9, 4, 2, 5, 8},
                {2, 8, 4, 5, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 1, 4, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        printBoard(sameRow);
        System.out.println("Same Row Reject Status: " + reject(sameRow, 0, 1));

        int[][] sameCol = new int[][] {
                {3, 6, 1, 7, 9, 4, 2, 5, 8},
                {3, 8, 4, 5, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 1, 4, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        printBoard(sameCol);
        System.out.println("Same Col Reject Status: " + reject(sameCol, 1, 0));

        int[][] sameRegion = new int[][] {
                {1, 1, 1, 7, 9, 4, 2, 5, 8},
                {1, 1, 1, 5, 6, 3, 1, 9, 7},
                {1, 1, 1, 8, 2, 1, 4, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        printBoard(sameRegion);
        System.out.println("Same Region Reject Status: " + reject(sameRegion, 2, 2));
    }

    static void testExtend() {
        int[][] empty = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println("Empty Before Extend");
        printBoard(empty);
        System.out.println("Empty After Extend");
        printBoard((extend(empty)));

        int[][] full = new int[][] {
                {3, 6, 1, 7, 9, 4, 2, 5, 8},
                {3, 8, 4, 5, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 1, 4, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        System.out.println("Full Before Extend");
        printBoard(full);
        System.out.println("Full After Extend");
        printBoard(extend(empty));
    }

    static void testNext() {
        int[][] nextNormal = new int[][] {
                {3, 6, 1, 7, 9, 4, 2, 5, 8},
                {3, 8, 4, 5, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 1, 0, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        System.out.println("Before Next: ");
        printBoard(nextNormal);
        System.out.println("After Next: ");
        printBoard(next(nextNormal, 2, 6));

        int[][] nextMax = new int[][] {
                {3, 6, 1, 7, 9, 4, 2, 5, 8},
                {3, 8, 4, 5, 6, 3, 1, 9, 7},
                {7, 9, 5, 8, 2, 9, 0, 6, 3},
                {6, 2, 3, 9, 7, 8, 5, 4, 1},
                {5, 7, 9, 1, 4, 6, 3, 8, 2},
                {1, 4, 8, 3, 5, 2, 9, 7, 6},
                {9, 3, 7, 2, 8, 5, 6, 1, 4},
                {4, 5, 2, 6, 1, 7, 8, 3, 9},
                {8, 1, 6, 4, 3, 9, 7, 2, 5}
        };
        System.out.println("Before Next: ");
        printBoard(nextMax);
        System.out.println("After Next: ");
        printBoard(next(nextMax, 2, 6));
    }

    static void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No assignment");
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----+-----+----");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    static int[][] readBoard(String filename) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } catch (IOException e) {
            return null;
        }
        int[][] board = new int[9][9];
        int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                } catch (Exception e) {
                    val = 0;
                }
                board[i][j] = val;
            }
        }
        return board;
    }

    static int[][] solve(int[][] board, int row, int col) {
        if (reject(board, row, col)) return null;
        if (isFullSolution(board)) return board;
        int[][] attempt = extend(board);
        row = oldRow;
        col = oldCol;
        while (attempt != null) {
            int[][] solution = solve(attempt, row, col);
            if (solution != null) return solution;
            attempt = next(attempt, row, col);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args[0].equals("-t")) {
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else {
            int[][] board = readBoard(args[0]);
            printBoard(board);
            System.out.println("Solution:");
            printBoard(solve(board, -1, -1));
        }
    }
}

