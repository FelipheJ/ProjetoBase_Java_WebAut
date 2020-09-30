package br.com.projeto.exceptions;

public class SystemNotSupportedException extends Exception {

    /**
     * Lança uma exceção de sistema incompatível.
     */
    public SystemNotSupportedException() {
        super();
    }

    /**
     * Lança uma exceção de sistema incompatível.
     *
     * @param message - Mensagem.
     */
    public SystemNotSupportedException(String message) {
        super(message);
    }

    /**
     * Lança uma exceção de sistema incompatível.
     *
     * @param message - Mensagem.
     * @param cause   - Causa.
     */
    public SystemNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

}
