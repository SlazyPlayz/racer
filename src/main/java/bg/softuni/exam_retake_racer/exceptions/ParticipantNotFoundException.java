package bg.softuni.exam_retake_racer.exceptions;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(String username, String vehicle) {
        super("Participant " + username + " with vehicle " + vehicle + " not found!");
    }
}
