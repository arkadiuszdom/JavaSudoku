package progkomp.zad2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public void solve(final SudokuBoard board) {
        // warunek, zeby nie zerowac w pierwszym wywolaniu/ po inicjalizacji
        if (board.getField(0, 0).getFieldValue() != 0) {
            for (int i = 0; i < board.N; i++) {
                for (int j = 0; j < board.N; j++) {
                    board.setField(i, j, new SudokuField(0));
                }

            }
        }

        int licznik = 0;
        isSolved(board);
    }

    private boolean isSolved(final SudokuBoard board) {
        // opakowanie int
        int[] row = new int[1];
        int[] col = new int[1];

        List<Integer> possibleNumbers = new ArrayList<Integer>();
        for (int i = 1; i <= board.N; i++) {
            possibleNumbers.add(i);
        }
        Collections.shuffle(possibleNumbers);

        if (!isEmptyField(board, row, col)) {
            return true;
            // nie ma pustych ->koniec rekurencji
        }

        for (int i = 0; i < board.N; i++) {
            if (isValid(board, row[0], col[0], possibleNumbers.get(i))) {
                board.setField(row[0], col[0], new SudokuField(possibleNumbers.get(i)));
                // wpisanie podejrzanej liczby

                if (isSolved(board)) {
                    // rekurencja
                    return true;
                }

                board.setField(row[0], col[0], new SudokuField(0));
                // liczba nie byla poprawna
            }
        }
        return false;
        // bedzie cofal sie
    }

    private boolean isValid(final SudokuBoard board, int row, int col, int num) {
        return !(usedInRow(board, row, num) || usedInCol(board, col, num)
                || usedInQuad(board, row - row % board.n, col - col % board.n, num));
        // lewe gorne pole danego kwadratu
    }

    private boolean usedInRow(final SudokuBoard board, int row, int num) {
        for (int col = 0; col < board.N; col++) {
            if (board.getField(row, col).getFieldValue() == num) {
                return true;
            }
        }

        return false;
    }

    private boolean usedInCol(final SudokuBoard board, int col, int num) {
        for (int row = 0; row < board.N; row++) {
            if (board.getField(row, col).getFieldValue() == num) {
                return true;
            }
        }

        return false;
    }

    private boolean usedInQuad(final SudokuBoard board, int row, int col, int num) {
        for (int i = row; i < row + board.n; i++) {
            for (int j = col; j < col + board.n; j++) {
                if (board.getField(i, j).getFieldValue() == num) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isEmptyField(final SudokuBoard board, final int[] row, final int[] col) {
        while (row[0] < board.N) {

            while (col[0] < board.N) {

                if (board.getField(row[0], col[0]).getFieldValue() == 0) {
                    return true;
                }
                col[0]++;
            }
            row[0]++;
            col[0] = 0;
        }
        return false;
    }

}