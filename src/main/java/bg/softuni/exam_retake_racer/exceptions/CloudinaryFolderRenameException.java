package bg.softuni.exam_retake_racer.exceptions;

public class CloudinaryFolderRenameException extends RuntimeException {
    public CloudinaryFolderRenameException() {
        super("There was an error when renaming the folder");
    }
}
