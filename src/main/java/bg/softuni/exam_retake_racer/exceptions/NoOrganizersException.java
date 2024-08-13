package bg.softuni.exam_retake_racer.exceptions;

public class NoOrganizersException extends RuntimeException {
    public NoOrganizersException() {
        super("No organizers exist");
    }
}
