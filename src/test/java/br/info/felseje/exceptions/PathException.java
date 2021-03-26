package br.info.felseje.exceptions;

/**
 * Indicates that the path does not exist.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class PathException extends RuntimeException {

    public PathException() {
        super();
    }

    public PathException(Throwable cause) {
        super(cause);
    }

    public PathException(String message) {
        super(message);
    }

    public PathException(String message, Throwable cause) {
        super(message, cause);
    }
}
