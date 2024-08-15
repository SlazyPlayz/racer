package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RaceAlreadyExistsException extends RuntimeException {
    public RaceAlreadyExistsException(String name) {
        super("Race with name " + name + " already exists.");
    }
}
