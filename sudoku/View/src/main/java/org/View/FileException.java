package org.View;

public class FileException extends Exception {
 
    public FileException(final String message, final Throwable cause) {
        super(message, cause);
    }
 
    public FileException(final String message) {
        super(message);
    }
}