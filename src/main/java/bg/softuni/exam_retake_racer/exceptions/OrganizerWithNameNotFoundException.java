package bg.softuni.exam_retake_racer.exceptions;

public class OrganizerWithNameNotFoundException extends RuntimeException {
    public OrganizerWithNameNotFoundException(String name) {
        super("Organizer " + name + " not found!");
    }
}
