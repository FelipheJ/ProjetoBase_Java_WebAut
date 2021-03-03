package br.info.felseje.commons.exceptions;

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
