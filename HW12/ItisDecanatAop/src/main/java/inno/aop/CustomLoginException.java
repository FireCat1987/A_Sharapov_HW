package inno.aop;

public class CustomLoginException extends RuntimeException {
    CustomLoginException(String message) {
        super(message);
    }
}
