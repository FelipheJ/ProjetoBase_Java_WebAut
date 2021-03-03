package br.info.felseje.commons.exceptions;

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
