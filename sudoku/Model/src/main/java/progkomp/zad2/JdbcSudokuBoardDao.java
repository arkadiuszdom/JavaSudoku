package progkomp.zad2;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;


import java.util.ArrayList;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private Connection connection;
    private Statement statement;
    public JdbcSudokuBoardDao(final String dbName, final String user, final String password) throws Exception {
        this.connection=null;
        this.statement=null;
        
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, user, password);
        this.statement = this.connection.createStatement();
    }
    
     
    public void write(final SudokuBoard obj) throws Exception {
        String sql;
        sql = "INSERT INTO boards (data) VALUES(\"" + obj.toString() + "\") ";

        this.statement.executeUpdate(sql);
    }

    public SudokuBoard read() throws Exception {
        List<SudokuField> sudokuFieldList = new ArrayList<SudokuField>();
        String readText=null;
        ResultSet result;
        String sql;
        sql = "SELECT * FROM boards ORDER BY name DESC LIMIT 1";

        result=this.statement.executeQuery(sql);        
        readText=result.getString("data");        
        String[] res = readText.split(" ");
        for (String line : res) {
            sudokuFieldList.add(new SudokuField(Integer.parseInt(line)));
        }

        SudokuBoard board = new SudokuBoard(sudokuFieldList);
        return board;
    }

    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void finalize() throws Throwable {
        
        super.finalize();
    }

}
