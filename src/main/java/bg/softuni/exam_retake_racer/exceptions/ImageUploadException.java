package bg.softuni.exam_retake_racer.exceptions;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException() {
        super("There was an error when uploading the image");
    }
}
