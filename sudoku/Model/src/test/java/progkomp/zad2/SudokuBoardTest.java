package progkomp.zad2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SudokuBoardTest {

    private boolean checkBoard(SudokuBoard sudoku) {
        int n = sudoku.N;
        int m = sudoku.n;

        for (int i = 0; i < n; i++) {
            if (!sudoku.getColumn(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!sudoku.getRow(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < n; i += m) {
            for (int j = 0; j < n; j += m) {
                if (!sudoku.getBox(i, j).verify()) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean compareBoards(SudokuField board1[][], SudokuField board2[][], int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board1[i][j].getFieldValue() != board2[i][j].getFieldValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testBoard() {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        assertTrue(checkBoard(board));

    }

    

    @Test
    public void testEquals() {

        SudokuBoard board1 = new SudokuBoard();
        SudokuBoard board2 = new SudokuBoard();
        SudokuBoard board3 = new SudokuBoard();
        Object obj = new Object();

        for (int i = 0; i < board1.N; i++) {
            for (int j = 0; j < board1.N; j++) {
                board1.setField(i, j, new SudokuField(i));
                board2.setField(i, j, new SudokuField(j));
                board3.setField(i, j, new SudokuField(i));
            }
        }

        assertFalse(board1.equals(board2));
        assertTrue(board1.equals(board3));
        assertFalse(board1.equals(null));
        assertTrue(board1.equals(board1));
        assertFalse(board1.equals(obj));

    }
    
    @Test
    public void testHashCode()
    {
        SudokuBoard board1 = new SudokuBoard();
        SudokuBoard board2 = new SudokuBoard();
        SudokuBoard board3 = new SudokuBoard();
        Object obj = new Object();

        for (int i = 0; i < board1.N; i++) {
            for (int j = 0; j < board1.N; j++) {
                board1.setField(i, j, new SudokuField(i));
                board2.setField(i, j, new SudokuField(j));
                board3.setField(i, j, new SudokuField(i));
            }
        }

        assertFalse(board1.hashCode() == board2.hashCode());
        assertTrue(board1.hashCode() == board3.hashCode());
        assertTrue(board1.hashCode() == board1.hashCode());
        assertFalse(board1.hashCode() == obj.hashCode());
    }

    @Test
    public void testToString() {
        SudokuBoard board1 = new SudokuBoard();
        String str = "";
        for (int i = 0; i < board1.N; i++) {
            for (int j = 0; j < board1.N; j++) {
                board1.setField(i, j, new SudokuField(i));
                str += i;
                if ((i != board1.N - 1) || (j != board1.N - 1)) {
                    str += " ";
                }

            }
        }

        assertEquals(str, board1.toString());
    }
    @Test
    public void testIdentityBoard() {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);

        SudokuBoard board2 = new SudokuBoard(board.getBoard());
        solver.solve(board);
        assertFalse(board.equals(board2));

    }
    @Test
    public void testCheckBoard() {
        SudokuBoard board1 = new SudokuBoard();
        int numbers[] = new int[] { 2, 4, 7, 8, 1, 9, 6, 5, 3, 3, 8, 9, 2, 5, 6, 4, 7, 1, 1, 5, 6, 4, 3, 7, 8, 2, 9, 7,
                6, 5, 1, 4, 2, 9, 3, 8, 8, 1, 2, 9, 6, 3, 7, 4, 5, 9, 3, 4, 5, 7, 8, 1, 6, 2, 4, 2, 8, 6, 9, 5, 3, 1, 7,
                5, 7, 1, 3, 8, 4, 2, 9, 6, 6, 9, 3, 7, 2, 1, 5, 8, 4 };

        for (int i = 0; i < board1.N; i++) {
            for (int j = 0; j < board1.N; j++) {
                board1.setField(i, j, new SudokuField(numbers[i * board1.N + j]));
            }
        }
        assertTrue(board1.checkBoard());
        board1.setField(0, 0, new SudokuField(3));
        board1.setField(0, 1, new SudokuField(2));

        assertFalse(board1.checkBoard());
        board1.setField(0, 0, new SudokuField(2));
        board1.setField(0, 1, new SudokuField(3));
        
        board1.setField(0, 1, new SudokuField(8));
        board1.setField(1, 1, new SudokuField(3));

        assertFalse(board1.checkBoard());

        board1.setField(0, 1, new SudokuField(2));

        board1.setField(2, 1, new SudokuField(2));
        board1.setField(3, 1, new SudokuField(9));
        board1.setField(3, 4, new SudokuField(2));
        board1.setField(2, 4, new SudokuField(9));
        assertFalse(board1.checkBoard());

    }

}