package progkomp.zad2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class JdbcSudokuBoardDaoTest {

    @Test
    public void DbTest() throws Exception  {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        SudokuBoard board2 = new SudokuBoard(board.getBoard());

       /* JdbcSudokuBoardDao jdbcSudokuBoardDao = SudokuBoardDaoFactory.getDbDao("sudoku", "root", "");
        jdbcSudokuBoardDao.write(board);
        board = jdbcSudokuBoardDao.read();
        
        assertTrue(board.equals(board2));     */   
    }

}