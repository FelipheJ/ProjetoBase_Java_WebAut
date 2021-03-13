package br.info.felseje.exceptions;

/**
 * Indicates that the path does not exist.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class PathNotImplementedException extends RuntimeException {

    public PathNotImplementedException() {
        super();
    }

    public PathNotImplementedException(Throwable cause) {
        super(cause);
    }

    public PathNotImplementedException(String message) {
        super(message);
    }

    public PathNotImplementedException(Throwable cause, String message) {
        super(message, cause);
    }
}
