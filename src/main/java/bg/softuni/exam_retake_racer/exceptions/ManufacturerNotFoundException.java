package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(String name) {
        super("Manufacturer " + name + " not found");
    }
}
