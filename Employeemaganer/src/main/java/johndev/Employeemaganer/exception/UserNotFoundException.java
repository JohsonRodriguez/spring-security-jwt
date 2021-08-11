package johndev.Employeemaganer.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super (message);
    }
}
