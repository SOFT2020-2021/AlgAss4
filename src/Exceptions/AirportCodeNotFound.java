package Exceptions;

public class AirportCodeNotFound extends RuntimeException {
    public AirportCodeNotFound(String errorMessage) {
        super(errorMessage);
    }
}
