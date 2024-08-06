package com.futguesser.exceptions;

/**
 *
 * @author 34661
 */
public class UsuarioAlredyExistsException extends RuntimeException{
    public UsuarioAlredyExistsException(String message) {
        super(message);
    }

    public UsuarioAlredyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
