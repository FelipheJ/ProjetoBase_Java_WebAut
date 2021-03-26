package br.info.felseje.exceptions;

/**
 * Indicates that the path does not exist.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class PathNotFoundException extends PathException {

    public PathNotFoundException() {
        super();
    }

    public PathNotFoundException(Throwable cause) {
        super(cause);
    }

    public PathNotFoundException(String message) {
        super(message);
    }

    public PathNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
