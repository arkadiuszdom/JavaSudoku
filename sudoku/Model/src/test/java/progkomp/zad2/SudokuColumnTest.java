package progkomp.zad2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SudokuColumnTest {
    
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
        SudokuColumn col1 = new SudokuColumn(N, fields1);
        SudokuColumn col2 = new SudokuColumn(N, fields2);
        SudokuColumn col3 = new SudokuColumn(N, fields3);

        assertFalse(col1.equals(col2));
        assertTrue(col3.equals(col2));
        assertFalse(col1.equals(null));
        assertTrue(col1.equals(col1));

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
        SudokuColumn col1 = new SudokuColumn(N, fields1);
        SudokuColumn col2 = new SudokuColumn(N, fields2);
        SudokuColumn col3 = new SudokuColumn(N, fields3);

        assertFalse(col1.hashCode() == col2.hashCode());
        assertTrue(col3.hashCode() == col2.hashCode());
        assertTrue(col1.hashCode() == col1.hashCode());
        assertTrue(col1.hashCode() == obj.hashCode());

    }

    @Test
    public void testToString() {
        int N = 9;
        SudokuField[] fields = new SudokuField[] { new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuColumn col = new SudokuColumn(N, fields);

        assertEquals(col.toString(),("2 2 2 2 2 2 2 2 2"));
    }
}
