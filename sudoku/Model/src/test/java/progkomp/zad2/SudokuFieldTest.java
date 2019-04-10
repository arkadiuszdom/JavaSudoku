package progkomp.zad2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SudokuFieldTest {

    @Test
    public void testEquals() {
        SudokuField field1 = new SudokuField(4);
        SudokuField field2 = new SudokuField(2);
        SudokuField field3 = new SudokuField(2);
        Object obj = new Object();
        
        assertFalse(field1.equals(field2));
        assertTrue(field3.equals(field2));
        assertFalse(field1.equals(null));
        assertTrue(field1.equals(field1));   
        assertFalse(field1.equals(obj));

    }
    
    @Test
    public void testToString() {
        SudokuField field = new SudokuField(4);
        assertEquals(field.toString(),"4");  

    }
    
    @Test
    public void testHashCode() {
        SudokuField field1 = new SudokuField(4);
        SudokuField field2 = new SudokuField(2);
        SudokuField field3 = new SudokuField(2);
        Object obj = new Object();
        
        assertFalse(field1.hashCode() == field2.hashCode());
        assertTrue(field3.hashCode() == field2.hashCode());
        assertTrue(field1.hashCode() == field1.hashCode());   
        assertFalse(field1.hashCode() == obj.hashCode());

    }

}