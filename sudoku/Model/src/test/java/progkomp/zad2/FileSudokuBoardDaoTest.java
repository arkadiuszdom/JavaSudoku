package progkomp.zad2;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class FileSudokuBoardDaoTest {

    @Test
    public void FileTest() throws Exception  {
        SudokuBoard board = new SudokuBoard();
        SudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(board);
        SudokuBoard board2 = new SudokuBoard(board.getBoard());

        FileSudokuBoardDao fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("data.txt");
        //FileSudokuBoardDao fileSudokuBoardDao = new  FileSudokuBoardDao("data.txt");
        fileSudokuBoardDao.write(board);
        board = fileSudokuBoardDao.read();
        
        assertTrue(board.equals(board2));        
    }

}