package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(String name) {
        super("Track " + name + " was not found");
    }
}
