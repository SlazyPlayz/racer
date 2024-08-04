package bg.softuni.exam_retake_racer.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("User with username: " + username + " already exists");
    }
}
