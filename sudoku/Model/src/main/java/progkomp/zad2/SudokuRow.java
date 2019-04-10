package progkomp.zad2;

public class SudokuRow extends SudokuFieldContainer {

    public SudokuRow(int N, final SudokuField[] fields) {
        super(N, fields);
    }
    
    public SudokuRow getClone() { 
        try { 
            return (SudokuRow) super.clone(); 
        } catch (CloneNotSupportedException e) {
            System.out.println(" Cloning not allowed. "); 
            return this; 
        } 
        
    }

}
