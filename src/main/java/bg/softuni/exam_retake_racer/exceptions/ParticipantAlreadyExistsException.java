package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParticipantAlreadyExistsException extends RuntimeException {
    public ParticipantAlreadyExistsException(String username, String vehicle) {
        super("Participant " + username + " with vehicle " + vehicle + " already exists!");
    }
}
