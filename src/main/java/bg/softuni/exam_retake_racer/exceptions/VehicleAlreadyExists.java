package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VehicleAlreadyExists extends RuntimeException {
    public VehicleAlreadyExists(String make, String model) {
        super("Vehicle " + make + " " + model + " already exists!");
    }
}
