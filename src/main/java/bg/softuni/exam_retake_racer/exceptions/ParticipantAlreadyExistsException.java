package bg.softuni.exam_retake_racer.exceptions;

public class ParticipantAlreadyExistsException extends RuntimeException {
    public ParticipantAlreadyExistsException(String username, String vehicle) {
        super("Participant " + username + " with vehicle " + vehicle + " already exists!");
    }
}
