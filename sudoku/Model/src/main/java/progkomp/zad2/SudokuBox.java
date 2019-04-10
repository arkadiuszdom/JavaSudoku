package progkomp.zad2;

public class SudokuBox extends SudokuFieldContainer{

    public SudokuBox(int N, final SudokuField[] fields) {
        super(N, fields);
    }
    
    public SudokuBox getClone() { 
        try { 
            return (SudokuBox) super.clone(); 
        } catch (CloneNotSupportedException e) {
            System.out.println(" Cloning not allowed. "); 
            return this; 
        } 
        
    }

}
