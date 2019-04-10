package org.View;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import progkomp.zad2.BacktrackingSudokuSolver;
import progkomp.zad2.SudokuBoard;
import progkomp.zad2.SudokuSolver;

public class GameLevelTest {
    
    private int countEmptyFieldsNumber(final SudokuBoard board) {
        int result = 0;
        int n = board.N;
        
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (board.getField(i, j).getFieldValue()==0) {
                    result++;
                }
            }
        }
            
        
        return result;
        
    }
    @Test
    public void testModifyBoard() {
        SudokuBoard board; 
        SudokuSolver solver = new BacktrackingSudokuSolver();
        GameLevel gameLevel;
        
        gameLevel=GameLevel.HIGH;
        board = new SudokuBoard();
        solver.solve(board);
        gameLevel.modifyBoard(board);
        assertEquals(20,countEmptyFieldsNumber(board));  
        
        gameLevel=GameLevel.MEDIUM;
        board = new SudokuBoard();
        solver.solve(board);
        gameLevel.modifyBoard(board);
        assertEquals(10,countEmptyFieldsNumber(board));   
        
        gameLevel=GameLevel.LOW;
        board = new SudokuBoard();
        solver.solve(board);
        gameLevel.modifyBoard(board);
        assertEquals(1,countEmptyFieldsNumber(board));
    }

}