package bg.softuni.exam_retake_racer.exceptions;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String username) {
        super("User with username " + username + " already exists.");
    }
}
