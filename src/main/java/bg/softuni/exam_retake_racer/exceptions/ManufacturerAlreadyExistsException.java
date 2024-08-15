package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ManufacturerAlreadyExistsException extends RuntimeException {
    public ManufacturerAlreadyExistsException(String name) {
        super("Manufacturer " + name + " already exists");
    }
}
