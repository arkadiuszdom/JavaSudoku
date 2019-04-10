package org.View;


import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionTest {
    
    private void throwFileException() throws FileException {
        
        try {
            throw new IndexOutOfBoundsException();
        } catch (Exception ex) {
            throw new FileException("file", ex);           
        }
    }
    
    private void throwDbException() throws DbException {
        
        try {
            throw new IndexOutOfBoundsException();
        } catch (Exception ex) {
            throw new DbException("db", ex);
        }
    }
            
        
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwFileExceptionTest() throws FileException {        
        thrown.expect(FileException.class);
        thrown.expectMessage("file");
        thrown.expectCause(IsInstanceOf.<Throwable>instanceOf(IndexOutOfBoundsException.class));
        throwFileException();
    }
    
    @Test
    public void throwDbExceptionTest() throws DbException {        
        thrown.expect(DbException.class);
        thrown.expectMessage("db");
        thrown.expectCause(IsInstanceOf.<Throwable>instanceOf(IndexOutOfBoundsException.class));
        throwDbException();
    }    
}