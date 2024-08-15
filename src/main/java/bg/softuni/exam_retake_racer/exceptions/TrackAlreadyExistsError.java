package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrackAlreadyExistsError extends RuntimeException {
    public TrackAlreadyExistsError(String name) {
        super("Track " + name + " already exists");
    }
}
