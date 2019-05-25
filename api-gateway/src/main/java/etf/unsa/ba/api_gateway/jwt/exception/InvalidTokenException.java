package etf.unsa.ba.api_gateway.jwt.exception;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String message) {
        super(message);
    }
}
