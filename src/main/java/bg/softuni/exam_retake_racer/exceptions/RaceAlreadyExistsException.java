package bg.softuni.exam_retake_racer.exceptions;

public class RaceAlreadyExistsException extends RuntimeException {
    public RaceAlreadyExistsException(String name) {
        super("Race with name " + name + " already exists.");
    }
}
