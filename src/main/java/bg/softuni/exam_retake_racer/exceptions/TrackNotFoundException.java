package bg.softuni.exam_retake_racer.exceptions;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(String name) {
        super("Track " + name + " was not found");
    }
}
