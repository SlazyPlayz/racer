package bg.softuni.exam_retake_racer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrganizerWithIdentificationNumberNotFoundException extends RuntimeException {
    public OrganizerWithIdentificationNumberNotFoundException(String identificationNumber) {
        super("Organizer with identification number " + identificationNumber + " was not found");
    }
}
