package bg.softuni.exam_retake_racer.exceptions;

public class TrackAlreadyExistsError extends RuntimeException {
    public TrackAlreadyExistsError(String name) {
        super("Track " + name + " already exists");
    }
}
