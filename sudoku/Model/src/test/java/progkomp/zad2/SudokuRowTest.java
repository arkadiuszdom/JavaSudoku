package progkomp.zad2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SudokuRowTest {

    @Test
    public void testEquals() {
        int N = 9;
        SudokuField[] fields1 = new SudokuField[] { new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuField[] fields2 = new SudokuField[] { new SudokuField(2), new SudokuField(1), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuField[] fields3 = new SudokuField[] { new SudokuField(2), new SudokuField(1), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        Object obj = new Object();
        SudokuRow row1 = new SudokuRow(N, fields1);
        SudokuRow row2 = new SudokuRow(N, fields2);
        SudokuRow row3 = new SudokuRow(N, fields3);

        assertFalse(row1.equals(row2));
        assertTrue(row3.equals(row2));
        assertFalse(row1.equals(null));
        assertTrue(row1.equals(row1));
        assertFalse(row1.equals(obj));

    }
    
    @Test
    public void testToString() {
        int N = 9;
        SudokuField[] fields = new SudokuField[] { new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuRow row = new SudokuRow(N, fields);

        assertEquals(row.toString(),("2 2 2 2 2 2 2 2 2"));
    }
    
    public void testHashCode() {
        int N = 9;
        SudokuField[] fields1 = new SudokuField[] { new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuField[] fields2 = new SudokuField[] { new SudokuField(2), new SudokuField(1), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuField[] fields3 = new SudokuField[] { new SudokuField(2), new SudokuField(1), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        Object obj = new Object();
        SudokuRow row1 = new SudokuRow(N, fields1);
        SudokuRow row2 = new SudokuRow(N, fields2);
        SudokuRow row3 = new SudokuRow(N, fields3);

        assertFalse(row1.hashCode() == row2.hashCode());
        assertTrue(row3.hashCode() == row2.hashCode());
        assertTrue(row1.hashCode() == row1.hashCode());
        assertTrue(row1.hashCode() == obj.hashCode());

    }

}
