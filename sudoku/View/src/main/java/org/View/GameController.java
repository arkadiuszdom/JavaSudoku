package org.View;


import javafx.scene.control.Label;
import java.util.logging.Level;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import progkomp.zad2.BacktrackingSudokuSolver;
import progkomp.zad2.FileSudokuBoardDao;
import progkomp.zad2.JdbcSudokuBoardDao;
import progkomp.zad2.SudokuBoard;
import progkomp.zad2.SudokuBoardDaoFactory;
import progkomp.zad2.SudokuField;
import javafx.util.StringConverter;

import progkomp.zad2.AuthorsResourceBundle;
@SuppressWarnings("restriction")
public class GameController implements Initializable {

    @FXML
    private GridPane boardGrid;
    private SudokuBoard board;
    private FileSudokuBoardDao fileSudokuBoardDao;    
    private JdbcSudokuBoardDao jdbcSudokuBoardDao;   
    private GameLevel difficultySelected;
    private ResourceBundle languageBundle;
    private ResourceBundle authorsBundle;
    @FXML
    private ToggleButton r1;
    @FXML
    private ToggleButton r2;
    @FXML
    private ToggleButton r3;
    @FXML 
    private Label firstAuthor;
    @FXML 
    private Label secondAuthor;

    @FXML
    private Button play;
    @FXML
    private Button save;
    @FXML
    private Button load;
    @FXML
    private Button insert;
    @FXML
    private Button select;
    @FXML
    private Button english;
    @FXML
    private Button polish;
    

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void setDifficulty(final Level difficulty) {
        this.difficultySelected = GameLevel.HIGH;
    }
    public void initialize(final URL location, final ResourceBundle resources) {
        int SIZE = 9;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                TextField textField = new TextField();
                textField.setPrefHeight(30);
                textField.setPrefWidth(30);
                textField.setAlignment(Pos.CENTER);
                textField.setEditable(false);
               
                this.boardGrid.add(textField, i, j);
            }
        }

        play.setOnAction(event -> {
            startUp();
        });
        save.setOnAction(event -> {
            try {
                saveBoard();
            } catch (FileException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e); 
            }
            
        });
        load.setOnAction(event -> {
            try {
                loadBoard();
            } catch (FileException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        });
        insert.setOnAction(event -> {
            try {
                insertBoard();
            } catch (DbException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e); 
            }
            
        });
        select.setOnAction(event -> {
            try {
                selectBoard();
            } catch (DbException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        });
        english.setOnAction(event -> {
            setLanguage("en", "US");            
        });
        polish.setOnAction(event -> {
            setLanguage("pl", "PL");
        });
        
        setLanguage("en", "US");
        
        this.authorsBundle = new AuthorsResourceBundle();
        this.firstAuthor.setText(this.authorsBundle.getString("FirstAuthorFirstName") +  " "  + this.authorsBundle.getString("FirstAuthorLastName") + " " + this.authorsBundle.getString("FirstAuthorIndex"));
        this.secondAuthor.setText(this.authorsBundle.getString("SecondAuthorFirstName") + " " + this.authorsBundle.getString("SecondAuthorLastName") + " " + this.authorsBundle.getString("SecondAuthorIndex"));
    
        try {
            MyLogger.setup(languageBundle);
        } catch (FileException e) {
            e.printStackTrace();
            //nie mamy loggera
        }    
        

        
        
    }
    public void setLabels() {
        english.setText(this.languageBundle.getString("english"));
        polish.setText(this.languageBundle.getString("polish"));
        play.setText(this.languageBundle.getString("play"));
        save.setText(this.languageBundle.getString("save"));
        load.setText(this.languageBundle.getString("load"));
        insert.setText(this.languageBundle.getString("insert"));
        select.setText(this.languageBundle.getString("select"));
        r1.setText(this.languageBundle.getString("easy"));
        r2.setText(this.languageBundle.getString("medium"));
        r3.setText(this.languageBundle.getString("hard"));
    }
    public void setLanguage(final String first, final String second) {
        Locale.setDefault(new Locale(first, second));
        this.languageBundle = ResourceBundle.getBundle("Language");        
        setLabels();
        
    }
    public void startUp() {
        if (!(r1.isSelected() ^ r2.isSelected() ^ r3.isSelected()) || (r1.isSelected() && r2.isSelected() && r3.isSelected())) {
            r1.setSelected(false);
            r2.setSelected(false);
            r3.setSelected(false);
            return;            
        }
        if (r1.isSelected()) {
            this.difficultySelected = GameLevel.LOW;
        } else if (r2.isSelected()) {
            this.difficultySelected = GameLevel.MEDIUM;
        } else {
            this.difficultySelected = GameLevel.HIGH;
        } 

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

        this.board = new SudokuBoard();
        solver.solve(this.board);

        this.difficultySelected.modifyBoard(this.board);
        try {
            setDbDao();
        } catch (DbException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e); 
        }
        try {
            setFileDao();
        } catch (FileException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e); 
        }
        this.displaySudokuBoard();
    }
    
    public void setFileDao() throws FileException {
        try {
            this.fileSudokuBoardDao = SudokuBoardDaoFactory.getFileDao("data.txt");
        } catch (Exception ex) {
            throw new FileException(this.languageBundle.getString("file"), ex);
        }
        
    }
    public void setDbDao() throws DbException {
        try {
            this.jdbcSudokuBoardDao = SudokuBoardDaoFactory.getDbDao("sudoku", "root", "");
        } catch (Exception ex) {
            throw new DbException(this.languageBundle.getString("db"), ex);
        }

    }
    public void saveBoard() throws FileException {
        try {
            this.fileSudokuBoardDao.write(this.board);
        } catch (Exception ex) {
            throw new FileException(this.languageBundle.getString("file"), ex);
        }

        LOGGER.log(Level.INFO, "save: " + this.board.toString());
    }
    public void loadBoard() throws FileException {
        try {
            this.board = this.fileSudokuBoardDao.read();
        } catch (Exception ex) {
            throw new FileException(this.languageBundle.getString("file"), ex);
        }
        
        LOGGER.log(Level.INFO, "load: " + this.board.toString());
        
        displaySudokuBoard();     
    }
    public void insertBoard() throws DbException {
        
        try {
            this.jdbcSudokuBoardDao.write(this.board);
        } catch (Exception ex) {
            throw new DbException(this.languageBundle.getString("db"), ex);
        }

        LOGGER.log(Level.INFO, "save: " + this.board.toString());
    }
    public void selectBoard() throws DbException {
        try {
            this.board = this.jdbcSudokuBoardDao.read();
        } catch (Exception ex) {
            throw new DbException(this.languageBundle.getString("db"), ex);
        }
        
        LOGGER.log(Level.INFO, "load: " + this.board.toString());
        
        displaySudokuBoard();     
    }
    public void displaySudokuBoard() {
        
        StringConverter<Number> converter = new StringConverter<Number>() {

            @Override
            public String toString(final Number object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Number fromString(final String string) {
                if (string == null) {
                    return 0;
                } else {
                    return Integer.parseInt(string);
                }
            }

        };
        for (int i = 0; i < board.N; i++) {
            for (int j = 0; j < board.N; j++) {
                TextField textField = new TextField();
                textField.setPrefHeight(30);
                textField.setPrefWidth(30);
                textField.setAlignment(Pos.CENTER);
                textField.setEditable(true);
                textField.setId(Integer.toString(i * board.N + j));
                if (this.board.getField(i, j).getFieldValue() != 0) {
                    textField.setText(Integer.toString(this.board.getField(i, j).getFieldValue()));

                }                
               
               textField.textProperty().bindBidirectional(this.board.getField(i, j).getField(), converter);

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                                       
                    if (!StringUtils.isNumeric(newValue) || (Integer.parseInt(newValue) < 0 || Integer.parseInt(newValue) > 9)) {
                        textField.textProperty().set(oldValue);
                    } else {
                        board.setField(Integer.parseInt(textField.getId()), new SudokuField(Integer.parseInt(newValue)));
                        textField.textProperty().set(newValue);
                    }
                    
                    if (this.board.checkBoard()) {
                        Stage stage = (Stage) textField.getScene().getWindow();
                        stage.close();                        
                    }
                    
                    LOGGER.log(Level.INFO, "wpis: " + this.board.toString());
                });

                this.boardGrid.add(textField, i, j);
            }
        }
    }

}
