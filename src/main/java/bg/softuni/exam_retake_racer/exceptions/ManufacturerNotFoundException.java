package bg.softuni.exam_retake_racer.exceptions;

public class ManufacturerNotFoundException extends RuntimeException {
    public ManufacturerNotFoundException(String name) {
        super("Manufacturer " + name + " not found");
    }
}
