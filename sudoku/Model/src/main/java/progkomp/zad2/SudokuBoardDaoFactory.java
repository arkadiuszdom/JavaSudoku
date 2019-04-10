package progkomp.zad2;

public class SudokuBoardDaoFactory {
    public static FileSudokuBoardDao getFileDao(final String fileName) {
       return new FileSudokuBoardDao(fileName);            
    }
    public static JdbcSudokuBoardDao getDbDao(final String dbName, final String user, final String password) throws Exception {
        return new JdbcSudokuBoardDao(dbName, user, password);            
     }

}
