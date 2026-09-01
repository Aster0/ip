package carl.exceptions;

/**
 * Represents the base exception for all custom exceptions in the Carl application.
 */
public class CarlException extends Exception {

    /**
     * Constructs a new {@code CarlException} with the specified error message.
     *
     * @param message The detail message explaining the exception.
     */
    public CarlException(String message) {
        super(message);
    }

}
