package org.View;

public class DbException extends Exception {
 
    public DbException(final String message, final Throwable cause) {
        super(message, cause);
    }
 
    public DbException(final String message) {
        super(message);
    }
}