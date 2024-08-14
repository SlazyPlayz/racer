package bg.softuni.exam_retake_racer.exceptions;

public class VehicleAlreadyExists extends RuntimeException {
    public VehicleAlreadyExists(String make, String model) {
        super("Vehicle " + make + " " + model + " already exists!");
    }
}
