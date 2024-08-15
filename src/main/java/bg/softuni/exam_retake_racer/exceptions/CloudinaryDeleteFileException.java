package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CloudinaryDeleteFileException extends RuntimeException {
    public CloudinaryDeleteFileException() {
        super("There was an error deleting files from Cloudinary");
    }
}
