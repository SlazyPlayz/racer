package bg.softuni.exam_retake_racer.exceptions;

public class ImageUploadError extends RuntimeException {
    public ImageUploadError() {
        super("There was an error when uploading the image");
    }
}
