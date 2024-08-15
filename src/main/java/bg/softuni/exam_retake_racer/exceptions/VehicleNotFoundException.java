package bg.softuni.exam_retake_racer.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String make) {
        super("Vehicle from " + make + " not found!");
    }

    public VehicleNotFoundException(String make, String model) {
        super("Vehicle " + model + "(" + make + ") not found!");
    }
}
