package progkomp.zad2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuBoard implements Cloneable, Serializable {
    public final int N = 9;
    public final int n = (int) Math.sqrt(N);
    private List<SudokuField> board;

    public SudokuBoard() {
        this.board = Arrays.asList(new SudokuField[N * N]);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.setField(i, j, new SudokuField(0));
            }
        }
    }

    public SudokuBoard(final List<SudokuField> sudokuFieldList) {
        this.board = Arrays.asList(new SudokuField[N * N]);
        if (sudokuFieldList.size() != N * N) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.setField(i, j, new SudokuField(sudokuFieldList.get(i * N + j).getFieldValue()));
            }
        }
    }
    public SudokuBoard(final SudokuField[][] sudokuFieldList) {
        this.board = Arrays.asList(new SudokuField[N * N]);
        if (sudokuFieldList.length != N) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < N; i++) {
            if (sudokuFieldList[i].length != N) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j < N; j++) {
                this.setField(i, j, new SudokuField(sudokuFieldList[i][j].getFieldValue()));
            }
        }
    }
    public SudokuField[][] getBoard() {
        SudokuField[][] fields = new SudokuField[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fields[i][j] = new SudokuField(this.getField(i, j).getFieldValue());
            }
        }
        return fields;
    }

    public SudokuField getField(int x, int y) {

        return new SudokuField(board.get(x * N + y).getFieldValue());

    }

    public void setField(int x, int y, final SudokuField field) {
        board.set(x * N + y, field);
    }
    public void setField(int pos, final SudokuField field) {
        board.set(pos, field);
    }

    public SudokuRow getRow(int y) {
        SudokuField[] fields = new SudokuField[N];
        for (int i = 0; i < N; i++) {
            fields[i] = this.getField(i, y);
        }
        return new SudokuRow(N, fields);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] fields = new SudokuField[N];
        for (int i = 0; i < N; i++) {
            fields[i] = this.getField(x, i);
        }

        return new SudokuColumn(N, fields);
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] fields = new SudokuField[N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fields[i * n + j] = this.getField(x + i, y + j);
            }
        }

        return new SudokuBox(N, fields);
    }

    public boolean checkBoard() {

        for (int i = 0; i < N; i++) {
            if (!this.getColumn(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < N; i++) {
            if (!this.getRow(i).verify()) {
                return false;
            }
        }

        for (int i = 0; i < N; i += n) {
            for (int j = 0; j < N; j += n) {
                if (!this.getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        int i;
        for (i = 0; i < N * N - 1; i++) {
            result += board.get(i).toString() + " ";
        }
        return result + board.get(i).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(143, 2137).append(board).append(N).append(n).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        SudokuBoard sudokuBoard = (SudokuBoard) obj;
        return new EqualsBuilder().append(board, sudokuBoard.board).append(N, sudokuBoard.N).append(n, sudokuBoard.n)
                .isEquals();
    }
    public SudokuBoard clone() { 
        try { 
            return (SudokuBoard) super.clone(); 
        } catch (CloneNotSupportedException e) {
            System.out.println(" Cloning not allowed. "); 
            return this; 
        } 
        
    }
};