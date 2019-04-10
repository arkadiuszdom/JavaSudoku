package progkomp.zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;

    public FileSudokuBoardDao(final String fileName) {
        this.fileName = fileName;
    }

    public void write(final SudokuBoard obj) {

        try (PrintWriter writer = new PrintWriter(fileName)) { 
       
            writer.write(obj.toString());
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public SudokuBoard read() {

        List<SudokuField> sudokuFieldList = new ArrayList<SudokuField>();
        File file = new File(fileName);
        List<String> readText = new ArrayList<String>();
        if (file.exists()) {
            try {
                readText = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (readText.isEmpty()) {
                return null;
            }
        }
        String[] res = readText.get(0).split(" ");
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
