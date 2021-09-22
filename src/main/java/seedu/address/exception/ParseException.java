package seedu.address.exception;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
