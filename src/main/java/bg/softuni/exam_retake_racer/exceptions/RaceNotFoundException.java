package bg.softuni.exam_retake_racer.exceptions;

public class RaceNotFoundException extends RuntimeException {
    public RaceNotFoundException(String name) {
        super("Race with name " + name + " not found.");
    }
}
