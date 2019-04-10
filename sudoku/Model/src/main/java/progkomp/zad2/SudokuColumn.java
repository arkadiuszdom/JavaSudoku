package progkomp.zad2;

public class SudokuColumn extends SudokuFieldContainer{

    public SudokuColumn(int N, final SudokuField[] fields) {
        super(N, fields);
    }
    
    public SudokuColumn getClone() { 
        try { 
            return (SudokuColumn) super.clone(); 
        } catch (CloneNotSupportedException e) {
            System.out.println(" Cloning not allowed. "); 
            return this; 
        } 
        
    }
}
