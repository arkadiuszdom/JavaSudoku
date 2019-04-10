package org.View;

import java.util.ArrayList;
import java.util.Collections;

import progkomp.zad2.SudokuBoard;
import progkomp.zad2.SudokuField;

public enum GameLevel {

    HIGH  (20),  
    MEDIUM(10), 
    LOW   (1);  


    private final int LEVEL_CODE;

    private GameLevel(int levelCode) {
        this.LEVEL_CODE = levelCode;
    }
    public void modifyBoard(final SudokuBoard board) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i < board.N * board.N; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i < this.LEVEL_CODE; i++) {
            board.setField(list.get(i), new SudokuField(0));
        }
        
    }
}
