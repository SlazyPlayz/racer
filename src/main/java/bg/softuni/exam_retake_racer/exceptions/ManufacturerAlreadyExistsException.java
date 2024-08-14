package bg.softuni.exam_retake_racer.exceptions;

public class ManufacturerAlreadyExistsException extends RuntimeException {
    public ManufacturerAlreadyExistsException(String name) {
        super("Manufacturer " + name + " already exists");
    }
}
