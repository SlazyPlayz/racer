package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(String username, String vehicle) {
        super("Participant " + username + " with vehicle " + vehicle + " not found!");
    }
}
