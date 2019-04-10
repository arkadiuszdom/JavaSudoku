package progkomp.zad2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SudokuBoxTest {
    
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
        SudokuBox box1 = new SudokuBox(N, fields1);
        SudokuBox box2 = new SudokuBox(N, fields2);
        SudokuBox box3 = new SudokuBox(N, fields3);

        assertFalse(box1.equals(box2));
        assertTrue(box3.equals(box2));
        assertFalse(box1.equals(null));
        assertTrue(box1.equals(box1));

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
        SudokuBox box1 = new SudokuBox(N, fields1);
        SudokuBox box2 = new SudokuBox(N, fields2);
        SudokuBox box3 = new SudokuBox(N, fields3);

        assertFalse(box1.hashCode() == box2.hashCode());
        assertTrue(box3.hashCode() == box2.hashCode());
        assertTrue(box1.hashCode() == box1.hashCode());
        assertTrue(box1.hashCode() == obj.hashCode());

    }
    
    @Test
    public void testToString() {
        int N = 9;
        SudokuField[] fields = new SudokuField[] { new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2), new SudokuField(2),
                new SudokuField(2) };
        SudokuBox box = new SudokuBox(N, fields);

        assertEquals(box.toString(),("2 2 2 2 2 2 2 2 2"));
    }
}
