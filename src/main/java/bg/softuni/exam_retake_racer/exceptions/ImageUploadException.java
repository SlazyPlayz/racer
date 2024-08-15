package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageUploadException extends RuntimeException {
    public ImageUploadException() {
        super("There was an error when uploading the image");
    }
}
