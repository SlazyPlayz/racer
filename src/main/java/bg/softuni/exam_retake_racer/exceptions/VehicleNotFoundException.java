package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String make) {
        super("Vehicle from " + make + " not found!");
    }

    public VehicleNotFoundException(String make, String model) {
        super("Vehicle " + model + "(" + make + ") not found!");
    }
}
