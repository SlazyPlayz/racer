package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrganizerWithNameNotFoundException extends RuntimeException {
    public OrganizerWithNameNotFoundException(String name) {
        super("Organizer " + name + " not found!");
    }
}
