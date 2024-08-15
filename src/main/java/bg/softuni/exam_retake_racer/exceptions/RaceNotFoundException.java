package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RaceNotFoundException extends RuntimeException {
    public RaceNotFoundException(String name) {
        super("Race with name " + name + " not found.");
    }
}
