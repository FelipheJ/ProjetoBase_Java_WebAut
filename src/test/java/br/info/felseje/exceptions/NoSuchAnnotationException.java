package br.info.felseje.exceptions;

/**
 * Indicates that the annotation was not found
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class NoSuchAnnotationException extends RuntimeException {

    public NoSuchAnnotationException() {
        super();
    }

    public NoSuchAnnotationException(Throwable cause) {
        super(cause);
    }

    public NoSuchAnnotationException(String message) {
        super(message);
    }

    public NoSuchAnnotationException(Throwable cause, String message) {
        super(message, cause);
    }
}
